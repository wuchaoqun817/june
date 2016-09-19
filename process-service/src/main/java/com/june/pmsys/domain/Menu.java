package com.june.pmsys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单表
 * */
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String menuName;
	private String menuNameDesc;
	private int menuParentId;
	private String menuIcon;
	private String menuUrl;
	private List<Menu> children;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuNameDesc() {
		return menuNameDesc;
	}
	public void setMenuNameDesc(String menuNameDesc) {
		this.menuNameDesc = menuNameDesc;
	}
	public int getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(int menuParentId) {
		this.menuParentId = menuParentId;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", menuNameDesc="
				+ menuNameDesc + ", menuParentId=" + menuParentId
				+ ", menuIcon=" + menuIcon + ", menuUrl=" + menuUrl
				+ ", children=" + children + "]";
	}
	
	
}
