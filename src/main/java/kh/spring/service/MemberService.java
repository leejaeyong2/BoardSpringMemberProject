package kh.spring.service;

import kh.spring.dto.LoginDTO;
import kh.spring.dto.MemberDTO;

public interface MemberService {
	public int signOut(String loginId) throws Exception;
	public int idChk(String loginId) throws Exception;
	public int login(LoginDTO dto) throws Exception;
	public void insertImageJoin(MemberDTO dto) throws Exception;
	public MemberDTO getDataForMyPage(String loginId) throws Exception;

}
