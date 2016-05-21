package com.recepcion;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) throws SQLException {
		
		desplegarMenu();	
}

	private static void desplegarMenu() throws SQLException{
		
		Scanner ingresaUsuarioConsola= new Scanner(System.in);
		//String leerMenu;
		int leerMenu;
		// TODO Auto-generated method stub
		System.out.println("*****************************");
		System.out.println(" Bienvenidos a Restaurantrix");
		System.out.println("*****************************");
		System.out.println("      Elija una opción : ");
		System.out.println("");
		System.out.println("1.- Ingresar nuevo Cliente");
		System.out.println("2.- Listar clientes");
		System.out.println("3.- Actualizar clientes");
		System.out.println("4.- Eliminar cliente");
		System.out.println("5.- Salir");
		System.out.println("");
		System.out.println("*****************************");
		
		leerMenu= ingresaUsuarioConsola.nextInt();
		
		switch (leerMenu) {
		case 1:
			
			Scanner userInput = new Scanner(System.in);
	        System.out.println("Seleccionó la opción 1: Crear nuevo cliente ");
	        String name;
	        System.out.println("Ingrese nombre");
	        name = userInput.next();
	        
	        if (name.matches("[0-9]*")) {
				System.out.println("No puede ingresar números");
				
			}
	        
	        else {
	        	
	        	String dni;
		        System.out.println("Ingrese número de DNI: ");
		        dni = userInput.next();
		      
		        BdUtilitarios dbUtilities = new BdUtilitarios();
		        
		        String sql_stmt = "INSERT INTO cliente (name,dni) VALUES ('" + name + "','" + dni + "')";
		        
		        dbUtilities.ExecuteSQLStatement(sql_stmt);
		        
		        System.out.println("El cliente "+name+ " fue creado exitosamente...Gracias");
		        desplegarMenu();
				
			}
	        
	       	break;
		
		case 2:
			System.out.println("Selecionó la opción 2: Listar clientes");
			
		       try {
		            BdUtilitarios dbUtilities1 = new BdUtilitarios();
		 
		            String sql_stmt1 = "SELECT id, name, dni FROM cliente";
		            ResultSet resultSet = dbUtilities1.ReadRecords(sql_stmt1);
		 
		            // process query results
		            if (resultSet.next()) {
		 
		                ResultSetMetaData metaData = resultSet.getMetaData();
		                int numberOfColumns = metaData.getColumnCount();
		                System.out.print("Listando");
		 
		                for (int i = 1; i <= numberOfColumns; i++) {
		                    System.out.print(metaData.getColumnName(i));
		                   
		                }
		                System.out.println();
		 
		                do {
		                    for (int i = 1; i <= numberOfColumns; i++) {
		                        System.out.print(resultSet.getObject(i));
		                    }
		                    System.out.println();
		                } while (resultSet.next());
		                
		                System.out.println();
		 
		            } else {
		                System.out.println("No se encontró");
		            }
		 
		            //close db connection
		            dbUtilities1.DisconnectFromDB();
		        } catch (SQLException ex) {
		            System.out.println("Han ocurrido los siguientes errores: " + ex.getMessage());
		        } finally {
		            desplegarMenu();
		        	
		        }
			
			
			
			break;
			
		case 3:
			actualizarCliente();
			break;
			
		case 4:
			eliminarcliente();
			break;
			
		case 5:
			System.exit(0);
			break;
			
		default:
			System.out.println("Elección inválida");
			break;
		}
		
		
	}

	private static void eliminarcliente() throws SQLException {
		
		
		 Scanner userInput = new Scanner(System.in);
	        System.out.println("Seleccionó la opción 4: Eliminar ");
	 
	        String customer_id;
	        System.out.println("Ingrese Id para eliminar: ");
	        customer_id = userInput.next();
	 
	        //retrieve record to update
	        DisplayRecordEliminar(customer_id);
	 
	        String confirm_delete;
	        System.out.println("Ingrese Y para confirmar la eliminación ");
	        confirm_delete = userInput.next();
	 
	        if ("Y".equals(confirm_delete)) {
	            BdUtilitarios dbUtilities = new BdUtilitarios();
	 
	            String sql_stmt = "DELETE FROM cliente WHERE id = " + customer_id;
	 
	            dbUtilities.ExecuteSQLStatement(sql_stmt);
	            
	            System.out.println("Se eliminó");
	        }
	 
	        desplegarMenu();
		
	}

	private static void DisplayRecordEliminar(String customer_id) throws SQLException {
		
		 try {
	            BdUtilitarios dbUtilities = new BdUtilitarios();
	 
	            String sql_stmt = "SELECT id, name, dni FROM cliente WHERE id = " + customer_id;
	            ResultSet resultSet = dbUtilities.ReadRecords(sql_stmt);
	 
	            // process query results
	            if (resultSet.next()) {
	 
	                ResultSetMetaData metaData = resultSet.getMetaData();
	                int numberOfColumns = metaData.getColumnCount();
	                System.out.print("Listando");
	 
	                for (int i = 1; i <= numberOfColumns; i++) {
	                    //System.out.printf("%-8st", metaData.getColumnName(i));
	                	System.out.print(metaData.getColumnName(i));
	                }
	                System.out.println();
	 
	                do {
	                    for (int i = 1; i <= numberOfColumns; i++) {
	                        System.out.print(resultSet.getObject(i));
	                    }
	                    System.out.println();
	                } while (resultSet.next());
	 
	                System.out.println();
	 
	            } else {
	                System.out.println("No se encontró");
	            }
	 
	            //close db connection
	            dbUtilities.DisconnectFromDB();
	        } catch (SQLException ex) {
	            System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
	        }
		
	}

	private static void actualizarCliente() throws SQLException {
		
		Scanner userInput = new Scanner(System.in);
        System.out.println("Seleccionó la opción 3: Actualizar ");
 
        String customer_id;
        System.out.println("Ingrese id para actualizar: ");
        customer_id = userInput.next();
 
        //retrieve record to update
        DisplayRecord(customer_id);
 
        String name;
        System.out.println("Ingrese nombre: ");
        name = userInput.next();
 
        String dni;
        System.out.println("Ingrese dni: ");
        dni = userInput.next();
 
        BdUtilitarios dbUtilities = new BdUtilitarios();
 
        String sql_stmt = "UPDATE cliente SET name = '" + name + "',dni = '" + dni + "' WHERE id = " + customer_id;
 
        dbUtilities.ExecuteSQLStatement(sql_stmt);
 
        System.out.println("Se actualizó correctamente");
 
        desplegarMenu();
		
		
	}

	private static void DisplayRecord(String customer_id) throws SQLException {
		
		try {
            BdUtilitarios dbUtilities = new BdUtilitarios();
 
            String sql_stmt = "SELECT id, name, dni FROM cliente WHERE id = " + customer_id;
            ResultSet resultSet = dbUtilities.ReadRecords(sql_stmt);
 
            // process query results
            if (resultSet.next()) {
 
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                System.out.print("Listando");
 
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.print( metaData.getColumnName(i));
                }
                System.out.println();
 
                do {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        System.out.print( resultSet.getObject(i));
                    }
                    System.out.println();
                } while (resultSet.next());
 
                System.out.println();
 
            } else {
                System.out.println("No se encontró");
            }
 
            
            dbUtilities.DisconnectFromDB();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
        }
		
		
		
	}

}
