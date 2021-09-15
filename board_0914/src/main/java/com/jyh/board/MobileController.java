package com.jyh.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jyh.board.model.Board;
import com.jyh.board.model.Navigator;
import com.jyh.board.service.BoardService;


@Controller
public class MobileController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/mobile/list",method=RequestMethod.POST)
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
}














