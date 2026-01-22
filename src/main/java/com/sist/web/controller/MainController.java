package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.BusanService;
import com.sist.web.service.JejuService;
import com.sist.web.service.RealFindDataService;
import com.sist.web.service.SeoulService;
import com.sist.web.vo.BusanVO;
import com.sist.web.vo.JejuVO;
import com.sist.web.vo.RealFindDataVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;
/*
 *  1. 일반 보안 => 자동 로그인 => 프로시저 / 트리거 / 람다식
 *  2. JWT => 카카오 / 구글 로그인
 *  3. 소켓 : 실시간 : 그룹 / 1:1 => spring AI(챗봇)
 *  4. 실시간 메소지 전송 : Kafka / batch
 *  5. MSA : React => NodeJs
 *  6. CI/CD => 통합
 *  
 *  
 *  
 *  -- procedure
 *  
 *  MyBatis
 *  => 리턴형이 없다
 *  => CREATE OR REPLACE PROCEDURE proc_name(매개변수...)
 *     IS
 *      지역변수
 *     BEGIN
 *       기능 처리
 *     END
 *     /
 *     
 *  -- 목록 출력 / 댓글 => 트리거
 *  -- 일반 JDBC : CURSOR(ResultSet)
 *  -- MyBatis : Map으로 리턴
 */
@Controller
@RequiredArgsConstructor
public class MainController {
	private final SeoulService sservice;
	private final BusanService bservice;
	private final JejuService jservice;
	private final RealFindDataService rservice;
	
	@GetMapping("/main")
	public String main_page(Model model)
	{
		List<SeoulVO> slist=sservice.seoulTop5Data();
		for(SeoulVO vo:slist)
		{
			String[] addrs=vo.getAddress().split(" ");
			vo.setAddress(addrs[1]+" "+addrs[2]);
		}
		List<BusanVO> blist=bservice.busanTop4Data();
		for(BusanVO vo:blist)
		{
			String[] addrs=vo.getAddress().split(" ");
			vo.setAddress(addrs[1]+" "+addrs[2]);
		}
		List<JejuVO> jlist=jservice.jejuTop4Data();
		for(JejuVO vo:jlist)
		{
			String[] addrs=vo.getAddress().split(" ");
			vo.setAddress(addrs[1]+" "+addrs[2]);
		}
		
		List<RealFindDataVO> rlist=rservice.realFindDataList();
		model.addAttribute("rlist", rlist);
		
		model.addAttribute("slist", slist);
		model.addAttribute("blist", blist);
		model.addAttribute("jlist", jlist);
		
		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
}
