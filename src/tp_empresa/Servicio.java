package tp_empresa;

public abstract class Servicio {
	private String tipoServicio;
	private Especialista especialistaACargo;
	private String direccion;
	protected double costoTotalServicio;
	private Cliente cliente;

	
	public Servicio(String tipoServicio, Especialista especialistaACargo, String direccion, Cliente cliente) {
		setDireccion(direccion);
		this.tipoServicio = tipoServicio;
		this.cliente = cliente; 
		setEspecialistaACargo(especialistaACargo);
	}
	
	private void setEspecialistaACargo(Especialista especialistaACargo) {
		if(!especialistaACargo.consultarEspecialidad().equals(tipoServicio)) {
		throw new RuntimeException("El especialista no trabaja de " + tipoServicio);
		}
		this.especialistaACargo = especialistaACargo;
	}
		
	private void setDireccion(String direccion) {
		if(direccion == null || direccion == "") {
			throw new RuntimeException("La direccion no es valida");
		}
		this.direccion = direccion;
	}

	abstract double calcularCostoServicio();
	
	public String consultarTipoServicio() {
		return tipoServicio;
	}
	
	public Especialista consultarEspecialista() {
		return especialistaACargo;
	}
	
	public void cambiarEspecialista(Especialista e) {
		this.especialistaACargo=e;
	}
	
	public String consultarDireccion() {
		return direccion;
	}
	
	@Override
	public String toString() {
		return "["+ tipoServicio+" "+ especialistaACargo.consultarNombre()+" " + direccion +" "+ costoTotalServicio+ " "+cliente.consultarNombre()+"]";
	}
}
