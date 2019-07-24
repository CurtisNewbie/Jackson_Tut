package objectMapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public class ObjectMapperExample {

    public static void main(String[] args) {

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

        // Mimic a Json object
        String aJsonObject = "{ \"name\":\"Curtis\" , \"major\":\"Information Systems\" , \"age\":\"24\"}";

        /*
        ---------------------------------------

         deserialize this Json object

         --------------------------------------
         */

        // create a object mapper (method 2 above)
        ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            // map json object to objectMapper.Student class
            Student student = mapper.readValue(aJsonObject, Student.class);

            System.out.println("Json deserialised: " + student.toString());

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        ---------------------------------------

         serialize this objectMapper.Student object to Json

         --------------------------------------
         */
        try {
            Student newStudent = new Student();
            newStudent.setAge(24);
            newStudent.setName("Sharon");
            newStudent.setMajor("Animation");

            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            String jsonStr = writer.writeValueAsString(newStudent);
            System.out.println("Serialised json: " + jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        /*
        ---------------------------------------

        serialize a objectMapper.Student object to Json to a local file.

        ---------------------------------------
         */
        try {
            Student tom = new Student();
            tom.setName("Tom");
            tom.setAge(100);
            tom.setMajor("Law");

            mapper.writeValue(new File("tom.json"), tom);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        /*
        -----------------------------------------

        deserialize a json object from local file

        -----------------------------------------
         */
        try {
            Student stuTom;
            stuTom = mapper.readValue(new File("tom.json"), Student.class);
            System.out.println("tom.json: " + stuTom.toString());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
