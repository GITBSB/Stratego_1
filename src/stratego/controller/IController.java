package stratego.controller;

import stratego.entities.IField;
import stratego.entities.IFigure;
import stratego.entities.impl.GameStatus;
import stratego.entities.impl.Player;
import stratego.util.observer.IObservable;

public interface IController extends IObservable {

	public void setFigure(int j, int i, String figure);

	public Player getPlayer();

	public String getStatus();

	public void createNewGame();
	
	public void startNewGame();

	public GameStatus getGameStatus();

	public String printGrid();

	public void testsetup();

	public void changePlayer();
	
	public IFigure deleteFigure(int i, int j);

	public void move_attack(int i, int j, int n, int m);

	public boolean ready_or_not();

	public void show_all();

	public String getFieldId(int i, int j);
	
	public String getFigureToSet();

	public void setFigureToSet(String figure);
	
	public void playerSetFinished();
	
	public Player getPlayerFromPlayerList(int idx);
	
	public void notifyObserversC(GameStatus gameStatus);
	
	public void continueGame();
	
	public IField getField(int i, int j);
	
	//TODO: test entfernen
	public void changePlayerTest();
}
