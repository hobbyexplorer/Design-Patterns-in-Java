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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.actionform.ActionFillOutFormController;

public class MoveActionFillOutForm implements ActionFillOutForm, ChangeListener
{
	// Current JFrame
	private JFrame moveActionFillOutFrame;

	// Current JPanel
	private JPanel moveActionFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;

	private JSlider angleSelectionSlider;

	private JTextField angleTextField;

	private JLabel angleTextLabel;

	/**
	 * MoveActionFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public MoveActionFillOutForm()
	{
		moveActionFillOutFrame = new JFrame();
		moveActionFillOutFrame.setVisible(false);
		moveActionFillOutFrame.setUndecorated(true);
		moveActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		moveActionFillOutFrame.setResizable(false);
		moveActionFillOutFrame.setLocationRelativeTo(null);

		moveActionFillOutPanel = new JPanel();
		moveActionFillOutPanel.setLayout(null);
		moveActionFillOutPanel.setBackground(Color.white);
		moveActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		moveActionFillOutFrame.add(moveActionFillOutPanel);

		titleLabel = new JLabel("Move Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		moveActionFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		moveActionFillOutPanel.add(nextButton);
		moveActionFillOutPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Move the slider to choose a <BR>move angle for your sprite.</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		moveActionFillOutPanel.add(helpLabel);

		angleTextLabel = new JLabel("Angle: ");
		angleTextLabel.setBounds(140, 100, 40, 30);
		moveActionFillOutPanel.add(angleTextLabel);
		
		angleTextField = new JTextField("90");
		angleTextField.setBounds(180, 100, 50, 25);
		moveActionFillOutPanel.add(angleTextField);
		
		angleSelectionSlider = new JSlider(0, 359);
		angleSelectionSlider.setBounds(50, 180, 250, 30);
		angleSelectionSlider.setBackground(Color.white);
		angleSelectionSlider.addChangeListener(this);
		moveActionFillOutPanel.add(angleSelectionSlider);


	}
	
	public String getAngleText()
	{
		return angleTextField.getText();
	}

	public void setVisible(boolean visibility)
	{
		moveActionFillOutFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.moveActionFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.moveActionFillOutPanel;
	}

	/**
	 * Adds action listeners to the cancel button and next button.
	 */
	@Override
	public void AddActionListenerToButtons(ActionFillOutFormController actionFillOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) actionFillOutFormController);
		this.nextButton.addActionListener((ActionListener) actionFillOutFormController);
	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		angleTextField.setText(Integer.toString(this.angleSelectionSlider.getValue()));
	}
	
}
