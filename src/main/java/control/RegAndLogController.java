package control;

import app.Main;
import service.JsonWriter;
import service.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.UserValidator;

import UserDAO.UserDAO;
import UserDAO.UserDAOFactory;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegAndLogController implements Initializable {

    @FXML
    public AnchorPane loginImage;
    @FXML
    public TextField userName;
    @FXML
    public PasswordField pwd;
    @FXML
    public Button loginButton;
    @FXML
    public Button registrationButton;
    @FXML
    public Label feedbackLabel;

    public UserDAO ud;

    private SceneChanger sceneChanger = new SceneChanger();

    @FXML
    private void handleRegistration(ActionEvent event) throws IOException, ParseException {
        UserValidator v = new UserValidator(ud);

        Main.actualUserName = userName.getText();

        if (v.regUsernameisUnique(Main.actualUserName)) {
            if(v.regUsernameValidate(Main.actualUserName)) {
                if (v.regPasswordValidate(pwd.getText())) {
                    ud.createUser(Main.actualUserName, pwd.getText(), 1L, 1L);
                    Main.actualUserlevel =  ud.find(Main.actualUserName).get(0).getSzint();

                    JsonWriter jsonWriter = new JsonWriter();
                    jsonWriter.createObjectForCurrentUser();

                    sceneChanger.napisceneChange(Main.getWindow(), "/fxml/NapiAjanlat.fxml");

                    PopUpWindowController.display("Teszt");
                }
                else
                    feedbackLabel.setText("A felhasználónévnek legalább 6 karaktert tartalmaznia kell! " +
                            "\n and contain at least one of each of these types: \n Upper-case letters, Numbers, " +
                            "\n Special characters: !, $, #, +, -, %");
            }
            else
                feedbackLabel.setText("A felhasználónévnek legalább 6 karaktert tartalmaznia kell!");
        } else {
            feedbackLabel.setText("A felhasználónév foglalt!");
        }
    }


    @FXML
    private void handleLoginBtn(ActionEvent event) throws IOException, ParseException {

        Main.actualUserName = userName.getText();

        UserValidator v= new UserValidator(ud);
        if(v.loginValidate(userName.getText(), pwd.getText())){
            feedbackLabel.setText("Sikeres Belépés!");
            Main.actualUserlevel = ud.find(Main.actualUserName).get(0).getSzint();

            sceneChanger.napisceneChange(Main.getWindow(), "/fxml/NapiAjanlat.fxml");

        }
        else
            feedbackLabel.setText("Hibás felhasználónév vagy jelszó!");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ud = UserDAOFactory.getInstance().createUserDAO();
    }

}
