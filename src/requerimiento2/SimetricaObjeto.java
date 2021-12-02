package requerimiento2;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import Enrtidad.Coche;

public class SimetricaObjeto {
	
	static Cipher cifrador = null;
	static SecretKey claveSecreta= null;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
	
	Scanner sc = new Scanner(System.in);
	boolean exit = false;
	int numero = 0;
	String data = null;
	byte[] bytesMensajeDescifrado = null;
	byte[] bytesMensajeCifrado = null;
	Cipher cifrador = null;
	SealedObject so = null;
	Coche cocheDes = null;
	
	KeyGenerator generador = KeyGenerator.getInstance("AES");
	claveSecreta = generador.generateKey();
	
	while(exit == false) {
		System.out.println("----- MENU -------");
		System.out.println("1 - Salir");
		System.out.println("2 - Encriptar");
		System.out.println("3 - Mostrar encriptación");
		System.out.println("4 - Desencriptar");
		System.out.println("5 - Encriptar coche");
		try {	
			numero = Integer.parseInt(sc.nextLine());
				switch(numero){
				case 1:
					System.out.println("Ha salido");
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
					break;
				
				case 3:
					if (bytesMensajeCifrado != null) {
						System.out.println("Mensaje Cifrado: " + new String(bytesMensajeCifrado));
					} else if (so != null) {
						System.out.println("No hay nada cifrado");
					}
					break;
				case 4:
					if(bytesMensajeCifrado != null) {
						cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
						bytesMensajeDescifrado = cifrador.doFinal(bytesMensajeCifrado);
						System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
						bytesMensajeCifrado = null;
					} else if (so != null) {
						cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
						cocheDes = (Coche)so.getObject(cifrador);
						System.out.println("Coche Descifrado: " + new String(cocheDes.toString()));
						so = null;
					} else {
						System.out.println("No hay nada cifrado");
					}
					break;
				case 5:
					System.out.println("Coche a Encriptar:");
					System.out.println("Matricula:");
					String matricula = sc.nextLine();
					System.out.println("Marca:");
					String marca = sc.nextLine();
					System.out.println("Modelo:");
					String modelo = sc.nextLine();
					System.out.println("Precio:");
					String precio = sc.nextLine();
					Coche coche = new Coche(matricula, marca, modelo, Float.parseFloat(precio));
					System.out.println(coche);
					cifrador = Cipher.getInstance("AES");
					cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
					so = new SealedObject(coche, cifrador);
					System.out.println(so);
					break;
				default:
					System.out.println("Sólo del menú");
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
