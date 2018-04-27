package mundo.DTO;

import java.io.Serializable;

public class VehiculoDTO implements IDto , Serializable
{
	private static final long serialVersionUID = 447413656289813155L;
	private String placa;
	private String cedulaPropietario;
	private String marca;
	private Long modelo;
	private String tipoVehiculo;
	private int cantidadPasajero;
	public VehiculoDTO(String placa, String cedulaPropietario, String marca, Long modelo, String tipoVehiculo,
			int cantidadPasajero) 
	{
		this.placa = placa;
		this.cedulaPropietario = cedulaPropietario;
		this.marca = marca;
		this.modelo = modelo;
		this.tipoVehiculo = tipoVehiculo;
		this.cantidadPasajero = cantidadPasajero;
	}
	public VehiculoDTO() 
	{
		// TODO Auto-generated constructor stub
	}
	public VehiculoDTO(String placa) 
	{
		this.placa=placa;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCedulaPropietario() {
		return cedulaPropietario;
	}
	public void setCedulaPropietario(String cedulaPropietario) {
		this.cedulaPropietario = cedulaPropietario;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Long getModelo() {
		return modelo;
	}
	public void setModelo(Long modelo) {
		this.modelo = modelo;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public int getCantidadPasajero() {
		return cantidadPasajero;
	}
	public void setCantidadPasajero(int cantidadPasajero) {
		this.cantidadPasajero = cantidadPasajero;
	}
	@Override
	public String toString() 
	{
		return "VehiculoDTO [placa=" + placa + ", cedulaPropietario=" + cedulaPropietario + ", marca=" + marca
				+ ", modelo=" + modelo + ", tipoVehiculo=" + tipoVehiculo + ", cantidadPasajero=" + cantidadPasajero
				+ "]";
	}
	@Override
	public String insertar() 
	{
		return "INSERT INTO VEHICULO (PLACA, CEDULAPROPIETARIO, MARCA, MODELO, TIPOVEHICULO, CANTIDADPASAJERO) VALUES ( '"+ placa+"', '"+ cedulaPropietario+"', '" + marca+ "', " +modelo+", '"+tipoVehiculo+"', " + cantidadPasajero+")";
	}
	@Override
	public String eliminar() 
	{
		return "DELETE FROM VEHICULO WHERE PLACA = '" +placa+"'";
	}
	@Override
	public String actualizar() 
	{
		return "UPDATE VEHICULO SET PLACA = '" + placa+"', CEDULAPROPIETARIO = '" + cedulaPropietario+"',  MARCA = '"+ marca+"', MODELO = "+modelo+", TIPOVEHICULO = '"+ tipoVehiculo+"', CANTIDADPASAJERO = "+ cantidadPasajero+" WHERE PLACA = '"+placa+"'";
	}
	@Override
	public String consultarPk() 
	{
		return "select * from VEHICULO WHERE PLACA = '" +placa+"'";
	}
	@Override
	public String consultarTodos() 
	{
		return "select * from VEHICULO";
	}
	
	
	
}
