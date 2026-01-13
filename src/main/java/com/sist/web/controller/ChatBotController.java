package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatBotController {
   @GetMapping("/chatbot/chatbot")
   public String chatbot_list(Model model)
   {
	   model.addAttribute("main_jsp", "../chatbot/chatbot.jsp");
	   return "main/main";
   }
}