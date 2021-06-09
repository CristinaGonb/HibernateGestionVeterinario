package com.veterinario.modelo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.veterinario.dao.CitaDAO;
import com.veterinario.dao.DuenoDAO;
import com.veterinario.dao.MascotaDAO;
import com.veterinario.dao.VeterinarioDAO;
import com.veterinario.persistencia.Cita;
import com.veterinario.persistencia.Dueno;
import com.veterinario.persistencia.Especialidad;
import com.veterinario.persistencia.HibernateUtil;
import com.veterinario.persistencia.Mascota;
import com.veterinario.persistencia.Veterinario;

/**
 * Aplicación para uso veterinario donde se gestiona la información de las
 * mascotas
 * 
 * @author Cristina González Baizán
 */
public class Principal {
	private static Session session;
	private static DuenoDAO duenoDao = new DuenoDAO();
	private static MascotaDAO mascotaDao = new MascotaDAO();
	private static VeterinarioDAO veteDao = new VeterinarioDAO();
	private static CitaDAO citaDao = new CitaDAO();

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
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
		int opcionMenu = 0;
		boolean encontrado = false;

		do {
			do {
				try {
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
					encontrado = true;
				} catch (NumberFormatException e) {
					System.out.println("Debes introducir un numero");
				}
			} while (encontrado == false);

		} while (opcionMenu < 0 || opcionMenu > 4);

		return opcionMenu;
	}

	/*
	 * Metodo del submenu del menu de Veterinario
	 */
	public static int menuVeterinario() {
		int opcionVeterinario = 0;
		boolean encontradoNumero = false;

		do {
			do {

				try {
					System.out.println("======Menú Veterinario======");
					System.out.println("1. Listar todos los veterinarios");
					System.out.println("2. Consultar veterinario por dni");
					System.out.println("3. Consultar veterinarios por especialidad");
					System.out.println("4. Insertar un nuevo veterinario");
					System.out.println("5. Volver al menú principal");
					System.out.println("============================");
					System.out.println("Seleccione una opción: ");

					// Introduzco opción del menú
					opcionVeterinario = Integer.parseInt(teclado.nextLine());
					encontradoNumero = true;
				} catch (NumberFormatException e) {
					System.out.println("Debes introducir un numero");
				}
			} while (encontradoNumero == false);

		} while (opcionVeterinario < 1 || opcionVeterinario > 5);

		return opcionVeterinario;
	}

	/*
	 * Metodo del submenu del menu de Dueño de la mascota
	 */
	public static int menuDueno() {
		int opcionDueno = 0;
		boolean encontradoNumero = false;

		do {
			do {

				try {
					System.out.println("====== Menú Dueño mascota=======");
					System.out.println("1. Listar todos los dueños");
					System.out.println("2. Consultar dueño por nombre");
					System.out.println("3. Insertar un nuevo dueño");
					System.out.println("4. Eliminar dueño");
					System.out.println("5. Volver al menú principal");
					System.out.println("================================");
					System.out.println("Seleccione una opción: ");

					// Introduzco opción del menú
					opcionDueno = Integer.parseInt(teclado.nextLine());

					encontradoNumero = true;
				} catch (NumberFormatException e) {
					System.out.println("Debes introducir un numero");
				}
			} while (encontradoNumero == false);
		} while (opcionDueno < 1 || opcionDueno > 5);

		return opcionDueno;
	}

	/*
	 * Metodo del submenu del menu de Mascota
	 */
	public static int menuMascota() {
		int opcionMascota = 0;
		boolean encontradoNumero = false;

		do {
			do {

				try {
					System.out.println("====== Menú Mascota======");
					System.out.println("1. Listar todas las mascotas");
					System.out.println("2. Consultar mascotas por ciudad");// dueño
					System.out.println("3. Consultar mascotas de un dueño");
					System.out.println("4. Insertar nueva mascota");
					System.out.println("5. Eliminar Mascota por chip");
					System.out.println("6. Volver al menú principal");
					System.out.println("=========================");
					System.out.println("Seleccione una opción:");

					// Introduzco opción del menú
					opcionMascota = Integer.parseInt(teclado.nextLine());

					encontradoNumero = true;
				} catch (NumberFormatException e) {
					System.out.println("Debes introducir un numero");
				}
			} while (encontradoNumero == false);
		} while (opcionMascota < 1 || opcionMascota > 6);

		return opcionMascota;
	}

	/*
	 * Metodo del submenu del menu de Cita
	 */
	public static int menuCita() {
		int opcionCita = 0;
		boolean encontradoNumero = false;

		do {
			do {

				try {
					System.out.println("====== Menú Cita======");
					System.out.println("1. Listar todas las citas");
					System.out.println("2. Consultar todas las citas de un veterinario");
					System.out.println("3. Consultar citas de una mascota");
					System.out.println("4. Consultar citas de un dueño");// 3 tablas
					System.out.println("5. Consultar citas de un día");
					System.out.println("6. Insertar nueva cita ");
					System.out.println("7. Modificar fecha de la cita");
					System.out.println("8. Volver al menú principal");
					System.out.println("=============================");
					System.out.println("Seleccione una opción: ");

					// Introduzco opción del menú
					opcionCita = Integer.parseInt(teclado.nextLine());

					encontradoNumero = true;
				} catch (NumberFormatException e) {
					System.out.println("Debes introducir un numero");
				}
			} while (encontradoNumero == false);

		} while (opcionCita < 1 || opcionCita > 8);

		return opcionCita;
	}
	// -------------OPCIONES MENU----------------------------

	public static void opcionesMenuPrincipal(int opcionElegida) {
		int opcion;

		switch (opcionElegida) {
		case 1:
			do {
				opcion = menuVeterinario();
				tratarMenuVeterinario(opcion);
			} while (opcion != 5);
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
				tratarMenuMascota(opcion);
			} while (opcion != 6);
			break;
		case 4:
			do {
				opcion = menuCita();
				tratarMenuCitas(opcion);
			} while (opcion != 8);
			break;
		}
	}

	// ------------ TRATAR SUBMENU VETERINARIO-----------------------------------
	private static void tratarMenuVeterinario(int opcionElegida) {

		switch (opcionElegida) {
		case 1:
			listarTodosLosVeterinarios();
			break;
		case 2:
			listarVeterinarioPorDni();
			break;
		case 3:
			listarVeterinarioPorEspecialidad();
			break;
		case 4:
			anadirProfesionalVeterinario();
			break;
		}
	}

	public static void listarTodosLosVeterinarios() {
		List<Veterinario> listaVeterinario = veteDao.listarTodosLosVeterinarios();

		if (listaVeterinario == null) {
			System.out.println("No se encuentran veterinarios registrados");
		} else {
			for (Veterinario todosVeterinarios : listaVeterinario) {
				System.out.println(todosVeterinarios);
			}
		}
	}

	public static void listarVeterinarioPorDni() {

		System.out.println("Introduce el dni que deseas buscar: ");
		String dniVeterinario = teclado.nextLine();

		Veterinario buscarDniVeterinario = veteDao.buscarPorDni(dniVeterinario);

		if (buscarDniVeterinario == null) {
			System.out.println("No se encuentran ningun veterinario registrado con ese dni" + dniVeterinario);
		} else {
			System.out.println(buscarDniVeterinario.toString());
		}
	}

	public static void listarVeterinarioPorEspecialidad() {

		Especialidad especialidadABuscar = null;
		int numeroEspecialidad;

		System.out.println("Introduzca la especialidad del veterinario que deseas buscar: ");
		System.out.println("\t0-DERMATOLOGIA \n\t1-PELUQUERIA \n\t2-CIRUJIA \n\t3-GENERAL");
		numeroEspecialidad = Integer.parseInt(teclado.nextLine());

		// Si la especialidad introducida no existe, mensaje de error para volver a
		// insertar las opciones que hay
		if (numeroEspecialidad >= 0 && numeroEspecialidad <= 3) {
			especialidadABuscar = Especialidad.values()[numeroEspecialidad];

			List<Veterinario> listaVeterinariosConEspecialidad = veteDao.buscarPorEspecialidad(especialidadABuscar);

			if (listaVeterinariosConEspecialidad == null) {
				System.out.println("Error, no existe ningun veterinario registrado con esa especialidad");
			} else {
				for (Veterinario veterinario : listaVeterinariosConEspecialidad) {
					System.out.println(veterinario.toString());
				}
			}
		}
	}

	public static void anadirProfesionalVeterinario() {
		String dni, nombre, apellidos;
		Veterinario nuevoVeterinario;

		System.out.println("Añadir nuevo profesional");
		System.out.println("Introduzca el dni del veterinario: ");
		dni = teclado.nextLine();

		nuevoVeterinario = veteDao.buscarPorDni(dni);

		if (nuevoVeterinario != null) {
			System.out.println("Error, este dni ya existe " + dni);
		} else {
			System.out.println("Introduzca el nombre: ");
			nombre = teclado.nextLine();
			System.out.println("Introduzca los apellidos: ");
			apellidos = teclado.nextLine();

			int numeroEspecialidad;
			do {
				System.out.println("Introduzca la especialidad del veterinario: ");
				System.out.println("\t0-DERMATOLOGIA \n\t1-PELUQUERIA \n\t2-CIRUJIA \n\t3-GENERAL");
				numeroEspecialidad = Integer.parseInt(teclado.nextLine());

			} while (numeroEspecialidad < 0 || numeroEspecialidad > 3);

			String tipoEspecialidad = null;

			switch (numeroEspecialidad) {
			case 0:
				tipoEspecialidad = "DERMATOLOGIA";
				break;
			case 1:
				tipoEspecialidad = "PELUQUERIA";
				break;
			case 2:
				tipoEspecialidad = "CIRUJIA";
				break;
			case 3:
				tipoEspecialidad = "GENERAL";
				break;
			}
			// Inserto nuevo veterinario
			nuevoVeterinario = new Veterinario(dni, nombre, apellidos, tipoEspecialidad);
			veteDao.guardar(nuevoVeterinario);
			System.out.println("Veterinario creado correctamente");
		}
	}

	// ------------ TRATAR SUBMENU DUENO-----------------------------------
	private static void tratarMenuDueno(int opcionElegida) {

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
			System.out.println("¿Está usted seguro de que desea eliminar este registro? (S/N)");
			String respuesta = teclado.nextLine();

			if (respuesta.equalsIgnoreCase("S")) {
				duenoDao.borrar(usuarioAEliminar);
				System.out.println("Registro eliminado correctamente");
			}
		}
	}

	// ------------ TRATAR SUBMENU MASCOTA-----------------------------------
	private static void tratarMenuMascota(int opcionElegida) {

		switch (opcionElegida) {
		case 1:
			listarTodasLasMascotas();
			break;
		case 2:
			listarMascotasPorCiudad();
			break;
		case 3:
			listarMascotasDeUnDueno();
			break;
		case 4:
			anadirMascota();
			break;
		case 5:
			borrarMascotaPorChip();
			break;
		}
	}

	public static void listarTodasLasMascotas() {
		List<Mascota> listaMascota = mascotaDao.listarTodasLasMascotas();

		if (listaMascota == null) {
			System.out.println("No se encuentran mascotas registrados");
		} else {
			for (Mascota todasLasMascotas : listaMascota) {
				System.out.println(todasLasMascotas);
			}
		}
	}

	public static void listarMascotasPorCiudad() {
		String ciudad;
		boolean encontrado = false;

		List<Mascota> listaMascota = mascotaDao.listarTodasLasMascotas();

		System.out.println("Busqueda de una mascota por ciudad");
		System.out.println("Introduzca la ciudad que deseas buscar: ");
		ciudad = teclado.nextLine();

		for (Mascota mascota : listaMascota) {
			if (mascota.getDueno().getCiudad().equalsIgnoreCase(ciudad)) {
				System.out.println(mascota.getNombre());
				encontrado = true;
			}
		}

		if (encontrado == false) {
			System.out.println("No se encuentran mascotas registradas en la ciudad " + ciudad);
		}
	}

	public static void listarMascotasDeUnDueno() {
		System.out.println("Introduzca el dni del dueño: ");
		String dni = teclado.nextLine();

		// Compruebo que el dueño existe
		Dueno duenoMascota = duenoDao.buscarPorDni(dni);
		// Compruebo que el dueño de la mascota es el mismo que el que pido
		List<Mascota> mascotasConDueno = mascotaDao.mascotaPorDueno(dni);

		if (duenoMascota == null) {
			System.out.println("No existe ningun registro con ese dni");
		} else {
			if (mascotasConDueno != null) {
				for (Mascota mascota : mascotasConDueno) {
					System.out.println(mascota.toString());
				}
			} else {
				System.out.println("Dueño con dni " + duenoMascota.getDni() + " no tiene mascota asociada.");
			}
		}
	}

	public static void anadirMascota() {
		String nombre, raza, sexo, dniDueno;
		int chip;
		Mascota mascotaEncontrada;
		Dueno duenoMascota;

		System.out.println("Añadir nueva mascota");
		System.out.println("Introduzca el chip de la mascota: ");
		chip = Integer.parseInt(teclado.nextLine());

		mascotaEncontrada = mascotaDao.buscarPorChip(chip);

		if (mascotaEncontrada != null) {
			System.out.println("Error, este chip ya existe " + chip);
		} else {
			System.out.println("Introduzca el nombre de la mascota: ");
			nombre = teclado.nextLine();
			System.out.println("Introduzca la raza: ");
			raza = teclado.nextLine();
			System.out.println("Introduzca el sexo(M/H): ");
			sexo = teclado.nextLine();

			// Datos dueño
			System.out.println("Introduce el dni del dueño de la mascota: ");
			dniDueno = teclado.nextLine();

			duenoMascota = duenoDao.buscarPorDni(dniDueno);

			if (duenoMascota == null) {
				System.out.println("No existe ningun dueño registrado con ese dni " + dniDueno);
			} else {
				mascotaEncontrada = new Mascota(chip, nombre, raza, sexo, duenoMascota);
				mascotaDao.guardar(mascotaEncontrada);
				System.out.println("Mascota creada correctamente");
			}
		}
	}

	public static void borrarMascotaPorChip() {
		System.out.println("Introduce elchip de la mascota que deseas eliminar: ");
		int chip = Integer.parseInt(teclado.nextLine());

		Mascota mascotaAEliminar = mascotaDao.buscarPorChip(chip);

		if (mascotaAEliminar == null) {
			System.out.println("La mascota con el chip " + chip + " no existe");
		} else {
			System.out.println("¿Está usted seguro de que desea eliminar este registro? (S/N)");
			String respuesta = teclado.nextLine();

			if (respuesta.equalsIgnoreCase("S")) {
				mascotaDao.borrar(mascotaAEliminar);
				System.out.println("Registro eliminado correctamente");
			}
		}
	}

	// ------------ TRATAR SUBMENU CITAS-----------------------------------
	private static void tratarMenuCitas(int opcionElegida) {

		switch (opcionElegida) {
		case 1:
			listarTodasCitas();
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;
		}
	}

	public static void listarTodasCitas() {
		List<Cita> todasCitas = citaDao.listarTodasLasCitas();

		if (todasCitas == null) {
			System.out.println("Error, no se encuentran citas registradas");
		} else {
			for (Cita cita : todasCitas) {
				System.out.println(cita);
			}
		}
	}

	public static void anadirCita() {
		Cita nuevaCita;
		String nombreCita, motivo, srtFecha, dniVeterinario;
		LocalDate fecha = null;
		int chipMascota;
		Veterinario veterinarioEncontrado;
		Mascota mascotaEncontrada;

		try {
			System.out.println("*Añadir nueva Cita*");
			System.out.println("Introduzca el nombre de la cita: ");
			nombreCita = teclado.nextLine();
			
			System.out.println("Introduzca la fecha: ");
			srtFecha = teclado.nextLine();
			
			fecha = LocalDate.parse(srtFecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		} catch (DateTimeParseException e) {
			System.out.println("La fecha introducida no es correcta, debe contener el formato(dd-MM-yyyy)");
		}
		System.out.println("Introduzca el motivo de la cita: ");
		motivo = teclado.nextLine();
		// Datos Mascota
		System.out.println("Introduzca el chip de la mascota: ");
		chipMascota = Integer.parseInt(teclado.nextLine());
		// Datos Veterinario
		System.out.println("Introduzca el dni del veterinario que va a tratar a la mascota: ");
		dniVeterinario = teclado.nextLine();

		veterinarioEncontrado = veteDao.buscarPorDni(dniVeterinario);
		mascotaEncontrada = mascotaDao.buscarPorChip(chipMascota);

		if (veterinarioEncontrado == null) {
			System.out.println("No existe ningun veterinario registrado con ese dni " + dniVeterinario);
		} else {
			if (mascotaEncontrada == null) {
				System.out.println("Cita no creada, no existe ninguna mascota registrada con ese chip " + chipMascota);
			} else {
				//nuevaCita = new Cita(fecha, motivo, mascotaEncontrada, veterinarioEncontrado);
				//citaDao.guardar(nuevaCita);
				System.out.println("Nuevo registro insertado correctamente");
			}
		}
	}

	public static void consultarCitasMascotas() {
		Mascota mascotaEncontrada;
		List<Cita> mascotasConCita;

		System.out.println("Busqueda cita Mascota");
		System.out.println("Introduce el chip de la mascota que deseas buscar: ");
		int chipMascota = Integer.parseInt(teclado.nextLine());

		mascotaEncontrada = mascotaDao.buscarPorChip(chipMascota);
		mascotasConCita = citaDao.listarCitasMascota(chipMascota);

		if (mascotaEncontrada == null) {
			System.out.println("No existe ninguna mascota con ese chip " + chipMascota);
		} else {
			if (mascotasConCita == null) {
				System.out
						.println("La mascota con el chip " + chipMascota + " no tiene citas asociadas en este momento");
			} else {
				for (Cita cita : mascotasConCita) {
					System.out.println(cita.mostrarCitasConMascotas());
				}
			}
		}
	}

	public static void consultarCitasVeterinario() {
		Veterinario veterinarioEncontrado;
		List<Cita> VeterinariosConCita;

		System.out.println("Búsqueda de la cita de un veterinario");
		System.out.println("Introduce el dni del veterinario que deseas buscar: ");
		String dniVeterinario = teclado.nextLine();

		veterinarioEncontrado = veteDao.buscarPorDni(dniVeterinario);
		VeterinariosConCita = citaDao.listarCitasVeterinario(dniVeterinario);

		if (veterinarioEncontrado == null) {
			System.out.println("No existe ningun veterinario registrado con ese dni " + dniVeterinario);
			System.out.println("");
		} else {
			if (VeterinariosConCita == null) {
				System.out.println(
						"El veterinario con el dni" + dniVeterinario + " no tiene citas asociadas en este momento.");
			} else {
				for (Cita cita : VeterinariosConCita) {
					System.out.println(cita.mostrarCitasConVeterinario());
				}
			}
		}
	}

	public static void consultarCitasDueno() {
		Dueno duenoEncontrado;
		List<Cita> duenosConCita;

		System.out.println("Introduce el dni el dueño que deseas buscar: ");
		String dniDueno = teclado.nextLine();

		duenoEncontrado = duenoDao.buscarPorDni(dniDueno);
		duenosConCita = citaDao.listarCitasDueno(dniDueno);

		if (duenoEncontrado == null) {
			System.out.println("Error, no existe ningun registro con ese dni " + dniDueno);
		} else {
			if (duenosConCita == null) {
				System.out.println("El dueño con el dni " + dniDueno + " no tiene citas asociadas en este momento");
			} else {
				for (Cita cita : duenosConCita) {
					System.out.println(cita.mostrarCitasDueno());
				}
			}
		}
	}

	public static void consultarCitasPorFecha() {
		Date fecha = null;
		List<Cita> listaFechas;

		try {
			System.out.println("Introduce la fecha de la cita que deseas consultar: ");
			String fechaAConsultar = teclado.nextLine();

			// Formateo el tipo de fecha
			SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yyyy");
			fecha = formater.parse(fechaAConsultar);

			listaFechas = citaDao.listarCitasDia(fecha);

			if (listaFechas == null) {
				System.out.println("Error, no existe un registro con la fecha " + fechaAConsultar + " actualmente");
			} else {
				for (Cita cita : listaFechas) {
					System.out.println(cita.mostrarFechaCita());
				}
			}
		} catch (ParseException e) {
			System.out.println("La fecha introducida no es correcta, debe contener el formato(dd-MM-yyyy)");
		}
	}

	public static void actualizarCita() {
		String nombreCita;
		
		System.out.println("Actualización de una cita");
		System.out.println("Introduce el nombre de la cita que deseas buscar: ");
		nombreCita = teclado.nextLine();
		
		
		
	}
}