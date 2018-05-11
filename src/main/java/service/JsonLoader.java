package service;

import app.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonLoader {

    String[] strings ;
    String[] foodNames;
    String[] foodRecept;
    JSONParser jsonParser;
    Reader fileReader;
    Object object;
    JSONArray root;

    public JsonLoader() throws IOException, ParseException {
        jsonParser = new JSONParser();
        fileReader = new FileReader("src/kaja.json");
        object = jsonParser.parse(fileReader);
        root = (JSONArray) object;
        strings = new String[3];
        foodNames = new String[getSizeOfRoot()];
        foodRecept = new String[2];
    }

    public int getSizeOfRoot() {
        return root.size();
    }

    public String[] listAllReceptNameFromFile() {
        for(int i= 0; i < getSizeOfRoot(); i++){

            JSONObject jsonObject =(JSONObject) root.get(i);

            JSONObject nameObject = (JSONObject) jsonObject.get("Kaja");

            foodNames[i] =(String) nameObject.get("Név");
        }

        return foodNames;
    }

    public String[] getRandomReceptFromFile() {

        JSONObject food = (JSONObject) root.get(Main.randomFoodIndex);

        JSONObject foodObject = (JSONObject) food.get("Kaja");

        strings[0] = (String) foodObject.get("Név");
        strings[1] = (String) foodObject.get("Hozzávalók");
        strings[2] = (String) foodObject.get("Elkészítés");

        return strings;
    }

    public String[] getReceptForClickedFood(String name){

        for(int i= 0; i < getSizeOfRoot(); i++){

            JSONObject jsonObject =(JSONObject) root.get(i);

            JSONObject nameObject = (JSONObject) jsonObject.get("Kaja");
            if (nameObject.get("Név").equals(name)) {
                foodRecept[0] = (String) nameObject.get("Hozzávalók");
                foodRecept[1] = (String) nameObject.get("Elkészítés");
            }
        }
        return foodRecept;
    }

    public JSONObject[] getExp(){
        JSONObject[] jsonObjects = new JSONObject[getSizeOfRoot()];
        for(int i= 0; i < getSizeOfRoot(); i++){
            jsonObjects[i] =(JSONObject) root.get(i);
        }

        return jsonObjects;
    }

}
