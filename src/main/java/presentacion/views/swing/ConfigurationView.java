package presentacion.views.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.ConfigDatabaseDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
	private JButton cancelButton;
	private JButton okButton;
	private JButton btnTest;
	private JLabel testConectivityStatus;

	public ConfigurationView() {
		setResizable(false);
		setBounds(100, 100, 368, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUser = new JLabel("User");
			lblUser.setBounds(13, 40, 70, 15);
			contentPanel.add(lblUser);
		}
		{
			textUser = new JTextField("");
			textUser.setBounds(118, 38, 245, 19);
			contentPanel.add(textUser);
			textUser.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Password");
			lblNewLabel.setBounds(13, 66, 70, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			textPassword = new JTextField("");
			textPassword.setBounds(118, 64, 245, 19);
			contentPanel.add(textPassword);
			textPassword.setColumns(10);
		}
		{
			JLabel IP = new JLabel("IP");
			IP.setBounds(13, 92, 70, 15);
			contentPanel.add(IP);
		}
		{
			textIp = new JTextField("");
			textIp.setBounds(118, 90, 245, 19);
			contentPanel.add(textIp);
			textIp.setColumns(10);
		}
		{
			JLabel lblPort = new JLabel("Port");
			lblPort.setBounds(13, 118, 70, 15);
			contentPanel.add(lblPort);
		}
		{
			textPort = new JTextField("");
			textPort.setBounds(118, 116, 245, 19);
			contentPanel.add(textPort);
			textPort.setColumns(10);
		}
		{
			btnTest = new JButton("Test");
			btnTest.setBounds(13, 142, 70, 25);
			contentPanel.add(btnTest);
		}
		{
			testConectivityStatus = new JLabel("");
			testConectivityStatus.setBounds(118, 209, 245, 0);
			contentPanel.add(testConectivityStatus);
		}
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
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setModal(true);
	}
	
	public void open() {
		setVisible(true);
	}
	
	public void close() {
		this.dispose();
	}

	public void setData(String status) {
		this.testConectivityStatus.setText(status);
	}
	
	public void clearData() {
		this.textUser.setText("");
		this.textPassword.setText("");
		this.textIp.setText("");
		this.textPort.setText("");
		this.testConectivityStatus.setText("");
	}
	
	public void setData(ConfigDatabaseDTO dto) {
		assert dto != null;
		this.textUser.setText(dto.getUser());
		this.textPassword.setText(dto.getPassword());
		this.textIp.setText(dto.getIp());
		this.textPort.setText(dto.getPort());
	}
		
	public ConfigDatabaseDTO getData() {
		return new ConfigDatabaseDTO(textUser.getText(), textPassword.getText(), textIp.getText(), textPort.getText());
	}
	
	public void setActionSave(ActionListener listener) {
		assert listener != null;
		okButton.addActionListener(listener);
	}
	
	public void setActionCancel(ActionListener listener) {
		assert listener != null;
		cancelButton.addActionListener(listener);
	}
	
	public void setActionTest(ActionListener listener) {
		assert listener != null;
		btnTest.addActionListener(listener);
	}
}
