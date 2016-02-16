package com.patomalo.app;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        System.out.println("Respose Code: "+getRequest());
    }

    public static int getRequest() {
        int out=500;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/riot-core-services/api/thing/");
            ClientResponse response = webResource.accept("application/json").header("api_key","root").get(ClientResponse.class);
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
}
