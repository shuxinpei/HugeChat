package com.qq.client.tools;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FriendClickedListener extends MouseAdapter  {
	int ClickedID;
	public FriendClickedListener(){

	}
	public FriendClickedListener(int ClickedID){
		this.ClickedID=ClickedID;
	}

	public int getClickedID() {
		return ClickedID;
	}
	public void setClickedID(int clickedID) {
		ClickedID = clickedID;
	}
	 public void mouseClicked(MouseEvent e) {
		 //Ö´ÐÐ·¢ËÍ
		 
	 }

}
