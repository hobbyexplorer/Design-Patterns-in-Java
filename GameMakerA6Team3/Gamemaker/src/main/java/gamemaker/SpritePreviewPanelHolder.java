package gamemaker;

import java.awt.GridLayout;

import javax.swing.JButton;

/**
 * Class Name: ActionPanel Class Responsibilities: Contains the panel displaying
 * various actions Class collaborators: GameMaker.java
 * 
 */
public class SpritePreviewPanelHolder
{

	// The add sprite button.
	private JButton addSpriteButton;
	private JButton addSpriteButton0;

	// The preview panel.
	private SpritePreviewPanel spritePreviewPanel;

	// Constructor SpritePanel : Instantiates a new sprite panel.
	public SpritePreviewPanelHolder()
	{
		this.spritePreviewPanel = new SpritePreviewPanel();
		
		this.addSpriteButton = new JButton(Constants.GAMECREATEPANEL_ADD_SPRITE_BUTTON);
		this.addSpriteButton0 = new JButton(Constants.GAMECREATEPANEL_ADD_SPRITE_BUTTON);
		this.addSpriteButton0.setVisible(false);
		//this.addSpriteButton.setBounds(45, 180, 120, 20);
		spritePreviewPanel.add(this.addSpriteButton0);
		spritePreviewPanel.add(this.addSpriteButton);
		this.spritePreviewPanel.setLayout(new GridLayout(2,1));
	}

	/* Method getAddSpriteButton: Gets the adds the sprite button. */
	public JButton getAddSpriteButton()
	{
		return addSpriteButton;
	}

	/* Method getSpritePanel : Gets the sprite panel. */
	public SpritePreviewPanel getSpritePreviewPanel()
	{
		return spritePreviewPanel;
	}
	 
}
