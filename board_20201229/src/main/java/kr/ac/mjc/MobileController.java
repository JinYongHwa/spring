package kr.ac.mjc;

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

import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.model.Query;
import kr.ac.mjc.model.User;
import kr.ac.mjc.service.BoardService;
import kr.ac.mjc.service.UserService;

@Controller
public class MobileController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);
	
	@RequestMapping(value="/mobile/list",method=RequestMethod.GET)
	public String list() {
		return "mobile/list";
	}
	
	
	@RequestMapping(value="/mobile/list.do",method=RequestMethod.POST)
	public ModelAndView listDo(Query query) {
		List<Board> list=boardService.getList(query.getPage());
		Navigator nav=boardService.getNavigator(query.getPage());
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("list",list);
		mav.addObject("nav",nav);
		return mav;
	}
	@RequestMapping(value="/mobile/login",method=RequestMethod.GET)
	public String login() {
		return "mobile/login";
	}
	@RequestMapping(value="/mobile/login.do",method=RequestMethod.POST)
	public ModelAndView loginDo(@RequestBody User user,HttpSession session) {
		logger.info("{}",user.getEmail());
		logger.info("{}",user.getPassword());
		User loginUser=userService.login(user);
		
		
		ModelAndView mav=new ModelAndView("jsonView");
		
		if(loginUser==null) {
			mav.addObject("result", "fail");
			mav.addObject("message","���̵� �н����尡 ��ġ���� �ʽ��ϴ�");
		}
		else {
			mav.addObject("result", "success");
			session.setAttribute("user", loginUser);
		}
		
		return mav;
	}
	@RequestMapping(value="/mobile/userinfo.do",method=RequestMethod.POST)
	public ModelAndView userInfo(HttpSession session) {
		User loginUser=(User)session.getAttribute("user");
		ModelAndView mav=new ModelAndView("jsonView");
		if(loginUser!=null) {
			mav.addObject("result", true);
			mav.addObject("user", loginUser);
		}
		else {
			mav.addObject("result", false);
		}
		return mav;
	}
	
	@RequestMapping(value="/mobile/logout.do",method=RequestMethod.POST)
	public ModelAndView logout(HttpSession session) {
		session.setAttribute("user", null);
		
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("result",true);
		return mav;
	}
	@RequestMapping(value="/mobile/write",method=RequestMethod.GET)
	public String write() {
		return "mobile/write";
	}
	@RequestMapping(value="/mobile/write.do",method=RequestMethod.POST)
	public ModelAndView writeDo(@RequestBody Board board,HttpSession session) {
		
		User loginUser=(User)session.getAttribute("user");
		
		ModelAndView mav=new ModelAndView("jsonView");
		if(loginUser!=null) {
			board.setUserId(loginUser.getId());
			boardService.write(board);
			
			mav.addObject("result",true);
			mav.addObject("board",board);
		}
		else {
			mav.addObject("result",false);
			mav.addObject("message","�Խñ��� �ۼ��Ϸ��� �α����� ���ּ���");
		}
			
		return mav;
	}
	@RequestMapping(value="/mobile/view",method=RequestMethod.GET)
	public String view() {
		return "mobile/view";
	}
	
	@RequestMapping(value="/mobile/board.do",method=RequestMethod.POST)
	public ModelAndView getItem(@RequestBody Board board) {
		boardService.upViewCount(board.getId());
		board=boardService.getItem(board.getId());
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("board", board);
		return mav;
	}
	@RequestMapping(value="/mobile/test",method=RequestMethod.POST)
	public String test() {
		return "mobile/dist";
	}
	
}