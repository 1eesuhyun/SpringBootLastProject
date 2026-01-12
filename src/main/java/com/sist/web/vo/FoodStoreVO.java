package com.sist.web.vo;

import lombok.Data;

@Data
public class FoodStoreVO {
	/*
	 * NO            NUMBER         
		CONTENTID     NUMBER         
		FIRSTMENU     VARCHAR2(1024) 
		TREATMENU     VARCHAR2(1024) 
		INFOCENTER    VARCHAR2(1024) 
		PARKING       VARCHAR2(1024) 
		OPENDATE      VARCHAR2(1024) 
		OPENTIME      VARCHAR2(1024) 
		RESTDATE      VARCHAR2(1024) 
		MSG           CLOB 
	 */
	private int no,contentid;
	private String firstmenu,treatmenu,infocenter,parking,opendate,opentime,restdate,msg;
}
