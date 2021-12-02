package requerimiento1;

import java.lang.NumberFormatException;
import java.util.Scanner;

public class Simetrico {
	
	public static void main(String[] args) {
		System.out.println("Bienvenido al sistema de cifrado simétrico.\nPor favor elija una opción:");
		
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion=0;
		String frase = null;
		int algoritmo = 0;
		GestorCifrado cifrador = null;
		
		while(!salir) {
			System.out.println("1 - Salir del programa");
			System.out.println("2 - Encriptar frase");
			System.out.println("3 - Mostrar frase encriptada");
			System.out.println("4 - Desencriptar frase");
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
						System.out.println("Elija el Algortimo simétrico de cifrado:");
						System.out.println("1 - DES");
						System.out.println("2 - AES");
						algoritmo = Integer.parseInt(sc.nextLine());
						if (algoritmo == 1) {
							cifrador = new GestorCifrado("DES");
							cifrador.cifrar(frase);	
						}
						else if(algoritmo == 2) {
							cifrador = new GestorCifrado("AES");
							cifrador.cifrar(frase);
						}
						else {
							System.out.println("Opción no válida. Esciba 1 o 2");
						}
						break;
					
					case 3:
						if (cifrador != null) {
						System.out.println("Su frase cifrada es: "+cifrador.getMensaje());
						} else {
							System.out.println("No hay ninguna frase cifrada para mostrar");
						}
						break;
					
					case 4:
						if(cifrador != null) {
							cifrador.descifrar();
						} else {
							System.out.println("No hay ninguna frase para desencriptar");
						}
						break;
					
					default:
						System.out.println("Opción no válida. Introduzca un número entre 1 y 4");
				}
			}catch (NumberFormatException e) {
				System.out.println("Opcion no válida");
			}
		}
		sc.close();
	}
}
