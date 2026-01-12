package com.sist.web.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.web.mapper.*;
import lombok.RequiredArgsConstructor;
import com.sist.web.vo.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper mapper;

	@Override
	public int memberIdCheck(String userid) {
		// TODO Auto-generated method stub
		return mapper.memberIdCheck(userid);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	/*
	 *  일괄처리 => 성공 / 실패
	 *        commit / rollback
	 */
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		mapper.memberInsert(vo);
	}

	@Override
	public void memberAuthorityInsert(String userid) {
		// TODO Auto-generated method stub
		mapper.memberAuthorityInsert(userid);
	}

	@Override
	public MemberVO memberInfoData(String userid) {
		// TODO Auto-generated method stub
		return mapper.memberInfoData(userid);
	}

	
	
}
