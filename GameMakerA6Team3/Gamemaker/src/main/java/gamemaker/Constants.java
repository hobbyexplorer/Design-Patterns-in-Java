package gamemaker;

import java.awt.Color;

/**
 * Class name: Constants.java Class Responsibilities: Contains the constants.
 * Class collaborators:
 */
public final class Constants
{

	// Constants for Frame
	public static final int FRAME_WIDTH = 1224;
	public static final int FRAME_HEIGHT = 700;
	// Constants for GameBoard
	public static final int GAMEBOARD_WIDTH = 600;
	public static final int GAMEBOARD_HEIGHT = 600;
	public static final int GAMEBOARD_XPADDING = 5;
	public static final int GAMEBOARD_YPADDING = 60;
	// Constants for GameCreatPanel
	public static final int GAMECREATE_PANEL_WIDTH = 500;
	public static final int GAMECREATE_PANEL_HEIGHT = 650;
	// Constants for HelpLabel
	public static final int GAMEMAKERHELP_LABEL_WIDTH = 550;
	public static final int GAMEMAKERHELP_LABEL_HEIGHT = 400;

	// Constants for SpriteParameterPanel
	public static final int SPRITE_PARAMETER_PANEL_WIDTH = 300;
	public static final int SPRITE_PARAMETER_PANEL_HEIGHT = 200;
	// Constants for SpritePanel
	public static final int SPRITEPANEL_WIDTH = 500;
	public static final int SPRITEPANEL_HEIGHT = 500;

	public static final String GAMEMAKERHELP_LABEL = "<html><body><h2 font face=\"comic sans ms\" color=\"red\"><center>Building a Game " + "using our Game Maker</center></h2>" + "<br />" + "<font face=\"cursive\" size=\"4\" color =\"blue\">" + "<p>We have build a game maker which can build various games like breakout,space invaders,etc." + "To get started with building a game here are the steps to be followed:</p>" + "<ol>" + "<li> Create the sprites by clicking on the add sprite button and specifying its name, positions ,dimensions and speed </li>" + "<li> Select the events and actions to be associated with each of the sprites and click on Add/ Delete Event Action to add the sprites  </li>" + "<li> You can delete and update the sprites and event actions</li>" + "<li> You can also drag the sprites on the game board to the required location </li>" + "<li> Next add a winning or losing condition to the game, or you can specify a scoring action for game on timer event </li>" + "<li> You can choose different backgrounds for the game </li>" + "<li> Click Start to begin game play </li>" + "<li> Save and Load feature are available to Save and load a unfinished/completed built game in the build mode</li>" + "<li> Rebuild Feature available to rebuild game in build mode </li>" +"<li> In play mode user can just start and pause game.</li> " +"</ol>" + "</body></html>";
	public static final String SPRITEPANEL_TITLE = "Sprite Definition";
	public static final String SPRITEPANEL_LABEL_SPRITE_NAME = "Sprite Name :";
	public static final String SPRITEPANEL_LABEL_SPRITE_TYPE = "Sprite Type :";
	public static final String SPRITEPANEL_LABEL_SPRITE_X = "X :";
	public static final String SPRITEPANEL_LABEL_SPRITE_Y = "Y :";
	public static final String SPRITEPANEL_LABEL_SPRITE_SPEED = "Speed";
	public static final String SPRITEPANEL_LABEL_SPRITE_SPEED_X = "Speed X :";
	public static final String SPRITEPANEL_LABEL_SPRITE_SPEED_Y = "Speed Y :";
	public static final String SPRITEPANEL_LABEL_SPRITE_WIDTH = "Width :";
	public static final String SPRITEPANEL_LABEL_SPRITE_HEIGHT = "Height :";
	public static final int SPRITE_DEFAULT_HEIGHT = 50;
	public static final int SPRITE_DEFAULT_WIDTH = 50;

	// Constants for SpriteDisplayPanel
	public static final int SPRITE_DISPLAY_HEIGHT = 200;
	public static final int SPRITE_DISPLAY_WIDTH = 200;
	// Constants for EventDiaplayPanel
	public static final int EVENT_DISPLAY_HEIGHT = 200;
	public static final int EVENT_DISPLAY_WIDTH = 200;
	// Constants for Slider
	public static final int ROW_COUNT = 4;
	public static final int COLUMN_COUNT = 2;
	public static final int SLIDER_MIN = 0;
	public static final int SLIDER_MAXIMUM_SPEED = 10;
	public static final int SLIDER_MAX = Constants.GAMECREATE_PANEL_WIDTH;
	public static final int SLIDER_SIZE_MAX = 1000;
	public static final int TEXT_COLUMN_SIZE = 2;
	public static final int SLIDER_POSITION_INIT = 50;
	public static final int SLIDER_SIZE_INIT = 50;
	public static final int SLIDER_SPEED_INIT = 0;
	public static final int SLIDER_MAJOR_TIC_SPACING = 100;

	// Display X,Y position and newline position for the winning and losing
	// message
	public static final int DISPLAY_X = 155;
	public static final int DISPLAY_Y = 200;
	public static final int DISPLAY_NEWLINE = 20;

	public static final String ACTIONPANEL_TITLE = "Select Action";

	// List of actions
	public static final String ACTION_MOVE = "Move Action";
	public static final String ACTION_MOVE_DOWN = "Move Down Action";
	public static final String ACTION_MOVE_LEFT = "Move Left Action";
	public static final String ACTION_MOVE_RIGHT = "Move Right Action";
	public static final String ACTION_MOVE_UP = "Move Up Action";
	public static final String ACTION_MOVE_ALL = "Move All Action";
	public static final String ACTION_MOVE_RANDOM = "Move Random Action";
	public static final String ACTION_MOVE_ALONG = "Move Along Action";

	public static final String ACTION_REVOLVE = "Revolve Action";
	public static final String ACTION_SCORE = "score action";
	public static final String ACTION_ROTATE = "Rotate Action";
	public static final String ACTION_SHOOT = "Shoot Action";
	public static final String ACTION_SHOOT_RANDOM = "Shoot at Random Action";
	public static final String ACTION_SPLIT = "Split Action";
	public static final String ACTION_BLINK = "Blink Action";
	public static final String ACTION_BOUNCE_ALL = "Bounce All Action";
	public static final String ACTION_BOUNCE = "Bounce Action";
	public static final String ACTION_DESTROY = "Destroy Action";
	public static final String ACTION_SOUND = "Sound Action";
	public static final String ACTION_CONTRACT = "Contract Action";
	public static final String ACTION_BOUNCE_VOID = "Bounce Void Action";
	public static final String ACTION_EXPAND = "Expand Action";
	public static final String ACTION_BLINK_RANDOM = "Blink at Random Action";
	public static final String ACTION_GAME_OVER = "Game Over Action";
	public static final String ACTION_SET_GAME_TIME = "Set the Game time";
	public static final String ACTION_CHASE = "Chase the other sprite";
	public static final int ACTIONPANEL_LISTSCROLL_WIDTH = 150;
	public static final int ACTIONPANEL_LISTSCROLL_HEIGHT = 100;
	// Constants for Background Preview PAnel
	public static final String BACKGROUND_PREVIEW_PANEL_TITLE = "Backgorund Preview";
	public static final int BACKGROUND_PREVIEW_SCALE_WIDTH = 200;
	public static final String BACKGROUND_SELECTION_PANEL_CHOOSE_BUTTON = "Choose BackGround";
	public static final String BACKGROUND_SELECTION_JFRAME_CONFIRM_BUTTON = "Confirm";
	public static final int BACKGROUND_PREVIEW_JFRAME_WIDTH = 300;
	public static final int BACKGROUND_PREVIEW_JFRAME_HEIGHT = 340;
	public static final int BACKGROUND_PREVIEW_JFRAME_LOCATION = 300;
	public static final int BACKGROUND_PREVIEW_PANEL_WIDTH = 220;
	public static final int BACKGROUND_PREVIEW_PANEL_HEIGHT = 200;
	public static final int ACTIONPANEL_LISTSCROLL_HEIGHT2 = 100;
	// Constants for Event Display Panel
	public static final String EVENTDISPLAY_PANEL_TITLE = "Available Event-Action List";
	public static final int EVENTDISPLAY_SCROLL_WIDTH = 350;
	public static final int EVENTDISPLAY_SCROLL_HEIGHT = 100;

	// Constants for Event Panel
	public static final String EVENTPANEL_TITLE = "Select Event";

	public static final String EVENT_GENERATE_AT_POSITION = "GenerateAtPosition";
	public static final String EVENT_GENERATE_RANDOM = "GenerateRandom";
	public static final String EVENT_TIMER = "Timer Event";
	public static final String EVENT_KEY_PRESSED = "Key Pressed Event";
	public static final String EVENT_COLLISION = "Collision Event";
	public static final String EVENT_COLLISION_WITH_EXCEPTION = "Collision Event With Exception";
	public static final String EVENT_COUNT = "Count Event";
	public static final String[] ALL_EVENT_NAMES = { EVENT_TIMER, EVENT_KEY_PRESSED, EVENT_COLLISION, EVENT_COLLISION_WITH_EXCEPTION, EVENT_GENERATE_RANDOM, EVENT_GENERATE_AT_POSITION };
	public static final String[] ONE_SPRITE_EVENT_NAMES = { EVENT_TIMER, EVENT_KEY_PRESSED, EVENT_GENERATE_RANDOM, EVENT_GENERATE_AT_POSITION, EVENT_COUNT };
	public static final String[] TWO_SPRITE_EVENT_NAMES = { EVENT_COLLISION, EVENT_COLLISION_WITH_EXCEPTION };

	public static final String EVENT_KEY_LEFT = "Key Left Event";
	public static final String EVENT_KEY_RIGHT = "Key Right Event";
	public static final String EVENT_KEY_UP = "Key Up Event";
	public static final String EVENT_KEY_DOWN = "Key Down Event";
	
	public static final String[] KEY_EVENT_ACTION_NAMES = { ACTION_SCORE, ACTION_MOVE, ACTION_MOVE_DOWN, ACTION_MOVE_UP, ACTION_MOVE_LEFT, ACTION_MOVE_RIGHT, ACTION_ROTATE, ACTION_SHOOT, ACTION_SOUND };
	public static final String[] COLLISION_EVENT_ACTION_NAMES = {  ACTION_SCORE, ACTION_MOVE, ACTION_MOVE_ALL, ACTION_MOVE_ALONG, ACTION_BOUNCE_ALL, ACTION_BOUNCE, ACTION_CONTRACT, ACTION_EXPAND, ACTION_SPLIT, ACTION_SOUND, ACTION_GAME_OVER, ACTION_DESTROY };
	public static final String[] EVENT_GENERATE_AT_POSITION_NAMES = {};
	public static final String[] EVENT_GENERATE_RANDOM_NAMES = {};
	public static final String[] EVENT_COLLISION_WITH_EXCEPTION_NAMES = { ACTION_SCORE,  ACTION_MOVE_LEFT, ACTION_MOVE_RIGHT, ACTION_MOVE_UP, ACTION_MOVE_DOWN, ACTION_MOVE, ACTION_MOVE_ALL, ACTION_MOVE_ALONG, ACTION_BOUNCE_ALL, ACTION_BOUNCE, ACTION_CONTRACT, ACTION_EXPAND, ACTION_SPLIT, ACTION_SOUND, ACTION_GAME_OVER };
	public static final String[] EVENT_TIMER_NAMES = {  ACTION_SCORE, ACTION_MOVE_LEFT, ACTION_MOVE_RIGHT, ACTION_MOVE_DOWN, ACTION_MOVE_UP, ACTION_MOVE, ACTION_MOVE_ALL, ACTION_MOVE_RANDOM, ACTION_REVOLVE, ACTION_ROTATE, ACTION_BLINK, ACTION_BLINK_RANDOM, ACTION_SET_GAME_TIME,ACTION_CHASE };
	public static final String[] EVENT_COUNT_NAMES = {  ACTION_SCORE, ACTION_GAME_OVER };

	public static final int EVENTPANEL_LISTSCROLL_WIDTH = 150;
	public static final int EVENTPANEL_LISTSCROLL_HEIGHT = 100;
	// Constants for Game Create Panel
	public static final String GAMECREATEPANEL_ADD_SPRITE_BUTTON = "Add Object";
	public static final String GAMECREATEPANEL_ADD_EVNTACT_BUTTON = "Add Rule";
	// Constants for Victory Criteria Frame
	public static final int VICTORY_JFRAME_WIDTH = 340;
	public static final int VICTORY_JFRAME_HEIGHT = 350;
	public static final int VICTORY_JFRAME_LOCATION = 200;
	// // Constants for Sound Frame
	public static final int SOUND_JFRAME_WIDTH = 250;
	public static final int SOUND_JFRAME_HEIGHT = 140;
	public static final int SOUND_JFRAME_LOCATION = 300;
	// Constants for Time Panel
	public static final int TIME_PANEL_WIDTH = 500;
	public static final int TIME_PANEL_HEIGHT = 50;
	public static final int TIME_PANEL_XPADDING = 50;
	public static final int TIME_PANEL_YPADDING = 10;
	public static final int STARTPAUSEPANEL_GAMEAREA_YPADDING = 665;
	public static final int STARTPAUSEPANEL_GAMEAREA_XPADDING = 150;
	public static final int STARTPAUSEPANEL_GAMEAREA_HEIGHT = 45;
	public static final int STARTPAUSEPANEL_GAMEAREA_WIDTH = 250;

	// Constants for Load Save Panel
	public static final String LOADSAVEPANEL_LOAD_BUTTON = "Load";
	public static final String LOADSAVEPANEL_SAVE_BUTTON = "Save";
	// // Constants for Sprite Display Panel
	public static final String SPRITEDISPLAY_PANEL_TITLE = "Available Sprite List";
	public static final int SPRITEDISPLAY_SCROLL_WIDTH = 190;
	public static final int SPRITEDISPLAY_SCROLL_HEIGHT = 145;
	// Constants for Sprite Preview Panel
	public static final String SPRITEPREVIEWPANEL_TITLE = "Sprite Preview";
	public static final int SPRITEPREVIEWPANEL_PADDING = 60;

	public static final String STARTPAUSEPANEL_START_BUTTON = "Start";
	public static final String STARTPAUSEPANEL_PAUSE_BUTTON = "Pause";
	public static final String STARTPAUSEPANEL_REBUILD_BUTTON = "Re-build";
	public static final String STARTPAUSEPANEL_RESUME_BUTTON = "Resume";

	public static final String CREATEVICTORYCOMBO_PANEL_TITLE = "Select victory combination";
	public static final String VICTORYCOMBO_PANEL_TITLE = "Victory combinations";
	public static final int WINLOSELIST_SCROLL_WIDTH = 70;
	public static final int WINLOSELIST_SCROLL_HEIGHT = 40;
	public static final int EVENTTYPELIST_SCROLL_WIDTH = 90;
	public static final int EVENTTYPELIST_SCROLL_HEIGHT = 60;
	public static final int VICTORYPANEL_CONDITION_TEXT_WIDTH = 10;
	public static final int VICTORYPANEL_CONDITION_TEXT_HEIGHT = 40;
	public static final int VICTORYPANEL_MESSAGE_TEXT_WIDTH = 290;
	public static final int VICTORYPANEL_MESSAGE_TEXT_HEIGHT = 50;
	public static final int VICTORYLIST_SCROLL_WIDTH = 250;
	public static final int VICTORYLIST_SCROLL_HEIGHT = 75;
	public static final String VICTORYPANEL_ADD_BUTTON = "Add Condition";
	public static final String VICTORYPANEL_DELETE_BUTTON = "Delete Condition";
	public static final String VICTORYPANEL_DONE_BUTTON = "Done";

	public static final String VICTORYPANEL_WIN_IF_ELEMENT = "Win if";
	public static final String VICTORYPANEL_LOOSE_IF_ELEMENT = "Lose if";
	public static final String VICTORYPANEL_TIME_IF_ELEMENT = "Time is";
	public static final String VICTORYPANEL_DESTROYED_IF_ELEMENT = "Destroyed";
	public static final String VICTORYPANEL_WALL_COLLIDES_IF_ELEMENT = "Collides wall";

	// Constants for BounceAction
	public static final String DEFAULT_COLLISION = "defaultCollision";
	public static final String LEFT_RIGHT_COLLISION = "leftRightCollision";
	public static final String UP_DOWN_COLLISION = "upDownCollision";

	// Constants for create sprite
	public static final int CREATE_SPRITE_PANEL_WIDTH = 500;
	public static final int CREATE_SPRITE_PANEL_HEIGHT = 550;
	public static final int NO_OF_SPRITES = 28;
	public static final int PREVIEW_HEIGHT = 200;
	public static final int PREVIEW_WIDTH = 200;

	public static final String SPACE = " - ";
	public static final String CREATE_SPRITE_BUTTON = "Create New";
	public static final String DELETE_SPRITE_BUTTON = "Delete Sprite";

	// Constants for event fill out forms
	public static final int FILL_OUT_FORM_WIDTH = 400;
	public static final int FILL_OUT_FORM_HEIGHT = 450;

	public static final int FILLOUT_FORM_TITLE_HEIGHT = 20;
	public static final int FILLOUT_FORM_TITLE_DIMENSION_X = 50;
	public static final int FILLOUT_FORM_TITLE_DIMENSION_Y = 40;
	public static final int FILLOUT_FORM_TITLE_WIDTH = 340;

	public static final int EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_X = 40;
	public static final int EVENT_FILLOUT_FORM_HELPTEXT_DIMENSION_Y = 370;
	public static final int EVENT_FILLOUT_FORM_HELPTEXT_WIDTH = 300;
	public static final int EVENT_FILLOUT_FORM_HELPTEXT_HEIGHT = 80;
	public static final String CANCEL_BUTTON = "Cancel";
	public static final String NEXT_BUTTON = "Next->";
	public static final String SAVE_BUTTON = "Save";
	public static final String LOAD_BUTTON = "Load";
	public static final String SUBMIT_BUTTON = "Submit";
	public static final String BROWSE_BUTTON = "Browse";
	public static final String PLAY_BUTTON = "Play";
	
	// Fill out form button position
	public static final int FILLOUT_CANCEL_BUTTON_X = 85;
	public static final int FILLOUT_CANCEL_BUTTON_Y = 350;
	public static final int FILLOUT_CANCEL_BUTTON_WIDTH = 75;
	public static final int FILLOUT_CANCEL_BUTTON_HEIGHT = 20;

	
	// browse button
	public static final int FILLOUT_BR0WSE_BUTTON_X = 200;
	public static final int FILLOUT_BROWSE_BUTTON_Y = 130;
	public static final int FILLOUT_BROWSE_BUTTON_WIDTH = 80;
	public static final int FILLOUT_BROWSE_BUTTON_HEIGHT = 20;

	// play button
	public static final int FILLOUT_PLAY_BUTTON_X = 200;
	public static final int FILLOUT_PLAY_BUTTON_Y = 180;
	public static final int FILLOUT_PLAY_BUTTON_WIDTH = 80;
	public static final int FILLOUT_PLAY_BUTTON_HEIGHT = 20;
		
	public static final int FILLOUT_NEXT_BUTTON_X = 180;
	public static final int FILLOUT_NEXT_BUTTON_Y = 350;
	public static final int FILLOUT_NEXT_BUTTON_WIDTH = 75;
	public static final int FILLOUT_NEXT_BUTTON_HEIGHT = 20;
	public static final Color FILLOUT_FORM_BG_COLOR = Color.white;

}
