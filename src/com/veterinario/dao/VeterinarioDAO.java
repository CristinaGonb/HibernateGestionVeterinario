package com.veterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.Dueno;
import com.veterinario.persistencia.Especialidad;
import com.veterinario.persistencia.HibernateUtil;
import com.veterinario.persistencia.Veterinario;

public class VeterinarioDAO extends GenericDAO<Veterinario> {
	public List<Veterinario> listarTodosLosVeterinarios() {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		sesion.beginTransaction();
		// Realizo consulta
		Query query = sesion.createQuery("SELECT v FROM Veterinario v");
		// Añado rdo de la consulta a la lista
		List<Veterinario> resultadoVeterinario = query.list();

		// Si esta vacio
		if (resultadoVeterinario.isEmpty()) {
			resultadoVeterinario = null;
		} 
		sesion.getTransaction().commit();
		
		return resultadoVeterinario;
	}
	
	public Veterinario buscarPorDni(String dniVeterinario) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		// Realizo consulta
		Veterinario veterinarioABuscar = (Veterinario) session.createQuery("SELECT v FROM Veterinario v WHERE v.dni=?")
				.setString(0, dniVeterinario).uniqueResult();
		session.getTransaction().commit();

		return veterinarioABuscar;
	}
	
	public List<Veterinario> buscarPorEspecialidad(Especialidad especialidad) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Realizo consulta
		Query query = session.createQuery("SELECT v FROM Veterinario v WHERE v.especialidad=?")
				.setString(0,especialidad.name());
		// Añado rdo de la consulta a la lista
		List<Veterinario> especialidadABuscar = query.list();

		// Si esta vacio
		if (especialidadABuscar.isEmpty()) {
			especialidadABuscar = null;
		}
		session.getTransaction().commit();
		return especialidadABuscar;
	}
}
