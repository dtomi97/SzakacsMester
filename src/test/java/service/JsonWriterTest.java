package service;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonWriterTest {

    @Test
    public void loadData() throws IOException, ParseException {
    JsonWriter jsonWriter = new JsonWriter();
    assertNotNull(jsonWriter.loadData());
    }
}