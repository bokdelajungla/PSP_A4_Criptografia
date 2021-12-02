package requerimiento1;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Simetrica {
	static Cipher cifrador = null;
	static SecretKey claveSecreta= null;

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		boolean done = false;
		int numero = 0;
		String data = null;
		byte[] bytesMensajeDescifrado = null;
		byte[] bytesMensajeCifrado = null;
		
		KeyGenerator generador = KeyGenerator.getInstance("AES");
		claveSecreta = generador.generateKey();
		
		while(exit == false) {
			System.out.println("----- MENU -------");
			System.out.println("1 - Salir");
			System.out.println("2 - Encriptar");
			System.out.println("3 - Mostrar encriptación");
			System.out.println("4 - Desencriptar");
			try {	
		
				System.out.println(claveSecreta);
				Cipher cifrador = Cipher.getInstance("AES");
				numero = Integer.parseInt(sc.nextLine());
					switch(numero){
					case 1:
						exit = true;
						break;			
					case 2:
						System.out.println("Qué quiere encriptar");
						data = sc.nextLine();				
						cifrador = Cipher.getInstance("AES");
						cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
						System.out.println(claveSecreta);
						System.out.println(cifrador);
						byte[] bytesMensajeOriginal = data.getBytes();
						 bytesMensajeCifrado = cifrador.doFinal(bytesMensajeOriginal);
						String mensajeCifrado = new String(bytesMensajeCifrado);
						done = true;
						break;
					
					case 3:
						if (bytesMensajeDescifrado != null) {
							System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
						} else {
							System.out.println("No hay nada descrifrado");
						}
						break;
					case 4:
						if(bytesMensajeCifrado != null) {
							System.out.println("llega hasta auqui");
							System.out.println(cifrador);
							System.out.println(claveSecreta);
							cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
							bytesMensajeDescifrado = cifrador.doFinal(bytesMensajeCifrado);
							byte[] bytesMensajeDescifradoo = cifrador.doFinal(bytesMensajeCifrado);
							System.out.println("Descifrado!");
						} else {
							System.out.println("No hay ningún mensaje");
						}
						break;
					default:
						System.out.println("No está en el menú");
				}
			}catch (NumberFormatException e) {
				System.out.println("Sólo números");
			}
			catch (GeneralSecurityException gse) {
				System.out.println("Algo ha fallado.." + gse.getMessage());
				gse.printStackTrace();
			}
			
		}
		
		sc.close();
	}

}
