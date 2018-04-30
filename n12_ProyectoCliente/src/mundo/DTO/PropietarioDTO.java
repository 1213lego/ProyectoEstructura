package mundo.DTO;

import java.io.Serializable;

public class PropietarioDTO implements IDto, Serializable
{
	private static final long serialVersionUID = 447413656289813155L;
	private String cedulaPropietario;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	public PropietarioDTO(String cedulaPropietario, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido) 
	{
		this.cedulaPropietario = cedulaPropietario;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
	}
	public PropietarioDTO() 
	{
	}
	public PropietarioDTO(String cedulaPropietario) 
	{
		this.cedulaPropietario=cedulaPropietario;
	}
	public String getCedulaPropietario() 
	{
		return cedulaPropietario;
	}
	public void setCedulaPropietario(String cedulaPropietario) 
	{
		this.cedulaPropietario = cedulaPropietario;
	}
	public String getPrimerNombre() 
	{
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) 
	{
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() 
	{
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) 
	{
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() 
	{
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) 
	{
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() 
	{
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) 
	{
		this.segundoApellido = segundoApellido;
	}
	
	@Override
	public String toString() {
		return "PropietarioDTO [cedulaPropietario=" + cedulaPropietario + ", primerNombre=" + primerNombre
				+ ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + "]";
	}
	@Override
	public String insertar() 
	{
		return "INSERT INTO PROPIETARIO (CEDULAPROPIETARIO, PRIMERNOMBRE, SEGUNDONOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO) VALUES ( '"+ cedulaPropietario +"', '" +primerNombre+"', '" + segundoNombre+"', '" + primerApellido+"', '" + segundoApellido + "')" ;
	}
	@Override
	public String eliminar() 
	{
		return "DELETE FROM PROPIETARIO  WHERE CEDULAPROPIETARIO = '"+ cedulaPropietario+"'";
	}
	@Override
	public String actualizar() 
	{
		return "UPDATE PROPIETARIO SET CEDULAPROPIETARIO = '" + cedulaPropietario+"', PRIMERNOMBRE = '"+ primerNombre+ "', SEGUNDONOMBRE = '"+ segundoNombre+ "', PRIMERAPELLIDO = '" + primerApellido +"', SEGUNDOAPELLIDO = '"+ segundoApellido +"' WHERE CEDULAPROPIETARIO = '" + cedulaPropietario+"'";
	}
	@Override
	public String consultarPk() 
	{
		return "select * from PROPIETARIO WHERE CEDULAPROPIETARIO = '" + cedulaPropietario +  "'";
	}
	@Override
	public String consultarTodos() 
	{
		return "select * from PROPIETARIO";
	}
	
	
}
