package com.jyh.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jyh.board.model.Board;
import com.jyh.board.model.Navigator;
import com.jyh.board.model.User;
import com.jyh.board.service.BoardService;
import com.jyh.board.service.UserService;


@Controller
public class MobileController {
	
	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/mobile/list",method=RequestMethod.GET)
	public ModelAndView list(String page) {
		int intPage=1;
		if(page!=null) {
			intPage=Integer.parseInt(page);
		}
		
		List<Board> boardList=boardService.getBoardList(intPage);
		Navigator navigator=boardService.getNavigator(intPage);
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("boardList", boardList);
		mav.addObject("nav", navigator);
		return mav;
	}
	
	@RequestMapping(value="/mobile/login",method=RequestMethod.POST)
	public ModelAndView login(@RequestBody User user,HttpSession session) {
		logger.info("{}",user.getEmail());
		User loginUser=userService.login(user); //로그인된 사용자정보
		if(loginUser!=null) {		//로그인 성공했을때
			logger.info("{}",loginUser);
			logger.info("{}",loginUser.getEmail());
			session.setAttribute("user", loginUser);
			ModelAndView mav=new ModelAndView("jsonView");
			mav.addObject("result",true);
			mav.addObject("message","로그인이 성공하였습니다");
			return mav;
		}
		else {	//로그인 실패시
			ModelAndView mav=new ModelAndView("jsonView");
			mav.addObject("result",false);
			mav.addObject("message", "아이디 또는 패스워드가 틀렸습니다");
			return mav;
		}
	}
	
	@RequestMapping(value="/mobile/userinfo",method=RequestMethod.POST)
	public ModelAndView userinfo(HttpSession session) {
		
//		User user=(User)session.getAttribute("user");
		User user=new User();
		user.setId(3);
		user.setEmail("test");
		
		
		ModelAndView mav=new ModelAndView("jsonView");
		if(user==null) {	//로그인이 안되있을때
			mav.addObject("result",false);
		}
		else {	//로그인이 되있을때
			mav.addObject("result",true);
			mav.addObject("user",user);
		}
		return mav;
		
	}
	
	@RequestMapping(value="/mobile/board/write",method=RequestMethod.POST)
	public ModelAndView boardWrite(@RequestBody Board board,HttpSession session) {
		ModelAndView mav=userinfo(session);
		User user=(User)mav.getModel().get("user");
		board.setUserId(user.getId());
		boardService.write(board);
		
		mav=new ModelAndView("jsonView");
		mav.addObject("result",true);
		mav.addObject("boardId",board.getId());
		return mav;
	}
	
	@RequestMapping(value="/mobile/view",method=RequestMethod.POST)
	public ModelAndView boardView(@RequestBody Board board) {
		
		boardService.viewCountUp(board.getId());
		board=boardService.getBoard(board.getId());
		
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("board",board);
		return mav;
	}
}














