package controller;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.GameObject;
import model.Sprite;
import model.event.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import databaseconnector.DatabaseConnector;

/**
 * Class Name: SaveGame. Class responsibility: Contains the logic for saving the
 * game Class Collaborators:
 */
public class SaveGame
{
	private DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder documentBuilder;
	private Document document;
	private String[] spriteProperties = new String[] { "spriteId", "name", "width", "height", "imagePathString", "color", "shape", "angle", "imageRotationAngle", "speed", "objectCount", "randomAngle" };

	private String[] gameObjectProperties = new String[] { "objectId", "xCoordinate", "yCoordinate", "height", "width", "speed", "angle", "shape", "color", "visible", "lastVisibilityToggle", "imageRotationAngle", "initialHeight", "initialWidth", "rotatedFlag" };

	public boolean saveGame(String gameName, long user_Id, long version, boolean isPublished) throws ParserConfigurationException, TransformerException
	{
		
		documentBuilder = docBuilderFactory.newDocumentBuilder();
		document = documentBuilder.newDocument();

		Element rootElement = document.createElement("userCreatedGame");
		document.appendChild(rootElement);
		// gameBoardController = RunGameMaker.test;

		Element spriteCounterElement = document.createElement("spriteCounter");
		spriteCounterElement.setTextContent(Integer.toString(GameBoardController.getInstance().getGameBoardModel().getSpriteCounter()));
		rootElement.appendChild(spriteCounterElement);

		Element eventCounterElement = document.createElement("eventCounter");
		eventCounterElement.setTextContent(Integer.toString(GameBoardController.getInstance().getGameBoardModel().getEventCounter()));
		rootElement.appendChild(eventCounterElement);

		rootElement.appendChild(createAllSpriteElement());
		rootElement.appendChild(createAllEventElement());

		// rootElement.appendChild(createAllGameObjectElement());

		// rootElement.appendChild(createAllEventElement("allEvent",gameBoardController.getEventList()));

		/*TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		Source sourceDocument = new DOMSource(document);
		Result destinationDocument = new StreamResult(file);

		transformer.transform(sourceDocument, destinationDocument);*/
		DatabaseConnector dbConn = DatabaseConnector.getInstance();
         
         dbConn.createConnection();
         System.out.println("gameNAme: "+gameName);
         System.out.println("File: "+convertDocumentToString(document));
         System.out.println("User: "+user_Id);
         System.out.println("version"+String.valueOf(version));
         System.out.println("ispublished"+isPublished);
         
         dbConn.insertGameRecord(gameName, convertDocumentToString(document),user_Id,String.valueOf(version),isPublished);
         //dbConn.selectGames();
         dbConn.shutdown();

		return true;

	}
	
	 public String convertDocumentToString(Document doc)
     {
         try
         {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
         }
         catch(TransformerException ex)
         {
            ex.printStackTrace();
            return null;
         }
     }

	private Element createAllSpriteElement()
	{
		Element allSpriteElement = document.createElement("allSprite");
		List<Sprite> spriteList = GameBoardController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite sprite : spriteList)
		{
			Element spriteElement = document.createElement("sprite");
			for (String propertyName : spriteProperties)
			{
				createNode(spriteElement, propertyName, sprite.getPropertyValue(propertyName));
			}
			// spriteElement.appendChild(createAllEventElement("spriteEvent",sprite.getEventList()));
			spriteElement.appendChild(createAllGameObjectElement("spriteObject", sprite.getGameObjects()));
			allSpriteElement.appendChild(spriteElement);
		}

		return allSpriteElement;
	}

	private Element createAllGameObjectElement(String tagName, List<GameObject> gameObjectList)
	{
		Element allGameObjectElement = document.createElement("allGameObjects");
		for (GameObject gameObject : gameObjectList)
		{
			Element gameObjectElement = document.createElement("gameObject");
			for (String propertyName : gameObjectProperties)
			{
				createNode(gameObjectElement, propertyName, gameObject.getPropertyValue(propertyName));
			}
			allGameObjectElement.appendChild(gameObjectElement);
		}
		return allGameObjectElement;
	}

	private Element createAllEventElement()
	{
		Element allEventElement = document.createElement("allEvent");
		List<Event> eventList = GameBoardController.getInstance().getGameBoardModel().getEventList();
		// ArrayList<Event> eventList = gameBoardController.getEventList();
		for (Event event : eventList)
		{
			Element eventElement = event.getEventInfoXML(this);
			allEventElement.appendChild(eventElement);
		}
		return allEventElement;
	}

	public void createNode(Element parentElement, String nodeName, String nodeText)
	{
		Node node = document.createElement(nodeName);
		node.setTextContent(nodeText);
		parentElement.appendChild(node);
	}

	public Document getDocument()
	{
		return document;
	}
}