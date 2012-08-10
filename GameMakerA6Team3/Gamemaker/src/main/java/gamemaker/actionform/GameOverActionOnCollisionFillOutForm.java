package gamemaker.actionform;

import gamemaker.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import controller.actionform.ActionFillOutFormController;
import controller.actionform.TwoSpriteMoveAllActionFillOutFormController;

public class GameOverActionOnCollisionFillOutForm implements ActionFillOutForm
{
	// Current JFrame
	private JFrame gameOverActionFillOutFormFrame;

	// Current JPanel
	private JPanel gameOverActionFillOutFormPanel;

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
	

	/**
	 * BounceActionFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public GameOverActionOnCollisionFillOutForm()
	{
		gameOverActionFillOutFormFrame = new JFrame();
		gameOverActionFillOutFormFrame.setVisible(false);
		gameOverActionFillOutFormFrame.setUndecorated(true);
		gameOverActionFillOutFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		gameOverActionFillOutFormFrame.setResizable(false);
		gameOverActionFillOutFormFrame.setLocationRelativeTo(null);

		gameOverActionFillOutFormPanel = new JPanel();
		gameOverActionFillOutFormPanel.setLayout(null);
		gameOverActionFillOutFormPanel.setBackground(Color.white);
		gameOverActionFillOutFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		gameOverActionFillOutFormFrame.add(gameOverActionFillOutFormPanel);

		titleLabel = new JLabel("Provide game over message:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		gameOverActionFillOutFormPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);

		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);
		
		gameOverActionFillOutFormPanel.add(nextButton);
		gameOverActionFillOutFormPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML></HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		gameOverActionFillOutFormPanel.add(helpLabel);

		messageLabel = new JLabel("Message: ");
		messageLabel.setBounds(150, 100, 100, 30);
		gameOverActionFillOutFormPanel.add(messageLabel);
		
		messageBox = new JTextArea();
		messageBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		messageBox.setBounds(40, 150, 320, 80 );
		gameOverActionFillOutFormPanel.add(messageBox);
		
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
		gameOverActionFillOutFormFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.gameOverActionFillOutFormFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.gameOverActionFillOutFormPanel;
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
