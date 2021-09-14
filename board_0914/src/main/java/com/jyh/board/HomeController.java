package com.jyh.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	String[] newsList= {
			"올 상반기 제주관광소비, 코로나 이전보다 높아↑",
			"제주, 확진자 수 크게 꺾여...123123 확",
			"올 상반기 제주관광소비, 코로나 123 높아↑",
			"제주, 확진자 수 크게 꺾여...1일 오23123후 5시 11명 확",
			"제주, 확진자 수 크게 꺾여...1일 오후 5시 11명 확",
	};
	
	String[] newsList2= {
			"11111↑",
			"제주, 확진자 수 크게 꺾여...123123 확",
			"222↑",
			"제주, 확진자 수 크게 꺾여...1일 오23123후 5시 11명 확",
			"제주, 확진자 수 크게 꺾여...1일 오후 5시 11명 확",
	};
	
	
	
	@RequestMapping(value = "/news1", method=RequestMethod.GET)
	public String news1(Model model) {
		model.addAttribute("newsList", newsList2);
		return "news1";
	}
	@RequestMapping(value = "/news2", method=RequestMethod.GET)
	public String news2(Model model) {
		model.addAttribute("newsList", newsList2);
		return "news2";
	}
	
	
	
}























