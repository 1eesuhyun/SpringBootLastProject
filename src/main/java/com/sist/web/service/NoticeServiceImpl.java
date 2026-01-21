package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.NoticeMapper;
import com.sist.web.vo.NoticeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
	private final NoticeMapper mapper;

	@Override
	public List<NoticeVO> noticeListdata(int start) {
		// TODO Auto-generated method stub
		return mapper.noticeListdata(start);
	}

	@Override
	public int noticeTotalPage() {
		// TODO Auto-generated method stub
		return mapper.noticeTotalPage();
	}

	@Override
	public void noticeInsert(NoticeVO vo) {
		// TODO Auto-generated method stub
		mapper.noticeInsert(vo);
	}

	@Override
	public NoticeVO noticeDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.hitIncrement(no);
		return mapper.noticeDetailData(no);
	}

	@Override
	public NoticeVO noticeFileInfoData(int no) {
		// TODO Auto-generated method stub
		return mapper.noticeFileInfoData(no);
	}

	@Override
	public void noticeDelete(int no) {
		// TODO Auto-generated method stub
		mapper.noticeDelete(no);
	}

	@Override
	public NoticeVO noticeUpdateData(int no) {
		// TODO Auto-generated method stub
		return mapper.noticeDetailData(no);
	}

	@Override
	public void noticeUpdate(NoticeVO vo) {
		// TODO Auto-generated method stub
		mapper.noticeUpdate(vo);
	}
}
