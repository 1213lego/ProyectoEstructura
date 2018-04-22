package mundo.DTO;

import java.io.Serializable;

public class DepartamentosDTO implements IDto , Serializable
{
	private static final long serialVersionUID = 447413656289813155L;
	private int codigo;
	private String nombre;
	
	public DepartamentosDTO() 
	{
	}
	
	public DepartamentosDTO(int codigo, String nombre) 
	{
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public int getCodigo() 
	{
		return codigo;
	}

	public void setCodigo(int codigo) 
	{
		this.codigo = codigo;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	
	@Override
	public String toString() 
	{
		return "DepartamentosDTO [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

	@Override
	public String insertar() 
	{
		return "INSERT INTO DEPARTAMENTOS (CODIGO, NOMBRE) VALUES ("+codigo+ " ,' "+ nombre.trim()+ " ' ) ";
	}

	@Override
	public String actualizar() 
	{
		return "UPDATE DEPARTAMENTOS a SET  a.CODIGO =" + codigo + " ,  a.NOMBRE = '" + nombre.trim()+ " ' WHERE a.CODIGO ="+  codigo;

	}

	@Override
	public String eliminar() 
	{
		return "delete from DEPARTAMENTOS WHERE CODIGO = "+codigo;
	}

	@Override
	public String consultarTodos() 
	{
		return "select * from DEPARTAMENTOS";
	}

	@Override
	public String consultarPk() 
	{
		return "select * from DEPARTAMENTOS WHERE CODIGO = "+codigo;
	}

}
