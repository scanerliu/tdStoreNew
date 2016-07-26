package com.tiandu.common.db.dialect;

public class MySqlDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();    
        StringBuffer sb = new StringBuffer(sql.length() + 100);
        sb.append(sql);
        sb.append(" limit ").append(offset).append(",").append(limit);
        return sb.toString();    
	}

}
