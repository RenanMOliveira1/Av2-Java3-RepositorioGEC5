package br.com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Essa Classe serializa Objetos em bytes e 
 * também deserializa bytes em objeto.
 * 
 * @author Tiago Henrique
 * @version 1.0 (12/12/2015)
 */
public class RedeUtil {

	/**
	 * Esse método serializa um objeto em bytes
	 * 
	 * @param obj
	 * 			Objeto
	 * @return bytes
	 * 
	 * @throws IOException
	 */
	public static byte[] serializar(Object obj) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		o.close();
		return b.toByteArray();
	}

	/**
	 * Esse método serializa um objeto em bytes
	 * 
	 * @param obj
	 * 			Objeto
	 * @return bytes
	 * 
	 * @throws IOException
	 */
	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		o.close();
		return o.readObject();
	}
}