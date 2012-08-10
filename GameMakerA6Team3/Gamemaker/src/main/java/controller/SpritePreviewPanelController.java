package controller;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.SpriteDisplayPanel;
import gamemaker.SpritePreviewPanelHolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;

import model.Sprite;

/**
 * Class Name: SpritePreviewPanelController. Class responsibility: Contains the
 * controller for the SpritePreviewPanelController Class Collaborators:
 */
public class SpritePreviewPanelController implements ActionListener
{

	// The combo box.
	private SpritePreviewPanelHolder spritePreviewPanelHolder;
	private static SpritePreviewPanelController instance;
	private ArrayList<Sprite> spriteList;
	private JList spriteJList;
	private SpriteDisplayPanel spriteDisplayPanel;
	private String currentSpriteName;
	private Sprite currentSprite;

	// constructor SpritePanelController : Instantiates a new sprite panel
	// controller.
	public SpritePreviewPanelController()
	{
		setSpritePreviewPanelHolder(new SpritePreviewPanelHolder());
		spritePreviewPanelHolder.getAddSpriteButton().addActionListener(this);		

		//	getSpritePanel().getComboBox().addActionListener(this);
	}

	public static SpritePreviewPanelController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new SpritePreviewPanelController();
		return instance;
	}


	public void setSpritePreviewPanelHolder(SpritePreviewPanelHolder spritePreviewPanelHolder)
	{
		this.spritePreviewPanelHolder = spritePreviewPanelHolder;
	}

	public SpritePreviewPanelHolder getSpritePreviewPanelHolder()
	{
		return spritePreviewPanelHolder;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(Constants.GAMECREATEPANEL_ADD_SPRITE_BUTTON))
		{
			//TODO: Validations for sprite
			spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
			spriteDisplayPanel = GameCreatePanelController.getInstance().getGameCreatePanel().getSpriteDisplayPanel();
			spriteJList = spriteDisplayPanel.getSpriteList();
			currentSpriteName = spriteJList.getSelectedValue().toString();
			
			for (Sprite oneSprite : spriteList)
			{
				if(currentSpriteName.equalsIgnoreCase(oneSprite.getName())){
					currentSprite = oneSprite;
				}											
			}
			currentSprite.addGameObject();
			GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();
			GameMaker.logger.logInfo(this.getClass().getName() + "\t" + currentSprite.getName() + currentSprite.getAngle() + currentSprite.getHeight() + currentSprite.getRotationAngle() + currentSprite.getSpeed() + currentSprite.getWidth() + " - Sprite added/updated.");
		}
	}
}
