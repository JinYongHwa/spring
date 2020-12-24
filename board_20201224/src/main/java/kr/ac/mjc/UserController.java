package kr.ac.mjc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.mjc.model.Result;
import kr.ac.mjc.model.User;
import kr.ac.mjc.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	@RequestMapping(value="/join.do",method=RequestMethod.POST)
	public ModelAndView joinDo(User user) {
		boolean status=userService.join(user);
		if(status) {
			ModelAndView mav=new ModelAndView("login");
			return mav;
		}
		else {
			ModelAndView mav=new ModelAndView("join");
			mav.addObject("msg","이미 존재하는 이메일입니다");
			return mav;
		}
		
	}
	@RequestMapping(value="/checkemail.do",method=RequestMethod.POST)
	public ModelAndView checkEmail(String email) {
		int result=userService.checkEmail(email);
		ModelAndView mav=new ModelAndView("jsonView");
		Result resultObject=new Result();
		if(result==401) {
			resultObject.setSuccess(false);
			resultObject.setMessage("이메일을 확인해주세요");
		}
		else if(result==402) {
			resultObject.setSuccess(false);
			resultObject.setMessage("이미 가입된 이메일입니다");
		}
		else {
			resultObject.setSuccess(true);
			resultObject.setMessage("사용할 수 있는 이메일입니다");
		}
		mav.addObject("result",resultObject);
		return mav;
	} 
}

















