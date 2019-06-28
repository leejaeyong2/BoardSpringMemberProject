package kh.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dto.LoginDTO;
import kh.spring.dto.MemberDTO;
import kh.spring.service.MemberService;

@Component
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAOImpl mdao;

	
	@Transactional("txManager")	// 트랜잭션 관리해줘
	public int signOut(String loginId) {
		int result = mdao.signOut(loginId);
		return result;
	}
	
	@Transactional("txManager")
	public int idChk(String loginId) {
		int result = mdao.idChk(loginId);
		return result;
	}
	
	@Transactional("txManager")
	public int login(LoginDTO dto) {
		int result = mdao.login(dto);
		return result;
	}
	
	@Transactional("txManager")
	public void insertImageJoin(MemberDTO dto) {
		int result = mdao.insert(dto);
	}
	
	@Transactional("txManager")
	public MemberDTO getDataForMyPage(String loginId) {
		MemberDTO result = mdao.getDataForMyPage(loginId);
		return result;
	}
}
