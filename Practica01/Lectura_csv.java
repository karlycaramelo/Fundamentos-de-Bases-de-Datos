import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;
//import com.csvreader.CsvReader;

public class Lectura_csv {
 
    public static void main(String[] args) {
     
        try {
         
        CsvReader mi_csv = new CsvReader("./P1.csv"); //Cambien a la ruta de su archivo
        mi_csv.readHeaders(); // Leemos las Cabeceras, las cuales nos dan informacion de cada campo
         
        while (mi_csv.readRecord())
        {
            String nombre = mi_csv.get(0);
            String tel = mi_csv.get(1);
            String direccion = mi_csv.get(2);
            String correo = mi_csv.get(3);
            String m2 = mi_csv.get(4);
            String val_prop = mi_csv.get(5);
            String val_venta = mi_csv.get(6);
            
            System.out.println("Registro leido");
            System.out.println("Nombre: "+nombre);
            System.out.println("Télefono: "+tel); 
            System.out.println("Dirección: "+direccion);             
            System.out.println("Correo: "+correo);     
            System.out.println("Metros Cuadrados: "+m2+" m^²"); 
            System.out.println("Valor de la Propiedad: $"+val_prop); 
            System.out.println("Valor para Venta: $"+val_venta);
            System.out.println("/***********************************/\n");
        }
         
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
