package gamemaker.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import controller.actionform.ActionFillOutFormController;

public class SoundActionFillOutForm  implements ActionFillOutForm
{

	// Current JFrame
	private JFrame soundActionFillOutFrame;

	// Current JPanel
	private JPanel soundActionFillOutPanel;

	// Displays the title label of the current form
	private JLabel titleLabel;

	// Displays the help text relevant to the form
	private JLabel helpLabel;

	// Next Button. Takes the user to next form(Action fill out form)
	private JButton nextButton;

	// Cancel button. Disposes the frame upon click
	private JButton cancelButton;
	
	//sound file chooser
	private JFileChooser soundFileChooser;
	
	//choose file button
	private JButton selectFileButton;
	
	//textfield showing the selected file
	private JTextField selectedFileField;
	
	private JLabel soundFileLabel;
	private JComboBox soundFileNameJComboBox;
	
	public SoundActionFillOutForm(){
		soundActionFillOutFrame = new JFrame();
		soundActionFillOutFrame.setVisible(false);
		soundActionFillOutFrame.setUndecorated(true);
		soundActionFillOutFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		soundActionFillOutFrame.setResizable(false);
		soundActionFillOutFrame.setLocationRelativeTo(null);

		soundActionFillOutPanel = new JPanel();
		soundActionFillOutPanel.setLayout(null);
		soundActionFillOutPanel.setBackground(Color.white);
		soundActionFillOutPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		soundActionFillOutFrame.add(soundActionFillOutPanel);

		titleLabel = new JLabel("Sound Action fill out form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		soundActionFillOutPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		nextButton = new JButton(Constants.NEXT_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		nextButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		soundActionFillOutPanel.add(nextButton);
		soundActionFillOutPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Choose a sound file <BR>to add sound to the game.</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		soundActionFillOutPanel.add(helpLabel);
		
		soundFileLabel = new JLabel();
		soundFileLabel.setText("<HTML>Select a File: </HTML>");
		soundFileLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		soundFileLabel.setBounds(20,100,100,20);
		soundActionFillOutPanel.add(soundFileLabel);
		
		selectedFileField = new JTextField();
		selectedFileField.setBounds(110, 100, 170, 20);
		//soundActionFillOutPanel.add(selectedFileField);
		
		soundFileNameJComboBox = new JComboBox();
		soundFileNameJComboBox.addItem("beep");
		soundFileNameJComboBox.addItem("boing");
		soundFileNameJComboBox.addItem("brick_hit");
		soundFileNameJComboBox.addItem("chimes");
		soundFileNameJComboBox.addItem("gameover");
		soundFileNameJComboBox.addItem("pop");
		soundFileNameJComboBox.setBounds(110, 140, 150, 20);
		soundActionFillOutPanel.add(soundFileNameJComboBox);
		
		
		//selectFileButton = new JButton(Constants.BROWSE_BUTTON);
		selectFileButton = new JButton(Constants.PLAY_BUTTON);
		//selectFileButton.setBounds(Constants.FILLOUT_BR0WSE_BUTTON_X, Constants.FILLOUT_BROWSE_BUTTON_Y, Constants.FILLOUT_BROWSE_BUTTON_WIDTH, Constants.FILLOUT_BROWSE_BUTTON_HEIGHT);
		selectFileButton.setBounds(Constants.FILLOUT_PLAY_BUTTON_X, Constants.FILLOUT_PLAY_BUTTON_Y, Constants.FILLOUT_PLAY_BUTTON_WIDTH, Constants.FILLOUT_PLAY_BUTTON_HEIGHT);
		soundActionFillOutPanel.add(selectFileButton);
		//soundActionFillOutPanel.add(selectFileButton);
		
		soundFileChooser = new JFileChooser();
		//soundActionFillOutPanel.add(soundFileChooser);
	}
	
	public int showFileChooser(){
		int ret=1;
		//ret = soundFileChooser.showDialog(null, "Open File");
		return ret;
	}
	
	public void populateTextField(){
		//selectedFileField.setText(soundFileChooser.getSelectedFile().getName());
		selectedFileField.setText(soundFileNameJComboBox.getSelectedItem().toString());
	}
	
	public void populateTextField(String text){
		selectedFileField.setText(text);
	}
	
	public String getFileName(){
		return selectedFileField.getText();
	}
	
	public boolean isTextFieldFilled(){
		
		boolean flag = (selectedFileField.getText() == "")?false:true;
		return flag;
	}
	
	
	@Override
	public void disposeFrame()
	{
		soundActionFillOutFrame.dispose();
	}

	@Override
	public JPanel getFillOutForm()
	{
		return this.soundActionFillOutPanel;
	}

	@Override
	public void AddActionListenerToButtons(ActionFillOutFormController actionFillOutFormController)
	{
		this.cancelButton.addActionListener((ActionListener) actionFillOutFormController);
		this.nextButton.addActionListener((ActionListener) actionFillOutFormController);
		this.selectFileButton.addActionListener((ActionListener) actionFillOutFormController);
	}

	@Override
	public void setVisible(boolean visibility)
	{
		soundActionFillOutFrame.setVisible(visibility);
	}

	@Override
	public void makeThisLastForm()
	{
		this.nextButton.setText(Constants.SUBMIT_BUTTON);
	}
	
	public void playSample()
	{
		GameMaker.logger.logInfo(soundFileNameJComboBox.getSelectedItem().toString() + ".wav");
		try
		{
	
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("sound/"+ soundFileNameJComboBox.getSelectedItem().toString() + ".wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();


		}
		catch (Exception e)
		{
			GameMaker.logger.logInfo("Failed to load audio clip");
		}
	}
}
