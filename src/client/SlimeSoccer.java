package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class SlimeSoccer 
{
	GameData gameData;
	Socket socket;
	DataInputStream is;
	PrintStream os;
	String hostName = "localhost";
	ClientWindow window;
	int port = 6969;
	Font scoreFont = new Font("Franklin Gothic Medium Italic", Font.PLAIN, 80);
	Font goalFont = new Font("Franklin Gothic Medium Italic", Font.PLAIN, 300);
	
	
	public SlimeSoccer()
	{		
		hostName = JOptionPane.showInputDialog("Enter hostname");
		
		window = new ClientWindow(this);
		
		try 
		{
			socket = new Socket(hostName, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		gameData = new GameData();
		new Thread(new GameInfoReceiverRunnable(socket, gameData)).start();
		try {
			os = new PrintStream(socket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		while(true)
		{
			try 
			{
				Thread.sleep(16);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			window.repaint();
			os.println(gameData.upIsPressed+" "+gameData.leftIsPressed+" "+gameData.rightIsPressed);
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 1920, 1080);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 900, 1920, 280);
		
		drawSlime(g, gameData.p1PosX, gameData.p1PosY, 75, gameData.p1FacingRight, gameData.p1Color, gameData.ballPosX, gameData.ballPosY);
		drawSlime(g, gameData.p2PosX, gameData.p2PosY, 75, gameData.p2FacingRight, gameData.p2Color, gameData.ballPosX, gameData.ballPosY);
		drawSlime(g, gameData.p3PosX, gameData.p3PosY, 75, gameData.p3FacingRight, gameData.p3Color, gameData.ballPosX, gameData.ballPosY);
		drawSlime(g, gameData.p4PosX, gameData.p4PosY, 75, gameData.p4FacingRight, gameData.p4Color, gameData.ballPosX, gameData.ballPosY);
		
		g.setColor(Color.YELLOW);
		g.fillOval((int)(gameData.ballPosX-20), (int)(gameData.ballPosY-20), 40, 40);
		int radius = (int)-(gameData.ballPosY)/50;
		g.setColor(Color.GRAY);
		g.fillOval((int)gameData.ballPosX-radius, 50, radius*2, radius*2);
		
		drawGoal(g, 0, 720, true);
		drawGoal(g, 1828, 720, false);
		
		g.setColor(gameData.p1Color);
		g.fillRect(0, 930, (int)gameData.p1FoulBarWidth, 10);
		
		g.setColor(gameData.p3Color);
		g.fillRect((int)gameData.p2FoulBarX, 930, (int)gameData.p2FoulBarWidth, 10);
		
		g.setFont(scoreFont);
		g.setColor(Color.WHITE);
		g.drawString(""+gameData.player1Score, 50, 100);
		g.drawString(""+gameData.player2Score, 1700, 100);
		
		if(gameData.goalScored) 
		{
			g.setFont(goalFont);
			g.drawString("GOAL!", 550, 300);
		}
		if(gameData.foul) 
		{
			g.setFont(goalFont);
			g.drawString("FOUL!", 550, 300);
		}
	}
	
	public void drawSlime(Graphics g, float posX, float posY, int radius, boolean facingRight, Color color, float ballPosX, float ballPosY)
	{
		g.setColor(color);
		g.fillArc((int)(posX - radius), (int)(posY - radius), radius*2, radius*2, 0, 180);
		
		float eyePosY = posY - 35;
		float eyePosX;
		if(facingRight){
			eyePosX = posX + 35;
		} else {
			eyePosX = posX - 35;
		}
		float ballDist=(float)Math.sqrt(Math.pow(ballPosX-eyePosX,2)+Math.pow(ballPosY-eyePosY,2));
		g.setColor(Color.WHITE);
		g.fillOval((int)(eyePosX - 15), (int)(eyePosY - 15), 30, 30);
		g.setColor(Color.BLACK);
		g.fillOval((int)(eyePosX + 6*(ballPosX-eyePosX)/ballDist- 7), (int)(eyePosY + 6*(ballPosY-eyePosY)/ballDist- 7), 14, 14);
	}
	
	public void drawGoal(Graphics g, int posX, int posY, boolean isLeftGoal){
		g.setColor(Color.WHITE);
		for(int i = 0; i < 10; i++)
		{
			g.fillRect(posX + (i*10), posY, 2, 182);
		}
		for(int i = 0; i < 19; i++)
		{
			g.fillRect(posX, posY + (i*10), 92, 2);
		}
		if(isLeftGoal)
		{
			g.fillRect(posX + 90, posY - 5, 10, 190);
		}
		if(!isLeftGoal)
		{
			g.fillRect(posX - 10, posY - 5, 10, 190);
		}
	}
	
	public static void main(String[] args)
	{
		new SlimeSoccer();
	}
}
