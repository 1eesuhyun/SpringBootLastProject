package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BusanMapper;
import com.sist.web.vo.BusanVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService{
	private final BusanMapper mapper;

	@Override
	public List<BusanVO> busanListData(Map map) {
		// TODO Auto-generated method stub
		return mapper.busanListData(map);
	}

	@Override
	public int busanTotalPage(int contenttype) {
		// TODO Auto-generated method stub
		return mapper.busanTotalPage(contenttype);
	}

	@Override
	public List<BusanVO> busanFindData(Map map) {
		// TODO Auto-generated method stub
		return mapper.busanFindData(map);
	}

	@Override
	public int busanFindTotalPage(String address) {
		// TODO Auto-generated method stub
		return mapper.busanFindTotalPage(address);
	}

	@Override
	public List<BusanVO> busanTop4Data() {
		// TODO Auto-generated method stub
		return mapper.busanTop4Data();
	}
}
