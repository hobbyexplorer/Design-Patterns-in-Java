package controller;

import gamemaker.Constants;
import gamemaker.GameMakerParams;
import gamemaker.LoadSavePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import controller.eventaction.EventActionController;
import databaseconnector.DatabaseConnector;

/**
 * Class Name: LoadSavePanelController. Class responsibility: Contains the
 * actions for the background Selection Class Collaborators:
 */
public class LoadSavePanelController implements ActionListener
{

	private LoadSavePanel loadSavePanel;

	private static LoadSavePanelController instance;
	private long testVersionCount=0;

	public static LoadSavePanelController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new LoadSavePanelController();
		return instance;
	}

	private LoadSavePanelController()
	{
		setLoadSavePanel(new LoadSavePanel());
		getLoadSavePanel().getSave().addActionListener(this);
		getLoadSavePanel().getLoad().addActionListener(this);

	}

	public void setLoadSavePanel(LoadSavePanel loadSavePanel)
	{
		this.loadSavePanel = loadSavePanel;
	}

	public LoadSavePanel getLoadSavePanel()
	{
		return loadSavePanel;
	}

	/*
	 * Method actionPerformed: contains the action performed events for the load
	 * and save
	 */
	public void actionPerformed(ActionEvent actionEvent)
	{

		if (actionEvent.getActionCommand().equals(Constants.LOADSAVEPANEL_SAVE_BUTTON))
		{
			// GameMaker.timerEvent.setPlayMode(false);
			// GameBoardController.getInstance().getGameBoard().setSavevalue(true);
			// GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();

			/*JFileChooser fileChooser = new JFileChooser();

		/*	JFileChooser fileChooser = new JFileChooser();

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML", "xml"));
			int returnVal = fileChooser.showSaveDialog(GameCreatePanelController.getInstance().getGameCreatePanel().getGameCreatePanel());
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file;
				if (fileChooser.getSelectedFile().getName().endsWith(".xml"))
					file = fileChooser.getSelectedFile();
				else file = new File(fileChooser.getSelectedFile() + ".xml");
				// GameMaker.gameCreatePanel.saveGame(file);
				try
				{
					new SaveGame().saveGame(file);
				}
				catch (ParserConfigurationException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (TransformerException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

*/
			GameSaveFormController gameSaveFormController = new GameSaveFormController();
			DatabaseConnector dbConn = DatabaseConnector.getInstance();
	        dbConn.createConnection();
	        //String loadGameData="";// = dbConn.getGameRecord(gameName);
	        ArrayList<String[]> gameNameVersionList = dbConn.getGameRecords(GameMakerParams.getInstance().getUserID());
	        Iterator<String[]> iterator = gameNameVersionList.iterator();
	        dbConn.shutdown();
	        System.out.println(gameNameVersionList.size());
	        gameSaveFormController.getFillOutForm().getGameChooserConboBox().addItem("");
			while(iterator.hasNext()){
				String temp[] = iterator.next();
				System.out.println("Game: "+temp[0]+"Version: "+temp[1]);
				gameSaveFormController.getFillOutForm().getGameChooserConboBox().addItem(temp[0]+"_v"+temp[1]);
			}
			gameSaveFormController.getFillOutForm().setVisible(true);
			

			/*try
            {
				    testVersionCount++;
				   // testVersionCount = 5;
                    new SaveGame().saveGame("spaceinvaders",12,testVersionCount, true);
            }
            catch (ParserConfigurationException e)
            {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            catch (TransformerException e)
            {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }*/

		}
		else if (actionEvent.getActionCommand().equals(Constants.LOADSAVEPANEL_LOAD_BUTTON))
		{
			LoadGameFormController loadGameFormController = new LoadGameFormController();
			DatabaseConnector dbConn = DatabaseConnector.getInstance();
	        dbConn.createConnection();
	        //String loadGameData="";// = dbConn.getGameRecord(gameName);
	        ArrayList<String[]> gameNameVersionList = dbConn.getLoadGameRecords(GameMakerParams.getInstance().getUserID());
	        loadGameFormController.getFillOutForm().setVisible(true);
	        System.out.println(gameNameVersionList.size());
	        Iterator<String[]> iterator = gameNameVersionList.iterator();
	        ArrayList<String> name = new ArrayList<String>(); 
	        String[] version = new String[gameNameVersionList.size()];
	        int i = 0;
	        while(iterator.hasNext()){
	        	String temp[] = iterator.next();
	        	if(!name.contains(temp[1]+"("+temp[0]+")"))
	        		name.add(temp[1]+"("+temp[0]+")");
	        	version[i] = temp[2]+"_#!"+temp[0];
	        	i++;
	        }
	        Iterator<String> nameItr = name.iterator();
	        String[] nameId = new String[name.size()];
	        i = 0;
	        while(nameItr.hasNext()){
	        	nameId[i] = new String(nameItr.next());
	        	i++;
	        }
	        loadGameFormController.getFillOutForm().changeNameList(nameId);
	        loadGameFormController.getFillOutForm().setVersion(version);
	        //loadGameFormController.getFillOutForm().changeVersionList(version);
			// GameMaker.gameCreatePanel.getDeleteSpriteButton().setEnabled(true);
			// GameMaker.gameCreatePanel.getDeleteEventActionButton().setEnabled(true);
			// GameMaker.gameCreatePanel.getAddEventActionButton().setEnabled(true);
			// GameMaker.timerEvent.setPlayMode(false);
			// GameBoardController.getInstance().getGameBoard().setLoadvalue(true);
			// GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();

			/*JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML", "xml"));
			int returnVal = fileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();

				try
				{
					new LoadGame().loadGame(file);
					SpriteDisplayPanelController.getInstance().updateSpriteJList();
					EventActionController.getInstance().updateEventActionJList();
				}
				catch (ParserConfigurationException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (SAXException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// new LoadGame(file).loadGameDetails();
			}*/
			/*
			 try
             {
                     new LoadGame().loadGame("frogger",2,12,2);
                     SpriteDisplayPanelController.getInstance().updateSpriteJList();
                     EventActionController.getInstance().updateEventActionJList();     
             }
             catch (ParserConfigurationException e)
             {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
             }
             catch (SAXException e)
             {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
             }
             catch (IOException e)
             {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
             }*/
             
		}
	}

}
