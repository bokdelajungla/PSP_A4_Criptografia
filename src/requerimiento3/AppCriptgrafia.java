package requerimiento3;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;

import recursos.*;

public class AppCriptgrafia {
	
	public static void main(String[] args) throws IOException {
		String matricula;
		String marca, modelo;
		double precio;
		String msg;
		int opc = 0;
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		boolean esCifrado = false;
		String cifrado = null;
		String descifrado = null;
		while(!salir) {
			System.out.print(menu());
			opc = sc.nextInt();
			// limpiamos el buffer
			sc.nextLine();
			switch(opc) {
			case 1:
				System.out.println("**** Encriptar un mensaje ****");
				System.out.print("mensaje: ");
				msg = sc.nextLine();
				byte[] msgOriginalBytes = msg.getBytes();
				System.out.println("Enciptación mensaje -> 3.Pasamos a bytes el mensaje a encriptar");
				
				System.out.println("** Encriptando asimétricamente con autenticidad un mensaje haciendo uso del algoritmo RSA **");
				try {
					// Enciptación mensaje -> 1.Generador de claves obtenido
					KeyPairGenerator generador1 = KeyPairGenerator.getInstance("RSA");
					
					// Enciptación mensaje -> 2.Clave obtenida
					KeyPair claves1 = generador1.generateKeyPair();
					
					// Enciptación mensaje -> 3.Descrifrador obtenido
					Cipher cifrador1 = Cipher.getInstance("RSA");
					
					// Enciptación mensaje -> 5.Cifrador configurado (para cifrar) con clave privada
					cifrador1.init(Cipher.ENCRYPT_MODE, claves1.getPrivate());
					System.out.println("clave utilizada: " + claves1.getPublic().getClass().getName());
					
					// Enciptación mensaje -> 6.Ciframos el mensaje
					byte[] msgCifradoBytes = cifrador1.doFinal(msgOriginalBytes);
					cifrado = new String(msgCifradoBytes);
					
					// Enciptación mensaje -> 7.Descifrador configurado (para descrifrar) con clave pública
					cifrador1.init(Cipher.DECRYPT_MODE, claves1.getPublic());
					
					// Enciptación mensaje -> 8.Desciframos el mensaje
					byte[] msgDesencriptadoBytes = cifrador1.doFinal(msgCifradoBytes);
					descifrado = new String(msgDesencriptadoBytes);
					
					System.out.println("Se ha cifrado el mensaje");
					esCifrado = true;
				} catch (Exception gse) {
					System.out.println("Fallo en: " + gse.getMessage());
					gse.printStackTrace();
				}
				break;
			case 2:
				System.out.println("**** Mostrar mensaje encriptado ****");
				if(esCifrado) {
					System.out.println("mensaje encriptado: " + cifrado);
				} else {
					System.out.println("Antes debes cifrar un mensaje (símil de error 404)");
				}
				break;
			case 3:
				System.out.println("**** Desencriptar mensaje ****");
				if(esCifrado) {
					System.out.println("mensaje desencriptado: " + descifrado);
				} else {
					System.out.println("Antes debes cifrar un mensaje (símil de error 404)");
				}
				break;
			case 4:
				System.out.println("**** Encriptar coche ****");
				do {
					System.out.print("matricula: ");
					matricula = sc.nextLine();	
					if(!Coche.matriculaValida(matricula))
						System.out.println("introduzca una matrícula válida: 4 dígitos y 3 letras 0000XXX");
				} while(!Coche.matriculaValida(matricula));
				System.out.print("marca: ");
				marca = sc.nextLine();
				System.out.print("modelo: ");
				modelo = sc.nextLine();
				System.out.print("precio: ");
				try {
					precio = sc.nextDouble();
				} catch(InputMismatchException e) {
					System.out.println("precio no válido, se establece el valor por defecto 0.0 (€)");
					precio = 0.0;
				}
				// limpiamos el buffer
				sc.nextLine();
				Coche coche = new Coche(matricula, marca, modelo, precio);
				System.out.println("** Encriptando asimétricamente con confindencialidad un objteo coche haciendo uso del algoritmo RSA **");
				try {
					// Enciptación objeto -> 1.Generador de claves obtenido
					KeyPairGenerator generador2 = KeyPairGenerator.getInstance("RSA");

					// Enciptación objeto -> 2.Clave obtenida
					KeyPair claves2 = generador2.generateKeyPair();
					
					// Enciptación objeto -> 3.Descrifrador obtenidoa
					Cipher cifrador2 = Cipher.getInstance("RSA");

					// ncriptación objeto -> 4.Cifrador configurado (para cifrar) con clave pública
					cifrador2.init(Cipher.ENCRYPT_MODE, claves2.getPublic());
					System.out.println("clave utilizada: " + claves2.getPublic().getClass().getName());

					// Encriptación objeto -> 5.Encriptamos el coche (objeto serializado)
					// la clase Coche debe estar serializada
					SealedObject so = new SealedObject(coche, cifrador2);
					System.out.println("objeto coche encriptado, procedemos a descencriptarlo");					

					// Enciptación obteto -> 6.Descifrador configurado (para descrifrar) con clave pública
					// configuramos el descrifrador -> puede arrojar una IOException (capturarla)
					cifrador2.init(Cipher.DECRYPT_MODE, claves2.getPrivate());

					// Enciptación obteto -> 7.Descifrador configurado (para descrifrar) con clave pública
					// creamos otro objeto para guardar el valor descifrado
					Coche coche2 = (Coche) so.getObject(cifrador2);
					System.out.println("coche desencriptado: " + coche2);
					
				} catch (Exception gse) {
					System.out.println("Fallo en: " + gse.getMessage());
					gse.printStackTrace();
				}
				break;
			case 5:
				System.out.println("** Ha finalizado la apliacación de Julio César **");
				salir = true;
				break;
			}
		}
		sc.close();
	}
	
	public static String menu() {
		return "\n******************** Menu ********************\n"
				+ "1 - Encriptar frase\n"
				+ "2 - Mostrar frase encriptada\n"
				+ "3 - Desencriptar frase\n"
				+ "4 - Encriptar coche\n"
				+ "5 - Salir del programa\n"
				+ "*******************************************\n\n"
				+ "Introduzca una opción: ";
	}

}
