package app;

import UserDAO.UserDAOFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import service.JsonLoader;
import service.JsonWriter;
import service.SceneChanger;

import java.io.IOException;
import java.util.Random;

public class Main extends Application {

    private static Stage window = new Stage();

    public static int randomFoodIndex;

    public static String actualUserName;
    public static Long actualUserlevel;

    public static JsonLoader jsonLoader;

    static {
        try {
            jsonLoader = new JsonLoader();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private SceneChanger sceneChanger = new SceneChanger();

    public static void main(String[] args) {
        Random random = new Random();
        randomFoodIndex = random.nextInt(jsonLoader.getSizeOfRoot());

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        window.setTitle("SzakácsMester");

        sceneChanger.sceneChange(window, "/fxml/login.fxml");


        window.setResizable(false);
        window.show();

        window.setOnCloseRequest(e -> closeApp());

    }

    public static Stage getWindow(){
        return window;
    }

    public static void closeApp(){
        UserDAOFactory.getInstance().close();
    }
}
