package tp_empresa;


public class Especialista {
	private int nroEspecialista;
	private String nombre;
	private String telefono;
	private String especialidad;
	private StringBuilder serviciosAtendidos;
	
	public Especialista(int nroEspecialista, String nombre, String telefono, String especialidad) {
		setNroEspecialista(nroEspecialista);
		setNombre(nombre);
		setTelefono(telefono);
		this.especialidad = especialidad;// El IREP se verifica cuando se registra en la clase EmpresaDeServicios
		this.serviciosAtendidos= new StringBuilder();
	}

	private void setNombre(String nombre) {

		if(nombre ==  null || nombre == "") {
			throw new RuntimeException("El nombre del especialista no es valido");
		}
		this.nombre = nombre;
	}
	
	private void setTelefono(String telefono) {
		if(telefono ==  null || telefono == "") {
			throw new RuntimeException("El numero de telefono no es valido");
		}
		this.telefono = telefono;
	}
		
	private void setNroEspecialista(int nroEspecialista) {
		if(nroEspecialista < 0) {
			throw new RuntimeException("El nroEspecialista no es valido");
		}
		this.nroEspecialista = nroEspecialista;
		
	}

	public int consultarNroEspecialista() {
		return nroEspecialista;
	}
	
	public String consultarNombre() {
		return nombre;
	}
	
	public String consultarTelefono() {
		return telefono;
	}
	
	public String consultarEspecialidad() {
		return especialidad;
	}

	public void actualizarServiciosAtendidos(Integer cod, String tipo, String direccion) {
		
		serviciosAtendidos.append(" +");
		serviciosAtendidos.append(" [ ");
		serviciosAtendidos.append(cod);
		serviciosAtendidos.append(" - ");
		serviciosAtendidos.append(tipo);
		serviciosAtendidos.append(" ] ");
		serviciosAtendidos.append(direccion + "\n");
		
	}
	
	public String consultarServiciosAtendidos() {
		String s= serviciosAtendidos.toString();
		return s;
	}
	
	@Override
	public String toString() {
		return "["+ nombre +" "+ nroEspecialista +" "+  telefono +" "+ especialidad +"]";
	}
}
