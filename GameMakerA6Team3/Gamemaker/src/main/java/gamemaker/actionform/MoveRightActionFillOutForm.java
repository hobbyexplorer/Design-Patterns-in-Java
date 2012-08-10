package gamemaker.actionform;

import gamemaker.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import controller.actionform.ActionFillOutFormController;

public class MoveRightActionFillOutForm   implements ActionFillOutForm
{
	// Current JFrame
	private JFrame moveRightActionFillOutFrame;

	// Current JPanel
	private JPanel moveRightActionFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;
	
	// Display the user a question relevant to the selected sprite
	private JLabel questionLabel;
	
	// The combo box which lets the user to decide whether the object has to rotate
	private JComboBox rotateSprite;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;
	
	// The array list containing the rotate options
	private ArrayList<String> rotateOption;

	public MoveRightActionFillOutForm(){
		moveRightActionFillOutFrame = new JFrame();
		moveRightActionFillOutFrame.setVisible(false);
		moveRightActionFillOutFrame.setUndecorated(true);
		moveRightActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		moveRightActionFillOutFrame.setResizable(false);
		moveRightActionFillOutFrame.setLocationRelativeTo(null);

		moveRightActionFillOutPanel = new JPanel();
		moveRightActionFillOutPanel.setLayout(null);
		moveRightActionFillOutPanel.setBackground(Color.white);
		moveRightActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		moveRightActionFillOutFrame.add(moveRightActionFillOutPanel);		

		titleLabel = new JLabel("Move Right Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		moveRightActionFillOutPanel.add(titleLabel);
		
		questionLabel = new JLabel("Do you want the sprite to rotate ?");
		questionLabel.setBounds(10, 100, 250, 30);
		moveRightActionFillOutPanel.add(questionLabel);
				
		rotateOption = new ArrayList<String>();
		rotateOption.add("no");
		rotateOption.add("yes");
		rotateSprite = new JComboBox(rotateOption.toArray());
		rotateSprite.setBounds(140, 140, 50, 30);
		moveRightActionFillOutPanel.add(rotateSprite);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		moveRightActionFillOutPanel.add(nextButton);
		moveRightActionFillOutPanel.add(cancelButton);
	}

	@Override
	public void disposeFrame()
	{
		this.moveRightActionFillOutFrame.dispose();

	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.moveRightActionFillOutPanel;
		//return null;
	}

	@Override
	public void AddActionListenerToButtons(ActionFillOutFormController actionFullOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) actionFullOutFormController);
		this.nextButton.addActionListener((ActionListener) actionFullOutFormController);

	}

	@Override
	public void setVisible(boolean visibility)
	{
		moveRightActionFillOutFrame.setVisible(visibility);

	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);

	}
	
	public JComboBox getRotateSprite()
	{
		return rotateSprite;
	}

	public void setRotateSprite(JComboBox rotateSprite)
	{
		this.rotateSprite = rotateSprite;
	}

}
