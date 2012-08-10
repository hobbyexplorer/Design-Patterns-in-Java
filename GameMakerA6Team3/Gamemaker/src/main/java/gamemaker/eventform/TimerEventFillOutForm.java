package gamemaker.eventform;

import gamemaker.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import controller.eventform.EventFillOutFormController;
import controller.eventform.KeyPressEventFillOutFormController;
import controller.rule.RuleController;

public class TimerEventFillOutForm implements EventFillOutForm
{

	// Current JFrame
	private JFrame timerEventFillOutFrame;

	// Current JPanel
	private JPanel timerEventFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;

	private JLabel keyEventLabel;

	private KeyEvent inputKeyCode;

	/**
	 * KeyPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public TimerEventFillOutForm()
	{
		timerEventFillOutFrame = new JFrame();
		timerEventFillOutFrame.setUndecorated(true);
		timerEventFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		timerEventFillOutFrame.setResizable(false);
		timerEventFillOutFrame.setLocationRelativeTo(null);
		timerEventFillOutFrame.setVisible(true);

		timerEventFillOutPanel = new JPanel();
		timerEventFillOutPanel.setLayout(null);
		timerEventFillOutPanel.setBackground(Color.white);
		timerEventFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		timerEventFillOutFrame.add(timerEventFillOutPanel);

		titleLabel = new JLabel("Timer Event added");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		timerEventFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(85, 250, 75, 20);
		nextButton.setBounds(180, 250, 75, 20);

		timerEventFillOutPanel.add(nextButton);
		timerEventFillOutPanel.add(cancelButton);

		helpLabel = new JLabel("Click next to add actions");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		timerEventFillOutPanel.add(helpLabel);
		timerEventFillOutFrame.show();

	}

	public void addMouseListenerToLabel(KeyPressEventFillOutFormController keyPressEventFillOutFormController)
	{
		keyEventLabel.addMouseListener(keyPressEventFillOutFormController);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.timerEventFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.timerEventFillOutPanel;
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

	public void setAcceptedKeyCode(KeyEvent e)
	{
		this.inputKeyCode = e;
	}

	public void setKeyInputLabelText(String modifiedKeyInputText)
	{
		this.keyEventLabel.setText(modifiedKeyInputText);
	}
}
