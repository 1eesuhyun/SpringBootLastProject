package com.sist.web.service;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.sist.web.mapper.BoardMapper;
import com.sist.web.mapper.BoardReplyMapper;
import com.sist.web.vo.*;

/*
 *  VO : 불변(setter가 없다)
 *  Entity : 테이벌 제어(컬럼과 일치) => JPA
 *  DTO : getter/setter
 *  Record : getter => Kotlin(data)
 */

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService,BoardReplyService{
	private final BoardMapper bmapper;
	private final BoardReplyMapper rmapper;

	@Override
	public List<BoardVO> boardListData(int srtart) {
		// TODO Auto-generated method stub
		return bmapper.boardListData(srtart);
	}

	@Override
	public int boardTotalPage() {
		// TODO Auto-generated method stub
		return bmapper.boardTotalPage();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		bmapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		bmapper.hitIncrement(no);
		return bmapper.boardDetailData(no);
	}

	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return bmapper.boardDetailData(no);
	}

	@Override
	public String boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		String result="no";
		String db_pwd=bmapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			result="yes";
			bmapper.boardUpdate(vo);
		}
		return result;
	}

	@Override
	public boolean boardDelete(int no,String pwd) {
		// TODO Auto-generated method stub
		String db_pwd=bmapper.boardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			bmapper.boardDelete(no);
			return true; // 종료 => void인 경우에는 마지막줄에 자동 추가
		}
		return false;
	}
	
	// 댓글 목록
	@Override
	public List<BoardReplyVO> boardReplyListData(int bno) {
		// TODO Auto-generated method stub
		return rmapper.boardReplyListData(bno);
	}

	@Override
	public int boardReplyCount(int bno) {
		// TODO Auto-generated method stub
		int count=rmapper.boardReplyCount(bno);
		bmapper.boardReplycountUpdate(count, bno);
		return count;
	}

	@Override
	public void boardReplyInsert(BoardReplyVO vo) {
		// TODO Auto-generated method stub
		rmapper.boardReplyInsert(vo);
	}

	@Override
	public void boardReplyDelete(int no) {
		// TODO Auto-generated method stub
		rmapper.boardReplyDelete(no);
	}

	@Override
	public void boardReplyUpdate(BoardReplyVO vo) {
		// TODO Auto-generated method stub
		rmapper.boardReplyUpdate(vo);
	}

}
