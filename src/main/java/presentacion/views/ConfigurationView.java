package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dto.ConfigDatabaseDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JCheckBox;

public class ConfigurationView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4088765082266597046L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textUser;
	private JTextField textPassword;
	private JTextField textIp;
	private JTextField textPort;
	private JButton okButton;
	private JCheckBox chckbxIsLocalhost;
	private static ConfigurationView instance;
	
	public static ConfigurationView getInstance() {
		if(instance == null) instance = new ConfigurationView();
		return instance;
	}	
	
	private ConfigurationView() {
		setTitle("Database engine parameters");
		setResizable(false);
		setBounds(100, 100, 362, 270);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUser = new JLabel("User");
			lblUser.setBounds(13, 16, 70, 15);
			contentPanel.add(lblUser);
		}
		{
			textUser = new JTextField("");
			textUser.setBounds(118, 11, 218, 24);
			contentPanel.add(textUser);
			textUser.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Password");
			lblNewLabel.setBounds(13, 53, 70, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			textPassword = new JTextField("");
			textPassword.setBounds(118, 46, 218, 24);
			contentPanel.add(textPassword);
			textPassword.setColumns(10);
		}
		{
			JLabel IP = new JLabel("IP");
			IP.setBounds(13, 129, 70, 15);
			contentPanel.add(IP);
		}
		{
			textIp = new JTextField("");
			textIp.setBounds(118, 120, 218, 24);
			contentPanel.add(textIp);
			textIp.setColumns(10);
		}
		{
			JLabel lblPort = new JLabel("Port");
			lblPort.setBounds(13, 164, 70, 15);
			contentPanel.add(lblPort);
		}
		{
			textPort = new JTextField("");
			textPort.setBounds(118, 159, 218, 24);
			contentPanel.add(textPort);
			textPort.setColumns(10);
		}
		
		chckbxIsLocalhost = new JCheckBox("Is localhost");
		chckbxIsLocalhost.setBounds(115, 90, 99, 23);
		contentPanel.add(chckbxIsLocalhost);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		});
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setModal(true);
	}
	
	public void open() {
		setVisible(true);
	}
	
	public void close() {
		this.dispose();
	}
	
	public void clearData() {
		this.textUser.setText("");
		this.textPassword.setText("");
		this.textIp.setText("");
		this.textPort.setText("");
	}
	
	public void setData(ConfigDatabaseDTO dto) {
		this.textUser.setText(dto.getUser());
		this.textPassword.setText(dto.getPassword());
		this.textIp.setText(dto.getIp());
		this.textPort.setText(dto.getPort());
	}
		
	public ConfigDatabaseDTO getData() {
		return new ConfigDatabaseDTO().user(textUser.getText()).password(textPassword.getText())
				.ip(textIp.getText())
				.port(textPort.getText())
				.isLocalhost(this.chckbxIsLocalhost.isSelected());
	}
	
	public void setActionSave(ActionListener listener) {
		okButton.addActionListener(listener);
	}
		
	public void setActionLocalhost(ActionListener listener) {
		this.chckbxIsLocalhost.addActionListener(listener);
	}

	public void clearIp() {
		this.textIp.setText("");		
	}
	
	public void disableIp() {
		this.textIp.setEnabled(false);
	}

	public void enableIp() {
		this.textIp.setEnabled(true);
	}
}
