package service;

import app.Main;
import javafx.application.Application;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class JsonLoaderTest {

    @Test
    public void getSizeOfRoot() throws IOException, ParseException {
        JsonLoader jsonLoader = new JsonLoader();
        assertNotNull(jsonLoader.getSizeOfRoot());
    }

    @Test
    public void listAllReceptNameFromFile() throws IOException, ParseException {
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
        JsonLoader jsonLoader = new JsonLoader();
        assertNotNull(jsonLoader.listAllReceptNameFromFile());
        thread.interrupt();
    }

    @Test
    public void getRandomReceptFromFile() throws IOException, ParseException {
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
        JsonLoader jsonLoader = new JsonLoader();
        assertNotNull(jsonLoader.getRandomReceptFromFile());
        thread.interrupt();
    }

    @Test
    public void getReceptForClickedFood() throws IOException, ParseException {
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
        JsonLoader jsonLoader = new JsonLoader();
        assertNotNull(jsonLoader.getReceptForClickedFood("Töltött bundáskenyér"));
        thread.interrupt();
    }

    @Test
    public void getExp() throws IOException, ParseException {
        JsonLoader jsonLoader = new JsonLoader();
        assertNotNull(jsonLoader.getExp());
    }
}