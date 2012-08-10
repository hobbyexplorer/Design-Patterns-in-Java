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
import javax.swing.border.EtchedBorder;

import controller.actionform.ActionFillOutFormController;

public class MoveRandomActionFillOutForm   implements ActionFillOutForm
{
	// Current JFrame
	private JFrame moveRandomActionFillOutFrame;

	// Current JPanel
	private JPanel moveRandomActionFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	//private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;
	
	// Information label
	private JLabel infoLabel;

	public MoveRandomActionFillOutForm()
	{
		moveRandomActionFillOutFrame = new JFrame();
		moveRandomActionFillOutFrame.setVisible(false);
		moveRandomActionFillOutFrame.setUndecorated(true);
		moveRandomActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		moveRandomActionFillOutFrame.setResizable(false);
		moveRandomActionFillOutFrame.setLocationRelativeTo(null);

		moveRandomActionFillOutPanel = new JPanel();
		moveRandomActionFillOutPanel.setLayout(null);
		moveRandomActionFillOutPanel.setBackground(Color.white);
		moveRandomActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		moveRandomActionFillOutFrame.add(moveRandomActionFillOutPanel);

		titleLabel = new JLabel("Sound Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		moveRandomActionFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		moveRandomActionFillOutPanel.add(nextButton);
		moveRandomActionFillOutPanel.add(cancelButton);

		/*helpLabel = new JLabel();
		helpLabel.setText("<HTML>Choose a sound file <BR>to add sound to the game.</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		moveRandomActionFillOutPanel.add(helpLabel);*/
		
		infoLabel = new JLabel();
		infoLabel.setText("<HTML>The Move Random action has been added<br /> to the selected Sprite</HTML>");
		infoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		infoLabel.setBounds(20,150,200,100);
		moveRandomActionFillOutPanel.add(infoLabel);
		
	}
	@Override
	public void disposeFrame()
	{
		moveRandomActionFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.moveRandomActionFillOutPanel;
	}

	@Override
	public void AddActionListenerToButtons(ActionFillOutFormController actionFillOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) actionFillOutFormController);
		this.nextButton.addActionListener((ActionListener) actionFillOutFormController);
	}

	@Override
	public void setVisible(boolean visibility)
	{
		moveRandomActionFillOutFrame.setVisible(true);
	}

	@Override
	public void makeThisLastForm()
	{
		nextButton.setText(Constants.SUBMIT_BUTTON);
	}

}
