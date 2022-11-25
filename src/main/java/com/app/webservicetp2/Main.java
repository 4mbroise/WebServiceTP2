package com.app.webservicetp2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) {

        // EXERCICE 1
        callWebService callWebService = new callWebService();

        callWebService.initializeService("http://jsonplaceholder.typicode.com/");
        String response = callWebService.callJSONPlaceholderService("posts",1);

        System.out.println("EXERCICE 1:");
        System.out.println("Raw response");
        System.out.println(response);

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);

            System.out.println("");
            System.out.println("Parsing JSON:");
            System.out.println("ID user:\t\t"+jsonObject.get("userId"));
            System.out.println("ID:\t\t"+jsonObject.get("id"));
            System.out.println("Title:\t\t"+jsonObject.get("title"));
            System.out.println("Body:\t\t"+jsonObject.get("body"));


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("");
        System.out.println("");

        // EXERCICE 2



        System.out.println("Exercice 2 :");
        callWebService.initializeService("http://api.geonames.org/");
        response = callWebService.callCountryInfoService("countryInfo","UK","me");

        System.out.println(response);

        Document document = convertXMLStringToDocument(response);

        System.out.println("Nom du Pays: "+document.getElementsByTagName("countryName").item(0).getTextContent());
        System.out.println("Continent: "+document.getElementsByTagName("continentName").item(0).getTextContent());
        System.out.println("Capital: "+document.getElementsByTagName("capital").item(0).getTextContent());
        System.out.println("Monnaie: "+document.getElementsByTagName("currencyCode").item(0).getTextContent());



        // EXERCICE 3


        System.out.println("");
        System.out.println("");


        System.out.println("Exercice 3 :");
        callWebService.initializeService("https://labs.bible.org");
        response = callWebService.callBibleTagService("api","John",3, 16, "json");

        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(response);

            for(Object o:jsonArray){
                JSONObject jsonObject = (JSONObject) o;

                System.out.println("Response of callBibleTagService parsed (JSON)");
                System.out.println("Bookname: "+jsonObject.get("bookname"));
                System.out.println("Chapter: "+jsonObject.get("chapter"));
                System.out.println("Verse: "+jsonObject.get("verse"));
                System.out.println("Text: "+jsonObject.get("text"));
            }


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        callWebService.initializeService("https://labs.bible.org");
        response = callWebService.callBibleMutiTagService("api", new String[]{"John", "Acts"}, new int[]{3, 1}, new int[]{16, 12}, "xml");


        document = convertXMLStringToDocument(response);

        System.out.println("Response of callBibleMultiTagService parsed (XML)");
        for(int i = 0;i<2;i++){
            System.out.println("Bookname: "+document.getElementsByTagName("bookname").item(i).getTextContent());
            System.out.println("Chapter: "+document.getElementsByTagName("chapter").item(i).getTextContent());
            System.out.println("Verse: "+document.getElementsByTagName("verse").item(i).getTextContent());
            System.out.println("Text: "+document.getElementsByTagName("text").item(i).getTextContent());
            System.out.println("");

        }



    }


    private static Document convertXMLStringToDocument(String xmlString){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;



        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

}