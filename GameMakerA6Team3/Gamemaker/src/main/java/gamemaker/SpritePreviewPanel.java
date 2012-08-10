package gamemaker;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class Name: SpritePreviewPanel Class Responsibilities: Contains the preview
 * display panel of the sprite image Class collaborators: GameMaker.java
 * 
 */
public class SpritePreviewPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6880272574498838023L;

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SpritePreviewPanel.class);

	// The image.
	private Image image;

	private JButton addSpriteButton;
	
	private int defaultHeight;
	private int defaultWidth;
	public static double currentHeightRate;
	public static double currentWidthRate;
	private SpritePreviewPanel spritePreviewPanel = null;

	/*
	 * Constructor SpritePreviewPanel: Instantiates a new sprite preview panel.
	 */
 
	public SpritePreviewPanel()
	{
	 this.addSpriteButton = new JButton(Constants.GAMECREATEPANEL_ADD_SPRITE_BUTTON);
		PropertyConfigurator.configure(getClass().getResource("gamemaker_logger.properties"));
		setBorder(BorderFactory.createTitledBorder(Constants.SPRITEPREVIEWPANEL_TITLE));
		 
		
	}
	public void setDefaultDimension(){
		setDefaultHeight();
		setDefaultWidth();
	}
	public void  setDefaultHeight()
	{
		defaultHeight=this.getHeight();
	}
	public void  setDefaultWidth()
	{
		defaultWidth=this.getWidth();
	}
	public int getDefaultHeight(){
		return defaultHeight;
	}
	public int getDefaultWidth(){
		return defaultWidth;
	}
	
	public double getCurrentHeightRate(){
		currentHeightRate=(double)this.getHeight()/(double)getDefaultHeight();
		
		return currentHeightRate;
		
	}
	 
	public double getCurrentWidthRate(){
		
		
		currentHeightRate=(double)this.getWidth()/(double)getDefaultWidth();
		
		return currentHeightRate;
		
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
	int i =0;
	public void paintComponent(Graphics g)
	{
	  if(i++==0)
		  setDefaultDimension(); 
 	 g.clearRect(0, 0, getWidth(), getHeight());
	 	if(image != null)
	 		g.drawImage(image, (int)(Constants.SPRITEPREVIEWPANEL_PADDING*getCurrentWidthRate()),(int)( Constants.SPRITEPREVIEWPANEL_PADDING*getCurrentHeightRate()),(int)( (double)Constants.SPRITE_DEFAULT_WIDTH*getCurrentWidthRate()), (int)((double)Constants.SPRITE_DEFAULT_HEIGHT*getCurrentHeightRate()), null);
		 	
	  	//System.out.println("SpritePrevieewPanel height" + this.getCurrentWidthRate());
	 	//System.out.println("SpritePrevieewPanel height" + this.getCurrentHeightRate());
	}
	

	/* Method updatePreviewPanel: Update preview panel. */
	public void updatePreviewPanel(String imagePreview)
	{
		this.setImageToPanel(imagePreview);
		this.repaint();
	 
	}

	public void updatePreviewPanel(Image spriteImage)
	{
		this.setImageToPanel(spriteImage);
		this.repaint();
		//System.out.println("Pepaint AINT IMAGE PANEL :  "+ i++);
	}

	private void setImageToPanel(Image spriteImage)
	{
		image = spriteImage;
	}

	public JButton getAddSpriteButton()
	{
		return addSpriteButton;
	}

	public void setAddSpriteButton(JButton addSpriteButton)
	{
		this.addSpriteButton = addSpriteButton;
	}
}
