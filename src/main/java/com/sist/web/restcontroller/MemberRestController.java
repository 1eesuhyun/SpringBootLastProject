package com.sist.web.restcontroller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
	private final MemberService mservice;
	
	@GetMapping("/member/idCheck_vue/")
	public String idCheck_vue(@RequestParam("userid") String userid)
	{
		int count=mservice.memberIdCheck(userid);
		return String.valueOf(count);
	}
}
