package streamingapi;

import com.fasterxml.jackson.core.*;

import java.io.File;
import java.io.IOException;

/**
 * Streaming API
 * <p>
 * Streaming API reads and writes JSON content as discrete events.
 * JsonParser reads the data whereas JsonGenerator writes the data.
 * It is most powerful approach among the three and is of lowest overhead
 * and fastest in read/write opreations. It is Analogus to Stax parser for XML.
 */
public class StreamingAPIExample {


        /*
        There are three majors ways to process JSON

        1. Streaming API - reads and writes JSON content as discrete events.
        JsonParser reads the data whereas JsonGenerator writes the data.

        2. Tree Model - prepares a in-memory tree representation of the JSON document.
        ObjectMapper build tree of JsonNode nodes. It is most flexible approach.

        3. Data Binding - converts JSON to and from POJO (Plain Old Java Object)
        using property accessor or using annotations.
            3.1 Simple Data Binding - Converts JSON to and from Java Maps,
            Lists, Strings, Numbers, Booleans and null objects.
            3.2 Full Data Binding - Converts JSON to and from any JAVA type.

         */

    public static void main(String[] args) {


        /*
        JsonGenerator - Write to JSON String.

        JsonParser - Parse JSON String.
         */

        /*
        -------------------------------------------

        JsonGenerator - write

        -------------------------------------------
         */
        JsonFactory factory = new JsonFactory();
        File jsonFile = new File("streamAPIJSon.json");


        try {
            JsonGenerator jsonGenerator = factory.createGenerator(jsonFile, JsonEncoding.UTF8);

            // create json object
            jsonGenerator.writeStartObject();

            // write fields/attributes
            jsonGenerator.writeStringField("name", "Curtis");
            jsonGenerator.writeNumberField("age", 24);

            // write array
            jsonGenerator.writeArrayFieldStart("marks");
            jsonGenerator.writeNumber(100);
            jsonGenerator.writeNumber(90);
            jsonGenerator.writeNumber(84);
            jsonGenerator.writeEndArray();

            // must close the generator
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        -------------------------------------

        JsonParser - Read

        -------------------------------------
         */

        // create json parser
        try {
            JsonParser jsonParser = factory.createParser(jsonFile);

            // read attribute as token
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                final String TAG = "JsonParser:";

                final String fieldName = jsonParser.getCurrentName();

                if ("name".equals(fieldName)) { // fieldName.equals("name") causes NullPointerException
                    jsonParser.nextToken();
                    System.out.println(TAG + "name:" + jsonParser.getText());
                }

                if ("age".equals(fieldName)) {
                    jsonParser.nextToken();
                    System.out.println(TAG + "age:" + jsonParser.getNumberValue());
                }

                if ("age".equals(fieldName)) {

                    jsonParser.nextToken();
                    System.out.print(TAG + "marks[");

                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.print(jsonParser.getNumberValue() + " ");
                    }
                    System.out.print("]");
                }
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
