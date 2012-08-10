package controller;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.GameMakerParams;
import gamemaker.GameSaveForm;
import gamemaker.actionform.ActionFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.transform.TransformerException;

import model.action.Action;

public class GameSaveFormController implements ActionListener, ChangeListener {

	private GameSaveForm fillOutForm;
	
	Action actionToBeAdded;
	
	public GameSaveFormController() {
		this.fillOutForm = new GameSaveForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Save Game Fillout form controller called.");
	}
	
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		if (Constants.CANCEL_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
		}
		if (Constants.SAVE_BUTTON.equalsIgnoreCase(e.getActionCommand())){
			String chosenNameVersion = fillOutForm.getGameChooserConboBox().getSelectedItem().toString();
			String typedName = fillOutForm.getGameNameFromTextField(); 
			Scanner scanner = new Scanner(chosenNameVersion);
			String gameName = "";
			int gameVersion = -1;
			int userID = GameMakerParams.getInstance().getUserID();
			if(!chosenNameVersion.equals("") && typedName.equals("")){
				scanner.useDelimiter("_");
				gameName = scanner.next();
				gameVersion = Integer.parseInt(scanner.next().substring(1));
			}
			else if(!typedName.equals("") && chosenNameVersion.equals("")){
				String tempName;
				boolean flag = false;
				for(int i = 1; i < fillOutForm.getGameChooserConboBox().getModel().getSize(); i++){
					scanner = new Scanner(fillOutForm.getGameChooserConboBox().getItemAt(i).toString());
					scanner.useDelimiter("_");
					tempName = scanner.next();
					if(tempName.equals(typedName)){
						gameName = tempName;
						gameVersion = Integer.parseInt(scanner.next().substring(1));
						flag = true;
						break;
					}
				}
				if(!flag){
					gameName = typedName;
					gameVersion = 0;
				}
			}
			else {
				scanner.useDelimiter("_");
				gameName = scanner.next();
				gameVersion = Integer.parseInt(scanner.next().substring(1));
			}
			fillOutForm.getPublishCheck().isSelected();
			fillOutForm.getHelpLabel().setText("Game: "+gameName+"|Version: "+gameVersion+"|UserID: "+userID);
			try{
				new SaveGame().saveGame(gameName, userID, ++gameVersion, fillOutForm.getPublishCheck().isSelected());
			}
			catch(Exception te){
				te.printStackTrace();
			}
			fillOutForm.disposeFrame();
		}
	}
	
	public void setVisible(boolean visibility)
	{
		fillOutForm.setVisible(visibility);
		
	}

	
	public GameSaveForm getFillOutForm()
	{
		return fillOutForm;
	}

}
