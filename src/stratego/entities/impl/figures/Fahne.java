package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Fahne extends AFigures implements IFigure {

	public Fahne(Player player) {
		super(50, player, 1, "Fahne");
	}

	public String toString() {
		return "[FF]";
	}
}
