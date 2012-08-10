package controller;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.StartPausePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.eventaction.ActionController;
import controller.eventaction.EventActionController;
import controller.eventaction.EventController;

/**
 * Class Name: StartPausePanelController. Class responsibility: Contains the
 * actions for the start pause panel Class Collaborators:
 */
public class StartPausePanelController implements ActionListener
{

	// The start pause panel.
	private StartPausePanel startPausePanel;

	private static StartPausePanelController instance;

	public static StartPausePanelController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new StartPausePanelController();
		return instance;
	}

	// Constructor StartPausePanelController : Instantiates a new start pause
	// panel controller.
	private StartPausePanelController()
	{
		setStartPausePanel(new StartPausePanel());
		getStartPausePanel().getStart().addActionListener(this);
		getStartPausePanel().getPause().addActionListener(this);
		getStartPausePanel().getRebuild().addActionListener(this);

	}

	public void setStartPausePanel(StartPausePanel startPausePanel)
	{
		this.startPausePanel = startPausePanel;
	}

	public StartPausePanel getStartPausePanel()
	{
		return startPausePanel;
	}

	/* Method actionPerformed: */
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getActionCommand().equals(Constants.STARTPAUSEPANEL_START_BUTTON))
		{
			GameBoardController.getInstance().saveIntialPositions();
			GameBoardController.getInstance().setGameVariables();
			GameBoardController.getInstance().getTimer().start();
			this.startPausePanel.getStart().setEnabled(false);
			this.startPausePanel.getPause().setEnabled(true);
			this.startPausePanel.getRebuild().setEnabled(true);

			GameBoardController.getInstance().getGameBoard().getGameBoard().addKeyListener(GameBoardController.getInstance());
			GameBoardController.getInstance().getGameBoard().getGameBoard().removeMouseListener(GameBoardController.getInstance());
			GameBoardController.getInstance().getGameBoard().getGameBoard().removeMouseMotionListener(GameBoardController.getInstance());
			GameCreatePanelController.getInstance().getGameCreatePanel().getAddEventActionButton().setEnabled(false);
			SpriteDisplayPanelController.getInstance().getSpriteDisplayPanel().getSpriteList().setEnabled(false);
			EventActionController.getInstance().getEventDisplayPanel().getEventList().setEnabled(false);
			ActionController.getInstance().getActionPanel().getActionList().setEnabled(false);
			EventController.getInstance().getEventPanel().getEventList().setEnabled(false);
			SpriteDisplayPanelController.getInstance().getSpriteDisplayPanel().getCreateSpriteButton().setEnabled(false);
			SpritePreviewPanelController.getInstance().getSpritePreviewPanelHolder().getAddSpriteButton().setEnabled(false);

			GameBoardController.getInstance().getGameBoard().getGameBoard().requestFocus();
			/*
			 * if
			 * (GameMaker.gameCreatePanel.getSpriteDisplayPanel().isEventListEmpty
			 * ()) { JOptionPane.showMessageDialog(GameMaker.gameCreatePanel.
			 * getGameCreatePanel(),
			 * "Please select sprite and event action pair before hitting on start game."
			 * ); return; } if
			 * (GameMaker.gameCreatePanel.getEventDisplayPanel().
			 * isEventListEmpty()) {
			 * JOptionPane.showMessageDialog(GameMaker.gameCreatePanel
			 * .getGameCreatePanel(),
			 * "Please add the event action pair before hitting on start game."
			 * ); return; }
			 * 
			 * this.startPausePanel.getStart().setEnabled(false);
			 * this.startPausePanel.getPause().setEnabled(true);
			 * this.startPausePanel.getRebuild().setEnabled(true);
			 * GameMaker.gameCreatePanel
			 * .getAddEventActionButton().setEnabled(false);
			 * GameMaker.gameCreatePanel.getAddSpriteButton().setEnabled(false);
			 * GameMaker
			 * .gameCreatePanel.getDeleteSpriteButton().setEnabled(false);
			 * GameMaker
			 * .gameCreatePanel.getDeleteEventActionButton().setEnabled(false);
			 * GameMaker
			 * .gameCreatePanel.getVictoryConditionButton().setEnabled(false);
			 * GameMaker
			 * .gameCreatePanel.getLoadSavePanel().getLoad().setEnabled(false);
			 * GameMaker
			 * .gameCreatePanel.getLoadSavePanel().getSave().setEnabled(false);
			 * GameMaker.timerEvent.setPlayMode(true);
			 * GameBoardController.getInstance
			 * ().getGameBoard().getGameBoard().requestFocus(); if
			 * (GameBoardController.getInstance().getGameBoard().isLosevalue()
			 * || GameBoardController.getInstance().getGameBoard().isWinvalue())
			 * {
			 * GameBoardController.getInstance().getGameBoard().setLosevalue(false
			 * );
			 * GameBoardController.getInstance().getGameBoard().setWinvalue(false
			 * ); GameMaker.spriteList.setToInitialState();
			 * GameMaker.timerEvent.setPlayMode(false);
			 * GameMaker.victoryEvent.setHitTopWall(false);
			 * GameMaker.victoryEvent.setHitBottomWall(false);
			 * GameMaker.victoryEvent.setHitRightWall(false);
			 * GameMaker.victoryEvent.setHitLeftWall(false);
			 * GameBoardController.
			 * getInstance().getGameBoard().getGameBoard().repaint();
			 * GameBoardController
			 * .getInstance().getGameBoard().getGameBoard().requestFocus(); }
			 */GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Game Started.");
		}
		else if (actionEvent.getActionCommand().equals(Constants.STARTPAUSEPANEL_PAUSE_BUTTON))
		{
			this.startPausePanel.getPause().setEnabled(false);
			this.startPausePanel.getStart().setEnabled(true);
			this.startPausePanel.getStart().setText(Constants.STARTPAUSEPANEL_RESUME_BUTTON);
			GameBoardController.getInstance().getTimer().stop();
			// GameMaker.timerEvent.setPlayMode(false);
			GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Game paused.");
		}
		else if (actionEvent.getActionCommand().equals(Constants.STARTPAUSEPANEL_RESUME_BUTTON))
		{
			this.startPausePanel.getPause().setEnabled(true);
			this.startPausePanel.getStart().setEnabled(false);
			this.startPausePanel.getStart().setText(Constants.STARTPAUSEPANEL_START_BUTTON);
			GameBoardController.getInstance().getTimer().start();
			GameBoardController.getInstance().getGameBoard().getGameBoard().requestFocus();
			GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Game resumed.");
		}
		else if (actionEvent.getActionCommand().equals(Constants.STARTPAUSEPANEL_REBUILD_BUTTON))
		{
			GameBoardController.getInstance().getTimer().stop();
			GameBoardController.getInstance().loadIntialPositions();
			this.startPausePanel.getRebuild().setEnabled(false);
			this.startPausePanel.getPause().setEnabled(false);
			this.startPausePanel.getStart().setText(Constants.STARTPAUSEPANEL_START_BUTTON);
			this.startPausePanel.getStart().setEnabled(true);

			GameBoardController.getInstance().resetScore();
			GameBoardController.getInstance().getGameBoard().getGameBoard().removeKeyListener(GameBoardController.getInstance());
			GameBoardController.getInstance().getGameBoard().getGameBoard().addMouseListener(GameBoardController.getInstance());
			GameBoardController.getInstance().getGameBoard().getGameBoard().addMouseMotionListener(GameBoardController.getInstance());

			GameCreatePanelController.getInstance().getGameCreatePanel().getAddEventActionButton().setEnabled(true);
			SpriteDisplayPanelController.getInstance().getSpriteDisplayPanel().getSpriteList().setEnabled(true);
			EventActionController.getInstance().getEventDisplayPanel().getEventList().setEnabled(true);
			ActionController.getInstance().getActionPanel().getActionList().setEnabled(true);
			EventController.getInstance().getEventPanel().getEventList().setEnabled(true);
			SpriteDisplayPanelController.getInstance().getSpriteDisplayPanel().getCreateSpriteButton().setEnabled(true);
			SpritePreviewPanelController.getInstance().getSpritePreviewPanelHolder().getAddSpriteButton().setEnabled(true);

			GameBoardController.getInstance().getGameBoard().getGameBoard().requestFocus();
			/*
			 * GameMaker.gameCreatePanel.getAddEventActionButton().setEnabled(true
			 * );
			 * GameMaker.gameCreatePanel.getAddSpriteButton().setEnabled(true);
			 * GameMaker
			 * .gameCreatePanel.getDeleteSpriteButton().setEnabled(true);
			 * GameMaker
			 * .gameCreatePanel.getDeleteEventActionButton().setEnabled(true);
			 * GameMaker
			 * .gameCreatePanel.getLoadSavePanel().getLoad().setEnabled(true);
			 * GameMaker
			 * .gameCreatePanel.getLoadSavePanel().getSave().setEnabled(true);
			 * GameMaker
			 * .gameCreatePanel.getVictoryConditionButton().setEnabled(true);
			 * GameBoardController
			 * .getInstance().getGameBoard().setLosevalue(false);
			 * GameBoardController
			 * .getInstance().getGameBoard().setWinvalue(false);
			 * GameMaker.spriteList.setToInitialState();
			 * GameMaker.timerEvent.setPlayMode(false); GameMaker.timercount =
			 * 0; GameMaker.gameTime.setText("00:" + "00:" + "00");
			 * GameMaker.victoryEvent.setHitTopWall(false);
			 * GameMaker.victoryEvent.setHitBottomWall(false);
			 * GameMaker.victoryEvent.setHitRightWall(false);
			 * GameMaker.victoryEvent.setHitLeftWall(false);
			 * GameBoardController.
			 * getInstance().getGameBoard().getGameBoard().repaint();
			 * GameBoardController
			 * .getInstance().getGameBoard().getGameBoard().requestFocus();
			 */GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Back to game build mode.");
		}
	}
}
