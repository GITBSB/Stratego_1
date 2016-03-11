package stratego.game;

import java.util.Scanner;

import javax.swing.UIManager;

import org.apache.log4j.PropertyConfigurator;

import stratego.controller.IController;
import stratego.controller.impl.Controller;
import stratego.view.tui.TextUI;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Stratego {
	private static Scanner scanner;
    private static TextUI tui;
    private IController controller;
    private static Stratego instance = null;

    private Stratego() {
    	controller = new Controller();
    	tui = new TextUI(controller);

    	// Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");

        // Set up Google Guice Dependency Injector
        Injector injector = Guice.createInjector(new StrategoModule());

        // Build up the application, resolving dependencies automatically by
        // Guice
        controller = injector.getInstance(IController.class);

//        @SuppressWarnings("unused")
        //StrategoFrame gui = injector.getInstance(StrategoFrame.class);

        tui = injector.getInstance(TextUI.class);

        tui.printTUI();

        // Create an initial game
        controller.createnewgame();
    }
	public static Stratego getInstance() {
		if (instance == null) {
			instance = new Stratego();
		}
		return instance;
	}

    public static void main(final String[] args) throws Exception {
    	Stratego.getInstance();
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    	// reads input on the tui continuesly until user quits
    	boolean go = true;
        scanner = new Scanner(System.in);
        while (go) {
            go = tui.userinputselection(scanner.next());
        }
    }
}