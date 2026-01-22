package com.sist.web.service;
import java.util.*;
import com.sist.web.vo.RealFindDataVO;

public interface RealFindDataService {
	public void realFindDataInsert(RealFindDataVO vo);
	
	public void realFindDataDelete();
	
	public List<RealFindDataVO> realFindDataList();
}
