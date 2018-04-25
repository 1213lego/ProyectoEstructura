package controlador;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;

import MyList.MyList;
import mundo.DAO.DepartamentoDao;
import mundo.DTO.DepartamentosDTO;
import mundo.DTO.IDto;
import mundo.DTO.PropietarioDTO;
import mundo.client.SocketCliente;
import mundo.facade.DepartamentoFacade;
import mundo.facade.PropietarioFacade;

public class Controlador 
{
	private PropietarioFacade df;
	private SocketCliente sc;
	public Controlador() throws UnknownHostException, IOException, InstantiationException, IllegalAccessException 
	{
		sc=new SocketCliente();
		df= new PropietarioFacade(sc);
	}
	public boolean elimnarDpt() throws InstantiationException, IllegalAccessException
	{
		PropietarioDTO p= new PropietarioDTO("111111111");
		return df.eliminar(p);
	}
	public MyList<IDto> consultarTodosDpt ()
	{
		PropietarioDTO p= new PropietarioDTO("");
		MyList<IDto> result= df.consultarTodos(p);
		return result;
	}
	public IDto consultarpoPk()
	{
		PropietarioDTO p= new PropietarioDTO("3333333");
		return df.consultarPk(p);
	}
	public boolean insertar()
	{
		PropietarioDTO p= new PropietarioDTO("Nuevo", "nuevo", "nuevo", "nuevo", "nuevo");
		return df.insertar(p);
	}
	public boolean actulizar()
	{
		PropietarioDTO p= new PropietarioDTO("4444444", "ac", "ac", "ac", "ac");
		return df.actualizar(p);
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
