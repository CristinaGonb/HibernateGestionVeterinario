package com.veterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.HibernateUtil;


public class CitaDAO extends GenericDAO<Cita> {

	public List<Cita> listarTodasLasCitas() {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT c FROM Cita c");
		// Añado rdo de la consulta a la lista
		List<Cita> resultadoCita = query.list();

		// Si esta vacio
		if (resultadoCita.isEmpty()) {
			resultadoCita = null;
		} else {
			// Recorro la lista
			for (Cita cita : resultadoCita) {
				System.out.println(cita);
			}
		}
		return resultadoCita;
	}
}
