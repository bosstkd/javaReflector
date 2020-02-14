package com.myLists;

import java.util.Date;

public class user {
    private int id;
	private String nom;
	private String prenom;
	private Date dtNaissance;
	private String adresse;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	public Date getDtNaissance() {
		return dtNaissance;
	}
	public void setDtNaissance(Date dtNaissance) {
		this.dtNaissance = dtNaissance;
	}
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public user(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public user(int id, String nom, String prenom, Date dtNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dtNaissance = dtNaissance;
	}
	
	public user() {}
	

	
}
