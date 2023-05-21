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
		
		Data = data.read_file("guategrafo");

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
}
