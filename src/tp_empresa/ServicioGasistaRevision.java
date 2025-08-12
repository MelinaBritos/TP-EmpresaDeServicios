package tp_empresa;

public class ServicioGasistaRevision extends ServicioGasista {
	
	
	public ServicioGasistaRevision(int cantArtefactos, double precioPorArtefacto, Especialista especialistaACargo, String direccion, Cliente cliente) {
		super(cantArtefactos, precioPorArtefacto, "GasistaRevision",  especialistaACargo, direccion, cliente);
		costoTotalServicio = calcularCostoServicio(); 
		
	}
	
	@Override
	double calcularCostoServicio() {
		return cantArtefactos*precioPorArtefacto - calcularDescuento(cantArtefactos, precioPorArtefacto) ;
	}
	
	private double calcularDescuento(int cantArtefactos, double precioPorArtefacto ){
		if(cantArtefactos > 5 && cantArtefactos <= 10) {
			return (cantArtefactos * precioPorArtefacto)*10/100;	
		}
		if(cantArtefactos > 10) {
			return (cantArtefactos * precioPorArtefacto)*15/100;
		}
		return 0;
	}	
	
}
