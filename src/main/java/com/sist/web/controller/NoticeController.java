package com.sist.web.controller;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.service.*;
import com.sist.web.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
 *  1. Spring-Boot
 *     = ORM(MyBatis)
 *            | - CRUD / 동적 쿼리
 *              - JOIN 
 *     = RestFul => GET / Post / Put / Delete
 *     = FileUpload
 *     = WebSocket
 *     = Kafka
 *     = Task / JUnit
 */
@Controller
@RequiredArgsConstructor
public class NoticeController {
	private final NoticeService service;
	
	@GetMapping("notice/list")
	public String notice_list(@RequestParam(name="page",required = false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<NoticeVO> list=service.noticeListdata((curpage-1)*10);
		int totalpage=service.noticeTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		
		model.addAttribute("main_jsp", "../notice/list.jsp");
		return "main/main";
	}
	@GetMapping("/admin/notice_list")
	public String admin_notice_list(@RequestParam(name="page",required = false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<NoticeVO> list=service.noticeListdata((curpage-1)*10);
		int totalpage=service.noticeTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		
		model.addAttribute("admin_jsp", "../admin/notice_list.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	@GetMapping("/admin/notice_insert")
	public String admin_notice_insert(Model model)
	{
		model.addAttribute("admin_jsp", "../admin/notice_insert.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	@PostMapping("/admin/notice_insert_ok")
	public String notice_insert_ok(@ModelAttribute NoticeVO vo,HttpServletRequest request)
	throws Exception
	{
		String uploadDir=request.getServletContext().getRealPath("/upload");
		File dir=new File(uploadDir);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		List<MultipartFile> files=vo.getFiles();
		String filename="";
		boolean bCheck=false;
		for(MultipartFile file:files)
		{
			if(file.isEmpty())
			{
				bCheck=false;
			}
			else
			{
				String oname=file.getOriginalFilename();
				File f=new File(uploadDir+"/"+oname);
				if(f.exists())
				{
					String name=oname.substring(0,oname.lastIndexOf("."));
					String ext=oname.substring(oname.lastIndexOf("."));
					int count=1;
					while(f.exists())
					{
						String newName=name+"("+count+")"+ext;
						f=new File(uploadDir+"/"+newName);
						count++;
					}
				}
				filename+=f.getName()+",";
				bCheck=true;
				Path path=Paths.get(uploadDir,f.getName());
				Files.copy(file.getInputStream(), path);
			}
		}
		if(bCheck==true)
		{
			filename=filename.substring(0,filename.lastIndexOf(",")); 
			vo.setFilename(filename);
			vo.setFilecount(files.size());
		}
		else
		{
			vo.setFilename("");
			vo.setFilecount(0);
		}
		service.noticeInsert(vo);
		return "redirect:/admin/notice_list";
	}
	@GetMapping("/admin/notice_detail")
	public String admin_notice_detail(@RequestParam("no") int no,Model model)
	{
		NoticeVO vo=service.noticeDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("admin_jsp", "../admin/notice_detail.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	@GetMapping("/notice/detail")
	public String notice_detail(@RequestParam("no") int no,Model model)
	{
		NoticeVO vo=service.noticeDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "../notice/detail.jsp");
		return "main/main";
	}
	@GetMapping("/admin/notice_delete")
	public String admin_notice_delete(@RequestParam("no") int no,HttpServletRequest request)
	{
		NoticeVO info=service.noticeFileInfoData(no);
		if(info.getFilecount()!=0)
		{
			// 파일 삭제
			String path=request.getServletContext().getRealPath("/upload");
			StringTokenizer st=new StringTokenizer(info.getFilename(),",");
			while(st.hasMoreTokens())
			{
				File f=new File(path+"/"+st.nextToken());
				f.delete();
			}
		}
		service.noticeDelete(no);
		return "redirect:/admin/notice_list";
	}
	@GetMapping("/admin/notice_update")
	public String admin_notice_update(Model model,@RequestParam("no") int no)
	{
		NoticeVO vo=service.noticeUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("admin_jsp", "../admin/notice_update.jsp");
		model.addAttribute("main_jsp", "../admin/admin_main.jsp");
		return "main/main";
	}
	@PostMapping("/admin/notice_update_ok")
	public String admin_notice_update_ok(@ModelAttribute NoticeVO vo,RedirectAttributes ra)
	{
		// 수정
		service.noticeUpdate(vo);
		ra.addAttribute("no",vo.getNo()); // ?no=1
		return "redirect:/admin/notice_detail";
	}
}
