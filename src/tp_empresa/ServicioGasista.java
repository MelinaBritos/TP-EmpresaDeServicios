package tp_empresa;

public abstract class ServicioGasista extends Servicio {
	protected int cantArtefactos;
	protected double precioPorArtefacto;
	
	public ServicioGasista(int cantArtefactos, double precioPorArtefacto, String tipoServicio, Especialista especialistaACargo, String direccion, Cliente cliente) {
		super(tipoServicio, especialistaACargo, direccion, cliente);
		setCantArtefactos(cantArtefactos);
		setPrecioPorArtefacto(precioPorArtefacto);
	}
	
	
	private void setCantArtefactos(int cantArtefactos) {
		if(cantArtefactos <= 0) {
			throw new RuntimeException("La cantidad de artefactos no es valida");
		}
		this.cantArtefactos = cantArtefactos;
	}

	private void setPrecioPorArtefacto(double precioPorArtefacto) {
		if(precioPorArtefacto <= 0) {
			throw new RuntimeException("El precio por artefacto no es valido");
		}
		this.precioPorArtefacto = precioPorArtefacto;	
	}

	@Override
	double calcularCostoServicio() {
		return cantArtefactos*precioPorArtefacto;
	}
}
