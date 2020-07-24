package com.example.demo.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class CestaMasVentas implements Serializable {
	private Cesta cesta;
	private ArrayList<Venta> ventas;
	
	public CestaMasVentas(Cesta cesta, ArrayList<Venta> ventas) {
		super();
		this.cesta = cesta;
		this.ventas = ventas;
	}

	public Cesta getCesta() {
		return cesta;
	}

	public void setCesta(Cesta cesta) {
		this.cesta = cesta;
	}

	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}
}
