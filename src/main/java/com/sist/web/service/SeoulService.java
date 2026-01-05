package com.sist.web.service;
import java.util.*;
import com.sist.web.vo.SeoulVO;

/*
 *  user요청 ---------- DispatcherServlet
 *                          | 위임
 *                       HandlerMapping
 *                          | URI주소 찾기 => @GetMapping
 *                                          @PostMapping
 *                                          @PutMapping
 *                                          @DeleteMapping
 *                          | 밑에 있는 메소드 호출
 *                                  |
 *                               Service
 *                                  |
 *                               Mapper => 수정시에 의존성 약하게
 *                                  |
 *                                오라클
 */
public interface SeoulService {
	public List<SeoulVO> seoulListData(Map map);
	public int seoulTotalPage(int contenttype);
	public SeoulVO seoulAttractionDetailData(int contentid);
}
