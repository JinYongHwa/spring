package kr.ac.mjc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.model.Query;
import kr.ac.mjc.service.BoardService;

@Controller
public class MobileController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/mobile/list",method = RequestMethod.GET)
	public String listView() {
		return "/mobile/list";
	}
	
	@RequestMapping(value="/api/v1/list",method = RequestMethod.POST)
	public ModelAndView list(Query query) {
		List<Board> list= boardService.getList(query.getPage());
		Navigator nav=boardService.getNavigator(query.getPage());
		ModelAndView mav=new ModelAndView("jsonView");
		mav.addObject("list",list);
		mav.addObject("nav",nav);
		return mav;
	}
	
}
