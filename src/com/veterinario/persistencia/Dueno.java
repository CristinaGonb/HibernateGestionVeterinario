package com.veterinario.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Dueño que recoge la informacion de los dueños registrados en la
 * aplicación
 * 
 * @author Cristina Gonzalez Baizan
 *
 */
@Entity
@Table(name = "hib_dueno")
public class Dueno implements Serializable {

	/**
	 * Propiedad que recoge el dni del dueño de la mascota Primary key
	 */
	@Id
	@Column(name = "dni", length = 9)
	private String dni;

	/**
	 * Propiedad que recoge el nombre de un dueño
	 */
	@Column(name = "nombre", length = 50)
	private String nombre;

	/**
	 * Propiedad que recoge los apellidos de un dueño
	 */

	@Column(name = "apellidos", length = 50)
	private String apellidos;

	/**
	 * Propiedad que recoge el telefono
	 */
	@Column(name = "telefono", length = 9)
	private String telefono;

	/**
	 * Propiedad que recoge la ciudad de un dueño
	 */
	@Column(name = "ciudad", length = 80)
	private String ciudad;

	/**
	 * Relacion 1:N. Un dueño puede tener varias mascotas
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dueno", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Mascota> listaMascota=new ArrayList<Mascota>();

	public Dueno() {
	}

	/**
	 * Constuctor que recoga toda la informacion de dueño
	 * 
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param telefono
	 * @param ciudad
	 */
	public Dueno(String dni, String nombre, String apellidos, String telefono, String ciudad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.ciudad = ciudad;
		
	}

	/**
	 * Metodo get que devuelve la informacion del dueño
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

	public String getTelefono() {
		return telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public List<Mascota> getListaMascota() {
		return listaMascota;
	}

	/**
	 * Metodo set que modifica la informacion del dueño
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

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setListaMascota(List<Mascota> listaMascota) {
		this.listaMascota = listaMascota;
	}

	/**
	 * Metodo hashCode y equal
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((listaMascota == null) ? 0 : listaMascota.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Dueno other = (Dueno) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (listaMascota == null) {
			if (other.listaMascota != null)
				return false;
		} else if (!listaMascota.equals(other.listaMascota))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	/**
	 * Metodo toString que recoge toda la informacion del dueño
	 */
	@Override
	public String toString() {
		return "Dueno [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", ciudad=" + ciudad + "]";
	}
}
