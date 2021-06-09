package com.veterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.veterinario.persistencia.Dueno;
import com.veterinario.persistencia.HibernateUtil;

public class DuenoDAO extends GenericDAO<Dueno> {
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

	public Dueno buscarPorDni(String dniD) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta nativa(1 resultado)
		Dueno duenoABuscar = (Dueno) session.createQuery("SELECT d FROM Dueno d WHERE d.dni=?").setString(0, dniD)
				.uniqueResult();

		session.getTransaction().commit();

		return duenoABuscar;
	}
}
