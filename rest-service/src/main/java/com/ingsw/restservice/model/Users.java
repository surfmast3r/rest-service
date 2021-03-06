package com.ingsw.restservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String pwd;
	private String nickname;
	private Boolean showNickname;
	private String userRole;

	public Users(){

	}


	public String getUserRole() {
		return userRole;
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Boolean getShowNickname() {
		return showNickname;
	}
	
	public void setShowNickname(Boolean showNickname) {
		this.showNickname = showNickname;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String password) {
		this.pwd = password;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String username) {
		this.nome = username;
	}
}
