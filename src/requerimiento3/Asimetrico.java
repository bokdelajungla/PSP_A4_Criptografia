package requerimiento3;

import com.google.gson.Gson;

import java.lang.NumberFormatException;
import java.util.Scanner;

public class Asimetrico {
	
	public final static String MATTRICULA_VALIDA = "^[0-9]{4}[A-Z]{3}$";
	
	public static void main(String[] args) {
		System.out.println("Bienvenido al sistema de cifrado Asimétrico.\nPor favor elija una opción:");
		
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion=0;
		String frase = null;
		GestorCifradoAsimetrico cifradorFrase = null;
		GestorCifradoAsimetrico cifradorCoche = null;
		Coche coche = null;
		Gson gson = new Gson();
		int modo = 0;
		
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
						
						System.out.println("Elija el Método de cifrado:");
						System.out.println("1 - Confidencialidad");
						System.out.println("2 - Autenticidad");
						modo = Integer.parseInt(sc.nextLine());
						
						if (modo == 1) {
							cifradorFrase = new GestorCifradoAsimetrico(modo);
							cifradorFrase.cifrar(frase);
						}
						else if(modo == 2) {
							cifradorFrase = new GestorCifradoAsimetrico(modo);
							cifradorFrase.cifrar(frase);
						}
						else {
							System.out.println("Opción no válida. Esciba 1 o 2");
						}				

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
						
						try {
							coche = new Coche(matricula, marca, modelo, Float.parseFloat(precio));
							
							System.out.println("Elija el Método de cifrado:");
							System.out.println("1 - Confidencialidad");
							System.out.println("2 - Autenticidad");
							modo = Integer.parseInt(sc.nextLine());
							
							String json = gson.toJson(coche);
							
							if (modo == 1) {
								cifradorCoche = new GestorCifradoAsimetrico(modo);
								cifradorCoche.cifrar(json);
							}
							else if(modo == 2) {
								cifradorCoche = new GestorCifradoAsimetrico(modo);
								cifradorCoche.cifrar(json);
							}
							else {
								System.out.println("Opción no válida. Esciba 1 o 2");
							}
							
						}catch(MatriculaNoValida e) {
							System.out.println(e.getMessage());
						}
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
