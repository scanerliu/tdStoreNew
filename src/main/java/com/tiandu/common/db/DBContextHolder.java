package com.tiandu.common.db;

import org.apache.log4j.Logger;

public class DBContextHolder {
	/**
	 * 数据源线程池
	 */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	private static Logger log = Logger.getLogger(DBContextHolder.class);

	public static String DB_RW = "readwritedb";
	public static String DB_R = "readdb";

	public static String getDbType() {
		String db = contextHolder.get();
		if (db == null) {
			db = DB_RW;// 默认读写数据源
		}
		log.debug("当前数据源:" + db);
		return db;
	}

	/**
	 * 设置当前数据源 
	 * @param str
	 */
	public static void setDbType(String str) {
		log.debug("设置当前数据源:" + str);
		contextHolder.set(str);
	}

	/**
	 * clearDBType
	 * @Title: clearDBType
	 * @Description: 清除上下文数据
	 */
	public static void clearDBType() {
		contextHolder.remove();
	}
}
