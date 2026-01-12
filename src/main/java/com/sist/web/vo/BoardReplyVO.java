package com.sist.web.vo;
import lombok.Data;
import java.util.*;

@Data
public class BoardReplyVO {
	/*
	 * 	NO      NOT NULL NUMBER       
		BNO              NUMBER       
		ID               VARCHAR2(20) 
		NAME    NOT NULL VARCHAR2(51) 
		SEX              VARCHAR2(6)  
		MSG     NOT NULL CLOB         
		REGDATE          DATE 
	 */
	private int no,bno;
	private String id,name,sex,msg,dbday;
	private Date regdate;
}





