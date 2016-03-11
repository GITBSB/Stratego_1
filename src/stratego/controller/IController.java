package stratego.controller;

import stratego.entities.impl.GameStatus;
import stratego.entities.impl.Player;
import stratego.util.observer.IObservable;

public interface IController extends IObservable {

	public void setFigure(char c, int j, String figure);

	public Player getPlayer();

	public String getStatus();

	public void createnewgame();

	public void startGame();

	public GameStatus getGameStatus();

	public String printGrid();

	public void testsetup();

	public void changePlayer();

	public void move_attack(int ii, char c1, int nn, char c2);

	public void ready_or_not();

	public void show_all();
}
