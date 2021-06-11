package com.veterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.veterinario.persistencia.Dueno;
import com.veterinario.persistencia.HibernateUtil;

/**
 * Clase DuenoDAO que se utiliza para realizar consultas para la clase
 * Dueno
 * 
 * @author Cristina Gonzalez Baizán
 *
 */
public class DuenoDAO extends GenericDAO<Dueno> {
	
	/**
	 * Metodo que recoge todos los dueños registrados en la base de datos
	 * @return resultadoDueno
	 */
	public List<Dueno> listarTodosLosDuenos() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta
		Query query = session.createQuery("SELECT d FROM Dueno d");
		// Añado rdo de la consulta a la lista
		List<Dueno> resultadoDueno = query.list();

		// Si esta vacio
		if (resultadoDueno.isEmpty()) {
			resultadoDueno = null;
		}

		session.getTransaction().commit();

		return resultadoDueno;
	}

	/**
	 * Metodo que devuelve un dueño por su nombre
	 * @param nombreDueno
	 * @return duenoABuscar
	 */
	public List<Dueno> buscarPorNombre(String nombreDueno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta
		Query query = session.createQuery("SELECT d FROM Dueno d WHERE d.nombre=?").setString(0, nombreDueno);
		// Añado rdo de la consulta a la lista
		List<Dueno> duenoABuscar = query.list();

		// Si esta vacio
		if (duenoABuscar.isEmpty()) {
			duenoABuscar = null;
		}

		session.getTransaction().commit();

		return duenoABuscar;
	}

	/**Metodo que busca un dueño por su dni
	 * @param dniDueno
	 * @return duenoABuscar
	 */
	public Dueno buscarPorDni(String dniDueno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta nativa(1 resultado)
		Dueno duenoABuscar = (Dueno) session.createQuery("SELECT d FROM Dueno d WHERE d.dni=?").setString(0, dniDueno)
				.uniqueResult();

		session.getTransaction().commit();

		return duenoABuscar;
	}
}
