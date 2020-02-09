package com.myLists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class abstractList<T> {
	
	Class<T> entityClass;
	
	public abstractList(Class<T> entityClass) {
		this.entityClass = entityClass;
		
		
	}
	
	public boolean isElementExit(List<T> lst, T elt) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		for(T e:lst) {
			if(isEqual(e, elt)) {
				return true;
			}
		}
						
		return false;
	}
	
	
	private boolean isEqual(T elt1, T elt2) {
		 Method[] s =entityClass.getDeclaredMethods();

	        for(Method mt : s) 
	        	if(mt.toString().contains("get")) {
	        	 	// System.out.println("MY FUNCTION : "+mt);
	        	    String str1;
	        	    String str2;
					try {
						str1 = mt.invoke(elt1).toString();
						str2 = mt.invoke(elt2).toString();
						
						System.out.println(str1+"  "+str2);
						if(! str1.equals(str2)) return false;						
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	        	}
	        
		return true;
	}
	
	public List<T> addElement(List<T> lst, T elt){
		List<T> l = lst;
		l.add(elt);
		return l;
	}

}
