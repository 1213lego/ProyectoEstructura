package mundo.facade;

import mundo.DAO.DepartamentoDao;
import mundo.DTO.DepartamentosDTO;
import mundo.client.SocketCliente;

public class DepartamentoFacade extends AbsFacade<DepartamentoDao, DepartamentosDTO>
{
	public DepartamentoFacade(SocketCliente sc) throws InstantiationException, IllegalAccessException 
	{
		super(sc,DepartamentoDao.class);
	}
}
