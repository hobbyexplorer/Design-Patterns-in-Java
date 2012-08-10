package gamemaker.eventform;

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
import controller.eventform.EventFillOutFormController;
import controller.rule.RuleController;

public class CountEventFillOutForm implements EventFillOutForm
{
	// Current JFrame
	private JFrame countEventFillOutFrame;

	// Current JPanel
	private JPanel countEventFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;

	/**
	 * CountPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public CountEventFillOutForm()
	{
		countEventFillOutFrame = new JFrame();
		countEventFillOutFrame.setUndecorated(true);
		countEventFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		countEventFillOutFrame.setResizable(false);
		countEventFillOutFrame.setLocationRelativeTo(null);
		countEventFillOutFrame.setVisible(true);

		countEventFillOutPanel = new JPanel();
		countEventFillOutPanel.setLayout(null);
		countEventFillOutPanel.setBackground(Color.white);
		countEventFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		countEventFillOutFrame.add(countEventFillOutPanel);

		titleLabel = new JLabel("Count event added for " + RuleController.getInstance().getSelectedSpriteName());
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, 150, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		countEventFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		countEventFillOutPanel.add(nextButton);
		countEventFillOutPanel.add(cancelButton);

		helpLabel = new JLabel("");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		countEventFillOutPanel.add(helpLabel);
		countEventFillOutFrame.show();
		countEventFillOutPanel.show();
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.countEventFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.countEventFillOutPanel;
	}

	/**
	 * Adds action listeners to the cancel button and next button.
	 */
	@Override
	public void AddActionListenerToButtons(EventFillOutFormController eventFillOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) eventFillOutFormController);
		this.nextButton.addActionListener((ActionListener) eventFillOutFormController);
	}
}
