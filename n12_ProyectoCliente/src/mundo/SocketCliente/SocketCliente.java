package mundo.SocketCliente;

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
		MyList<IDto> result= new MyList<IDto>();
		SentenciaSQL consulta= new SentenciaSQL(instruccion);
		try 
		{
			out.writeObject(consulta);
			result=(MyList<IDto>) in.readObject();			
		} 
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "No hay conexion con el servidor");
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
			JOptionPane.showMessageDialog(null, "No hay conexion con el servidor");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void cerrarConexiones() throws IOException
	{
		out.close();
		in.close();
		canal.close();
	}
}
