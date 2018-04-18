package mundo.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JOptionPane;

import MyList.MyList;
import mensaje.SentenciaSQL;
import mundo.DTO.DepartamentosDTO;
import mundo.DTO.IDto;


public class SocketCliente 
{
	private Socket canal;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String ip;
	private int puerto;
	public SocketCliente() throws UnknownHostException, IOException 
	{
		FileInputStream archivo= new FileInputStream("data/servidor.properties");
		Properties prop;
		
		///CLASE
		prop = new Properties();
		prop.load(archivo);
		puerto= Integer.parseInt(prop.getProperty("puerto"));
		ip=prop.getProperty("ip");
		canal= new Socket(ip,puerto);
		out= new ObjectOutputStream(canal.getOutputStream());
		in= new ObjectInputStream(canal.getInputStream());
		
	}
	
	public MyList<IDto> ejecutaConsulta(String instruccion)
	{
		MyList<IDto> result= null;
		SentenciaSQL consulta= new SentenciaSQL(instruccion);
		try 
		{
			out.writeObject(consulta);
			result=(MyList<IDto>) in.readObject();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean ejecutaActualizacion(String instruccion)
	{
		boolean result=false;
		SentenciaSQL consulta= new SentenciaSQL(instruccion);
		try 
		{
			out.writeObject(consulta);
			consulta= (SentenciaSQL) in.readObject();
			if(consulta.getSentencia().equals("Se ha ejecutado"))
			{
				result=true;
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) 
	{
		
			JOptionPane.showMessageDialog(null,"Inciando cliente");
			try 
			{
				SocketCliente c= new SocketCliente();
				DepartamentosDTO dto= new DepartamentosDTO();
				MyList<IDto>  lista= c.ejecutaConsulta(dto.consultarTodos());
				Iterator<IDto> iter= lista.iterator();
				while(iter.hasNext())
				{
					System.out.println(iter.next().toString());
				}
				DepartamentosDTO dto1= new DepartamentosDTO(122,"kkkk");
				boolean result=c.ejecutaActualizacion(dto1.insertar());
				System.out.println(result);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DepartamentosDTO dto= new DepartamentosDTO();
			
		
	}

}
