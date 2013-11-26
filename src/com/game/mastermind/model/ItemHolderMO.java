package com.game.mastermind.model;

public class ItemHolderMO {

	private int position;
	private boolean enable;
	
	public ItemHolderMO(int p, boolean e) {
		this.position = p;
		this.enable = e;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	
	
}
