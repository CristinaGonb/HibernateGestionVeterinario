package com.veterinario.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.HibernateUtil;

public class CitaDAO extends GenericDAO<Cita> {

	public List<Cita> listarTodasLasCitas() {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c");
		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		}

		sesion.getTransaction().commit();

		return resultadoCita;
	}

	public List<Cita> listarCitasVeterinario(String dni) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c WHERE c.dniVeterinario=?");
		query.setString(0, dni);

		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		}

		sesion.getTransaction().commit();

		return resultadoCita;
	}

	public List<Cita> listarCitasMascota(int chip) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c WHERE c.chip=?");
		query.setInteger(0, chip);
		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		}

		sesion.getTransaction().commit();

		return resultadoCita;
	}

	public List<Cita> listarCitasDueno(String dniDueno) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c WHERE c.mascota.dniDueno=?");
		query.setString(0, dniDueno);
		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		}

		sesion.getTransaction().commit();

		return resultadoCita;
	}

	public List<Cita> listarCitasDia(Date fecha) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c WHERE c.fecha =?");
		query.setDate(0, fecha);
		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		}

		sesion.getTransaction().commit();

		return resultadoCita;
	}

	public Cita buscarCitasPorNombre(String nombre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Cita CitaBuscar = (Cita) session.createSQLQuery("SELECT c FROM Cita c WHERE c.nombreCita=?")
				.setString(0, nombre).uniqueResult();

		session.getTransaction().commit();

		return CitaBuscar;
	}
}
