package com.qq.client.tools;

import java.awt.event.MouseAdapter;

public class QunClickedListener extends MouseAdapter{
	String ClickedQun;
	public QunClickedListener(){

	}
	public QunClickedListener(String ClickedQun){
		this.ClickedQun=ClickedQun;
	}
	public String getClickedQun() {
		return ClickedQun;
	}
	public void setClickedQun(String clickedQun) {
		ClickedQun = clickedQun;
	}
}
