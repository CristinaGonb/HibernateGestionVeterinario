package com.veterinario.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.Dueno;
import com.veterinario.persistencia.HibernateUtil;
import com.veterinario.persistencia.Mascota;

/**
 * Clase MascotaDAO que se utiliza para realizar consultas para la clase Mascota
 * 
 * @author Cristina Gonzalez Baizán
 *
 */
public class MascotaDAO extends GenericDAO<Mascota> {

	/**
	 * Metodo que recoge una lista de todas las mascotas de la base de datos
	 * 
	 * @return resultadoMascota
	 */
	public List<Mascota> listarTodasLasMascotas() {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT m FROM Mascota m");
		// Añado rdo de la consulta a la lista
		List<Mascota> resultadoMascota = query.list();

		// Si esta vacio
		if (resultadoMascota.isEmpty()) {
			resultadoMascota = null;
		}

		sesion.getTransaction().commit();

		return resultadoMascota;
	}

	/**
	 * Metodo que devuelve una mascota por su chip
	 * 
	 * @param chip
	 * @return mascotaABuscar
	 */
	public Mascota buscarPorChip(int chip) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta
		Mascota mascotaABuscar = (Mascota) session.createQuery("SELECT m FROM Mascota m WHERE m.chip=?")
				.setInteger(0, chip).uniqueResult();

		session.getTransaction().commit();

		return mascotaABuscar;
	}

	/**
	 * Metodo que busca a las mascotas por su dueño
	 * 
	 * @return resultadoDueñoMascota
	 */
	public List<Mascota> mascotaPorDueno(String dniDueno) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT m FROM Mascota m WHERE dniDueno=?").setString(0, dniDueno);
		// Añado rdo de la consulta a la lista
		List<Mascota> resultadoDuenoMascota = query.list();

		// Si esta vacio
		if (resultadoDuenoMascota.isEmpty()) {
			resultadoDuenoMascota = null;
		}

		sesion.getTransaction().commit();

		return resultadoDuenoMascota;
	}

	/**
	 * Metodo que devuelve el nombre de una mascota de una ciudad determinada
	 * Consulta de agrupacion
	 * 
	 * @param ciudad
	 * @return resultadoCiudadMascota
	 */
	public List<Mascota> mascotaPorCiudad(String ciudad) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery(
				"SELECT m.nombre,count (m.nombre) FROM Mascota m JOIN m.dueno d WHERE d.ciudad=? GROUP BY m.nombre")
				.setString(0, ciudad);
		// Añado rdo de la consulta a la lista
		List<Object[]> resultado = query.list();
		List<Mascota> resultadoCiudadMascota = new ArrayList<Mascota>();

		// Recorro el rdo en una lista de objetos
		for (Object[] r : resultado) {
			Mascota mascota = new Mascota();
			mascota.setNombre((String) r[0]);
			resultadoCiudadMascota.add(mascota);
		}
		// Si esta vacio
		if (resultadoCiudadMascota.isEmpty()) {
			resultadoCiudadMascota = null;
		}

		sesion.getTransaction().commit();

		return resultadoCiudadMascota;
	}
}
