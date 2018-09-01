package de.htwsaar.prog3.carrental.i18n;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test class for Internationalization and String Externalisation.
 *
 * @author Lukas Raubuch
 */
class I18nComponentsUtilTest {
    @Test
    void testReturnGermanStringStageTitle() {
        Locale germanLocale = new Locale("de");
        Locale.setDefault(germanLocale);

        assertThat(I18nComponentsUtil.getStageTitleString(), is(equalTo("Autoverwaltung")));
    }

    @Test
    @Disabled("Only one of the Locale tests can be run without the other failing.\n"
            + "This is due to the static initialization of the ResourceBundle...")
    void testReturnEnglishStringStageTitle() {
        Locale englishLocale = new Locale("en");
        Locale.setDefault(englishLocale);

        assertThat(I18nComponentsUtil.getStageTitleString(), is(equalTo("Carrental")));
    }
}
