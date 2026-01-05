package com.sist.web.mapper;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.sist.web.vo.*;

@Repository
@Mapper
public interface JejuMapper {
	/*
	 * <select id="jejuListData" resultType="com.sist.web.vo.JejuVO" parameterType="hashmap">
		 SELECT no,contentid,hit,title,address,image1
		 FROM busantravel
		 WHERE contenttype=#{contenttype}
		 ORDER BY no ASC
		 OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
	 */
	public List<JejuVO> jejuListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM jejutravel "
			+ "WHERE contenttype=#{contenttype}")
	public int jejuTotalPage(int contenttype);
}
