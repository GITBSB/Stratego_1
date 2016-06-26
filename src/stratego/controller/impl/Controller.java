package stratego.controller.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import stratego.controller.IController;
import stratego.entities.IField;
import stratego.entities.IFigure;
import stratego.entities.impl.Field;
import stratego.entities.impl.FieldStatus;
import stratego.entities.impl.GameStatus;
import stratego.entities.impl.Grid;
import stratego.entities.impl.Player;
import stratego.entities.impl.PlayerType;
import stratego.entities.impl.figures.Aufklärer;
import stratego.entities.impl.figures.Bombe;
import stratego.entities.impl.figures.Fahne;
import stratego.entities.impl.figures.General;
import stratego.entities.impl.figures.Hauptmann;
import stratego.entities.impl.figures.Leutnant;
import stratego.entities.impl.figures.Major;
import stratego.entities.impl.figures.Marschall;
import stratego.entities.impl.figures.Mineur;
import stratego.entities.impl.figures.NoFigure;
import stratego.entities.impl.figures.Oberst;
import stratego.entities.impl.figures.Spion;
import stratego.entities.impl.figures.Unteroffizier;
import stratego.util.observer.Observable;

@Singleton
public class Controller extends Observable implements IController {
	private Grid grid;
	private GameStatus gameStatus;
	private Player player = null;
	private String statusLine = "Stratego!";
	private List<Player> playerList;
	private String figuretoSet = null;
	Logger logger = Logger.getLogger(Controller.class);

	public final static String PLAYER_A = "Player A";
	public final static String PLAYER_B = "Player B";

	@Inject
	public Controller() {
		grid = Grid.getInstance();
		playerList = new LinkedList<Player>();
	}

	public void createNewGame() {
		buildEmptyGrid();
		gameStatus = GameStatus.NOT_STARTED;
		statusLine = "New Stratego Game created";
		enterPlayernames();
		notifyObservers();
	}
	
	public void startNewGame() {
		if (gameStatus != GameStatus.NOT_STARTED) {
			createNewGame();
		}
		// Testaufbau TODO:remove
		testsetup();
		
		// eigentlicher Verlauf
//		playerSetFigures();
	}

	private void buildEmptyGrid() {
		grid.createGrid();
	}

	private void enterPlayernames() {
		playerList.clear();
		playerList.add(new Player(PLAYER_A, PlayerType.PLAYER_A, FieldStatus.A_SIDE));
		playerList.add(new Player(PLAYER_B, PlayerType.PLAYER_B, FieldStatus.B_SIDE));
	}

	public void testsetup() {
		playerSetFigures();

		setFigure(0, 1, Aufklärer.AUFKLÄRER);
		setFigure(0, 2, Aufklärer.AUFKLÄRER);
		setFigure(0, 3, Aufklärer.AUFKLÄRER);
		setFigure(0, 0, Aufklärer.AUFKLÄRER);
		setFigure(1, 1, Aufklärer.AUFKLÄRER);
		setFigure(1, 2, Aufklärer.AUFKLÄRER);
		setFigure(1, 3, Major.MAJOR);
		setFigure(1, 0, Aufklärer.AUFKLÄRER);
		setFigure(2, 1, Spion.SPION);
		setFigure(2, 2, Mineur.MINEUR);
		setFigure(2, 3, Mineur.MINEUR);
		setFigure(2, 0, Major.MAJOR);
		setFigure(3, 1, Mineur.MINEUR);
		setFigure(3, 2, Hauptmann.HAUPTMANN);
		setFigure(3, 3, Unteroffizier.UNTEROFFIZIER);
		setFigure(3, 0, Unteroffizier.UNTEROFFIZIER);
		setFigure(4, 1, Unteroffizier.UNTEROFFIZIER);
		setFigure(4, 2, Unteroffizier.UNTEROFFIZIER);
		setFigure(4, 3, Leutnant.LEUTNANT);
		setFigure(4, 0, Leutnant.LEUTNANT);
		setFigure(5, 1, Leutnant.LEUTNANT);
		setFigure(5, 2, Leutnant.LEUTNANT);
		setFigure(5, 3, Mineur.MINEUR);
		setFigure(5, 0, Hauptmann.HAUPTMANN);
		setFigure(7, 1, Hauptmann.HAUPTMANN);
		setFigure(7, 2, Hauptmann.HAUPTMANN);
		setFigure(7, 3, Oberst.OBERST);
		setFigure(7, 0, Major.MAJOR);
		setFigure(8, 1, Oberst.OBERST);
		setFigure(8, 2, General.GENERAL);
		setFigure(8, 3, Aufklärer.AUFKLÄRER);
		setFigure(8, 0, Bombe.BOMBE);
		setFigure(6, 1, Bombe.BOMBE);
		setFigure(6, 2, Bombe.BOMBE);
		setFigure(6, 3, Bombe.BOMBE);
		setFigure(6, 0, Bombe.BOMBE);
		setFigure(9, 1, Bombe.BOMBE);
		setFigure(9, 2, Marschall.MARSCHALL);
		setFigure(9, 3, Mineur.MINEUR);
		setFigure(9, 0, Fahne.FAHNE);
		playerSetFinished();
		setFigure(0, 7, Aufklärer.AUFKLÄRER);
		setFigure(0, 8, Aufklärer.AUFKLÄRER);
		setFigure(0, 9, Aufklärer.AUFKLÄRER);
		setFigure(0, 6, Aufklärer.AUFKLÄRER);
		setFigure(1, 7, Aufklärer.AUFKLÄRER);
		setFigure(1, 8, Aufklärer.AUFKLÄRER);
		setFigure(1, 9, Major.MAJOR);
		setFigure(1, 6, Aufklärer.AUFKLÄRER);
		setFigure(2, 7, Spion.SPION);
		setFigure(2, 8, Mineur.MINEUR);
		setFigure(2, 9, Mineur.MINEUR);
		setFigure(2, 6, Major.MAJOR);
		setFigure(3, 7, Mineur.MINEUR);
		setFigure(3, 8, Hauptmann.HAUPTMANN);
		setFigure(3, 9, Unteroffizier.UNTEROFFIZIER);
		setFigure(3, 6, Unteroffizier.UNTEROFFIZIER);
		setFigure(4, 7, Unteroffizier.UNTEROFFIZIER);
		setFigure(4, 8, Unteroffizier.UNTEROFFIZIER);
		setFigure(4, 9, Leutnant.LEUTNANT);
		setFigure(4, 6, Leutnant.LEUTNANT);
		setFigure(5, 7, Leutnant.LEUTNANT);
		setFigure(5, 8, Leutnant.LEUTNANT);
		setFigure(5, 9, Mineur.MINEUR);
		setFigure(5, 6, Hauptmann.HAUPTMANN);
		setFigure(7, 7, Hauptmann.HAUPTMANN);
		setFigure(7, 8, Hauptmann.HAUPTMANN);
		setFigure(7, 9, Oberst.OBERST);
		setFigure(7, 6, Major.MAJOR);
		setFigure(8, 7, Oberst.OBERST);
		setFigure(8, 8, General.GENERAL);
		setFigure(8, 9, Aufklärer.AUFKLÄRER);
		setFigure(8, 6, Bombe.BOMBE);
		setFigure(6, 7, Bombe.BOMBE);
		setFigure(6, 8, Bombe.BOMBE);
		setFigure(6, 9, Bombe.BOMBE);
		setFigure(6, 6, Bombe.BOMBE);
		setFigure(9, 7, Bombe.BOMBE);
		setFigure(9, 8, Marschall.MARSCHALL);
		setFigure(9, 9, Mineur.MINEUR);
		setFigure(9, 6, Fahne.FAHNE);
		statusLine = "Testsetup";
		notifyObservers();
	}

	public void playerSetFigures() {
		nextPlayer();
		statusLine = "Spieler " + player.getPlayerName() + " Figuren setzen!";
		gameStatus = GameStatus.SET_FIGURES;
		notifyObservers();
	}

	public void setFigure(int j, int i, String fig) {
		IFigure figure;
		try {
			figure = player.getFigureSet().getfigure(fig);
		} catch (Exception e) {
			statusLine = "Falsche Eingabe!";
			notifyObservers();
			return;
		}
		if (grid.getField(i, j).getFieldstatus() != player.getFieldStatus()) {
			statusLine = "Platzierung nur auf eigenem Feld möglich!";
		} else if (figure.getAnz() <= 0) {
			statusLine = "Nicht genug Figuren dieser Art!";
		} else {
			grid.getField(i, j).setFigure(figure);
			statusLine = "Figur gesetzt!";
		}
		notifyObservers();
	}

	public void move_attack(int i, int j, int n, int m) {
		Class<?> from_class = grid.getField(i, j).getFigure().getClass();

		IFigure from_figure = grid.getField(i, j).getFigure();
		IFigure to_figure = grid.getField(n, m).getFigure();

		if (from_figure.getPlayer() != player) {
			statusLine = "Nur eigene Figuren dürfen bewegt werden";
		} else if (from_class == Bombe.class || from_class == Fahne.class) {
			statusLine = "Figur kann nicht bewegt werden!";
		} else if (grid.getField(n, m).getFieldstatus() == FieldStatus.NO_FIELD) {
			statusLine = "Zielfeld darf nicht besetzt werden!";
		} else if (to_figure.getClass() != NoFigure.class && to_figure.getPlayer() == from_figure.getPlayer()) {
			statusLine = "Zielfeld ist von eigener Figur besetzt!";
		} else if (from_class == Aufklärer.class && ((i + 10 > n && n > i - 10) && j == m)
				|| ((j + 10 > m && m > j - 10) && i == n)) {
			if (n > i) {
				for (int z = i + 1; z < n; z++) {
					if (grid.getField(z, m).getFigure().getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else if (n < i) {
				for (int z = i - 1; z > n; z--) {
					if (grid.getField(z, m).getFigure().getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else if (m > j) {
				for (int z = j + 1; z < m; z++) {
					if (grid.getField(n, z).getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else
				for (int z = j - 1; z > m; z--) {
					if (grid.getField(n, z).getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			if (statusLine != "Zug ist nicht erlaubt!") {
				move_attack_Impl(from_figure, to_figure, i, j, n, m);
			}
		} else if (((n == i + 1 || n == i - 1) && j == m) || ((m == j + 1 || m == j - 1) && i == n)) {
			move_attack_Impl(from_figure, to_figure, i, j, n, m);
		} else {
			statusLine = "Dieser Zug ist nicht erlaubt, bitte Bewegungsradius beachten!";
		}
		notifyObservers();
	}

	private void move_attack_Impl(IFigure from_figure, IFigure to_figure, int i, int j, int n, int m) {
		if (to_figure.getClass() != NoFigure.class) {
			if (from_figure.getClass() == Spion.class && to_figure.getClass() == Marschall.class) {
				grid.move(i, j, n, m);
				statusLine = "Spion erwischt den Marschall!";
			} else if (from_figure.getClass() == Mineur.class && to_figure.getClass() == Bombe.class) {
				grid.move(i, j, n, m);
				statusLine = "Mineur entschärft Bombe!";
			} else if (to_figure.getClass() == Bombe.class) {
				deleteFigure(i, j);
				statusLine = "Bombe!";
			} else if (to_figure.getClass() == Fahne.class) {
				statusLine = "Spieler " + from_figure.getPlayer().getPlayerName() + " hat das Spiel gewonnen!!!";
				gameStatus = GameStatus.END;
			} else {
				if (from_figure.getStrength() > to_figure.getStrength()) {
					deleteFigure(n, m);
					statusLine = from_figure.getName() + "(" + from_figure.getPlayer().getPlayerName() + ") besiegt "
							+ to_figure.getName() + "(" + to_figure.getPlayer().getPlayerName() + ")!";
				} else if (from_figure.getStrength() < to_figure.getStrength()) {
					deleteFigure(i, j);
					statusLine = from_figure.getName() + "(" + from_figure.getPlayer().getPlayerName()
							+ ") verliert gegen " + to_figure.getName() + "(" + to_figure.getPlayer().getPlayerName()
							+ ")!";
				} else {
					deleteFigure(i, j);
					deleteFigure(n, m);
					statusLine = "Unentschieden! Beide Krieger fallen (" + from_figure.getName() + ")!";
				}
			}
		} else {
			grid.move(i, j, n, m);
			statusLine = "Figur wurde bewegt!";
		}
		if (getGameStatus() == GameStatus.END) {
			notifyObservers();
		} else {
			changePlayer();
		}
	}

	public Grid getGrid() {
		return grid.getInstance();
	}

	public Player getPlayer() {
		return this.player;
	}

	public String getStatus() {
		return statusLine;
	}

	public GameStatus getGameStatus() {
		return this.gameStatus;
	}

	public void setGameStatus(GameStatus e) {
		gameStatus = e;
	}

	public void playerSetFinished() {
		if (getGameStatus() == GameStatus.SET_FIGURES) {
			if (ready_or_not()) {
				nextPlayer();
				statusLine = "Spieler " + player.getPlayerName() + "Figuren setzten!";
				if (ready_or_not()) {
					gameStatus = GameStatus.FIGHT;
					statusLine = "Lasset die Schlacht beginnen!";
				}
			} else {
				statusLine = "Es müssen alle Figuren gesetzt sein!";
			}
			notifyObservers();
		}
	}

	public void changePlayerTest() {
		nextPlayer();
		notifyObservers();

	}

	public void changePlayer() {
		nextPlayer();
		gameStatus = GameStatus.PLAYER_CHANGE;
		notifyObservers();
	}

	public void continueGame() {
		gameStatus = GameStatus.FIGHT;
		notifyObservers();
	}

	private void nextPlayer() {
		if (playerList.get(0).equals(player)) {
			player = getPlayerFromPlayerList(1);
		} else {
			player = getPlayerFromPlayerList(0);
		}
	}

	private List<Player> getPlayerList() {
		return playerList;
	}

	public Player getPlayerFromPlayerList(int idx) {
		return getPlayerList().get(idx);
	}

	public static int letterCode(char c) {
		if (Character.isLetter(c) && Character.isLowerCase(c))
			return c - 'a';
		if (Character.isLetter(c) && Character.isUpperCase(c))
			return c - 'A';
		throw new IllegalArgumentException("letter: " + c);
	}

	public boolean ready_or_not() {
		if (player.get_count_figures() == 40) {
			return true;
		} else {
			return false;
		}
	}

	public String printGrid() {
		return grid.toString(this.getGameStatus(), player);
	}

	public void show_all() {
		grid.toString(gameStatus, player);
	}

	public String getFieldId(int i, int j) {
		String fieldId;
		if (gameStatus == GameStatus.PLAYER_CHANGE) {
			fieldId = getFieldId_PlayerChange(i, j);
		} else {
			fieldId = getFieldId_Fight(i, j);
		}
		return fieldId;
	}

	private String getFieldId_PlayerChange(int i, int j) {
		return getField(i, j).getFieldIdPlayerChange(getGameStatus(), getPlayer());
	}

	private String getFieldId_Fight(int i, int j) {
		return getField(i, j).getFieldId(getGameStatus(), getPlayer());
	}

	public IField getField(int i, int j) {
		return grid.getField(i, j);
	}

	public void setFigureToSet(String figureToSet) {
		this.figuretoSet = figureToSet;
	}

	public String getFigureToSet() {
		return this.figuretoSet;
	}

	public IFigure deleteFigure(int i, int j) {
		IFigure figure = getField(i, j).deleteFigure();
		notifyObservers();
		return figure;

	}

	public void notifyObserversC(GameStatus gameStatus) {
		notifyObservers(gameStatus);
	}
}