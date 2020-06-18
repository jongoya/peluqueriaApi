package com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estiloPrivado")
public class EstiloPrivado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estiloId;
	private Long comercioId;
	private String primaryTextColor;
	private String secondaryTextColor;
	private String primaryColor;
	private String secondaryColor;
	private String backgroundColor;
	private String navigationColor;
	private String appSmallIcon;
	private String appName;
	
	public EstiloPrivado() {
		super();
	}
	
	

	public EstiloPrivado(Long comercioId) {
		super();
		this.comercioId = comercioId;
		this.primaryTextColor = "";
		this.secondaryTextColor = "";
		this.primaryColor = "";
		this.secondaryColor = "";
		this.backgroundColor = "";
		this.navigationColor = "";
		this.appSmallIcon = "";
		this.appName = "";
	}



	public Long getEstiloId() {
		return estiloId;
	}
	
	public void setEstiloId(Long estiloId) {
		this.estiloId = estiloId;
	}
	
	public Long getComercioId() {
		return comercioId;
	}
	
	public void setComercioId(Long comercioId) {
		this.comercioId = comercioId;
	}
	
	public String getPrimaryTextColor() {
		return primaryTextColor;
	}
	
	public void setPrimaryTextColor(String primaryTextColor) {
		this.primaryTextColor = primaryTextColor;
	}
	
	public String getSecondaryTextColor() {
		return secondaryTextColor;
	}
	
	public void setSecondaryTextColor(String secondaryTextColor) {
		this.secondaryTextColor = secondaryTextColor;
	}
	
	public String getPrimaryColor() {
		return primaryColor;
	}
	
	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}
	
	public String getSecondaryColor() {
		return secondaryColor;
	}
	
	public void setSecondaryColor(String secondaryColor) {
		this.secondaryColor = secondaryColor;
	}
	
	public String getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public String getNavigationColor() {
		return navigationColor;
	}
	
	public void setNavigationColor(String navigationColor) {
		this.navigationColor = navigationColor;
	}
	
	public String getAppSmallIcon() {
		return appSmallIcon;
	}
	
	public void setAppSmallIcon(String appSmallIcon) {
		this.appSmallIcon = appSmallIcon;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
}
