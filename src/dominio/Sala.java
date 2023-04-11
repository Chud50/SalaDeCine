package dominio;

public class Sala {
	
	private final int CANTIDAD_DE_FILAS = 32;
	private final int CANTIDAD_DE_ASIENTOS_POR_FILA = 6;
	private final int CANTIDAD_DE_BUTACAS = CANTIDAD_DE_FILAS * CANTIDAD_DE_ASIENTOS_POR_FILA;
	
	private String pelicula = "";
	private Cliente clientes[];
	private Cliente [][] asientos;
	private int cantidadDeClientesActuales = 0;
	
	public Sala(String pelicula, int cantidadDeClientes) {
		this.pelicula = pelicula;
		clientes = new Cliente[cantidadDeClientes];
		asientos = new Cliente[CANTIDAD_DE_FILAS][CANTIDAD_DE_ASIENTOS_POR_FILA];
		cantidadDeClientesActuales++;
	}

	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public Cliente[] getClientes() {
		return clientes;
	}

	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes;
	}

	public Cliente[][] getAsientos() {
		return asientos;
	}

	public void setAsientos(Cliente[][] asientos) {
		this.asientos = asientos;
	}

	public int getCantidadDeClientesActuales() {
		return cantidadDeClientesActuales;
	}

	public void setCantidadDeClientesActuales(int cantidadDeClientesActuales) {
		this.cantidadDeClientesActuales = cantidadDeClientesActuales;
	}

	public int getCANTIDAD_DE_FILAS() {
		return CANTIDAD_DE_FILAS;
	}

	public int getCANTIDAD_DE_ASIENTOS_POR_FILA() {
		return CANTIDAD_DE_ASIENTOS_POR_FILA;
	}

	public int getCANTIDAD_DE_BUTACAS() {
		return CANTIDAD_DE_BUTACAS;
	}
	
	public boolean agregarCliente(Cliente nuevo) {
		boolean agregado = false;
		
		for(int i = 0; i < clientes.length; i++) {
			if(clientes[i] == null) {
				clientes[i] = nuevo;
				agregado = true;
				break;
			}
		}
		return agregado;
	}
	
	public boolean verificarDisponibilidadDelAsiento(int fila, int columna) {
		boolean estaDisponible = false;
		
		if(asientos[fila][columna] == null) {
			estaDisponible = true;
		}
		return estaDisponible;
	}
	
	public Cliente buscarCliente(String nombre, String apellido) {
		Cliente clienteEncontrado = null;
		
		for(int i = 0; i < clientes.length; i++) {
			if(clientes[i] != null) {
				if(clientes[i].getNombre().equalsIgnoreCase(nombre) && clientes[i].getApellido().equalsIgnoreCase(apellido)) {
					clienteEncontrado = clientes[i];
					break;
				}
			}
		}
		return clienteEncontrado;
	}
	
	public boolean asignarAsiento(Cliente clienteIngresado, int fila,int columna) {
		boolean asignado = false;
		
		if(verificarDisponibilidadDelAsiento(fila, columna)) {
			if(fila <= asientos.length && columna <= asientos.length) {
				asientos[fila][columna] = clienteIngresado;
				asignado = true;
			}
		}
		return asignado;
	}
	
	public void ordenarClientesPorEdad() {
		Cliente auxiliar = null;
		
		for(int i = 0; i < clientes.length; i++) {
			for(int j = 0; j < clientes.length -1; j++) {
				if(clientes[j] != null && clientes[j+1] != null) {
					if(clientes[j].getEdad() > clientes[j+1].getEdad()) {
						auxiliar = clientes[j+1];
						clientes[j+1] = clientes[j];
						clientes[j] = auxiliar;
					}
				}
			}
		}
	}
	
	public String mostrarClientes() {
		String mensaje = "";
		
		for(int i = 0; i < clientes.length; i++) {
			if(clientes[i] != null) {
				mensaje += i + ") " + clientes[i].getNombre() + "  " + clientes[i].getApellido() + " . Edad: " + clientes[i].getEdad() + "\n";
			}
		}
		return mensaje;
	}
	
	public String mostrarAsientos() {
		String mensaje = "";
		
		for(int fila = 0; fila < CANTIDAD_DE_FILAS; fila++) {
			mensaje += "\n------------------------\n";
			for(int columna = 0; columna < asientos[fila].length; columna++) {
				if(asientos[fila][columna] != null) {
					mensaje += " O |";
				}
				else {
					mensaje += " L |";
				}
			}
		}
		
		return mensaje;
	}
}
