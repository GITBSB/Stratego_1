package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Bombe extends AFigures implements IFigure {

	public Bombe(Player player) {
		super(100, player, 6, "Bombe");
	}

	public String toString() {
		return "[BO]";
	}
}
