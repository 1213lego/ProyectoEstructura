package mundo.facade;

import mundo.DAO.PropietarioDao;
import mundo.DTO.PropietarioDTO;
import mundo.client.SocketCliente;

public class PropietarioFacade extends AbsFacade<PropietarioDao	,PropietarioDTO>
{
	public PropietarioFacade(SocketCliente sc) throws InstantiationException, IllegalAccessException 
	{
		super(sc,PropietarioDao.class);
	}

}
