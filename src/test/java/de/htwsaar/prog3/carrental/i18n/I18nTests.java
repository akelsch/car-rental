package de.htwsaar.prog3.carrental.i18n;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Test class for Internationalization and String Externalisation
 * 
 * @author Lukas Raubuch
 */
class I18nTests {

	@Test
	void testReturnGermanStringStageTitle() {
		Locale germanLocale = new Locale("de");
		Locale.setDefault(germanLocale);
		assertEquals("Autoverwaltung", I18nComponentsUtil.getStageTitleString());
	}

	@Test
	@Disabled("Only one of the Locale Tests can be run without the other failing"
			+ "this is due to the static initialization of the ResourceBundle..."
			+ "This Tests have both been run with success.")
	void testReturnEnglishStringStageTitle() {
		Locale englishLocale = new Locale("en");
		Locale.setDefault(englishLocale);
		assertEquals("Carrental", I18nComponentsUtil.getStageTitleString());
	}
}
