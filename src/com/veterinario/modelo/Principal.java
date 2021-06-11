package com.veterinario.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	/**
	 * Metodo que se utiliza para cerrar la sesión de la base de datos de Hibernate
	 */
	private static void cerrarSesion() {
		HibernateUtil.closeSessionFactory();
	}

	/**
	 * Metodo que se utiliza para configurar la sesion de la base de datos de
	 * Hibernate
	 */
	private static void configurarSesion() {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	// --------------------------------------------------------------------------
	/**
	 * Método menú principal de la aplicación donde se controla que solo se pueda
	 * introducir numeros
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

	/**
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

	/**
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

	/**
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

	/**
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
					System.out.println("4. Consultar citas de las mascotas de un dueño");// 3 tablas
					System.out.println("5. Consultar citas de las mascotas de un dueño por fecha ");// 3 tablas
					System.out.println("6. Consultar citas de un día");
					System.out.println("7. Insertar nueva cita");
					System.out.println("8. Modificar fecha de la cita");
					System.out.println("9. Volver al menú principal");
					System.out.println("=============================");
					System.out.println("Seleccione una opción: ");

					// Introduzco opción del menú
					opcionCita = Integer.parseInt(teclado.nextLine());

					encontradoNumero = true;
				} catch (NumberFormatException e) {
					System.out.println("Debes introducir un numero");
				}
			} while (encontradoNumero == false);

		} while (opcionCita < 1 || opcionCita > 9);

		return opcionCita;
	}

	/**
	 * Metodo que recoge la opcion del menu principal y dependiendo de la opcion
	 * introducida, aparece el submenú elegido
	 * 
	 * @param opcionElegida recoge el numero de la opcion del menu
	 */
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
			} while (opcion != 9);
			break;
		}

	}

	/**
	 * Metodo que recoge el submenu de veterinario. Se tratan todas las
	 * consultas,bajas,actualizaciones y insercion de datos
	 * 
	 * @param opcionElegida
	 */
	private static void tratarMenuVeterinario(int opcionElegida) {

		switch (opcionElegida) {
		case 1:
			try {
				listarTodosLosVeterinarios();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				listarVeterinarioPorDni();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				listarVeterinarioPorEspecialidad();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			try {
				anadirProfesionalVeterinario();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
	}

	/**
	 * Metodo que recoge una lista de todos los veterinarios registrados en la base
	 * de datos
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarTodosLosVeterinarios() throws GestionVeterinarioException {
		List<Veterinario> listaVeterinario = veteDao.listarTodosLosVeterinarios();

		if (listaVeterinario == null) {
			throw new GestionVeterinarioException("No se encuentran veterinarios registrados");
		} else {
			for (Veterinario todosVeterinarios : listaVeterinario) {
				System.out.println(todosVeterinarios);
			}
		}
	}

	/**
	 * Metodo que muestra la informacion de un veterinario por su dni
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarVeterinarioPorDni() throws GestionVeterinarioException {

		System.out.println("Introduce el dni que deseas buscar: ");
		String dniVeterinario = teclado.nextLine();

		Veterinario buscarDniVeterinario = veteDao.buscarPorDni(dniVeterinario);

		if (validarDni(dniVeterinario)) {
			if (buscarDniVeterinario == null) {
				throw new GestionVeterinarioException(
						"No se encuentran ningun veterinario registrado con ese dni" + dniVeterinario);
			} else {
				System.out.println(buscarDniVeterinario.toString());
			}
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}

	}

	/**
	 * Metodo que muestra la informacion de un veterinario por su especialidad
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarVeterinarioPorEspecialidad() throws GestionVeterinarioException {

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
				throw new GestionVeterinarioException(
						"Error, no existe ningun veterinario registrado con esa especialidad");
			} else {
				for (Veterinario veterinario : listaVeterinariosConEspecialidad) {
					System.out.println(veterinario.toString());
				}
			}
		} else {
			System.out.println("Elige una especialidad entre 0 y 3 " + "\n");
		}
	}

	/**
	 * Metodo que se utiliza para la inserción de datos de un veterinario
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void anadirProfesionalVeterinario() throws GestionVeterinarioException {
		String dni, nombre, apellidos;
		Veterinario nuevoVeterinario;

		System.out.println("Añadir nuevo profesional");
		System.out.println("Introduzca el dni del veterinario: ");
		dni = teclado.nextLine();

		nuevoVeterinario = veteDao.buscarPorDni(dni);
		if (validarDni(dni)) {
			if (nuevoVeterinario != null) {
				throw new GestionVeterinarioException("Error, este dni ya existe " + dni);
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
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}

	}

	/**
	 * Metodo que recoge el submenu de el dueño de la mascota. Se tratan todas las
	 * consultas,bajas,actualizaciones y insercion de datos
	 * 
	 * @param opcionElegida
	 */
	private static void tratarMenuDueno(int opcionElegida) {

		switch (opcionElegida) {
		case 1:
			try {
				listarTodosLosDuenos();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				listarPorNombre();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				anadirDueno();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			try {
				borrarDuenoPorDni();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
	}

	/**
	 * Metodo que recoge una lista de todos los dueños registrados en la base de
	 * datos
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarTodosLosDuenos() throws GestionVeterinarioException {
		List<Dueno> listaDueno = duenoDao.listarTodosLosDuenos();

		if (listaDueno == null) {
			throw new GestionVeterinarioException("No se encuentran dueños registrados");
		} else {
			for (Dueno dueno : listaDueno) {
				System.out.println(dueno);
			}
		}
	}

	/**
	 * Metodo que muestra una lista de los dueños por su nombre
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarPorNombre() throws GestionVeterinarioException {

		System.out.println("Introduce el nombre que deseas buscar: ");
		String nombre = teclado.nextLine();

		List<Dueno> buscarDuenoNombre = duenoDao.buscarPorNombre(nombre);

		if (buscarDuenoNombre == null) {
			throw new GestionVeterinarioException("No se encuentran dueños con ese nombre " + nombre);
		} else {
			for (Dueno dueno : buscarDuenoNombre) {
				System.out.println(dueno);
			}
		}
	}

	/**
	 * Metodo que recoge la insercion de datos de un nuevo dueño
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void anadirDueno() throws GestionVeterinarioException {
		String dni, nombre, apellidos, telefono, ciudad;
		Dueno duenoNuevo;

		System.out.println("Añadir nuevo dueño");
		System.out.println("Introduzca el dni del dueño de la mascota: ");
		dni = teclado.nextLine();

		duenoNuevo = duenoDao.buscarPorDni(dni);

		if (validarDni(dni)) {

			if (duenoNuevo != null) {
				throw new GestionVeterinarioException("Error, este dni ya existe " + dni);
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
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}
	}

	/**
	 * Metodo que borra a un dueño por su dni. Antes de eliminarlo muestra mensaje
	 * de confirmación. Si quieres borrar un dueño con mascotas asociadas aparece
	 * mensaje de error
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void borrarDuenoPorDni() throws GestionVeterinarioException {
		System.out.println("Introduce el dni del dueño que deseas eliminar: ");
		String dni = teclado.nextLine();

		Dueno usuarioAEliminar = duenoDao.buscarPorDni(dni);

		if (validarDni(dni)) {
			if (usuarioAEliminar == null) {
				throw new GestionVeterinarioException("El usuario con dni " + dni + " no existe");
			} else {
				// Si el usuario no tiene mascotas
				if (usuarioAEliminar.getListaMascota().isEmpty()) {
					System.out.println("¿Está usted seguro de que desea eliminar este registro? (S/N)");
					String respuesta = teclado.nextLine();

					if (respuesta.equalsIgnoreCase("S")) {
						duenoDao.borrar(usuarioAEliminar);
						System.out.println("Registro eliminado correctamente");
					}
				} else {
					throw new GestionVeterinarioException(
							"Error, no puedes eliminar este usuario " +dni+ " porque contiene mascotas asociadas ");
				}
			}
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}
	}

	/**
	 * Metodo que recoge el submenu de la mascota. Se tratan todas las
	 * consultas,bajas,actualizaciones y insercion de datos
	 * 
	 * @param opcionElegida
	 */
	private static void tratarMenuMascota(int opcionElegida) {

		switch (opcionElegida) {
		case 1:
			try {
				listarTodasLasMascotas();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				listarMascotasPorCiudad();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				listarMascotasDeUnDueno();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			try {
				anadirMascota();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 5:
			try {
				borrarMascotaPorChip();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
	}

	/**
	 * Metodo que recoge una lista de todas las mascotas registradas en la
	 * aplicacion
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarTodasLasMascotas() throws GestionVeterinarioException {
		List<Mascota> listaMascota = mascotaDao.listarTodasLasMascotas();

		if (listaMascota == null) {
			throw new GestionVeterinarioException("No se encuentran mascotas registrados");
		} else {
			for (Mascota todasLasMascotas : listaMascota) {
				System.out.println(todasLasMascotas);
			}
		}
	}

	/**
	 * Metodo que muestra las mascotas de una determinada ciudad
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarMascotasPorCiudad() throws GestionVeterinarioException {
		String ciudad;

		System.out.println("Busqueda de una mascota por ciudad");
		System.out.println("Introduzca la ciudad que deseas buscar: ");
		ciudad = teclado.nextLine();

		List<Mascota> listaMascota = mascotaDao.mascotaPorCiudad(ciudad);

		if (listaMascota == null) {
			throw new GestionVeterinarioException("No existe ninguna mascota registrada en ese ciudad " + ciudad);
		} else {
			System.out.println("Mascotas registradas en la ciudad " + ciudad + " : ");
			for (Mascota mascota : listaMascota) {
				System.out.println(mascota.getNombre());
			}
		}
	}

	/**
	 * Metodo que recoge una lista de las mascotas que tiene un determinado dueño.
	 * El dueño puede tener mas de 1 mascota
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarMascotasDeUnDueno() throws GestionVeterinarioException {
		System.out.println("Introduzca el dni del dueño: ");
		String dni = teclado.nextLine();

		// Compruebo que el dueño existe
		Dueno duenoMascota = duenoDao.buscarPorDni(dni);
		// Compruebo que el dueño de la mascota es el mismo que el que pido
		List<Mascota> mascotasConDueno = mascotaDao.mascotaPorDueno(dni);

		if (validarDni(dni)) {
			if (duenoMascota == null) {
				throw new GestionVeterinarioException("No existe ningun registro con ese dni");
			} else {
				if (mascotasConDueno != null) {
					for (Mascota mascota : mascotasConDueno) {
						System.out.println(mascota.toString());
					}
				} else {
					throw new GestionVeterinarioException(
							"Dueño con dni " + duenoMascota.getDni() + " no tiene mascota asociada.");
				}
			}
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}
	}

	/**
	 * Metodo que se utiliza para la insercion de datos de una nueva mascota
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void anadirMascota() throws GestionVeterinarioException {
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

			if (validarDni(dniDueno)) {

				if (duenoMascota == null) {
					throw new GestionVeterinarioException("No existe ningun dueño registrado con ese dni " + dniDueno);
				} else {
					mascotaEncontrada = new Mascota(chip, nombre, raza, sexo, duenoMascota);
					mascotaDao.guardar(mascotaEncontrada);
					System.out.println("Mascota creada correctamente");
				}
			} else {
				System.out
						.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
			}
		}
	}

	/**
	 * Metodo que elimina una mascota por su chip. Antes de eliminar la mascota
	 * muestra un mensaje de confirmacion
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void borrarMascotaPorChip() throws GestionVeterinarioException {
		System.out.println("Introduce elchip de la mascota que deseas eliminar: ");
		int chip = Integer.parseInt(teclado.nextLine());

		Mascota mascotaAEliminar = mascotaDao.buscarPorChip(chip);

		if (mascotaAEliminar == null) {
			throw new GestionVeterinarioException("La mascota con el chip " + chip + " no existe");
		} else {
			System.out.println("¿Está usted seguro de que desea eliminar este registro? (S/N)");
			String respuesta = teclado.nextLine();

			if (respuesta.equalsIgnoreCase("S")) {
				mascotaDao.borrar(mascotaAEliminar);
				System.out.println("Registro eliminado correctamente");
			}
		}
	}

	/**
	 * Metodo que recoge el submenu de citas. Se tratan todas las
	 * consultas,bajas,actualizaciones y insercion de datos
	 * 
	 * @param opcionElegida
	 */
	private static void tratarMenuCitas(int opcionElegida) {

		switch (opcionElegida) {
		case 1:
			try {
				listarTodasCitas();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				consultarCitasVeterinario();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;

		case 3:
			try {
				consultarCitasMascotas();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;

		case 4:
			try {
				consultarCitasDeLasMascotasDeUnDueno();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 5:
			try {
				consultarCitasDeLasMascotasDeUnDuenoPorFechaActual();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 6:
			try {
				consultarCitasPorFecha();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 7:
			try {
				anadirCita();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 8:
			try {
				actualizarCita();
			} catch (GestionVeterinarioException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
	}

	/**
	 * Metodo que muestra una lista de todas las citas registradas en la base de
	 * datos
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void listarTodasCitas() throws GestionVeterinarioException {
		List<Cita> todasCitas = citaDao.listarTodasLasCitas();

		if (todasCitas == null) {
			throw new GestionVeterinarioException("Error, no se encuentran citas registradas");
		} else {
			for (Cita cita : todasCitas) {
				System.out.println(cita);
			}
		}
	}

	/**
	 * Metodo que recoge la insercion de dato de una nueva cita Aparece una
	 * excepcion en el caso de no introducir una fecha correcta
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void anadirCita() throws GestionVeterinarioException {
		Cita nuevaCita;
		boolean correcta = false;
		String motivo, srtFecha, dniVeterinario;
		Date fecha = null;
		int chipMascota;

		Veterinario veterinarioEncontrado;
		Mascota mascotaEncontrada;

		System.out.println("*Añadir nueva Cita*");
		do {
			try {
				System.out.println("Introduzca la fecha: ");
				srtFecha = teclado.nextLine();

				// Formateo el tipo de fecha
				SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
				fecha = formater.parse(srtFecha);

				correcta = true;

			} catch (ParseException e) {
				System.out.println("La fecha introducida no es correcta, debe contener el formato(dd-MM-yyyy)");
			}

		} while (correcta == false);

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

		if (validarDni(dniVeterinario)) {

			if (veterinarioEncontrado == null) {
				throw new GestionVeterinarioException(
						"No existe ningun veterinario registrado con ese dni " + dniVeterinario);
			} else {
				if (mascotaEncontrada == null) {
					throw new GestionVeterinarioException(
							"Cita no creada, no existe ninguna mascota registrada con ese chip " + chipMascota);
				} else {
					nuevaCita = new Cita(fecha, motivo, mascotaEncontrada, veterinarioEncontrado);
					citaDao.guardar(nuevaCita);
					System.out.println("Nuevo registro insertado correctamente");
				}
			}
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}
	}

	/**
	 * Metodo que muestra las citas de una determinada mascota por su chip
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void consultarCitasMascotas() throws GestionVeterinarioException {
		Mascota mascotaEncontrada;
		List<Cita> mascotasConCita;

		System.out.println("Busqueda cita Mascota");
		System.out.println("Introduce el chip de la mascota que deseas buscar: ");
		int chipMascota = Integer.parseInt(teclado.nextLine());

		mascotaEncontrada = mascotaDao.buscarPorChip(chipMascota);
		mascotasConCita = citaDao.listarCitasMascota(chipMascota);

		if (mascotaEncontrada == null) {
			throw new GestionVeterinarioException("No existe ninguna mascota con ese chip " + chipMascota);
		} else {
			if (mascotasConCita == null) {
				throw new GestionVeterinarioException(
						"La mascota con el chip " + chipMascota + " no tiene citas asociadas en este momento");
			} else {
				for (Cita cita : mascotasConCita) {
					System.out.println(cita.mostrarCitasConMascotas());
				}
			}
		}
	}

	/**
	 * Metodo que muestra las citas que tiene un veterinario actualmente
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void consultarCitasVeterinario() throws GestionVeterinarioException {
		Veterinario veterinarioEncontrado;
		List<Cita> VeterinariosConCita;

		System.out.println("Búsqueda de la cita de un veterinario");
		System.out.println("Introduce el dni del veterinario que deseas buscar: ");
		String dniVeterinario = teclado.nextLine();

		veterinarioEncontrado = veteDao.buscarPorDni(dniVeterinario);
		VeterinariosConCita = citaDao.listarCitasVeterinario(dniVeterinario);

		if (validarDni(dniVeterinario)) {

			if (veterinarioEncontrado == null) {
				throw new GestionVeterinarioException(
						"No existe ningun veterinario registrado con ese dni " + dniVeterinario);

			} else {
				if (VeterinariosConCita == null) {
					throw new GestionVeterinarioException("El veterinario con el dni" + dniVeterinario
							+ " no tiene citas asociadas en este momento.");
				} else {
					for (Cita cita : VeterinariosConCita) {
						System.out.println(cita.mostrarCitasConVeterinario());
					}
				}
			}
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}
	}

	/**
	 * Metodo que muestra las citas de las mascotas que tiene un dueño
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void consultarCitasDeLasMascotasDeUnDueno() throws GestionVeterinarioException {
		Dueno duenoEncontrado;
		List<Cita> duenosConCita;

		System.out.println("Introduce el dni el dueño que deseas buscar: ");
		String dniDueno = teclado.nextLine();

		duenoEncontrado = duenoDao.buscarPorDni(dniDueno);
		duenosConCita = citaDao.listarCitasDueno(dniDueno);

		if (validarDni(dniDueno)) {

			if (duenoEncontrado == null) {
				throw new GestionVeterinarioException("Error, no existe ningun registro con ese dni " + dniDueno);
			} else {
				if (duenosConCita == null) {
					throw new GestionVeterinarioException(
							"El dueño con el dni " + dniDueno + " no tiene citas asociadas en este momento");
				} else {
					for (Cita cita : duenosConCita) {
						System.out.println(cita.mostrarCitasDueno());
					}
				}
			}
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}
	}

	/**
	 * Metodo que muestra las citas que aún no han pasado de las mascotas de un
	 * dueño
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void consultarCitasDeLasMascotasDeUnDuenoPorFechaActual() throws GestionVeterinarioException {
		Dueno duenoEncontrado;
		List<Cita> duenosConCita;

		System.out.println("Introduce el dni el dueño que deseas buscar: ");
		String dniDueno = teclado.nextLine();

		duenoEncontrado = duenoDao.buscarPorDni(dniDueno);
		duenosConCita = citaDao.listarCitasDuenoAgrupadasPorFechaDescendente(dniDueno);

		if (validarDni(dniDueno)) {

			if (duenoEncontrado == null) {
				throw new GestionVeterinarioException("Error, no existe ningun registro con ese dni " + dniDueno);
			} else {
				if (duenosConCita == null) {
					throw new GestionVeterinarioException(
							"El dueño con el dni " + dniDueno + " no tiene citas asociadas en este momento");
				} else {
					for (Cita cita : duenosConCita) {
						System.out.println(cita.mostrarCitasDueno());
					}
				}
			}
		} else {
			System.out.println("Error, dni no válido.Vuelve a introducir el dni con el siguiente formato(12345678A)");
		}
	}

	/**
	 * Metodo que muestra las citas de una determinada fecha. Aparece una excepcion
	 * en el caso de no introducir la fecha correcta
	 * 
	 * @throws GestionVeterinarioException
	 */
	public static void consultarCitasPorFecha() throws GestionVeterinarioException {
		Date fecha = null;
		List<Cita> listaFechas;

		try {
			System.out.println("Introduce la fecha de la cita que deseas consultar: ");
			String fechaAConsultar = teclado.nextLine();

			// Formateo el tipo de fecha
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			fecha = formater.parse(fechaAConsultar);

			listaFechas = citaDao.listarCitasDia(fecha);

			if (listaFechas == null) {
				throw new GestionVeterinarioException(
						"Error, no existe un registro con la fecha " + fechaAConsultar + " actualmente");
			} else {
				for (Cita cita : listaFechas) {
					System.out.println(cita.mostrarFechaCita());
				}
			}
		} catch (ParseException e) {
			System.out.println("La fecha introducida no es correcta, debe contener el formato(dd-MM-yyyy)");
		}
	}

	/**
	 * Metodo que se utiliza para actualizar una cita por su fecha. Aparece una
	 * excepcion en el caso de no introducir la fecha correcta
	 * 
	 * @throws GestionVeterinarioException
	 * 
	 */
	public static void actualizarCita() throws GestionVeterinarioException {
		Date fecha = null;
		boolean correcta = false;
		List<Cita> citaEncontrada;
		Mascota mascotaChip;
		Dueno duenoEncontrado;

		// Solicito id de cita que deseo actualizar
	
		System.out.println("Actualizacion de una cita");
		System.out.println("Introduce el dni de la persona que quiere cambiar la cita: ");
		String dni = teclado.nextLine();

		duenoEncontrado = duenoDao.buscarPorDni(dni);

		if (duenoEncontrado == null) {
			throw new GestionVeterinarioException("Error, no existe un dueño con ese dni " + dni);
		} else {
			System.out.println("Introduce el chip de la mascota: ");
			int chip = Integer.parseInt(teclado.nextLine());

			mascotaChip = mascotaDao.buscarPorChip(chip);
			if (mascotaChip == null) {
				throw new GestionVeterinarioException("No existe una mascota con ese chip " + chip);
			} else {
				citaEncontrada = citaDao.listarCitasMascota(chip);

				if (citaEncontrada == null) {
					throw new GestionVeterinarioException("No existen citas asociadas a esta mascota " + chip);
				} else {
					
					if (citaEncontrada.size() == 1) {
						do {
							try {
								System.out.println("Introduzca la fecha: ");
								String nuevaFecha = teclado.nextLine();

								// Formateo el tipo de fecha
								SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
								fecha = formater.parse(nuevaFecha);

								correcta = true;

								// Recojo la primera cita y la modifico
								citaEncontrada.get(0).setFecha(fecha);

								// Actualizo la cita
								citaDao.modificar(citaEncontrada.get(0));

								System.out.println("Cita actualizada correctamente.");
							} catch (ParseException e) {
								System.out.println(
										"La fecha introducida no es correcta, debe contener el formato(dd-MM-yyyy)");
							}

						} while (correcta == false);
					} else {
						// Muestro todas las que ha encontrado
						for (int i = 0; i < citaEncontrada.size(); i++) {
							System.out.println("Cita " + i + " :" + citaEncontrada.get(i));
						}

						int numeroCita = -1;

						do {
							try {
								System.out.println("Selecciona la cita que quieres modificar: ");
								numeroCita = Integer.parseInt(teclado.nextLine());

							} catch (NumberFormatException e) {
								System.out.println("Error, debes introducir un numero ");
							}
						} while (numeroCita < 0 || numeroCita >= citaEncontrada.size());

						do {
							try {
								System.out.println("Introduzca la fecha: ");
								String nuevaFecha = teclado.nextLine();

								// Formateo el tipo de fecha
								SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
								fecha = formater.parse(nuevaFecha);

								correcta = true;

								// Recojo la primera cita y la modifico
								citaEncontrada.get(numeroCita).setFecha(fecha);

								// Actualizo la cita
								citaDao.modificar(citaEncontrada.get(numeroCita));

								System.out.println("Cita actualizada correctamente.");
							} catch (ParseException e) {
								System.out.println(
										"La fecha introducida no es correcta, debe contener el formato(dd-MM-yyyy)");
							}

						} while (correcta == false);
					}
				}
			}
		}
	}

	/**
	 * Metodo para comprobar que el dni tenga una longitud de 9
	 */
	public static boolean validarDni(String dni) {
		return dni.matches("^[0-9]{7,8}[A-Z]$");
	}
}