package client;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class GameInfoReceiverRunnable implements Runnable
{
	GameData gameData;
	Socket socket;
	DataInputStream is;
	PrintStream os;
	
	public GameInfoReceiverRunnable(Socket newSocket, GameData newGameData)
	{
		gameData = newGameData;
		socket = newSocket;
	}
	
	public void run()
	{
		try
		{
			is = new DataInputStream(socket.getInputStream());
			os = new PrintStream(socket.getOutputStream());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		while(true)
		{
			try
			{
				Scanner s = new Scanner(is.readLine());
				gameData.p1PosX = Float.parseFloat(s.next());
				gameData.p1PosY = Float.parseFloat(s.next());
				gameData.p1FacingRight = Boolean.parseBoolean(s.next());
				gameData.p2PosX = Float.parseFloat(s.next());
				gameData.p2PosY = Float.parseFloat(s.next());
				gameData.p2FacingRight = Boolean.parseBoolean(s.next());
				gameData.p3PosX = Float.parseFloat(s.next());
				gameData.p3PosY = Float.parseFloat(s.next());
				gameData.p3FacingRight = Boolean.parseBoolean(s.next());
				gameData.p4PosX = Float.parseFloat(s.next());
				gameData.p4PosY = Float.parseFloat(s.next());
				gameData.p4FacingRight = Boolean.parseBoolean(s.next());
				gameData.ballPosX = Float.parseFloat(s.next());
				gameData.ballPosY = Float.parseFloat(s.next());
				gameData.p1Color = new Color(Integer.parseInt(s.next()));
				gameData.p2Color = new Color(Integer.parseInt(s.next()));
				gameData.p3Color = new Color(Integer.parseInt(s.next()));
				gameData.p4Color = new Color(Integer.parseInt(s.next()));
				gameData.goalScored = Boolean.parseBoolean(s.next());
				gameData.foul = Boolean.parseBoolean(s.next());
				gameData.p1FoulBarWidth = Float.parseFloat(s.next());
				gameData.p2FoulBarWidth = Float.parseFloat(s.next());
				gameData.p2FoulBarX = Float.parseFloat(s.next());
				gameData.player1Score = Integer.parseInt(s.next());
				gameData.player2Score = Integer.parseInt(s.next());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
