package com.june.pmsys.domain;

import java.io.Serializable;

public class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 页大小
	 */
	private int pagesize; 
	private int pageid;
	/**
	 * 开始页
	 */
	private int pagebegin;
	private int count;
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPageid() {
		return pageid;
	}
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
	public int getPagebegin() {
		return pagebegin;
	}
	public void setPagebegin(int pagebegin) {
		this.pagebegin = pagebegin;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
