package stratego.entities.impl;

import stratego.entities.IField;
import stratego.entities.IFigure;
import stratego.entities.impl.figures.NoFigure;

public class Field implements IField{
	private FieldStatus fs;
	private IFigure figure;
	public Field(FieldStatus fs) {
		this.fs = fs;
		figure = new NoFigure();
	}

	@Override
	public IFigure setFigure(IFigure figure) {
		deleteFigure();
		this.figure = figure;
		figure.decAnz();
		setFieldStatus(figure.getPlayer().getFieldStatus());
		return figure;
	}

	@Override
	public IFigure getFigure() {
		return this.figure;
	}

	public IFigure deleteFigure() {
		IFigure figure_old = this.figure;
		this.figure = new NoFigure();
		figure_old.incAnz();
		fs = FieldStatus.EMPTY_FIELD;
		return figure_old;
	}

	public void setFieldStatus(FieldStatus fs) {
		this.fs = fs;
	}

	@Override
	public FieldStatus getFieldstatus() {
		return fs;
	}

	public String toString(GameStatus gs, Player player) {
		if(gs == GameStatus.END) {
			return figure.toString();
		}
		else if(getFieldstatus() == FieldStatus.NO_FIELD || figure.getPlayer() != player) {
			return fs.toString();
		} else {
			return figure.toString();
		}
	}
}
