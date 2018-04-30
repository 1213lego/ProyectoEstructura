package mundo.Servidor.Reflection;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import mundo.DTO.IDto;
import mundo.Servidor.DBConexion;
import MyList.*;

public class ReflectionDTO 
{
	public static final String PAQUETE= "mundo.DTO.";
	
	public  MyList<IDto> listar(ResultSet rs)  
	{
		MyList<IDto> result= new MyList<>();		
		try
		{
			ResultSetMetaData metaData= rs.getMetaData();
			int numColumnas= metaData.getColumnCount();
			String nombreTabla= metaData.getTableName(1);
			nombreTabla= nombreTabla.substring(0, 1)+ nombreTabla.substring(1).toLowerCase();
			String nombreClase= PAQUETE + nombreTabla+"DTO";
			while(rs.next())
			{
				IDto actual= (IDto) Class.forName(nombreClase).newInstance();
				for(int i= 1; i<= numColumnas ; i++)
				{
					String nombreColumnaActual= metaData.getColumnName(i);
					Object valorActual= rs.getObject(i);
					Method [] metodos= actual.getClass().getMethods();
					Method metodoActual= buscarElMetodo(nombreColumnaActual, metodos);
					if(metodoActual!=null)
					{
						metodoActual.invoke(actual, valorActual);
					}
				}
				result.agregar(actual);
			}		
		}
		catch (Exception ee) 
		{
			ee.printStackTrace();
		}
		return result;
	}
	
	private Method buscarElMetodo(String nombreColumna, Method [] metodos)
	{
		Method result=null;		
		nombreColumna= nombreColumna.replaceAll("_", "");
		for (Method method : metodos) 
		{
			if(method.getName().equalsIgnoreCase("set"+nombreColumna))
			{
				result= method;
				break;
			}
		}		
		return result;
	}
}
