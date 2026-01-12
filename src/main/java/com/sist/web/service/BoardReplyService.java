package com.sist.web.service;
import java.util.List;
import com.sist.web.vo.*;

public interface BoardReplyService {
	
	public List<BoardReplyVO> boardReplyListData(int bno);
	public int boardReplyCount(int bno);
	public void boardReplyInsert(BoardReplyVO vo);
	public void boardReplyDelete(int no);
}
