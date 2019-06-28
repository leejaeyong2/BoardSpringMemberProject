package kh.spring.practice;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.LoginDTO;
import kh.spring.dto.MemberDTO;
import kh.spring.impl.BoardServiceImpl;
import kh.spring.impl.MemberServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private MemberServiceImpl mservice;
	@Autowired
	private BoardServiceImpl bservice;
	
	@RequestMapping("delete")
	public String deleteBoard(HttpServletRequest request, BoardDTO dto) {
		int result = bservice.deleteBoard(dto);
		request.setAttribute("result", result);
		return "alertDelete";
	}
	
	@RequestMapping("modifyBoard")
	public String modifyBoard(HttpServletRequest request, BoardDTO dto, MultipartFile image) {
		int result = bservice.modifyBoard(dto);
		request.setAttribute("result", result);
		return "alertModify";
	}
	
	@RequestMapping("toModify")
	public String toModify(HttpServletRequest request, int bNo) {
		BoardDTO dto = bservice.readBoard(bNo);
		request.setAttribute("dto",dto);
		return "modifyBoard";
	}
	
	@RequestMapping("readBoard")
	public String readBoard(HttpServletRequest request, int bNo) {
		BoardDTO dto = bservice.readBoard(bNo);
		request.setAttribute("dto", dto);
		return "readBoard";
	}
	
	@RequestMapping("/")
	public String index() {
		return "home";
	}
	
	@RequestMapping("toWebChat")
	public String toWebChat() {
		return "webChat";
	}
	
	@RequestMapping("toBoard")
	public String toBoard(Model m, int currentPage) {
		List<BoardDTO> list = bservice.selectAllBoard(currentPage);
		m.addAttribute("list", list);
		String pageNavi = bservice.getNaviforBoard(currentPage);
		m.addAttribute("pageNavi", pageNavi);
		return "boardList";
	}
	
	
	
	@RequestMapping("toWrite")
	public String toWrite() {
		return "write";
	}
	
	@RequestMapping("writeBoard")
	public String writeBoard(HttpServletRequest request,BoardDTO dto, MultipartFile image, Model m) {
		String imagePath = session.getServletContext().getRealPath("/resources");
		System.out.println(dto.getContents());
		dto.setWriter((String)session.getAttribute("loginId"));
		int result = bservice.writeBoard(dto);
		m.addAttribute("result", result);
		
		
//		아래 cos.jar 사용법	
//		System.out.println(imagePath);
//		
//		int maxSize = 10 * 1024 * 1024;
//		try {
//		MultipartRequest multi = new MultipartRequest(request, imagePath, maxSize, "utf-8",new DefaultFileRenamePolicy());
//		String contents = multi.getParameter("contents");
//		
//		BoardDTO dto = new BoardDTO();
//		dto.setWriter(session.getId());
//		dto.setTitle(multi.getParameter("title"));
//		dto.setContents(multi.getParameter("contents"));
//		int result = bservice.writeBoard(dto);
//		System.out.println(result);
//		m.addAttribute("result",result);
//		
//	}catch(Exception e) {
//			e.printStackTrace();
//		}
		return "alertCompleteWrite";
	}
	
	@RequestMapping("/signOut")
	public String signOut(HttpServletRequest request, Model m) {
			String loginId =  (String)session.getAttribute("loginId");
			int result = mservice.signOut(loginId);
			if(result == 1) {
				session.invalidate();
			}
			m.addAttribute("result",result);		
		return "alertSignOut";
	}


	@ResponseBody
	@RequestMapping("idChk.ajax")
	public String idChk(String loginId, Model m) {
		int result = mservice.idChk(loginId);
		String rs;
		if(result==1) {
			rs = "exist";
		}else {
			rs = "noExist";
		}

		//		m.addAttribute("result",result);
		return rs;
	}

	@RequestMapping("/loginProc")
	public String loginProc(LoginDTO dto, Model m) {

		int result = mservice.login(dto);
		if(result == 1) {
			session.setAttribute("loginId", dto.getId());
		}
		m.addAttribute("result", result);
		return "alertLogin";
	}



	@RequestMapping("/joinForm")
	public String toJoinForm() {
		return "joinForm";
	}

	@RequestMapping("/joinProc")
	public String joinProc(MemberDTO dto) {

		String uploadPath = session.getServletContext().getRealPath("/resources");
		System.out.println(uploadPath);
		try {
			dto.getImage().transferTo(new File(uploadPath + "/" + dto.getImage().getOriginalFilename()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		dto.setProfilePath("/resources/" + dto.getImage().getOriginalFilename() );
		mservice.insertImageJoin(dto);
		return "home";
	}

	@RequestMapping("/myPage")
	public String myPage(HttpServletRequest request, Model model) {
		model.addAttribute("dto", mservice.getDataForMyPage((String)session.getAttribute("loginId")));
		return "myPage";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		session.invalidate();
		return "home";
	}

}
