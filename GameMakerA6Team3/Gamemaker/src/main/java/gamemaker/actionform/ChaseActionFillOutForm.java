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

import model.Sprite;

import controller.GameCreatePanelController;
import controller.actionform.ActionFillOutFormController;

public class ChaseActionFillOutForm implements ActionFillOutForm
{

	// Current JFrame
	private JFrame chaseActionFillOutFrame;

	// Current JPanel
	private JPanel chaseActionFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;
		
	// Display the user a question relevant to the selected sprite
	private JLabel questionLabel;
		
	// The combo box containing the list of sprite
	private JComboBox spriteListCombo;
	
	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;
	
	// The array list containing the list of sprite
	private ArrayList<Sprite> spriteList;
	
	// The array list of strings containing the sprite names
	private ArrayList<String> spriteListName;
	

	public ChaseActionFillOutForm(){
		chaseActionFillOutFrame = new JFrame();
		chaseActionFillOutFrame.setVisible(false);
		chaseActionFillOutFrame.setUndecorated(true);
		chaseActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		chaseActionFillOutFrame.setResizable(false);
		chaseActionFillOutFrame.setLocationRelativeTo(null);

		chaseActionFillOutPanel = new JPanel();
		chaseActionFillOutPanel.setLayout(null);
		chaseActionFillOutPanel.setBackground(Color.white);
		chaseActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		chaseActionFillOutFrame.add(chaseActionFillOutPanel);		

		titleLabel = new JLabel("Chase Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		chaseActionFillOutPanel.add(titleLabel);
		
		questionLabel = new JLabel("Please select the sprite to be chased ?");
		questionLabel.setBounds(10, 100, 250, 30);
		chaseActionFillOutPanel.add(questionLabel);
				
		spriteListName = new ArrayList<String>();
		
		spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite oneSprite : spriteList)
		{
			spriteListName.add(oneSprite.getName());
		}
		
		spriteListCombo = new JComboBox(spriteListName.toArray());
		spriteListCombo.setBounds(140, 140, 100, 30);
		chaseActionFillOutPanel.add(spriteListCombo);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		chaseActionFillOutPanel.add(nextButton);
		chaseActionFillOutPanel.add(cancelButton);
	}
	@Override
	public void disposeFrame()
	{
		this.chaseActionFillOutFrame.dispose();
		
	}

	@Override
	public JPanel getFillOutForm()
	{
		// TODO Auto-generated method stub
		return this.chaseActionFillOutPanel;
	}

	@Override
	public void AddActionListenerToButtons(ActionFillOutFormController actionFillOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) actionFillOutFormController);
		this.nextButton.addActionListener((ActionListener) actionFillOutFormController);
		
	}

	@Override
	public void setVisible(boolean visibility)
	{
		chaseActionFillOutFrame.setVisible(visibility);
		
	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);
		
	}
	
	/* Getters and Setters for chase action form */

	public JComboBox getSpriteListCombo()
	{
		return spriteListCombo;
	}

	public void setRotateSprite(JComboBox spriteListCombo)
	{
		this.spriteListCombo = spriteListCombo;
	}

	public ArrayList<Sprite> getSpriteList()
	{
		return spriteList;
	}

	public void setSpriteList(ArrayList<Sprite> spriteList)
	{
		this.spriteList = spriteList;
	}

	public ArrayList<String> getSpriteListName()
	{
		return spriteListName;
	}

	public void setSpriteListName(ArrayList<String> spriteListName)
	{
		this.spriteListName = spriteListName;
	}

}
