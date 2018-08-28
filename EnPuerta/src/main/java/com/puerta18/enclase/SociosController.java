package com.puerta18.enclase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SociosController {

	@Autowired
	private Environment env;

	// landing page, muestra un formulario de busqueda 
	// y tambien muestra los resultados con un parametro no requerido 
	@GetMapping("/")
	public String landing() {
		return "home";
	}
	
	@GetMapping("/socios/nuevo") // formulario de alta vacio
	public String nuevo() {
		return "nuevo_socio";
	}
	
	@GetMapping("/socios/nuevo/procesar") // inserta nuevos socios
	public String insertarNuevo(@RequestParam String nombre,@RequestParam String apellido,@RequestParam(required=false) String email, @RequestParam String dni)throws SQLException  {
		
	
	
			
			Connection connection; // Usar el import de java.sql
			
			connection = DriverManager.getConnection(
					env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
			
			PreparedStatement consulta = 
					connection.prepareStatement(
		"INSERT INTO socios(nombre, apellido, email, dni) VALUES(?, ?, ?,? );"
					);
			consulta.setString(1, nombre);
			consulta.setString(2, apellido);
			consulta.setString(3, email);
			consulta.setString(4, dni);
			
			consulta.execute();
			connection.close();
		return "home";
	}
	
	@GetMapping("/socios/checkin/{id}") // 
	public String checkIn(@PathVariable int id) throws SQLException {
		Connection connection; // Usar el import de java.sql
		
		connection = DriverManager.getConnection(
				env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement(
	"INSERT INTO checks(id_usuario,momento, tipo) VALUES (?, NOW(), ? );"	
				);
		consulta.setInt(1, id);
		consulta.setString(2, "in");
		
		consulta.execute();
		connection.close();
		return "home";
	}
	
	
	@GetMapping("/socios/checkout/{id}") // 
	public String checkOut(@PathVariable int id) throws SQLException {
Connection connection; // Usar el import de java.sql
		
		connection = DriverManager.getConnection(
				env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement(
	"INSERT INTO checks(id_usuario,momento, tipo) VALUES (?, NOW(), ? );"	
				);
		consulta.setInt(1, id);
		consulta.setString(2, "out");
		
		consulta.execute();
		connection.close();
		return "home";
	}
	
	// estas rutas mas adelante vamos a protegerlas con usuario y contraseña
	// @GetMapping("/socios/mostrar/{id}") // muestra el detalle completo de un socio
	// @GetMapping("/socios/listado")      // muestra el listado completo sin paginacion, por ahora
	
	// @GetMapping("/socios/modificar/{id}")
	// @GetMapping("/socios/modificar/procesar/{id}")
}
