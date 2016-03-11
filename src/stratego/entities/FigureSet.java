package stratego.entities;

import java.util.HashMap;
import java.util.Map;

import stratego.entities.impl.Player;
import stratego.entities.impl.figures.Aufklärer;
import stratego.entities.impl.figures.Bombe;
import stratego.entities.impl.figures.Fahne;
import stratego.entities.impl.figures.General;
import stratego.entities.impl.figures.Hauptmann;
import stratego.entities.impl.figures.Leutnant;
import stratego.entities.impl.figures.Major;
import stratego.entities.impl.figures.Marschall;
import stratego.entities.impl.figures.Mineur;
import stratego.entities.impl.figures.Oberst;
import stratego.entities.impl.figures.Spion;
import stratego.entities.impl.figures.Unteroffizier;

public class FigureSet {
	private Map<String, IFigure> figures;
	public FigureSet(Player player) {
		figures = new HashMap<String, IFigure>();
		figures.put("S", new Spion(player));
		figures.put("A", new Aufklärer(player));
		figures.put("Mi", new Mineur(player));
		figures.put("U", new Unteroffizier(player));
		figures.put("L", new Leutnant(player));
		figures.put("H", new Hauptmann(player));
		figures.put("Ma", new Major(player));
		figures.put("O", new Oberst(player));
		figures.put("G", new General(player));
		figures.put("M", new Marschall(player));
		figures.put("B", new Bombe(player));
		figures.put("F", new Fahne(player));
	}

	public IFigure getfigure(String s) {
		return figures.get(s);
	}
}