package mundo.facade;

import mundo.DAO.DepartamentoDao;
import mundo.client.SocketCliente;

public class DepartamentoFacade extends AbsFacade<DepartamentoDao>
{
	public DepartamentoFacade(SocketCliente sc) throws InstantiationException, IllegalAccessException 
	{
		super(sc,DepartamentoDao.class);
	}
}
