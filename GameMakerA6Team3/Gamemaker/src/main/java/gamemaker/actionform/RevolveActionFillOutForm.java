package gamemaker.actionform;

import gamemaker.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import controller.actionform.ActionFillOutFormController;

public class RevolveActionFillOutForm  implements ActionFillOutForm
{

	// Current JFrame
	private JFrame revolveActionFillOutFrame;

	// Current JPanel
	private JPanel revolveActionFillOutPanel;

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
	
	
	public RevolveActionFillOutForm(){
		revolveActionFillOutFrame = new JFrame();
		revolveActionFillOutFrame.setVisible(false);
		revolveActionFillOutFrame.setUndecorated(true);
		revolveActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		revolveActionFillOutFrame.setResizable(false);
		revolveActionFillOutFrame.setLocationRelativeTo(null);

		revolveActionFillOutPanel = new JPanel();
		revolveActionFillOutPanel.setLayout(null);
		revolveActionFillOutPanel.setBackground(Color.white);
		revolveActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		revolveActionFillOutFrame.add(revolveActionFillOutPanel);

		titleLabel = new JLabel("Sound Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		revolveActionFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		revolveActionFillOutPanel.add(nextButton);
		revolveActionFillOutPanel.add(cancelButton);

		/*helpLabel = new JLabel();
		helpLabel.setText("<HTML>Choose a sound file <BR>to add sound to the game.</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		revolveActionFillOutPanel.add(helpLabel);*/
		
		infoLabel = new JLabel();
		infoLabel.setText("<HTML>The Revolve actions has been added<br /> to the selected Sprite</HTML>");
		infoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		infoLabel.setBounds(20,150,200,100);
		revolveActionFillOutPanel.add(infoLabel);
		
	}
	@Override
	public void disposeFrame()
	{
		revolveActionFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.revolveActionFillOutPanel;
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
		revolveActionFillOutFrame.setVisible(true);		
	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);
		
	}

}
