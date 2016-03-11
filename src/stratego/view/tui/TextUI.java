package stratego.view.tui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import stratego.controller.IController;
import stratego.entities.FigureSet;
import stratego.entities.impl.GameStatus;
import stratego.util.observer.IObserver;

public class TextUI implements IObserver{
	private IController controller;
    private static final String NEWLINE = System.getProperty("line.separator");
    private Logger logger = Logger.getLogger("stratego.view.tui");
    private static Scanner scanner;

    @Inject
    public TextUI(IController controller) {
    	this.controller = controller;
    	this.controller.addObserver(this);
    }

	public void update(GameStatus e) {
		if(e == GameStatus.END) {
			controller.show_all();
		} else if(e != GameStatus.NOT_STARTED) {
			logger.info(controller.printGrid());
		}
		printTUI();
	}

    public boolean userinputselection(String next) {
    	scanner = new Scanner(System.in);
        boolean go = true;
        switch (next) {
        case"q":
            go = false;
            break;
        case"n":
        	controller.testsetup();
        	/*logger.info("Name Spieler 1?");
        	String player1 = scanner.next();
        	logger.info("Name Spieler 2?");
        	String player2 = scanner.next();
        	controller.enterPlayernames(player1, player2);
        	controller.startGame();*/
        	break;
        case"f":
        	if(controller.getGameStatus() != GameStatus.SET_FIGURES) {
        		break;
        	}
        	FigureSet fs = controller.getPlayer().getFigureSet();
        	logger.info("\nW�hlen Sie eine Figur aus:\n"
        			+ "S - Spion_1 (" + fs.getfigure("S").getAnz() + ")\n"
        			+ "A - Aufkl�rer_2 (" + fs.getfigure("A").getAnz() + ")\n"
        			+ "Mi - Mineur_3 (" + fs.getfigure("Mi").getAnz() + ")\n"
        			+ "U - Unteroffizier_4 (" + fs.getfigure("U").getAnz() + ")\n"
        			+ "L - Leutnant_5 (" + fs.getfigure("L").getAnz() + ")\n"
        			+ "H - Hauptmann_6 (" + fs.getfigure("H").getAnz() + ")\n"
        			+ "Ma - Major_7 (" + fs.getfigure("Ma").getAnz() + ")\n"
        			+ "O - Oberst_8 (" + fs.getfigure("O").getAnz() + ")\n"
        			+ "G - General_9 (" + fs.getfigure("G").getAnz() + ")\n"
        			+ "M - Marschall_10 (" + fs.getfigure("M").getAnz() + ")\n"
        			+ "B - Bombe (" + fs.getfigure("B").getAnz() + ")\n"
        			+ "F - Fahn (" + fs.getfigure("F").getAnz() + ")");
        	String figure = scanner.next();

        	logger.info("Koordinaten: Zahl,Buchstabe ");
        	String koord = scanner.next();
        	String[] tmpkoord = koord.split(",");
        	controller.setFigure(tmpkoord[1].charAt(0), Integer.parseInt(tmpkoord[0]), figure);
        	break;
        case"c":
        	controller.changePlayer();
        	break;
        case"m":
        	if(controller.getGameStatus() != GameStatus.FIGHT) {
        		break;
        	}
        	logger.info("Welche Figur soll bewegt/angreifen? Koordinaten: Zahl,Buchstabe");
        	String koord2 = scanner.next();
        	String[] tmpkoord2 = koord2.split(",");
        	logger.info("Wohin/Wen? Koordinaten: Zahl,Buchstabe");
        	String koord3 = scanner.next();
        	String[] tmpkoord3 = koord3.split(",");
        	controller.move_attack(Integer.parseInt(tmpkoord2[0]), tmpkoord2[1].charAt(0),
        					Integer.parseInt(tmpkoord3[0]), tmpkoord3[1].charAt(0));
        	break;
        case"r":
        	controller.ready_or_not();
        	break;
    	}
        return go;
    }

	public void printTUI() {
		logger.info(NEWLINE + controller.getStatus());
		logger.info(NEWLINE
				+ "Possible commands: q-quit, n-new game, f-setFigure, c-change player, r-ready, m-move/attack");
    }
}