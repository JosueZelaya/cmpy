/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.utils;

import java.util.Random;

/**
 *
 * @author arch
 */
public class Utilidades {
    
    public static int calculateTotalPages(int totalPublicaciones,int pageZise)
    {
        int sum = (totalPublicaciones % pageZise == 0) ? 0 : 1;
        int totalPages = totalPublicaciones / pageZise + sum;
        return totalPages;
    }
    
    public static int randomInt(int min,int max)
    {
        if(max<=0)
        {
            return 0;
        }
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
}
