package Interfaz;

import java.util.Scanner;

import dominio.Cliente;
import dominio.Sala;

public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int cantidadClientes = 150, opcion = 0;
		Sala sala1 = new Sala("Shrek", cantidadClientes);

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
				+ "\n4) Mostrar asientos" + "\n0) Salir");
		System.out.println("----------------------------");
		System.out.println("Elija una opcion: ");
	}

	public static void ingresarCliente(Scanner teclado, Sala salaIngresada) {
		String nombreIngresado = "", apellidoIngresado = "";
		int edadIngresada = 0;

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

	public static void elegirAsiento(Scanner teclado, Sala salaIngresada) {
		int filaIngresada = 0, columnaIngresada = 0;
		String nombreBuscado = "", apellidoBuscado = "";
		Cliente clienteEncontrado = null;
		
		System.out.println("Ingrese el nombre del cliente: ");
		nombreBuscado = teclado.next();
		System.out.println("Ingrese el apellido del cliente: ");
		apellidoBuscado = teclado.next();
		clienteEncontrado = salaIngresada.buscarCliente(nombreBuscado, apellidoBuscado);
		
		if(clienteEncontrado != null) {
			System.out.println(salaIngresada.mostrarAsientos());
			System.out.println("Elija una fila: ");
			filaIngresada = teclado.nextInt();
			System.out.println("Elija el asiento: ");
			columnaIngresada = teclado.nextInt();
			if(salaIngresada.asignarAsiento(clienteEncontrado,filaIngresada, columnaIngresada)) {
				System.out.println("\tASIENTO ASIGNADO...");
				System.out.println(salaIngresada.mostrarAsientos());
			}
			else {
				System.out.println("\tNO SE PUDO ASIGNAR EL ASIENTO...");
			}
		}
		else {
			System.out.println("\tEL CLIENTE NO FUE ENCONTRADO...");
		}
	}
	
	public static void listarClientes(Sala salaIngresada) {
		salaIngresada.ordenarClientesPorEdad();
		System.out.println(salaIngresada.mostrarClientes());
	}
	
	public static void mostrarAsientos(Sala salaIngresada) {
		System.out.println(salaIngresada.mostrarAsientos());
	}
}
