package service;

import app.Main;
import javafx.application.Application;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class FoodValidatorTest {

    @Test
    public void validateExists() throws IOException, ParseException {
        FoodValidator foodValidator = new FoodValidator();
        assertTrue(foodValidator.validateExists("Tomcsika12"));
    }

    @Test
    public void validateName() throws IOException, ParseException {
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
        FoodValidator foodValidator = new FoodValidator();
        assertFalse(foodValidator.validateName("Barbarossa1"));
        thread.interrupt();

    }

    @Test
    public void getSizeOfRootTest() throws IOException, ParseException {
        FoodValidator foodValidator = new FoodValidator();
        assertNotNull(foodValidator.getSizeOfRoot());
    }
}