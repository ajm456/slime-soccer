package client;

import java.awt.Color;

public class GameData
{
	float p1PosX;
	float p1PosY;
	float p2PosX;
	float p2PosY;
	float p3PosX;
	float p3PosY;
	float p4PosX;
	float p4PosY;
	float ballPosX;
	float ballPosY;
	float p1FoulBarWidth;
	float p2FoulBarWidth;
	float p2FoulBarX;
	int player1Score, player2Score;
	Color p1Color, p2Color, p3Color, p4Color;
	boolean p1FacingRight;
	boolean p2FacingRight;
	boolean p3FacingRight;
	boolean p4FacingRight;
	boolean goalScored;
	boolean foul;
	
	boolean upIsPressed;
	boolean rightIsPressed;
	boolean leftIsPressed;
}