package com.veterinario.modelo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.veterinario.dao.CitaDAO;
import com.veterinario.dao.DuenoDAO;
import com.veterinario.dao.MascotaDAO;
import com.veterinario.dao.VeterinarioDAO;
import com.veterinario.persistencia.Dueno;
import com.veterinario.persistencia.HibernateUtil;

public class Principal {
	private static Session session;
	private static DuenoDAO duenoDao = new DuenoDAO();
	private static MascotaDAO mascotaDao = new MascotaDAO();
	private static VeterinarioDAO veteDao = new VeterinarioDAO();
	private static CitaDAO citaDao = new CitaDAO();

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;

		do {
			opcion = menuPrincipal();
			opcionesMenuPrincipal(opcion);
		} while (opcion != 0);
	}

	private static void cerrarSesion() {
		HibernateUtil.closeSessionFactory();
	}

	private static void configurarSesion() {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

// --------------------------------------------------------------------------
	/*
	 * Método menú principal de la aplicación
	 */
	public static int menuPrincipal() {
		int opcionMenu;

		do {
			System.out.println("============SISTEMA GESTION VETERINARIO============");
			System.out.println("Bienvenidos al sistema. Introduzca un numero: ");
			System.out.println("0. Salir");
			System.out.println("1. Veterinario");
			System.out.println("2. Dueño de la mascota");
			System.out.println("3. Mascota");
			System.out.println("4. Cita");
			System.out.println("===================================================");

			// Introduzco opción del menú
			opcionMenu = Integer.parseInt(teclado.nextLine());

		} while (opcionMenu < 0 || opcionMenu > 4);

		return opcionMenu;
	}

	/*
	 * Metodo del submenu del menu de Veterinario
	 */
	public static int menuVeterinario() {
		int opcionVeterinario;

		do {
			System.out.println("====== Menú Veterinario=======");
			System.out.println("1. Listar todos los veterinarios");
			System.out.println("2. Consultar veterinario por dni");
			System.out.println("3. Insertar un nuevo veterinario");
			System.out.println("4. Volver al menú principal");
			System.out.println("=============================");
			System.out.println("Seleccione una opción: ");

			// Introduzco opción del menú
			opcionVeterinario = Integer.parseInt(teclado.nextLine());

		} while (opcionVeterinario < 1 || opcionVeterinario > 4);

		return opcionVeterinario;
	}

	/*
	 * Metodo del submenu del menu de Dueño de la mascota
	 */
	public static int menuDueno() {
		int opcionDueno;

		do {
			System.out.println("====== Menú Dueño mascota=======");
			System.out.println("1. Listar todos los dueños");
			System.out.println("2. Consultar dueño por nombre");
			System.out.println("3. Insertar un nuevo dueño");
			System.out.println("4. Eliminar dueño por dni");
			System.out.println("5. Volver al menú principal");
			System.out.println("================================");
			System.out.println("Seleccione una opción: ");

			// Introduzco opción del menú
			opcionDueno = Integer.parseInt(teclado.nextLine());

		} while (opcionDueno < 1 || opcionDueno > 5);

		return opcionDueno;
	}

	/*
	 * Metodo del submenu del menu de Mascota
	 */
	public static int menuMascota() {
		int opcionMascota;

		do {
			System.out.println("====== Menú Mascota======");
			System.out.println("1. Listar todas las mascotas");
			System.out.println("2. Consultar mascotas por ciudad");// dueño
			System.out.println("3. Insertar nueva mascota");
			System.out.println("4. Eliminar Mascota por chip");
			System.out.println("5. Volver al menú principal");
			System.out.println("=========================");
			System.out.println("Seleccione una opción:");

			// Introduzco opción del menú
			opcionMascota = Integer.parseInt(teclado.nextLine());

		} while (opcionMascota < 1 || opcionMascota > 5);

		return opcionMascota;
	}

	/*
	 * Metodo del submenu del menu de Cita
	 */
	public static int menuCita() {
		int opcionCita;

		do {
			System.out.println("====== Menú Cita======");
			System.out.println("1. Listar todas las citas");
			System.out.println("2. Consultar todas las citas de un veterinario");
			System.out.println("3. Insertar nueva cita ");
			System.out.println("4. Modificar motivo de la cita");
			System.out.println("5. Volver al menú principal");
			System.out.println("=============================");
			System.out.println("Seleccione una opción: ");

			// Introduzco opción del menú
			opcionCita = Integer.parseInt(teclado.nextLine());

		} while (opcionCita < 1 || opcionCita > 5);

		return opcionCita;
	}

	// -------------OPCIONES MENU----------------------------

	public static void opcionesMenuPrincipal(int opcionElegida) {
		int opcion;

		switch (opcionElegida) {
		case 0:
			break;
		case 1:
			do {
				opcion = menuVeterinario();
			} while (opcion != 4);
			break;
		case 2:
			do {
				opcion = menuDueno();
				tratarMenuDueno(opcion);
			} while (opcion != 5);
			break;
		case 3:
			do {
				opcion = menuMascota();
			} while (opcion != 5);
			break;
		case 4:
			do {
				opcion = menuCita();
			} while (opcion != 5);
			break;
		}
	}

	// ------------ TRATAR SUBMENU VETERINARIO-----------------------------------
	private static void tratarMenuDueno(int opcionElegida) {

		int opcion;

		switch (opcionElegida) {
		case 1:
			listarTodosLosDuenos();
			break;
		case 2:
			listarPorNombre();
			break;
		case 3:
			anadirDueno();
			break;
		case 4:
			borrarDuenoPorDni();
			break;
		case 5:
			// Volver al menu principal
			opcion = menuPrincipal();
			opcionesMenuPrincipal(opcion);
			break;
		}
	}

	public static void listarTodosLosDuenos() {
		List<Dueno> listaDueno = duenoDao.listarTodosLosDuenos();

		if (listaDueno == null) {
			System.out.println("No se encuentran dueños registrados");
		} else {
			for (Dueno dueno : listaDueno) {
				System.out.println(dueno);
			}
		}
	}

	public static void listarPorNombre() {

		System.out.println("Introduce el nombre que deseas buscar: ");
		String nombre = teclado.nextLine();

		List<Dueno> buscarDuenoNombre = duenoDao.buscarPorNombre(nombre);

		if (buscarDuenoNombre == null) {
			System.out.println("No se encuentran dueños con ese nombre " + nombre);
		} else {
			for (Dueno dueno : buscarDuenoNombre) {
				System.out.println(dueno);
			}
		}
	}

	public static void anadirDueno() {
		String dni, nombre, apellidos, telefono, ciudad;
		Dueno duenoNuevo;

		System.out.println("Añadir nuevo dueño");
		System.out.println("Introduzca el dni del dueño de la mascota: ");
		dni = teclado.nextLine();

		duenoNuevo = duenoDao.buscarPorDni(dni);

		if (duenoNuevo != null) {
			System.out.println("Error, este dni ya existe " + dni);
		} else {
			System.out.println("Introduzca el nombre del dueño: ");
			nombre = teclado.nextLine();
			System.out.println("Introduzca los apellidos del dueño: ");
			apellidos = teclado.nextLine();
			System.out.println("Introduzca el telefono: ");
			telefono = teclado.nextLine();
			System.out.println("Introduce la ciudad: ");
			ciudad = teclado.nextLine();

			// Inserto nuevo dueño
			duenoNuevo = new Dueno(dni, nombre, apellidos, telefono, ciudad);
			duenoDao.guardar(duenoNuevo);
			System.out.println("Dueno creado correctamente");
		}
	}

	public static void borrarDuenoPorDni() {
		System.out.println("Introduce el dni del dueño que deseas eliminar: ");
		String dni = teclado.nextLine();

		Dueno usuarioAEliminar = duenoDao.buscarPorDni(dni);

		if (usuarioAEliminar == null) {
			System.out.println("El usuario con dni " + dni + " no existe");
		} else {
			duenoDao.borrar(usuarioAEliminar);
			System.out.println("Registro eliminad correctamente");
		}
	}
}