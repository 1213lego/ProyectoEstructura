package mundo.Servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;

import MyList.MyList;

public class Servidor
{
	private ServerSocket server;
	private int puerto;
	private String url;
	private String user;
	private String pass;
	public Servidor() throws IOException 
	{
		FileInputStream archivo= new FileInputStream("data/servidor.properties");
		Properties prop;
		prop = new Properties();
		prop.load(archivo);
		puerto= Integer.parseInt(prop.getProperty("puerto"));
		url= prop.getProperty("url");
		user=prop.getProperty("user");
		pass=prop.getProperty("pass");
	}	
	public void recibirCliente() throws Exception
	{
		
		server= new ServerSocket(puerto);
		DBConexion con= new DBConexion(url,user,pass);
		while(true)
		{
			Socket canal= server.accept();			
			ServidorHilo cliente= new ServidorHilo(canal, con );
			cliente.start();
		}
	}
	
	public void run() 
	{
		// TODO Auto-generated method stub
		try 
		{
			recibirCliente();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		JOptionPane.showMessageDialog(null,"server");
		try 
		{
			Servidor server= new Servidor();
			server.recibirCliente();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
