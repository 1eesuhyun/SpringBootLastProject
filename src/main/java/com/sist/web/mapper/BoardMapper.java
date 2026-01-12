package com.sist.web.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
/*
 *  SpringFramework : XML + Annotation
 *  SpringBoot : Annotation
 *  ----------- Framework(JSP / ThymeLeaf)
 *  ----------- Security : 일반 / JWT(단점 : 토큰을 생성해야 함)
 *                         |       |
 *                      Session  Cookie 
 *  ----------- WebSocket
 *  ----------- Spring AI
 *  ----------- Batch : Task(실시간) => 스케쥴러
 *  ----------- Kafka : 메세지(알림)
 *  
 *  Vue / Vues / Pinia(Jquery : Ajax)
 *  ------------------
 *  React / Redux / TanStack-Query => Next : TypeScript
 *                  |
 *             MAS => NodeJS / SpringBoot
 *  --------------------------------------------------------
 *  CI/CD
 *   Got Action / Docker / Docker-compose
 *                  |
 *             Docker Hub
 *   Kube => 우분투에서 처리하는 내용
 *   Jenkin => 모니터링 => webhook
 *                         | Git
 *   --------------------------------------------
 *   | 자바 / 오라플 / JSP / MVC
 *   | JavaScript / 
 */

@Mapper
@Repository
public interface BoardMapper {
	@Select("SELECt no,subject,name,hit,replycount,TO_CHAR(regdate,'yyyy-mm-dd') as dbday "
			+ "FROM board_1 "
			+ "ORDER BY no DESC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int srtart);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM board_1")
	public int boardTotalPage();
	
	@SelectKey(keyProperty = "no",resultType = int.class,before = true,statement = "SELECT NVL(MAX(NO)+1,1) as no FROM board_1")
	@Insert("INSERT INTO board_1 VALUES(#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0,0)")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE board_1 SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'yyyy-mm-dd hh24:mi:ss') as dbday "
			+ "FROM board_1 "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	@Select("SELECT pwd FROM board_1 "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE board_1 SET "
			+ "name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	@Delete("DELETE FROM board_1 "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	
	@Update("UPDATE board_1 SET "
			+ "replycount=#{replycount} "
			+ "WHERE no=#{no}")
	public void boardReplycountUpdate(@Param("replycount") int replycount,@Param("no") int no);
}
