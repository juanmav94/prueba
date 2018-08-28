package com.puerta18.model;

import java.util.Date;

public class check {
	
	int id;
	int id_usuario;
	Date momento;
	String tipo;
	
	public check(int id, int id_usuario, Date momento, String tipo) {
		super();
		this.id = id;
		this.id_usuario = id_usuario;
		this.momento = momento;
		this.tipo = tipo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Date getMomento() {
		return momento;
	}
	public void setMomento(Date momento) {
		this.momento = momento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
