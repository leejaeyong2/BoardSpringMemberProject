package kh.spring.dao;

import kh.spring.dto.LoginDTO;
import kh.spring.dto.MemberDTO;

public interface MemberDAO {
	public int signOut(String loginId) throws Exception;
	public MemberDTO getDataForMyPage(String loginId) throws Exception;
	public int idChk(String loginId);
	public int insert(MemberDTO dto);
	public int login(LoginDTO dto);
	public String testSHA256(String str);
	
}
