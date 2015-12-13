package br.com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RedeUtil {
	
	public static byte[] serializar(Object obj) throws IOException {
		  ByteArrayOutputStream b = new ByteArrayOutputStream();
		  ObjectOutputStream o = new ObjectOutputStream(b);
		  o.writeObject(obj);
		  o.close();
		  return b.toByteArray();
	}
	
	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		  ObjectInputStream o = new ObjectInputStream(b);
		  o.close();
		  return o.readObject();
	}
}