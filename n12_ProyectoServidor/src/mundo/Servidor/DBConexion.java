/**
 * 
 */
package mundo.Servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.firebirdsql.jdbc.parser.JaybirdSqlParser.statement_return;

import mensaje.Usuario;

/**
 * @author SALAS
 *
 */
public class DBConexion 
{
	private Connection  con;
	private Statement st;
	private ResultSet rs;
	/**
	 * Crea una conexion a la base de datos
	 * @param url ruta de la base de datos. url != null
	 * @param usuario usuario a conectar en la base de datos. usuario != null
	 * @param clave password del usuario. clave != null
	 * @throws SQLException 
	 * @throws ServidorException 
	 */
	public DBConexion(String url, String usuario, String clave) throws SQLException 
	{
		con= DriverManager.getConnection("jdbc:firebirdsql://localhost:3050/"+url, usuario, clave);
	}

	public Connection getCon() 
	{
		return con;
	}
	public void setCon(Connection con) 
	{
		this.con = con;
	}
	/**
	 * Ejecuta una instrucci�n dml y retorna la consulta
	 * @param instruccionSql consulta a ejecutar. instruccionSql != null 
	 * @return El resultado de la consulta. 
	 * @throws ServidorException 
	 */
	public ResultSet ejecutaConsulta(String instruccionSql)
	{
		rs = null;
		st = null;
		try 
		{
			st = con.createStatement();
			rs = st.executeQuery(instruccionSql);
		} 
		catch (SQLException e) 
		{
			rs=null;
		}
	
		return rs;
	}
	/**
	 * Ejecuta una instrucci�n dml de actualizaci�n y retorna falso o verdadero
	 * @param instruccionSql consulta a ejecutar. instruccionSql != null 
	 * @return true si se efectuo la actualizaci�n de lo contrario false. 
	 * @throws ServidorException 
	 */
	public boolean ejecutaActualizacion(String instruccionSql)
	{
		int filas=0;
		Statement st= null;
		try 
		{
			st = con.createStatement();
			filas = st.executeUpdate(instruccionSql);
			if(filas==0)
			{
				return false;
			}
			return true;
		} 
		catch (SQLException e)
		{
			return false;
		}
	
	}
	
	public void closeResultAndStatement()
	{
		try
		{
			if(st!=null)
			{
				st.close();
			}
			if(rs!=null)
			{
				rs.close();
			}
		}	
		catch  (SQLException e)
		{
			e.printStackTrace();
		}
	}

    public boolean estaCerrada() 
    {
        try 
        {
            return con.isClosed();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void cerrarConexion()
    {
    	closeResultAndStatement();
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
