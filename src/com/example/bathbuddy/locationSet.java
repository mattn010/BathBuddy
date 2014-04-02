package com.example.bathbuddy;

public class locationSet {
	
	private final String name;
	private final String detail;
	private final int x;
	private final int y;
	private final char icon;

	public locationSet(String name, String detail, int x, int y, char icon) {
		this.name = name;
		this.detail = detail;
		this.x = x;
		this.y = y;
		this.icon = icon;
	}
	
	public locationSet(String name, int x, int y) {
		this.name = name;
		this.detail = "";
		this.x = x;
		this.y = y;
		this.icon = 'd';
	}
	
	public locationSet(String name, int x, int y, char icon) {
		this.name = name;
		this.detail = "";
		this.x = x;
		this.y = y;
		this.icon = icon;
	}
	
	public String getName() { return name; }
	public String getDetail() { return detail; }
	public int getX() { return x; }
	public int getY() { return y; }
	public char getIcon() { return icon; }

}
