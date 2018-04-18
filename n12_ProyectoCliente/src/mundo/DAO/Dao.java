package mundo.DAO;

import MyList.MyList;
import mundo.DTO.DepartamentosDTO;
import mundo.DTO.IDto;
import mundo.client.SocketCliente;

public abstract class Dao<T extends IDto> 
{
	public Class classs;
	public Dao(Class classs) 
	{
		this.classs=classs;
	}
	
	public void eliminar(SocketCliente conexion, T idto)
	{
		conexion.ejecutaActualizacion(idto.eliminar());
	}
	public MyList<IDto> consultarTodos(SocketCliente conexion, T idto)
	{
		MyList<IDto> result= conexion.ejecutaConsulta(idto.consultarTodos());
		return result;
	}
	
}
