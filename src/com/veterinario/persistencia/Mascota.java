package com.veterinario.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Mascota que recoge la información de las mascotas de la aplicacion
 * 
 * @author Cristina Gonzalez Baizan
 *
 */
@Entity
@Table(name = "hib_mascota")
public class Mascota implements Serializable {

	/**
	 * Propiedad que recoge el chip de una mascota Primary key
	 */
	@Id
	@Column(name = "Chip", length = 9)
	private int chip;

	/**
	 * Propiedad que recoge el nombre de una mascota
	 */
	@Column(name = "nombre", length = 50)
	private String nombre;

	/**
	 * Propiedad que recoge la raza de una mascota
	 */
	@Column(name = "raza", length = 80)
	private String raza;

	/**
	 * Propiedad que recoge el sexo de una mascota
	 */
	@Column(name = "sexo")
	private String sexo;

	/**
	 * Relacion N:1 de la clase mascotas. Una mascota solo puede tener un dueño.
	 * Propiedad de la clase dueño, la foreignKey es dniDueño
	 */
	@ManyToOne
	@JoinColumn(name = "dniDueno")
	private Dueno dueno;

	public Mascota() {
	}

	/**
	 * Constructor que recoge todas las propiedades
	 * 
	 * @param chip
	 * @param nombre
	 * @param raza
	 * @param sexo
	 * @param dueno
	 */
	public Mascota(int chip, String nombre, String raza, String sexo, Dueno dueno) {

		this.chip = chip;
		this.nombre = nombre;
		this.raza = raza;
		this.sexo = sexo;
		this.dueno = dueno;
	}

	/**
	 * Metodos get para devolver informacion sobre las propiedades de la mascota
	 */

	public int getChip() {
		return chip;
	}

	public String getNombre() {
		return nombre;
	}

	public String getRaza() {
		return raza;
	}

	public String getSexo() {
		return sexo;
	}

	public Dueno getDueno() {
		return dueno;
	}

	/**
	 * Metodos set para modificar informacion sobre las propiedades de la mascota
	 */

	public void setChip(int chip) {
		this.chip = chip;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setDueno(Dueno dueno) {
		this.dueno = dueno;
	}

	/**
	 * Metodo hashCode y equals
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chip;
		result = prime * result + ((dueno == null) ? 0 : dueno.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((raza == null) ? 0 : raza.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Mascota other = (Mascota) obj;
		if (chip != other.chip)
			return false;
		if (dueno == null) {
			if (other.dueno != null)
				return false;
		} else if (!dueno.equals(other.dueno))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (raza == null) {
			if (other.raza != null)
				return false;
		} else if (!raza.equals(other.raza))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}

	/**
	 * Metodo toString que devuelve la informacion de la mascota
	 */
	@Override
	public String toString() {
		return "Mascota [chip=" + chip + ", nombre=" + nombre + ", raza=" + raza + ", sexo=" + sexo + ", dueno=" + dueno
				+ "]";
	}
}
