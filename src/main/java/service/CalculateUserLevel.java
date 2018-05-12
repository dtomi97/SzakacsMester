package service;

import UserDAO.UserDAO;
import app.Main;
import javafx.collections.ObservableList;
import model.Users;
import org.json.simple.JSONObject;

public class CalculateUserLevel {

    public static Long globalUserExp;
    private Long szint = 0L;
    private Long userExp = 1L;
    private UserDAO ud;
    Users user;

    public CalculateUserLevel(UserDAO ud, Users user) {
        this.ud = ud;
        this.user = user;
        szint = user.getSzint();
        userExp = user.getUserTP() + globalUserExp;
    }

    public CalculateUserLevel() {

    }

    public Long updateExp(ObservableList observableList, int size) {


        JSONObject[] jsonObjects = new JSONObject[Main.jsonLoader.getSizeOfRoot()];
        jsonObjects = Main.jsonLoader.getExp();

        for (int i = 0; i < size; i++) {
            JSONObject nameObject = (JSONObject) jsonObjects[i].get("Kaja");
            String name = (String) nameObject.get("Név");
            if(observableList.stream().anyMatch(e -> e.equals(name))){
                Long exp = (Long) nameObject.get("exp");
                Long foodlvl = (Long) nameObject.get("Szint");
                userExp += exp * (foodlvl / szint);
            }
        }

        ud.updateUserExp(user, userExp);

        return userExp;
    }

    public static void updateExpByTest(int score) {
        globalUserExp = score * 100L;
    }


    public void updateLevel(){

        int unit = 1000;
        int[] units = new int[10];
        units[0] = unit;
        for(int i = 1; i < 10; i++){
            units[i] = (int) (units[i-1] * 1.5);
        }
        for(int i = 0; i<10; i++ ){
            if(szint == (i+1) && userExp > units[i]){
                szint++;
                ud.updateUserlevel(user, szint);
                ud.updateUserExp(user, 1L);
            }
        }
    }
}
