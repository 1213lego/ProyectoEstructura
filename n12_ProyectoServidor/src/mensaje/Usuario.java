package mensaje;

import java.io.Serializable;

import mensaje.SentenciaSQL;

public class Usuario implements Serializable
{
	private static final long serialVersionUID = 447413656289813155L;
	private String usuario;
	private String password;
	private SentenciaSQL mensaje;
	public Usuario(String user, String password) throws Exception 
	{
		if(user!=null && password!=null)
		{
			this.usuario = user;
			this.password = password;
		}
		else
		{
			throw new Exception("No ha ingresado usuario y/o contraseña");
		}
	}

	public String insertar() 
	{
		return "INSERT INTO USUARIO (USUARIO, PASSWORD) VALUES ('"+usuario+"', '"+ password+"')";
	}
	
	public String eliminar() 
	{
		return "DELETE FROM USUARIO WHERE USUARIO = '" +usuario+"' AND PASSWORD = '"+password+"'";
	}
	
	public String actualizar() 
	{
		return "UPDATE USUARIO SET PASSWORD = '" +password+"' WHERE USUARIO = '"+ usuario+"' AND PASSWORD = '"+password+"'";
	}
	
	public String login()
	{
		return "SELECT USUARIO, PASSWORD FROM USUARIO WHERE USUARIO = '" + usuario+"' AND PASSWORD = '"+password+"'";
	}
}
