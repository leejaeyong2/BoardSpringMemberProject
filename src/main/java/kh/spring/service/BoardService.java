package kh.spring.service;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface BoardService {
	public int writeBoard(BoardDTO dto) throws Exception;
	public String getNaviforBoard(int currentPage) throws Exception;
	public List<BoardDTO> selectAllBoard(int currentPage) throws Exception;
	public BoardDTO readBoard(int bNo) throws Exception;
	public int deleteBoard(BoardDTO dto) throws Exception;
	public int modifyBoard(BoardDTO dto) throws Exception;
}
