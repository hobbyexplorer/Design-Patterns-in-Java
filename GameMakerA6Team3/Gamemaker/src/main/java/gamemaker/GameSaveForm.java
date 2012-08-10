package gamemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.GameSaveFormController;
import controller.actionform.ActionFillOutFormController;

public class GameSaveForm implements ChangeListener{

	private JFrame gameSaveFormFrame;
	
	private JPanel gameSaveFormPanel;
	
	private JLabel titleLabel;
	
	private JLabel helpLabel;
	
	private JButton saveButton;
	
	private JButton cancelButton;
	
	private JTextField gameNameField;
	
	private JLabel gameNameLabel;
	
	private JLabel gameNameChooserLabel;
	
	private JLabel publishLabel;

	private JCheckBox publishCheck;
	
	private JComboBox gameChooserComboBox;
	
	public GameSaveForm() {
		gameSaveFormFrame = new JFrame();
		gameSaveFormFrame.setVisible(false);
		gameSaveFormFrame.setUndecorated(true);
		gameSaveFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		gameSaveFormFrame.setResizable(false);
		gameSaveFormFrame.setLocationRelativeTo(null);

		gameSaveFormPanel = new JPanel();
		gameSaveFormPanel.setLayout(null);
		gameSaveFormPanel.setBackground(Color.white);
		gameSaveFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		gameSaveFormFrame.add(gameSaveFormPanel);

		titleLabel = new JLabel("Save Game Form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		gameSaveFormPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		saveButton = new JButton(Constants.SAVE_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		saveButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		gameSaveFormPanel.add(saveButton);
		gameSaveFormPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Either add a new Game Name or Choose existing from Drop-Down</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		gameSaveFormPanel.add(helpLabel);

		gameNameLabel = new JLabel("Game Name: ");
		gameNameLabel.setBounds(60, 100, 80, 30);
		gameSaveFormPanel.add(gameNameLabel);
		
		gameNameField = new JTextField("");
		gameNameField.setBounds(150, 100, 130, 25);
		gameSaveFormPanel.add(gameNameField);
		
		gameNameChooserLabel = new JLabel("Save as: ");
		gameNameChooserLabel.setBounds(60, 150, 80, 30);
		gameSaveFormPanel.add(gameNameChooserLabel);
		
		gameChooserComboBox = new JComboBox();
		gameChooserComboBox.setBounds(150, 150, 130, 30);
		gameSaveFormPanel.add(gameChooserComboBox);
		
		publishLabel = new JLabel("Publish the current version");
		publishLabel.setBounds(60, 200, 200, 30);
		gameSaveFormPanel.add(publishLabel);
		
		publishCheck = new JCheckBox();
		publishCheck.setBounds(270, 200, 20, 20);
		gameSaveFormPanel.add(publishCheck);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void AddActionListenerToButtons(GameSaveFormController gameSaveFormController)
	{
		this.cancelButton.addActionListener((ActionListener) gameSaveFormController);
		this.saveButton.addActionListener((ActionListener) gameSaveFormController);
	}

	public JFrame getGameSaveForm() {
		return gameSaveFormFrame;
	}

	public void setGameSaveForm(JFrame gameSaveForm) {
		this.gameSaveFormFrame = gameSaveForm;
	}

	public JPanel getGameSaveFormPanel() {
		return gameSaveFormPanel;
	}

	public void setGameSaveFormPanel(JPanel gameSaveFormPanel) {
		this.gameSaveFormPanel = gameSaveFormPanel;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JLabel getHelpLabel() {
		return helpLabel;
	}

	public void setHelpLabel(JLabel helpLabel) {
		this.helpLabel = helpLabel;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JLabel getGameNameLabel() {
		return gameNameLabel;
	}

	public void setGameNameLabel(JLabel gameNameLabel) {
		this.gameNameLabel = gameNameLabel;
	}

	public JTextField getGameNameField() {
		return gameNameField;
	}

	public void setGameNameField(JTextField gameNameField) {
		this.gameNameField = gameNameField;
	}
	
	public void setVisible(boolean visibility)
	{
		gameSaveFormFrame.setVisible(visibility);
	}

	/**
	 * Disposes the current frame
	 */
	public void disposeFrame()
	{
		this.gameSaveFormFrame.dispose();
	}
	
	public JPanel getFillOutForm()
	{
		return this.getGameSaveFormPanel();
	}
	
	public String getGameNameFromTextField(){
		return this.gameNameField.getText();
	}

	public JComboBox getGameChooserConboBox() {
		return gameChooserComboBox;
	}

	public void setGameChooserConboBox(JComboBox gameChooserConboBox) {
		this.gameChooserComboBox = gameChooserConboBox;
	}

	public JCheckBox getPublishCheck() {
		return publishCheck;
	}

	public void setPublishCheck(JCheckBox publishCheck) {
		this.publishCheck = publishCheck;
	}

	public JLabel getPublishLabel() {
		return publishLabel;
	}

	public void setPublishLabel(JLabel publishLabel) {
		this.publishLabel = publishLabel;
	}

}
