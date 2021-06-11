package com.veterinario.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.HibernateUtil;
import com.veterinario.persistencia.Mascota;

/**
 * Clase CitaDAO que se utiliza para realizar consultas para la clase Cita
 * 
 * @author Cristina Gonzalez Baizán
 *
 */
public class CitaDAO extends GenericDAO<Cita> {

	/**
	 * Metodo que devuelve una lista de todas las citas registradas en la base de
	 * datos
	 * 
	 * @return resultadoCita
	 */
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

	/**
	 * Metodo que devuelve una lista de las citas que tiene un veterinario
	 * 
	 * @param dni
	 * @return resultadoCita
	 */
	public List<Cita> listarCitasVeterinario(String dni) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c WHERE dniVeterinario=?")
				.setString(0, dni);

		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		}

		sesion.getTransaction().commit();

		return resultadoCita;
	}

	/**
	 * Metodo que devuelve una lista de las citas que tiene una mascota
	 * 
	 * @param chip
	 * @return resultadoCita
	 */
	public List<Cita> listarCitasMascota(int chip) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c WHERE chip=?");
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

	/**
	 * Metodo que devuelve una lista de las citas que tiene un dueno
	 * 
	 * @param dniDueno
	 * @return resultadoCita
	 */
	public List<Cita> listarCitasDueno(String dniDueno) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c JOIN c.mascota m JOIN m.dueno d WHERE d.dni=?");
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

	/**
	 * Metodo que devuelve una lista de las citas que tiene un dueño agrupadas por
	 * citas con fechas actuales
	 * 
	 * @param dniDueno
	 * @return resultadoCita
	 */
	public List<Cita> listarCitasDuenoAgrupadasPorFechaDescendente(String dniDueno) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c JOIN c.mascota m JOIN m.dueno d WHERE d.dni=? and c.fecha > ?");
		query.setString(0, dniDueno);
		//Creo newDate para que me busque por la fecha actual
		query.setDate(1, new Date());
		
		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		}

		sesion.getTransaction().commit();

		return resultadoCita;
	}

	/**
	 * Metodo que devuelve una lista de las citas por id
	 * 
	 * @param id
	 * @return resultadoCita
	 */
	public Cita listarPorId(int id) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta
		Cita resultadoCita = (Cita) session.createQuery("SELECT c FROM Cita c WHERE c.id=?").setInteger(0, id)
				.uniqueResult();

		session.getTransaction().commit();

		return resultadoCita;
	}

	/**
	 * Metodo que devuelve una lista de las citas que tiene en una fecha determinada
	 * 
	 * @param fecha
	 * @return resultadoCita
	 */
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

}
