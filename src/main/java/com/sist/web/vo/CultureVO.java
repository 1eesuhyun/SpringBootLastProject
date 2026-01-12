package com.sist.web.vo;

import lombok.Data;

@Data
public class CultureVO {
	/*
	 * NO              NUMBER         
		CONTENTID       NUMBER         
		USEFEE          VARCHAR2(1024) 
		SPENDTIME       VARCHAR2(1024) 
		PARKINGFEE      VARCHAR2(1024) 
		DISCOUNTINFO    VARCHAR2(1024) 
		INFOCENTER      VARCHAR2(1024) 
		USETIME         VARCHAR2(1024) 
		RESTDATE        VARCHAR2(1024) 
		PARKING         VARCHAR2(1024) 
		MSG             CLOB           
	 */
	private int no,contentid;
	private String usefee,spendtime,parkingfee,discountinfo,infocenter,usetime,restdate,parking,msg;
}
