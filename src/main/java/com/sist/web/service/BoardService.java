package com.sist.web.service;
import java.util.*;
import com.sist.web.vo.*;

public interface BoardService {
	public List<BoardVO> boardListData(int srtart);
	public int boardTotalPage();
	public void boardInsert(BoardVO vo);
	public BoardVO boardDetailData(int no);
	public BoardVO boardUpdateData(int no);
	public String boardUpdate(BoardVO vo);
	public boolean boardDelete(int no,String pwd);
	//public void boardReplycountUpdate(int replycount,int no);
}
