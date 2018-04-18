package mundo.facade;

import MyList.MyList;
import mundo.DAO.Dao;
import mundo.DTO.IDto;
import mundo.client.SocketCliente;

public abstract class AbsFacade <T extends Dao>
{
	protected Class classs;
	public AbsFacade(Class classs) 
	{
		this.classs=classs;
	}
	
	
	public void eliminar (SocketCliente so, IDto idto) throws InstantiationException, IllegalAccessException
	{
		T nuevo= (T) classs.newInstance();
		nuevo.eliminar(so, idto);
	}
	
	public MyList<IDto> consultarTodos (SocketCliente so, IDto idto)
	{
		MyList<IDto> result= so.ejecutaConsulta(idto.consultarTodos());
		return result;
	}

}
