package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.application.StageInitializer;
import de.htwsaar.prog3.carrental.util.DialogUtils;
import de.htwsaar.prog3.carrental.util.MessageUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * Base controller used for dependency injection of commonly used fields.
 */
@Getter
abstract class BaseController {

    private StageInitializer stageInitializer;
    private DialogUtils dialogUtils;
    private MessageUtils messageUtils;
    private Validator validator;

    @Autowired
    final void setStageInitializer(StageInitializer stageInitializer) {
        this.stageInitializer = stageInitializer;
    }

    @Autowired
    final void setDialogUtils(DialogUtils dialogUtils) {
        this.dialogUtils = dialogUtils;
    }

    @Autowired
    final void setMessageUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Autowired
    final void setValidator(Validator validator) {
        this.validator = validator;
    }
}
