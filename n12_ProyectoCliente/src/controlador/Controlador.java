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
	public Controlador() throws UnknownHostException, IOException, InstantiationException, IllegalAccessException 
	{
		sc=new SocketCliente();
		df= new DepartamentoFacade(sc);
	}
	public boolean elimnarDpt() throws InstantiationException, IllegalAccessException
	{
		DepartamentosDTO ss= new DepartamentosDTO(16, "Cundinamarca");
		return df.eliminar(ss);
	}
	public MyList<IDto> consultarTodosDpt ()
	{
		DepartamentosDTO dpt= new DepartamentosDTO();
		MyList<IDto> result= df.consultarTodos(dpt);
		return result;
	}
	public IDto consultarpoPk()
	{
		DepartamentosDTO ss= new DepartamentosDTO(38, "Cundinamarca");
		return df.consultarPk(ss);
	}
	public boolean insertar()
	{
		DepartamentosDTO dpt= new DepartamentosDTO(54, "nuevoo");
		return df.insertar(dpt);
	}
	public boolean actulizar()
	{
		DepartamentosDTO dpt= new DepartamentosDTO(15, "update44");
		return df.actualizar(dpt);
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
