import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main{
    public static void main(String[] args)throws IOException, InterruptedException {
		extractData();
    }

    private static void extractData()throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		Interaction view = new Interaction();
		view.welcome();
		
		openFiles data = new openFiles();
		
		ArrayList<ArrayList<String>> Data = new ArrayList<ArrayList<String>>();
		ArrayList<String> cities = new ArrayList<String>();
		
		Data = data.read_file("grafo");

		// Extracting only the name of the cities.
		for(int i = 0; i < Data.size(); i++) {
			for(int j = 0; j < 2; j++) {
				cities.add(Data.get(i).get(j));	
			}
		}

        // Cleanup of repeated values
		Set<String> hashSet = new HashSet<String>(cities);
		cities.clear();
		cities.addAll(hashSet);

		// Lowercase the cities
		String[] city = new String[cities.size()];
		for (int i = 0; i < cities.size(); i++) {
			city[i] = cities.get(i).toLowerCase();
		}
		
		cities.clear();
		for (int i = 0; i < city.length; i++) {
			cities.add(city[i]);
		}
		// Start the program
		start(cities, view, city);
	}

	private static void start(ArrayList<String> cities, Interaction view, String[] city)throws IOException, InterruptedException {
		int selection = 0;
		while(selection != 2) {
			selection = view.selectionMainMenu();
			switch(selection) {
			case 1:
				// Ingresar lugar de salida.
				userEntry(cities, view, city);
				break;
			default:
				view.end_sys();
				break;
			}
		}
	}

	private static void userEntry(ArrayList<String> cities, Interaction view, String[] city) throws IOException, InterruptedException{
		int startPlace = view.startCity(cities);
		int destiny = view.destiny(cities);
		final int INF = Integer.MAX_VALUE;   // Valor maximo (infinito)
		
		int[][] matrix = new int[5][5];
		// SaoPaulo
		matrix[0][0] = 0;
		matrix[0][1] = 30;
		matrix[0][2] = INF;
		matrix[0][3] = INF;
		matrix[0][4] = 15;
		// Lima
		matrix[1][0] = 30;
		matrix[1][1] = 0;
		matrix[1][2] = 25;
		matrix[1][3] = INF;
		matrix[1][4] = 40;
		// Quito
		matrix[2][0] = INF;
		matrix[2][1] = 25;
		matrix[2][2] = 0;
		matrix[2][3] = 15;
		matrix[2][4] = 70;
		// BuenosAires
		matrix[3][0] = INF;
		matrix[3][1] = INF;
		matrix[3][2] = 15;
		matrix[3][3] = 0;
		matrix[3][4] = 90;
		// SantiagodeChile
		matrix[4][0] = 15;
		matrix[4][1] = 40;
		matrix[4][2] = 70;
		matrix[4][3] = 90;
		matrix[4][4] = 0;

		
	}
}
