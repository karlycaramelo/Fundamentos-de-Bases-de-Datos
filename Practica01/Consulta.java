public class Consulta {


/*Clase consulta para hacer la llamada a las consultas aunque suene redundante */
	public static void main(String[] args) {

		Cliente nuevo = new Cliente("P1.csv", 101,7);
		//nuevo.printData();
		int query1rest = nuevo.query1();
		System.out.println("Hay "+ query1rest +" casas que miden menos de 200 metros y estan entre 50000 y 500000");

		System.out.println("Las personas con mas de una propiedad son:");
		nuevo.repetidos();
		
		System.out.println("Las 5 Propiedades mas caras son:");
		nuevo.las5MasCaras();

		System.out.println("Las 10 propiedades más baratas son:");
		nuevo.las10MasBaratas();

		System.out.println("5 propiedades con mejor Margen de Ganancia:");
		nuevo.margenDeGanancia();
		
	}
}