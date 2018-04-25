package com.example.avneet.project;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Avneet on 4/25/2018.
 */

public class Users
{
    @Test
    public void user1()
    {
        Scanner sc=new Scanner(System.in);
        String role;

        role="Admin";

        if(role.equals("Admin"))
        {
            System.out.print("ADMIN RIGHTS");
        }
        else if(role.equals("Users"))
        {
            System.out.print("LIMITED ACCESS");
        }
        else
        {
            assertEquals("NOT A ACCESSIBLE", "Admin", role);
        }
    }

    @Test
    public void user2()
    {
        Scanner sc=new Scanner(System.in);
        String role;

        role="Users";

        if(role.equals("Admin"))
        {
            System.out.print("ADMIN RIGHTS");
        }
        else if(role.equals("Users"))
        {
            System.out.print("LIMITED ACCESS");
        }
        else
        {
            assertEquals("NOT A ACCESSIBLE", "Admin", role);
        }
    }

    @Test
    public void user3()
    {
        Scanner sc=new Scanner(System.in);
        String role;

        role="";

        if(role.equals("Admin"))
        {
            System.out.print("ADMIN RIGHTS");
        }
        else if(role.equals("Users"))
        {
            System.out.print("LIMITED ACCESS");
        }
        else
        {
            assertEquals("NOT A ACCESSIBLE", "Admin", role);
        }
    }
}
