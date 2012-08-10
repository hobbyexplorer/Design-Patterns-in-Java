package controller.gameboardmodel;

import model.GameBoardModel;

public class GameBoardModelController
{
	private static GameBoardModelController instance;

	public static GameBoardModelController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new GameBoardModelController();
		return instance;
	}

	private GameBoardModel gameBoardModel;

	private GameBoardModelController()
	{
		gameBoardModel = new GameBoardModel();
	}

	public GameBoardModel getGameBoardModel()
	{
		return gameBoardModel;
	}
}
