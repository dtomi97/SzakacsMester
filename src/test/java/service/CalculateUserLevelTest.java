package service;

import app.Main;
import control.MainController;
import control.RegAndLogController;
import javafx.application.Application;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class CalculateUserLevelTest {

    @Test
    public void updateExp() throws IOException, ParseException {
        Thread thread = new Thread(() -> {
            try{
                Application.launch(Main.class);
            }catch (Throwable t){
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, t);
            }

        });
        thread.setDaemon(true);
        thread.start();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.actualUserName = "alma";
        MainController mainController = new MainController();
        CalculateUserLevel calculateUserLevel = new CalculateUserLevel(new RegAndLogController().ud, new RegAndLogController().ud.find(Main.actualUserName).get(0));
        assertNotNull(calculateUserLevel.updateExp(mainController.getObservableList(), mainController.getObservableList().size()));
        thread.interrupt();
    }
}