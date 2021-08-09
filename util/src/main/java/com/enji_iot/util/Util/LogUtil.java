package com.enji_iot.util.Util;


import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;


public class LogUtil{
	
	private final static Log LOGGER = LogFactory.getLog(LogUtil.class);
	
	private  static RuntimeMXBean runtimeMXBean=ManagementFactory.getRuntimeMXBean();
	
	public static void errorLog(Exception e  ){
		e.printStackTrace();
		errorLog(e.getLocalizedMessage());
	}	
	
	//日志记录错误信息
	public static void errorLog(String errorMessage){
		StringBuffer sb=new StringBuffer();
		sb.append("pid:");
		sb.append(getPid());
		sb.append(",");
		sb.append(getClassName());
		sb.append(".");
		sb.append(getMethodName());
		sb.append("(");
		sb.append(getClassNameWithoutPackageName());
		sb.append(".java:");
		sb.append(getLineNumber());
		sb.append("),");
		sb.append("error:");
		sb.append(errorMessage);
		LOGGER.error(sb.toString());
	}	
	//日志记录错误信息
	public static void errorLog(Object[] parametersName,Object[]parametersValue,String errorMessage){
		StringBuffer sb=new StringBuffer();
		sb.append("pid:");
		sb.append(getPid());
		sb.append(",");
		sb.append(getClassName());
		sb.append(".");
		sb.append(getMethodName());
		sb.append("(");
		sb.append(getClassNameWithoutPackageName());
		sb.append(".java:");
		sb.append(getLineNumber());
		sb.append("),");
		sb.append("parameters:[");
		sb.append(getPatameters(parametersName,parametersValue).toString());
		sb.append("],error:");
		sb.append(errorMessage);
		LOGGER.error(sb.toString());
	}
	
	//日志记录信息
	public static void infoLog(String msg){
		StringBuffer sb=new StringBuffer();
		sb.append("pid:");
		sb.append(getPid());
		sb.append(",");
		sb.append(getClassName());
		sb.append(".");
		sb.append(getMethodName());
		sb.append("(");
		sb.append(getClassNameWithoutPackageName());
		sb.append(".java:");
		sb.append(getLineNumber());
		sb.append("),");
		sb.append("info:");
		sb.append(msg);
		LOGGER.info(sb.toString());
	}		
	//日志记录信息
	public static void infoLog(Object[] parametersName,Object[]parametersValue,String msg){
		StringBuffer sb=new StringBuffer();
		sb.append("pid:");
		sb.append(getPid());
		sb.append(",");
		sb.append(getClassName());
		sb.append(".");
		sb.append(getMethodName());
		sb.append("(");
		sb.append(getClassNameWithoutPackageName());
		sb.append(".java:");
		sb.append(getLineNumber());
		sb.append("),");
		sb.append("parameters:[");
		sb.append(getPatameters(parametersName,parametersValue).toString());
		sb.append("],info:");
		sb.append(msg);
		LOGGER.info(sb.toString());
	}
	
	//获取当前进程号
	private static String getPid(){
		return runtimeMXBean.getName().split("@")[0];
	}
	//获取当前运行类的类名(带包名称)
	private static String getClassName(){
		return Thread.currentThread().getStackTrace()[3].getClassName();
	}
	//获取当前运行类的的方法名
	private static String getMethodName(){
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}
	//获取类实现的接口集合
	@SuppressWarnings("unused")
	private static Class<?>[] getInterfaces(){
		Class<?>[] interfaces=getCurrentClass().getInterfaces();
		return interfaces;
	}
	//获取当前运行类的class 对象
	private static Class<?> getCurrentClass(){
		Class<?> currentClass=null;
		try {
			//通过类名称加载一个类来获取到当前类实例
			 currentClass=Class.forName(Thread.currentThread().getStackTrace()[3].getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return currentClass;
		
	}
	//获取当前运行类的行号
	private static int getLineNumber(){
		return Thread.currentThread().getStackTrace()[3].getLineNumber();
	}
	//获取当前运行类的类名(不含包名称)
	private static String getClassNameWithoutPackageName(){
		String []str=Thread.currentThread().getStackTrace()[3].getClassName().split("\\.");
		return str[str.length-1];
	}
	
	//根据不同的参数类型，获取到相应的参数名，参数值
	private static StringBuffer getPatameters(Object[] parametersName,Object[]parametersValue){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<parametersName.length;i++){
			//如果是List类型
			if(parametersValue[i] instanceof List){
				sb.append(parametersValue[i].getClass().getName());
				sb.append(":");
				String json;
				try {
					json = JsonMarshaller.marshal(parametersValue[i]);
					sb.append(json+"  ");
				} catch (JsonGenerationException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(!(parametersValue[i] instanceof Integer)&&
					   !(parametersValue[i] instanceof Double)&&
					   !(parametersValue[i] instanceof Long)&&
					   !(parametersValue[i] instanceof String)&&
					   parametersValue[i] instanceof Object){
				sb.append(parametersValue[i].getClass().getName());
				sb.append(":");
				try {
					String json=JsonMarshaller.marshal(parametersValue[i]);
					sb.append(json+"  ");
				} catch (JsonGenerationException e1) {
					e1.printStackTrace();
				} catch (JsonMappingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				if(parametersValue[i]!=null){
					sb.append("\""+parametersName[i]+"\"");
					sb.append(":");
					
					if(parametersValue[i] instanceof String){
						sb.append("\""+parametersValue[i]+"\"");
					}else{
						sb.append(parametersValue[i]);
					}
					sb.append(" ");
				}
			}
		}
		return sb;
	}
	
	public static void main(String[]args) throws InterruptedException{
  		
	}
}
