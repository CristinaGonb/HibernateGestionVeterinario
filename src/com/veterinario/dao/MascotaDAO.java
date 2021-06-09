package com.veterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.Dueno;
import com.veterinario.persistencia.HibernateUtil;
import com.veterinario.persistencia.Mascota;

public class MascotaDAO extends GenericDAO<Mascota> {

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

	public Mascota buscarPorChip(int chip) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta
		Mascota mascotaABuscar = (Mascota) session.createQuery("SELECT m FROM Mascota m WHERE m.chip=?")
				.setInteger(0, chip).uniqueResult();

		session.getTransaction().commit();

		return mascotaABuscar;
	}

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

	public List<Mascota> mascotaPorCiudad(String ciudad) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT m FROM Mascota m WHERE ciudad=?").setString(0, ciudad);
		// Añado rdo de la consulta a la lista
		List<Mascota> resultadoCiudadMascota = query.list();

		// Si esta vacio
		if (resultadoCiudadMascota.isEmpty()) {
			resultadoCiudadMascota = null;
		}

		sesion.getTransaction().commit();

		return resultadoCiudadMascota;
	}
}
