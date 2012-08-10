package gamemaker.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import controller.actionform.TwoSpriteMoveAllActionFillOutFormController;
import controller.rule.RuleController;

public class ContractActionFillOutForm implements ActionFillOutForm, ChangeListener
{
	// Current JFrame
	private JFrame expandActionFillOutFormFrame;

	// Current JPanel
	private JPanel expandActionFillOutFormPanel;

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

	private CheckboxGroup cbg;

	private Checkbox spriteSelect1;

	private Checkbox spriteSelect2;

	private Sprite selectedSprite;

	/**
	 * KeyPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public ContractActionFillOutForm()
	{
		expandActionFillOutFormFrame = new JFrame();
		expandActionFillOutFormFrame.setVisible(false);
		expandActionFillOutFormFrame.setUndecorated(true);
		expandActionFillOutFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		expandActionFillOutFormFrame.setResizable(false);
		expandActionFillOutFormFrame.setLocationRelativeTo(null);

		expandActionFillOutFormPanel = new JPanel();
		expandActionFillOutFormPanel.setLayout(null);
		expandActionFillOutFormPanel.setBackground(Color.white);
		expandActionFillOutFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		expandActionFillOutFormFrame.add(expandActionFillOutFormPanel);

		titleLabel = new JLabel("Contract action:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		expandActionFillOutFormPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		expandActionFillOutFormPanel.add(nextButton);
		expandActionFillOutFormPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Choose the sprite to perform this action.<br> Choose a percentage of expansion</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		expandActionFillOutFormPanel.add(helpLabel);

		ArrayList<String> selectedSprites = RuleController.getInstance().getSelectedSprites();
		String spriteOne = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(selectedSprites.get(0)).getName();
		String spriteTwo = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(selectedSprites.get(1)).getName();

		JPanel checkBoxPanel = new JPanel();
		cbg = new CheckboxGroup();
		spriteSelect1 = new Checkbox(spriteOne, cbg, false);
		spriteSelect1.setBounds(10, 10, 30, 30);
		checkBoxPanel.add(spriteSelect1);

		spriteSelect2 = new Checkbox(spriteTwo, cbg, false);
		spriteSelect2.setBounds(70, 10, 30, 30);
		checkBoxPanel.add(spriteSelect2);

		checkBoxPanel.setBackground(Constants.FILLOUT_FORM_BG_COLOR);
		checkBoxPanel.setBounds(100, 100, 100, 100);
		this.expandActionFillOutFormPanel.add(checkBoxPanel);

		angleTextLabel = new JLabel("Expand %: ");
		angleTextLabel.setBounds(100, 250, 70, 30);
		expandActionFillOutFormPanel.add(angleTextLabel);

		angleTextField = new JTextField("90");
		angleTextField.setBounds(180, 250, 50, 25);
		expandActionFillOutFormPanel.add(angleTextField);

		angleSelectionSlider = new JSlider(1, 100);
		angleSelectionSlider.setBounds(80, 300, 250, 30);
		angleSelectionSlider.setBackground(Color.white);
		angleSelectionSlider.addChangeListener(this);
		expandActionFillOutFormPanel.add(angleSelectionSlider);
	}

	public String getExpandPercent()
	{
		return angleTextField.getText();
	}

	public void setVisible(boolean visibility)
	{
		expandActionFillOutFormFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.expandActionFillOutFormFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.expandActionFillOutFormPanel;
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
		angleTextField.setText(Integer.toString(this.angleSelectionSlider.getValue()));
		GameMaker.logger.logInfo(this.getClass().getName() + " angle text changed ");
	}

	public Sprite getSelectedSprite()
	{
		if (cbg.getSelectedCheckbox().equals(spriteSelect1))
		{
			this.selectedSprite = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(spriteSelect1.getLabel());
			GameMaker.logger.logInfo(this.getClass().getName() + " First sprite selected  ");
		}
		else
		{
			this.selectedSprite = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(spriteSelect2.getLabel());
			GameMaker.logger.logInfo(this.getClass().getName() + "  Seconbd sprite selected ");
		}
		return this.selectedSprite;
	}
}