import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Proyecto 2 - Gestión de Stock: Restaurante de Sushi IGNACIO GIL GARZÓN

public class ProyectoNavidad_IgnacioGilGarzon {

	//FUNCIONES DE MANEJO DE FICHEROS UNIDAD 4 Tema 2:

	public static int matriz_a_CSV (String[][] inventario, String ruta) {

		int num_lineas = 0;
		try {
			FileWriter fichero = new FileWriter(ruta);

			for(int i = 0; i<inventario.length; i++){
				for(int c = 0; c<inventario[i].length; c++){
					fichero.write(inventario[i][c] + "\n");
					num_lineas  ++;
				}
			}
            fichero.close();

		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("No se puede abrir el fichero");
		}

		return num_lineas;
	}

	//FUNCION DE MENÚ INSPIRADA EN PROYECTO UNIDAD 5 TEMA 1

	public static int imprimir_menu () {
		int opcion = 0;
		Scanner teclado = new Scanner(System.in);

		System.out.print(
				"\n-------- Elija la acción que desea realizar --------" + "\n" +
				"  0 - Salir" + "\n" +
				"  1 - Imprimir inventario" + "\n" +
				"  2 - Introducir Nuevo item" + "\n" +
				"  3 - Actualizar Stock" + "\n" +
				"  4 - Actualizar Precios" + "\n" +
				"  5 - Actualizar Disponibilidad" + "\n" +
				"  6 - Borrar item" + "\n" +
				"  7 - Guardar datos actuales en inventario.csv" + "\n"+

				"Opción: "
		);

		if (teclado.hasNextInt()) {
			opcion = teclado.nextInt();
		}
		else {
			teclado.next();
		}
		return opcion;
	}

	//FUNCIONES ORIGINALES E INDIVIDUALES

	public static void listar_inventario(String[][] inventario, String ruta, int contadorNuevoItem) {

		for(int i = 0; i< inventario.length; i++) {
			for (int j=0; j< inventario[i].length; j++)
				System.out.println(inventario[i][j]);
		}
	}

	public static int nuevo_item(String[][] inventario, int contadorNuevoItem){
		Scanner teclado = new Scanner(System.in);
		int numRegistro = 10 + contadorNuevoItem;
		String nombreProducto = null;
		int cantidad = 0;
		double precio = 0.00;
		char disponibilidad = 'a';
		String disponible = null;

		// OBTENER DATOS DEL NUEVO ITEM

		System.out.println("Introduce el nombre del nuevo item (ej- Leche): ");
		if (teclado.hasNextLine()) {
			nombreProducto = teclado.nextLine();
		}
		else
			System.out.println("ERROR al introducir el nombre");

		System.out.println("Introduce la cantidad del nuevo item (ej- 14): ");
		if (teclado.hasNextInt()) {
			cantidad = teclado.nextInt();
		}
		else
			System.out.println("ERROR al introducir la cantidad");

		System.out.println("Introduce el precio del nuevo item (ej- 1.99): ");
		if (teclado.hasNextDouble()) {
			precio = teclado.nextDouble();
		}
		else
			System.out.println("ERROR al introducir el precio");

		System.out.println("Introduce la disponibilidad (D- DISPONIBLE, N- NO): ");
		disponibilidad = teclado.next().charAt(0);
		if (disponibilidad == 'n' || disponibilidad == 'N'
				|| disponibilidad == 'd' || disponibilidad == 'D') {
			if(disponibilidad == 'n' || disponibilidad == 'N')
				disponible  = "NO DISPONIBLE";
			if(disponibilidad == 'd' || disponibilidad == 'D')
				disponible = "DISPONIBLE";
		}
		else
			System.out.println("ERROR al introducir la disponibilidad");

		//ESCRIBE EN FICHERO
		BufferedWriter bw = null;
	      try {
	    	  bw = new BufferedWriter(new FileWriter("inventario.csv", true));
	    	  bw.write("00"  + numRegistro + "\n" + nombreProducto + "\n" + cantidad + "\n" + precio + "\n" + disponible);
	    	  bw.newLine();
	    	  bw.flush();
	    	  } catch (IOException ioe) {
	    		  ioe.printStackTrace();
	    		  } finally {
	    			  if (bw != null)
	    				  try {
	    					  bw.close();
	    				  } catch (IOException ioe2) {

	    				  }
	    			  }
	      return contadorNuevoItem;
	}

	public static void añadir_accion (String accion, String[][] logs){
		java.util.Date fecha = new Date();

		BufferedWriter bw = null;
	    try {
	  	  bw = new BufferedWriter(new FileWriter("logs.csv", true));
	  	  bw.write(accion +":  "+ fecha);
	  	  bw.newLine();
	  	  bw.flush();
	  	  } catch (IOException ioe) {
	  		  ioe.printStackTrace();
	  		  } finally {
	  			  if (bw != null)
	  				  try {
	  					  bw.close();
	  				  } catch (IOException ioe2) {

	  				  }
	  			  }
	}

	public static int seleccionar_ID(int ID){
		Scanner teclado = new Scanner(System.in);
		boolean valido = false;
		System.out.println("Introduce el ID del producto que quiere actualizar (ej:1): ");
		if (teclado.hasNextInt()) {
			ID = teclado.nextInt();
			if(ID > 0 && ID < 50){
				valido = true;
			}
		}
		else
			System.out.println("ERROR al introducir el ID");
		if(valido == true){
			System.out.println("Valor del ID válido");
			return ID;
		}
		else{
			System.out.println("ERROR, ID con valor erróneo");
			return 0;
		}
	}

	public static void act_stock(String[][] inventario, int ID){
		Scanner teclado = new Scanner(System.in);
		int [][]numEntero = null;
		numEntero = new int [inventario.length][1];
		int x = 0;
		int y = 0;
		int stock = 0;

		for(int i = 0; i<inventario.length; i++){
			for(int c = 0; c<1; c++){
				numEntero[i][c] = Integer.parseInt(inventario [i][c]);
				if(numEntero[i][c]==(ID)){
					System.out.println("Número encontrado");
					x = i;
					y = c;
				}
			}
		}
		System.out.println("Ahora, introduce el nuevo stock: ");
		if (teclado.hasNextInt()) {
			stock = teclado.nextInt();
		}
		else
			System.out.println("ERROR al introducir el nuevo stock");
		String cadStock = stock+"";
		inventario[x][y+2] = cadStock;
	}

	public static void act_precios(String[][] inventario, int ID){
		Scanner teclado = new Scanner(System.in);
		int [][]numEntero = null;
		numEntero = new int [inventario.length][1];
		int x = 0;
		int y = 0;
		double price = 0.00;

		for(int i = 0; i<inventario.length; i++){
			for(int c = 0; c<1; c++){
				numEntero[i][c] = Integer.parseInt(inventario [i][c]);
				if(numEntero[i][c]==(ID)){
					System.out.println("Número encontrado");
					x = i;
					y = c;
				}
			}
		}
		System.out.println("Ahora, introduce el nuevo precio(solo acepta enteros): ");
		if (teclado.hasNextDouble()) {
			price = teclado.nextDouble();
		}
		else
			System.out.println("ERROR al introducir el nuevo precio");
		String cadStock = price+"";
		inventario[x][y+3] = cadStock;
	}

	public static void act_disponibilidad(String[][] inventario, int ID){
		int stock = 0;
		Scanner teclado = new Scanner(System.in);
		char nuevaDis = 'a';
		int [][]numEntero = null;
		numEntero = new int [inventario.length][1];
		int x = 0;
		int y = 0;
		String disponible = null;

		for(int i = 0; i<inventario.length; i++){
			for(int c = 0; c<1; c++){
				numEntero[i][c] = Integer.parseInt(inventario [i][c]);
				if(numEntero[i][c]==(ID)){
					System.out.println("Número encontrado");
					x = i;
					y = c;
				}
			}
		}
		System.out.println("Introduce la nueva disponibilidad (D- DISPONIBLE, N- NO): ");
		nuevaDis = teclado.next().charAt(0);
		if (nuevaDis == 'n' || nuevaDis == 'N'
				|| nuevaDis == 'd' || nuevaDis == 'D') {
			if(nuevaDis == 'n' || nuevaDis == 'N')
				disponible  = "NO DISPONIBLE";
			if(nuevaDis == 'd' || nuevaDis == 'D')
				disponible = "DISPONIBLE";
		}
		else
			System.out.println("ERROR al introducir la disponibilidad");

		inventario[x][y+4] = disponible;
	}

	private static void borrar_item(String[][] inventario, String ruta, int ID) {

		//Pasamos ID a formato String para poderlo comparar con inventario
		Scanner teclado = new Scanner(System.in);
		int [][]numEntero = null;
		numEntero = new int [inventario.length][1];
		int x = 0;
		int y = 0;

		//Buscamos el ID dentro de inventario en formato INT
		for(int i = 0; i<inventario.length; i++){
			for(int c = 0; c<1; c++){
				numEntero[i][c] = Integer.parseInt(inventario [i][c]);
				if(numEntero[i][c]==(ID)){
					System.out.println("Número encontrado");
					x = i;
					y = c;
				}
			}
		}

		//BORRAR
		for(int i = x; i<inventario.length-1;i++){
			inventario[i][y] = inventario[i+1][y];
			inventario[i][y+1] = inventario[i+1][y+1];
			inventario[i][y+2] = inventario[i+1][y+2];
			inventario[i][y+3] = inventario[i+1][y+3];
			inventario[i][y+4] = inventario[i+1][y+4];
		}

	}

	//MAIN

	public static void main(String[] args) throws IOException {

		System.out.println("Bienvenido al programa de Gestión de Stock: Restaurante de Sushi \n");

		 String[][]inventario = {   {"0001","Botellas salsa de soja","23","9.99","DISPONIBLE"},
		                            {"0002","Raíces de Wasabi","58","9.99","DISPONIBLE"},
		                            {"0003","Sopa Miso","0","4.99","NO DISPONIBLE"},
		                            {"0004","Raciones de Maki","11","11.99","DISPONIBLE"},
                                    {"0005","Raciones de Nigiri","17","10.99","DISPONIBLE"},
                                    {"0006","Kg de Atún","15","35.00","DISPONIBLE"},
                                    {"0007","Kg de Salmón","21","12.00","DISPONIBLE"},
                                    {"0008","Kg de Arroz preparado","36","1.50","DISPONIBLE"},
                                    {"0009","Kg de Edamame","14","2.30","DISPONIBLE"}};

		matriz_a_CSV (inventario, "inventario.csv");

		java.util.Date fecha = new Date();
		String[][]logs = {   {"Login: "+ fecha},};
		matriz_a_CSV (logs, "logs.csv");

		int opcion = -1;
		int ID = 0;
		String ruta = null;
		String accion = null;
		int contadorNuevoItem = 0;

		while (opcion != 0) {
			opcion = imprimir_menu();
			switch (opcion) {
			case 1: //IMPRIMIR INVENTARIO
				listar_inventario(inventario, ruta, contadorNuevoItem);
				accion = "IMPRIMIR INVENTARIO";
				añadir_accion(accion, logs);
				break;
			case 2: //INTRODUCIR NUEVO ITEM
				nuevo_item(inventario, contadorNuevoItem);
				accion = "INTRODUCIR NUEVO ITEM";
				añadir_accion(accion, logs);
				contadorNuevoItem ++;
				break;
			case 3: //ACTUALIZAR STOCK
				act_stock(inventario, seleccionar_ID(ID));
				accion = "ACTUALIZAR STOCK";
				añadir_accion(accion, logs);
				break;
			case 4: //ACTUALIZAR PRECIOS
				act_precios(inventario, seleccionar_ID(ID));
				accion = "ACTUALIZAR PRECIOS";
				añadir_accion(accion, logs);
				break;
			case 5: //ACTUALIZAR DISPONIBILIDAD
				act_disponibilidad(inventario, seleccionar_ID(ID));
				accion = "ACTUALIZAR DISPONIBILIDAD";
				añadir_accion(accion, logs);
				break;
			case 6: //BORRAR ITEM
				borrar_item(inventario, ruta, seleccionar_ID(ID));
				accion = "BORRAR ITEM";
				añadir_accion(accion, logs);
				break;
			case 7: //GUARDAR DATOS
				matriz_a_CSV (inventario, "inventario.csv");
				System.out.println("Guardado");
				accion = "GUARDADO";
				añadir_accion(accion, logs);
				break;
			case 0:
				System.out.println("¡Hasta Pronto!");
				accion = "TERMINAR PROGRAMA";
				añadir_accion(accion, logs);
				break;
			default:
				System.out.println("Opción incorrecta!");
			}
		}
	}
}
