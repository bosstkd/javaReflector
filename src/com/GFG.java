package com;

//Java program to demonstrate the above method 

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.myLists.user;

public class GFG {

    public static void main(String... args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<user> c = user.class;
        Method[] s =c.getDeclaredMethods();

        
        for(Method mt : s) {
        	if(mt.toString().contains("TEST()")) {
        		System.out.println("MY FUNCTION : "+mt);
        	    mt.invoke(new user()); // pass args
        	}
        
        }
        
        
    }

    public String aMethod(String h) {
    	return h;
    }
    
    
}
