package tp_empresa;

public class ServicioElectricidad extends Servicio {
	private int horasTrabajadas;
	private double precioPorHora;
	
	public ServicioElectricidad(int horasTrabajadas, double precioPorHora, Especialista especialistaACargo, String direccion, Cliente cliente) {
		super("Electricidad", especialistaACargo, direccion, cliente);
		setHorasTrabajadas(horasTrabajadas);
	    setPrecioPorHora(precioPorHora);
	    costoTotalServicio = calcularCostoServicio();
	}
	
	private void setHorasTrabajadas(int horasTrabajadas) {
		if(horasTrabajadas < 0) {
			throw new RuntimeException("Las horas trabajadas no son validas");
		}
		this.horasTrabajadas = horasTrabajadas;
	}

	private void setPrecioPorHora(double precioPorHora) {
		if(precioPorHora < 0) {
			throw new RuntimeException("El precio por hora no es valido");
		}
		this.precioPorHora = precioPorHora;
	}
	
	@Override
	double calcularCostoServicio() {
		return horasTrabajadas * precioPorHora;
	}

}
