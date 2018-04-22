package mundo.facade;
import MyList.MyList;
import mundo.DAO.AbsDao;
import mundo.DTO.IDto;
import mundo.client.SocketCliente;

public abstract class AbsFacade <T extends AbsDao>
{
	protected Class classs;
	protected T dao;
	protected SocketCliente sc;
	
	public AbsFacade(SocketCliente sc, Class classs) throws InstantiationException, IllegalAccessException 
	{
		this.sc= sc;
		this.classs=classs;
		dao=(T) classs.newInstance();
	}

	public boolean insertar(IDto dto) 
	{
		return dao.insertar(sc, dto);
	}
	
	public boolean actualizar(IDto dto) 
	{
		return dao.actualizar(sc, dto);
	}
	
	public boolean eliminar( IDto idto)
	{		
		return dao.eliminar(sc, idto);
	}
	
	public MyList<IDto> consultarTodos ( IDto idto)
	{
		MyList<IDto> result= dao.consultarTodos(sc, idto);
		return result;
	}	
	public IDto consultarPk(IDto dto) 
	{
		return dao.consultarPk(sc, dto);
	}
}
