package com.enji_iot.util.Util;
  
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.enji_iot.util.Entity.bo.ProDictionaryInfoBO;
  
/** 
 * 
 */  
public class MysqlDbGenerateBean {
	
	private static final String BUSSINESS = "IOT";				// 业务标识，如果不需要，则写空字符串
	
    private static final String TARGET_DIR="D:/";                            //类文件存放的路径  
    private static final String DIVER_NAME= "com.mysql.jdbc.Driver";  
    private static final String URL="jdbc:mysql://127.0.0.1:3306/easyiot3.6?characterEncoding=gbk";   
    private static final String USERNAME="root";  
    private static final String PASSWORD="root";  
    private static final String DATABASE_NAME = "easyiot3.6";                                //数据库名称  
    private static final String AUTHOR="M";                                       //作者    
    private static final String PACKAGE_NAME="com.lp.bean";                     	  //包名  
    private static final String BO_PACKAFE_NAME = "com.lp.bo" ;
    private Connection conn;  
    private Statement stmt;  
    private String sql;  
    private ResultSet rs;  
    private String[] fields;    //属性  
    private String[] dataTypes; //数据类型  
    private String[] comments;  //属性的注释  
    
    
   
    
    /**
     * 生成Mapper xml 文件
     * @param entityName
     * @param tableName
     */
    private void generateMapperXml(String entityName , String tableName){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		sb.append(
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
		sb.append("<mapper namespace=\"" + entityName + "\">\r\n");
		//
		// 新增
		sb.append("	<!-- 新增, 并返回id -->\r\n");
		sb.append("	<insert id=\"insert\" parameterType=\"com.lp.bo." 
				+ entityName + "BO\">\r\n");
		sb.append("		INSERT INTO " + tableName + "\r\n");
		sb.append("		(");
		String insert_sql = "";
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i];
			if (!fieldName.equals("id") && !fieldName.equals("atime") && !fieldName.equals("mtime")) {
				insert_sql = insert_sql +  fieldName + ",";
			}
		}
		insert_sql = insert_sql.substring(0, insert_sql.length()-1);
		sb.append(insert_sql);
		sb.append(")\r\n		VALUES\r\n");
		sb.append("		(");
		insert_sql = "";
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i];
			if (!fieldName.equals("id") && !fieldName.equals("atime") && !fieldName.equals("mtime")) {
				insert_sql = insert_sql + "#{" + fieldName + "}" + ",";
			}
		}
		insert_sql = insert_sql.substring(0, insert_sql.length()-1);
		sb.append(insert_sql);
		sb.append(")\r\n");
		sb.append("		<selectKey resultType=\"java.lang.Integer\" order=\"AFTER\" keyProperty=\"id\">\r\n");
			sb.append("			select id from "+ tableName +" ORDER BY id desc LIMIT 1\r\n");
		sb.append("		</selectKey>\r\n");
		sb.append(" </insert>\r\n\r\n\r\n");
		//
		// 修改
		sb.append("<!-- 修改 -->\r\n");
		sb.append("	<update id=\"update\" parameterType=\"com.lp.bo." + entityName + "BO\">\r\n");
		sb.append("	UPDATE " + tableName + " SET mtime=now()\r\n");
		for (int i = 0; i < fields.length; i++) {
			String columnName = fields[i].toLowerCase();
			if (!columnName.equals("id") && !columnName.equals("atime") && !columnName.equals("mtime")) {
				if (dataTypes[i].contains("String")) {
					sb.append("		<if test=\"" + columnName + "!=null and " + columnName
							+ " !=''\">\r\n");
				} else {
					sb.append("		<if test=\"" + columnName + "!=null\">\r\n");
				}
				sb.append("			," + columnName + "=#{" + columnName + "}\r\n");
				sb.append("		</if>\r\n");
			}
		}
		sb.append("		WHERE id=#{id}\r\n");
		sb.append("	</update>\r\n\r\n");


		// 检索记录
		sb.append("	<!-- 检索记录 -->\r\n");
		sb.append("	<select id=\"selectOne\" parameterType=\"com.lp.bo." 
				+ entityName + "BO\"\r\n");
		sb.append("		resultType=\"com.lp.bo." + entityName
				+ "BO\">\r\n");
		sb.append("		<include refid=\"sql_select_field\"/>\r\n");
		sb.append("		WHERE o.id=#{id} LIMIT 1\r\n");
		sb.append("	</select>\r\n\r\n");

		// 检索记录字段
		sb.append("	<!-- 检索记录字段 -->\r\n");
		sb.append("	<sql id=\"sql_select_field\">\r\n");
		sb.append("		SELECT o.* FROM " + tableName + " o \r\n");
		sb.append("	</sql>\r\n\r\n");

		// 检索记录条件
		sb.append("	<!-- 检索记录条件 -->\r\n");
		sb.append("	<sql id=\"sql_select_condition\">\r\n");
		sb.append("	WHERE 1=1\r\n");
		// idl数组检索
		sb.append("		<if test=\"id_array!=null\">\r\n");
		sb.append("			AND o.id IN\r\n");
		sb.append(
				"			<foreach collection=\"id_array\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">\r\n");
		sb.append("				#{item}\r\n");
		sb.append("			</foreach>\r\n");
		sb.append("		</if>\r\n");
		for (int i = 0; i < fields.length; i++) {
			String columnName = fields[i];
			if (dataTypes[i].contains("String")) {
				sb.append("		<if test=\"" + columnName + "!=null and " + columnName
						+ " !=''\">\r\n");
				sb.append("			AND o." + columnName + " = #{" + columnName + "}\r\n");
				sb.append("		</if>\r\n");
			} else {
				sb.append("		<if test=\"" + columnName + "!=null\">\r\n");
				sb.append("			AND o." + columnName + "=#{" + columnName + "}\r\n");
				sb.append("		</if>\r\n");
			}
		}
		sb.append("	</sql>\r\n");

		// 检索记录列表
		sb.append("	<!-- 检索记录列表 -->\r\n");
		sb.append("	<select id=\"select\" parameterType=\"com.lp.bo." + entityName + "BO\"\r\n");
		sb.append("	resultType=\"com.lp.bo." +  entityName + "BO\">\r\n");
		sb.append("		<include refid=\"sql_select_field\" />\r\n");
		sb.append("		<include refid=\"sql_select_condition\" />\r\n");
		sb.append("		ORDER BY o.id DESC\r\n");
		sb.append("	</select>\r\n\r\n");
		
		// 检索记录列表
		sb.append("	<!-- 检索记录列表 -->\r\n");
		sb.append("	<select id=\"selectPage\" parameterType=\"com.lp.bo." + entityName + "BO\"\r\n");
		sb.append("	resultType=\"com.lp.bo." +  entityName + "BO\">\r\n");
		sb.append("		<include refid=\"sql_select_field\" />\r\n");
		sb.append("		<include refid=\"sql_select_condition\" />\r\n");
		sb.append("		ORDER BY o.id DESC\r\n");
		sb.append("		<include refid=\"Base.sql_limit_condition\" />\r\n");
		sb.append("	</select>\r\n\r\n");

		// 检索记录数量
		sb.append("	<!-- 检索记录数量 -->\r\n");
		sb.append("	<select id=\"selectPageCount\" resultType=\"java.lang.Integer\" "
				+ "parameterType=\"com.lp.bo." + entityName+ "BO\">\r\n");
		sb.append("		SELECT COUNT(1) FROM " + tableName + " o\r\n");
		sb.append("		<include refid=\"sql_select_condition\" />\r\n");
		sb.append("	</select>\r\n");
		sb.append("</mapper>");
	
		OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(TARGET_DIR+tableName + "_mapper.xml"),
					"UTF-8");
			out.write(sb.toString());
			out.flush();
			out.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void generateControllerFile(String talbeName){
    	
    	String keyName = foramtClassName( formatField(talbeName),"") ;
    	
    	StringBuffer content = new StringBuffer();  
    	content.append("package com.lp.controller."+ BUSSINESS.toLowerCase() +";\n\n");
    	
    	content.append("import java.util.Map;\n\n");  
        content.append("import javax.servlet.http.HttpServletResponse;\r\n");
        content.append("import org.springframework.stereotype.Controller;\r\n"); 
        
        content.append("import org.springframework.web.bind.annotation.RequestBody; \r\n"); 
        content.append("import org.springframework.web.bind.annotation.RequestHeader; \r\n"); 
        content.append("import org.springframework.web.bind.annotation.RequestMapping; \r\n"); 
        content.append("import org.springframework.web.bind.annotation.RequestMethod; \r\n"); 
        content.append("import org.springframework.web.bind.annotation.RequestParam; \r\n"); 
        content.append("import org.springframework.web.servlet.ModelAndView; \r\n"); 
        content.append("import com.lp.bo."+ keyName +"BO; \r\n"); 
        content.append("import com.lp.common.Constants; \n\n"); 
        content.append(" import com.lp.common.Code; \r\n"); 
        content.append("import com.lp.common.RequestURL"+BUSSINESS+"; \r\n"); 
        content.append("import com.lp.controller.BaseController; \r\n"); 
        content.append("import com.lp.util.ObjectUtil; \r\n"); 
        content.append("import com.lp.util.ResultMapUtils; \r\n"); 
        content.append(" \r\n"); 
        content.append(" \r\n"); 
        content.append("@Controller\n\n"); 
        content.append("public class "+keyName+"Controller extends BaseController { \r\n"); 
        content.append("/** \r\n"); 
        content.append("* 检索 \r\n"); 
        content.append(" */ \r\n"); 
        content.append("@RequestMapping(method = RequestMethod.POST, value = RequestURL"+BUSSINESS+"."+keyName +"."+ talbeName.toUpperCase() +"_PAGE) \r\n"); 
        content.append("public ModelAndView selectPage(HttpServletResponse response,  \r\n"); 
        content.append(" @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,\r\n"); 
        content.append(" @RequestBody "+keyName+"BO obj,\r\n"); 
        content.append(" @RequestParam(required=false) Integer pageSize ,\r\n"); 
        content.append(" @RequestParam Integer paged ) {\r\n"); 
        content.append(" Map<String, Object> resultMap = getResultMap();\r\n"); 
        content.append(" try {\r\n"); 
        content.append(" resultMap = service.selectPageList(\""+keyName+".selectPage\",getPageBean(paged,pageSize), obj);\r\n"); 
        content.append(" } catch (Exception e) {\r\n"); 
        content.append(" exception(e,resultMap, obj);\r\n"); 
        content.append(" }\r\n"); 
        content.append(" return getModelAndView(response, resultMap);\r\n"); 
        content.append(" }\r\n"); 
        content.append(" \r\n"); 
        content.append(" /**\r\n"); 
        content.append("  * 插入\r\n"); 
        content.append(" */\r\n"); 
        content.append(" @RequestMapping(method = RequestMethod.POST, value = RequestURL"+BUSSINESS+"."+keyName+"."+talbeName.toUpperCase()+" )\r\n"); 
        content.append(" public ModelAndView save(HttpServletResponse response,\r\n"); 
        content.append(" @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,\r\n"); 
        content.append(" @RequestBody "+keyName+"BO obj ) {\r\n"); 
        content.append(" Map<String, Object> resultMap = getResultMap();\r\n"); 
        content.append(" try {\r\n"); 
        content.append(" resultMap = service.insert(\""+keyName+".insert\", obj) ;\r\n"); 
        content.append(" } catch (Exception e) {\r\n"); 
        content.append(" exception(e,resultMap, obj);\r\n"); 
        content.append(" }\r\n"); 
        content.append(" return getModelAndView(response, resultMap);\r\n"); 
        content.append(" }\r\n"); 
        content.append(" \r\n"); 
        content.append(" /**\r\n"); 
        content.append("  * 查询单个\r\n"); 
        content.append("  */\r\n"); 
        content.append("@RequestMapping(method = RequestMethod.GET, value = RequestURL"+BUSSINESS+"."+keyName+"."+talbeName.toUpperCase()+") \r\n"); 
        content.append("public ModelAndView selectOne(HttpServletResponse response,  \r\n"); 
        content.append("@RequestParam Integer id ) { \r\n"); 
        content.append("Map<String, Object> resultMap = getResultMap(); \r\n"); 
        content.append("try { \r\n"); 
        content.append("resultMap = service.selectOne(\""+keyName+".selectOne\", new "+keyName+"BO(id)) ; \r\n"); 
        content.append("} catch (Exception e) { \r\n"); 
        content.append("exception(e,resultMap, id); \r\n"); 
        content.append("} \r\n"); 
        content.append("return getModelAndView(response, resultMap); \r\n"); 
        content.append("} \r\n"); 
        content.append(" \r\n"); 
        content.append("/** \r\n"); 
        content.append("* 更新 \r\n"); 
        content.append("*/ \r\n"); 
        content.append("@RequestMapping(method = RequestMethod.PUT, value = RequestURL"+BUSSINESS+"."+keyName+"."+talbeName.toUpperCase()+") \r\n"); 
        content.append("public ModelAndView update(HttpServletResponse response,  \r\n"); 
        content.append("@RequestBody "+keyName+"BO obj ) { \r\n"); 
        content.append("Map<String, Object> resultMap = getResultMap(); \r\n"); 
        content.append("try { \r\n"); 
        content.append("resultMap = service.update(\""+keyName+".update\", obj) ; \r\n"); 
        content.append("} catch (Exception e) { \r\n"); 
        content.append("exception(e,resultMap, obj); \r\n"); 
        content.append("} \r\n"); 
        content.append("return getModelAndView(response, resultMap); \r\n"); 
        content.append("} \r\n"); 
        content.append(" \r\n"); 
        content.append("/** \r\n"); 
        content.append(" * 删除 \r\n"); 
        content.append("*/ \r\n"); 
        content.append("@RequestMapping(method = RequestMethod.DELETE, value = RequestURL"+BUSSINESS+"."+keyName+"."+talbeName.toUpperCase()+") \r\n"); 
        content.append("public ModelAndView delete(HttpServletResponse response,  \r\n"); 
        content.append("@RequestParam Integer id ) { \r\n"); 
        content.append("Map<String, Object> resultMap = getResultMap(); \r\n"); 
        content.append("try { \r\n"); 
        content.append(keyName +"BO obj = new "+keyName+"BO(); \r\n"); 
        content.append("if(ObjectUtil.isEmpty(id)){ \r\n"); 
        content.append("putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR); \r\n"); 
        content.append("}else{ \r\n"); 
        content.append("obj.setId(id); \r\n"); 
        content.append("obj.setDelete_flag(Constants.DELETE.YES); \r\n"); 
        content.append("resultMap = service.update(\""+keyName+".update\", obj) ; \r\n"); 
        content.append("} \r\n"); 
        content.append("} catch (Exception e) { \r\n"); 
        content.append("exception(e,resultMap, id); \r\n"); 
        content.append("} \r\n"); 
        content.append("return getModelAndView(response, resultMap); \r\n"); 
        content.append("} \r\n"); 
        content.append("} \r\n"); 
        content.append(" \r\n"); 
        content.append(" \r\n"); 
        
        OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(TARGET_DIR+keyName + "Controller.java"),
					"UTF-8");
			out.write(content.toString());
			out.flush();
			out.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void generateServiceFile(String talbeName){
		// service
    	String keyName = foramtClassName( formatField(talbeName),"") ;
    	StringBuffer content = new StringBuffer();  
    	content.append("package com.lp.service;\n\n");
    	content.append("\n\n");  
        content.append("public interface "+keyName+"Service {\r\n");
    	content.append(" \r\n");
    	content.append(" \r\n");
        content.append("}\r\n"); 
        
        content.append(" \r\n"); 
        content.append(" \r\n"); 
        
        OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(TARGET_DIR+keyName + "Service.java"),
					"UTF-8");
			out.write(content.toString());
			out.flush();
			out.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void generateServiceImplFile(String talbeName){
		// service
    	String keyName = foramtClassName( formatField(talbeName),"") ;
    	StringBuffer content = new StringBuffer();  
    	content.append("package com.lp.service.impl;\n\n");
    	content.append("\n\n");  
        content.append("import org.springframework.stereotype.Service;\r\n");
    	content.append("import com.lp.service."+keyName+"Service; \r\n");
    	content.append(" \r\n");
        content.append("@Service\r\n"); 
        content.append("public class "+keyName+"ServiceImpl extends BaseServiceImpl implements "+keyName+"Service { \r\n"); 
        content.append(" \r\n"); 
        content.append(" \r\n"); 
        content.append(" \r\n"); 
        content.append("} \r\n"); 
        content.append(" \r\n"); 
        content.append(" \r\n"); 
        
        OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(TARGET_DIR+keyName + "ServiceImpl.java"),
					"UTF-8");
			out.write(content.toString());
			out.flush();
			out.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 生成BO文件
     * @param className
     */
    private void generateBO(String className){
    	 StringBuffer content = new StringBuffer();  
         content.append("package "+BO_PACKAFE_NAME+";\n\n");  
         
         content.append("import java.util.Date;\n\n");  
         content.append("import org.codehaus.jackson.map.annotate.JsonSerialize;\n\n");  
         
         content.append("import com.lp.bean."+ className +";\n\n"); 
         
         content.append("import lombok.Data;\n\n");  
         content.append("import lombok.EqualsAndHashCode; \n\n");  
         content.append("import lombok.NoArgsConstructor; \n\n");  
         
         content.append("/**\n");  
         content.append(" *@类:"+className+"\n");  
         content.append(" *@作者:"+AUTHOR+"\n");  
         content.append(" */\n\n");  
         content.append("@Data\n");  
         content.append("@NoArgsConstructor\n");
         content.append("@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) \n");
         content.append("@EqualsAndHashCode(callSuper = false) \n");
         
         content.append("public class "+className+"BO extends "+className+" {\n\n");
         
         content.append("public "+className+"BO(Integer id)  {\n\n");
         content.append(" super();\n");
         content.append(" this.setId(id);\n");
         content.append(" }\n\n");  
         
         content.append(" }\n\n");  
         
        OutputStreamWriter out;
 		try {
 			out = new OutputStreamWriter(new FileOutputStream(TARGET_DIR + className + "BO.java"), "UTF-8");
 			out.write(content.toString());
 			out.flush();
 			out.close();
 		}  catch (Exception e) {
 			e.printStackTrace();
 		}
    }
    
    /** 
     * 方法:根据数据库表生成VO--Value Object 
     * @param tableName 
     */  
    private String generateBean( String tableName){
        String className;  
        try {  
            className = foramtClassName(null,tableName) ;
            Class.forName(DIVER_NAME);  
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);  
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);    //创建可滚动的,只读的结果集  
            sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT"+  
                  " FROM INFORMATION_SCHEMA.COLUMNS"+  
                  " WHERE table_name = '"+tableName.toUpperCase()+"'"+      //表名  
                  " AND table_schema = '"+DATABASE_NAME+"'";                //数据库名  
            System.out.println(sql);  
            rs = stmt.executeQuery(sql);  
            rs.last();                                      //把指针指向结果集的最后  
            int fieldNum = rs.getRow();                     //取得最后一条结果的行号,作为类的属性个数  
            int n = fieldNum;  
            if(n>0){                                 //判断数据表中是否存在字段  
                fields = new String[n];  
                dataTypes = new String[n];  
                comments = new String[n];  
                fields[--n] = rs.getString(1);  
                dataTypes[n] = rs.getString(2);  
                comments[n] = rs.getString(3);  
                while(rs.previous()){  
                    fields[--n]=rs.getString(1);            //取得结果集的第一列数据,对应的列名:Field  
                    dataTypes[n] = rs.getString(2);  
                    comments[n] = rs.getString(3);  
                }  
                //打印相关信息  
                System.out.println("你要转换的表是:"+tableName);  
                System.out.println("该表中共有"+fieldNum+"个字段,信息如下:");  
                for(int i=0,j=fields.length;i<j;i++){  
                    System.out.println("----------------------------------------");  
                    String field = fields[i];  
                    System.out.println("字段名称:"+field);  
                    //把字段名称格式化成java命名规则形式  
//                    field=formatField(field);  
                    fields[i]=field;                        //把格式化后的字段放入属性数组中  
                    System.out.println("数据类型:"+dataTypes[i]);  
                    //把数据库字段类型转换成java数据类型  
                    String dataType = dataTypes[i].toLowerCase();  
                    dataType = formatDataType(dataType);  
                    dataTypes[i] = dataType;  
                      
                    System.out.println("字段注释:"+comments[i]);  
                    if("".equals(comments[i])||comments[i]==null){  
                        comments[i]=fields[i];  
                    }  
                    System.out.println("----------------------------------------");  
                }  
                //格式化类名称  
                foramtClassName(className,tableName);  
                //生成类文件,写入到磁盘中  
                writeObjectToFile(className);  
            }else{  
                System.out.println("该表不存在或者表中没有字段");  
            }     
        } catch (ClassNotFoundException e) {  
            System.out.println("未找到数据库驱动");  
            System.out.println(e.getMessage());  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }finally{
            try {  
                if(stmt!=null){  
                    if(!stmt.isClosed()){  
                        stmt.close();  
                        stmt=null;  
                        System.gc();  
                    }  
                }  
                if(conn!=null){  
                    if(!conn.isClosed()){  
                        conn.close();  
                        conn=null;  
                        System.gc();  
                    }  
                }  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }
        return tableName;
    }
      
    /** 
     * 把数据库字段格式成java变量名 
     * @param field 
     * @return 
     */  
    private static String formatField(String field){  
        String[] strs = field.split("_");  
        field="";  
        for(int m = 0,length=strs.length;m<length;m++){  
            if(m>0){  
                String tempStr = strs[m].toLowerCase();  
                tempStr = tempStr.substring(0, 1).toUpperCase()+tempStr.substring(1, tempStr.length());  
                field += tempStr;  
            }else{  
                field += strs[m].toLowerCase();  
            }  
        }  
        return field;  
    }  
      
    private String formatDataType(String dataType){
        if(dataType.contains("char")){  
            dataType="String";  
        }else if(dataType.contains("int")){  
            dataType="Integer";  
        }else if(dataType.contains("float")){  
            dataType="Float";  
        }else if(dataType.contains("double")){  
            dataType="Double";  
        }else if(dataType.contains("number")||dataType.contains("decimal")){  
            dataType="BigDecimal";  
        }else if(dataType.contains("date") || dataType.contains("time") ){  
            dataType="Date";  
        }
//        else if(dataType.contains("time")){  
//            dataType="Timestamp";  
//        }
        else if(dataType.contains("clob")){  
            dataType="Clob";  
        }else{  
            dataType="Object";  
        }  
        return dataType;  
    }
    
    
    /** 
     * 格式化类名 
     * @param className 
     * @param tableName 
     * @return 
     */  
    private static String foramtClassName(String className,String tableName){  
        //如果类名不是自己定义的,那么根据表名格式化类名  
        if("".equals(className)||className==null){  
            className="";  
            String[] tempArr = tableName.split("_");  
            for(int m=0,length=tempArr.length;m<length;m++){       
                className += tempArr[m].substring(0,1).toUpperCase()+tempArr[m].substring(1,tempArr[m].length()).toLowerCase();  
            }  
        }else{  
            //如果类名已经输入,那么不管三七二十一就把类名的第一个字母大写  
            className = className.substring(0,1).toUpperCase()+className.substring(1,className.length());  
        }  
        return className;  
    }  
    
    
    /**
     * 生成数据字典
     */
    private void genUrl(StringBuffer content ,String tableName ){
    	if("".equals(content.toString())){
    		content.append("package com.lp.common;\n\n");  
             
             content.append("/**\n");  
             content.append(" *@类: Codes \n");  
             content.append(" *@作者:"+AUTHOR+"\n");  
             content.append(" */\n\n");  
             content.append("public class RequestURL"+BUSSINESS +"  {\n\n");
             
             content.append("public static final class "+ foramtClassName(formatField(tableName),"") +"  {\n\n");
             
             content.append("public static final String "+ StringUtils.upperCase(tableName)  +"_PAGE =\"/page/"+ formatField(tableName) +"\";\n\n");
             
             content.append("public static final String "+ StringUtils.upperCase(tableName)  +" = \"/"+ formatField(tableName) +"\";\n\n");
            
             
             content.append("}\n\n");  
    	}else{
    		
    		content.append("public static final class "+ foramtClassName(formatField(tableName),"") +"  {\n\n");
            
            content.append("public static final String "+ StringUtils.upperCase(tableName)  +"_PAGE =\"/page/"+ formatField(tableName) +"\";\n\n");
            content.append("public static final String "+ StringUtils.upperCase(tableName)  +" = \"/"+ formatField(tableName) +"\";\n\n");
            
            content.append("}\n\n");   
    		
    	}
    }
    
    /**
     * 生成项目URL
     */
    public List<String> generateRequestUrl(){
    	// tables 
    	List<String> tableList= new ArrayList<>();
    	StringBuffer urlSb = new StringBuffer();
        try {  
            Class.forName(DIVER_NAME);  
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);  
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);    //创建可滚动的,只读的结果集  
            sql = "select table_name from information_schema.tables where table_type='base table' "+  
            			" AND table_schema = '"+DATABASE_NAME+"'";                //数据库名  
            System.out.println(sql);  
            rs = stmt.executeQuery(sql);  
            rs.last();                                      //把指针指向结果集的最后  
            int fieldNum = rs.getRow();                     //取得最后一条结果的行号,作为类的属性个数  
            int n = fieldNum;  
            if( n > 0 ){                               //判断数据表中是否存在字段  
                rs.first();
                tableList.add(rs.getString(1));
                genUrl(urlSb,tableList.get(0));
                while( rs.next() ){
                	String tmp = rs.getString(1);
                	tableList.add(tmp);
                	 genUrl(urlSb,tmp);
                }
                urlSb.append("}\n\n");
                
                OutputStreamWriter out;
        		try {
        			out = new OutputStreamWriter(new FileOutputStream(TARGET_DIR+ "RequestURL"+BUSSINESS + ".java"),
        					"UTF-8");
        			out.write(urlSb.toString());
        			out.flush();
        			out.close();
        		}  catch (Exception e) {
        			e.printStackTrace();
        		}
                
                
            }else{  
                System.out.println("该数据库没有数据表");  
            }     
        } catch (ClassNotFoundException e) {
            System.out.println("未找到数据库驱动");
            System.out.println(e.getMessage());
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }finally{
            try {  
                if(stmt!=null){  
                    if(!stmt.isClosed()){  
                        stmt.close();  
                        stmt=null;  
                        System.gc();  
                    }  
                }  
                if(conn!=null){  
                    if(!conn.isClosed()){  
                        conn.close();  
                        conn=null;  
                        System.gc();  
                    }  
                }  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }
        return tableList;
    }
    
    /**
     * 生成数据字典
     */
    public static void generateDictionaryCode(StringBuffer content ,ProDictionaryInfoBO obj ){
    	if("".equals(content.toString())){
    		content.append("package com.lp.common;\n\n");  
             
             content.append("/**\n");  
             content.append(" *@类: Codes \n");  
             content.append(" *@作者:"+AUTHOR+"\n");  
             content.append(" */\n\n");  
             content.append("public class Codes  {\n\n");
             
             content.append("public static class "+ obj.getDictionary_name() +"  {\n\n");
             
             for(int i=0; i<obj.getSub().size(); i++){
            	 content.append("public final static Integer "+ obj.getSub().get(i).getDictionary_name() +" = "+obj.getSub().get(i).getCode() +"; \n\n");
             }
             
             content.append("}\n\n");  
    	}else{
    		
    		content.append("public static class "+ obj.getDictionary_name() +"  {\n\n");
             
             for(int i=0; i<obj.getSub().size(); i++){
            	 content.append("public final static Integer "+ obj.getSub().get(i).getDictionary_name() +" = "+obj.getSub().get(i).getCode() +"; \n\n");
             }
             
             content.append("}\n\n");  
    		
    	}
    }
    
    /** 
     * 生成类并写到文件 
     * @param className 
     */  
    @SuppressWarnings("deprecation")  
    private void writeObjectToFile(String className){  
        PrintWriter writer = null;  
        try {  
             File dir = new File(TARGET_DIR);  
             if(!dir.exists()){  
                 dir.mkdirs();  
             }  
             File file = new File(TARGET_DIR+className+".java");  
             if(!file.exists()){  
                 file.createNewFile();  
             }  
             writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));  
               
             //拼文件内容  
             StringBuffer content = new StringBuffer();  
             content.append("package "+PACKAGE_NAME+";\n\n");  
             
             content.append("import java.util.Date;\n\n");  
             content.append("import org.codehaus.jackson.map.annotate.JsonSerialize;\n\n");  
             content.append("import lombok.Data;\n\n");  
             content.append("import lombok.EqualsAndHashCode; \n\n");  
             content.append("import lombok.NoArgsConstructor; \n\n");  
             
             
             content.append("/**\n");  
             content.append(" *@类:"+className+"\n");  
             content.append(" *@作者:"+AUTHOR+"\n");  
             content.append(" */\n\n");  
             content.append("@Data\n");  
             content.append("@NoArgsConstructor\n");
             content.append("@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) \n");
             content.append("@EqualsAndHashCode(callSuper = false) \n");
             
             content.append("public class "+className+" extends BaseBean {\n\n");  
             for(int i=0,j=fields.length;i<j;i++){  
                 content.append("   /**"+comments[i]+"*/\n");  
                 content.append("   private "+dataTypes[i]+" "+fields[i]+";\n\n");  
             }  
 
             content.append("}");  
             writer.write(content.toString());  
             writer.flush();  
             System.out.println("类生成成功，存放路径："+TARGET_DIR);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{
            if(writer!=null){  
                writer.close();  
            }  
        } 
    }
      
    public static void main(String[] args) {
        try {
        	MysqlDbGenerateBean obj = new MysqlDbGenerateBean();

//        	List<String> tableNames = obj.generateRequestUrl();
        	List<String> tableNames = Arrays.asList(new String[]{"iot_device_category"  });
        	
        	for(int i=0;i<tableNames.size();i++){
            	  // 生成bean
	              String t = obj.generateBean(tableNames.get(i));
	              // 生成Mapper xml
	              obj.generateMapperXml(foramtClassName(null,t),t);
	              // 生成BO
	              obj.generateBO(foramtClassName(null,t));
	          	 // 生成controller
	              obj.generateControllerFile(t);
	              // 生成controller
	              obj.generateServiceFile(t);
	              // 生成controller
	              obj.generateServiceImplFile(t);
        	}
        	
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}  