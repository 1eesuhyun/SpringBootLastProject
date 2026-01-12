package com.sist.web.vo;
import lombok.Data;
import java.util.*;

@Data
public class BoardVO {
	private int no,hit,replycount;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
