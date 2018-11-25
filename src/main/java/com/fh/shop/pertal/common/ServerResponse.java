/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:serverResponse.java 
 * 包名:com.fh.shop.admin.common 
 * 创建日期:2018年10月18日下午6:33:33 
 * Copyright (c) 2018, lw951119@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.pertal.common;

import java.io.Serializable;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：serverResponse    
 * 类描述：    
 * 创建人：李旺   lw951119@163.com 
 * 创建时间：2018年10月18日 下午6:33:33    
 * 修改人：李旺    lw951119@163.com
 * 修改时间：2018年10月18日 下午6:33:33    
 * 修改备注
 * @version </pre>    
 */
public class ServerResponse implements Serializable{

	private static final long serialVersionUID = -7391779465030957026L;
	
	private Integer code;
	private String msg;
	private Object data;
	
	//构造函数
	public ServerResponse(){
			
	}
	//构造函数
	public ServerResponse(Integer code,String msg,Object data){
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	//静态方法
	public static ServerResponse success(Object data){
		return new ServerResponse(200, "ok", data);
	}
	//无data返回
	public static ServerResponse success(){
		return new ServerResponse(200, "ok", null);
	}
	//error提示
	public static ServerResponse error(Integer code,String msg){
		return new ServerResponse(code, msg, null);
	}
	//error提示2方法
	public static ServerResponse error(SystemEnum systemEnum){
		return new ServerResponse(systemEnum.getCode(),systemEnum.getMsg(),null);
	}
	
	//get方法
	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}
	
}
