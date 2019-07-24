package DataBinding;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import student.Student;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Using Data Binding
 */
public class DataBindingExample {


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
        ---------------------------------

        Simple Data Binding

        ---------------------------------


        Simple data binding refers to mapping of JSON to JAVA Core data types.

        JSON Type       Java Type
        ----------------------------------------------
        object              HashMap<String, Object>
        array               ArrayList<Object>
        string              String
        complete number     Integer, Long or BigInteger
        factional number    Double/ BigInteger
        true|false          Boolean
        null                null
        ----------------------------------
         */

        // mapper
        ObjectMapper mapper = new ObjectMapper();

        /*
        ---------------------------------

        Using map - JSON treated as a map

        ---------------------------------
         */
        Map<String, Object> jsonDataMap = new HashMap<String, Object>();

        Student tom = new Student();
        tom.setName("Tom");
        tom.setAge(12);
        tom.setMajor("Physics");

        int[] marks = new int[]{80,79,82};

        /*
         essentially, you can put any java type objects in the map, which
         will be converted to the json type objects accordingly.
         */
        // Java Object
        jsonDataMap.put("student", tom);

        // Java String type
        jsonDataMap.put("university", "University of Sheffield");

        // Java Boolean type
        jsonDataMap.put("registered", Boolean.TRUE);

        // Java Array
        jsonDataMap.put("marks", marks);


        /*
        ---------------------------------

        convert this map to a json file / object

        ---------------------------------
         */
        try {
            mapper.writeValue(new File("data.json"), jsonDataMap);

            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            System.out.println(writer.writeValueAsString(jsonDataMap));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        /*
        ---------------------------------

        convert json file/ object to map

        ---------------------------------
         */
        try{
            Map<String, Object> readJsonMap = new HashMap<>();

            readJsonMap = mapper.readValue(new File("data.json"), Map.class);

            System.out.println(readJsonMap.get("student"));
            System.out.println(readJsonMap.get("university"));
            System.out.println(readJsonMap.get("registered"));
            System.out.println(readJsonMap.get("marks"));

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
