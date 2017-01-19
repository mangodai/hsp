package web.store.order.domain;

import java.util.List;

public class PageBean<T> {
	private int nowPage;//现在的页数
	private int allPage;//总共的页数
	private int beanTotal;//数据库数量
	private int pageSize;//每页的数量
	private List<T> list;//数据库返回的对象
	private int beginPage;
	private int endPage;
	private int pageLength = 10;
	
	public int getPageLength() {
		return pageLength;
	}
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public int getBeanTotal() {
		return beanTotal;
	}
	public void setBeanTotal(int beanTotal) {
		this.beanTotal = beanTotal;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageBean [nowPage=" + nowPage + ", allPage=" + allPage
				+ ", beanTotal=" + beanTotal + ", pageSize=" + pageSize
				+ ", list=" + list + "]";
	}

	
	
	
}
