package service;

import app.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collector;

public class JsonWriter {

    private JSONParser jsonParser;
    private Reader fileReader;
    private Object object;
    private JSONArray root;
    private JSONObject[] firstLevelObjects = new JSONObject[1000];
    private JSONObject[] secondLevelObjects = new JSONObject[1000];
    private JSONArray jsonArray;
    private String[] nevek = new String[100];

    public JsonWriter() {
    }

    public void saveData(String name) throws IOException, ParseException {
        loadData();

        JSONObject newData = new JSONObject();
        newData.put("Név", name);

        JSONArray arrayforUser = new JSONArray();
        arrayforUser.add(newData);
        for (JSONObject secondLevelObject : secondLevelObjects) {
            if (!(secondLevelObject == null)) {
                arrayforUser.add(secondLevelObject);
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Main.actualUserName, arrayforUser);

        JSONArray rootArray = new JSONArray();
        rootArray.add(jsonObject);


        char[] str = new char[8];
        jsonObject.toString().getChars(2, 10, str, 0);
        String string = String.valueOf(Arrays.copyOf(str, str.length));

        for (JSONObject firstLevelObject : firstLevelObjects) {

            if (!(firstLevelObject == null) && !(firstLevelObject.toString().contains(string))) {
                rootArray.add(firstLevelObject);
            }
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
        for(int i= 0; i < root.size(); i++){
            firstLevelObjects[i] = (JSONObject) root.get(i);

            jsonArray = (JSONArray) firstLevelObjects[i].get(Main.actualUserName);
            if (!(jsonArray == null)) {
                for (int j = 0; j < jsonArray.size(); j++) {
                    secondLevelObjects[j] = (JSONObject) jsonArray.get(j);
                    nevek[j] = (String) secondLevelObjects[j].get("Név");
                }
            }
        }
        return nevek;
    }

    public void createObjectForCurrentUser() throws IOException, ParseException {
        jsonParser = new JSONParser();
        fileReader = new FileReader("src/elkészítettÉtelek.json");
        object = jsonParser.parse(fileReader);
        root = (JSONArray) object;

        JSONObject actualObject = new JSONObject();
        actualObject.put("Név", " ");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(actualObject);

        JSONObject outerObject = new JSONObject();
        outerObject.put(Main.actualUserName, jsonArray);

        root.add(outerObject);

        try (FileWriter file = new FileWriter("src/elkészítettÉtelek.json")) {
            file.write(root.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
