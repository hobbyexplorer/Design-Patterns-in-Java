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
import controller.rule.RuleController;

public class SplitActionFillOutForm   implements ActionFillOutForm
{
	// Current JFrame
	private JFrame splitActionFillOutFrame;

	// Current JPanel
	private JPanel splitActionFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;
	
	// Display the user a question relevant to the selected sprite
	private JLabel questionLabel, questionLabel2;
	
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
	
	private ArrayList<String> selectedSprites;
	
	private JComboBox selectedSpritesCombo;
	

	public SplitActionFillOutForm(){
		splitActionFillOutFrame = new JFrame();
		splitActionFillOutFrame.setVisible(false);
		splitActionFillOutFrame.setUndecorated(true);
		splitActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		splitActionFillOutFrame.setResizable(false);
		splitActionFillOutFrame.setLocationRelativeTo(null);

		splitActionFillOutPanel = new JPanel();
		splitActionFillOutPanel.setLayout(null);
		splitActionFillOutPanel.setBackground(Color.white);
		splitActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		splitActionFillOutFrame.add(splitActionFillOutPanel);		

		titleLabel = new JLabel("Split Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		splitActionFillOutPanel.add(titleLabel);
		
		questionLabel = new JLabel("Please select the sprite to be split ?");
		questionLabel.setBounds(10, 100, 250, 30);
		splitActionFillOutPanel.add(questionLabel);
				
		spriteListName = new ArrayList<String>();		
		spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite oneSprite : spriteList)
		{
			spriteListName.add(oneSprite.getName());
		}	
		selectedSprites = new ArrayList<String>();
		selectedSprites = RuleController.getInstance().getSelectedSprites();
		selectedSpritesCombo = new JComboBox(selectedSprites.toArray());
		selectedSpritesCombo.setBounds(140, 100, 100, 30);
		splitActionFillOutPanel.add(selectedSpritesCombo);
		
		questionLabel2 = new JLabel("Please select the sprite to be generated on split ?");
		questionLabel2.setBounds(10, 140, 250, 30);
		splitActionFillOutPanel.add(questionLabel2);
		
		spriteListCombo = new JComboBox(spriteListName.toArray());
		spriteListCombo.setBounds(140, 140, 100, 30);
		splitActionFillOutPanel.add(spriteListCombo);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(180, 250, 75, 20);		

		splitActionFillOutPanel.add(nextButton);
		splitActionFillOutPanel.add(cancelButton);
	}

	@Override
	public void disposeFrame()
	{
		this.splitActionFillOutFrame.dispose();

	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.splitActionFillOutPanel;
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
		splitActionFillOutFrame.setVisible(visibility);

	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);

	}
	
	/* Getters and Setters for split action form */

	public JComboBox getSpriteListCombo()
	{
		return spriteListCombo;
	}
	
	public JComboBox getSelectedSpritesCombo()
	{
		return selectedSpritesCombo;
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
