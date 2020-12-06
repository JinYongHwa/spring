package kr.ac.mjc;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value="page",required=false) String page) {
		
		int pageInt=1;
		if(page!=null) {
			pageInt=Integer.parseInt(page);
		}
		else {
			pageInt=1;
		}
		
		List<Board> list =service.list(pageInt);
		
		int count=service.count();
		Navigator nav=service.getNavigator(pageInt, count);
		
		ModelAndView mav=new ModelAndView("list");
		mav.addObject("list", list);
		mav.addObject("nav", nav);
		mav.addObject("currentPage", pageInt);
		return mav;
	}
	
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "write";
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String writeProcess(Board board) {
		
		service.insertBoard(board);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView boardItem(
			@RequestParam(value="id",required=true) String id,
			@RequestParam(value="page",required=false) String page) {
		
		Board board=service.getBoard(Integer.parseInt(id));
		ModelAndView mav=new ModelAndView("board");
		mav.addObject("board", board);
		mav.addObject("page", page);
		
		return mav;
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modify(@RequestParam(value="id",required=true) String id,
			@RequestParam(value="page",required=false) String page) {
		
		
		Board board=service.getBoard(Integer.parseInt(id));
		ModelAndView mav=new ModelAndView("modify");
		mav.addObject("board", board);
		mav.addObject("page", page);
		
		return mav;
		
		
	}
	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public String modify(Board board,@RequestParam(value="page",required=false) String page) {
		
		service.modify(board);
		
		return String.format("redirect:/board?id=%s&page=%s",board.getId(),page);
	}
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id",required=true) String id,@RequestParam(value="page",required=false) String page) {
		service.delete(Integer.parseInt(id));
		
		return String.format("redirect:/?page=%s",page);
	}
	
	
	
}
