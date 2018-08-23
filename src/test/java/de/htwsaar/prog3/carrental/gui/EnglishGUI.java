package de.htwsaar.prog3.carrental.gui;

import java.util.Locale;
import de.htwsaar.prog3.carrental.gui.CarTableView;

/**
 * Test class for english GUI
 *
 * @author Lukas Raubuch
 */
public class EnglishGUI {

	public static void main(String[] args) {
		Locale englishLocale = new Locale("en");
		Locale.setDefault(englishLocale);
		
		CarTableView.main(null);
	}

}
