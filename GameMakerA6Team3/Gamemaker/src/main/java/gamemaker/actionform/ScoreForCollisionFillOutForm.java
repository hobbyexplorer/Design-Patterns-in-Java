package gamemaker.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import model.Sprite;
import controller.GameCreatePanelController;
import controller.actionform.ActionFillOutFormController;
import controller.rule.RuleController;

public class ScoreForCollisionFillOutForm implements ActionFillOutForm, ChangeListener
{
	// Current JFrame
	private JFrame scoreFillOutFormFrame;

	// Current JPanel
	private JPanel scoreFillOutFormPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;

	private JSlider scoreSelectionSlider;

	private JTextField scoreTextField;

	private JLabel scoreTextLabel;

	/**
	 * KeyPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public ScoreForCollisionFillOutForm()
	{
		scoreFillOutFormFrame = new JFrame();
		scoreFillOutFormFrame.setVisible(false);
		scoreFillOutFormFrame.setUndecorated(true);
		scoreFillOutFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		scoreFillOutFormFrame.setResizable(false);
		scoreFillOutFormFrame.setLocationRelativeTo(null);

		scoreFillOutFormPanel = new JPanel();
		scoreFillOutFormPanel.setLayout(null);
		scoreFillOutFormPanel.setBackground(Color.white);
		scoreFillOutFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		scoreFillOutFormFrame.add(scoreFillOutFormPanel);

		titleLabel = new JLabel("Score action:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		scoreFillOutFormPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		scoreFillOutFormPanel.add(nextButton);
		scoreFillOutFormPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Choose the sprite to perform this action.<br> Choose a percentage of expansion</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		scoreFillOutFormPanel.add(helpLabel);

		ArrayList<String> selectedSprites = RuleController.getInstance().getSelectedSprites();

		scoreTextLabel = new JLabel("Increase socre points by: ");
		scoreTextLabel.setBounds(50, 250, 120, 30);
		scoreFillOutFormPanel.add(scoreTextLabel);

		scoreTextField = new JTextField("3");
		scoreTextField.setBounds(180, 250, 50, 25);
		scoreFillOutFormPanel.add(scoreTextField);

		scoreSelectionSlider = new JSlider(1, 100);
		scoreSelectionSlider.setBounds(80, 300, 250, 30);
		scoreSelectionSlider.setBackground(Color.white);
		scoreSelectionSlider.addChangeListener(this);
		scoreFillOutFormPanel.add(scoreSelectionSlider);
	}

	public String getScorePoint()
	{
		return scoreTextField.getText();
	}

	public void setVisible(boolean visibility)
	{
		scoreFillOutFormFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.scoreFillOutFormFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.scoreFillOutFormPanel;
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
		scoreTextField.setText(Integer.toString(this.scoreSelectionSlider.getValue()));
		GameMaker.logger.logInfo(this.getClass().getName() + " angle text changed ");
	}
}