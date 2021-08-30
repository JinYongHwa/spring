package kr.ac.mjc;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.ac.mjc.model.User;
import kr.ac.mjc.service.TestService;


@Controller
public class UserController {
	
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private TestService testService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login( Model model) {
		return "login";
	}
	@RequestMapping(value = "/login_proc", method = RequestMethod.POST)
	public String loginProc(User user,Model model,HttpSession session) {
		
		User loginUser=testService.login(user);
		
		if(loginUser!=null) {
			logger.info("login user {}",loginUser.getName());
			session.setAttribute("user", loginUser);
			return "redirect:/";
		}
		else {
			model.addAttribute("message","아이디 혹은 패스워드가 틀렸습니다");
			return "login";
		}
		
	}
	
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join( Model model) {
		return "join";
	}
	
	
	@RequestMapping(value = "/join_proc", method = RequestMethod.POST)
	public String joinProc(User user,Model model) {
		logger.info("name {}",user.getName());
		boolean result=testService.checkId(user);
		if(!result) {
			logger.info("이미 존재하는 아이디입니다");
			model.addAttribute("message","이미 존재하는 아이디입니다");
			return "join";
		}
		result=testService.join(user);
		if(result) {
			return "login";
		}
		return "join";
		
	}
	

}
