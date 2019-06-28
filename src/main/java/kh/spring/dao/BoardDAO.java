package kh.spring.dao;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface BoardDAO {
	public int writeBoard(BoardDTO dto) throws Exception;
	public List<BoardDTO> selectAllBoard(int currentPage) throws Exception;
	public int boardContentsSize() throws Exception;
	public BoardDTO readBoard(int bNo) throws Exception;
	public int deleteBoard(BoardDTO dto) throws Exception;
	public int modifyBoard(BoardDTO dto) throws Exception;
	
}
