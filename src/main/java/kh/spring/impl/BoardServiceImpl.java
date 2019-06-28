package kh.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dto.BoardDTO;
import kh.spring.service.BoardService;

@Component
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAOImpl bdao;
	
	@Transactional("txManager")
	public int deleteBoard(BoardDTO dto) {
		int result = bdao.deleteBoard(dto);
		return result;
	}
	
	@Transactional("txManager")
	public int modifyBoard(BoardDTO dto) {
		int result = bdao.modifyBoard(dto);
		return result;
	}
	
	@Transactional("txManager")
	public BoardDTO readBoard(int bNo) {
		try {
			BoardDTO dto = bdao.readBoard(bNo);
			return dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional("txManager")
	public int writeBoard(BoardDTO dto) {
		int result = bdao.writeBoard(dto);
		return result;
	}
	
	@Transactional("txManager")
	public List<BoardDTO> selectAllBoard(int currentPage) {
		List<BoardDTO> list = bdao.selectAllBoard(currentPage);
		return list;
	}
	
	@Transactional("txManager")
	public String getNaviforBoard(int currentPage) {
		String pageNavi = bdao.getNaviforBoard(currentPage);
		return pageNavi;
	}

	
}
