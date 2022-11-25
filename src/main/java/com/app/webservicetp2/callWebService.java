package com.app.webservicetp2;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author NGO_Phuc
 */
public class callWebService {
    private WebResource service;
    
    /**
     * @param url the command line arguments
     */
    public void initializeService(String url) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(
        UriBuilder.fromUri(url).build());
    }
    
    /**
     * @param serviceName : web service address 
     * @return 
     */
    public String callGreetingService(String serviceName) {
        String response = service
                        .path(serviceName)
                        .get(String.class);
        return response;
	
    }
    
    /**
     * @param serviceName : name of services
     * @param code : code of post
     * @return 
     */
    public String callJSONPlaceholderService(String serviceName, int code) {
        String ser=serviceName+"/"+Integer.toString(code);
        String response = service
                        .path(ser)
                        .get(String.class);
        return response;
    }
    
    /**
     * @param serviceName : name of services
     * @param isoCode : iso code
     *@param login : login 
     * @return 
     */
    public String callCountryInfoService(String serviceName, String isoCode, String login) {
        String ser=serviceName;
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("country",isoCode);
        params.add("username",login);
        String response = service
                        .path(ser)
                        .queryParams(params)
                        .get(String.class);
        return response;
    }
    
    public String callBibleTagService(String serviceName, String titre, int chapter, int verse, String type) {
        String para=titre+"+"+Integer.toString(chapter)+":"+Integer.toString(verse);
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("passage",para);
        params.add("type",type);
        String response = service
                        .path(serviceName)
                        .queryParams(params)
                        .get(String.class);
        return response;
    }
    
    public String callBibleMutiTagService(String serviceName, String[] titre, int[] chapter, int[] verse, String type) {
        int size = titre.length;
        String para = "";
        for (int i=0; i<size-1; i++)
          para+=titre[i]+"+"+Integer.toString(chapter[i])+":"+Integer.toString(verse[i])+";";
        para+=titre[size-1]+"+"+chapter[size-1]+":"+verse[size-1];
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("passage",para);
        params.add("type",type);
        String response = service
                        .path(serviceName)
                        .queryParams(params)
                        .get(String.class);
        return response;
    }

    public String callGeoNamesService(String serviceName, double north, double south, double east, double west) {
        String ser=serviceName;
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("north", Double.toString(north));
        params.add("south", Double.toString(south));
        params.add("east", Double.toString(east));
        params.add("west", Double.toString(west));

        String response = service
                .path(ser)
                .queryParams(params)
                .get(String.class);

        return response;
    }

    
    /**
     * @param serviceName the command line arguments
     * @param IsoCode
     * @return 
    
    public String callIso2CodeService(String serviceName, String IsoCode) {
        String ser=serviceName+"/"+IsoCode;
        String response = service
                        .path(ser)
                        .get(String.class);
        return response;
    }
     */
    
    /**
     * @param serviceName the command line arguments
     * @param symbol the command line arguments
     * @return 
    
    public String callGetQuoteService(String serviceName, String symbol) {
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("symbol",symbol);
        String responseRaw = service
                        .path(serviceName)
                        .queryParams(params)
                        .get(String.class);
        String reponse = responseRaw.replaceAll("&lt;", "<");
        reponse = reponse.replaceAll("&gt;", ">");
        return reponse;
    }
     */
    
    /**
     * @param serviceName the command line arguments
     * @param LengthValue the command line arguments
     * @param fromLengthUnit the command line arguments
     * @param toLengthUnit the command line arguments
     * @return 
    
    public String callChangeLengthUnit(String serviceName, String LengthValue, String fromLengthUnit, String toLengthUnit) {
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("LengthValue",LengthValue);
        params.add("fromLengthUnit",fromLengthUnit);
        params.add("toLengthUnit",toLengthUnit);
        String responseRaw = service
                        .path(serviceName)
                        .queryParams(params)
                        .get(String.class);
        String reponse = responseRaw.replaceAll("&lt;", "<");
        reponse = reponse.replaceAll("&gt;", ">");
        return reponse;
    }
     */
    
    /**
     * @param serviceName the command line arguments
     * @return 
        
    public String callGetBookTitles(String serviceName) {
        String responseRaw = service
                        .path(serviceName)
                        .get(String.class);
        String reponse = responseRaw.replaceAll("&lt;", "<");
        reponse = reponse.replaceAll("&gt;", ">");
        return reponse;
    }
     */
}
