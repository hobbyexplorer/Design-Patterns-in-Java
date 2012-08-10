package gamemaker;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class name: BackGroundSelectingPanel.java Class Responsibilities: Contains
 * the panel for background selection Class Collaborators: GameMaker.java
 */
public class BackGroundSelectingPanel
{

	// The choose back ground.
	private JButton chooseBackGround;

	// The back ground select panel.
	private JPanel backGroundSelectPanel;

	// The image selected.
	private BufferedImage imageSelected;

	// Constructor BackGroundSelectingPanel : Instantiates a new back ground
	// selecting panel.

	public BackGroundSelectingPanel()
	{
		this.backGroundSelectPanel = new JPanel();
		this.backGroundSelectPanel.setBackground(Color.white);
		this.chooseBackGround = new JButton("Choose BackGround");
		this.backGroundSelectPanel.add(chooseBackGround);
	//	this.chooseBackGround.addActionListener(this.bgSelectController);
	}

	/* Method getImageSelected: Gets the image selected. */
	public BufferedImage getImageSelected()
	{
		return imageSelected;
	}

	/* Method setImageSelected: Sets the image selected. */
	public void setImageSelected(BufferedImage imageSelected)
	{
		this.imageSelected = imageSelected;
	}

	/* Method getBackGroundSelectPanel: Gets the back ground select panel. */
	public JPanel getBackGroundSelectPanel()
	{
		return backGroundSelectPanel;
	}

	/* Method setBackGroundSelectPanel: Sets the back ground select panel. */
	public void setBackGroundSelectPanel(JPanel backGroundSelectPanel)
	{
		this.backGroundSelectPanel = backGroundSelectPanel;
	}

	public JButton getChooseBackGround()
	{
		return chooseBackGround;
	}

	public void setChooseBackGround(JButton chooseBackGround)
	{
		this.chooseBackGround = chooseBackGround;
	}

}
