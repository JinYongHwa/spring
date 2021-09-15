package com.jyh.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jyh.board.model.AttachFile;
import com.jyh.board.model.Board;
import com.jyh.board.model.Navigator;
import com.jyh.board.model.User;
import com.jyh.board.service.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	File filePath=new File("c:\\workspace\\file");
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public ModelAndView writeView(HttpSession session) {
		User loginUser=(User)session.getAttribute("user");
		if(loginUser!=null) {	//로그인이 되있을때
			ModelAndView mav=new ModelAndView("write");
			mav.addObject("user",loginUser);
			return mav;
		}
		else {	//로그인이 안되있을때
			ModelAndView mav=new ModelAndView("redirect:login");
			return mav;
		}
	}
	
	@RequestMapping(value="/write_proc",method=RequestMethod.POST)
	public String writeProc(Board board,HttpSession session) {
		User loginUser=(User)session.getAttribute("user");
		board.setUserId(loginUser.getId());
		boardService.write(board);
		
		for(MultipartFile file : board.getFiles()) {
			logger.info("file size{}",file.getSize());
			logger.info("file type{}",file.getContentType());
			logger.info("file name {}",file.getOriginalFilename());
			AttachFile attachFile=boardService.insertFile(file,board.getId());
			
			File saveFilePath=new File(filePath,attachFile.getId());
			try {
				file.transferTo(saveFilePath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "redirect:view?id="+board.getId();
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public ModelAndView view(String id) {
		int intId=Integer.parseInt(id);
		
		boardService.viewCountUp(intId);
		Board board=boardService.getBoard(intId);
		List<AttachFile> files=boardService.getAttachFiles(intId);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("view");
		mav.addObject("board",board);
		mav.addObject("files",files);
		return mav;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public ModelAndView modifyView(String id,HttpSession session){
		int intId=Integer.parseInt(id);
		Board board=boardService.getBoard(intId);
				
		User user=(User)session.getAttribute("user");
		if(user==null) {	//로그인 되있지 않을경우
			return new ModelAndView("redirect:login");
		}
		else if(user.getId()!=board.getUserId()) {
			return new ModelAndView("redirect:list");
		}
		ModelAndView mav=new ModelAndView("modify");

		mav.addObject("board",board);
		return mav;
	}
	@RequestMapping(value="/modify_proc",method=RequestMethod.POST)
	public String modifyProc(Board board) {
		boardService.modify(board);
		
		return "redirect:view?id="+board.getId();
		
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView main() {
		return new ModelAndView("redirect:list");
	}
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(String page) {
		int intPage=1;
		if(page!=null) {
			intPage=Integer.parseInt(page);
		}
		
		List<Board> boardList=boardService.getBoardList(intPage);
		Navigator navigator=boardService.getNavigator(intPage);
		
		ModelAndView mav=new ModelAndView("list");
		mav.addObject("boardList", boardList);
		mav.addObject("nav", navigator);
		return mav;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id,HttpSession session) {
		int intId=Integer.parseInt(id);
		Board board=boardService.getBoard(intId);
		User user=(User)session.getAttribute("user");
		if(user==null||board.getUserId()!=user.getId()) {
			
			return "redirect:view?id="+id;
		}
		boardService.delete(intId);
		return "redirect:list";
	}
	
	@RequestMapping(value="/file",method=RequestMethod.GET)
	public void file(String id,HttpServletResponse response,HttpServletRequest request) {
		AttachFile attachFile=boardService.getAttachFile(id);
		
		
		String header = request.getHeader("User-Agent");
		String fileNameOrg=attachFile.getOriginalname();
		try {
			if (header.contains("MSIE") || header.contains("Trident")) {
				fileNameOrg = URLEncoder.encode(fileNameOrg, "UTF-8").replaceAll("\\+", "%20");
				response.setHeader("Content-Disposition", "attachment;filename=" + fileNameOrg + ";");
			} else {
				fileNameOrg = new String(fileNameOrg.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameOrg + "\"");
			}
		}catch(UnsupportedEncodingException e) {}
		
		File saveFilePath=new File(filePath,id);
		try {
			FileInputStream fis=new FileInputStream(saveFilePath);
			ServletOutputStream os=response.getOutputStream();
			FileCopyUtils.copy(fis,os);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {}
		
	}
	
	

}











