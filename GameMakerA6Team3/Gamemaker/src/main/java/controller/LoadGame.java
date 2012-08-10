package controller;

import gamemaker.Constants;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Sprite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import databaseconnector.DatabaseConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.GameObject;
import model.action.Action;
import model.action.Blink;
import model.action.BlinkRandom;
import model.action.Bounce;
import model.action.BounceAll;
import model.action.BounceVoid;
import model.action.Contract;
import model.action.Destroy;
import model.action.Expand;
import model.action.GameOver;
import model.action.Move;
import model.action.MoveAll;
import model.action.MoveAlong;
import model.action.MoveDown;
import model.action.MoveLeft;
import model.action.MoveRandom;
import model.action.MoveRight;
import model.action.MoveUp;
import model.action.PlaySound;
import model.action.Revolve;
import model.action.Rotate;
import model.action.SetGameTime;
import model.action.Shoot;
import model.action.ShootRandom;
import model.action.Split;
import model.event.CollisionEvent;
import model.event.CollisionEventWithException;
import model.event.CountEvent;
import model.event.Event;
import model.event.GenerateAtPosition;
import model.event.GenerateRandom;
import model.event.KeyPressedEvent;
import model.event.TimerEvent;

/**
 * Class Name: LoadGame. Class responsibility: Performs the logic for load game
 * Class Collaborators:
 */
public class LoadGame
{

	private DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder documentBuilder;
	private Document document;

	private String[] spriteProperties = new String[] { "spriteId", "name", "width", "height", "imagePathString", "color", "shape", "angle", "imageRotationAngle", "speed", "objectCount", "randomAngle" };

	private String[] gameObjectProperties = new String[] { "objectId", "xCoordinate", "yCoordinate", "height", "width", "speed", "angle", "shape", "color", "visible", "lastVisibilityToggle", "imageRotationAngle", "initialHeight", "initialWidth", "rotatedFlag" };

	private HashMap<Integer, Sprite> spriteMap = new HashMap<Integer, Sprite>();
	private HashMap<String, GameObject> gameObjectMap = new HashMap<String, GameObject>();

	public boolean loadGame(String gameName, long gameId,long userId, int version) throws ParserConfigurationException, SAXException, IOException
	{


		DatabaseConnector dbConn = DatabaseConnector.getInstance();
        dbConn.createConnection();
        String loadGameData= dbConn.loadGameRecord(gameId, version, userId);
        dbConn.selectGames();
        dbConn.shutdown();
		documentBuilder = docBuilderFactory.newDocumentBuilder();
		//document = documentBuilder.parse(file);
		
		document = documentBuilder.parse(new InputSource(new StringReader(loadGameData)));

		// normalize text representation
		document.getDocumentElement().normalize();

		Element spriteCounterElement = (Element) document.getElementsByTagName("spriteCounter").item(0);
		GameBoardController.getInstance().getGameBoardModel().setSpriteCounter(Integer.parseInt(spriteCounterElement.getTextContent()));

		Element eventCounterElement = (Element) document.getElementsByTagName("eventCounter").item(0);
		GameBoardController.getInstance().getGameBoardModel().setEventCounter(Integer.parseInt(eventCounterElement.getTextContent()));

		loadAllSpriteElement();
		loadAllSpriteObjectElement();

		Element allEventElementList = (Element) document.getElementsByTagName("allEvent").item(0);
		Node eventNode = ((Node) allEventElementList.getChildNodes().item(0));
		if (eventNode != null)
			loadAllEventElement(eventNode, GameBoardController.getInstance().getGameBoardModel().getEventList());


		/*
		 * SpriteDisplayPanelController.getInstance().updateSpriteJList();
		 * EventActionController.getInstance().updateEventActionJList();
		 */
		GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();

		return true;

	}

	private void loadAllSpriteElement()
	{
		NodeList spriteElementList = document.getElementsByTagName("sprite");
		// System.out.println("NodeList size: " +
		// spriteElementList.getLength());
		for (int i = 0; i < spriteElementList.getLength(); i++)
		{
			Sprite sprite = new Sprite();
			Node spriteNode = spriteElementList.item(i);
			// System.out.println("node value: " + spriteNode.getNodeName() +
			// " Parent: " + spriteNode.getParentNode() );
			if (spriteNode.getNodeType() == Node.ELEMENT_NODE)
			{

				for (String propertyName : spriteProperties)
				{
					// System.out.println(propertyName + "  node value: " +
					// getNodeValue(spriteNode, propertyName));
					sprite.setPropertyValue(propertyName, getNodeValue(spriteNode, propertyName));
				}
				GameBoardController.getInstance().getGameBoardModel().getSpriteList().add(sprite);
				// gameBoardController.getGameSprites().addSprite(sprite);
				spriteMap.put(sprite.getSpriteId(), sprite);
			}

		}
	}

	private void loadAllSpriteObjectElement()
	{
		NodeList spriteElementList = document.getElementsByTagName("sprite");
		for (int i = 0; i < spriteElementList.getLength(); i++)
		{
			Node spriteNode = spriteElementList.item(i);
			Sprite sprite = spriteMap.get(Integer.valueOf(getNodeValue(spriteNode, "spriteId")));

			if (spriteNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Node allObjectElementList = ((Element) spriteNode).getElementsByTagName("allGameObjects").item(0);
				Node objectNode = ((Node) allObjectElementList.getChildNodes().item(0));
				if (objectNode != null)
					loadSpriteObjectElement(objectNode, sprite);
			}
		}
	}

	public void loadSpriteObjectElement(Node objectNode, Sprite sprite)
	{
		// List<GameObject> gameObjectList = sprite.getGameObjects();
		// System.out.println("Objects being created for: " + sprite.getName());
		GameObject gameObject = null;

		// System.out.println(objectNode.getNodeName());
		gameObject = new GameObject();
		if (objectNode.getNodeType() == Node.ELEMENT_NODE)
		{
			for (String propertyName : gameObjectProperties)
			{
				gameObject.setPropertyValue(propertyName, getNodeValue(objectNode, propertyName));
			}
			gameObject.setImage(sprite.getImage());

			if (gameObject != null)
				sprite.addGameObject(gameObject);
		}
		if (objectNode.getNextSibling() != null)
			loadSpriteObjectElement(objectNode.getNextSibling(), sprite);
	}

	private void loadAllEventElement(Node eventNode, List<Event> eventList)
	{

		Event event = null;
		if (eventNode.getNodeType() == Node.ELEMENT_NODE)
		{
			if (getNodeValue(eventNode, "eventString").equals(Constants.EVENT_KEY_PRESSED))
			{
				//System.out.println("key pressed event found");
				int eventId = Integer.parseInt(getNodeValue(eventNode, "eventId"));
				int keyCode = Integer.parseInt(getNodeValue(eventNode, "eventKeyCode"));
				String eventActionString = getNodeValue(eventNode, "eventActionString");
				event = new KeyPressedEvent(spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "eventSprite"))), keyCode);
				event.setEventId(eventId);
				event.setEventString(eventActionString);
			}
			else if (getNodeValue(eventNode, "eventString").equals(Constants.EVENT_COLLISION))
			{
				//System.out.println("Collision event found");
				int eventId = Integer.parseInt(getNodeValue(eventNode, "eventId"));
				String eventActionString = getNodeValue(eventNode, "eventActionString");
				event = new CollisionEvent(spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "spriteOne"))), spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "spriteTwo"))));
				event.setEventId(eventId);
				event.setEventString(eventActionString);
			}
			else if (getNodeValue(eventNode, "eventString").equals(Constants.EVENT_COLLISION_WITH_EXCEPTION))
			{
				//System.out.println("Collision event with exception found");
				int eventId = Integer.parseInt(getNodeValue(eventNode, "eventId"));
				String eventActionString = getNodeValue(eventNode, "eventActionString");
				ArrayList<Sprite> exceptionSpriteList = new ArrayList<Sprite>();
				NodeList eventChildList = eventNode.getChildNodes();
				Node eventChildNode = null;
				for (int i = 0; i < eventChildList.getLength(); i++)
				{
					eventChildNode = eventChildList.item(i);
					if (eventChildNode.getNodeName().equals("allExceptionSprite"))
						break;
				}

				if (eventChildNode != null)
				{
					Element element = (Element) eventChildNode;

					NodeList taglist = element.getElementsByTagName("spriteId");
					Element tagElement = (Element) taglist.item(0);
					for (int i = 0; i < taglist.getLength(); i++)
					{

						tagElement = (Element) taglist.item(i);
						if (tagElement != null)
						{
							NodeList textTagList = tagElement.getChildNodes();

							if (textTagList.item(0) != null)
								System.out.println(((Node) textTagList.item(0)).getNodeValue().trim());
							exceptionSpriteList.add(spriteMap.get(Integer.parseInt(((Node) textTagList.item(0)).getNodeValue().trim())));
						}
					}

				}

				event = new CollisionEventWithException(spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "spriteOne"))), spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "spriteTwo"))), exceptionSpriteList);
				event.setEventId(eventId);
				event.setEventString(eventActionString);

			}
			else if (getNodeValue(eventNode, "eventString").equals(Constants.EVENT_TIMER))
			{
				//System.out.println("timer event found");
				int eventId = Integer.parseInt(getNodeValue(eventNode, "eventId"));
				String eventActionString = getNodeValue(eventNode, "eventActionString");
				event = new TimerEvent(spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "eventSprite"))));
				event.setEventId(eventId);
				event.setEventString(eventActionString);
			}
			else if (getNodeValue(eventNode, "eventString").equals(Constants.EVENT_COUNT))
			{
				//System.out.println("count event found");
				int eventId = Integer.parseInt(getNodeValue(eventNode, "eventId"));
				String eventActionString = getNodeValue(eventNode, "eventActionString");
				event = new CountEvent(spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "eventSprite"))));
				event.setEventId(eventId);
				event.setEventString(eventActionString);
			}
			else if (getNodeValue(eventNode, "eventString").equals(Constants.EVENT_GENERATE_AT_POSITION))
			{
				//System.out.println("generate at position event found");
				int eventId = Integer.parseInt(getNodeValue(eventNode, "eventId"));
				int x = Integer.parseInt(getNodeValue(eventNode, "xCoordinate"));
				int y = Integer.parseInt(getNodeValue(eventNode, "yCoordinate"));
				int delay = Integer.parseInt(getNodeValue(eventNode, "delay"));
				int startTime = Integer.parseInt(getNodeValue(eventNode, "startTime"));
				int maxTime = Integer.parseInt(getNodeValue(eventNode, "maxTime"));
				int count = Integer.parseInt(getNodeValue(eventNode, "count"));
				String eventActionString = getNodeValue(eventNode, "eventActionString");
				event = new GenerateAtPosition(spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "eventSprite"))), x, y, count, startTime, delay, maxTime);
				event.setEventId(eventId);
				event.setEventString(eventActionString);
			}
			else if (getNodeValue(eventNode, "eventString").equals(Constants.EVENT_GENERATE_RANDOM))
			{
				//System.out.println("generate at random event found");
				int eventId = Integer.parseInt(getNodeValue(eventNode, "eventId"));
				String eventActionString = getNodeValue(eventNode, "eventActionString");
				int delay = Integer.parseInt(getNodeValue(eventNode, "delay"));
				int startTime = Integer.parseInt(getNodeValue(eventNode, "startTime"));
				int maxTime = Integer.parseInt(getNodeValue(eventNode, "maxTime"));
				int count = Integer.parseInt(getNodeValue(eventNode, "count"));
				int sides[];
				sides = new int[5];
				int index = 0;
				NodeList eventChildList = eventNode.getChildNodes();
				Node eventChildNode = null;
				for (int i = 0; i < eventChildList.getLength(); i++)
				{
					eventChildNode = eventChildList.item(i);
					if (eventChildNode.getNodeName().equals("allSides"))
						;
					break;
				}
				Node side = null;
				if (eventChildNode != null)
					side = eventChildNode.getFirstChild();

				while (side != null)
				{
					sides[index++] = Integer.parseInt(side.getNodeValue());
					side = side.getNextSibling();
					//System.out.println("Index: " + index + " side: " + sides[index]);
				}

				event = new GenerateRandom(spriteMap.get(Integer.parseInt(getNodeValue(eventNode, "eventSprite"))), sides, count, startTime, delay, maxTime);
				event.setEventId(eventId);
				event.setEventString(eventActionString);
			}

		}

		/*
		 * if(eventNode.getNodeName().equals("keyListenerEvent")){ GameObject
		 * actionObject = gameObjectMap.get(getNodeValue(eventNode,
		 * "actionObject")); int eventId =
		 * Integer.parseInt(getNodeValue(eventNode, "eventId")); int keyCode =
		 * Integer.parseInt(getNodeValue(eventNode, "eventKeyCode")); String
		 * eventString = getNodeValue(eventNode, "eventString"); event = new
		 * KeyListenerEvent(actionObject, eventId, keyCode, eventString); }else
		 * if(eventNode.getNodeName().equals("collisionEvent")){ GameObject
		 * objectOne = gameObjectMap.get(getNodeValue(eventNode, "objectOne"));
		 * GameObject objectTwo = gameObjectMap.get(getNodeValue(eventNode,
		 * "objectTwo")); int eventId = Integer.parseInt(getNodeValue(eventNode,
		 * "eventId")); String eventString = getNodeValue(eventNode,
		 * "eventString"); event = new CollisionEvent(eventId, objectOne,
		 * objectTwo, eventString); }else
		 * if(eventNode.getNodeName().equals("updateEvent")){ GameObject
		 * actionObject = gameObjectMap.get(getNodeValue(eventNode,
		 * "actionObject")); int eventId =
		 * Integer.parseInt(getNodeValue(eventNode, "eventId")); String
		 * eventString = getNodeValue(eventNode, "eventString"); event = new
		 * UpdateEvent(actionObject, eventId, eventString); }
		 */
		if (event != null)
		{
			Element allActionElementList = (Element) ((Element) eventNode).getElementsByTagName("allAction").item(0);
			if (allActionElementList != null)
			{
				Node actionNode = ((Node) allActionElementList.getChildNodes().item(0));
				loadAllActionElement(event, actionNode);
			}
			eventList.add(event);
		}

		if (eventNode.getNextSibling() != null)
			loadAllEventElement(eventNode.getNextSibling(), eventList);

	}

	private void loadAllActionElement(Event event, Node actionNode)
	{

		Action action = null;
		if (actionNode.getNodeType() == Node.ELEMENT_NODE)
		{

			if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_BLINK))
			{
				int blinkDelay = Integer.parseInt(getNodeValue(actionNode, "blinkDelay"));
				action = new Blink(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), blinkDelay);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_BLINK_RANDOM))
			{
				int blinkDelay = Integer.parseInt(getNodeValue(actionNode, "blinkDelay"));
				int blinkFor = Integer.parseInt(getNodeValue(actionNode, "blinkFor"));
				action = new BlinkRandom(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), blinkDelay, blinkFor);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_BOUNCE))
			{
				action = new Bounce(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_BOUNCE_ALL))
			{
				action = new BounceAll(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_BOUNCE_VOID))
			{
				action = new BounceVoid(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_CONTRACT))
			{
				int contractBy = Integer.parseInt(getNodeValue(actionNode, "contractBy"));
				action = new Contract(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), contractBy);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_DESTROY))
			{
				action = new Destroy(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_EXPAND))
			{
				int expandBy = Integer.parseInt(getNodeValue(actionNode, "expandBy"));
				action = new Expand(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), expandBy);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_GAME_OVER))
			{
				String message = getNodeValue(actionNode, "message");
				Boolean conditionApplied;
				if (getNodeValue(actionNode, "conditionApplied").equals("true"))
					conditionApplied = true;
				else conditionApplied = false;

				//System.out.println("Condition applied: " + conditionApplied);
				action = new GameOver(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), message, conditionApplied);
				if (conditionApplied)
				{
					int conditionCount = Integer.parseInt(getNodeValue(actionNode, "conditionCount"));
					Boolean greaterThan;
					if (getNodeValue(actionNode, "greaterThan").equals("true"))
						greaterThan = true;
					else greaterThan = false;

					Boolean lessThan;
					if (getNodeValue(actionNode, "lessThan").equals("true"))
						lessThan = true;
					else lessThan = false;

					Boolean equalTo;
					if (getNodeValue(actionNode, "equalTo").equals("true"))
						equalTo = true;
					else equalTo = false;

					((GameOver) action).setCondition(conditionCount, greaterThan, lessThan, equalTo);
				}
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE))
			{
				int angle = Integer.parseInt(getNodeValue(actionNode, "angle"));
				action = new Move(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), angle);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE_ALL))
			{
				int angle = Integer.parseInt(getNodeValue(actionNode, "angle"));
				int speed = Integer.parseInt(getNodeValue(actionNode, "speed"));
				action = new MoveAll(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), angle, speed);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE_ALONG))
			{
				action = new MoveAlong(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE_DOWN))
			{
				Boolean rotateImage;
				if (getNodeValue(actionNode, "rotateImage").equals("true"))
					rotateImage = true;
				else rotateImage = false;
				// Boolean rotateImage =
				// Boolean.getBoolean(getNodeValue(actionNode, "rotateImage"));
				action = new MoveDown(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), rotateImage);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE_LEFT))
			{
				Boolean rotateImage;
				if (getNodeValue(actionNode, "rotateImage").equals("true"))
					rotateImage = true;
				else rotateImage = false;
				action = new MoveLeft(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), rotateImage);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE_RIGHT))
			{
				Boolean rotateImage;
				if (getNodeValue(actionNode, "rotateImage").equals("true"))
					rotateImage = true;
				else rotateImage = false;
				action = new MoveRight(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), rotateImage);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE_UP))
			{
				Boolean rotateImage;
				if (getNodeValue(actionNode, "rotateImage").equals("true"))
					rotateImage = true;
				else rotateImage = false;
				action = new MoveUp(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), rotateImage);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_MOVE_RANDOM))
			{
				action = new MoveRandom(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_SOUND))
			{
				String soundFileName = getNodeValue(actionNode, "soundFileName");
				action = new PlaySound(soundFileName);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_REVOLVE))
			{
				action = new Revolve(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_ROTATE))
			{
				int angle = Integer.parseInt(getNodeValue(actionNode, "angle"));
				action = new Rotate(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), angle);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_SET_GAME_TIME))
			{
				int gametime = Integer.parseInt(getNodeValue(actionNode, "gametime"));
				String message = getNodeValue(actionNode, "message");
				action = new SetGameTime(gametime, message);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_SHOOT))
			{
				action = new Shoot(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "shootSprite"))));
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_SHOOT_RANDOM))
			{
				int shootDelay = Integer.parseInt(getNodeValue(actionNode, "shootDelay"));
				int angle = Integer.parseInt(getNodeValue(actionNode, "angle"));
				int speed = Integer.parseInt(getNodeValue(actionNode, "speed"));
				action = new ShootRandom(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "shootSprite"))), shootDelay, angle, speed);
			}
			else if (getNodeValue(actionNode, "actionString").equals(Constants.ACTION_SPLIT))
			{
				action = new Split(spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "actionsprite"))), spriteMap.get(Integer.parseInt(getNodeValue(actionNode, "splitSprite"))));
			}

		}

		if (action != null)
			event.addAction(action);

		if (actionNode.getNextSibling() != null)
			loadAllActionElement(event, actionNode.getNextSibling());
	}

	public String getNodeValue(Node node, String tagName)
	{
		Element element = (Element) node;

		NodeList taglist = element.getElementsByTagName(tagName);
		Element tagElement = (Element) taglist.item(0);
		if (tagElement != null)
		{
			NodeList textTagList = tagElement.getChildNodes();

			if (textTagList.item(0) != null)
				return ((Node) textTagList.item(0)).getNodeValue().trim();
		}
		return null;
	}

	/*
	 * // The String element private String element; // The position of x,
	 * position of y, speed of x, speed of y. private String spriteName,
	 * spriteType, spriteWidth, spriteHeight, spriteX, spriteY, spriteXSpeed,
	 * spriteYSpeed, soundKey, soundValue, conditionKey, conditionValue,
	 * backGround;
	 * 
	 * // The event action. private Vector<String> eventAction = new
	 * Vector<String>();
	 * 
	 * // The event. private Vector<String> event = new Vector<String>();
	 * 
	 * // The sound file to event map private HashMap<String, String>
	 * soundFileToEventMap = new HashMap<String, String>();
	 * 
	 * // The victory condition map. private HashMap<String, Integer>
	 * victoryConditionMap = new HashMap<String, Integer>();
	 * 
	 * // The loaded file private File loadFile;
	 * 
	 * // The sprite list. // private ArrayList<SpriteObject> spriteList = new
	 * ArrayList<SpriteObject>();
	 * 
	 * // The spriteobject. // private SpriteObject spriteobject = new
	 * SpriteObject(); // Declaring the boolean values // The is sprite name.
	 * private boolean isSpriteName;
	 * 
	 * // The is sprite type. private boolean isSpriteType;
	 * 
	 * // The is sprite x. private boolean isSpriteX;
	 * 
	 * // The is sprite y. private boolean isSpriteY;
	 * 
	 * // The is sprite width. private boolean isSpriteWidth;
	 * 
	 * // The is sprite height. private boolean isSpriteHeight;
	 * 
	 * // The is sprite x speed. private boolean isSpriteXSpeed;
	 * 
	 * // The is sprite y speed. private boolean isSpriteYSpeed;
	 * 
	 * // The is event action. private boolean isEventAction;
	 * 
	 * // The is event. private boolean isEvent;
	 * 
	 * // The is sound map key. private boolean isSoundMapKey;
	 * 
	 * // The is sound map value. private boolean isSoundMapValue;
	 * 
	 * // The is condition map key. private boolean isConditionMapKey;
	 * 
	 * // The is condition map value. private boolean isConditionMapValue;
	 * 
	 * // The is back ground value.
	 * 
	 * @SuppressWarnings("unused") private boolean isBackGroundValue;
	 */
	/*
	 * // Constructor LoadGame: Instantiates a new load game. public
	 * LoadGame(File file) { this.loadFile = file; }
	 * 
	 * Method loadGameDetails : Loading the game details public void
	 * loadGameDetails() { try { SAXParserFactory factory =
	 * SAXParserFactory.newInstance(); SAXParser saxParser =
	 * factory.newSAXParser(); DefaultHandler handler = new DefaultHandler() {
	 * // method called at start of document element public void
	 * startElement(String uri, String localName, String qName, Attributes
	 * attributes) throws SAXException { // Check if game component and set
	 * element if (qName.equalsIgnoreCase("SpriteObject")) { element = qName; }
	 * else if (qName.equalsIgnoreCase("soundMap")) { element = qName; } else if
	 * (qName.equalsIgnoreCase("conditionMap")) { element = qName; } else if
	 * (qName.equalsIgnoreCase("backGround")) { element = qName; } // Check if
	 * game component parameter and set flag if
	 * (qName.equalsIgnoreCase("spriteName")) { isSpriteName = true; } else if
	 * (qName.equalsIgnoreCase("spriteType")) { isSpriteType = true; } else if
	 * (qName.equalsIgnoreCase("spriteWidth")) { isSpriteWidth = true; } else if
	 * (qName.equalsIgnoreCase("spriteHeight")) { isSpriteHeight = true; } else
	 * if (qName.equalsIgnoreCase("spriteX")) { isSpriteX = true; } else if
	 * (qName.equalsIgnoreCase("spriteY")) { isSpriteY = true; } else if
	 * (qName.equalsIgnoreCase("spriteXSpeed")) { isSpriteXSpeed = true; } else
	 * if (qName.equalsIgnoreCase("spriteYSpeed")) { isSpriteYSpeed = true; }
	 * else if (qName.equalsIgnoreCase("eventAction")) { isEventAction = true; }
	 * else if (qName.equalsIgnoreCase("event")) { isEvent = true; } else if
	 * (qName.equalsIgnoreCase("soundKey")) { isSoundMapKey = true; } else if
	 * (qName.equalsIgnoreCase("soundValue")) { isSoundMapValue = true; } else
	 * if (qName.equalsIgnoreCase("conditionKey")) { isConditionMapKey = true; }
	 * else if (qName.equalsIgnoreCase("conditionValue")) { isConditionMapValue
	 * = true; } else if (qName.equalsIgnoreCase("backGroundValue")) {
	 * isBackGroundValue = true; } }
	 * 
	 * // method called at the end of document element public void
	 * endElement(String uri, String localName, String qName) throws
	 * SAXException { if (qName.equalsIgnoreCase("soundMap")) {
	 * soundFileToEventMap.put(soundKey, soundValue); element = "SpriteObject";
	 * } if (qName.equalsIgnoreCase("conditionMap")) {
	 * victoryConditionMap.put(conditionKey, new Integer(conditionValue)); } if
	 * (qName.equalsIgnoreCase("SpriteObject")) { //
	 * spriteobject.setEventActionVector(eventAction); //
	 * spriteobject.setEventVector(event); //
	 * spriteobject.setSoundFileToEventMap(soundFileToEventMap); //
	 * spriteList.add(spriteobject); // spriteobject = new SpriteObject();
	 * eventAction = new Vector<String>(); event = new Vector<String>();
	 * soundFileToEventMap = new HashMap<String, String>(); } if
	 * (qName.equalsIgnoreCase("backGround")) { //
	 * GameMaker.gameObject.setBackGround(backGround); if (backGround != null)
	 * GameBoardController
	 * .getInstance().getGameBoard().setImageToBackground(backGround); } if
	 * (qName.equalsIgnoreCase("Game")) { //
	 * GameMaker.spriteList.setObjectList(spriteList); //
	 * GameMaker.spriteList.cloneObjectList(); //
	 * GameMaker.gameObject.setVictoryConditionMap(victoryConditionMap);
	 * GameCreatePanelController
	 * .getInstance().getGameCreatePanel().reloadDisplayList();
	 * GameBoardController
	 * .getInstance().getGameBoard().getGameBoard().repaint(); } }
	 * 
	 * // method called with text contents in between start and end // tags of
	 * XML document element public void characters(char ch[], int start, int
	 * length) throws SAXException { if (element.equalsIgnoreCase("soundMap")) {
	 * if (isSoundMapKey) { soundKey = new String(ch, start, length);
	 * isSoundMapKey = false; } else if (isSoundMapValue) { soundValue = new
	 * String(ch, start, length); isSoundMapValue = false; } } if
	 * (element.equalsIgnoreCase("conditionMap")) { if (isConditionMapKey) {
	 * conditionKey = new String(ch, start, length); isConditionMapKey = false;
	 * } else if (isConditionMapValue) { conditionValue = new String(ch, start,
	 * length); isConditionMapValue = false; } } if
	 * (element.equalsIgnoreCase("SpriteObject")) { if (isSpriteName) {
	 * spriteName = new String(ch, start, length); //
	 * spriteobject.setSpriteName(spriteName); isSpriteName = false; } else if
	 * (isSpriteType) { spriteType = new String(ch, start, length); //
	 * spriteobject.setSpriteType(spriteType); //
	 * spriteobject.setImage(spriteobject.generateSpriteImage(spriteType));
	 * isSpriteType = false; } else if (isSpriteX) { spriteX = new String(ch,
	 * start, length); // spriteobject.setSpriteX(Integer.parseInt(spriteX));
	 * isSpriteX = false; } else if (isSpriteY) { spriteY = new String(ch,
	 * start, length); // spriteobject.setSpriteY(Integer.parseInt(spriteY));
	 * isSpriteY = false; } else if (isSpriteXSpeed) { spriteXSpeed = new
	 * String(ch, start, length); //
	 * spriteobject.setSpriteXSpeed(Integer.parseInt(spriteXSpeed));
	 * isSpriteXSpeed = false; } else if (isSpriteYSpeed) { spriteYSpeed = new
	 * String(ch, start, length); //
	 * spriteobject.setSpriteYSpeed(Integer.parseInt(spriteYSpeed));
	 * isSpriteYSpeed = false; } else if (isSpriteWidth) { spriteWidth = new
	 * String(ch, start, length); //
	 * spriteobject.setSpriteWidth(Integer.parseInt(spriteWidth)); isSpriteWidth
	 * = false; } else if (isSpriteHeight) { spriteHeight = new String(ch,
	 * start, length); //
	 * spriteobject.setSpriteHeight(Integer.parseInt(spriteHeight));
	 * isSpriteHeight = false; } else if (isEventAction) { eventAction.add(new
	 * String(ch, start, length)); isEventAction = false; } else if (isEvent) {
	 * event.add(new String(ch, start, length)); isEvent = false; } } if
	 * (element.equalsIgnoreCase("backGround")) { backGround = new String(ch,
	 * start, length); } } }; saxParser.parse(loadFile.getAbsolutePath(),
	 * handler); GameMaker.logger.logInfo(this.getClass().getName() +
	 * " - Game Load successful."); } catch (Exception e) {
	 * GameMaker.logger.logError(this.getClass().getName() +
	 * " - Error on Load - " + e.toString()); e.printStackTrace(); } }
	 */

}