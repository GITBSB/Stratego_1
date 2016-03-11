package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Aufklärer extends AFigures implements IFigure{

	public Aufklärer(Player player) {
		super(2, player, 8, "Aufklärer");
	}

	public String toString() {
		return "[02]";
	}
}