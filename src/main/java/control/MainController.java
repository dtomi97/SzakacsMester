package control;

import UserDAO.UserDAO;
import app.Main;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import service.CalculateUserLevel;
import service.FoodValidator;
import service.JsonWriter;
import service.SceneChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.simple.parser.ParseException;
import UserDAO.UserDAOFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class MainController implements Initializable {
    @FXML
    public Hyperlink Profil;
    @FXML
    public Hyperlink NapiAjanlat;
    @FXML
    public Hyperlink Receptek;
    @FXML
    public Hyperlink Kilepes;
    @FXML
    public ListView achievment_list;
    @FXML
    public Label menuNameLabel;
    @FXML
    public Label ReceptnameLabel;
    @FXML
    public Label NapinameLabel;
    @FXML
    public Label ReceptlvlLabel;
    @FXML
    public Label NapilvlLabel;
    @FXML
    public Label menulvlLabel;
    @FXML
    public Label actualFoodNameLabel;
    @FXML
    public Label actualFoodRecept;
    @FXML
    public Label ReceptLeirasLabel;
    @FXML
    public ListView<String> ReceptNameList = new ListView<>();
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public TextField keszEtelTextField;
    @FXML
    public Button doneButton;
    @FXML
    private ListView<String> food_done_list = new ListView<>();

    private JsonWriter jsonWriter = new JsonWriter();
    private FoodValidator foodValidator = new FoodValidator();


    private UserDAO ud;

    private ObservableList observableList;

    SceneChanger sceneChanger = new SceneChanger();

    public MainController() throws IOException, ParseException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ud = UserDAOFactory.getInstance().createUserDAO();

        Profil.setBorder(Border.EMPTY);

        observableList = FXCollections.observableArrayList();
        food_done_list.setFocusTraversable(false);
        try {
            for (int i = 0; i <jsonWriter.loadData().length; i++) {
                if(jsonWriter.loadData()[i] != null) {
                    observableList.add(jsonWriter.loadData()[i]);
                }
            }
            food_done_list.setItems(observableList);
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleContentButton(ActionEvent event ) throws IOException, ParseException {
        if (Profil.isVisited()) {
            sceneChanger.menuSceneChange(Main.getWindow(),"/fxml/menu.fxml");
            Profil.setVisited(false);
        } else if (Receptek.isVisited()) {
            sceneChanger.receptSceneChange(Main.getWindow(), "/fxml/Receptek.fxml");
            Receptek.setVisited(false);
        } else if (NapiAjanlat.isVisited()) {
            sceneChanger.napisceneChange(Main.getWindow(), "/fxml/NapiAjanlat.fxml");
            NapiAjanlat.setVisited(false);
        }
    }


    @FXML
    private void showReceptonClick(){
        String[] leiras = Main.jsonLoader.getReceptForClickedFood(ReceptNameList.getSelectionModel().getSelectedItem());
        scrollPane.setVvalue(0.0);
        ReceptNameList
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> ReceptLeirasLabel.setText("Hozzávalók:\n" +leiras[0] + "\nLeírás:\n" + leiras[1]));
    }

    @FXML
    private void persistFood(ActionEvent event) throws IOException, ParseException {
        if (foodValidator.validateName(keszEtelTextField.getText())) {
            if (foodValidator.validateExists(keszEtelTextField.getText())) {
                jsonWriter.saveData(keszEtelTextField.getText());

                observableList.add(keszEtelTextField.getText());
                food_done_list.setItems(observableList);

                CalculateUserLevel calculateUserLevel = new CalculateUserLevel(ud, ud.find(Main.actualUserName).get(0));
                calculateUserLevel.updateExp(observableList, observableList.size());
                calculateUserLevel.updateLevel();
                sceneChanger.menuSceneChange(Main.getWindow(), "/fxml/menu.fxml");

            }
        }
    }

    public ObservableList getObservableList() {
        return observableList;
    }

    @FXML
    private void handleKilepesButton(ActionEvent event){
        exit(0);
    }
}
