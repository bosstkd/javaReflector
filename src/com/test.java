package com;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.myLists.CgiList;
import com.myLists.user;

public class test {

	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		CgiList cl = new CgiList();
		List<user> lstUsr = new ArrayList<user>();
		
		lstUsr = cl.addElement(lstUsr, new user("Mahmoudi","Mohammed ElAmine"));
		lstUsr = cl.addElement(lstUsr, new user("Grandi","Zahia"));
		lstUsr = cl.addElement(lstUsr, new user("Mahmoudi","Lylia"));
		lstUsr = cl.addElement(lstUsr, new user("Boudjemaa","Amar"));
		
		System.out.println(cl.isElementExit(lstUsr, new user("Grandi","Zahia")));
		
		
		/*
		for(user u : lstUsr) {
			System.out.println("Class: "+((Object)u).toString()+" New instance: "+((Object)new user("Mahmoudi","Lylia")).toString());
		}
		*/
		
	}
	
	
}
