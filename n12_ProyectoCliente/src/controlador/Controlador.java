package controlador;

import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit.Parser;

import InterfazCliente.InterfazPrincipal;
import MyList.MyList;
import mundo.DTO.IDto;
import mundo.DTO.PropietarioDTO;
import mundo.DTO.VehiculoDTO;
import mundo.SocketCliente.SocketCliente;
import mundo.facade.PropietarioFacade;
import mundo.facade.VehiculoFacade;

public class Controlador implements ActionListener
{
	private VehiculoFacade df;
	private PropietarioFacade pf;
	private SocketCliente sc;
	private InterfazPrincipal ip;
	public Controlador(InterfazPrincipal ip) throws UnknownHostException, IOException, InstantiationException, IllegalAccessException 
	{
		sc=new SocketCliente();
		pf= new PropietarioFacade(sc);
		df= new VehiculoFacade(sc);
		this.ip=ip;
		ip.setVisible(true);
		ip.buttAgregar.addActionListener(this);
	    ip.buttListar.addActionListener(this);
	    ip.buttBuscar.addActionListener(this);
	    ip.buttEliminar.addActionListener(this);
	    ip.buttActualizar.addActionListener(this);
	    ip.buttAgregarVehiculo.addActionListener(this);
	    ip.buttListavehiculos.addActionListener(this);
	    ip.buttBuscarVehiculo.addActionListener(this);
	    ip.buttEliminarVehiculo.addActionListener(this);
	    ip.buttActualizarVehiculo.addActionListener(this);
	    ip.exitMenuItem.addActionListener(this);
	    String [] nombreColumnasPropietario= {"Cedula","Primer Nombre","Segundo Nombre","PrimerApellido","Segundo Apelldio"};
	    ip.asignarModelosTablaPropietarios(nombreColumnasPropietario);

	    String [] nombreColumnasVehiculo= {"Placa","Cedula ","Marca","Modelo","Tipo","Cantidad pasajeros"};
	    ip.asignarModelosTablaVehiculos(nombreColumnasVehiculo);

	}
	public void agregarPropietario(String cedulaPropietario, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido)
	{
		PropietarioDTO p=new PropietarioDTO(cedulaPropietario, primerNombre, segundoNombre, primerApellido, segundoApellido);
		boolean agrego= pf.insertar(p);
		if(agrego)
		{
			ip.mostrarMensaje("Se ha agregado el propietario");
			listarPropietarios(ip.tablaPropietarios);
		}
		else
		ip.mostrarMensaje("No se agrego el propietario");
	}
	public void listarPropietarios(JTable jp)
	{
		PropietarioDTO p= new PropietarioDTO();
		MyList<PropietarioDTO> lista= pf.consultarTodos(p);
		if(lista!=null)
		{
			Iterator<PropietarioDTO> iter= lista.iterator();
			ip.modelTablaPropietarios.setRowCount(0);
			while(iter.hasNext())
			{
				PropietarioDTO prop= iter.next();
				Object [] datos= new Object[5];
				datos[0]=prop.getCedulaPropietario();
				datos[1]=prop.getPrimerNombre();
				datos[2]=prop.getSegundoNombre();
				datos[3]=prop.getPrimerApellido();
				datos[4]=prop.getSegundoApellido();	
				ip.modelTablaPropietarios.addRow(datos);
			}
		}
	}
	public void eliminaPropietario()
	{
		int fila= ip.tablaPropietarios.getSelectedRow();
		if(fila>=0)
		{
			String cedula= (String) ip.modelTablaPropietarios.getValueAt(fila, 0);
			PropietarioDTO p= new PropietarioDTO(cedula);
			boolean elimino=pf.eliminar(p);
			if(elimino)
			{
				ip.mostrarMensaje("Se ha eliminado");
				listarPropietarios(ip.tablaPropietarios);
			}
			else
			{
				ip.mostrarMensaje("No se ha eliminado");
			}
		}
		else
		{
			ip.mostrarMensaje("No ha seleccionado un propietario");
		}		
	}
	public void buscarPropietario()
	{
		String cedula= ip.solicitarDato("Ingrese la cedula");
		if(!cedula.equals(""))
		{
			PropietarioDTO d= new PropietarioDTO(cedula);
			d=pf.consultarPk(d);
			if(d==null)
			{
				ip.mostrarMensaje("El propietario no existe");
			}
			else
			{
				ip.txtCedula.setText(d.getCedulaPropietario());
				ip.txtPrimerNombre.setText(d.getPrimerNombre());
				ip.txtSegundoNombre.setText(d.getSegundoNombre());
				ip.txtPrimerapellido.setText(d.getPrimerApellido());
				ip.txtSegundoApellido.setText(d.getSegundoApellido());			
			}
		}
		else
		{
			ip.mostrarMensaje("No ha ingresado la cedula/n"
					+ "la cedula debe tener maximo 15 caracteres");
		}
	}
	public void actualizarPropietario(String cedulaPropietario, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido)
	{
		PropietarioDTO p=new PropietarioDTO(cedulaPropietario, primerNombre, segundoNombre, primerApellido, segundoApellido);
		boolean actualizo= pf.actualizar(p);
		if(actualizo)
		{
			ip.mostrarMensaje("Se actualizaron los datos");
			listarPropietarios(ip.tablaPropietarios);
		}
		else
		{
			ip.mostrarMensaje("No se actualizaron los datos");
		}
	}
	
	public void agregarVehiculo(String placa, String cedulaPropietario, String marca, Long modelo, String tipoVehiculo,
			int cantidadPasajero)
	{
		VehiculoDTO v= new VehiculoDTO(placa, cedulaPropietario, marca, modelo, tipoVehiculo, cantidadPasajero);
		boolean agrego=df.insertar(v);
		if(agrego)
			{
				ip.mostrarMensaje("Se ha agregado el vehiculo");
				listarVehiculos(ip.tablaVehiculos);
			}
			else
			ip.mostrarMensaje("No se agrego el vehiculo");
	}
	
	
	public void listarVehiculos(JTable jp)
	{
		VehiculoDTO v= new VehiculoDTO();
		MyList<VehiculoDTO> lista= df.consultarTodos(v);
		if(lista!=null)
		{
			Iterator<VehiculoDTO> iter= lista.iterator();
			ip.modelTablaVehiculos.setRowCount(0);
			while(iter.hasNext())
			{
				VehiculoDTO prop= iter.next();
				Object [] datos= new Object[6];
				datos[0]=prop.getPlaca();
				datos[1]=prop.getCedulaPropietario();
				datos[2]=prop.getMarca();
				datos[3]=prop.getModelo();
				datos[4]=prop.getTipoVehiculo();
				datos[5]=prop.getCantidadPasajero();
				ip.modelTablaVehiculos.addRow(datos);
			}
		}
	}
	public void buscarVehiculo()
	{
		String placa= ip.solicitarDato("Ingrese la placa");
		if(!placa.equals(""))
		{
			VehiculoDTO d= new VehiculoDTO(placa);
			d=df.consultarPk(d);
			if(d==null)
			{
				ip.mostrarMensaje("El vehiculo no existe");
			}
			else
			{
				ip.txtPlaca.setText(d.getPlaca());
				ip.txtCedulaPropietario.setText(d.getCedulaPropietario());
				ip.txtMarca.setText(d.getMarca());
				ip.txtModelo.setText(Long.toString(d.getModelo()));
				ip.txtTipo.setText(d.getTipoVehiculo());
				ip.txtpasajeros.setText(Integer.toString(d.getCantidadPasajero()));
			}
		}
		else
		{
			ip.mostrarMensaje("No ha ingresado la placa/n"
					+ "la placa debe tener maximo 15 caracteres");
		}
	}
	public void eliminarVehiculo()
	{
		int fila= ip.tablaVehiculos.getSelectedRow();
		if(fila>=0)
		{
			String placa= (String) ip.modelTablaVehiculos.getValueAt(fila, 0);
			VehiculoDTO v= new VehiculoDTO(placa);
			boolean elimino=df.eliminar(v);
			if(elimino)
			{
				ip.mostrarMensaje("Se ha eliminado");
				listarVehiculos(ip.tablaVehiculos);
			}
			else
			{
				ip.mostrarMensaje("No se ha eliminado");
			}
		}
		else
		{
			ip.mostrarMensaje("No ha seleccionado un Vehiculo");
		}	
	}
	public void actualizarVehiculo(String placa, String cedulaPropietario, String marca, Long modelo, String tipoVehiculo,
			int cantidadPasajero)
	{
		VehiculoDTO v= new VehiculoDTO(placa, cedulaPropietario, marca, modelo, tipoVehiculo, cantidadPasajero);
		boolean actualizo=df.actualizar(v);
		if(actualizo)
		{
			ip.mostrarMensaje("Se ha actualizado");
			listarVehiculos(ip.tablaVehiculos);
		}
		else
		{
			ip.mostrarMensaje("No se ha actualizado");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comand= e.getActionCommand();
		if(comand.equals(InterfazPrincipal.AGREGARPROPIETARIO))
		{
			String cedulaPropietario= ip.txtCedula.getText();
			String primerNombre=ip.txtPrimerNombre.getText();
			String segundoNombre=ip.txtSegundoNombre.getText();
			String primerApellido= ip.txtPrimerapellido.getText();
			String segundoApellido= ip.txtSegundoApellido.getText();
			agregarPropietario(cedulaPropietario, primerNombre, segundoNombre, primerApellido, segundoApellido);
		}
		else if(comand.equals(InterfazPrincipal.lISTAR))
		{
			listarPropietarios(ip.tablaPropietarios);
		}
		else if(comand.equals(InterfazPrincipal.BUSCAR))
		{
			buscarPropietario();
		}
		else if(comand.equals(InterfazPrincipal.ACTUALIZAR))
		{
			String cedulaPropietario= ip.txtCedula.getText();
			String primerNombre=ip.txtPrimerNombre.getText();
			String segundoNombre=ip.txtSegundoNombre.getText();
			String primerApellido= ip.txtPrimerapellido.getText();
			String segundoApellido= ip.txtSegundoApellido.getText();
			actualizarPropietario(cedulaPropietario, primerNombre, segundoNombre, primerApellido, segundoApellido);			
		}
		else if(comand.equals(InterfazPrincipal.ElIMiNAR))
		{
			eliminaPropietario();
		}		
		
		else if(comand.equals(InterfazPrincipal.AGREGARVEHICULO))
		{
			try
			{
				String placa= ip.txtPlaca.getText();
				String cedula=ip.txtCedulaPropietario.getText();
				String marca=ip.txtMarca.getText();
				Long modelo= Long.valueOf(ip.txtModelo.getText());
				String tipo= ip.txtTipo.getText();
				int pasajeros= Integer.parseInt(ip.txtpasajeros.getText());
				agregarVehiculo(placa, cedula, marca, modelo, tipo, pasajeros);
			}
			catch(NumberFormatException number)
			{
				ip.mostrarMensaje("El modelo y la cantidad de pasajeros son enteros");
			}	
		}
		else if(comand.equals(InterfazPrincipal.lISTARVEHICULO))
		{
			listarVehiculos(ip.tablaVehiculos);
		}
		else if(comand.equals(InterfazPrincipal.BUSCARVEHICULO))
		{
			buscarVehiculo();	
		}
		else if(comand.equals(InterfazPrincipal.ACTUALIZARVEHICULO))
		{			
			try
			{
				String placa= ip.txtPlaca.getText();
				String cedula=ip.txtCedulaPropietario.getText();
				String marca=ip.txtMarca.getText();
				Long modelo= Long.valueOf(ip.txtModelo.getText());
				int pasajeros= Integer.parseInt(ip.txtpasajeros.getText());
				String tipo= ip.txtTipo.getText();			
				actualizarVehiculo(placa, cedula, marca, modelo, tipo, pasajeros);
			}
			catch(NumberFormatException number)
			{
				ip.mostrarMensaje("El modelo y la cantidad de pasajeros son enteros");
			}						
		}
		else if(comand.equals(InterfazPrincipal.ElIMiNARVEHICULO))
		{
			eliminarVehiculo();
		}
		else if(comand.equals(InterfazPrincipal.SALIR))
		{
			ip.salir();
			try {
				sc.cerrarConexiones();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sc=null;
		}
	}
	public static void main(String[] args) 
	{
		InterfazPrincipal ip;
		Controlador ss;
		try 
		{
			ip= new InterfazPrincipal();
			System.out.println();
			ss= new Controlador(ip);		
		}		
		catch (SocketException e) 
		{
			JOptionPane.showMessageDialog(null, "Se ha perdido la conexion con el servidor");
			System.exit(0);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

}
