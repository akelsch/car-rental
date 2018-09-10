package de.htwsaar.prog3.carrental.util.i18n;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the {@link I18nComponentsUtil} class.
 *
 * @author Lukas Raubuch
 */
@Disabled("Disabled as I18nComponentsUtil can be initialized beforehand resulting in wrong locales.\n"
        + "Due to the static initialization of the ResourceBundle only one test can be run at a time!")
class I18nComponentsUtilTest {
    @Test
    void testGetStageTitleStringGerman() {
        Locale.setDefault(new Locale("de"));

        assertThat(I18nComponentsUtil.getStageTitle(), is(equalTo("Autoverwaltung")));
    }

    @Test
    void testGetStageTitleStringEnglish() {
        Locale.setDefault(new Locale("en"));

        assertThat(I18nComponentsUtil.getStageTitle(), is(equalTo("Car Rental")));
    }
}
