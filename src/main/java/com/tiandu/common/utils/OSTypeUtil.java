package com.tiandu.common.utils;

public class OSTypeUtil {
	
	public static final String windowsLike = "WINDOWS";
	public static final String linuxLike = "LINUX";
	public static final String unknowedOS = "UNKNOWEDOS";
	
	public static String getOSType(){
		String osName = System.getProperty("os.name");
		if(osName.indexOf("Windows") != -1){
			return windowsLike;
		}
		if(osName.indexOf("Linux") != -1){
			return linuxLike;
		}
		return unknowedOS;
	}
}
