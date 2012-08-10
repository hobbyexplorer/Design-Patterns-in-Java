package controller;

import gamemaker.BackGroundPreviewPanel;
import gamemaker.BackGroundSelectingPanel;
import gamemaker.Constants;
import gamemaker.GameMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 * Class Name: BackGroundSelectionController. Class responsibility: Contains the
 * actions for the background Selection Class Collaborators:
 */
public class BackGroundSelectionController implements ActionListener
{
	private static BackGroundSelectionController instance;

	// The back ground preview panel.
	private BackGroundPreviewPanel bgPreviewPanel;

	// The button group.
	private ButtonGroup buttongroup;

	// The select back ground file.
	private JFrame selectBackGroundFile;

	private BackGroundSelectingPanel backGroundSelectingPanel;

	public static BackGroundSelectionController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new BackGroundSelectionController();
		return instance;
	}

	// Un parameterized Constructor BackGroundSelectionController :Instantiates
	// a new back ground selection controller.
	public BackGroundSelectionController()
	{
		backGroundSelectingPanel = new BackGroundSelectingPanel();
		getBackGroundSelectingPanel().getChooseBackGround().addActionListener(this);
	}

	/*
	 * Method actionPerformed : Contains the logic for the action performed on
	 * button click
	 */
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getActionCommand().equals(Constants.BACKGROUND_SELECTION_JFRAME_CONFIRM_BUTTON)&&buttongroup.getSelection()!=null)
		{
			GameBoardController.getInstance().getGameBoard().setImageToBackground(buttongroup.getSelection().getActionCommand());
			GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();
			GameBoardController.getInstance().setImageToBackground(buttongroup.getSelection().getActionCommand());
			selectBackGroundFile.setVisible(false);
			GameMaker.logger.logInfo(this.getClass().getName() + " - Game Back ground selected");
		}
		else if(buttongroup.getSelection()!=null)
		{
			String imagePreview = (String) buttongroup.getSelection().getActionCommand();
			bgPreviewPanel.setBackgroundToPanel(imagePreview);
			bgPreviewPanel.repaint();
		}
	}

	public void setBackGroundSelectingPanel(BackGroundSelectingPanel backGroundSelectingPanel)
	{
		this.backGroundSelectingPanel = backGroundSelectingPanel;
	}

	public BackGroundSelectingPanel getBackGroundSelectingPanel()
	{
		return backGroundSelectingPanel;
	}

	/* Method getBackGroundFile: Gets the back ground file. */
	public void getBackGroundFile()
	{
		this.selectBackGroundFile = new JFrame();
		this.selectBackGroundFile.setUndecorated(true);
		this.selectBackGroundFile.setBackground(Color.white);
		this.bgPreviewPanel = new BackGroundPreviewPanel();
		this.bgPreviewPanel.setBackground(Color.white);
		this.buttongroup = new ButtonGroup();
		selectBackGroundFile.setSize(Constants.BACKGROUND_PREVIEW_JFRAME_WIDTH, Constants.BACKGROUND_PREVIEW_JFRAME_HEIGHT);
		selectBackGroundFile.setLocation(Constants.BACKGROUND_PREVIEW_JFRAME_LOCATION, Constants.BACKGROUND_PREVIEW_JFRAME_LOCATION);
		this.bgPreviewPanel.setPreferredSize(new Dimension(Constants.BACKGROUND_PREVIEW_PANEL_WIDTH, Constants.BACKGROUND_PREVIEW_PANEL_HEIGHT));
		JRadioButton first = new JRadioButton("red");
		first.setActionCommand("red");
		JRadioButton second = new JRadioButton("black");
		second.setActionCommand("black");
		JRadioButton third = new JRadioButton("green");
		third.setActionCommand("green");
		JRadioButton forth = new JRadioButton("hellokitty");
		forth.setActionCommand("hellokitty");
		JRadioButton fifth = new JRadioButton("warcraft");
		fifth.setActionCommand("warcraft");
		JRadioButton sixth = new JRadioButton("starcraft");
		sixth.setActionCommand("starcraft");
		buttongroup.add(first);
		buttongroup.add(second);
		buttongroup.add(third);
		buttongroup.add(forth);
		buttongroup.add(fifth);
		buttongroup.add(sixth);

		JButton confirm = new JButton(Constants.BACKGROUND_SELECTION_JFRAME_CONFIRM_BUTTON);
		selectBackGroundFile.setLayout(new FlowLayout());
		selectBackGroundFile.add(first);
		selectBackGroundFile.add(second);
		selectBackGroundFile.add(third);
		selectBackGroundFile.add(forth);
		selectBackGroundFile.add(fifth);
		selectBackGroundFile.add(sixth);
		selectBackGroundFile.add(bgPreviewPanel);
		selectBackGroundFile.add(confirm);
		selectBackGroundFile.setVisible(true);

		first.addActionListener(this);
		second.addActionListener(this);
		third.addActionListener(this);
		forth.addActionListener(this);
		fifth.addActionListener(this);
		sixth.addActionListener(this);
		confirm.addActionListener(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Game Back ground selection frame opened");
	}
}
