package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.SpriteDisplayPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Sprite;
import controller.eventaction.EventController;

/**
 * Class Name: SpriteDisplayPanelController. Class responsibility: Contains the
 * sprite display panel controller Class Collaborators:
 */
public class SpriteDisplayPanelController implements ListSelectionListener, ActionListener
{
	// private SpritePanel spritePanel;
	private static SpriteDisplayPanelController instance;
	private SpriteDisplayPanel spriteDisplayPanel;
	private ArrayList<Sprite> spriteList;
	private JList spriteJList;
	private String currentSpriteName;
	private Sprite currentSprite;
	private int currentSpriteIndex;

	// Constructor SpriteDisplayPanelController: Instantiates a new sprite
	// display panel controller.
	private SpriteDisplayPanelController()
	{
		setSpriteDisplayPanel(new SpriteDisplayPanel());
		getSpriteDisplayPanel().getCreateSpriteButton().addActionListener(this);
		getSpriteDisplayPanel().getSpriteList().addListSelectionListener(this);
		getSpriteDisplayPanel().getDeleteSpriteButton().addActionListener(this);

	}

	public void updateSpriteJList()
	{
		ArrayList<Sprite> spriteList;
		spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite oneSprite : spriteList)
		{
			spriteDisplayPanel.addToSpriteDisplay(oneSprite.getName());
		}
	}

	public String getSelectedSpriteName()
	{
		return this.spriteDisplayPanel.getSelectedSpriteName();
	}

	/**
	 * Provides access to singleton SpriteDisplayPanelController
	 * 
	 * @return {@link SpriteDisplayPanelController}
	 */
	public static SpriteDisplayPanelController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new SpriteDisplayPanelController();
		return instance;
	}

	public void setSpriteDisplayPanel(SpriteDisplayPanel spriteDisplayPanel)
	{
		this.spriteDisplayPanel = spriteDisplayPanel;
	}

	public SpriteDisplayPanel getSpriteDisplayPanel()
	{
		return spriteDisplayPanel;
	}

	public ArrayList<String> getSelectedValues()
	{
		return this.spriteDisplayPanel.getSelectedSpriteNames();
	}

	/* Method valueChanged: value changed event */
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		int selectedSpriteNumber = spriteDisplayPanel.getSpriteList().getSelectedIndices().length;
		switch (selectedSpriteNumber)
		{
		case 0:
			GameMaker.logger.logInfo(this.getClass().getName() + " - zero sprites selected in the list");
			EventController.getInstance().removeAllEventsInList();
			EventController.getInstance().populateJList(Constants.ALL_EVENT_NAMES);
			EventController.getInstance().getEventList().setEnabled(true);
			if (!getSpriteDisplayPanel().getSpriteList().isSelectionEmpty())
			{
				String spriteName = getSpriteDisplayPanel().getSpriteList().getSelectedValue().toString();
				GameMaker.logger.logInfo(this.getClass().getName() + "Selected value in the list: " + spriteName);
				Image spriteImage = CreateSpriteController.getInstance().getSprite(spriteName).getImage();
				SpritePreviewPanelController.getInstance().getSpritePreviewPanelHolder().getSpritePreviewPanel().updatePreviewPanel(spriteImage);
			}
			break;

		case 1:
			GameMaker.logger.logInfo(this.getClass().getName() + " - One sprite selected");
			EventController.getInstance().removeAllEventsInList();
			EventController.getInstance().populateJList(Constants.ONE_SPRITE_EVENT_NAMES);
			EventController.getInstance().getEventList().setEnabled(true);
			if (!getSpriteDisplayPanel().getSpriteList().isSelectionEmpty())
			{
				String spriteName = getSpriteDisplayPanel().getSpriteList().getSelectedValue().toString();
				GameMaker.logger.logInfo(this.getClass().getName() + "Selected value in the list: " + spriteName);
				Image spriteImage = CreateSpriteController.getInstance().getSprite(spriteName).getImage();
				SpritePreviewPanelController.getInstance().getSpritePreviewPanelHolder().getSpritePreviewPanel().updatePreviewPanel(spriteImage);
			}
			break;

		case 2:
			GameMaker.logger.logInfo(this.getClass().getName() + " -Two sprites selected in the list");
			EventController.getInstance().removeAllEventsInList();
			EventController.getInstance().populateJList(Constants.TWO_SPRITE_EVENT_NAMES);
			EventController.getInstance().getEventList().setEnabled(true);
			break;

		default:
			GameMaker.logger.logInfo(this.getClass().getName() + " - more than two sprites selected in the list");
			EventController.getInstance().removeAllEventsInList();
			EventController.getInstance().populateJList(Constants.ALL_EVENT_NAMES);
			EventController.getInstance().getEventList().setEnabled(false);
			JOptionPane.showMessageDialog(spriteDisplayPanel, "More than two sprite selection is not allowed", "Invalid Selection", JOptionPane.INFORMATION_MESSAGE);
			break;
		}

		GameMaker.logger.logInfo(this.getClass().getName() + " - value changed in sprite list.");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Constants.CREATE_SPRITE_BUTTON.equals(e.getActionCommand()))
		{
			CreateSpriteController.getInstance().displayCreateSpriteDialog();
		}
		if (Constants.DELETE_SPRITE_BUTTON.equals(e.getActionCommand()))
		{
			spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
			spriteDisplayPanel = GameCreatePanelController.getInstance().getGameCreatePanel().getSpriteDisplayPanel();
			spriteJList = spriteDisplayPanel.getSpriteList();
			currentSpriteName = spriteJList.getSelectedValue().toString();
			currentSpriteIndex = spriteJList.getSelectedIndex();

			for (Sprite oneSprite : spriteList)
			{
				if (currentSpriteName.equalsIgnoreCase(oneSprite.getName()))
				{
					currentSprite = oneSprite;
				}
			}
			GameMaker.logger.logInfo(Integer.toString(currentSpriteIndex));
			GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList().remove(currentSprite);
			// DefaultListModel model = (DefaultListModel)
			// spriteDisplayPanel.getSpriteList().getModel();
			spriteDisplayPanel.removeFromSpriteDisplay(currentSpriteName);
			if (spriteDisplayPanel.getSpriteList().getModel().getSize() > currentSpriteIndex)
			{
				spriteDisplayPanel.getSpriteList().setSelectedIndex(currentSpriteIndex);
				String newSpriteName = spriteDisplayPanel.getSpriteList().getSelectedValue().toString();
				ArrayList<Sprite> tempSpriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
				for (Sprite oneSprite : tempSpriteList)
				{
					if (newSpriteName.equalsIgnoreCase(oneSprite.getName()))
					{
						SpritePreviewPanelController.getInstance().getSpritePreviewPanelHolder().getSpritePreviewPanel().updatePreviewPanel(oneSprite.getImage());
					}
				}

			}
			else
			{
				SpritePreviewPanelController.getInstance().getSpritePreviewPanelHolder().getSpritePreviewPanel().updatePreviewPanel((Image) null);
			}
		}
	}
}
