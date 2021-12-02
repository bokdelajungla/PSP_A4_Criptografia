package requerimiento3;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class GestorCifradoAsimetrico {

	private KeyPairGenerator generador; //En este caso es KeyPairGenerator
	private KeyPair claves; 
	private Cipher cifrador;
	private String algoritmo = "RSA"; //El algoritmo es RSA
	private byte[] mensajeCifrado; 	//Almacenamos el mensaje cifrado en bytes
									//porque el cifrador funciona con bloques de bytes
	
	//Constructor
	public GestorCifradoAsimetrico() {
		try {
			//El algoritmo es RSA
			System.out.println("Se empleará el algoritmo: "+algoritmo);
			//Creamos el Generador de claves
			this.generador = KeyPairGenerator.getInstance(algoritmo);
			System.out.println("Generador de claves creado");
			//Creamos el par de claves publica/privada
			this.claves = generador.generateKeyPair();
			System.out.println("Par de claves pública/privada generadas");
			//Creamos el objeto cifrador
			this.cifrador = Cipher.getInstance(algoritmo);
			System.out.println("Cifrador/descifrador creado");
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Función que cifra una cadena que se le pasa como parámetro y la guarda en la variable 
	 * {@link GestorCifradoAsimetrico.GestorCifrado#mensajeCifrado} para poder descifrala o mostrarla 
	 * posteriormente
	 * 
	 * @param frase el mensaje a cifrar
	 */
	public void cifrar (String frase) {
		try {
			//Configuramos el cifrador para que use la clave pública
			//para encriptar
			cifrador.init(Cipher.ENCRYPT_MODE, claves.getPublic());
			System.out.println("Cifrador configurado para encriptar con clave pública "+algoritmo);				
			//El cifrador trabaja con bytes, lo convertimos
			byte[] bytesFrase = frase.getBytes();
			System.out.println("Cifrando el mensaje original");
			//El cifrador devuelve una cadena de bytes
			mensajeCifrado = cifrador.doFinal(bytesFrase);
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Su mensaje ha sido encriptado correctamente");
	}
	
	/**
	 * Descifra el mensaje almacenado y lo muestra por pantalla
	 */
	public String descifrar() {			
		try {
			System.out.println("Descifrando la frase:");
			//Configuramos el cifrador para que use la clave privada
			//para desencriptar.
			cifrador.init(Cipher.DECRYPT_MODE, claves.getPrivate());
			System.out.println("Cifrador configurado para desencriptar mediante "+algoritmo);	
			byte[] bytesMensajeDescifrado = cifrador.doFinal(mensajeCifrado);
			String mensajeDescifrado = new String(bytesMensajeDescifrado);
			System.out.println("Mensaje Descifrado: " + mensajeDescifrado);
		
			return mensajeDescifrado;
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Devuelve el mensaje cifrado almacanado
	 * @return mensajeCifrado el mensaje cifrado almacenado convertido en un String
	 */
	public String getMensaje() {
		return new String(this.mensajeCifrado);
	}
	
}
