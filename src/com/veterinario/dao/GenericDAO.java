package com.veterinario.dao;

import org.hibernate.Session;
import com.veterinario.persistencia.HibernateUtil;

/**
 * Esta clase GenericDAO se utiliza para trabajar con la base de datos.
 * @author Cristina González Baizán
 * @param <T>
 */
public class GenericDAO<T> {

	/**
	 * Metodo que se utiliza para guardar un objeto de la base de datos
	 * @param entidad
	 */
	public void guardar(T entidad) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(entidad);
		session.getTransaction().commit();
	}
	
	/**
	 * Metodo que se utiliza para borrar un objeto de la base de datos
	 * @param entidad
	 */
	public  void borrar(T entidad) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(entidad);
		session.getTransaction().commit();
	}
	
	/**
	 * Metodo que se utiliza para actualizar un objeto de la base de datos
	 * @param entidad
	 */
	public void modificar(T entidad) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(entidad);
		session.getTransaction().commit();
	}
}
