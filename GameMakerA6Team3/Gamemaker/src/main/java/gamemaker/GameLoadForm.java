package gamemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.w3c.dom.NameList;

import controller.LoadGameFormController;

public class GameLoadForm implements ActionListener{
private JFrame gameLoadFormFrame;
	
	private JPanel gameLoadFormPanel;
	
	private JLabel titleLabel;
	
	private JLabel helpLabel;
	
	private JButton cancelButton;
	
	private JLabel gameNameChooserLabel;
	
	private JComboBox gameNameChooserComboBox;
	
	private JLabel gameVersionChooserLabel;
	
	private JComboBox gameVersionChooserComboBox;

	private JButton loadButton;
	
	private String[] version;
	
	public GameLoadForm() {
		gameLoadFormFrame = new JFrame();
		gameLoadFormFrame.setVisible(false);
		gameLoadFormFrame.setUndecorated(true);
		gameLoadFormFrame.setSize(new Dimension(Constants.FILL_OUT_FORM_WIDTH, Constants.FILL_OUT_FORM_HEIGHT));
		gameLoadFormFrame.setResizable(false);
		gameLoadFormFrame.setLocationRelativeTo(null);

		gameLoadFormPanel = new JPanel();
		gameLoadFormPanel.setLayout(null);
		gameLoadFormPanel.setBackground(Color.white);
		gameLoadFormPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		gameLoadFormFrame.add(gameLoadFormPanel);

		titleLabel = new JLabel("Load Game Form:");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleLabel.setBounds(Constants.FILLOUT_FORM_TITLE_DIMENSION_X, Constants.FILLOUT_FORM_TITLE_DIMENSION_Y, Constants.FILLOUT_FORM_TITLE_WIDTH, Constants.FILLOUT_FORM_TITLE_HEIGHT);
		gameLoadFormPanel.add(titleLabel);

		cancelButton = new JButton(Constants.CANCEL_BUTTON);
		loadButton = new JButton(Constants.LOAD_BUTTON);
		cancelButton.setBounds(Constants.FILLOUT_CANCEL_BUTTON_X, Constants.FILLOUT_CANCEL_BUTTON_Y, Constants.FILLOUT_CANCEL_BUTTON_WIDTH, Constants.FILLOUT_CANCEL_BUTTON_HEIGHT);
		loadButton.setBounds(Constants.FILLOUT_NEXT_BUTTON_X, Constants.FILLOUT_NEXT_BUTTON_Y, Constants.FILLOUT_NEXT_BUTTON_WIDTH, Constants.FILLOUT_NEXT_BUTTON_HEIGHT);

		gameLoadFormPanel.add(loadButton);
		gameLoadFormPanel.add(cancelButton);

		helpLabel = new JLabel();
		helpLabel.setText("<HTML>Enter the game and the respective version you want to load</HTML>");
		helpLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		helpLabel.setBounds(Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X, Constants.EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y, Constants.EVENT_FILLOUT_FORM_HELPTEXT_WIDTH, Constants.EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT);
		gameLoadFormPanel.add(helpLabel);

		gameNameChooserLabel = new JLabel("Game Name: ");
		gameNameChooserLabel.setBounds(60, 100, 80, 30);
		gameLoadFormPanel.add(gameNameChooserLabel);
		
		gameNameChooserComboBox = new JComboBox();
		gameNameChooserComboBox.setBounds(150, 100, 130, 25);
		gameLoadFormPanel.add(gameNameChooserComboBox);
		gameNameChooserComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox ncb = (JComboBox)e.getSource();
				Scanner nameScanner;
				if(ncb.getSelectedItem().toString().equals("")){
					changeVersionList(null);
				}
				else{
					gameVersionChooserComboBox.removeAllItems();
					nameScanner = new Scanner(ncb.getSelectedItem().toString());
					nameScanner.useDelimiter("\\(");
					gameVersionChooserComboBox.addItem("");
					String gameName = nameScanner.next();
					String gameID = nameScanner.next().toString().substring(0,1);
					for(int i = 0; i < version.length; i++){
						if(version[i].contains("_#!"+gameID)){
							nameScanner = new Scanner(version[i]);
							nameScanner.useDelimiter("_");
							gameVersionChooserComboBox.addItem(nameScanner.next());
						}
					}
				}
				
			}
		});
		
		gameVersionChooserLabel = new JLabel("Version Number: ");
		gameVersionChooserLabel.setBounds(60, 150, 120, 30);
		gameLoadFormPanel.add(gameVersionChooserLabel);
		
		gameVersionChooserComboBox = new JComboBox();
		gameVersionChooserComboBox.setBounds(190, 150, 70, 30);
		gameLoadFormPanel.add(gameVersionChooserComboBox);

	}

	public JFrame getGameLoadFormFrame() {
		return gameLoadFormFrame;
	}

	public void setGameLoadFormFrame(JFrame gameLoadFormFrame) {
		this.gameLoadFormFrame = gameLoadFormFrame;
	}

	public JPanel getGameLoadFormPanel() {
		return gameLoadFormPanel;
	}

	public void setGameLoadFormPanel(JPanel gameLoadFormPanel) {
		this.gameLoadFormPanel = gameLoadFormPanel;
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

	public JButton getLoadButton() {
		return loadButton;
	}

	public void setLoadButton(JButton loadButton) {
		this.loadButton = loadButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JLabel getGameNameChooserLabel() {
		return gameNameChooserLabel;
	}

	public void setGameNameChooserLabel(JLabel gameNameChooserLabel) {
		this.gameNameChooserLabel = gameNameChooserLabel;
	}

	public JComboBox getGameNameChooserComboBox() {
		return gameNameChooserComboBox;
	}

	public void setGameNameChooserComboBox(JComboBox gameNameChooserComboBox) {
		this.gameNameChooserComboBox = gameNameChooserComboBox;
	}

	public JLabel getGameVersionChooserLabel() {
		return gameVersionChooserLabel;
	}

	public void setGameVersionChooserLabel(JLabel gameVersionChooserLabel) {
		this.gameVersionChooserLabel = gameVersionChooserLabel;
	}

	public JComboBox getGameVersionChooserComboBox() {
		return gameVersionChooserComboBox;
	}

	public void setGameVersionChooserComboBox(JComboBox gameVersionChooserComboBox) {
		this.gameVersionChooserComboBox = gameVersionChooserComboBox;
	}
	
	public String getSelectedName(){
		return this.gameNameChooserComboBox.getSelectedItem().toString();
	}
	
	public String getSelectedVersion(){
		return this.gameVersionChooserComboBox.getSelectedItem().toString();
	}
	
	public void changeVersionList(String[] versionList){
		gameVersionChooserComboBox.removeAllItems();
		gameVersionChooserComboBox.addItem("");
		if(versionList != null)
			for(int i=0; i < versionList.length; i++){
				gameVersionChooserComboBox.addItem(versionList[i]);
			}
	}
	
	public void changeNameList(String[] nameList){
		gameNameChooserComboBox.removeAllItems();
		gameNameChooserComboBox.addItem("");
		if(nameList != null)
			for(int i=0; i < nameList.length; i++){
				gameNameChooserComboBox.addItem(nameList[i]);
			}
	}
	
	public void AddActionListenerToButtons(LoadGameFormController gameLoadFormController)
	{
		this.cancelButton.addActionListener((ActionListener) gameLoadFormController);
		this.loadButton.addActionListener((ActionListener) gameLoadFormController);
	}
	
	public void disposeFrame()
	{
		this.gameLoadFormFrame.dispose();
	}
	
	public JPanel getFillOutForm()
	{
		return this.getGameLoadFormPanel();
	}
	
	public void setVisible(boolean visibility)
	{
		gameLoadFormFrame.setVisible(visibility);
	}

	public String[] getVersion() {
		return version;
	}

	public void setVersion(String[] version) {
		this.version = version;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

