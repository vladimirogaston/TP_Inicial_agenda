package presentacion.views.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import repositories.jdbc.Conexion;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

public class WorkbenchView {
	
	static final String[] nombreColumnas = { "Nombre y apellido", "Telefono", "Email", "Fecha cumple", "Tipo", "Calle", "Altura",
			"Piso", "Dpto", "Localidad", "Provincia", "Pais", "Equipo", "CodigoPostal", "ID" };
	
	JFrame frame;
	JTable tablaPersonas;
	DefaultTableModel modelPersonas;
	JButton btnAgregar;
	JButton btnBorrar;
	JButton btnReporte;
	JButton btnEditar;
	JPanel panel_1;
	JMenuItem mntmNewMenuItemPaises;
	JMenuItem mntmNewMenuItemProvincias;
	JMenuItem mntmNewMenuItemLocalidades;
	JMenuItem mntmNewMenuItemTipos;
	static WorkbenchView vista;
	
	public static WorkbenchView getInstance() {
		if(vista == null) vista = new WorkbenchView();
		return vista;
	}
	
	@SuppressWarnings("serial")
	WorkbenchView() {
		super();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1132, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Agenda");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(0, 39, 1123, 270);
		panel.add(spPersonas);

		modelPersonas = new DefaultTableModel(null, nombreColumnas) {
			public boolean isCellEditable(int row, int column) { return false; } 
		};
		tablaPersonas = new JTable(modelPersonas);
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
	
		spPersonas.setViewportView(tablaPersonas);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(0, 308, 1123, 39);
		panel.add(panel_1);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_1.add(toolBar);

		btnAgregar = new JButton("Agregar");
		toolBar.add(btnAgregar);

		btnBorrar = new JButton("Borrar");
		toolBar.add(btnBorrar);

		btnEditar = new JButton("Editar");
		toolBar.add(btnEditar);

		btnReporte = new JButton("Reporte");
		toolBar.add(btnReporte);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1123, 39);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Opciones");
		menuBar.add(mnNewMenu);

		mntmNewMenuItemPaises = new JMenuItem("Paises");
		mnNewMenu.add(mntmNewMenuItemPaises);
		
		mntmNewMenuItemProvincias = new JMenuItem("Provincias");
		mnNewMenu.add(mntmNewMenuItemProvincias);
		
		mntmNewMenuItemLocalidades = new JMenuItem("Localidades");
		mnNewMenu.add(mntmNewMenuItemLocalidades);

		mntmNewMenuItemTipos = new JMenuItem("Tipos");
		mnNewMenu.add(mntmNewMenuItemTipos);
	}

	public void open() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}
	
	public JMenuItem getMntmNewMenuItemPaises() {
		return mntmNewMenuItemPaises;
	}

	public JMenuItem getMntmNewMenuItemProvincias() {
		return mntmNewMenuItemProvincias;
	}

	public JMenuItem getMntmNewMenuItemLocalidades() {
		return mntmNewMenuItemLocalidades;
	}

	public JMenuItem getMntmNewMenuItemTipos() {
		return mntmNewMenuItemTipos;
	}
}