package tp_empresa;

public class ServicioGasistaInstalacion extends ServicioGasista {
	
	public ServicioGasistaInstalacion(int cantArtefactos, double precioPorArtefacto, 
			Especialista especialistaACargo, String direccion,Cliente cliente) {
		super(cantArtefactos, precioPorArtefacto, "GasistaInstalacion",  especialistaACargo, direccion, cliente);
		costoTotalServicio = calcularCostoServicio(); 
	}

	
	// NO PONGO CALCULAR COSTO SERVICIO PORQUE LO HEREDA DEL PADRE
}
