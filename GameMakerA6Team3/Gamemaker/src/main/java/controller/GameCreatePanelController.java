package controller;

import gamemaker.Constants;
import gamemaker.GameCreatePanel;
import gamemaker.GameMaker;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import controller.gameboardmodel.GameBoardModelController;
import controller.rule.RuleController;
import model.GameBoardModel;

/**
 * Class Name: GameCreatePanelController. Class responsibility: the controller
 * for creating the game maker/ creater panel Class Collaborators:
 */
public class GameCreatePanelController implements ActionListener
{

	private static GameCreatePanelController instance;

	private GameBoardModel gameBoardModel;

	// The game create panel.
	GameCreatePanel gameCreatePanel;

	public static GameCreatePanelController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new GameCreatePanelController();
		return instance;
	}

	/*
	 * Constructor GameCreatePanelController: Instantiates a new game create
	 * panel controller.
	 */
	private GameCreatePanelController()
	{
		//this.gameBoardModel = GameBoardModelController.getInstance().getGameBoardModel();
		this.gameCreatePanel = new GameCreatePanel();
		getGameCreatePanel().getAddEventActionButton().addActionListener(this);
	}

	public GameCreatePanel getGameCreatePanel()
	{
		return gameCreatePanel;
	}

	public void setGameCreatePanel(GameCreatePanel gameCreatePanel)
	{
		this.gameCreatePanel = gameCreatePanel;
	}

	/* Method actionPerformed :Contains the action performed event */
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getActionCommand().equals(Constants.GAMECREATEPANEL_ADD_EVNTACT_BUTTON))
		{
			RuleController.getInstance().addRule();
		}
	}

	/* Method isExistingSprite: Checks if is sprite exists. */
	private boolean isExistingSprite()
	{
		/*
		 * for (int i = 0; i <
		 * GameMaker.spriteList.getInitialObjectList().size(); i++) { if
		 * (GameMaker
		 * .spriteList.getObjectList().get(i).getSpriteName().equalsIgnoreCase
		 * (this.gameCreatePanel.getSpritePanel().getSpriteName())) {
		 * GameMaker.spriteList
		 * .getObjectList().get(i).setSpriteType(this.gameCreatePanel
		 * .getSpritePanel().getSpriteType());
		 * GameMaker.spriteList.getObjectList
		 * ().get(i).setSpriteWidth(this.gameCreatePanel
		 * .getSpritePanel().getSpriteWidth());
		 * GameMaker.spriteList.getObjectList
		 * ().get(i).setSpriteHeight(this.gameCreatePanel
		 * .getSpritePanel().getSpriteHeight());
		 * GameMaker.spriteList.getObjectList
		 * ().get(i).setSpriteX(this.gameCreatePanel
		 * .getSpritePanel().getSpriteX());
		 * GameMaker.spriteList.getObjectList().
		 * get(i).setSpriteY(this.gameCreatePanel
		 * .getSpritePanel().getSpriteY());
		 * GameMaker.spriteList.getObjectList().
		 * get(i).setSpriteXSpeed(this.gameCreatePanel
		 * .getSpritePanel().getSpriteXSpeed());
		 * GameMaker.spriteList.getObjectList
		 * ().get(i).setSpriteYSpeed(this.gameCreatePanel
		 * .getSpritePanel().getSpriteYSpeed());
		 * GameMaker.spriteList.getObjectList
		 * ().get(i).setImage(GameMaker.spriteList
		 * .getObjectList().get(i).generateSpriteImage
		 * (this.gameCreatePanel.getSpritePanel().getSpriteType())); return
		 * true; } }
		 */return false;
	}

	/* Method getSoundFile : Gets the sound file */
	public void getSoundFile()
	{
		final JFrame selectFile = new JFrame();
		selectFile.setSize(Constants.SOUND_JFRAME_WIDTH, Constants.SOUND_JFRAME_HEIGHT);
		selectFile.setLocation(Constants.SOUND_JFRAME_LOCATION, Constants.SOUND_JFRAME_LOCATION);
		JRadioButton first = new JRadioButton("Beep");
		first.setActionCommand("Beep");
		JRadioButton second = new JRadioButton("Boing");
		second.setActionCommand("Boing");
		JRadioButton third = new JRadioButton("Gameover");
		third.setActionCommand("Gameover");
		JRadioButton fourth = new JRadioButton("Chimes");
		fourth.setActionCommand("Chimes");
		JRadioButton fifth = new JRadioButton("Brick_Hit");
		fifth.setActionCommand("Brick_Hit");
		JRadioButton sixth = new JRadioButton("Pop");
		sixth.setActionCommand("Pop");
		final ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(first);
		buttongroup.add(second);
		buttongroup.add(third);
		buttongroup.add(fourth);
		buttongroup.add(fifth);
		buttongroup.add(sixth);

		JButton confirm = new JButton("Confirm");
		selectFile.setLayout(new FlowLayout());
		selectFile.add(first);
		selectFile.add(second);
		selectFile.add(third);
		selectFile.add(fourth);
		selectFile.add(fifth);
		selectFile.add(sixth);
		selectFile.add(confirm);

		selectFile.setVisible(true);
		confirm.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				/*
				 * int selectedSpriteIndex =
				 * gameCreatePanel.getSpriteDisplayPanel().getSelectedSprite();
				 * GameMaker
				 * .spriteList.getObjectList().get(selectedSpriteIndex).
				 * getEventVector
				 * ().add(gameCreatePanel.getEventPanel().getSelectedEvent());
				 * GameMaker
				 * .spriteList.getObjectList().get(selectedSpriteIndex).
				 * getEventActionVector
				 * ().add(gameCreatePanel.getEventPanel().getSelectedEvent() +
				 * " - " +
				 * gameCreatePanel.getActionPanel().getSelectedAction());
				 * gameCreatePanel
				 * .getEventDisplayPanel().addToEventDisplay(GameMaker
				 * .spriteList
				 * .getObjectList().get(selectedSpriteIndex).getSpriteName() +
				 * " - " + gameCreatePanel.getEventPanel().getSelectedEvent() +
				 * " - " +
				 * gameCreatePanel.getActionPanel().getSelectedAction());
				 * GameMaker
				 * .spriteList.getObjectList().get(selectedSpriteIndex).
				 * getSoundFileToEventMap
				 * ().put(gameCreatePanel.getEventPanel().getSelectedEvent(),
				 * buttongroup.getSelection().getActionCommand());
				 * 
				 * GameMaker.spriteList.cloneObjectList();
				 * selectFile.setVisible(false);
				 */GameMaker.logger.logInfo(this.getClass().getName() + " - Sound added.");
			}
		});
	}

	public GameBoardModel getGameBoardModel()
	{
		return this.gameBoardModel;
	}

	public void setGameBoardModel(GameBoardModel gameBoardModel)
	{
		this.gameBoardModel = gameBoardModel;
	}

	public void enableRuleButton()
	{
		gameCreatePanel.enableRuleButton();
	}
}
