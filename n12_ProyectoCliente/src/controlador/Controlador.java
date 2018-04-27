package controlador;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;

import MyList.MyList;
import mundo.DAO.DepartamentoDao;
import mundo.DTO.DepartamentosDTO;
import mundo.DTO.IDto;
import mundo.DTO.PropietarioDTO;
import mundo.DTO.VehiculoDTO;
import mundo.client.SocketCliente;
import mundo.facade.DepartamentoFacade;
import mundo.facade.PropietarioFacade;
import mundo.facade.VehiculoFacade;

public class Controlador 
{
	private VehiculoFacade df;
	private SocketCliente sc;
	public Controlador() throws UnknownHostException, IOException, InstantiationException, IllegalAccessException 
	{
		sc=new SocketCliente();
		df= new VehiculoFacade(sc);
	}
	public boolean elimnarDpt() throws InstantiationException, IllegalAccessException
	{
		VehiculoDTO v= new VehiculoDTO("aaa123");
		return df.eliminar(v);
	}
	public MyList<IDto> consultarTodosDpt ()
	{
		VehiculoDTO v= new VehiculoDTO("aaa123");
		MyList<IDto> result= df.consultarTodos(v);
		return result;
	}
	public IDto consultarpoPk()
	{
		VehiculoDTO v= new VehiculoDTO("ccc123");
		return df.consultarPk(v);
	}
	public boolean insertar()
	{
		VehiculoDTO v= new VehiculoDTO("nuevo", "Nuevo", "nuevo",(long) 12 ,"nuevo", 12);
		return df.insertar(v);
	}
	public boolean actulizar()
	{
		VehiculoDTO v= new VehiculoDTO("bbb123", "111111111", "up",(long) 1111 ,"up", 1111);
		return df.actualizar(v);
	}
	public static void main(String[] args) 
	{
		try 
		{
			Controlador ss= new Controlador();
			System.out.println("eliminado: " + ss.elimnarDpt());
			System.out.println("Insertanto: " + ss.insertar());
			System.out.println("consultando por pk" + ss.consultarpoPk());
			System.out.println("Actualizando"+ ss.actulizar());
			MyList<IDto>  lista= ss.consultarTodosDpt();
			Iterator<IDto> iter= lista.iterator();
			while(iter.hasNext())
			{
				System.out.println(iter.next().toString());
			}
		} 
		catch (IOException | InstantiationException | IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
