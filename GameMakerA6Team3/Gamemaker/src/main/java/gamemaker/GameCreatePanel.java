package gamemaker;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import controller.LoadSavePanelController;
import controller.SaveGame;
import controller.SpriteDisplayPanelController;
import controller.SpritePreviewPanelController;
import controller.StartPausePanelController;
import controller.VictoryCriteriaFrameController;
import controller.eventaction.ActionController;
import controller.eventaction.EventActionController;
import controller.eventaction.EventController;

/**
 * Class Name: GameCreatePanel Class Responsibilities: Contains the of the game
 * creation panel Class collaborators: GameMaker.java
 */
public class GameCreatePanel
{
	// The game create panel.
	private JPanel gameCreatePanel;

	// The add event action button.
	private JButton addEventActionButton;

	// The event panel.
	private EventPanel eventPanel;

	// The action panel.
	private ActionPanel actionPanel;

	// The sprite panel.
	private SpritePreviewPanelHolder spritePreviewPanelHolder;

	// The load save panel.
	private LoadSavePanel loadSavePanel;

	// The start pause panel.
	//private StartPausePanel startPausePanel;

	// The sound file name.
	private String soundFileName;

	// The sprite display panel.
	private SpriteDisplayPanel spriteDisplayPanel;

	// The event display panel.
	private EventDisplayPanel eventDisplayPanel;

	// The victory frame.
	private VictoryCriteriaFrame victoryFrame;

	private JButton addRuleButton;

	private JPanel eventAction;
	
	// Constructor GameCreatePanel: Instantiates a new game create panel.
	public GameCreatePanel()
	{

		this.gameCreatePanel = new JPanel();
		this.gameCreatePanel.setBackground(Color.white);
		this.spritePreviewPanelHolder = SpritePreviewPanelController.getInstance().getSpritePreviewPanelHolder();
		this.gameCreatePanel.setSize(Constants.GAMECREATE_PANEL_WIDTH, Constants.GAMECREATE_PANEL_HEIGHT);

		this.eventPanel = EventController.getInstance().getEventPanel();

		this.actionPanel = ActionController.getInstance().getActionPanel();

		this.spriteDisplayPanel = SpriteDisplayPanelController.getInstance().getSpriteDisplayPanel();
		this.eventDisplayPanel = EventActionController.getInstance().getEventDisplayPanel();
		this.loadSavePanel = LoadSavePanelController.getInstance().getLoadSavePanel();
		//this.startPausePanel = StartPausePanelController.getInstance().getStartPausePanel();
		this.victoryFrame = VictoryCriteriaFrameController.getInstance().getVictoryCriteriaFrame();

		this.gameCreatePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.gameCreatePanel.setSize(Constants.GAMECREATE_PANEL_WIDTH, Constants.GAMECREATE_PANEL_HEIGHT);

		this.gameCreatePanel.setLayout(null);
		this.gameCreatePanel.setBounds(500, 600, 700, 800);
		
		//this.spritePreviewPanelHolder.getSpritePreviewPanel().setBounds(250, 10, 210, 210);
	

		this.eventPanel.getEventPanel().setBounds(10, 10, 200, 100);
		this.eventPanel.getEventPanel().setVisible(true);
		
		this.actionPanel.getActionPanel().setBounds(220, 10, 200, 100);
		this.actionPanel.getActionPanel().setVisible(true);

		eventAction = new JPanel(null);
		eventAction.setBackground(Color.white);
		eventAction.setBounds(5, 220, 580, 120);
		eventAction.add(this.eventPanel.getEventPanel());
		eventAction.add(this.actionPanel.getActionPanel());
		

		

		//this.spriteDisplayPanel.getSpriteDisplayPanel().setBounds(10, 10, 230, 210);
		JPanel spriteHolder = new JPanel();
		//JPanel spritePreviewHolder = new JPanel();
		//spritePreviewHolder.add(spritePreviewPanelHolder.getSpritePreviewPanel());
		//spritePreviewHolder.add(spritePreviewPanelHolder.getSpritePreviewPanel());
		//spritePreviewHolder.setLayout(new GridLayout(2,1));
		
		spriteHolder.add(this.spriteDisplayPanel.getSpriteDisplayPanel());
		spriteHolder.add(spritePreviewPanelHolder.getSpritePreviewPanel());
		spriteHolder.setBounds(10, 10, 460, 210);
		spriteHolder.setLayout(new GridLayout(1,2));
		//this.gameCreatePanel.add(spritePreviewPanelHolder.getSpritePreviewPanel());
		//this.gameCreatePanel.add(this.spriteDisplayPanel.getSpriteDisplayPanel());
		
		this.gameCreatePanel.add(spriteHolder);
		
		addEventActionButton = new JButton(Constants.GAMECREATEPANEL_ADD_EVNTACT_BUTTON);
		this.addEventActionButton.setBounds(180, 340, 100, 20);
		eventAction.add(this.addEventActionButton);
		eventAction.setLayout(new GridLayout(0,3));
		//JPanel holdeEventActionPanel = new JPanel();
		this.gameCreatePanel.add(eventAction);
		//gameCreatePanel.add(this.addEventActionButton);
		// holdeEventActionPanel.add(eventAction);
		// holdeEventActionPanel.add(this.addEventActionButton);
		 
		
		
		
		
		this.eventDisplayPanel.getEventDisplayPanel().setBounds(50, 380, 400, 140);
	
		

		disableOnStart();
		this.loadSavePanel.getLoadSavePanel().setBounds(70, 520, 135, 45);
		JPanel holdeLoadSaveStartPausePanel = new JPanel();
		 holdeLoadSaveStartPausePanel.setBounds(70,520,400,90);
		 holdeLoadSaveStartPausePanel.add(this.loadSavePanel.getLoadSavePanel());
		 //holdeLoadSaveStartPausePanel.add(this.startPausePanel.getStartPausePanel());
		 holdeLoadSaveStartPausePanel.setLayout(new GridLayout(0,2));
		 this.gameCreatePanel.add( holdeLoadSaveStartPausePanel);
		 this.eventDisplayPanel.getEventDisplayPanel().add( holdeLoadSaveStartPausePanel);
		 this.eventDisplayPanel.getEventDisplayPanel().setLayout(new GridLayout(2,1));
		 this.gameCreatePanel.add(this.eventDisplayPanel.getEventDisplayPanel());
		//this.gameCreatePanel.add(this.loadSavePanel.getLoadSavePanel());
		//this.startPausePanel.getStartPausePanel().setBounds(195, 520, 250, 45);
		//this.gameCreatePanel.add(this.startPausePanel.getStartPausePanel());
		addActionListenersToButtons();
		gameCreatePanel.setLayout(new GridLayout(3,1));
	}

	/* Method addActionListenersToButtons: Adds the action listeners to buttons. */
	public void addActionListenersToButtons()
	{
		/*
		 * this.addSpriteButton.addActionListener(this.gcPanelController);
		 * this.addEventActionButton.addActionListener(this.gcPanelController);
		 * this.deleteSpriteButton.addActionListener(this.gcPanelController);
		 * this
		 * .deleteEventActionButton.addActionListener(this.gcPanelController);
		 * this
		 * .victoryConditionButton.addActionListener(this.gcPanelController);
		 */}

	/* Method disableOnStart: Disable on start. */
	public void disableOnStart()
	{
		//this.startPausePanel.getPause().setEnabled(false);
		//this.startPausePanel.getRebuild().setEnabled(false);
		this.addEventActionButton.setEnabled(false);
	}
	
	/*
	 * Method reloadDisplayList: Reload display list.
	 */
	public void reloadDisplayList()
	{

		/*
		 * this.spriteDisplayPanel.emptySpriteDisplay();
		 * this.eventDisplayPanel.emptyEventDisplay(); for (int i = 0; i <
		 * GameMaker.spriteList.getObjectList().size(); i++) { SpriteObject
		 * sprite = GameMaker.spriteList.getObjectList().get(i);
		 * this.spriteDisplayPanel.addToSpriteDisplay(sprite.getSpriteName());
		 * for (int j = 0; j < sprite.getEventActionVector().size(); j++) {
		 * this.eventDisplayPanel.addToEventDisplay(sprite.getSpriteName() +
		 * " - " + sprite.getEventActionVector().get(j)); } }
		 * this.victoryFrame.loadVictoryConditions();
		 */GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Reloaded display list.");
	}

	/* Method displayEventActionPanel: Display event action panel. */
	public void displayEventActionPanel(boolean show)
	{
		this.eventPanel.getEventPanel().setVisible(show);
		this.actionPanel.getActionPanel().setVisible(show);
	}

	/* Method saveGame: Save game. */

/*	public void saveGame(File file)
	{

		SaveGame saveGame = new SaveGame(file);
		saveGame.saveFile();
	}
*/
	/* Method getEventDisplayPanel : Gets the event display panel. */
	public EventDisplayPanel getEventDisplayPanel()
	{
		return eventDisplayPanel;
	}

	/* Method getEventPanel: Gets the event panel. */
	public EventPanel getEventPanel()
	{
		return eventPanel;
	}

	/* Method setEventPanel: Sets the event panel. */
	public void setEventPanel(EventPanel eventPanel)
	{
		this.eventPanel = eventPanel;
	}

	/* Method setEventDisplayPanel: Sets the event display panel */
	public void setEventDisplayPanel(EventDisplayPanel eventDisplayPanel)
	{
		this.eventDisplayPanel = eventDisplayPanel;
	}

	/*
	 * Method performValidationAndRetrieveValuesForSprite: Perform validation
	 * and retrieve values for sprite.
	 */
	public void performValidationAndRetrieveValuesForSprite()
	{

	}

	/* Method getGameCreatePanel: Gets the game create panel. */
	public JPanel getGameCreatePanel()
	{
		return gameCreatePanel;
	}

	/* Method getSpriteDisplayPanel: Gets the sprite display panel. */
	public SpriteDisplayPanel getSpriteDisplayPanel()
	{
		return spriteDisplayPanel;
	}

	/* Method setGameCreatePanel: Sets the game create panel. */
	public void setGameCreatePanel(JPanel gameCreatePanel)
	{
		this.gameCreatePanel = gameCreatePanel;
	}

	/* Method getSoundFileName: Gets the sound file name. */
	public String getSoundFileName()
	{
		return soundFileName;
	}

	/* Method getAddEventActionButton: Gets the adds the event action button. */
	public JButton getAddEventActionButton()
	{
		return addEventActionButton;
	}
	
	/* Method getLoadSavePanel: Gets the load save panel. */
	public LoadSavePanel getLoadSavePanel()
	{
		return loadSavePanel;
	}

	/* Method setSoundFileName : Sets the sound file name. */
	public void setSoundFileName(String soundFileName)
	{
		this.soundFileName = soundFileName;
	}

	/* Method getActionPanel: Gets the action panel. */
	public ActionPanel getActionPanel()
	{
		return actionPanel;
	}

	/* Method getSpritePanel: Gets the sprite panel. */
	public SpritePreviewPanelHolder getSpritePanel()
	{
		return spritePreviewPanelHolder;
	}

	/* Method setSpritePanel: Sets the sprite panel. */
	public void setSpritePanel(SpritePreviewPanelHolder spritePanel)
	{
		this.spritePreviewPanelHolder = spritePanel;
	}

	/* Method getVictoryFrame: Gets the victory frame. */
	public VictoryCriteriaFrame getVictoryFrame()
	{
		return victoryFrame;
	}

	/* Method setVictoryFrame: Sets the victory frame. */
	public void setVictoryFrame(VictoryCriteriaFrame victoryFrame)
	{
		this.victoryFrame = victoryFrame;
	}

	/*
	 * Method getStartPausePanel: Returns the startPausePanel
	 */
	/*public StartPausePanel getStartPausePanel()
	{
		return startPausePanel;
	}*/
	
	public void enableRuleButton()
	{
		addEventActionButton.setEnabled(true);
	}
	
	public JButton getEventActionButton()
	{
		return addEventActionButton;
	}
}
