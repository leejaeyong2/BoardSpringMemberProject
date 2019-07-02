package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public int deleteBoard(BoardDTO dto) {
		String sql = "delete from board2 where bNo=?";
		return template.update(sql, dto.getbNo());
	}
	
	public int modifyBoard(BoardDTO dto) {
		String sql = "update board2 set title=?, contents=? where bNo=?";
		return template.update(sql, dto.getTitle(), dto.getContents(), dto.getbNo());
	}
	
	@Override
	public BoardDTO readBoard(int bNo) throws Exception {
		String sql = "select * from board2 where bNo=?";
		return template.queryForObject(sql, new Object[] {bNo}, new RowMapper<BoardDTO>() {
		@Override
		public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException{
			BoardDTO dto = new BoardDTO();
			dto.setbNo(rs.getInt("bNo"));
			dto.setTitle(rs.getString("title"));
			dto.setContents(rs.getString("contents"));
			dto.setWriter(rs.getString("writer"));
			return dto;
		}
		});
		
	}
	
	public int writeBoard(BoardDTO dto) {
		String sql = "insert into board2 values(b_seq.nextval, ?,?,?,?)";
		return template.update(sql,dto.getTitle(),dto.getImagePath(), dto.getContents(), dto.getWriter());
	}
	
	static int recordCountPerPage = 5;
	
	public List<BoardDTO> selectAllBoard(int currentPage){
		
		int endNum = currentPage*recordCountPerPage;
		int startNum = endNum - (recordCountPerPage-1);	
		
		String sql = "select * from (select row_number() over(order by bNo desc) as rown,bNo,title,imagePath,contents,writer from board2)"
				+ " where rown between ? and ?";
		return template.query(sql, new Object[] {startNum,endNum}, new RowMapper<BoardDTO>() {
		@Override
		public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException{
			BoardDTO dto = new BoardDTO();
			dto.setbNo(rs.getInt("bNo"));
			dto.setTitle(rs.getString("title"));
			dto.setImagePath(rs.getString("imagePath"));
			dto.setContents(rs.getString("contents"));
			dto.setWriter(rs.getString("writer"));
			return dto;
		}
		});
	}
	
	public int boardContentsSize() {
		String sql = "select count(*) from board2";
		return template.queryForObject(sql, Integer.class);
	}
	
	public String getNaviforBoard(int currentPage){ // 부트스트랩은 int로 받아야함
		int recordTotalCount = this.boardContentsSize();
		int recordCountPerPage = 5; // 5개의 글이 보이게 한다.
		int naviCountPerPage = 5; // 5개의 네비가 보이게 한다.

		int pageTotalCount = recordTotalCount / recordCountPerPage;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount++;
		}

		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;
		int endNavi = startNavi + (naviCountPerPage - 1);

		// 네비 끝값이 최대 페이지 번호를 넘어가면 최대 페이지번호로 네비 끝값을 설정한다.
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		System.out.println("1시작 : 현재 위치 : " + currentPage);
		System.out.println("네비 시작 : " + startNavi);
		System.out.println("네비 끝 : " + endNavi);

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			int prevStartNavi = startNavi - 1;
			sb.append("   <li class=\"page-item\"><a class=\"page-link\" href=\"toBoard?currentPage="
					+ prevStartNavi + "\""
					+ "                     aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>"
					+ "                  </a></li>");

		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<li class=\"page-item\"><a class=\"page-link pageNumber1\" href=\"toBoard?currentPage=" + i + "\">"
					+ i + "</a></li>");
		}
		if (needNext) {
			int nextEndNavi = endNavi + 1;
			sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"toBoard?currentPage="
					+ nextEndNavi++ + "\""
					+ "                     aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>"
					+ "                  </a></li>");
		}

		return sb.toString();
	}

	


	
}
