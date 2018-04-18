package mundo.Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import MyList.MyList;
import mensaje.SentenciaSQL;
import mundo.DTO.IDto;
import mundo.Servidor.Reflection.ReflectionDTO;

public class ServidorHilo extends Thread 
{
	public static final String CONSULTA="select";
	
	private Socket canal;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private DBConexion conexionDB;

	
	public ServidorHilo(Socket socket, DBConexion con) throws Exception 
	{	
		canal= socket;
		out= new ObjectOutputStream(canal.getOutputStream());
		in= new ObjectInputStream(canal.getInputStream());	
		conexionDB=con;
	}
	
	public void atenderCliente() 
	{		
		boolean continua=false;
		do
		{
			try
			{
				Object objeto= in.readObject();
				SentenciaSQL sentenciaSQL= (SentenciaSQL) objeto;
				String linea= sentenciaSQL.getSentencia();
				if(linea.substring(0, 6).equals(CONSULTA))
				{
					ResultSet rs= conexionDB.ejecutaConsulta(linea);
					if(rs!=null)
					{
						MyList<IDto> lista= ReflectionDTO.listar(rs);
						out.writeObject(lista);
					}			
				}
				else // en caso de una actualizacion
				{
					boolean ejecuto = conexionDB.ejecutaActualizacion(linea);
					if(ejecuto)
					{
						sentenciaSQL.setSentencia("Se ha ejecutado");
						out.writeObject(sentenciaSQL);						
					}
					else
					{
						sentenciaSQL.setSentencia("No ha ejecutado");
						out.writeObject(sentenciaSQL);
					}
				}
				
			}
			catch (SocketException e) 
			{
				continua= true;
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				continua= true;
			} 
			catch (IOException e) 
			{
				continua= true;
				e.printStackTrace();
			}
			finally 
			{
				conexionDB.closeResultAndStatement();
			}
		}
		while(!continua);
	}
	public void run()
	{
		atenderCliente();
	}
}
