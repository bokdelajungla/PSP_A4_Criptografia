package requerimiento2;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class GestorCifrado {

	private KeyGenerator generador;
	private SecretKey clave;
	private Cipher cifrador;
	private String algoritmo;
	private byte[] mensajeCifrado; 	//Almacenamos el mensaje cifrado en bytes
									//porque el cifrador funciona con bloques de bytes
	
	//Constructor
	public GestorCifrado(String algoritmo) {
		try {
			this.algoritmo = algoritmo;
			System.out.println("Ha elegido el algoritmo: "+algoritmo);
			//Creamos el Generador de claves
			this.generador = KeyGenerator.getInstance(algoritmo);
			System.out.println("Generador de claves creado");
			//Creamos la clave simétrica
			this.clave = generador.generateKey();
			System.out.println("Clave simétrica "+algoritmo+" generada");
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
	 * {@link requqrimiento1.GestorCifrado#mensajeCifrado} para poder descifrala o mostrarla 
	 * posteriormente
	 * 
	 * @param frase el mensaje a cifrar
	 */
	public void cifrarFrase (String frase) {
		try {
			//Configuramos el cifrador para que use la clave simetrica
			//para encriptar
			cifrador.init(Cipher.ENCRYPT_MODE, clave);
			System.out.println("Cifrador configurado para encriptar mediante "+algoritmo);				
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
		System.out.println("Su frase ha sido encriptada correctamente");
	}
	
	/**
	 * Descifra el mensaje almacenado y lo muestra por pantalla
	 */
	public void descifrarFrase () {			
		try {
			System.out.println("Descifrando la frase:");
			//Configuramos el cifrador para que use la clave simetrica
			//para desencriptar. Debemos de usar la MISMA clave para descifrar, NO
			//PODEMOS usar/generar una diferente.
			cifrador.init(Cipher.DECRYPT_MODE, clave);
			System.out.println("Cifrador configurado para desencriptar mediante "+algoritmo);	
			byte[] bytesMensajeDescifrado = cifrador.doFinal(mensajeCifrado);
			System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
		
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve el mensaje cifrado almacanado
	 * @return mensajeCifrado el mensaje cifrado almacenado convertido en un String
	 */
	public String getMensaje() {
		return new String(this.mensajeCifrado);
	}
	
	/**
	 * Función que cifra un objeto de tipo Coche que se le pasa como parámetro y la guarda en la variable 
	 * {@link requqrimiento1.GestorCifrado#mensajeCifrado} para poder descifralo o mostrarlo 
	 * posteriormente
	 * 
	 * @param coche el objeto Coche a cifrar
	 */
	public void cifrarCoche (Coche coche) {
		try {
			//Configuramos el cifrador para que use la clave simetrica
			//para encriptar
			cifrador.init(Cipher.ENCRYPT_MODE, clave);
			System.out.println("Cifrador configurado para encriptar mediante "+algoritmo);				
			//El cifrador trabaja con bytes, lo convertimos
			byte[] bytesFrase = coche.toString().getBytes();
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
		System.out.println("Su frase ha sido encriptada correctamente");
	}
	
	/**
	 * Descifra el coche almacenado y lo muestra por pantalla
	 */
	public void descifrarCoche () {			
		try {
			System.out.println("Descifrando la frase:");
			//Configuramos el cifrador para que use la clave simetrica
			//para desencriptar. Debemos de usar la MISMA clave para descifrar, NO
			//PODEMOS usar/generar una diferente.
			cifrador.init(Cipher.DECRYPT_MODE, clave);
			System.out.println("Cifrador configurado para desencriptar mediante "+algoritmo);	
			byte[] bytesMensajeDescifrado = cifrador.doFinal(mensajeCifrado);
			System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
		
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
