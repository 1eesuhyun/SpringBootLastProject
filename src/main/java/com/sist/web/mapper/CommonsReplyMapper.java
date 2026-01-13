package com.sist.web.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface CommonsReplyMapper {
	@Select("SELECT no,cno,id,name,msg,sex,TO_CHAR(regdate,'yyyy-mm-dd hh24:mi:ss') as dbday,group_tab "
			+ "FROM commonsReply_1 "
			+ "WHERE cno=#{cno} "
			+ "ORDER BY group_id DESC, group_step ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<CommonsReplyVO> commonsReplyListData(@Param("cno") Integer cno,@Param("start") Integer start);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM commonsReply_1 "
			+ "WHERE cno=#{cno}")
	public int commonsReplyTotalPage(int cno);
	
	@Insert("INSERT INTO commonsReply_1(no,cno,id,name,sex,msg,group_id) "
			 +"VALUES(cs1_no_seq.nextval,#{cno},#{id},#{name},"
			 +"#{sex},#{msg},(SELECT NVL(MAX(group_id)+1,1) FROM commonsReply_1))")
	public void commonsReplyInsert(CommonsReplyVO vo);
	
	@Select("SELECT root,depth FROM commonsReply_1 "
			+ "WHERE no=#{no}")
	public CommonsReplyVO commonsInfoData(int no);
	
	@Update("UPDATE commonsReply_1 SET "
			+ "msg=#{msg} "
			+ "WHERE no=#{no}")
	public void commonsUpdate(CommonsReplyVO vo);
	
	@Delete("DELETE FROM commonsReply_1 "
			+ "WHERE no=#{no}")
	public void commonsDelete(int no);
	
	@Update("UPDATE commonsReply SET "
			+ "depth=depth-1 "
			+ "WHERE no=#{no}")
	public void commonsDepthDecre(int no);
}
