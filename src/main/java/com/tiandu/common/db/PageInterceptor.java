package com.tiandu.common.db;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import com.tiandu.common.db.dialect.Dialect;
import com.tiandu.common.db.dialect.MySqlDialect;
import com.tiandu.common.db.dialect.OracleDialect;
import com.tiandu.common.search.SearchCriteria;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {
		 //日志对象    
	    protected static Logger log = Logger.getLogger(PageInterceptor.class);	    
	    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
		private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
		private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
		
		public Object intercept(Invocation invocation) throws Throwable {
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			BoundSql boundSql2 = statementHandler.getBoundSql();
			MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
			Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
			String pageSqlId = configuration.getVariables().getProperty("pageSqlId"); 
			if(null==pageSqlId || "".equals(pageSqlId)){
				return invocation.proceed();
			}
			MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
			
			if(mappedStatement.getId().matches(pageSqlId)){
				SearchCriteria  sc = (SearchCriteria) boundSql2.getParameterObject();		
				if (sc == null || !sc.isFlag()) {
					return invocation.proceed();
				}
				RowBounds rowBounds = new RowBounds((sc.getPageNo()-1<0?0:(sc.getPageNo()-1))*sc.getPageSize(),sc.getPageSize());
				String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
				
				Dialect.Type databaseType = null;
				try {
					databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
				} catch (Exception e) {
					log.error("mybatis-config.xml中未设置数据库类型");
				}
				if (databaseType == null) {
					throw new RuntimeException(
							"the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
				}
				Dialect dialect = null;
				switch (databaseType) {
					case ORACLE: // oracle 分页
						dialect = new OracleDialect();
						break;
					case MYSQL: // MySQL分页
						dialect = new MySqlDialect();
						break;
				}
				metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
				metaStatementHandler.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit",RowBounds.NO_ROW_LIMIT);
				if (log.isDebugEnabled()) {
					BoundSql boundSql = statementHandler.getBoundSql();
					log.debug(" 生成分页SQL : " + boundSql.getSql());
				}
			}
			return invocation.proceed();
		}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}
