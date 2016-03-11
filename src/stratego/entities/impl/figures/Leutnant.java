package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Leutnant extends AFigures implements IFigure {

	public Leutnant(Player player) {
		super(5, player, 4, "Leutnant");
	}

	public String toString() {
		return "[05]";
	}
}