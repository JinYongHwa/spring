package kr.ac.mjc;

import javax.servlet.http.HttpSession;

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
			mav.addObject("msg","�̹� �����ϴ� �̸����Դϴ�");
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
			resultObject.setMessage("�̸����� Ȯ�����ּ���");
		}
		else if(result==402) {
			resultObject.setSuccess(false);
			resultObject.setMessage("�̹� ���Ե� �̸����Դϴ�");
		}
		else {
			resultObject.setSuccess(true);
			resultObject.setMessage("����� �� �ִ� �̸����Դϴ�");
		}
		mav.addObject("result",resultObject);
		return mav;
	} 
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView loginDo(User user,HttpSession session) {
		logger.info("{}",user.getEmail());
		logger.info("{}",user.getPassword());
		User loginUser=userService.login(user);
		
		if(loginUser==null) {
			ModelAndView mav=new ModelAndView("login");
			mav.addObject("msg","�α����� �����Ͽ����ϴ�");
			return mav;
		}
		session.setAttribute("user", loginUser);
		
		
		ModelAndView mav=new ModelAndView("redirect:/list");
		return mav;
	}
	
	@RequestMapping(value="/logout.do",method=RequestMethod.POST)
	public ModelAndView logoutDo(HttpSession session) {
		session.setAttribute("user", null);
		ModelAndView mav=new ModelAndView("redirect:/list");
		return mav;
	}
}

















