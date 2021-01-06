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
	public ModelAndView listDo(Query query,HttpSession session) {
		
		
		logger.info("page {}",(String)session.getAttribute("test"));
		List<Board> list=boardService.getList(query.getPage());
		Navigator nav=boardService.getNavigator(query.getPage());
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("list",list);
		mav.addObject("nav",nav);
		session.setAttribute("test", "test");
		return mav;
	}
	@RequestMapping(value="/mobile/login",method=RequestMethod.GET)
	public String login() {
		return "mobile/login";
	}
	@RequestMapping(value="/mobile/login.do",method=RequestMethod.POST)
	public ModelAndView loginDo(User user,HttpSession session) {
		logger.info("{}",session.getAttribute("user"));
		
		User loginUser=userService.login(user);
		
		
		ModelAndView mav=new ModelAndView("jsonView");
		
		if(loginUser==null) {
			mav.addObject("result", false);
			mav.addObject("message","아이디나 패스워드가 일치하지 않습니다");
		}
		else {
			mav.addObject("result",true);
			mav.addObject("message","로그인 완료되었습니다");
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
	@RequestMapping(value="/mobile/checkemail.do",method=RequestMethod.POST)
	public ModelAndView checkEmail(String email) {
		int result=userService.checkEmail(email);
		ModelAndView mav=new ModelAndView("jsonView");
		
		if(result==200) {
			mav.addObject("isUse",true);
			mav.addObject("message","사용 가능한 이메일입니다");
		}
		else if(result==401) {
			mav.addObject("isUse",false);
			mav.addObject("message","이메일 형식을 맞춰주세요");
		}
		else if(result==402) {
			mav.addObject("isUse",false);
			mav.addObject("message","이미 가입된 이메일입니다");
		}
		return mav;
	}
	@RequestMapping(value="/mobile/join.do",method=RequestMethod.POST)
	public ModelAndView join(String email,String name,String password) {
		User user=new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		
		boolean result=userService.join(user);
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("result",result);
		if(result) {
			mav.addObject("message","회원가입이 완료되었습니다");
		}
		else {
			mav.addObject("message","회원가입이 실패하였습니다");
		}
		return mav;
		
	}
	
	
	@RequestMapping(value="/mobile/write",method=RequestMethod.GET)
	public String write() {
		return "mobile/write";
	}
	@RequestMapping(value="/mobile/write.do",method=RequestMethod.POST)
	public ModelAndView writeDo(Board board,HttpSession session) {
		
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
			mav.addObject("message","게시글을 작성하려면 로그인을 해주세요");
		}
			
		return mav;
	}
	@RequestMapping(value="/mobile/view",method=RequestMethod.GET)
	public String view() {
		return "mobile/view";
	}
	
	@RequestMapping(value="/mobile/board.do",method=RequestMethod.POST)
	public ModelAndView getItem(int id) {
		logger.info("{}",id);
		boardService.upViewCount(id);
		Board board=boardService.getItem(id);
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("board", board);
		return mav;
	}
	@RequestMapping(value="/mobile/remove.do",method=RequestMethod.POST)
	public ModelAndView removeBoard(int id) {
		boardService.remove(id);
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("result", true);
		return mav;
	}
	@RequestMapping(value="/mobile/modify.do",method=RequestMethod.POST)
	public ModelAndView modifyBoard(int id,String title,String text) {
		boardService.modify(board);
	}
	
	@RequestMapping(value="/mobile/test",method=RequestMethod.POST)
	public String test() {
		return "mobile/dist";
	}
	
}
