package com.veterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.HibernateUtil;
import com.veterinario.persistencia.Veterinario;

public class VeterinarioDAO extends GenericDAO<Veterinario> {
	public List<Veterinario> listarTodosLosVeterinarios() {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT v FROM Veterinario v");
		// Añado rdo de la consulta a la lista
		List<Veterinario> resultadoVeterinario = query.list();

		// Si esta vacio
		if (resultadoVeterinario.isEmpty()) {
			resultadoVeterinario = null;
		} else {
			// Recorro la lista
			for (Veterinario vete : resultadoVeterinario) {
				System.out.println(vete);
			}
		}
		return resultadoVeterinario;
	}
}
