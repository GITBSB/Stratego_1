package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Spion extends AFigures implements IFigure {

	public Spion(Player player) {
		super(1, player, 1, "Spion");
	}

	public String toString() {
		return "[01]";
	}
}
