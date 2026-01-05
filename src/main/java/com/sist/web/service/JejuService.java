package com.sist.web.service;
import java.util.*;

import com.sist.web.vo.JejuVO;

public interface JejuService {
	public List<JejuVO> jejuListData(Map map);
	public int jejuTotalPage(int contenttype);
}
