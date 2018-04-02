package com.libjam.neonrain;

public class Constants {
	
	public static final String GAME_ATLAS = "game.atlas";
	public static final String BUBBLE_FONT = "BubbleFont.fnt";
	public static int HIGH_SCORE = 0;
	
	public static enum Direction{
		LEFT,
		RIGHT,
		UP,
		DOWN,
		IDLE
	}
	
	public static Direction getOppositeDirection(Direction direction) {
		switch(direction) {
			case LEFT:
				return Direction.RIGHT;
			case RIGHT:
				return Direction.LEFT;
			case UP:
				return Direction.DOWN;
			case DOWN:
				return Direction.UP;
		}
		return Direction.IDLE;
	}
}
