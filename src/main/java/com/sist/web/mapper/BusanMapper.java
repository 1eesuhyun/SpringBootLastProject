package com.sist.web.mapper;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.sist.web.vo.BusanVO;

@Repository
@Mapper
public interface BusanMapper {
	/*
	 * <select id="busanListData" resultType="com.sist.web.vo.BusanVO" parameterType="hashmap">
		 SELECT no,contentid,hit,title,address,image1
		 FROM busantravel
		 WHERE contenttype=#{contenttype}
		 ORDER BY no ASC
		 OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
	 */
	public List<BusanVO> busanListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM busantravel "
			+ "WHERE contenttype=#{contenttype}")
	public int busanTotalPage(int contenttype);
}
