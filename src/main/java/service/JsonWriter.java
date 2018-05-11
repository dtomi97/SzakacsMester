package service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JsonWriter {

    JSONParser jsonParser;
    Reader fileReader;
    Object object;
    JSONArray root;
    String[] foodNames;
    JSONObject[] jsonObjects = new JSONObject[1000];
    String[] nevek = new String[1000];

    public JsonWriter() {
    }

    public void saveData(String name) throws IOException, ParseException {
        loadData();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Név", name);

        JSONArray rootArray = new JSONArray();
        rootArray.add(jsonObject);
        for(int i= 0; i<root.size(); i++){
            rootArray.add(jsonObjects[i]);
        }

        try(FileWriter file = new FileWriter("src/elkészítettÉtelek.json")){
            file.write(rootArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] loadData() throws IOException, ParseException {
        jsonParser = new JSONParser();
        fileReader = new FileReader("src/elkészítettÉtelek.json");
        object = jsonParser.parse(fileReader);
        root = (JSONArray) object;
        foodNames = new String[root.size()];
        for(int i= 0; i < root.size(); i++){
            jsonObjects[i] =(JSONObject) root.get(i);
            nevek[i] = (String) jsonObjects[i].get("Név");
        }
        return nevek;
    }
}
