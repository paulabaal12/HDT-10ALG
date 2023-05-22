
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class openFiles {

    public ArrayList<ArrayList<String>> read_file(String file_name) {
        ArrayList<ArrayList<String>> dataFile = new ArrayList<ArrayList<String>>();
        Path filePath = Paths.get("Archivos\\" + file_name + ".txt");     	
    	try {
            
            BufferedReader br = Files.newBufferedReader(filePath);
            String line;
            
            while ((line = br.readLine()) != null) {
                
                String[] dataOfLine = line.split(" ");
                ArrayList<String> dataTemp = new ArrayList<String>();
                // Adding the fields fo the array to the temporal dinamic array.
                for (String data : dataOfLine) {
                    dataTemp.add(data);
                }
                dataFile.add(dataTemp);
            }
        } catch (IOException exception) {
        	System.out.println("EL ARCHIVO NO EXISTE, DEBE INGRESAR UN NOMBRE VALIDO.");
            System.out.println("NOTA: RECUERDE AGREGAR EL ARCHIVO A LA CARPETA ARCHIVOS.");
        }	
        return dataFile;
    }

   
    public void new_file(String file_name) {
        File File;
        try {
            // ruta archivos
            File = new File("Archivos\\" + file_name + ".txt");
         
            if (File.exists()) {
                System.out.println("el arhcivo ya existe");
            } else if (File.createNewFile()) {
                System.out.println("Se ha creado el archivo");
            }
        } catch (IOException exception) {
            System.err.println("No se ha creado el archivo");
        }
    }
}
