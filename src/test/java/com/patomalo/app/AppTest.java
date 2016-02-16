package com.patomalo.app;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Unit test for simple App.
 */
public class AppTest extends App
{

    @Test
    public void getThings() {
        System.out.println("Test: GET Things");
        int resposeCode = getRequest();
        Assert.assertTrue(resposeCode == 200, "There was an error answer");
    }

    @Test
    public void putThings() {
        System.out.println("Test: PUT Things");
        int responseCode = putRequest("TEST2");
        Assert.assertTrue(responseCode == 200, "There was an error answer");
    }


    @Test
    public void deleteThing() {
        System.out.println("Test: DELETE Thing");
        int responseCode = deleteRequest(29);
        Assert.assertTrue(responseCode == 204,"There was an error");
    }

}