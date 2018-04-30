package mundo.facade;
import MyList.MyList;
import mundo.DAO.AbsDao;
import mundo.DTO.IDto;
import mundo.SocketCliente.SocketCliente;

public abstract class AbsFacade <T extends AbsDao, X extends IDto>
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

	public boolean insertar(X dto) 
	{
		return dao.insertar(sc, dto);
	}
	
	public boolean actualizar(X dto) 
	{
		return dao.actualizar(sc, dto);
	}
	
	public boolean eliminar( X idto)
	{		
		return dao.eliminar(sc, idto);
	}
	
	public MyList<X> consultarTodos ( X idto)
	{
		MyList<X> result= dao.consultarTodos(sc, idto);
		return result;
	}	
	public X consultarPk(X dto) 
	{
		return (X) dao.consultarPk(sc, dto);
	}
}
