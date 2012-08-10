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

public class MoveUpActionFillOutForm   implements ActionFillOutForm
{
	// Current JFrame
	private JFrame moveUpActionFillOutFrame;

	// Current JPanel
	private JPanel moveUpActionFillOutPanel;

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

	public MoveUpActionFillOutForm(){
		moveUpActionFillOutFrame = new JFrame();
		moveUpActionFillOutFrame.setVisible(false);
		moveUpActionFillOutFrame.setUndecorated(true);
		moveUpActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		moveUpActionFillOutFrame.setResizable(false);
		moveUpActionFillOutFrame.setLocationRelativeTo(null);

		moveUpActionFillOutPanel = new JPanel();
		moveUpActionFillOutPanel.setLayout(null);
		moveUpActionFillOutPanel.setBackground(Color.white);
		moveUpActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		moveUpActionFillOutFrame.add(moveUpActionFillOutPanel);		

		titleLabel = new JLabel("Move Up Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		moveUpActionFillOutPanel.add(titleLabel);
		
		questionLabel = new JLabel("Do you want the sprite to rotate ?");
		questionLabel.setBounds(10, 100, 250, 30);
		moveUpActionFillOutPanel.add(questionLabel);
				
		rotateOption = new ArrayList<String>();
		rotateOption.add("no");
		rotateOption.add("yes");
		rotateSprite = new JComboBox(rotateOption.toArray());
		rotateSprite.setBounds(140, 140, 50, 30);
		moveUpActionFillOutPanel.add(rotateSprite);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		moveUpActionFillOutPanel.add(nextButton);
		moveUpActionFillOutPanel.add(cancelButton);
	}

	@Override
	public void disposeFrame()
	{
		this.moveUpActionFillOutFrame.dispose();

	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.moveUpActionFillOutPanel;
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
		moveUpActionFillOutFrame.setVisible(visibility);

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
