package treemodel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;

/**
 * Using Tree Model - Tree Model prepares a in-memory tree
 * representation of the JSON document. ObjectMapper build
 * tree of JsonNode nodes. It is most flexible approach. It is
 * analogus to DOM parser for XML.
 */
public class TreeModelExample {

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
        -----------------------------------------------------------------

        Object Mapper -> creates a JsonNode (as a root node of the tree)
        -> the node traverse the tree though [path]

        -----------------------------------------------------------------
         */
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";

        try {

            // create root node
            JsonNode rootNode = mapper.readTree(jsonString);

            // traverse the tree using path
            JsonNode nameNode = rootNode.path("name");
            System.out.println("Name: "+ nameNode.textValue() + "\n");



            JsonNode marksNode = rootNode.path("marks");
            // if the node is an array or iterable
            Iterator<JsonNode> iterator = marksNode.elements();
            System.out.println("Marks:[");
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            System.out.println("];");

            System.out.println();

            JsonNode ageNode = rootNode.path("age");
            System.out.println("Age: " + ageNode.intValue());

            JsonNode verifiedNode = rootNode.path("verified");
            System.out.println(verifiedNode.booleanValue());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
