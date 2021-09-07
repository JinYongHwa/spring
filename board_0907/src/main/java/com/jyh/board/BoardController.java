package com.jyh.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jyh.board.model.Board;
import com.jyh.board.model.Navigator;
import com.jyh.board.model.User;
import com.jyh.board.service.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public ModelAndView writeView(HttpSession session) {
		User loginUser=(User)session.getAttribute("user");
		if(loginUser!=null) {	//로그인이 되있을때
			ModelAndView mav=new ModelAndView("write");
			mav.addObject("user",loginUser);
			return mav;
		}
		else {	//로그인이 안되있을때
			ModelAndView mav=new ModelAndView("redirect:login");
			return mav;
		}
	}
	
	@RequestMapping(value="/write_proc",method=RequestMethod.POST)
	public String writeProc(Board board) {
		logger.info("board title {}",board.getTitle());
		boardService.write(board);
		return "redirect:view?id="+board.getId();
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public ModelAndView view(String id) {
		int intId=Integer.parseInt(id);
		
		boardService.viewCountUp(intId);
		Board board=boardService.getBoard(intId);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("view");
		mav.addObject("board",board);
		return mav;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public ModelAndView modifyView(String id){
		int intId=Integer.parseInt(id);
		Board board=boardService.getBoard(intId);
		
		ModelAndView mav=new ModelAndView("modify");
		mav.addObject("board",board);
		return mav;
	}
	@RequestMapping(value="/modify_proc",method=RequestMethod.POST)
	public String modifyProc(Board board) {
		boardService.modify(board);
		
		return "redirect:view?id="+board.getId();
		
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView main() {
		return list("1");
	}
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(String page) {
		int intPage=1;
		if(page!=null) {
			intPage=Integer.parseInt(page);
		}
		
		List<Board> boardList=boardService.getBoardList(intPage);
		Navigator navigator=boardService.getNavigator(intPage);
		
		ModelAndView mav=new ModelAndView("list");
		mav.addObject("boardList", boardList);
		mav.addObject("nav", navigator);
		return mav;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id) {
		int intId=Integer.parseInt(id);
		boardService.delete(intId);
		return "redirect:list";
	}
	
	

}











