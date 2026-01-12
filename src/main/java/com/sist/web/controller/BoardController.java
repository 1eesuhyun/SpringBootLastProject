package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.vo.*;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.web.service.*;

// RouterController => Pinia에서 router 
// 화면만 변경 => 실제 RestController에서 주로 처리

@Controller
@RequiredArgsConstructor // 생성자를 통해서 @Autowired
public class BoardController {
	private final BoardService bservice;
	// GET / POST => @RequestMapping
	// 사용자가 어떤 값을 보내냐 => 주고 받기(Socket) C/S
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name="page",required = false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<BoardVO> list=bservice.boardListData((curpage-1)*10);
		int totalpage=bservice.boardTotalPage();
		
		// 데이터가 많은 경우 BLOCK처리  이전123456다음
		// 요청에 대한 처리 결과
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		// 출력
		model.addAttribute("main_jsp", "../board/list.jsp");
		return "main/main";
	}
	@GetMapping("/board/insert")
	public String board_insert(Model model)
	{
		model.addAttribute("main_jsp", "../board/insert.jsp");
		return "main/main";
	}
	/*
	 *  @RequestParam => 단일값
	 *  @ModelAttribute => VO 단위로 값을 받는 경우
	 *  @RequestBody => Restcontroller
	 *                  JSON을 객체로 변환
	 */
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(@ModelAttribute BoardVO vo)
	{
		bservice.boardInsert(vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/detail")
	public String board_detail(@RequestParam("no") int no,Model model)
	{
		BoardVO vo=bservice.boardDetailData(no);
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/detail.jsp");
		return "main/main";
	}
	@GetMapping("/board/update")
	public String board_update(@RequestParam("no") int no,Model model)
	{
		BoardVO vo=bservice.boardUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/update.jsp");
		return "main/main";
	}
	@PostMapping(value="/board/update_ok",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String board_update_ok(@ModelAttribute BoardVO vo)
	{
		String res="";
		String s=bservice.boardUpdate(vo);
		if(s.equals("yes"))
		{
			res="<script>"
				+ "location.href=\"/board/detail?no="+vo.getNo()+"\""
				+ "</script>";
		}
		else
		{
			res="<script>"
				+ "alert(\"비밀번호가 틀립니다\");"
				+ "history.back();"
				+ "</script>";
		}
		return res;
	}
	@GetMapping("/board/delete")
	public String board_delete(Model model,@RequestParam("no") int no)
	{
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../board/delete.jsp");
		return "main/main";
	}
	@PostMapping("/board/delete_ok")
	@ResponseBody
	public String board_delete_ok(@RequestParam("no") int no, @RequestParam("pwd") String pwd)
	{
		String res="";
		boolean b=bservice.boardDelete(no,pwd);
		if(b==true)
		{
			res="<script>"
				+ "location.href=\"/board/list\""
				+ "</script>";
		}
		else
		{
			res="<script>"
				+ "alert(\"비밀번호가 틀립니다\");"
				+ "history.back();"
				+ "</script>";
		}
		return res;
	}
}
