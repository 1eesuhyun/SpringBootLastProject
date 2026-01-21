package com.sist.web.vo;
import lombok.Data;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

@Data
public class NoticeVO {
	private int no,hit,filecount;
	private String type,name,subject,content,dbday,filename;
	private Date regdate;
	private List<MultipartFile> files;
}
