package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
	@GetMapping("/mypage/mypage_main")
	public String mypage_main(Model model)
	{
		model.addAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
		model.addAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "main/main";
	}
	
	@GetMapping("/mypage/mypage_reserve")
	public String mypage_reserve(Model model)
	{
		model.addAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
		model.addAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "main/main";
	}
}
