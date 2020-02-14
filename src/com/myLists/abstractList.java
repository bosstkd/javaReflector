package com.myLists;

import java.lang.reflect.InvocationTargetException;


public abstract class abstractList<T>{

	private Class<T> entityClass;
	
	public abstractList(Class<T> entityClass) {
        this.entityClass = entityClass;
        
      /* 
        java.lang.reflect.Field[] fields = entityClass.getDeclaredFields();
        System.out.printf("%d fields:%n", fields.length);
       
        for (java.lang.reflect.Field fld : fields) {
            System.out.println(
                java.lang.reflect.Modifier.toString(fld.getModifiers())+" "+
                		fld.getType().getSimpleName()+" "+
                		fld.getName()
            );
        }
        */
        
    }

// Retourne une liste qui a la valeur rechercher de l'attribut entré en parametre si le parametre contains est a true la valeur rechercher est mise entre % si upper est true la fonction n'est plus sensible a la casse.	
   public java.util.List<T> getListWithSearchingAttribute(java.util.List<T> lstBase, java.lang.String attribute, java.lang.String searchingValue, boolean contains, boolean upper) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	   java.lang.String getterName = "get"+attribute.substring(0,1).toUpperCase()+attribute.substring(1, attribute.length())+"()";
	   java.lang.String MethodegetterName = "get"+attribute.substring(0,1).toUpperCase()+attribute.substring(1, attribute.length());
		
	   if(!isMethodeExist(lstBase.get(0), attribute)) throw new IllegalArgumentException("La methode "+getterName+" de l'attribut "+attribute+" n'existe pas !!");
	   java.util.List<T> newLst = new java.util.ArrayList<T>();
	   
	   java.lang.reflect.Method s =entityClass.getDeclaredMethod(MethodegetterName);
		
	   java.lang.String str;
	   searchingValue = searchingValue.toUpperCase();
		for(int i = 0; i<lstBase.size(); i++){
			if(s.invoke(lstBase.get(i)) == null){
												str = "null";
					}else{
												str = s.invoke(lstBase.get(i)).toString();				
						 }
			  if(upper){
				  str = str.toUpperCase();
			  }
			  if(contains){
				  if(str.contains(searchingValue))
					  newLst.add(lstBase.get(i));
			  }else{
				  if(str.equals(searchingValue))
					  newLst.add(lstBase.get(i));
			  }
			 
		}
	   
	   return newLst;
   }
	
// Retourne la liste sans les attributs a supprimer si le parametre contains est a true la valeur rechercher est mise entre % si upper est true la fonction n'est plus sensible a la casse.  
   public java.util.List<T> getWithoutEltUsingAttribute(java.util.List<T> lstBase, java.lang.String attribute, java.lang.String deletingValue, boolean contains, boolean upper){
	   
	   java.lang.String getterName = "get"+attribute.substring(0,1).toUpperCase()+attribute.substring(1, attribute.length())+"()";
	   if(!isMethodeExist(lstBase.get(0), attribute)) throw new IllegalArgumentException("La methode "+getterName+" de l'attribut "+attribute+" n'existe pas !!");
	   java.util.List<T> newLst = lstBase;
	   
	   try {
		   java.util.List<T> lstToDelete =  getListWithSearchingAttribute(lstBase, attribute, deletingValue, contains, upper);
		   
		   for(T e:lstToDelete){
			   newLst = getWithoutElt(newLst, e);
		   }
	
	   } catch (NoSuchMethodException | SecurityException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   return newLst;
   }
   
// Retourne la liste sans l'element précisé en parametre.	
   public java.util.List<T> getWithoutElt(java.util.List<T> lstBase, T elt){
	   if(lstBase == null || elt == null) throw new NullPointerException("Les parametre de la methode getListWithoutElt ne peuvent pas etre null !!");
	   java.util.List<T> newLst = new java.util.ArrayList<T>();
	   for(T e:lstBase)
		   if(!isEqual(e, elt))
			   newLst.add(e);

	   return newLst;
   }
	
// Retourne la liste des pojos ordonné suivant l'attribut demandé en ordre descendant si desc est a true et ascendant si desc est a false  	
   public java.util.List<T> listOrder(java.util.List<T> lstBase, java.lang.String attribut, boolean desc) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if(lstBase == null || attribut == null) throw new NullPointerException("Les parametre de la methode getListWithoutElt ne peuvent pas etre null !!");

		java.util.List<T> newLst = new java.util.ArrayList<T>();
		java.util.List<T> newLstInv = new java.util.ArrayList<T>();
		java.util.List<T> lstGet = lstBase;
		int tailleList = lstGet.size();
		
		while(lstGet.size()>0){
			int pts = inferieur(lstGet, attribut);
			newLst.add(lstGet.get(pts));
			lstGet.remove(pts);
			if(lstGet.size()==tailleList) throw new ArithmeticException("La taille de la liste ne se reduit pas => entrer en boucle infinie !!");
		}
		
		if(lstGet.size() > 0) newLst.add(lstGet.get(0));
		
		if(desc){
			java.util.ListIterator<T> lstItr = newLst.listIterator(newLst.size());
			while(lstItr.hasPrevious()){
				newLstInv.add(lstItr.previous());
			}
			return newLstInv;
		}
		
		return newLst;
	}

//l'element inférieur en ordre alphabétique utilisé par la methode de trie
	private int inferieur(java.util.List<T> lst, java.lang.String attribut) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		java.lang.String getterName = "get"+attribut.substring(0,1).toUpperCase()+attribut.substring(1, attribut.length())+"()";
		java.lang.String MethodegetterName = "get"+attribut.substring(0,1).toUpperCase()+attribut.substring(1, attribut.length());
		if(!isMethodeExist(lst.get(0), attribut)) throw new IllegalArgumentException("La methode "+getterName+" de l'attribut "+attribut+" n'existe pas !!");
			
		java.lang.reflect.Method s =entityClass.getDeclaredMethod(MethodegetterName);
		String type = s.getGenericReturnType().toString();
		type = type.substring(type.lastIndexOf(".")+1, type.length());
		System.out.println("GENERIC RETURN : "+type);
		
		
		int pts = 0 ;
	 if(type.equals("Date")){
			java.util.Date str;
			java.util.Date x;
				if(s.invoke(lst.get(0))==null){
					x = null;
				}else{
					x = (java.util.Date) s.invoke(lst.get(0));
				}
				
				for(int i = 0; i<lst.size(); i++){
					if(s.invoke(lst.get(i)) == null)
						str =  null;
						 else
						str = (java.util.Date) s.invoke(lst.get(i));				
						if(superieur(str,x)){
							x = str;
							pts = i;
						}
				}			
		}else if(type.equals("double")||type.equals("Double")){
			double str;
			double x;
			
			if(s.invoke(lst.get(0))==null){
				x = 0;
			}else{
				x = (double) s.invoke(lst.get(0));
			}
			
			for(int i = 0; i<lst.size(); i++){
				if(s.invoke(lst.get(i)) == null)
					str = 0;
					 else
					str = (double) s.invoke(lst.get(i));				
					if(str<=x){
						x = str;
						pts = i;
					}
			}
			
		}else if(type.equals("float")||type.equals("Float")){
			float str;
			float x;
			
			if(s.invoke(lst.get(0))==null){
				x = 0;
			}else{
				x = (float) s.invoke(lst.get(0));
			}
			
			for(int i = 0; i<lst.size(); i++){
				if(s.invoke(lst.get(i)) == null)
					str = 0;
					 else
					str = (float) s.invoke(lst.get(i));				
					if(str<=x){
						x = str;
						pts = i;
					}
			}
			
		}else{
			java.lang.String str;
			java.lang.String x;
				if(s.invoke(lst.get(0))==null){
					x = "null";
				}else{
					x = s.invoke(lst.get(0)).toString();
				}
				 
				for(int i = 0; i<lst.size(); i++){
					if(s.invoke(lst.get(i)) == null)
						str = "null";
						 else
						str = s.invoke(lst.get(i)).toString();				
						if((str).compareTo( x)<=0){
							x = str;
							pts = i;
						}
				}
		}
		return pts;
	}

	private  boolean superieur(java.util.Date dt_1, java.util.Date dt_2){
		long dif = (long) dt_2.getTime() - (long) dt_1.getTime();
	            return dif >= 0;
	}
	
//Verfie si la méthode get de l'attribut existe.	
	private boolean isMethodeExist(T elt, java.lang.String attribut){
		java.lang.reflect.Method[] s =entityClass.getDeclaredMethods();
		java.lang.String getterName = "get"+attribut.substring(0,1).toUpperCase()+attribut.substring(1, attribut.length())+"()";
		  
		  for(java.lang.reflect.Method mt : s) 
	      	if(mt.toString().contains("get")) {
	      		 if(mt.toString().contains(getterName))return true;
	      	}
		return false;
	}
	
// Verifie si le pojo "elt" existe dans la liste des pojos lst	
	public boolean isElementExit(java.util.List<T> lst, T elt) throws IllegalAccessException, IllegalArgumentException, java.lang.reflect.InvocationTargetException {
			for(T e:lst) 
				if(isEqual(e, elt)) 
					        return true;
			return false;
		}
	
// Verifie si 2 element sont egaux	
	private boolean isEqual(T elt1, T elt2) {
			 java.lang.reflect.Method[] s =entityClass.getDeclaredMethods();
		        for(java.lang.reflect.Method mt : s) 
		        	if(mt.toString().contains("get")) {
		        	 	// System.out.println("MY FUNCTION : "+mt);
		        	    String str1;
		        	    String str2;
						try {
							if((mt.invoke(elt1) == null && mt.invoke(elt2)!=null)||(mt.invoke(elt1) != null && mt.invoke(elt2)==null)){
								return false;
							}else if(mt.invoke(elt1) != null && mt.invoke(elt2)!=null){
								str1 = mt.invoke(elt1).toString();
								str2 = mt.invoke(elt2).toString();
								if(! str1.equals(str2)) return false;	
							}
						} catch (NullPointerException | IllegalAccessException | IllegalArgumentException | java.lang.reflect.InvocationTargetException e) {
							 e.printStackTrace();
						} 
		        	}
			return true;
		}

// Retourne la liste avec l'element ajouter sur l'emplacement index	
	public java.util.List<T> addToList(java.util.List<T> lstBase, T element, int index){
		
		if(index<0) throw new ArithmeticException("index inférieur a 0 !!!");
		if(index>lstBase.size()-1) throw new IllegalArgumentException("Index superieur à la taille de liste !!");
		if(element==null) throw new NullPointerException("L'element a ajouter ne peut pas etre NULL !!");
		
		java.util.List<T> newLst = new java.util.ArrayList<T>();
		
		for(T e:lstBase){
			newLst.add(e);	
		}
			newLst.add(index, element);
		
		return newLst;
	}
	
// Retourne l'index de l'element rechercher.	
	public int getElementIndex(java.util.List<T> lstBase, T element){
		if(element==null) throw new NullPointerException("L'element a ajouter ne peut pas etre NULL !!");
		if(lstBase==null) throw new NullPointerException("La liste de base ne peut pas etre NULL !!");
		
		for(int i=0; i<lstBase.size(); i++)
			if(isEqual(lstBase.get(i), element)) return i;
		
		return -1;
	}
	
// Retourne la liste avec l'element ajouter sur l'emplacement de l'element demander en référence
	public java.util.List<T> addToList(java.util.List<T> lstBase, T elementToAdd, T referElement){

		if(elementToAdd==null || referElement == null) throw new NullPointerException("L'element a ajouter et l'element en référence ne peuvent pas etre NULL !!");
		java.util.List<T> newLst = new java.util.ArrayList<T>();
		
		if(getElementIndex(lstBase, referElement)<0){
			throw new IllegalArgumentException("l'argument de l'élement en référence n'existe pas !!");
		}else{
			newLst = addToList(lstBase, elementToAdd, getElementIndex(lstBase, referElement));
		}
		
		
		return newLst;
	}
	
	
	
	
}
