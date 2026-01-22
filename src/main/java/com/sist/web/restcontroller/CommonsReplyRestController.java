package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class CommonsReplyRestController {

    private final BoardReplyRestController boardReplyRestController;
	private final CommonsReplyService service;
	private final SimpMessagingTemplate template;

	public Map commonsData(int page,int cno)
	{
		Map map=new HashMap();
		List<CommonsReplyVO> list=service.commonsReplyListData(cno, (page-1)*10);
		int totalpage=service.commonsReplyTotalPage(cno);
		
		final int BLOCK=5;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("cno", cno);
		map.put("count", list.size());
		
		return map;
	}
	
	@GetMapping("/commons/list_vue/")
	public ResponseEntity<Map> commons_list_vue(@RequestParam("page") int page,@RequestParam("cno") int cno)
	{
		Map map=new HashMap();
		try
		{
			map=commonsData(page, cno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/commons/insert_vue/")
	public ResponseEntity<Map> commons_insert_vue(@RequestBody CommonsReplyVO vo,HttpSession session)
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
			String pid=service.commonsReplyReplyInsert(vo);
			if(!pid.equals(id))
			{
				template.convertAndSend("/sub/notice/"+pid,"[🤡댓글 알림🤡] 답글이 달렸습니다");
			}
			
			service.commonsReplyInsert(vo);
			map=commonsData(vo.getPage(), vo.getCno());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@DeleteMapping("/commons/delete_vue/")
	public ResponseEntity<Map> commons_delete_vue(@RequestParam("page") int page,@RequestParam("cno") int cno,@RequestParam("no") int no)
	{
		Map map=new HashMap();
		try
		{
			service.commonsDelete(no);
			map=commonsData(page, cno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/commons/update_vue/")
	public ResponseEntity<Map> commons_update(@RequestBody CommonsReplyVO vo)
	{
		Map map=new HashMap();
		try
		{
			service.commonsUpdate(vo);
			map=commonsData(vo.getPage(), vo.getCno());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/commons/reply_reply_insert_vue/")
	public ResponseEntity<Map> commons_reply_reply(@RequestBody CommonsReplyVO vo,HttpSession session)
	{
		Map map=new HashMap();
		try
		{
			String id=(String)session.getAttribute("userid");
			String sex=(String)session.getAttribute("sex");
			String name=(String)session.getAttribute("username");
			vo.setId(id);
			vo.setSex(sex);
			vo.setName(name);
			
			service.commonsReplyReplyInsert(vo);
			map=commonsData(vo.getPage(), vo.getCno());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
