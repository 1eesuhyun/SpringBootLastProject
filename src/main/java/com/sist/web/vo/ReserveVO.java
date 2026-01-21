package com.sist.web.vo;
import lombok.Data;
import java.util.*;

@Data
public class ReserveVO {
	/*
	 * 	NO        NOT NULL NUMBER       
		CNO                NUMBER       
		ID                 VARCHAR2(20) 
		RDAY      NOT NULL VARCHAR2(30) 
		RTIME     NOT NULL VARCHAR2(30) 
		RINWON    NOT NULL VARCHAR2(20) 
		REGDATE            DATE         
		ISRESERVE          NUMBER
	 */
	private int no,cno,isReserve;
	private String id,rday,rinwon,rtime,dbday,iscancel;
	private Date regdate;
	
	private SeoulVO svo=new SeoulVO();
}
