package com.sist.web.service;
import java.util.List;
import com.sist.web.vo.NoticeVO;

public interface NoticeService {
	public List<NoticeVO> noticeListdata(int start);
	public int noticeTotalPage();
	public void noticeInsert(NoticeVO vo);
	public NoticeVO noticeDetailData(int no);
	public NoticeVO noticeFileInfoData(int no);
	public void noticeDelete(int no);
	public NoticeVO noticeUpdateData(int no);
	public void noticeUpdate(NoticeVO vo);
}
