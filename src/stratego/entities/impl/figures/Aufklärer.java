package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Aufkl�rer extends AFigures implements IFigure{

	public Aufkl�rer(Player player) {
		super(2, player, 8, "Aufkl�rer");
	}

	public String toString() {
		return "[02]";
	}
}