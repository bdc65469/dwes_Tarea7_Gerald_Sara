package com.geraldSara.tarea7dwesGeraldSara.modelo;

public class PlantaDTO {
	private Long id;
	private int cantidad;
	
	 public PlantaDTO() {
	    }
	 
	

	public PlantaDTO(Long id, int cantidad) {
		this.id = id;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
        this.id = id;
    }

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
