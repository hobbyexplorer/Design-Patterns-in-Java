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
import java.awt.event.MouseListener;
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
import controller.actionform.MoveAlongActionFillOutFormController;
import controller.rule.RuleController;

public class MoveAlongActionFillOutForm implements ActionFillOutForm, ChangeListener, MouseListener
{
	// Current JFrame
	private JFrame moveAlongActionFillOutFormFrame;

	// Current JPanel
	private JPanel moveAlongActionFillOutFormPanel;

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

	private JLabel speedTextLabel;

	private JTextField speedTextField;

	private Sprite selectedSprite;

	/**
	 * KeyPressEventFillOutForm Constructor. Adds all user interface components
	 * to the panel and adds the panel to the frame.
	 */
	public MoveAlongActionFillOutForm()
	{
		moveAlongActionFillOutFormFrame = new JFrame();
		moveAlongActionFillOutFormFrame.setVisible(false);
		moveAlongActionFillOutFormFrame.setUndecorated(true);
		moveAlongActionFillOutFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		moveAlongActionFillOutFormFrame.setResizable(false);
		moveAlongActionFillOutFormFrame.setLocationRelativeTo(null);

		moveAlongActionFillOutFormPanel = new JPanel();
		moveAlongActionFillOutFormPanel.setLayout(null);
		moveAlongActionFillOutFormPanel.setBackground(Color.white);
		moveAlongActionFillOutFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		moveAlongActionFillOutFormFrame.add(moveAlongActionFillOutFormPanel);

		titleLabel = new JLabel("Move the Sprite along with the collided sprite");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		moveAlongActionFillOutFormPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		moveAlongActionFillOutFormPanel.add(nextButton);
		moveAlongActionFillOutFormPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Please select the sprite to MoveAlong</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		moveAlongActionFillOutFormPanel.add(helpLabel);

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
		this.moveAlongActionFillOutFormPanel.add(checkBoxPanel);
	}

	public void addMouseListenerToRadio(MoveAlongActionFillOutFormController moveAlongActionFillOutFormController)
	{
		this.spriteSelect1.addMouseListener(this);
		this.spriteSelect2.addMouseListener(this);
	}

	public void setVisible(boolean visibility)
	{
		moveAlongActionFillOutFormFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.moveAlongActionFillOutFormFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.moveAlongActionFillOutFormPanel;
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

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		GameMaker.logger.logInfo(this.getClass().getName() + " mouse pressed ");
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
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
}
