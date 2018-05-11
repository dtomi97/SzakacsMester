package service;

import app.Main;
import control.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;

public class SceneChanger {

    public SceneChanger() {
    }

    public void sceneChange(Stage stage, String location) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(root));
    }

    public void menuSceneChange(Stage stage, String location) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(location));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        fxmlLoader.<MainController>getController().menuNameLabel.setText(Main.actualUserName);
        fxmlLoader.<MainController>getController().menulvlLabel.setText("Szint: " + Main.actualUserlevel);
    }

    public void napisceneChange(Stage stage, String location) throws IOException, ParseException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(location));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        fxmlLoader.<MainController>getController().NapinameLabel.setText(Main.actualUserName);
        fxmlLoader.<MainController>getController().NapilvlLabel.setText("Szint: " + Main.actualUserlevel);

        String[] properties = Main.jsonLoader.getRandomReceptFromFile();

        fxmlLoader.<MainController>getController().actualFoodNameLabel.setText(properties[0]);
        fxmlLoader.<MainController>getController().actualFoodRecept.setWrapText(true);
        fxmlLoader.<MainController>getController().actualFoodRecept.setText("Hozzávalók: " + properties[1] +
                "\n" + "\nElkészítés: " + properties[2]);
    }

    public void receptSceneChange(Stage stage, String location) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(location));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        fxmlLoader.<MainController>getController().ReceptnameLabel.setText(Main.actualUserName);
        fxmlLoader.<MainController>getController().ReceptlvlLabel.setText("Szint: " + Main.actualUserlevel);

        String[] properties = Main.jsonLoader.listAllReceptNameFromFile();
        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(Arrays.asList(properties));

        fxmlLoader.<MainController>getController().ReceptNameList.setItems(observableList);
        fxmlLoader.<MainController>getController().ReceptNameList.getSelectionModel().selectFirst();
    }
}
