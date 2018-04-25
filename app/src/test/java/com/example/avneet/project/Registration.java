package com.example.avneet.project;

/**
 * Created by Avneet on 4/25/2018.
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class Registration
{
    @Test
    public void firstnamecheck()
    {
        String firstname,lastname,email,password;

        firstname="ABC";
        if(firstname.equals("ABC"))
        {
            System.out.println("Good input");
        }
        else if(firstname.equals(""))
        {
            assertEquals("CANNOT BE BLANK", "Admin", firstname);
        }
        else
        {
            assertEquals("NOT A ACCESSIBLE", "ABC", firstname);
        }
    }

    @Test
    public void lastnamecheck()
    {
        String firstname,lastname;

        lastname="";

        if(lastname.equals("XYZ"))
        {
            System.out.println("GOOD INPUT");
        }
        else if(lastname.equals(""))
        {
            assertEquals("CANNOT BE BLANK", "XYZ", lastname);
        }
    }

    @Test
    public void emailcheck()
    {
        String email;


        email="abc@abc.com";


        if(email.equals("abc@abc.com"))
        {
            System.out.println("GOOD INPUT");
        }
        else if(email.equals(""))
        {
            assertEquals("CANNOT BE BLANK", "abc@abc.com", email);
        }
    }

    @Test
    public void passwordcheck()
    {
        String password;

        password="";

        if(password.equals("abc@abc.com"))
        {
            System.out.println("GOOD INPUT");
        }
        else if(password.equals(""))
        {
            assertEquals("CANNOT BE BLANK", "SOMETHING >6 letters", password);
        }
    }
}
