package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.util.DialogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController {

    public DialogUtils dialogUtils;

    @Autowired
    final void setDialogUtils(DialogUtils dialogUtils) {
        this.dialogUtils = dialogUtils;
    }
}
