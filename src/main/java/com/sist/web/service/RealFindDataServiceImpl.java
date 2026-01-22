package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.RealFindDataMapper;
import com.sist.web.vo.RealFindDataVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RealFindDataServiceImpl implements RealFindDataService{
	private final RealFindDataMapper mapper;

	@Override
	public void realFindDataInsert(RealFindDataVO vo) {
		// TODO Auto-generated method stub
		mapper.realFindDataInsert(vo);
	}

	@Override
	public void realFindDataDelete() {
		// TODO Auto-generated method stub
		mapper.realFindDataDelete();
	}

	@Override
	public List<RealFindDataVO> realFindDataList() {
		// TODO Auto-generated method stub
		return mapper.realFindDataList();
	}
}