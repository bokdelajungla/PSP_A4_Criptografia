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
						byte[] bytesMensajeOriginal = data.getBytes();
						 bytesMensajeCifrado = cifrador.doFinal(bytesMensajeOriginal);
						String mensajeCifrado = new String(bytesMensajeCifrado);
						done = true;
						break;
					case 3:
						if (bytesMensajeCifrado != null) {
							System.out.println("Mensaje Cifrado: " + new String(bytesMensajeCifrado));
						} else {
							System.out.println("No hay nada cifrado");
						}
						break;
					case 4:
						if(bytesMensajeCifrado != null) {
							cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
							bytesMensajeDescifrado = cifrador.doFinal(bytesMensajeCifrado);
							System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
						} else {
							System.out.println("No hay nada cifrado");
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
