package service;

import app.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FoodValidator {


    private JSONParser jsonParser;
    private Reader fileReader;
    private Object object;
    private JSONArray root;


    public FoodValidator() throws IOException, ParseException {
        jsonParser = new JSONParser();
        fileReader = new FileReader("src/elkészítettÉtelek.json");
        object = jsonParser.parse(fileReader);
        root = (JSONArray) object;
    }

    public boolean validateExists(String name){

        boolean exists = true;

        for(int i= 0; i < getSizeOfRoot(); i++){
            JSONObject[] jsonObjects = new JSONObject[root.size()];

            jsonObjects[i] = (JSONObject) root.get(i);

            JSONArray jsonArray = (JSONArray) jsonObjects[i].get(Main.actualUserName);

            if (!(jsonArray == null)) {
                for (int j = 0; j < jsonArray.size(); j++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                    if (jsonObject.get("Név").equals(name)) {
                        exists = false;
                    }
                }
            }

        }

        return exists;

    }

    public boolean validateName(String name) throws IOException, ParseException {

        boolean exists = false;

        Reader reader = new FileReader("src/kaja.json");
        Object jsonObject1 = jsonParser.parse(reader);

        JSONArray jsonArray  =(JSONArray) jsonObject1;

        for(int i= 0; i < Main.jsonLoader.getSizeOfRoot(); i++){

            JSONObject jsonObject =(JSONObject) jsonArray.get(i);

            JSONObject nameObject = (JSONObject) jsonObject.get("Kaja");

            String foodName =(String) nameObject.get("Név");
            if (foodName.equals(name)) {
                exists = true;
            }
        }

        return exists;
    }

    public int getSizeOfRoot() {
        return root.size();
    }
}
