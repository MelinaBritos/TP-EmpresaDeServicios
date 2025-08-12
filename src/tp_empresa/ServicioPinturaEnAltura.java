package tp_empresa;

public class ServicioPinturaEnAltura extends ServicioPintura {
	private int pisos;
	private double seguro;
	private double alqAndamios;

	public ServicioPinturaEnAltura(int metrosCuadrados, double precioPorMetroCuadrado,
			Especialista especialistaACargo, String direccion, Cliente cliente, int pisos, double seguro, double alqAndamios) {
		super("PinturaEnAltura", metrosCuadrados, precioPorMetroCuadrado, especialistaACargo, direccion, cliente);
		setPisos(pisos);
		setSeguro(seguro);
		setAlqAndamios(alqAndamios);
		costoTotalServicio = calcularCostoServicio(); 
		}
	
	private void setPisos(int pisos) {
		if(pisos < 0) {
			throw new RuntimeException("El numero de pisos no es valido");
		}
		this.pisos = pisos;
	}
	
	private void setSeguro(double seguro) {
		if(seguro < 0) {
			throw new RuntimeException("El precio del seguro no es valido");
		}
		this.seguro = seguro;
	}
	
	private void setAlqAndamios(double alqAndamios) {
		if(alqAndamios < 0) {
			throw new RuntimeException("El precio no es valido");
		}
		this.alqAndamios = alqAndamios;
	}


	@Override
	double calcularCostoServicio() {
		return metrosCuadrados * precioPorMetroCuadrado + seguro + calcularCostoalquilerAndamio(alqAndamios);
	}

	private double calcularCostoalquilerAndamio(double alqAndamios) {
			if(pisos > 5) {
				return alqAndamios += alqAndamios*20/100;
		}
		return alqAndamios;
	}
}
