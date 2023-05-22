import javax.swing.JOptionPane;
import java.util.ArrayList;
public class Interaction {
	

	public void welcome() {
		System.out.println("Bienvenido al sistema de recomendaciones para rutas de viaje");
	}
	

	public void end_sys() {
		System.out.println("Gracias por probar el sistema!");
	}
	

	private int mainMenu() {
		String[] options = {"Indicar ciudad destino.","Salir."};
		for(int i = 0; i < options.length; i++) {
			System.out.println((i+1) + ". " + options[i]);
		}
		return options.length;
	}
	

	public int selectionMainMenu() {
		int selection = 0;
		boolean next_step = false;
		do {
			try {
				int options = mainMenu();
				selection = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una de las opciones: "));
				if(selection < 1 ||  selection > options) {
					System.out.println("DEBE INGRESAR UNA DE LAS OPCIONES");
					System.out.println(selection);
				}else {
					next_step = true;
				}
			}catch(NumberFormatException e) {
				System.out.println("DEBE INGRESAR UN VALOR NUMERICO.");
			}	
		}while(!next_step);
		
		return selection;
	}
	
	
	public int destiny(ArrayList<String> cities) {
		String destiny = "";
		boolean next_step = false;
		System.out.println("\n\n\n");
		for(int i = 0; i < cities.size(); i++) {
			System.out.println(cities.get(i) + " ----- " + (i) + "  (representa la ciudad en el grafo)");
		}
		
		do {
			destiny = JOptionPane.showInputDialog("Ingrese la ciudad destino: ");
			destiny = destiny.toLowerCase();
			if(cities.contains(destiny)) {
				next_step = true;
			}else {
				System.err.println("DEBE INGRESAR UNA CIUDAD VALIDA");
				for(int i = 0; i < cities.size(); i++) {
					System.out.println(cities.get(i));
				}
			}
		}while(!next_step);
		return transformData(destiny);
	}
	
	
	public int startCity(ArrayList<String> cities) {
		String startCity = "";
		boolean next_step = false;
		System.out.println("\n\n\n");
		for(int i = 0; i < cities.size(); i++) {
			System.out.println(cities.get(i) + " ----- " + (i) + "   (representa la ciudad en el grafo)");
		}
		
		do {
			startCity = JOptionPane.showInputDialog("Ingrese la ciudad de origen: ");
			startCity = startCity.toLowerCase();
			if(cities.contains(startCity)) {
				next_step = true;
			}else {
				System.err.println("DEBE INGRESAR UNA CIUDAD VALIDA!");
				for(int i = 0; i < cities.size(); i++) {
					System.out.println(cities.get(i));
				}
			}
		}while(!next_step);
		;
		return transformData(startCity);
	}
	

	private int transformData(String selection) {
		String[] cities = {"SaoPaulo","Lima", "Quito", "BuenosAires", "SantiagodeChile"};
		int value = 0;
		for(int i = 0; i < cities.length; i++) {
			if(cities[i].equals(selection)) {
				 value = i;
			}
		}
		return value;
	}
}