package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Marschall extends AFigures implements IFigure {
	public Marschall(Player player) {
		super(10, player, 1, "Marschall");
	}

	public String toString() {
		return "[10]";
	}
}
