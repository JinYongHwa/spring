package kr.ac.mjc.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.ac.mjc.service.BoardService;

public class Navigator {
	private static final Logger logger = LoggerFactory.getLogger(Navigator.class);
	private int page;
	private int start;
	private int end;
	private int prevPage;
	private int nextPage;
	private int lastPage;
	private int startNum;
	private boolean prev;
	private boolean next;

	int count;

	int itemPerPage = 10;
	int navCount = 10;
	
	public Navigator() {
	
	}
	public Navigator(int itemPerPage,int navCount) {
		this.itemPerPage=itemPerPage;
		this.navCount=navCount;
	}
	public int getSkip(int page) {
		return (page-1)*itemPerPage;
	}
	
	
	public List<Integer> getNavArr() {
		ArrayList<Integer> arr=new ArrayList();
		for(int i=start;i<=end;i++) {
			arr.add(i);
		}
		return arr;
	}

	 public Navigator getNav(int page,int count) {
		
		setPage(page);
		setCount(count);

		setStart(((int)Math.floor(page-1)/getNavCount())*getNavCount()+1);
		setEnd(getStart()+getNavCount()-1);
		
		
		int totalPage=getTotalPage(count);
		
		//글이 네비게이션셋일경우
		if(getStart()==1) {
			setPrev(false);
		}
		else {
			int prevPage=(page-1)/getNavCount() *getNavCount() -getNavCount()+1;
			setPrevPage(prevPage);
			setPrev(true);
		}
		//네비게이션 마지막이 글전체페이지수를 초과할경우	
		if(getEnd()>=totalPage) {
			setEnd(totalPage);
			setNext(false);
		}
		else {
			setNext(true);
			setNextPage(getStart()+navCount);
		}
		
		int startNum=count-((page-1)*itemPerPage);
		setStartNum(startNum);
		
		
		return this;
	}

	public int getTotalPage(int count) {
		return (int) Math.ceil((float)count / itemPerPage);
	}
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}
	
	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public int getNavCount() {
		return navCount;
	}

	public void setNavCount(int navCount) {
		this.navCount = navCount;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	

	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
