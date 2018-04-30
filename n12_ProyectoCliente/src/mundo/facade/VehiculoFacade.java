package mundo.facade;

import mundo.DAO.VehiculoDao;
import mundo.DTO.VehiculoDTO;
import mundo.SocketCliente.SocketCliente;

public class VehiculoFacade extends AbsFacade<VehiculoDao, VehiculoDTO>
{
	public VehiculoFacade(SocketCliente sc) throws InstantiationException, IllegalAccessException 
	{
		super(sc, VehiculoDao.class);
	}

}
