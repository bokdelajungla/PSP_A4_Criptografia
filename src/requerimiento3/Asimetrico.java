package requerimiento3;

import com.google.gson.Gson;
import java.lang.NumberFormatException;
import java.util.Scanner;

public class Asimetrico {
	
	public static void main(String[] args) {
		System.out.println("Bienvenido al sistema de cifrado simétrico.\nPor favor elija una opción:");
		
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion=0;
		String frase = null;
		GestorCifradoAsimetrico cifradorFrase = null;
		GestorCifradoAsimetrico cifradorCoche = null;
		Coche coche = null;
		Gson gson = new Gson();
		
		while(!salir) {
			System.out.println("1 - Salir del programa");
			System.out.println("2 - Encriptar frase");
			System.out.println("3 - Mostrar frase encriptada");
			System.out.println("4 - Desencriptar frase");
			System.out.println("5 - Encriptar coche");
			System.out.println("6 - Mostrar coche encriptado");
			System.out.println("7 - Desencriptar coche");
			try {	
				opcion = Integer.parseInt(sc.nextLine());
			
					switch(opcion){
					case 1:
						System.out.println("Ha elegido Salir");
						System.out.println("...Saliendo");
						salir = true;
						break;
						
					case 2:
						System.out.println("Introduzca la frase a encriptar:");
						frase = sc.nextLine();
						
						cifradorFrase = new GestorCifradoAsimetrico();
						cifradorFrase.cifrar(frase);
						break;
					
					case 3:
						if (cifradorFrase != null) {
						System.out.println("Su frase cifrada es: "+cifradorFrase.getMensaje());
						} else {
							System.out.println("No hay ninguna frase cifrada para mostrar");
						}
						break;
					
					case 4:
						if(cifradorFrase != null) {
							cifradorFrase.descifrar();
						} else {
							System.out.println("No hay ninguna frase para desencriptar");
						}
						break;
						
					case 5:
						System.out.println("Introduzca los datos del coche:");
						System.out.println("Matricula:");
						String matricula = sc.nextLine();
						System.out.println("Marca:");
						String marca = sc.nextLine();
						System.out.println("Modelo:");
						String modelo = sc.nextLine();
						System.out.println("Precio:");
						String precio = sc.nextLine();
						coche = new Coche(matricula, marca, modelo, Float.parseFloat(precio));
					
						String json = gson.toJson(coche);
						
						cifradorCoche = new GestorCifradoAsimetrico();
						cifradorCoche.cifrar(json);
						break;

					case 6:
						if (cifradorCoche != null) {
						System.out.println("Su Coche cifrado es: "+cifradorCoche.getMensaje());
						} else {
							System.out.println("No hay ningun coche cifrado para mostrar");
						}
						break;
					
					case 7:
						if(cifradorCoche != null) {
							String cocheDescifrado = cifradorCoche.descifrar();
							coche = gson.fromJson(cocheDescifrado, Coche.class);
							System.out.println("Invocando el método toString() del coche descifrado: \n" + coche);
							
						} else {
							System.out.println("No hay ningun coche para desencriptar");
						}
						break;
					
					default:
						System.out.println("Opción no válida. Introduzca un número entre 1 y 7");
				}
			}catch (NumberFormatException e) {
				System.out.println("Introduzca números por favor");
			}
		}
		sc.close();
	}
}
