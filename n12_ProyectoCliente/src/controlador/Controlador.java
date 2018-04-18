package controlador;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;

import MyList.MyList;
import mundo.DAO.DepartamentoDao;
import mundo.DTO.DepartamentosDTO;
import mundo.DTO.IDto;
import mundo.client.SocketCliente;
import mundo.facade.DepartamentoFacade;

public class Controlador 
{
	private DepartamentoFacade df;
	private SocketCliente sc;
	public Controlador() throws UnknownHostException, IOException 
	{
		sc=new SocketCliente();
		df= new DepartamentoFacade();
	}
	public void elimnarDpt() throws InstantiationException, IllegalAccessException
	{
		DepartamentosDTO ss= new DepartamentosDTO(16, "Cundinamarca");
		df.eliminar(sc, ss);
	}
	public MyList<IDto> consultarTodosDpt ()
	{
		DepartamentosDTO dpt= new DepartamentosDTO();
		MyList<IDto> result= df.consultarTodos(sc,dpt );
		return result;
	}
	public static void main(String[] args) 
	{
		try 
		{
			Controlador ss= new Controlador();
			ss.elimnarDpt();
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
