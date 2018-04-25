package com.example.avneet.project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Avneet on 4/25/2018.
 */

public class MapSearch
{
    @Test
    public void mapcheck1()
    {
        String address;

        address="";

        if(address.equals("2 iondale place"))
        {
            System.out.println("ADDRESS IS VALLID - GOOD INPUT");
        }
        else if(address.equals(""))
        {
            assertEquals("ADDRESS IS NOT VALID", "VALID ADDRESS", address);
        }
    }

    @Test
    public void mapcheck2()
    {
        String address;

        address="2 iondale place";

        if(address.equals("2 iondale place"))
        {
            System.out.println("ADDRESS IS VALLID - GOOD INPUT");
        }
        else if(address.equals(""))
        {
            assertEquals("ADDRESS IS NOT VALID", "VALID ADDRESS", address);
        }
    }
}
