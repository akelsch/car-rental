package de.htwsaar.prog3.carrental.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Utility class that provides internationalized application strings.
 *
 * @author Lukas Raubuch
 * @author Jens Thewes
 * @author Julian Quint
 * @author Arthur Kelsch
 */
@Component
public class MessageUtils {

    public static final String STAGE_TITLE = "stage.title";
    public static final String DIALOG_INFORMATION_ABOUT = "dialog.information.about";
    public static final String DIALOG_CONFIRMATION_CANCEL = "dialog.confirmation.cancel";
    public static final String DIALOG_CONFIRMATION_DELETE = "dialog.confirmation.delete";
    public static final String DIALOG_ERROR_DELETE = "dialog.error.delete";
    public static final String DIALOG_ERROR_DELETE_DETAILS = "dialog.error.delete.details";
    public static final String DIALOG_ERROR_VALIDATION = "dialog.error.validation";

    private final MessageSourceAccessor messageSourceAccessor;

    @Getter
    private final ResourceBundle resourceBundle;

    @Autowired
    public MessageUtils(MessageSource messageSource) {
        messageSourceAccessor = new MessageSourceAccessor(messageSource);
        resourceBundle = new MessageSourceResourceBundle(messageSource, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code) {
        return messageSourceAccessor.getMessage(code);
    }

}
