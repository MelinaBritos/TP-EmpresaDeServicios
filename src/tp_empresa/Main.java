package tp_empresa;

public class Main {
	
	public static void main(String[] args) {
		 // La empresa dispone de los siguientes tipos de Servicios:
		 // - Pintura
		 // - PinturaEnAltura
		 // - Electricidad
		 // - GasistaInstalacion
		 // - GasistaRevision
		 EmpresaDeServicios empresa = new EmpresaDeServicios("El Tano Construcciones");
		 empresa.registrarEspecialista(1001, "Electricista1", "1144556677", "Electricidad");
		 empresa.registrarEspecialista(20, "Gasista" , "1144556688", "GasistaInstalacion");
		 empresa.registrarEspecialista(33, "Pintor1" , "1144556699", "PinturaEnAltura");
		 empresa.registrarEspecialista(452, "Electricista2", "1144557700", "Electricidad");
		 empresa.registrarEspecialista(34, "Pintor2" , "1144557733", "PinturaEnAltura");

		 empresa.registrarCliente(30449448, "Cliente1", "1146651100");
		 empresa.registrarCliente(37223451, "Cliente2", "1146651111");

		 int cli1_elec1=empresa.solicitarServicioElectricidad(30449448, 1001, "calle falsa 123", 700, 20);
		 int cli1_pintor1=empresa.solicitarServicioPintura(30449448, 33, "calle falsa 123", 24, 4500,2,18000,5000);
		 int cli2_elec2=empresa.solicitarServicioElectricidad(37223451, 452, "otra Calle 321", 650, 2);
		 int cli2_gasista=empresa.solicitarServicioGasistaInstalacion(37223451, 20, "bulevar 333", 3, 3200);
		 int cli1_elec2=empresa.solicitarServicioElectricidad(30449448, 452, "calle falsa 139", 650, 10);

		 // El toString de empresa debe mostrar información útil, relevante y
		 // ordenada para que se pueda entender el estado actual del sistema.
		

		 empresa.finalizarServicio(cli1_elec1, 30000);
		 empresa.finalizarServicio(cli2_elec2, 10000);
		 empresa.finalizarServicio(cli1_elec2, 5000);
		 empresa.finalizarServicio(cli2_gasista, 1000);
		 empresa.cambiarResponsable(cli1_pintor1, 34);

		 System.out.println("Cantidad de servicios realizados por tipo: ");
		 System.out.println(empresa.cantidadDeServiciosRealizadosPorTipo());
		 System.out.println();
		 System.out.println("Facturacion total en 'Electricidad': " +
		 empresa.facturacionTotalPorTipo("Electricidad"));
		 System.out.println();
		 System.out.println("Facturacion total: " + empresa.facturacionTotal());
		 System.out.println();
		 System.out.println("Servicios realizados por 'Electricista2': ");
		 System.out.println(empresa.listadoServiciosAtendidosPorEspecialista(452));
		 System.out.println();

		 System.out.println(empresa + "\n");
		 
		 
		}

}
