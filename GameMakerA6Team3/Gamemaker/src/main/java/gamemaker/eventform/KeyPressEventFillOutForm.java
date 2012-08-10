package gamemaker.eventform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamemaker.Constants;
import gamemaker.GameMaker;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import controller.eventform.EventFillOutFormController;
import controller.eventform.KeyPressEventFillOutFormController;

/**
 * Contains User interface elements to display the form. Initialized and actions
 * listened by KeyPressEventFillOutController class.
 * 
 * Class colaborators: KeyPressEventFillOutController.
 * 
 */
public class KeyPressEventFillOutForm implements EventFillOutForm
{
	// Current JFrame
	private JFrame keyPressEventFillOutFrame;

	// Current JPanel
	private JPanel keyPressEventFillOutPanel;

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
	public KeyPressEventFillOutForm()
	{
		keyPressEventFillOutFrame = new JFrame();
		keyPressEventFillOutFrame.setUndecorated(true);
		keyPressEventFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		keyPressEventFillOutFrame.setResizable(false);
		keyPressEventFillOutFrame.setLocationRelativeTo(null);
		keyPressEventFillOutFrame.setVisible(true);
		
		keyPressEventFillOutPanel = new JPanel();
		keyPressEventFillOutPanel.setLayout(null);
		keyPressEventFillOutPanel.setBackground(Color.white);
		keyPressEventFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		keyPressEventFillOutFrame.add(keyPressEventFillOutPanel);

		titleLabel = new JLabel("Select a key of your choice");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		keyPressEventFillOutPanel.add(titleLabel);

		keyEventLabel = new JLabel();
		keyEventLabel.setText("<HTML>Click here to add a key event.</HTML>");
		keyEventLabel.setBounds(80, 150, 200, 90);
		keyPressEventFillOutPanel.add(keyEventLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(85, 250, 75, 20);
		nextButton.setBounds(180, 250, 75, 20);

		keyPressEventFillOutPanel.add(nextButton);
		keyPressEventFillOutPanel.add(cancelButton);

		helpLabel = new JLabel("");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		keyPressEventFillOutPanel.add(helpLabel);
		keyPressEventFillOutPanel.show();
		keyPressEventFillOutFrame.show();
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
		this.keyPressEventFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.keyPressEventFillOutPanel;
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
