package com.veterinario.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Veterinario que recoge la informacion de los veterinarios registrados
 * en la aplicación
 * 
 * @author Cristina Gonzalez Baizan
 *
 */
@Entity
@Table(name = "hib_veterinario")
public class Veterinario implements Serializable {

	// Propiedades
	/**
	 * Propiedad que recoge el dni de un veterinario. Primary key
	 */
	@Id
	@Column(name = "dni", length = 9)
	private String dni;

	/**
	 * Propiedad que recoge el nombre de un veterinario
	 */
	@Column(name = "nombre" , length = 50)
	private String nombre;

	/**
	 * Propiedad que recoge los apellidos de un veterinario
	 */
	@Column(name = "apellidos", length = 50)
	private String apellidos;

	/**
	 * Propiedad que almacena un numero determinado de especialidades que puede
	 * ejercer un veterinario Tipo enumerado, de tipo texto
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "especialidad", length = 50)
	private Especialidad especialidad;

	/**
	 * Relacion 1:N de la clase citas. Un veterinario puede tener varias citas, paso
	 * como foreignKey el dniVeterinario
	 */

	@OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
	private List<Cita> citasVeterinario;

	public Veterinario() {
	}

	/**
	 * Contructor de Veterinario que recoge todas las propiedades
	 * 
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param especialidad
	 */
	public Veterinario(String dni, String nombre, String apellidos, String especialidad) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		citasVeterinario = new ArrayList<Cita>();
		this.especialidad = Enum.valueOf(Especialidad.class, especialidad);
	}

	/**
	 * Metodos get para devolver informacion sobre las propiedades del veterinario
	 * 
	 */
	
	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public List<Cita> getCitasVeterinario() {
		return citasVeterinario;
	}
	
	/**
	 * Metodos set para modificar la informacion de las propiedades de un veterinario
	 */

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public void setCitasVeterinario(List<Cita> citasVeterinario) {
		this.citasVeterinario = citasVeterinario;
	}

	/**
	 * Metodo hashCode y equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((citasVeterinario == null) ? 0 : citasVeterinario.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((especialidad == null) ? 0 : especialidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Veterinario other = (Veterinario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (citasVeterinario == null) {
			if (other.citasVeterinario != null)
				return false;
		} else if (!citasVeterinario.equals(other.citasVeterinario))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (especialidad != other.especialidad)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	/**
	 * Metodo toString que devuelve la informacion del veterinario
	 */
	@Override
	public String toString() {
		return "Veterinario [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", especialidad="
				+ especialidad + "]";
	}

}
