package tp_empresa;

public class Cliente {
	private int dni;
	private String nombre;
	private String telefono;
	
	public Cliente(int dni, String nombre, String telefono) {
		setDni(dni);
		setNombre(nombre);
		setTelefono(telefono);
	}
	
	public String consultarNombre() {
		return nombre;
	}
	
	public int consultarDni() {
		return dni;
	}
	
	public String consularTelefono() {
		return telefono;
	}
	
	private void setDni(int dni) {
		if(dni < 0) {
			throw new RuntimeException("El numero de dni no es correcto");
		}
		this.dni = dni;
	}
	
	private void setNombre(String nombre) {
		if(nombre == null || nombre == "") {
			throw new RuntimeException("El nombre del cliente no es correcto");
		}
		this.nombre = nombre;
	}
	
	private void setTelefono(String telefono) {
		if(telefono == null || telefono == "") {
			throw new RuntimeException("El numero de telefono no es correcto");
		}
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return "["+ nombre +" "+  dni +" "+ telefono +"]";
	}
}
