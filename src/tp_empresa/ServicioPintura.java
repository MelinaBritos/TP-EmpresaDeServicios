package tp_empresa;

public class ServicioPintura extends Servicio {
	protected int metrosCuadrados;
	protected double precioPorMetroCuadrado;
	
	public ServicioPintura(String tipoServicio, int metrosCuadrados, double precioPorMetroCuadrado, Especialista especialistaACargo, String direccion, Cliente cliente) {
		super(tipoServicio, especialistaACargo, direccion, cliente);
		setMetrosCuadrados(metrosCuadrados);
		setPrecioPorMetroCuadrado(precioPorMetroCuadrado);	
	}
	
	private void setPrecioPorMetroCuadrado(double precioPorMetroCuadrado) {
		if(precioPorMetroCuadrado <= 0) {
			throw new RuntimeException("El precio no es valido");
		}
		this.precioPorMetroCuadrado = precioPorMetroCuadrado;
	}
	
	private void setMetrosCuadrados(int metrosCuadrados) {
		if(metrosCuadrados <= 0) {
			throw new RuntimeException("Los metros no son validos");
		}
		this.metrosCuadrados = metrosCuadrados;
	}
	
	@Override
	double calcularCostoServicio() {
		return metrosCuadrados * precioPorMetroCuadrado;
	}
	
	
	
}
