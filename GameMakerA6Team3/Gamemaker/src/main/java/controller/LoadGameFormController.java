package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gamemaker.Constants;
import gamemaker.GameLoadForm;
import gamemaker.GameMaker;
import gamemaker.GameMakerParams;
import gamemaker.GameSaveForm;
import model.action.Action;

public class LoadGameFormController implements ActionListener, ChangeListener {

	private GameLoadForm fillOutForm;
	
	Action actionToBeAdded;
	
	public LoadGameFormController() {
		this.fillOutForm = new GameLoadForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Load Game Fillout form controller called.");
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Constants.CANCEL_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
		}
		if (Constants.LOAD_BUTTON.equalsIgnoreCase(e.getActionCommand())){
			String tempName = fillOutForm.getGameNameChooserComboBox().getSelectedItem().toString();
			String tempVersion = fillOutForm.getGameVersionChooserComboBox().getSelectedItem().toString();
			String chosenName;
			int chosenVersion;
			int userID;
			int chosenID;
			Scanner nameScanner = new Scanner(tempName);
			if(!tempName.equals("") && !tempVersion.equals("")){
				nameScanner.useDelimiter("\\(");
				chosenName = nameScanner.next();
				chosenID = Integer.parseInt(nameScanner.next().substring(0, 1));
				chosenVersion = Integer.parseInt(tempVersion.trim());
				GameMakerParams.getInstance().setGameID(chosenID);
				GameMakerParams.getInstance().setVersion(chosenVersion);
				userID = GameMakerParams.getInstance().getUserID();
				fillOutForm.getHelpLabel().setText("Game: "+chosenName+"|Version: "+chosenVersion+"|UserID: "+userID+"|"+chosenID);
				try {
					new LoadGame().loadGame(chosenName, chosenID, userID, chosenVersion);
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fillOutForm.disposeFrame();
			}
			else fillOutForm.getHelpLabel().setText("<html>Please spcify the game name as well<br /> as the version to load successfully!</html>");
		}
		
		
	}
	
	public void setVisible(boolean visibility)
	{
		fillOutForm.setVisible(visibility);
		
	}

	
	public GameLoadForm getFillOutForm()
	{
		return fillOutForm;
	}

	
}
