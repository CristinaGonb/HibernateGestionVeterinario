package com.veterinario.persistencia;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hib_cita")
public class Cita implements Serializable {

	// Propiedades
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "nombreCita")
	private String nombreCita;
	
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private LocalDate fecha;

	@Column(name = "motivo")
	private String motivo;

	@ManyToOne
	@JoinColumn(name = "chip")
	private Mascota mascota;

	@ManyToOne
	@JoinColumn(name = "dniVeterinario")
	private Veterinario veterinario;

	public Cita() {
	}

	public Cita(int id,String nombreCita, LocalDate fecha, String motivo, Mascota mascota, Veterinario veterinario) {
		super();
		this.id = id;
		this.nombreCita=nombreCita;
		this.fecha = fecha;
		this.motivo = motivo;
		this.mascota = mascota;
		this.veterinario = veterinario;
	}

	public Cita(LocalDate fecha,String nombreCita, String motivo, Mascota mascota, Veterinario veterinario) {
		this.nombreCita=nombreCita;
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

	// Metodo para mostrar las citas de un veterinario
	public String mostrarCitasConVeterinario() {
		return "Citas del Veterinario " + veterinario.getNombre() + " con dni " + veterinario.getDni()
				+ ". Cita con fecha " + fecha + " " + motivo;
	}

	// Metodo para mostrar citas de una mascota
	public String mostrarCitasConMascotas() {
		return "Citas de la mascota " + mascota.getNombre() + " con chip " + mascota.getChip() + ". Cita con fecha "
				+ fecha + " motivo" + motivo;
	}

	// Metodo para mostrar citas de un dueño
	public String mostrarCitasDueno() {
		return "Citas de " + mascota.getDueno().getNombre() + " con dni " + mascota.getDueno().getDni()
				+ ".Cita con la fecha" + fecha + " para la mascota" + mascota.getNombre() + " con chip "
				+ mascota.getChip();
	}

	// Metodo para mostrar las citas de una determinada fecha
	public String mostrarFechaCita() {
		return "Listado de citas con la fecha: " + fecha + toString();
	}

	public String getNombreCita() {
		return nombreCita;
	}

	public void setNombreCita(String nombreCita) {
		this.nombreCita = nombreCita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		result = prime * result + ((mascota == null) ? 0 : mascota.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result + ((nombreCita == null) ? 0 : nombreCita.hashCode());
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
		if (nombreCita == null) {
			if (other.nombreCita != null)
				return false;
		} else if (!nombreCita.equals(other.nombreCita))
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
		return "Cita [id=" + id + ", nombreCita=" + nombreCita + ", fecha=" + fecha + ", motivo=" + motivo
				+ ", mascota=" + mascota + ", veterinario=" + veterinario + "]";
	}

}
