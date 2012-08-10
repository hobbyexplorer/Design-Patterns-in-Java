package controller;

import gamemaker.Constants;
import gamemaker.CreateSprite;
import gamemaker.CreateSpritePreviewPanel;
import gamemaker.SpriteDisplayPanel;
import gamemaker.SpritePreviewPanel;
import model.Shape;
import model.Sprite;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.PropertyConfigurator;

public class CreateSpriteController implements ActionListener, ChangeListener, DocumentListener
{
	private CreateSprite createSprite;
	private String buttonText;
	private String spriteName;
	private SpriteDisplayPanelController spriteDisplayPanelController;
	private SpriteDisplayPanel spriteDisplayPanel;
	private boolean isImageSelected = false;
	private CreateSpritePreviewPanel createSpritePreviewPanel;
	private int i;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SpritePreviewPanel.class);
	private int xPosition, yPosition;
	private ArrayList<Sprite> spriteList;
	private Sprite currentSprite;
	private int spriteHeight, spriteWidth, spriteSpeed;
	private String selectedImageFileName;
	private Shape spriteShape;
	private String spriteTransparency;

	private static CreateSpriteController instance;

	public static CreateSpriteController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new CreateSpriteController();
		return instance;
	}

	private CreateSpriteController()
	{
		spriteDisplayPanel = SpriteDisplayPanelController.getInstance().getSpriteDisplayPanel();
		spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite oneSprite : spriteList)
		{
			spriteDisplayPanel.addToSpriteDisplay(oneSprite.getName());
		}
	}

	public void displayCreateSpriteDialog()
	{
		createSpritePreviewPanel = new CreateSpritePreviewPanel();
		createSprite = new CreateSprite(createSpritePreviewPanel);
		addActions();
		PropertyConfigurator.configure(getClass().getResource("../log4j/gamemaker_logger.properties"));
	}

	public void addActions()
	{
		createSprite.getSaveSpriteButton().addActionListener(this);
		for (i = 0; i < Constants.NO_OF_SPRITES; i++)
		{
			createSprite.getImageButton().get(i).addActionListener(this);
		}
		createSprite.getWidthSlider().addChangeListener(this);
		createSprite.getHeightSlider().addChangeListener(this);
		createSprite.getSpeedSlider().addChangeListener(this);
		createSprite.getSpriteNameText().getDocument().addDocumentListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.buttonText = e.getActionCommand();
		if (buttonText.equalsIgnoreCase("Save Sprite"))
		{
			log.info("Save sprite");
			if (isImageSelected == false)
			{
				log.info("Select Image");
			}
			else
			{
				spriteName = createSprite.getSpriteName();
				spriteDisplayPanelController = SpriteDisplayPanelController.getInstance();
				spriteDisplayPanel = spriteDisplayPanelController.getSpriteDisplayPanel();
				spriteDisplayPanel.addToSpriteDisplay(spriteName);
				updateImageTransparency();
				createSpriteObject();
				createSprite.getCreateSprite().dispose();
				GameCreatePanelController.getInstance().enableRuleButton();
				isImageSelected = false;
			}

		}

		else
		{
			isImageSelected = true;
			log.info("ImageSelected");
			createSprite.setAlertLabel("");
			createSprite.getSaveSpriteButton().setEnabled(true);
			checkSpriteName();
			selectedImageFileName = e.getActionCommand();
			updateSliderValues();
			updatePreviewCoordinates();
			createSpritePreviewPanel.updatePreviewPanel(buttonText, xPosition, yPosition, Constants.SPRITE_DEFAULT_WIDTH, Constants.SPRITE_DEFAULT_HEIGHT);
			log.info(buttonText);
			log.info("image selected is" + selectedImageFileName);
		}

	}

	public void createSpriteObject()
	{
		Sprite currentSprite = new Sprite();

		currentSprite.setName(spriteName);
		currentSprite.setWidth(spriteWidth);
		currentSprite.setHeight(spriteHeight);
		currentSprite.setSpeed(spriteSpeed);
		currentSprite.setShape(spriteShape);
		currentSprite.setImagePath(selectedImageFileName);
		spriteList.add(currentSprite);
		GameCreatePanelController.getInstance().getGameBoardModel().addSprite(currentSprite);
	}

	public Sprite getSprite(String spriteName)
	{
		return GameCreatePanelController.getInstance().getGameBoardModel().getSprite(spriteName);
	}

	@Override
	public void stateChanged(ChangeEvent argument)
	{
		updatePreviewCoordinates();
		createSpritePreviewPanel.updatePreviewPanel(buttonText, xPosition, yPosition, createSprite.getWidthSliderValue(), createSprite.getHeightSliderValue());
		createSprite.setSpriteWidthText(Integer.toString(createSprite.getWidthSliderValue()));
		createSprite.setSpriteHeightText(Integer.toString(createSprite.getHeightSliderValue()));
		createSprite.setSpriteSpeedText(Integer.toString(createSprite.getSpeedSlider().getValue()));

	}

	public void updateSliderValues()
	{
		createSprite.setWidthSliderValue(Constants.SLIDER_SIZE_INIT);
		createSprite.setHeightSliderValue(Constants.SLIDER_SIZE_INIT);
	}

	public void updatePreviewCoordinates()
	{
		spriteHeight = createSprite.getHeightSliderValue();
		spriteWidth = createSprite.getWidthSliderValue();
		spriteSpeed = createSprite.getSpeedSlider().getValue();
		xPosition = (Constants.PREVIEW_WIDTH - createSprite.getWidthSliderValue()) / 2;
		yPosition = (Constants.PREVIEW_HEIGHT - createSprite.getHeightSliderValue()) / 2;
		log.info("transparency is" + spriteTransparency);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0)
	{
		checkSpriteName();

	}

	@Override
	public void insertUpdate(DocumentEvent arg0)
	{
		checkSpriteName();

	}

	@Override
	public void removeUpdate(DocumentEvent arg0)
	{
		checkSpriteName();

	}

	public void updateImageTransparency()
	{
		spriteTransparency = createSprite.getSpriteTransparentList().getSelectedItem().toString();
		if (spriteTransparency.equalsIgnoreCase("yes"))
			spriteShape = Shape.TRANSPERENTIMAGE;
		else spriteShape = Shape.IMAGE;
	}

	public void checkSpriteName()
	{
		for (Sprite oneSprite : spriteList)
		{
			if (oneSprite.getName().equalsIgnoreCase(createSprite.getSpriteName()))
			{
				createSprite.getSaveSpriteButton().setEnabled(false);
				createSprite.setAlertLabel("Sprite Name:\"" + createSprite.getSpriteName() + "\"   already exists: please enter a different name");
				log.info("sprite already exist");
			}
			else
			{
				createSprite.getSaveSpriteButton().setEnabled(true);
				createSprite.setAlertLabel("");
			}
		}
	}
}
