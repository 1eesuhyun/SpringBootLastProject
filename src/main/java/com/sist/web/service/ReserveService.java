package com.sist.web.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.ReserveVO;
import com.sist.web.vo.SeoulVO;

public interface ReserveService {
	public List<SeoulVO> seoulReserveData(Map map);
	public int seoulReserveTotalPage(Map map);
	
	public List<ReserveVO> myReserveList(String id);
	public List<ReserveVO> adminReserveList();
	public String reserveInsert(ReserveVO vo);
}
