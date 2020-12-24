package kr.ac.mjc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import kr.ac.mjc.model.AttachFile;
import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.model.Query;
import kr.ac.mjc.model.User;
import kr.ac.mjc.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	BoardService boardService;

	final String FILE_PATH = "C:\\workspace\\files\\";

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "redirect:/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView write(HttpSession session) {
		
		User loginUser=(User) session.getAttribute("user");
		ModelAndView mav;
		if(loginUser!=null) {
			 mav=new ModelAndView("write");
			mav.addObject("user",loginUser);	
		}
		else {
			 mav=new ModelAndView("redirect:/login");
		}
		
		return mav;
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String writeDo(Board board) {
		boardService.write(board);

		for (MultipartFile file : board.getFiles()) {

			if (file.getSize() > 0) {
				AttachFile attachFile = new AttachFile(file.getOriginalFilename(), file.getContentType(),
						file.getSize(), board.getId());
				boardService.insertAttachFile(attachFile);
				File destPath = new File(FILE_PATH + attachFile.getId());
				try {
					file.transferTo(destPath);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return "redirect:/view?id=" + board.getId();
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(Query query,HttpSession session) {

		boardService.upViewCount(query.getId());
		Board board = boardService.getItem(query.getId());
		List<AttachFile> attachFiles = boardService.getAttachFiles(query);
		board.setAttachFiles(attachFiles);

		ModelAndView mav = new ModelAndView();
		
		User loginUser=(User) session.getAttribute("user");
		mav.addObject("user",loginUser);
		
		mav.setViewName("view");
		mav.addObject("board", board);
		if (query.getPage() == 0) {
			query.setPage(1);
		}
		mav.addObject("query", query);

		return mav;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(String page,HttpSession session) {
		int pageInt = 1;

		if (page != null) {
			pageInt = Integer.parseInt(page);
		}
		if ("0".equals(page)) {
			pageInt = 1;
		}

		List<Board> list = boardService.getList(pageInt);
		Navigator nav = boardService.getNavigator(pageInt);
		ModelAndView mav = new ModelAndView("list");
		
		User loginUser=(User) session.getAttribute("user");
		

		mav.addObject("list", list);
		mav.addObject("nav", nav);
		mav.addObject("user",loginUser);
		
		return mav;

	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modify(Query query) {

		Board board = boardService.getItem(query.getId());
		ModelAndView mav = new ModelAndView("modify");
		mav.addObject("board", board);
		mav.addObject("query", query);
		return mav;
	}

	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public String modifyDo(Board board) {

		boardService.modify(board);
		return "redirect:/view?id=" + board.getId() + "&page=" + board.getPage();

	}

	@RequestMapping(value = "/remove.do", method = RequestMethod.GET)
	public String removeDo(Query query) {

		boardService.remove(query.getId());
		return "redirect:/list?page=" + query.getPage();
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(String id, HttpServletResponse response, HttpServletRequest request) {
		AttachFile attachFile = boardService.getAttachFileItem(id);

		String header = request.getHeader("User-Agent");
		String fileNameOrg=attachFile.getOriginalFileName();
		try {
			if (header.contains("MSIE") || header.contains("Trident")) {
				fileNameOrg = URLEncoder.encode(fileNameOrg, "UTF-8").replaceAll("\\+", "%20");
				response.setHeader("Content-Disposition", "attachment;filename=" + fileNameOrg + ";");
			} else {
				fileNameOrg = new String(fileNameOrg.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameOrg + "\"");
			}
		}catch(UnsupportedEncodingException e) {}
		

//		String dh = String.format("attachement; filename=\"%s\";", attachFile.getOriginalFileName());
//		response.setHeader("Content-Disposition", dh);
		response.setHeader("Content-Transfer-Encoding", "binary");
		try {
			OutputStream output = response.getOutputStream();
			File destPath = new File(FILE_PATH + id);
			FileInputStream inputStream = new FileInputStream(destPath);
			FileCopyUtils.copy(inputStream, output);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
