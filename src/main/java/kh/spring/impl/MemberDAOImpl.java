package kh.spring.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.LoginDTO;
import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public int signOut(String loginId) {
		String sql = "delete from member where id = ?";
		
		return template.update(sql, loginId);
	}
	
	
	public MemberDTO getDataForMyPage(String loginId) {
		String sql = "select * from member where id = ?";
		
		return template.queryForObject(sql,new Object[] {loginId}, new RowMapper<MemberDTO>() {
			@Override
			public MemberDTO mapRow(ResultSet rs, int rn) throws SQLException {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setProfilePath(rs.getString("profile_path"));
				return dto;
			}
		});
	}
	
	public int idChk(String loginId) {
		String sql = "select count(*) from member where id=?";
		return template.queryForObject(sql, Integer.class, loginId);
	}
	
//	public int insert(MemberDTO dto) {
//		String sql = "insert into member values(member_seq.nextval,?,?,?,?,?)";
//		return template.update(sql,dto.getId(),testSHA256(dto.getPw()),dto.getEmail(),dto.getPhone(),dto.getProfilePath());
//	}
	
	public int insert(MemberDTO dto) {
		String pw = testSHA256(dto.getPw());
		dto.setPw(pw);
		return sst.insert("MemberDAO.insert", dto);
	}
	
//	public int login(LoginDTO dto) {
//		String sql = "select count(*) from member where id=? and pw=?";
//		return template.queryForObject(sql, Integer.class, dto.getId(),testSHA256(dto.getPw()));
//	}
	
	public int login(LoginDTO dto) {
		String pw = testSHA256(dto.getPw());
		dto.setPw(pw);
		return sst.selectOne("MemberDAO.selectToLogin", dto);
	}
	
	public String testSHA256(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}

}
