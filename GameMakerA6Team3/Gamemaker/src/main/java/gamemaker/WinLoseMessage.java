package gamemaker;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class Name: WinLoseMessage Class Responsibilities: Contains the messages on
 * winning and loosing the game. Class collaborators: GameMaker.java
 */
public class WinLoseMessage
{
	// The image.
	private BufferedImage image;

	/* Method showWinMessage: Show win message. */
	public void showWinMessage(Graphics g)
	{
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("Click 'Re-build' and then 'Start' to start a new game ...", Constants.DISPLAY_X - Constants.DISPLAY_NEWLINE, Constants.DISPLAY_Y);
		try
		{
			image = ImageIO.read(ClassLoader.getSystemResource("image/win.jpg"));
		}
		catch (IOException ex)
		{
		}
		g.drawImage(image, Constants.DISPLAY_X - Constants.DISPLAY_NEWLINE, Constants.DISPLAY_Y + Constants.DISPLAY_NEWLINE, null);
	}

	/* Method showLoseMessage: Show lose message. */
	public void showLoseMessage(Graphics g)
	{
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("Click 'Re-build' and then 'Start' to start a new game ...", Constants.DISPLAY_X, 500);
		try
		{
			image = ImageIO.read(ClassLoader.getSystemResource("image/lose.jpg"));
		}
		catch (IOException ex)
		{
		}
		g.drawImage(image, 0, 0, null);
	}
}
