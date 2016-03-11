package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class General extends AFigures implements IFigure {

	public General(Player player) {
		super(9, player, 1, "General");
	}

	public String toString() {
		return "[09]";
	}
}
