package mensaje;

import java.io.Serializable;

public class SentenciaSQL implements Serializable
{
	private static final long serialVersionUID = 447413656289813155L;
	
	private String sentencia;

	public SentenciaSQL(String sentencia) 
	{
		this.sentencia = sentencia;
	}

	public String getSentencia() 
	{
		return sentencia;
	}

	public void setSentencia(String sentencia) 
	{
		this.sentencia = sentencia;
	}
	
	
}
