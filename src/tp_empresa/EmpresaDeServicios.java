package tp_empresa;

import java.util.HashMap;
import java.util.HashSet;


public class EmpresaDeServicios {
	private String nombre;									// nombre de la empresa
	private HashSet <String> tiposServicios;				// tipos de servicios disponibles de la empresa
	private HashMap <Integer, Servicio> servicios;			// llave= codigoUnico, valor= servicio
	private HashMap <Integer, Especialista> especialistas;	// llave= nroEspecialista, valor= especialista
	private HashMap <Integer, Cliente> clientes;			// llave= dni, valor= cliente
	private HashMap <String, Integer> facturaTotalPorTipo;  // llave= tipoServicio, valor= su factura total
	private HashMap <String, Integer> cantVecesSolicitado;  // llave= tipoServicio, valor= cant veces solicitado
	private HashSet <Integer> serviciosFinalizados;		    // conjunto con los codigos unicos de los servicios finalizados
	private double facturacionTotal;					    // facturacion total de la empresa
	
	public EmpresaDeServicios (String nombre) {
		setNombre(nombre);
		this.tiposServicios= new HashSet<String>();
		tiposServicios.add("Electricidad");
		tiposServicios.add("Pintura");
		tiposServicios.add("PinturaEnAltura");
		tiposServicios.add("GasistaInstalacion");
		tiposServicios.add("GasistaRevision");	
		this.servicios= new HashMap<Integer, Servicio>();
		this.especialistas= new HashMap<Integer, Especialista>();
		this.clientes= new HashMap<Integer, Cliente>();
		this.facturaTotalPorTipo= new HashMap<String, Integer>();
		this.cantVecesSolicitado= new HashMap<String, Integer>();
		this.serviciosFinalizados= new HashSet<Integer>();
		this.facturacionTotal= 0;
		
		facturaTotalPorTipo.put("Electricidad", 0);
		facturaTotalPorTipo.put("Pintura", 0);
		facturaTotalPorTipo.put("PinturaEnAltura", 0);
		facturaTotalPorTipo.put("GasistaInstalacion", 0);
		facturaTotalPorTipo.put("GasistaRevision", 0);
		
		cantVecesSolicitado.put("Electricidad", 0);
		cantVecesSolicitado.put("Pintura", 0);
		cantVecesSolicitado.put("PinturaEnAltura", 0);
		cantVecesSolicitado.put("GasistaInstalacion", 0);
		cantVecesSolicitado.put("GasistaRevision", 0);
	}
	
	public void registrarEspecialista(int nroEspecialista, String nombre,
			String telefono, String especialidad) {
		if(especialistas.containsKey(nroEspecialista)) {
			throw new RuntimeException("El especialista ya esta registrado");
		}
		if(!tiposServicios.contains(especialidad)){
			throw new RuntimeException("la especialidad no existe");
		}
		
		else {
			Especialista especialistaNuevo = new Especialista(nroEspecialista, nombre, telefono, especialidad);
			especialistas.put(nroEspecialista, especialistaNuevo);
		}		
			
	}

	public void registrarCliente(int dni, String nombre, String telefono) {
		if(clientes.containsKey(dni)) {
			throw new RuntimeException("El cliente ya esta registrado");
		}
		
		else {
			Cliente clienteNuevo = new Cliente(dni, nombre, telefono);
			clientes.put(dni, clienteNuevo);
		}
		
	}

	public int solicitarServicioPintura(int dni, int nroEspecialista,
			String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado) {
		verificarDatos(dni, nroEspecialista);
		Servicio nuevoServicio = new ServicioPintura("Pintura", metrosCuadrados, precioPorMetroCuadrado, 
				buscarEspecialista(nroEspecialista),direccion, buscarCliente(dni));
		servicios.put(generarCodigoUnico(dni, nroEspecialista), nuevoServicio);
		
		return generarCodigoUnico(dni, nroEspecialista);
		
	}
	
	public int solicitarServicioPintura(int dni, int nroEspecialista,String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado, int piso,double seguro, double alqAndamios) {	
		verificarDatos(dni, nroEspecialista);
		Servicio nuevoServicio = new ServicioPinturaEnAltura
				(metrosCuadrados, precioPorMetroCuadrado,buscarEspecialista(nroEspecialista),direccion, buscarCliente(dni),piso, seguro, alqAndamios);	
		servicios.put(generarCodigoUnico(dni, nroEspecialista), nuevoServicio);
		
		return generarCodigoUnico(dni, nroEspecialista); 
		
	}
	
	public int solicitarServicioElectricidad(int dni, int nroEspecialista,
			String direccion, double precioPorHora,
			int horasTrabajadas) {
		verificarDatos(dni, nroEspecialista);
		Servicio nuevoServicio = new ServicioElectricidad(horasTrabajadas, precioPorHora,
				buscarEspecialista(nroEspecialista), direccion, buscarCliente(dni));
		servicios.put(generarCodigoUnico(dni, nroEspecialista), nuevoServicio);
		
		return generarCodigoUnico(dni, nroEspecialista); 
		
	}

	public int solicitarServicioGasistaInstalacion(int dni, int nroEspecialista,
			String direccion, int cantArtefactos,
			double precioPorArtefacto) {
		verificarDatos(dni, nroEspecialista);
		Servicio nuevoServicio = new ServicioGasistaInstalacion(cantArtefactos, precioPorArtefacto,
				buscarEspecialista(nroEspecialista),direccion, buscarCliente(dni));		
		servicios.put(generarCodigoUnico(dni, nroEspecialista), nuevoServicio);
		
		return generarCodigoUnico(dni, nroEspecialista);
	}
	
	public int solicitarServicioGasistaRevision(int dni, int nroEspecialista,
			String direccion, int cantArtefactos,
			double precioPorArtefacto) {
		verificarDatos(dni, nroEspecialista);
		Servicio nuevaEntrada = new ServicioGasistaRevision(cantArtefactos, precioPorArtefacto, 
				buscarEspecialista(nroEspecialista),direccion, buscarCliente(dni));	
		servicios.put(generarCodigoUnico(dni, nroEspecialista), nuevaEntrada);
		
		return generarCodigoUnico(dni, nroEspecialista); 
	}

	public double finalizarServicio(int codServicio, double costoMateriales) {
		if (!servicios.containsKey(codServicio)) {
			throw new RuntimeException("El servicio no existe");
		}
		if (serviciosFinalizados.contains(codServicio)) {
			throw new RuntimeException("El servicio ya fue finalizado");
		}
		serviciosFinalizados.add(codServicio);									//agrega el codigo unico del servicio a servicios finalizados
		double costoTotal=buscarServicio(codServicio).calcularCostoServicio() + costoMateriales;
		facturacionTotal+=costoTotal;
		sumarFacturacionTotalPorTipo(buscarServicio(codServicio), costoTotal); // suma el costo total a la factura del tipoServicio correspondiente
		sumarCantVecesSolicitado(buscarServicio(codServicio));				   // suma 1 a cantidad de veces solicitado al tipo correspondiente
		actualizarHistorialEspecialista(buscarServicio(codServicio), codServicio); // agrega los datos solicitados al historial del especialista
		return costoTotal;
	}

	public void cambiarResponsable(int codServicio, int nroEspecialista) {
		if(!especialistaEstaRegistrado(nroEspecialista)) {
			throw new RuntimeException("El especialista no esta registrado");
		}
		if(!servicios.containsKey(codServicio)) {
			throw new RuntimeException("El servicio no esta registrado");
		}
		esDeEspecialidad(buscarServicio(codServicio).consultarTipoServicio(), nroEspecialista); //verifica que el especialista nuevo sea de la especialidad pedida
		
		buscarServicio(codServicio).cambiarEspecialista(buscarEspecialista(nroEspecialista)); // cambia especialista
		
		}
		
	public HashMap<String,Integer> cantidadDeServiciosRealizadosPorTipo(){
		
		return cantVecesSolicitado;
	}

	public double facturacionTotalPorTipo(String tipoServicio) {

				return facturaTotalPorTipo.get(tipoServicio);
	}

	public double facturacionTotal() {
		
		return facturacionTotal;
	}

	public String listadoServiciosAtendidosPorEspecialista(int nroEspecialista) {
		if(!especialistaEstaRegistrado(nroEspecialista)) {
			throw new RuntimeException("El especialista no esta registrado");
		}
		return buscarEspecialista(nroEspecialista).consultarServiciosAtendidos();
	}
	
	////////////////////////////////////////////////////////METODOS PRIVADOS
	
	private Cliente buscarCliente(int dni) {
		if (!clientes.containsKey(dni))
			throw new RuntimeException("El cliente no existe");
		return clientes.get(dni);
	}
	
	private Especialista buscarEspecialista(int numEspecialista) {
		if (!especialistas.containsKey(numEspecialista))
			throw new RuntimeException("El especialista no existe");
		return especialistas.get(numEspecialista);
	}


	private Servicio buscarServicio(int codigo) {
		if (!servicios.containsKey(codigo))
			throw new RuntimeException("El servicio no existe");
		return servicios.get(codigo);
	}
	
	//GENERA UN CODIGO HECHO CON LOS 3 ULTIMOS NUMEROS DEL DNI DEL CLIENTE Y EL NUMERO DEL ESPECIALISTA
	public int generarCodigoUnico(int dni, int nroEspecialista) {	
		String codigoUnicoString = "";
		Integer dniInt = dni;
		Integer nroEsp = nroEspecialista;		
		String dniString = dniInt.toString();
		String nroEspString = nroEsp.toString();
		
		for(int i = 5; i < dniString.length(); i++ ) {
			codigoUnicoString += dniString.charAt(i);
		}
		
		codigoUnicoString += nroEspString;
		int codigoUnicoInt = Integer.parseInt(codigoUnicoString);
		
		return codigoUnicoInt;
	}
	
	//LANZA EXCEPCION SI EL CLIENTE O ESPECIALISTAS NO ESTAN REGISTRADOS O SI EL SERVICIO YA ESTA REGISTRADO
	private void verificarDatos(int dni, int nroEspecialista) {
			if(!clienteEstaRegistrado(dni)) {
				throw new RuntimeException("El cliente no esta registrado");
			}
			if(!especialistaEstaRegistrado(nroEspecialista)) {
				throw new RuntimeException("El especialista no esta registrado");
			}
			if(servicios.containsKey(generarCodigoUnico(dni, nroEspecialista))) {
				throw new RuntimeException("El servicio ya esta registrado");
			}	
		}
		
	private boolean clienteEstaRegistrado(int dni) {
			if(clientes.containsKey(dni)) {
				return true;
			}
			return false;
		}
		
	private boolean especialistaEstaRegistrado(int nroEspecialista) {
			if(especialistas.containsKey(nroEspecialista)) {
				return true;
			}
			return false;
		}
	
	//VERIFICA QUE EL ESPECIALISTA SEA DE LA ESPECIALIDAD 
	private void esDeEspecialidad(String especialidad, int nroEspecialista) {
			if(!(especialidad==(buscarEspecialista(nroEspecialista).consultarEspecialidad()))){
				throw new RuntimeException("El especialista no trabaja de" + especialidad);
			}
		}
		
	private void sumarFacturacionTotalPorTipo(Servicio s, double d) {
			Integer i= (int) (facturaTotalPorTipo.get(s.consultarTipoServicio()) + d); 
			facturaTotalPorTipo.put(s.consultarTipoServicio(), i);
			
		}

	private void sumarCantVecesSolicitado(Servicio s) {
			Integer i= (int) (cantVecesSolicitado.get(s.consultarTipoServicio()) + 1);
			cantVecesSolicitado.put(s.consultarTipoServicio(), i);
		}
		
	private void actualizarHistorialEspecialista(Servicio s, Integer cod) {
		
		s.consultarEspecialista().actualizarServiciosAtendidos(cod, s.consultarTipoServicio(), s.consultarDireccion());
	}
	
	private void setNombre(String nombre) {
		if(nombre == null || nombre == "" ) {
			throw new RuntimeException("El nombre ingresado de la empresa no es valido");
		}
		this.nombre = nombre;
	}
		
	
	//
	public void consultarEspecialistasRegistrados() {
			for(Especialista e: especialistas.values()) {
				System.out.println(e.toString());
			}
		}	
		
	public void consultarClientesRegistrados() {
			for(Cliente c: clientes.values()) {
				System.out.println(c.toString());
			}
		}	
		
	public void consultarServicios() {
			for(Servicio serv: servicios.values()) {
				System.out.println(serv.toString());
			}
		}
		
		//
		
		
		
	@Override
	public String toString() {
			return "["+ nombre + " tiene " +servicios.size()+ " servicios activos /"+ " lleva facturado " + facturacionTotal + " pesos" + "]";
		}

}
