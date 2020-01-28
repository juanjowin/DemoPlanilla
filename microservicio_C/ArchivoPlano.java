package com.microservices.dto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchivoPlano {


	//crea el archivo en disco, recibe como parámetro la lista de estudiantes
	public void crearArchivo(List<DetallePlanillaDto> lista) {
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter("C:\\archivo\\planillEmpleados.txt");
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			for (DetallePlanillaDto detalle : lista) {
				//escribe los datos en el archivo
				bfwriter.write(detalle.getIdEmpleado() + "|" + detalle.getSueldoBase() + "|" + detalle.getOtrosIngresos()
				+ "," +detalle.getOtrosDescuentos() + "\n");
			}
			//cierra el buffer intermedio
			bfwriter.close();
			System.out.println("Archivo creado satisfactoriamente..");
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//crea el archivo en disco, retorna la lista de estudiantes
	public static ArrayList leerArchivo() {
		// crea el flujo para leer desde el archivo
		File file = new File("C:\\archivo\\planillEmpleados.txt");
		ArrayList detalle= new ArrayList<DetallePlanillaDto>();	;
		Scanner scanner;
		try {
			//se pasa el flujo al objeto scanner
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				// el objeto scanner lee linea a linea desde el archivo
				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);
				//se usa una expresión regular
				//que valida que antes o despues de una coma (,) exista cualquier cosa
				//parte la cadena recibida cada vez que encuentre una coma				
				delimitar.useDelimiter("\\s*|\\s*");
				DetallePlanillaDto e= new DetallePlanillaDto();
				e.setIdEmpleado(delimitar.nextInt());
				e.setSueldoBase(delimitar.nextDouble());
				e.setOtrosIngresos(delimitar.nextDouble());
				e.setOtrosDescuentos(delimitar.nextDouble());
				detalle.add(e);
			}
			//se cierra el ojeto scanner
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return  detalle;
	}
}
