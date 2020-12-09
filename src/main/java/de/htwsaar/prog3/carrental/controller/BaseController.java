package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.CarRentalUiApplication;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Base controller used for dependency injection of commonly used fields.
 */
@Component
abstract class BaseController {

    public CarRentalUiApplication application;
    public DialogUtils dialogUtils;
    public MessageUtils messageUtils;

    @Autowired
    final void setApplication(CarRentalUiApplication application) {
        this.application = application;
    }

    @Autowired
    final void setDialogUtils(DialogUtils dialogUtils) {
        this.dialogUtils = dialogUtils;
    }

    @Autowired
    final void setMessageUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }
}
