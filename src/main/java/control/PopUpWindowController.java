package control;


import UserDAO.UserDAO;
import UserDAO.UserDAOFactory;
import service.SceneChanger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpWindowController implements Initializable {
    @FXML
    public ChoiceBox elso_kerdes;
    @FXML
    public ChoiceBox masodik_kerdes;
    @FXML
    public ChoiceBox harmadik_kerdes;
    @FXML
    public ChoiceBox negyedik_kerdes;
    @FXML
    public ChoiceBox otodik_kerdes;
    @FXML
    public ChoiceBox hatodik_kerdes;
    @FXML
    public ChoiceBox hetedik_kerdes;
    @FXML
    public ChoiceBox nyolcadik_kerdes;
    @FXML
    public ChoiceBox kilencedik_kerdes;
    @FXML
    public ChoiceBox tizedik_kerdes;
    @FXML
    public Button finishedTest;

    private UserDAO ud;


    private static Stage window = new Stage();

    public int score = 0;

    private ChoiceBox[] questions = new ChoiceBox[10];

    public PopUpWindowController() {
        initializeQuestions();
    }

    static void display(String title) throws IOException{
        Parent root = FXMLLoader.load(PopUpWindowController.class.getResource("/fxml/popUp.fxml"));

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(800);
        window.setHeight(800);
        window.setOnCloseRequest(Event::consume);

        Scene scene = new Scene(root);
        window.setScene(scene);

        window.show();
    }

    private ChoiceBox[] initializeQuestions(){
        questions[0] = elso_kerdes;
        questions[1] = masodik_kerdes;
        questions[2] = harmadik_kerdes;
        questions[3] = negyedik_kerdes;
        questions[4] = otodik_kerdes;
        questions[5] = hatodik_kerdes;
        questions[6] = hetedik_kerdes;
        questions[7] = nyolcadik_kerdes;
        questions[8] = kilencedik_kerdes;
        questions[9] = tizedik_kerdes;

        return questions;
    }

    public boolean questionsAreAnswered(ChoiceBox[] choiceBoxes){
        boolean allAnswered = true;

        for (ChoiceBox question: choiceBoxes
             ) {
            if(question.getSelectionModel().isEmpty()){
                allAnswered = false;
                System.out.println("nem töltötted ki mindet");
            }
        }
        return allAnswered;
    }

    private int getScoreFromTest(){
        int szamlalo = 0;

        System.out.println(questions[0].getValue());

        if(questions[0].getValue().equals("liszt, olaj/zsír, víz")){
            szamlalo++;
        }
        if(questions[1].getValue().equals("tejföl")){
            szamlalo++;
        }
        if(questions[2].getValue().equals("Krumplis tészta")){
            szamlalo++;
        }
        if(questions[3].getValue().equals("liszt, tojás, zsemlemorzsa")){
            szamlalo++;
        }
        if(questions[4].getValue().equals("10 dl")){
            szamlalo++;
        }
        if(questions[5].getValue().equals("Nem kell hozzá")){
            szamlalo++;
        }
        if(questions[6].getValue().equals("igen")){
            szamlalo++;
        }
        if(questions[7].getValue().equals("lekvár")){
            szamlalo++;
        }
        if(questions[8].getValue().equals("frankfurtileves")){
            szamlalo++;
        }
        if(questions[9].getValue().equals("olajat rakok a tésztavízbe")){
            szamlalo++;
        }

        return szamlalo;
    }

    private void changePopUp() throws IOException{
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.sceneChange(window, "/fxml/userInformation.fxml");

        window.setWidth(600);
        window.setHeight(400);
        window.setOnCloseRequest(Event::consume);

        window.show();

    }

    @FXML
    public void handleTestData() throws IOException{
        if(questionsAreAnswered(initializeQuestions())){
            score = getScoreFromTest();

            changePopUp();
        }
    }

    @FXML
    private void closeLastPopUpMessage(){
        window.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ud = UserDAOFactory.getInstance().createUserDAO();
    }
}





































