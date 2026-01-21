package com.sist.web.service;
import java.util.*;
import org.springframework.stereotype.Service;
import com.sist.web.mapper.*;
import com.sist.web.vo.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService{
	private final ReserveMapper rmapper;

	@Override
	public List<SeoulVO> seoulReserveData(Map map) {
		// TODO Auto-generated method stub
		return rmapper.seoulReserveData(map);
	}

	@Override
	public int seoulReserveTotalPage(Map map) {
		// TODO Auto-generated method stub
		return rmapper.seoulReserveTotalPage(map);
	}

	@Override
	public List<ReserveVO> myReserveList(String id) {
		// TODO Auto-generated method stub
		return rmapper.myReserveList(id);
	}

	@Override
	public List<ReserveVO> adminReserveList() {
		// TODO Auto-generated method stub
		return rmapper.adminReserveList();
	}

	@Override
	public String reserveInsert(ReserveVO vo) {
		// TODO Auto-generated method stub
		String res="";
		try
		{
			rmapper.reserveInsert(vo);
			res="YES";
		}catch(Exception ex)
		{
			res="NO";
			ex.printStackTrace();
		}
		return res;
	}

	@Override
	public void reserveOk(int no) {
		// TODO Auto-generated method stub
		rmapper.reserveOk(no);
	}

	@Override
	public void reserveCancel(int no) {
		// TODO Auto-generated method stub
		rmapper.reserveCancel(no);
	}

	@Override
	public void reserveDelete(int no) {
		// TODO Auto-generated method stub
		rmapper.reserveDelete(no);
	}

	@Override
	public ReserveVO reserveDetailData(int no) {
		// TODO Auto-generated method stub
		return rmapper.reserveDetailData(no);
	}
}
