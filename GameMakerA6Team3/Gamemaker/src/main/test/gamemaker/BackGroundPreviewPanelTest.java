package gamemaker;

import static org.junit.Assert.*;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BackGroundPreviewPanelTest
{

	BackGroundPreviewPanel backGroundPreview = new BackGroundPreviewPanel();
	Image imageOriginal;
	Image image;
	ImageIcon imageIcon;
	String imageName = "black";

	@Before
	public void setUp() throws Exception
	{

		imageOriginal = ImageIO.read(getClass().getClassLoader().getResource("image/black.jpg"));
		backGroundPreview.setBackgroundToPanel(imageName);
		image = backGroundPreview.getImage();
	}

	@Test
	public void TestForBackGroundPreview()
	{

		assertNotSame(image.getHeight(backGroundPreview), imageOriginal.getHeight(backGroundPreview));
		assertNotSame(image.getWidth(backGroundPreview), imageOriginal.getWidth(backGroundPreview));

	}

	@After
	public void tearDown() throws Exception
	{
	}

}
