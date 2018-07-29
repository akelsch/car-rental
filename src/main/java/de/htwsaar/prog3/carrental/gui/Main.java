package de.htwsaar.prog3.carrental.gui;

import java.util.Locale;

import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.i18n.I18nStringsUtil;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry point of the car rental application. Definition of primary stage and building of the
 * environment
 * 
 * @author Lukas Raubuch
 */
public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(I18nComponentsUtil.getStageTitleString());
//        this.primaryStage.getIcons().add(new Image(
//                Main.class.getResourceAsStream(I18nStringsUtil.getStagePathIconString())));
        this.primaryStage.show();
    }


    /**
     * main method of this Application
     * 
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}