package kr.ac.mjc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.AttachFile;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.model.Query;
import kr.ac.mjc.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	BoardService boardService;
	
	final String FILE_PATH="C:\\workspace\\file\\";
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/list";
	}
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "write";
	}
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String writeDo(Board board) {
		
		
		
		board=boardService.write(board);
		logger.info("boardid {}",board.getId());
		for(MultipartFile file: board.getFiles()) {
			logger.info("filename {}",file.getOriginalFilename());
			if(file.getSize()>0) {
				AttachFile uploadFile=new AttachFile(file.getOriginalFilename(),file.getContentType(),file.getSize(),board.getId());
				
				File destPath=new File(FILE_PATH+uploadFile.getId());
				try {
					file.transferTo(destPath);
					boardService.insertFile(uploadFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			
			
		}
	
		return "redirect:/view?id="+board.getId();
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(Query query) {
		
		boardService.upViewCount(query.getId());
		Board board=boardService.getItem(query.getId());
		ModelAndView mav=new ModelAndView();
		mav.setViewName("view");
		mav.addObject("board",board);
		if(query.getPage()==0) {
			query.setPage(1);
		}
		mav.addObject("query",query);
		
		
		return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(String page) {
		int pageInt=1;
		
		if(page!=null) {
			pageInt=Integer.parseInt(page);
		}
		if("0".equals(page)) {
			pageInt=1;
		}
		
		List<Board> list=boardService.getList(pageInt);
		Navigator nav=boardService.getNavigator(pageInt);
		ModelAndView mav=new ModelAndView("list");
		mav.addObject("list",list);
		mav.addObject("nav", nav);
		return mav;
		
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modify(Query query) {
			
		Board board=boardService.getItem(query.getId());
		ModelAndView mav=new ModelAndView("modify");
		mav.addObject("board",board);
		mav.addObject("query",query);
		return mav;
	}
	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public String modifyDo(Board board) {
		
		boardService.modify(board);
		return "redirect:/view?id="+board.getId()+"&page="+board.getPage();
		
	}
	@RequestMapping(value = "/remove.do", method = RequestMethod.GET)
	public String removeDo(Query query) {
		
		boardService.remove(query.getId());
		return "redirect:/list?page="+query.getPage();
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam(value="id",required=true)String id,HttpServletResponse response) {
			
		AttachFile file=boardService.getFile(id);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getOriginalFileName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		try {
			OutputStream output=response.getOutputStream();
			FileInputStream fis=new FileInputStream(new File(FILE_PATH+file.getId()));
			FileCopyUtils.copy(fis, output);
			output.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	
	
	
}








