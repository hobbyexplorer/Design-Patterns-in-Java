package gamemaker;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Sprite;

import org.apache.log4j.PropertyConfigurator;

/**
 * Class Name: SpritePreviewPanel Class Responsibilities: Contains the preview
 * display panel of the sprite image Class collaborators: GameMaker.java
 * 
 */
public class CreateSpritePreviewPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6880272574498838023L;

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CreateSpritePreviewPanel.class);

	// The image.
	private Image image;
	
	private int height, width;
	
	private int xPosition, yPosition;

	/*
	 * Constructor SpritePreviewPanel
	 */
	public CreateSpritePreviewPanel()
	{
		PropertyConfigurator.configure(getClass().getResource("log4j/gamemaker_logger.properties"));
		setBorder(BorderFactory.createTitledBorder(Constants.SPRITEPREVIEWPANEL_TITLE));

	}

	/* Method setImageToPanel: Sets the image to panel. */
	public void setImageToPanel(String imageName)
	{
		try
		{
			//String imageAbsolutePath = System.getProperty("user.dir") + "\\src\\image\\" + imageName.toLowerCase() + ".gif";
			URL imageAbsolutePath = getClass().getClassLoader().getResource("image/" + imageName + ".gif");
			LOG.info("Image absolute path: " + imageAbsolutePath);
			image = ImageIO.read(imageAbsolutePath);
		}
		catch (IOException ex)
		{
		}
	}

	/*
	 * Method paintComponent: responsible for painting the various sprites on
	 * the preview panel
	 */
	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, xPosition, yPosition, height, width, null);
	}
	

	/* Method updatePreviewPanel: Update preview panel. */
	public void updatePreviewPanel(String imagePreview, int xPosition, int yPosition, int height, int width)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.height = height;
		this.width = width;
		this.setImageToPanel(imagePreview);
		this.repaint();
	}

}
