package com.sist.web.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;

@Mapper
@Repository
public interface ReserveMapper {
	public List<SeoulVO> seoulReserveData(Map map);
	
	public int seoulReserveTotalPage(Map map);
	
	@Insert("INSERT INTO reserve_1(no,cno,id,rday,rtime,rinwon) "
			+ "VALUES(r1_no_seq.nextval,#{cno},#{id},#{rday},#{rtime},#{rinwon})")
	public void reserveInsert(ReserveVO vo);
	
	/*@Results({
		@Result(column = "title", property = "svo.title"),
		@Result(column = "image1", property = "svo.image1"),
		@Result(column = "address", property = "svo.address"),
	})*/
	// vo.svo.setTitle()
	@ResultMap("resMap")
	@Select(value = "SELECT r.no,cno,rday,rtime,rinwon,TO_CHAR(regdate,'yyyy-mm-dd hh24:mi') as dbday,isReserve,title,image1,address,iscancel "
			+ "FROM reserve_1 r, seoultravel s "
			+ "WHERE r.cno=s.contentid "
			+ "AND id=#{id} "
			+ "ORDER BY no DESC")
	public List<ReserveVO> myReserveList(String id);
	
	@ResultMap("resMap")
	@Select(value = "SELECT r.no,cno,rday,rtime,rinwon,TO_CHAR(regdate,'yyyy-mm-dd hh24:mi') as dbday,isReserve,title,image1,address,id,iscancel "
			+ "FROM reserve_1 r, seoultravel s "
			+ "WHERE r.cno=s.contentid "
			+ "ORDER BY no DESC")
	public List<ReserveVO> adminReserveList();
	
	@Update("UPDATE reserve_1 SET "
			+ "isReserve=1 "
			+ "WHERE no=#{no}")
	public void reserveOk(int no);
	
	@Update("UPDATE reserve_1 SET "
			+ "isCancel=1 "
			+ "WHERE no=#{no}")
	public void reserveCancel(int no);
	
	@Delete("DELETE FROM reserve_1 "
			+ "WHERE no=#{no}")
	public void reserveDelete(int no);
	
	@ResultMap("resMap")
	@Select(value = "SELECT r.no,cno,rday,rtime,rinwon,title,image1,address "
			+ "FROM reserve_1 r, seoultravel s "
			+ "WHERE r.cno=s.contentid "
			+ "AND r.no=#{no}")
	public ReserveVO reserveDetailData(int no);
}