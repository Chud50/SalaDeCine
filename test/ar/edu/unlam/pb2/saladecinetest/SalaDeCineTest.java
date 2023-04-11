package ar.edu.unlam.pb2.saladecinetest;

import java.util.Scanner;

import ar.edu.unlam.pb2.saladecine.Cliente;
import ar.edu.unlam.pb2.saladecine.SalaDeCine;

public class SalaDeCineTest {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Integer cantidadClientes = 150, opcion = 0;
		SalaDeCine sala1 = new SalaDeCine("Shrek", cantidadClientes);

		System.out.println("\t---BIENVENIDO---");

		do {
			menu();
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				ingresarCliente(teclado, sala1);
				break;
			case 2:
				elegirAsiento(teclado, sala1);
				break;
			case 3:
				listarClientes(sala1);
				break;
			case 4:
				mostrarAsientos(sala1);
				break;
			case 5:
				hayEspacioParaCantidadXDePersonas(teclado, sala1);
				break;
			case 0:
				System.out.println("\tSALIO DEL PROGRAMA...");
				break;
			default:
				System.out.println("\tOPCION INVALIDA...");
				break;
			}
		} while (opcion != 0);

		teclado.close();
	}

	public static void menu() {
		System.out.println("\t---MENU PRINCIPAL---");
		System.out.println("1) Ingresar cliente	" + "\n2) Elegir asiento" + "\n3) Listar clientes"
				+ "\n4) Mostrar asientos" + "\n5) Agregar X Cantidad de personas " + "\n0) Salir");
		System.out.println("----------------------------");
		System.out.println("Elija una opcion: ");
	}

	public static void ingresarCliente(Scanner teclado, SalaDeCine salaIngresada) {
		String nombreIngresado = "", apellidoIngresado = "";
		Integer edadIngresada = 0;

		System.out.println("Ingrese el nombre del cliente: ");
		nombreIngresado = teclado.next();
		System.out.println("Ingrese el apellido del cliente: ");
		apellidoIngresado = teclado.next();
		System.out.println("Ingrese la edad del cliente: ");
		edadIngresada = teclado.nextInt();

		Cliente clienteIngresado = new Cliente(nombreIngresado, apellidoIngresado, edadIngresada);
		if (salaIngresada.agregarCliente(clienteIngresado)) {
			System.out.println("\tCliente ingresado...");
			System.out.println(clienteIngresado.toString());
		} else {
			System.out.println("\tEL CLIENTE NO FUE INGRESADO...");
		}
	}

	public static void elegirAsiento(Scanner teclado, SalaDeCine salaIngresada) {
		Integer filaIngresada = 0, columnaIngresada = 0;
		String nombreBuscado = "", apellidoBuscado = "";
		Cliente clienteEncontrado = null;

		System.out.println("Ingrese el nombre del cliente: ");
		nombreBuscado = teclado.next();
		System.out.println("Ingrese el apellido del cliente: ");
		apellidoBuscado = teclado.next();
		clienteEncontrado = salaIngresada.buscarCliente(nombreBuscado, apellidoBuscado);

		if (clienteEncontrado != null) {
			System.out.println(salaIngresada.mostrarAsientos());
			System.out.println("Elija una fila: ");
			filaIngresada = teclado.nextInt();
			System.out.println("Elija el asiento: ");
			columnaIngresada = teclado.nextInt();
			if (salaIngresada.asignarAsiento(clienteEncontrado, filaIngresada, columnaIngresada)) {
				System.out.println("\tASIENTO ASIGNADO...");
				System.out.println(salaIngresada.mostrarAsientos());
			} else {
				System.out.println("\tNO SE PUDO ASIGNAR EL ASIENTO...");
			}
		} else {
			System.out.println("\tEL CLIENTE NO FUE ENCONTRADO...");
		}
	}

	public static void hayEspacioParaCantidadXDePersonas(Scanner teclado, SalaDeCine salaIngresada) {
		Integer cantidadDePersonas = 0;
		char siOno = 0;
		System.out.println("Ingrese la cantidad de personas deseadas: ");
		cantidadDePersonas = teclado.nextInt();

		do {
			mostrarAsientos(salaIngresada);
			System.out.println("Hay disponibilidad en la fila elegida? (SI/NO)");
			siOno = teclado.next().charAt(0);
			if (siOno == 's' || siOno == 'S') {
				elegirAsiento(teclado, salaIngresada);

				if (salaIngresada.hayEspacioPara(cantidadDePersonas)) {
					System.out.println("Personas ingresadas con exito");
				} else {
					System.out.println("Fila ocupada, por favor ingrese una fila disponible");
				}
			} else {
				System.out.println("Fila ocupada, por favor ingrese una fila disponible");
				mostrarAsientos(salaIngresada);
				elegirAsiento(teclado, salaIngresada);
				System.out.println("Personas ingresadas con exito");
			}
		} while (siOno == 's' || siOno == 'S');

	}

	public static void listarClientes(SalaDeCine salaIngresada) {
		salaIngresada.ordenarClientesPorEdad();
		System.out.println(salaIngresada.mostrarClientes());
	}

	public static void mostrarAsientos(SalaDeCine salaIngresada) {
		System.out.println(salaIngresada.mostrarAsientos());
	}

}
