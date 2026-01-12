package com.sist.web.restcontroller;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class BoardReplyRestController {
	private final BoardReplyService rservice;
	
	public Map commonsData(int bno)
	{
		Map map=new HashMap();
		List<BoardReplyVO> list=rservice.boardReplyListData(bno);
		int count=rservice.boardReplyCount(bno);
		
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	
	@GetMapping("/reply/list_vue/")
	public ResponseEntity<Map> reply_list_vue(@RequestParam("bno") int bno)
	{
		Map map=new HashMap();
		try
		{
			map=commonsData(bno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/reply/insert_vue/")
	public ResponseEntity<Map> reply_insert_vue(@RequestBody BoardReplyVO vo,HttpSession session)
	{
		Map map=new HashMap();
		try
		{
			String id=(String)session.getAttribute("userid");
			String name=(String)session.getAttribute("username");
			String sex=(String)session.getAttribute("sex");
			vo.setId(id);
			vo.setName(name);
			vo.setSex(sex);
			rservice.boardReplyInsert(vo);
			
			map=commonsData(vo.getBno());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@DeleteMapping("/reply/delete_vue/")
	public ResponseEntity<Map> reply_delete_vue(@RequestParam("no") int no,@RequestParam("bno") int bno)
	{
		Map map=new HashMap();
		try
		{
			rservice.boardReplyDelete(no);
			map=commonsData(bno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
