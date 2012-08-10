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

public class CollisionEventFillOutForm implements EventFillOutForm
{
	// Current JFrame
	private JFrame collisionEventFillOutFrame;

	// Current JPanel
	private JPanel collisionEventFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;

	/**
	 * KeyPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public CollisionEventFillOutForm()
	{
		collisionEventFillOutFrame = new JFrame();
		collisionEventFillOutFrame.setUndecorated(true);
		collisionEventFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		collisionEventFillOutFrame.setResizable(false);
		collisionEventFillOutFrame.setLocationRelativeTo(null);
		collisionEventFillOutFrame.setVisible(true);

		collisionEventFillOutPanel = new JPanel();
		collisionEventFillOutPanel.setLayout(null);
		collisionEventFillOutPanel.setBackground(Color.white);
		collisionEventFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		collisionEventFillOutFrame.add(collisionEventFillOutPanel);

		titleLabel = new JLabel("Collision event added");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		collisionEventFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		collisionEventFillOutPanel.add(nextButton);
		collisionEventFillOutPanel.add(cancelButton);

		helpLabel = new JLabel("Click next to add actions");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		collisionEventFillOutPanel.add(helpLabel);
		collisionEventFillOutPanel.show();
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.collisionEventFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.collisionEventFillOutPanel;
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
