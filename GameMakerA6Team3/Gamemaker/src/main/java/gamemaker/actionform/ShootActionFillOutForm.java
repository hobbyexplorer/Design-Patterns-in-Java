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

public class ShootActionFillOutForm   implements ActionFillOutForm
{
	// Current JFrame
	private JFrame shootActionFillOutFrame;

	// Current JPanel
	private JPanel shootActionFillOutPanel;

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
	

	public ShootActionFillOutForm(){
		shootActionFillOutFrame = new JFrame();
		shootActionFillOutFrame.setVisible(false);
		shootActionFillOutFrame.setUndecorated(true);
		shootActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		shootActionFillOutFrame.setResizable(false);
		shootActionFillOutFrame.setLocationRelativeTo(null);

		shootActionFillOutPanel = new JPanel();
		shootActionFillOutPanel.setLayout(null);
		shootActionFillOutPanel.setBackground(Color.white);
		shootActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		shootActionFillOutFrame.add(shootActionFillOutPanel);		

		titleLabel = new JLabel("Shoot Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		shootActionFillOutPanel.add(titleLabel);
		
		questionLabel = new JLabel("Please select the sprite to be shot ?");
		questionLabel.setBounds(10, 100, 250, 30);
		shootActionFillOutPanel.add(questionLabel);
				
		spriteListName = new ArrayList<String>();
		
		spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite oneSprite : spriteList)
		{
			spriteListName.add(oneSprite.getName());
		}
		
		spriteListCombo = new JComboBox(spriteListName.toArray());
		spriteListCombo.setBounds(140, 140, 100, 30);
		shootActionFillOutPanel.add(spriteListCombo);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		shootActionFillOutPanel.add(nextButton);
		shootActionFillOutPanel.add(cancelButton);
	}

	@Override
	public void disposeFrame()
	{
		this.shootActionFillOutFrame.dispose();

	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.shootActionFillOutPanel;
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
		shootActionFillOutFrame.setVisible(visibility);

	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);

	}
	
	/* Getters and Setters for shoot action form */

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
