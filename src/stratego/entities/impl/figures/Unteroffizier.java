package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Unteroffizier extends AFigures implements IFigure {

	public Unteroffizier(Player player) {
		super(4, player, 4, "Unteroffizier");
	}

	public String toString() {
		return "[04]";
	}
}
