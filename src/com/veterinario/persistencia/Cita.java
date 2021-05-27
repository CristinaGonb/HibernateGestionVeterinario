package com.veterinario.persistencia;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cita")
public class Cita implements Serializable{
	
	//Propiedades
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@Column(name="motivo")
	private String motivo;
	
	@Column(name="mascota")
	private Mascota mascota;
	
	@Column(name="veterinario")
	private Veterinario veterinario;

	public Cita(){}
	
	public Cita(int id, LocalDate fecha, String motivo, Mascota mascota, Veterinario veterinario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.mascota = mascota;
		this.veterinario = veterinario;
	}

	public Cita(LocalDate fecha, String motivo, Mascota mascota, Veterinario veterinario) {
		this.fecha = fecha;
		this.motivo = motivo;
		this.mascota = mascota;
		this.veterinario = veterinario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
	//Metodo para mostrar las citas de un veterinario
	public String mostrarCitasConVeterinario() {
		return "El veterinario "+veterinario.getNombre() +" con dni "+veterinario.getDni()+ " tiene la cita "+id+ " el dia "+fecha+ " con la mascota "+mascota.getNombre();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		result = prime * result + ((mascota == null) ? 0 : mascota.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result + ((veterinario == null) ? 0 : veterinario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id != other.id)
			return false;
		if (mascota == null) {
			if (other.mascota != null)
				return false;
		} else if (!mascota.equals(other.mascota))
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (veterinario == null) {
			if (other.veterinario != null)
				return false;
		} else if (!veterinario.equals(other.veterinario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", fecha=" + fecha + ", motivo=" + motivo + ", mascot=" + mascota + ", v=" + veterinario + "]";
	}
}
