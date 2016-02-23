package com.patomalo.app;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

import java.io.StringWriter;
import java.util.Objects;

/**
 * Hello world!l
 *
 */
public class App 
{
    public static String HOST;
    public static String API_KEY;
    private static String http = "http://";
    private static String service = ":8080/riot-core-services/api/";

    private static  JSONObject LOGIN_BODY;

    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        System.out.println("Respose Code: "+getRequest());
        System.out.println("host: " + System.getProperty("serverHost"));
        System.out.println("user: "+ System.getProperty("userKey"));
        JSONObject jsonObject = new JSONObject();
    }

    public static String getHost(){
        String host = System.getProperty("serverHost");
        return host;
    }
    public static String getApi_key(){
        String api_key = System.getProperty("userKey");
        return api_key;
    }
    public static WebResource connection(String api){
        WebResource webResource = null;
        HOST=getHost();
        API_KEY=getApi_key();
        try {
            Client client = Client.create();
            webResource = client.resource(http+HOST+service+api);
        }
        catch(Exception e){
            System.out.println("ERROR Connection()!!: "+e.getMessage());
            e.printStackTrace();
        }
        return webResource;
    }

    public  static ClientResponse postRequestLogin(WebResource webResource, String username, String password) {

        JSONObject obj = new JSONObject();

        ClientResponse cl = null;
        try{
            obj.put("username",username);
            obj.put("password", password);
            StringWriter out = new StringWriter();
            obj.write(out);
            String json = out.toString();
            //String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
            //System.out.println("JSON converted: "+json);
            cl = webResource.accept("application/json").header("Content-Type", "application/json").post(ClientResponse.class, json);
        }
        catch (Exception e){
            System.out.println("ERROR postRequestLogin()!!: "+e.getMessage());
            e.printStackTrace();
        }
        //bodyConvert(cl.getEntity(String.class));
        return cl;
    }

    public static void bodyConvert(String BODY) {
        try {
            LOGIN_BODY = new JSONObject(BODY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLoginBody(String var) {
        String out = null;
        try{
            out = LOGIN_BODY.get(var).toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return out;
    }
    public static int getRequest() {
        int out=500;
        HOST=getHost();
        API_KEY=getApi_key();
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(http+HOST+service+"thing/");
            ClientResponse response = webResource.accept("application/json").header("api_key",API_KEY).get(ClientResponse.class);
            //ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

            if(response.getStatus() != 200){
                System.out.println("wrong");
            }
            String output = response.getEntity(String.class);
            System.out.println("Out from Server....");
            System.out.println(output);
            out = response.getStatus();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return out;
    }
    public static String getURL(String api){
        return http+HOST+service+api;
    }

    public static int putRequest(String serial){
        //{"group":">mojix>SM","name":"pato","serialNumber":"pato","thingTypeCode":"default_rfid_thingtype"}
        String s = "{\"group\":\">mojix>SM\",\"name\":\"TT2\",\"serialNumber\":\"TT2\",\"thingTypeCode\":\"default_rfid_thingtype\"}";
        HOST = getHost();
        API_KEY=getApi_key();
        try{
            Client client = Client.create();
            WebResource webResource = client.resource("http://" + HOST + ":8080/riot-core-services/api/thing/");
            String bodyInput = "{\"group\":\">mojix>SM\",\"name\":\""+serial+"\",\"serialNumber\":\""+serial+"\",\"thingTypeCode\":\"default_rfid_thingtype\"}";
            ClientResponse response = webResource.type("application/json").header("api_key",API_KEY).put(ClientResponse.class, s);
            System.out.println("PUT status:"+response.getStatus());
            String out = response.getEntity(String.class);
            JSONObject jsonObject = new JSONObject(out);
            System.out.println("salida: "+jsonObject);
            return response.getStatus();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteRequest(int id){
        HOST = getHost();
        API_KEY=getApi_key();
        try{
            Client client = Client.create();
            WebResource webResource = client.resource("http://" + HOST + ":8080/riot-core-services/api/thing/"+id);
            ClientResponse response = webResource.accept("application/json").header("api_key",API_KEY).delete(ClientResponse.class);
            System.out.println("PUT status:"+response.getStatus());
            //System.out.println("SOMETHING: "+response.getEntity(String.class));
            return response.getStatus();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
