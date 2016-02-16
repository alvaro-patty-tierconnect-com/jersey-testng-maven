package com.patomalo.app;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Unit test for simple App.
 */
public class AppTest extends App
{

    @Test
    public void testCaseRegister(){
        System.out.println("Register User");
    }

    @Test
    public void testCaseLoginTest() {
        System.out.println("Loggin into the app");
    }

    @Test
    public void testCasePasswordChange(){
        System.out.println("Changing password");
    }

    @Test
    public void getThings() {
        System.out.println("Getting all the Things test");
        int resposeCode = getRequest();
        Assert.assertTrue(resposeCode == 200, "There was an error anwser");
    }

    @Test
    public void upoladPic(){
        String expected="A";
        String actual="A";
        //Assertion in testng

        Assert.assertEquals(actual, expected);

        Assert.assertTrue(3 > 2, "Some erro msg");

    }
}