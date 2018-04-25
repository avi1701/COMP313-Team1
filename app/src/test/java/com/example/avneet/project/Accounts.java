package com.example.avneet.project;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Avneet on 4/25/2018.
 */

public class Accounts
{
    @Test
    public void acc1()
    {
        String email;
        int life_of_account;

        life_of_account=30;
        email="abc@abc.com";

        if(email.equals("abc@abc.com"))
        {
            if(life_of_account<35)
            {
                System.out.println("ACCOUNT ACTIVATED at age "+life_of_account);
            }
            else
            {
                assertEquals("ACCOUNT DEACTIVATED at age "+life_of_account, "abc@abc.com", email);
            }
        }
        else
        {
            assertEquals("NOT A VALID ACCOUNT", "abc@abc.com", email);
        }
    }
    @Test
    public void acc2()
    {
        String email;
        int life_of_account;

        life_of_account=39;
        email="abc@abc.com";

        if(email.equals("abc@abc.com"))
        {
            if(life_of_account<35)
            {
                System.out.println("ACCOUNT ACTIVATED at age "+life_of_account);
            }
            else
            {
                assertEquals("ACCOUNT DEACTIVATED at age "+life_of_account, "abc@abc.com", email);
            }
        }
        else
        {
            assertEquals("NOT A VALID ACCOUNT", "abc@abc.com", email);
        }
    }
    @Test
    public void acc3()
    {
        String email;
        int life_of_account;

        life_of_account=30;
        email="abc@adb.com";

        if(email.equals("abc@abc.com"))
        {
            if(life_of_account<35)
            {
                System.out.println("ACCOUNT ACTIVATED at age "+life_of_account);
            }
            else
            {
                assertEquals("ACCOUNT DEACTIVATED", "abc@abc.com", email);
            }
        }
        else
        {
            assertEquals("NOT A VALID ACCOUNT", "abc@abc.com", email);
        }
    }
}
