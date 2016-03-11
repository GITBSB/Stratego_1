package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Hauptmann extends AFigures implements IFigure {

	public Hauptmann(Player player) {
		super(6, player, 4, "Hauptmann");
	}

	public String toString() {
		return "[06]";
	}
}
