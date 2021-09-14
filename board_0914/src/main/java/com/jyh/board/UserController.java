package com.jyh.board;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jyh.board.model.User;
import com.jyh.board.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/join_proc",method=RequestMethod.POST)
	public ModelAndView joinProc(User user) {
		int result=userService.join(user);
		logger.info("result{}",result);
		if(result==200) {		//정상적으로 회원가입 됬을때
			ModelAndView mav=new ModelAndView("redirect:login");
			return mav;
		}
		else if(result==401){
			ModelAndView mav=new ModelAndView("join");
			mav.addObject("message","이미 가입된 이메일입니다");
			return mav;
		}
		
		ModelAndView mav=new ModelAndView("redirect:login");
		return mav;
		
	}
	@RequestMapping(value="/login_proc",method=RequestMethod.POST)
	public ModelAndView loginProc(User user,HttpSession session) {
		
		User loginUser=userService.login(user); //로그인된 사용자정보
		if(loginUser!=null) {		//로그인 성공했을때
			session.setAttribute("user", loginUser);
			ModelAndView mav=new ModelAndView("redirect:list");
			return mav;
		}
		else {	//로그인 실패시
			ModelAndView mav=new ModelAndView("login");
			mav.addObject("message", "아이디 또는 패스워드가 틀렸습니다");
			return mav;
		}
	}
	
	
}
