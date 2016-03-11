package stratego.entities.impl.figures;

import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class NoFigure implements IFigure {

	public NoFigure() {

	}

	public String toString() {
		return "[  ]";
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public Player getPlayer() {
		return null;
	}

	@Override
	public int getAnz() {
		return 0;
	}

	@Override
	public void incAnz() {
	}

	@Override
	public void decAnz() {
	}

	@Override
	public String getName() {
		return null;
	}
}