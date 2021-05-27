package com.veterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.HibernateUtil;
import com.veterinario.persistencia.Mascota;

public class MascotaDAO extends GenericDAO<Mascota> {
	public List<Mascota> listarTodasLasCitas() {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT m FROM Mascota m");
		// Añado rdo de la consulta a la lista
		List<Mascota> resultadoMascota = query.list();

		// Si esta vacio
		if (resultadoMascota.isEmpty()) {
			resultadoMascota = null;
		} else {
			// Recorro la lista
			for (Mascota mascota : resultadoMascota) {
				System.out.println(mascota);
			}
		}
		return resultadoMascota;
	}
}
