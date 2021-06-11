package com.veterinario.persistencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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

/**
 * Clase cita recoge la información de la citas
 * 
 * @author Cristina González Baizán
 *
 */
@Entity
@Table(name = "hib_cita")
public class Cita implements Serializable {

	// Propiedades
	/**
	 * Propiedad que muestra el id único de cada cita
	 */
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * Propiedad que recoge la fecha de la cita
	 */
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	/**
	 * Propiedad que muestra el motivo de la cita de forma detallada
	 */
	@Column(name = "motivo" , length = 100)
	private String motivo;

	/**
	 * Clave foránea de la tabla mascota que recoge el chip de la mascota para
	 * añadir la cita
	 */
	@ManyToOne
	@JoinColumn(name = "chip")
	private Mascota mascota;

	/**
	 * Clave foranea de la tabla veterinario que recoge el dni del profesional que
	 * atenderá la cita
	 */
	@ManyToOne
	@JoinColumn(name = "dniVeterinario")
	private Veterinario veterinario;

	/**
	 * Constructor vacio de Cita
	 */
	public Cita() {
	}

	/**
	 * Constructor de cita que recoge todas las propiedades
	 * 
	 * @param id          genera id
	 * @param fecha       guarda la fecha de a cita
	 * @param motivo      motivo detallado de la cita
	 * @param mascota     guarda el chip de la mascota relacionada con la cita
	 * @param veterinario guarda el dni del profesional que atiende la cita
	 */
	public Cita(int id, Date fecha, String motivo, Mascota mascota, Veterinario veterinario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.mascota = mascota;
		this.veterinario = veterinario;
	}

	/**
	 * Constructor de cita que recoge las propiedades menos el id
	 * 
	 * @param fecha       guarda la fecha de a cita
	 * @param motivo      motivo detallado de la cita
	 * @param mascota     guarda el chip de la mascota relacionada con la cita
	 * @param veterinario guarda el dni del profesional que atiende la cita
	 */
	public Cita(Date fecha, String motivo, Mascota mascota, Veterinario veterinario) {
		this.fecha = fecha;
		this.motivo = motivo;
		this.mascota = mascota;
		this.veterinario = veterinario;
	}

	/**
	 * Metodo get que devuelve la informacion de una cita
	 */

	public int getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	/**
	 * Metodo set que modifca toda la informacion de la cita
	 */

	public void setId(int id) {
		this.id = id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	/**
	 * Metodo que devuelve las citas de un veterinario
	 * 
	 * @return resultadoCitasVeterinario
	 */
	public String mostrarCitasConVeterinario() {
		return "Citas del Veterinario " + veterinario.getNombre() + " con dni " + veterinario.getDni()
				+ ". Cita con fecha " + fecha + " " + motivo;
	}

	/**
	 * Metodo que devuelve las citas de una mascota
	 * 
	 * @return resultadoCitasMascotas
	 */
	public String mostrarCitasConMascotas() {
		return "Citas de la mascota " + mascota.getNombre() + " con chip " + mascota.getChip() + ". Cita con fecha "
				+ fecha + " motivo" + motivo;
	}

	/**
	 * Metodo que devuelve las citas de las mascotas de un dueño
	 * 
	 * @return resultadoCitasMascotasDeUnDueño
	 */
	public String mostrarCitasDueno() {
		return "Citas de " + mascota.getDueno().getNombre() + " con dni " + mascota.getDueno().getDni()
				+ ".Cita con la fecha" + fecha + " para la mascota" + mascota.getNombre() + " con chip "
				+ mascota.getChip();
	}

	/**
	 * Metodo que devuelve la cita de una fecha determinada
	 * 
	 * @return resultadoCitasFecha
	 */
	public String mostrarFechaCita() {
		return "Listado de citas con la fecha: " + fecha + toString();
	}

	/**
	 * Metodo equals y hashCode
	 */
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

	/**
	 * Metodo toString que devuelve la infromacion de la cita
	 */
	@Override
	public String toString() {
		return "Cita [id=" + id + ",  fecha=" + fecha + ", motivo=" + motivo + ", mascota=" + mascota + ", veterinario="
				+ veterinario + "]";
	}

}
