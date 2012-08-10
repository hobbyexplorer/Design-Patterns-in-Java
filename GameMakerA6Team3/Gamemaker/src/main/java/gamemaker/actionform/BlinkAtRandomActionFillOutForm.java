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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.actionform.ActionFillOutFormController;

public class BlinkAtRandomActionFillOutForm implements ActionFillOutForm, ChangeListener
{
	// Current JFrame
	private JFrame blinkAtRandomActionFillOutFrame;

	// Current JPanel
	private JPanel blinkAtRandomActionFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;

	private JTextField blinkDelayTextField;

	private JTextField blinkForTextField;

	private JLabel blinkDelayTextLabel;

	private JLabel blinkForTextLabel;

	/**
	 * KeyPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public BlinkAtRandomActionFillOutForm()
	{
		blinkAtRandomActionFillOutFrame = new JFrame();
		blinkAtRandomActionFillOutFrame.setVisible(false);
		blinkAtRandomActionFillOutFrame.setUndecorated(true);
		blinkAtRandomActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		blinkAtRandomActionFillOutFrame.setResizable(false);
		blinkAtRandomActionFillOutFrame.setLocationRelativeTo(null);

		blinkAtRandomActionFillOutPanel = new JPanel();
		blinkAtRandomActionFillOutPanel.setLayout(null);
		blinkAtRandomActionFillOutPanel.setBackground(Color.white);
		blinkAtRandomActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		blinkAtRandomActionFillOutFrame.add(blinkAtRandomActionFillOutPanel);

		titleLabel = new JLabel("Blink at Random Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		blinkAtRandomActionFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		blinkAtRandomActionFillOutPanel.add(nextButton);
		blinkAtRandomActionFillOutPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Insert the blink delay and length in milliseconds.</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		blinkAtRandomActionFillOutPanel.add(helpLabel);

		blinkDelayTextLabel = new JLabel("Delay: ");
		blinkDelayTextLabel.setBounds(130, 100, 50, 30);
		blinkAtRandomActionFillOutPanel.add(blinkDelayTextLabel);

		blinkDelayTextField = new JTextField("0");
		blinkDelayTextField.setBounds(180, 100, 50, 25);
		blinkAtRandomActionFillOutPanel.add(blinkDelayTextField);

		blinkForTextLabel = new JLabel("Length: ");
		blinkForTextLabel.setBounds(130, 170, 50, 30);
		blinkAtRandomActionFillOutPanel.add(blinkForTextLabel);

		blinkForTextField = new JTextField("0");
		blinkForTextField.setBounds(180, 170, 50, 25);
		blinkAtRandomActionFillOutPanel.add(blinkForTextField);
	}

	public String getBlinkDelayText()
	{
		return blinkDelayTextField.getText();
	}

	public String getBlinkForText()
	{
		return blinkForTextField.getText();
	}

	public void setVisible(boolean visibility)
	{
		blinkAtRandomActionFillOutFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.blinkAtRandomActionFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.blinkAtRandomActionFillOutPanel;
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

	@Override
	public void stateChanged(ChangeEvent e)
	{

	}

}
