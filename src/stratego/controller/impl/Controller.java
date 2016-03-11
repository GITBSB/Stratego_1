package stratego.controller.impl;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import stratego.util.observer.Observable;
import stratego.entities.IFigure;
import stratego.entities.impl.*;
import stratego.controller.IController;
import stratego.entities.impl.FieldStatus;
import stratego.entities.impl.GameStatus;
import stratego.entities.impl.Grid;
import stratego.entities.impl.Player;
import stratego.entities.impl.figures.Aufklärer;
import stratego.entities.impl.figures.Bombe;
import stratego.entities.impl.figures.Fahne;
import stratego.entities.impl.figures.Marschall;
import stratego.entities.impl.figures.Mineur;
import stratego.entities.impl.figures.NoFigure;
import stratego.entities.impl.figures.Spion;

@Singleton
public class Controller extends Observable implements IController {
	private Grid grid;
	private GameStatus status;
	private Player player;
	private String statusLine = "Stratego!";
	private List<Player> playerList;
	private int number_figures;

	@Inject
	public Controller() {
		grid = Grid.getInstance();
		status = GameStatus.NOT_STARTED;
		playerList = new LinkedList<Player>();
		player = null;
		setNumber_figures(80);
	}

	public void createnewgame() {
		buildEmptyGrid();
		statusLine = "New Stratego Game created";
        notifyObservers(GameStatus.NOT_STARTED);
	}

	private void buildEmptyGrid() {
		grid.createGrid();
	}

	public void startGame() {
		status = GameStatus.SET_FIGURES;
		notifyObservers();
	}

	public void enterPlayernames(String playername1, String playername2) {
		playerList.add(new Player(playername1, PlayerType.PLAYER_A, FieldStatus.A_SIDE));
		playerList.add(new Player(playername2, PlayerType.PLAYER_B, FieldStatus.B_SIDE));
	}

	public void testsetup() {
		enterPlayernames("Aaa", "Bbb");
    	playerSetFigures();
    	setFigure('A', 1, "A");
    	setFigure('A', 2, "A");
    	setFigure('A', 3, "A");
    	setFigure('A', 0, "A");
    	setFigure('B', 1, "A");
    	setFigure('B', 2, "A");
    	setFigure('B', 3, "M");
    	setFigure('B', 0, "A");
    	setFigure('C', 1, "S");
    	setFigure('C', 2, "Mi");
    	setFigure('C', 3, "Mi");
    	setFigure('C', 0, "Ma");
    	setFigure('D', 1, "Mi");
    	setFigure('D', 2, "H");
    	setFigure('D', 3, "U");
    	setFigure('D', 0, "U");
    	setFigure('E', 1, "U");
    	setFigure('E', 2, "U");
    	setFigure('E', 3, "L");
    	setFigure('E', 0, "L");
    	setFigure('F', 1, "L");
    	setFigure('F', 2, "L");
    	setFigure('F', 3, "Mi");
    	setFigure('F', 0, "H");
    	setFigure('G', 1, "H");
    	setFigure('G', 2, "H");
    	setFigure('G', 3, "O");
    	setFigure('G', 0, "Ma");
    	setFigure('H', 1, "O");
    	setFigure('H', 2, "G");
    	setFigure('H', 3, "A");
    	setFigure('H', 0, "B");
    	setFigure('I', 1, "B");
    	setFigure('I', 2, "B");
    	setFigure('I', 3, "B");
    	setFigure('I', 0, "B");
    	setFigure('J', 1, "B");
    	setFigure('J', 2, "Ma");
    	setFigure('J', 3, "Mi");
    	setFigure('J', 0, "F");

    	playerSetFigures();
    	setFigure('A', 6, "F");
    	setFigure('A', 7, "A");
    	setFigure('A', 8, "A");
    	setFigure('A', 9, "A");
    	setFigure('B', 6, "A");
    	setFigure('B', 7, "A");
    	setFigure('B', 8, "A");
    	setFigure('B', 9, "A");
    	setFigure('C', 6, "S");
    	setFigure('C', 7, "Mi");
    	setFigure('C', 8, "Mi");
    	setFigure('C', 9, "Mi");
    	setFigure('D', 6, "Mi");
    	setFigure('D', 7, "Mi");
    	setFigure('D', 8, "U");
    	setFigure('D', 9, "U");
    	setFigure('E', 6, "U");
    	setFigure('E', 7, "U");
    	setFigure('E', 8, "L");
    	setFigure('E', 9, "L");
    	setFigure('F', 6, "L");
    	setFigure('F', 7, "L");
    	setFigure('F', 8, "H");
    	setFigure('F', 9, "H");
    	setFigure('G', 6, "H");
    	setFigure('G', 7, "H");
    	setFigure('G', 8, "O");
    	setFigure('G', 9, "Ma");
    	setFigure('H', 6, "O");
    	setFigure('H', 7, "G");
    	setFigure('H', 8, "M");
    	setFigure('H', 9, "B");
    	setFigure('I', 6, "B");
    	setFigure('I', 7, "B");
    	setFigure('I', 8, "B");
    	setFigure('I', 9, "B");
    	setFigure('J', 6, "B");
    	setFigure('J', 7, "Ma");
    	setFigure('J', 8, "Ma");
    	setFigure('J', 9, "A");
    	statusLine = "Testsetup";
    	notifyObservers();
	}

	public void playerSetFigures() {
		nextPlayer();
		statusLine = "Spieler " + player.getPlayerName() + " Figuren setzen!";
		status = GameStatus.SET_FIGURES;
		notifyObservers();
	}

	public void setFigure(char c, int i, String fig) {
		int j = letterCode(c);
		IFigure figure;
		try {
			figure = player.getFigureSet().getfigure(fig);
		} catch (Exception e) {
			statusLine = "Falsche Eingabe!";
			notifyObservers();
			return;
		}
		if(grid.getField(i, j).getFieldstatus() != player.getFieldStatus()) {
			statusLine = "Platzierung nur auf eigenem Feld möglich!";
		} else if(figure.getAnz() <= 0) {
			statusLine = "Nicht genug Figuren dieser Art!";
		} else {
			grid.getField(i, j).setFigure(figure);
			statusLine = "Figur gesetzt!";
		}
	    notifyObservers();
	}

	public void move_attack(int i, char c1, int n, char c2) {
		int j = letterCode(c1);
		int m = letterCode(c2);
		Class<?> from_class = grid.getField(i, j).getFigure().getClass();

		IFigure from_figure = grid.getField(i, j).getFigure();
		IFigure to_figure = grid.getField(n, m).getFigure();

		if(from_figure.getPlayer() != player) {
			statusLine = "Nur eigene Figuren dürfen bewegt werden";
		} else if(from_class == Bombe.class || from_class == Fahne.class) {
			statusLine = "Figur kann nicht bewegt werden!";
		} else if(grid.getField(n, m).getFieldstatus() == FieldStatus.NO_FIELD) {
			statusLine = "Zielfeld darf nicht besetzt werden!";
		} else if(to_figure.getClass() != NoFigure.class && to_figure.getPlayer() == from_figure.getPlayer()) {
			statusLine = "Zielfeld ist von eigener Figur besetzt!";
		} else if(from_class == Aufklärer.class && ((i+10 > n && n > i-10) && j == m)||((j+10 > m && m > j-10) && i == n)) {
			if(n > i) {
				for(int z = i+1; z < n; z++) {
					if(grid.getField(z, m).getFigure().getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else if(n < i) {
				for(int z = i-1; z > n; z--) {
					if(grid.getField(z, m).getFigure().getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else if(m > j) {
				for(int z = j+1; z < m; z++) {
					if(grid.getField(n, z).getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else
				for(int z = j-1; z > m; z--) {
					if(grid.getField(n, z).getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			if(statusLine != "Zug ist nicht erlaubt!") {
				move_attack_Impl(from_figure, to_figure, i, j, n, m);
			}
		} else if(((n == i+1 || n == i-1) && j == m) || ((m == j+1 || m == j-1) && i == n)) {
			move_attack_Impl(from_figure, to_figure, i, j, n, m);
		} else {
			statusLine = "Dieser Zug ist nicht erlaubt, bitte Bewegungsradius beachten!";
		}
		notifyObservers();
	}

	private void move_attack_Impl(IFigure from_figure, IFigure to_figure, int i, int j, int n, int m) {
		if(to_figure.getClass() != NoFigure.class) {
			if(from_figure.getClass() == Spion.class && to_figure.getClass() == Marschall.class) {
				grid.move(i, j, n, m);
				statusLine = "Spion erwischt den Marschall!";
			} else if(from_figure.getClass() == Mineur.class && to_figure.getClass() == Bombe.class) {
				grid.move(i, j, n, m);
				statusLine = "Mineur entschärft Bombe!";
			} else if(to_figure.getClass() == Bombe.class) {
				grid.getField(i, j).deleteFigure();
				statusLine = "Bombe!";
			} else if(to_figure.getClass() == Fahne.class) {
				statusLine = "Spieler " + to_figure.getPlayer().getPlayerName() + " hat das Spiel gewonnen!!!";
				status = GameStatus.END;
			} else {
				if(from_figure.getStrength() > to_figure.getStrength()) {
					grid.getField(n, m).deleteFigure();
					statusLine = from_figure.getName() + "("+ from_figure.getPlayer().getPlayerName() + ") besiegt "
								+ to_figure.getName() + "("+ to_figure.getPlayer().getPlayerName() + ")!";
				} else if(from_figure.getStrength() < to_figure.getStrength()) {
					grid.getField(i, j).deleteFigure();
					statusLine = to_figure.getName() + "("+ to_figure.getPlayer().getPlayerName() + ") besiegt "
								+ from_figure.getName() + "("+ from_figure.getPlayer().getPlayerName() + ")!";
				} else {
					grid.getField(i, j).deleteFigure();
					grid.getField(n, m).deleteFigure();
					statusLine = "Unentschieden! Beide Krieger fallen (" + from_figure.getName() + ")!";
				}
			}
		} else {
			grid.move(i, j, n, m);
			statusLine = "Figur wurde bewegt!";
		}
	}

	public Player getPlayer() {
		return this.player;
	}

    public String getStatus() {
        return statusLine;
    }

    public GameStatus getGameStatus() {
        return this.status;
    }

    public void setGameStatus(GameStatus e) {
        status = e;
    }

    public void changePlayer() {
		nextPlayer();
		statusLine = "Spieler " + player.getPlayerName() + " ist an der Reihe!";
    	notifyObservers();
	}

    private void nextPlayer() {
    	if(playerList.get(0).equals(player)) {
    		player = playerList.get(1);
    	} else {
    		player = playerList.get(0);
    	}
    }

    public static int letterCode(char c) {
       if(Character.isLetter(c) && Character.isLowerCase(c))
        return c - 'a';
       if(Character.isLetter(c) && Character.isUpperCase(c))
        return c - 'A';
       throw new IllegalArgumentException("letter: " + c);
    }

	public int getNumber_figures() {
		return number_figures;
	}

	public void setNumber_figures(int number_figures) {
		this.number_figures = number_figures;
	}

	public void incNumber_figures() {
		this.number_figures--;
	}

	public void decNumber_figures() {
		this.number_figures++;
	}

	public void ready_or_not() {
		if(playerList.get(0).get_count_figures() == 40 && playerList.get(1).get_count_figures() == 40) {
			status = GameStatus.FIGHT;
			statusLine = "Lasset die Schlacht beginnen!";
		}
		notifyObservers();
	}

	public String printGrid() {
		return grid.toString(this.getGameStatus(), player);
	}

	public void show_all() {
		grid.toString(status, player);
	}
}