package gamemaker;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Class name: BackGroundPreviewPanel. Class Responsibilities: Has the contents
 * of the background preview panel Class Collaborators: GameMaker.java
 */
public class BackGroundPreviewPanel extends JPanel
{

	// The Constant serialVersionUID.
	private static final long serialVersionUID = 1L;

	// The image.
	private Image image;

	// Constructor BackgroundPreviewPanel: Instantiates a new back ground
	// preview panel.
	public BackGroundPreviewPanel()
	{

		setBorder(BorderFactory.createTitledBorder(Constants.BACKGROUND_PREVIEW_PANEL_TITLE));
	}

	/*
	 * Method setBackgroundToPanel: Sets the background to panel.
	 */
	public void setBackgroundToPanel(String imageName)
	{
		try
		{
			image = ImageIO.read(ClassLoader.getSystemResource("image/" + imageName.toLowerCase() + ".jpg"));
			ImageIcon PreviewIcon = new ImageIcon(image);

			if (PreviewIcon != null)
			{
				if (PreviewIcon.getIconWidth() > Constants.BACKGROUND_PREVIEW_SCALE_WIDTH)
				{
					image = new ImageIcon(PreviewIcon.getImage().getScaledInstance(Constants.BACKGROUND_PREVIEW_SCALE_WIDTH, -1, image.SCALE_DEFAULT)).getImage();
				}
				else
				{
					image = PreviewIcon.getImage();
				}
			}

		}
		catch (IOException ex)
		{
		}
	}

	/* Method paint : paints the contents on the preview panel */
	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 5, 15, null);
	}

	/**
	 * @return the image
	 */
	public Image getImage()
	{
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image)
	{
		this.image = image;
	}

}
