package gamemaker;

import gamemaker.Constants;
import gamemaker.CreateSpritePreviewPanel;
import gamemaker.ModifiedFlowLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class CreateSprite
{
	private JFrame createSprite;

	// The sprite name label.
	private JLabel spriteNameLabel;	

	// The preview panel of create sprite panel
	private CreateSpritePreviewPanel createSpritePreviewPanel;

	// The labels to display information and alret
	private JLabel infoLabel, alertLabel;

	// The panel to display the sprite collection
	private JPanel spriteCollection;

	// The scroll bar for sprite Collection
	private JScrollPane scrollPane;

	// The sprite height label.
	private JLabel spriteHeightLabel;

	// The sprite width label.
	private JLabel spriteWidthLabel;

	// The sprite x speed label.
	private JLabel spriteSpeedLabel;

	// The sprite width text.
	private JLabel spriteWidthText;

	// The sprite height text.
	private JLabel spriteHeightText;

	// The sprite x speed text.
	private JLabel spriteSpeedText;
	
	// The label for the transparency of image
	private JLabel spriteTransparentLabel;

	// The sprite name text.
	private JTextField spriteNameText;

	// The width slider.
	private JSlider widthSlider;

	// The height slider. */
	private JSlider heightSlider;

	// The x speed slider. */
	private JSlider speedSlider;

	// The save sprite button
	private JButton saveSpriteButton;

	// The list of image buttons
	private ArrayList<JButton> imageButton;
	
	// The combo box for sprite transparency option
	private JComboBox spriteTransparentList;
	
    // The custom flow layout for sprite list panel
	private ModifiedFlowLayout modifiedFlowLayout;

	// The image icons for buttons
	private ImageIcon imageIcon, imageIconResized;
	private Image image;
	
	private ArrayList<String> transparentOptions;

	private int i;

	public CreateSprite(CreateSpritePreviewPanel createSpritePreviewPanel){	
		this.createSpritePreviewPanel = createSpritePreviewPanel;
		createSpriteFrame();
		createObjects();
		placeObjects();
		createImageButtons();
		addImages();
		addImagePanel();
	}

	public void createSpriteFrame(){
		createSprite = new JFrame();
		createSprite.setBounds(10, 50, Constants.CREATE_SPRITE_PANEL_WIDTH, Constants.CREATE_SPRITE_PANEL_HEIGHT);
		createSprite.setLayout(null);		
		createSprite.setBackground(Color.white);		
		createSprite.setVisible(true);
		createSprite.setResizable(false);
	}

	public void createObjects(){	
		widthSlider = new JSlider(JSlider.HORIZONTAL, Constants.SLIDER_MIN, Constants.SLIDER_SIZE_MAX, Constants.SLIDER_SIZE_INIT);
		heightSlider = new JSlider(JSlider.HORIZONTAL, Constants.SLIDER_MIN, Constants.SLIDER_SIZE_MAX, Constants.SLIDER_SIZE_INIT);
		speedSlider = new JSlider(JSlider.HORIZONTAL, Constants.SLIDER_MIN, Constants.SLIDER_MAXIMUM_SPEED, Constants.SLIDER_SPEED_INIT);

		spriteNameText = new JTextField("");		

		infoLabel = new JLabel("Please select the image and click on \"Save Sprite\" to create sprite");
		alertLabel = new JLabel("Please select sprite");
		spriteWidthText = new JLabel(String.valueOf(Constants.SLIDER_SIZE_INIT), Constants.TEXT_COLUMN_SIZE);
		spriteHeightText = new JLabel(String.valueOf(Constants.SLIDER_SIZE_INIT), Constants.TEXT_COLUMN_SIZE);
		spriteSpeedText = new JLabel(String.valueOf(Constants.SLIDER_SPEED_INIT), Constants.TEXT_COLUMN_SIZE);
		spriteNameLabel = new JLabel(Constants.SPRITEPANEL_LABEL_SPRITE_NAME);
		spriteWidthLabel = new JLabel(Constants.SPRITEPANEL_LABEL_SPRITE_WIDTH);
		spriteHeightLabel = new JLabel(Constants.SPRITEPANEL_LABEL_SPRITE_HEIGHT);
		spriteSpeedLabel = new JLabel(Constants.SPRITEPANEL_LABEL_SPRITE_SPEED);		

		saveSpriteButton = new JButton("Save Sprite");
		saveSpriteButton.setEnabled(false);

		spriteCollection = new JPanel();
		modifiedFlowLayout = new ModifiedFlowLayout();
		spriteCollection.setLayout(modifiedFlowLayout);
		
		transparentOptions = new ArrayList<String>();
		transparentOptions.add("no");
		transparentOptions.add("yes");
		spriteTransparentLabel = new JLabel("Transparent");
		spriteTransparentList = new JComboBox(transparentOptions.toArray());
	}	

	public void placeObjects(){
		infoLabel.setBounds(10,10,500,20);
		createSprite.add(infoLabel);

		spriteNameLabel.setBounds(50,50,100,20);
		createSprite.add(spriteNameLabel);		
		spriteNameText.setBounds(150,50,100,20);
		createSprite.add(spriteNameText);

		spriteWidthLabel.setBounds(10,270,100,20);
		createSprite.add(spriteWidthLabel);		
		spriteWidthText.setBounds(70,270,40,20);
		createSprite.add(spriteWidthText);		
		widthSlider.setBounds(120,270,100,20);
		createSprite.add(widthSlider);

		spriteHeightLabel.setBounds(10,300,100,20);
		createSprite.add(spriteHeightLabel);		
		spriteHeightText.setBounds(70,300,40,20);
		createSprite.add(spriteHeightText);		
		heightSlider.setBounds(120,300,100,20);
		createSprite.add(heightSlider);

		spriteSpeedLabel.setBounds(10,330,100,20);
		createSprite.add(spriteSpeedLabel);		
		spriteSpeedText.setBounds(70,330,40,20);
		createSprite.add(spriteSpeedText);		
		speedSlider.setBounds(120,330,100,20);
		createSprite.add(speedSlider);
		
		spriteTransparentLabel.setBounds(10,360,100,20);
		createSprite.add(spriteTransparentLabel);		
		spriteTransparentList.setBounds(130,360,60,20);
		createSprite.add(spriteTransparentList);

		createSpritePreviewPanel.setBounds(250, 240, Constants.PREVIEW_WIDTH, Constants.PREVIEW_HEIGHT);
		createSprite.add(createSpritePreviewPanel);

		saveSpriteButton.setBounds(160,450,100,20);
		createSprite.add(saveSpriteButton);	

		alertLabel.setBounds(10, 470, 500, 20);
		createSprite.add(alertLabel);
		alertLabel.setForeground(Color.red);
	}


	public JComboBox getSpriteTransparentList()
	{
		return spriteTransparentList;
	}

	public void setSpriteTransparentList(JComboBox spriteTransparentList)
	{
		this.spriteTransparentList = spriteTransparentList;
	}

	public ImageIcon createImageIcon(String imageName){
	//	imageIcon = new ImageIcon(System.getProperty("user.dir") + "/src/image/" + imageName + ".gif");
		imageIcon = new ImageIcon(getClass().getClassLoader().getResource("image/" + imageName + ".gif"));
		image = imageIcon.getImage().getScaledInstance(30, 30, 0);
		imageIconResized = new ImageIcon(image);
		return imageIconResized;

	}

	public void createImageButtons(){
		imageButton = new ArrayList<JButton>();
		imageButton.add(new JButton("ball", createImageIcon("ball")));
		imageButton.add(new JButton("rectangle", createImageIcon("rectangle")));
		imageButton.add(new JButton("asteroids",createImageIcon("asteroids")));
		imageButton.add(new JButton("aircraft",createImageIcon("aircraft")));
		imageButton.add(new JButton("spaceinvader",createImageIcon("spaceinvader")));
		imageButton.add(new JButton("android", createImageIcon("android")));
		imageButton.add(new JButton("ship", createImageIcon("ship")));
		imageButton.add(new JButton("bluestage", createImageIcon("bluestage")));
		imageButton.add(new JButton("blackstage", createImageIcon("blackstage")));
		imageButton.add(new JButton("bullet", createImageIcon("bullet")));
		imageButton.add(new JButton("car1", createImageIcon("car1")));
		imageButton.add(new JButton("car2", createImageIcon("car2")));
		imageButton.add(new JButton("car3", createImageIcon("car3")));
		imageButton.add(new JButton("car4", createImageIcon("car4")));
		imageButton.add(new JButton("car5", createImageIcon("car5")));
		imageButton.add(new JButton("enemy1", createImageIcon("enemy1")));
		imageButton.add(new JButton("enemy2", createImageIcon("enemy2")));
		imageButton.add(new JButton("enemy3", createImageIcon("enemy3")));
		imageButton.add(new JButton("frog", createImageIcon("frog")));
		imageButton.add(new JButton("froggerstage", createImageIcon("froggerstage")));
		imageButton.add(new JButton("green", createImageIcon("green")));
		imageButton.add(new JButton("greenstage", createImageIcon("greenstage")));
		imageButton.add(new JButton("longlog", createImageIcon("longlog")));
		imageButton.add(new JButton("shortlog", createImageIcon("shortlog")));
		imageButton.add(new JButton("lose", createImageIcon("lose")));
		imageButton.add(new JButton("shield", createImageIcon("shield")));
		imageButton.add(new JButton("tank", createImageIcon("tank")));
		imageButton.add(new JButton("turtles", createImageIcon("turtles")));
		imageButton.add(new JButton("winstage", createImageIcon("winstage")));		
	}


	public void addImages(){	
		for( i=0; i < Constants.NO_OF_SPRITES; i++ ){
			imageButton.get(i).setSize(40, 40);
			imageButton.get(i).setFont(new Font("sansserif",Font.BOLD,0));
			spriteCollection.add(imageButton.get(i));
		}
	}

	public void addImagePanel(){
		scrollPane = new JScrollPane(spriteCollection);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		scrollPane.setBounds(10, 80, 450, 140);
		scrollPane.setVisible(false);
		createSprite.getContentPane().add(scrollPane);
		 scrollPane.setVisible(true);
	}

	/* Getter and Setters for the create sprite class */

	public JButton getSaveSpriteButton()
	{
		return saveSpriteButton;
	}

	public void setSaveSpriteButton(JButton saveSpriteButton)
	{
		this.saveSpriteButton = saveSpriteButton;
	}

	public JLabel getSpriteWidthText()
	{
		return spriteWidthText;
	}

	public JLabel getAlertLabel()
	{
		return alertLabel;
	}

	public void setAlertLabel(String alertLabel)
	{
		this.alertLabel.setText(alertLabel);
	}

	public void setSpriteWidthText(String spriteWidthText)
	{
		this.spriteWidthText.setText(spriteWidthText);
	}

	public JLabel getSpriteHeightText()
	{
		return spriteHeightText;
	}

	public void setSpriteHeightText(String spriteHeightText)
	{
		this.spriteHeightText.setText(spriteHeightText);
	}

	public JLabel getSpriteSpeedText()
	{
		return spriteSpeedText;
	}

	public void setSpriteSpeedText(String spriteSpeedText)
	{
		this.spriteSpeedText.setText(spriteSpeedText);
	}

	public JSlider getSpeedSlider()
	{
		return speedSlider;
	}

	public void setSpeedSlider(JSlider speedSlider)
	{
		this.speedSlider = speedSlider;
	}

	public int getWidthSliderValue()
	{
		return widthSlider.getValue();
	}

	public void setWidthSliderValue(int widthSlider)
	{
		this.widthSlider.setValue(widthSlider);
	}

	public JSlider getWidthSlider(){
		return this.widthSlider;
	}


	public JSlider getHeightSlider(){
		return this.heightSlider;
	}

	public int getHeightSliderValue()
	{
		return heightSlider.getValue();
	}

	public void setHeightSliderValue(int heightSlider)
	{
		this.heightSlider.setValue(heightSlider);
	}

	public CreateSpritePreviewPanel getCreateSpritePreviewPanel()
	{
		return createSpritePreviewPanel;
	}

	public void setCreateSpritePreviewPanel(CreateSpritePreviewPanel createSpritePreviewPanel)
	{
		this.createSpritePreviewPanel = createSpritePreviewPanel;
	}

	public JFrame getCreateSprite()
	{
		return createSprite;
	}

	public void setCreateSprite(JFrame createSprite)
	{
		this.createSprite = createSprite;
	}

	public String getSpriteName()
	{
		return spriteNameText.getText();
	}

	public JTextField getSpriteNameText()
	{
		return spriteNameText;
	}

	public void setSpriteNameText(String spriteNameText)
	{
		this.spriteNameText.setText(spriteNameText);
	}

	public ArrayList<JButton> getImageButton()
	{
		return imageButton;
	}

	public void setImageButton(ArrayList<JButton> imageButton)
	{
		this.imageButton = imageButton;
	}	
}
