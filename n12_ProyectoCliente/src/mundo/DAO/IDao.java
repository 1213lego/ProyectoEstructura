package mundo.DAO;

import MyList.MyList;
import mundo.DTO.IDto;
import mundo.client.SocketCliente;

public interface IDao <T extends IDto>
{
	public boolean insertar(SocketCliente sc, T dto);
	public boolean eliminar(SocketCliente sc, T dto);
	public boolean actualizar(SocketCliente sc, T dto);
	public IDto consultarPk(SocketCliente sc, T dto);
	public MyList<IDto> consultarTodos(SocketCliente sc, T dto);
}
