package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ClientWindow extends JFrame
{
	SlimeSoccer slimesoccerclient;
	ClientPanel panel;
	
	ClientWindow(SlimeSoccer temp)
	{
		setVisible(true);
		setFocusable(false);
		slimesoccerclient = temp;
		panel = new ClientPanel(slimesoccerclient);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(panel);
		panel.setFocusable(true);
		panel.requestFocus();
		panel.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					slimesoccerclient.gameData.rightIsPressed=true;
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					slimesoccerclient.gameData.leftIsPressed=true;
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					slimesoccerclient.gameData.upIsPressed=true;
				}
			}

			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					slimesoccerclient.gameData.rightIsPressed=false;
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					slimesoccerclient.gameData.leftIsPressed=false;
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					slimesoccerclient.gameData.upIsPressed=false;
				}	
			}

			public void keyTyped(KeyEvent e)
			{
				
			}
			
		});
		pack();
	}

}