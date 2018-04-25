package com.example.avneet.project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Avneet on 4/25/2018.
 */

public class Comments
{
    @Test
    public void comm()
    {
        String email;

        email="abc@abc.com";

        if(email.equals("abc@abc.com"))
        {
                System.out.println("ALLOWED TO COMMENT");
        }
        else
        {
            assertEquals("NEED EMAIL TO UPDATE COMMENT", "abc@abc.com", email);
        }
    }

    @Test
    public void comm2()
    {
        String email;

        email="";

        if(email.equals("abc@abc.com"))
        {
            System.out.println("ALLOWED TO COMMENT");
        }
        else
        {
            assertEquals("NEED EMAIL TO UPDATE COMMENT", "abc@abc.com", email);
        }
    }
}
