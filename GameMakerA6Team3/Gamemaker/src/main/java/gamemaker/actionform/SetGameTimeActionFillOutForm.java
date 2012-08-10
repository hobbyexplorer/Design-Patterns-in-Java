package gamemaker.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Sprite;

import controller.GameCreatePanelController;
import controller.actionform.ActionFillOutFormController;
import controller.actionform.TwoSpriteMoveAllActionFillOutFormController;
import controller.rule.RuleController;

public class SetGameTimeActionFillOutForm implements ActionFillOutForm
{
	// Current JFrame
	private JFrame setGameTimeActionFillOutFormFrame;

	// Current JPanel
	private JPanel setGameTimeActionFillOutFormPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;
	
	private JTextArea messageBox;
	
	private JLabel messageLabel;
	
	private JTextField gameTimeField;
	
	private JLabel gameTimeLabel;

	/**
	 * BounceActionFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public SetGameTimeActionFillOutForm()
	{
		setGameTimeActionFillOutFormFrame = new JFrame();
		setGameTimeActionFillOutFormFrame.setVisible(false);
		setGameTimeActionFillOutFormFrame.setUndecorated(true);
		setGameTimeActionFillOutFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		setGameTimeActionFillOutFormFrame.setResizable(false);
		setGameTimeActionFillOutFormFrame.setLocationRelativeTo(null);

		setGameTimeActionFillOutFormPanel = new JPanel();
		setGameTimeActionFillOutFormPanel.setLayout(null);
		setGameTimeActionFillOutFormPanel.setBackground(Color.white);
		setGameTimeActionFillOutFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setGameTimeActionFillOutFormFrame.add(setGameTimeActionFillOutFormPanel);

		titleLabel = new JLabel("Provide game time and game over message:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		setGameTimeActionFillOutFormPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);

		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);
		
		setGameTimeActionFillOutFormPanel.add(nextButton);
		setGameTimeActionFillOutFormPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Kindly provide time in seconds.</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		setGameTimeActionFillOutFormPanel.add(helpLabel);

		gameTimeLabel = new JLabel("Time: ");
		gameTimeLabel.setBounds(140, 100, 40, 30);
		setGameTimeActionFillOutFormPanel.add(gameTimeLabel);
		
		gameTimeField = new JTextField("60");
		gameTimeField.setBounds(180, 100, 50, 25);
		setGameTimeActionFillOutFormPanel.add(gameTimeField);
		
		messageLabel = new JLabel("Message: ");
		messageLabel.setBounds(150, 160, 100, 30);
		setGameTimeActionFillOutFormPanel.add(messageLabel);
		
		messageBox = new JTextArea();
		messageBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		messageBox.setBounds(40, 210, 320, 80 );
		setGameTimeActionFillOutFormPanel.add(messageBox);
		
	}
	
	public String getGameTime()
	{
		return gameTimeField.getText();
	}

	public String getGameMessage()
	{
		return messageBox.getText();
	}

	
	public void addMouseListenerToRadio(TwoSpriteMoveAllActionFillOutFormController twoSpriteMoveAllActionFillOutFormController)
	{
		
	}

	public void setVisible(boolean visibility)
	{
		setGameTimeActionFillOutFormFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.setGameTimeActionFillOutFormFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.setGameTimeActionFillOutFormPanel;
	}

	/**
	 * Adds action listeners to the cancel button and next button.
	 */
	@Override
	public void AddActionListenerToButtons(ActionFillOutFormController actionFullOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) actionFullOutFormController);
		this.nextButton.addActionListener((ActionListener) actionFullOutFormController);
	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);
		
	}

}
