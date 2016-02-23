package com.patomalo.app;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.*;


/**
 * Unit test for simple App.
 */
public class AppTest extends App
{
    public String BODY;
    @BeforeMethod
    public void postLogin() throws InterruptedException {
        String user= getApi_key();
        System.out.println("Test: POST Login");
        System.out.println("Starting Test...");

        System.out.println("    Connect to the server");
        WebResource wb = connection("user/login");
        Thread.sleep(2000);

        System.out.println("    Get URL: ");
        System.out.println("        " + getURL("user/login/"));
        Thread.sleep(4000);

        System.out.println("    Post request");
        ClientResponse clientResponse = postRequestLogin(wb,user,user);

        Assert.assertTrue(clientResponse.getStatus() == 200,"Request Failed");
        System.out.println("    Status Result: " + clientResponse.getStatus());

        BODY = clientResponse.getEntity(String.class);
        System.out.println("    Body Response: "+BODY);
        bodyConvert(BODY);
    }
    @Test
    public void getThings() {
        System.out.println("Test: GET Things");
        int resposeCode = getRequest();
        Assert.assertTrue(resposeCode == 200, "There was an error answer");
    }

    /*
    @Test
    public void putThings() {
        System.out.println("Test: PUT Things");
        int responseCode = putRequest("TEST2");
        Assert.assertTrue(responseCode == 200, "There was an error answer");
    }
    */
    /*
    @Test
    public void deleteThing() {
        System.out.println("Test: DELETE Thing");
        int responseCode = deleteRequest(29);
        Assert.assertTrue(responseCode == 204,"There was an error");
    }
    */

}