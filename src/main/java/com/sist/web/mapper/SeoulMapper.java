package com.sist.web.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Repository
@Mapper
public interface SeoulMapper {
	/*
	 * 	<select id="seoulLocationListData" resultMap="locationMap" parameterType="int">
		 SELECT st.no,st.contentid,hit,title,image1,address,msg,restdate,usetime,parking
		 FROM seoultravel st JOIN attraction at
		 ON st.contentid = at.contentid
		 ORDER BY st.no ASC
		 OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
	 */
	public List<SeoulVO> seoulLocationListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoultravel "
			+ "WHERE contenttype=12")
	public int seoulLocationTotalPage();
}
