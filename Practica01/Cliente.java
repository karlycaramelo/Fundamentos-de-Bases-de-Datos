import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;
import java.util.*;

public class Cliente {

	public String[][] data;

	/*creamos un arreglo para meter los datos del archivo csv*/

	public Cliente (String file_name, int num_renglones, int num_columnas){
		data = new String[num_renglones][num_columnas];

		data[0][0] = "Nombre";
		data[0][1] = "Telefono";
		data[0][2] = "Direccion";
		data[0][3] = "Correo";
		data[0][4] = "Metros Cuadrados Propiedad";
		data[0][5] = "Valor Propiedad";
		data[0][6] = "Valor de Venta";

		try {
			CsvReader clientes = new CsvReader(file_name);
			clientes.readHeaders();	

			int arrayindex = 1;
			while (clientes.readRecord())
			{
				data[arrayindex][0] = clientes.get("Nombre");
				data[arrayindex][1] = clientes.get("Telefono");
				data[arrayindex][2] = clientes.get("Direccion");
				data[arrayindex][3] = clientes.get("Correo");
				data[arrayindex][4] = clientes.get("Metros Cuadrados Propiedad");
				data[arrayindex][5] = clientes.get("Valor Propiedad");
				data[arrayindex][6] = clientes.get("Valor de Venta");
				arrayindex++;
			}
		
			clientes.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

/* Imprime todo el contenido del archivo csv*/

	public void printData(){
		for (int i = 0; i < data.length; i++){
			System.out.println(
				data[i][0]+"|"+
				data[i][1]+"|"+
				data[i][2]+"|"+
				data[i][3]+"|"+
				data[i][4]+"|"+
				data[i][5]+"|"+
				data[i][6]);
		}
	}


	public void printRenglon(int index){
		System.out.println(
			data[index][0]+"|"+
			data[index][1]+"|"+
			data[index][2]+"|"+
			data[index][3]+"|"+
			data[index][4]+"|"+
			data[index][5]+"|"+
			data[index][6]);
	}

	/**
	*Cuantas Propiedades son de menos de 200m² y valen entre $50,000 y $500,000
	*/
	public int query1(){
		int cumplen = 0;
		for (int i = 1; i < data.length; i++){
			int tamano = Integer.parseInt(data[i][4]);
			double valorDeVenta = Double.parseDouble(data[i][6]);
			if (tamano < 200 && valorDeVenta >= 50000 && valorDeVenta <= 500000 ){
				cumplen++;
			}
		}
		return cumplen;
	}

/* Este método es para saber si una misma persona tiene mas de una propiedad, busca en la lista los nombres repetidos*/
	public void repetidos(){
		List<String> nombres = new LinkedList<String>();
		for (int i = 2; i < data.length; i++){
			String nombreactual = data[i][0];
			for (int j = i+1; j < data.length; j++){
				String nombreotro = data[j][0];
				if( nombreactual.equals(nombreotro) ){
					if( !nombres.contains(nombreactual) ){
						nombres.add(nombreactual);
					}
					break;
				}
			}

		}
		for(String nombre: nombres){
			System.out.println(nombre);
		}
	}

/* Este método es para encontrar las 5 propiedades mas caras, convertimos las cadenas en Doubles para conocer su valor y poder
comparar valores, se mete todo a una lista y  eliminamos el elemento mas grande que encontramos y repetimos */
	public void las5MasCaras(){
		List<String[]> dataList = new ArrayList();

		for(int i = 1; i < data.length; i++){
			dataList.add(data[i]);
		}

		int j = 0;
		while(j < 5){
			String[] elmascaro = dataList.get(0);
			int indexelmascaro = 0;
			for (int i = 1; i < dataList.size(); i++){
				String[] ele = dataList.get(i);
				double valorDeVentaMasCara = Double.parseDouble(elmascaro[6]);
				double valorDeVentaActual = Double.parseDouble(ele[6]);
				if(valorDeVentaMasCara < valorDeVentaActual){
					elmascaro = ele;
					indexelmascaro = i;
				}
			}


			System.out.println(
				elmascaro[0]+"|"+
				elmascaro[1]+"|"+
				elmascaro[2]+"|"+
				elmascaro[3]+"|"+
				elmascaro[4]+"|"+
				elmascaro[5]+"|"+
				elmascaro[6]);

			dataList.remove(indexelmascaro);
			j++;
		}
	}
	/*Método para mostrar las 5 propiedades con mayor margen de ganancia se mete todo a una lista y posteriormente convertimos en doubles
	las cadenas como en los métodos anteriores, después comparamos valores y hacemos la resta de Valor de Venta - Valor de propiedad y
	luego se imprime.*/
	public void margenDeGanancia(){
	List<String[]> dataList = new ArrayList();

		for(int i = 1; i < data.length; i++){
			dataList.add(data[i]);
		}

		int j = 0;
		while(j < 5){
		String[] mejorValor = dataList.get(0);
		double valorMejor = Double.parseDouble(mejorValor[6]) - Double.parseDouble(mejorValor[5]);
		int indexmejorValor = 0;
		
		for (int i = 1; i < dataList.size(); i++){
			String[] ele = dataList.get(i);
			double valorVenta = Double.parseDouble(ele[6]) - Double.parseDouble(ele[5]);
	
			if(valorMejor < valorVenta){
				mejorValor = ele;
				indexmejorValor = i;
				valorMejor = valorVenta;
			}
		}
		System.out.println(
		mejorValor[0]+"|"+
		mejorValor[1]+"|"+
		mejorValor[2]+"|"+
		mejorValor[3]+"|"+
		mejorValor[4]+"|"+
		mejorValor[5]+"|"+
		mejorValor[6] + "\n Ganancia:"+valorMejor);


		dataList.remove(mejorValor);
		j++;
					
	}
}

/*Método análogo al de mostrar las 10 propiedades más caras, solo que al revés xD */
	
	public void las10MasBaratas(){
		List<String[]> dataList = new ArrayList();

		for(int i = 1; i < data.length; i++){
			dataList.add(data[i]);
		}

		int j = 0;
		while(j < 10){
			String[] elmasbarato = dataList.get(0);
			int indexelmasbarato = 0;
			for (int i = 1; i < dataList.size(); i++){
				String[] ele = dataList.get(i);
				double valorDeVentaMasBarato = Double.parseDouble(elmasbarato[6]);
				double valorDeVentaActual = Double.parseDouble(ele[6]);
				if(valorDeVentaMasBarato > valorDeVentaActual){
					elmasbarato = ele;
					indexelmasbarato = i;
				}
			}

	
			System.out.println(
				elmasbarato[0]+"|"+
				elmasbarato[1]+"|"+
				elmasbarato[2]+"|"+
				elmasbarato[3]+"|"+
				elmasbarato[4]+"|"+
				elmasbarato[5]+"|"+
				elmasbarato[6]);

			dataList.remove(indexelmasbarato);
			j++;
		}
	}


}