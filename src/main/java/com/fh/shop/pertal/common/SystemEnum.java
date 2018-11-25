/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:SystemEnum.java 
 * 包名:com.fh.shop.admin.common 
 * 创建日期:2018年10月19日下午2:04:23 
 * Copyright (c) 2018, lw951119@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.pertal.common;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：SystemEnum    
 * 类描述：    
 * 创建人：李旺   lw951119@163.com 
 * 创建时间：2018年10月19日 下午2:04:23    
 * 修改人：李旺    lw951119@163.com
 * 修改时间：2018年10月19日 下午2:04:23    
 * 修改备注
 * @version </pre>    
 */
public enum SystemEnum {
	
	LOG_LACK_PASSWORD(1004,"密码不正确"),
	LOG_LACK_USERNAME(1003,"用户名错误"),
	LOG_LACK_CODE(1002,"验证码错误"),
	LOG_LACK_MESSAGE(1000,"缺少登入信息"),
	LOG_USER_SUCCESS(1004,"用户被锁定"),
	SMS_ERROR_PHONE(2004,"手机号为空"),
	SMS_ERROR_LACK(2005,"手机号不规范"),
	SMS_ERROR(-1,"网易接口调用失败"),
	SMS_MEMBER_ERROR(2006,"验证码超时"),
	SMS_CODE_ERROR(2007,"验证码错误"),
	LOG_BRAND_ERROR(1005,"缺少品牌信息"),
	USER_ERROR(1009,"用户名为空"),
	USER_ISEXIS(1010,"用户名不能已存在"),
	OPERATION_LACK_ERROR(-1,"error");


	private Integer code;
	private String msg;
	
	private SystemEnum(Integer code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}
