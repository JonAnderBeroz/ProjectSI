package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.objectdb.o.JCM;

import businessLogic.BLFacade;
import domain.Country;
import exceptions.invalidID;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class EditUserGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JLabel lblCountry;
	private JLabel lblPhoneNumber = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PhoneNumber") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
	private JLabel lblBirthdate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Birthdate") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
	private JLabel lblCity = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("City") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
	
	private JTextField idField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;

	private JRadioButton rdbtnUser = new JRadioButton("User");
	private JRadioButton rdbtnAdmin = new JRadioButton("Admin");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField phnTextField;
	private JTextField phnPrefixTextField;
	private JTextField cityTextField;
	
	
	private JComboBox<String> comboBoxNat = new JComboBox<String>();
	private JComboBox<String> comboBoxDay = new JComboBox<String>();
	private JComboBox<String> comboBoxMonth = new JComboBox<String>();
	private JComboBox<String> comboBoxYear = new JComboBox<String>();
	private JTextField addressTextField;

	/**
	 * Create the frame.
	 */
	public EditUserGUI(String ID, String name, String surname, String email, String country, String city,String address, String phonenumber, String birthdate, String status) {
		setTitle("Edit user information");
		setResizable(false);
		
		idField = new JTextField(ID);
		nameField = new JTextField(name);
		surnameField = new JTextField(surname);
		emailField = new JTextField(email);
		cityTextField = new JTextField(city);
		addressTextField = new JTextField(address);
		String[] phone = phonenumber.split(" ");
		phnPrefixTextField = new JTextField(phone[0]);
		phnTextField = new JTextField(phone[1]);


		if(status.equals("Admin.")) {
			rdbtnAdmin.setSelected(true);
		}
		else {
			rdbtnUser.setSelected(true);
		}
		
		
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 20, 20, 15, 93, -100, 24, 40, 40, 50, 30, 20, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 30, 40, 40, 40, 40, 0, 0, 7, 0, 0, 0, 20, 0, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblEditUserInformation = new JLabel("Edit user information");
		lblEditUserInformation.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblEditUserInformation = new GridBagConstraints();
		gbc_lblEditUserInformation.anchor = GridBagConstraints.WEST;
		gbc_lblEditUserInformation.gridwidth = 11;
		gbc_lblEditUserInformation.insets = new Insets(0, 0, 5, 0);
		gbc_lblEditUserInformation.gridx = 1;
		gbc_lblEditUserInformation.gridy = 1;
		panel.add(lblEditUserInformation, gbc_lblEditUserInformation);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 3;
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 3;
		panel.add(lblUsername, gbc_lblUsername);
		

		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.gridwidth = 5;
		gbc_idField.insets = new Insets(0, 0, 5, 5);
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.gridx = 4;
		gbc_idField.gridy = 3;
		panel.add(idField, gbc_idField);
		idField.setColumns(10);
		
		JLabel usernameError = new JLabel("ID already in use");
		usernameError.setForeground(Color.RED);
		GridBagConstraints gbc_usernameError = new GridBagConstraints();
		gbc_usernameError.anchor = GridBagConstraints.WEST;
		gbc_usernameError.gridwidth = 3;
		gbc_usernameError.insets = new Insets(0, 0, 5, 0);
		gbc_usernameError.gridx = 9;
		gbc_usernameError.gridy = 3;
		panel.add(usernameError, gbc_usernameError);
		usernameError.setVisible(false);;
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.gridwidth = 3;
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		panel.add(lblName, gbc_lblName);
		
		
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 5;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 4;
		gbc_nameField.gridy = 4;
		panel.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.gridwidth = 3;
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 5;
		panel.add(lblSurname, gbc_lblSurname);
		
	
		surnameField.setColumns(10);
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.gridwidth = 5;
		gbc_surnameField.insets = new Insets(0, 0, 5, 5);
		gbc_surnameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameField.gridx = 4;
		gbc_surnameField.gridy = 5;
		panel.add(surnameField, gbc_surnameField);
		
		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.gridwidth = 3;
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 6;
		panel.add(lblEmail, gbc_lblEmail);
		

		emailField.setColumns(10);
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridwidth = 7;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 4;
		gbc_emailField.gridy = 6;
		panel.add(emailField, gbc_emailField);
		
		lblCountry = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Country") + ":");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.EAST;
		gbc_lblCountry.gridwidth = 3;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 1;
		gbc_lblCountry.gridy = 7;
		panel.add(lblCountry, gbc_lblCountry);
		
		GridBagConstraints gbc_comboBoxNat = new GridBagConstraints();
		gbc_comboBoxNat.gridwidth = 5;
		gbc_comboBoxNat.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxNat.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxNat.gridx = 4;
		gbc_comboBoxNat.gridy = 7;
		panel.add(comboBoxNat, gbc_comboBoxNat);
		

		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.EAST;
		gbc_lblCity.gridwidth = 3;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 1;
		gbc_lblCity.gridy = 8;
		panel.add(lblCity, gbc_lblCity);
		
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.gridwidth = 5;
		gbc_cityTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityTextField.gridx = 4;
		gbc_cityTextField.gridy = 8;
		panel.add(cityTextField, gbc_cityTextField);
		cityTextField.setColumns(10);
		
		JLabel lblAddress = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Address"));
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.gridwidth = 3;
		gbc_lblAddress.anchor = GridBagConstraints.EAST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 9;
		panel.add(lblAddress, gbc_lblAddress);
		
		GridBagConstraints gbc_addressTextField = new GridBagConstraints();
		gbc_addressTextField.gridwidth = 5;
		gbc_addressTextField.insets = new Insets(0, 0, 5, 5);
		gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTextField.gridx = 4;
		gbc_addressTextField.gridy = 9;
		panel.add(addressTextField, gbc_addressTextField);
		addressTextField.setColumns(10);
		
		
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.gridwidth = 3;
		gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber.gridx = 1;
		gbc_lblPhoneNumber.gridy = 10;
		panel.add(lblPhoneNumber, gbc_lblPhoneNumber);
		
		GridBagConstraints gbc_phnPrefixTextField = new GridBagConstraints();
		gbc_phnPrefixTextField.insets = new Insets(0, 0, 5, 5);
		gbc_phnPrefixTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phnPrefixTextField.gridx = 4;
		gbc_phnPrefixTextField.gridy = 10;
		panel.add(phnPrefixTextField, gbc_phnPrefixTextField);
		phnPrefixTextField.setColumns(10);
		
		GridBagConstraints gbc_phnTextField = new GridBagConstraints();
		gbc_phnTextField.gridwidth = 4;
		gbc_phnTextField.insets = new Insets(0, 0, 5, 5);
		gbc_phnTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phnTextField.gridx = 5;
		gbc_phnTextField.gridy = 10;
		panel.add(phnTextField, gbc_phnTextField);
		phnTextField.setColumns(10);
		
		
		GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
		gbc_lblBirthdate.anchor = GridBagConstraints.EAST;
		gbc_lblBirthdate.gridwidth = 3;
		gbc_lblBirthdate.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthdate.gridx = 1;
		gbc_lblBirthdate.gridy = 11;
		panel.add(lblBirthdate, gbc_lblBirthdate);
		

		GridBagConstraints gbc_comboBoxDay = new GridBagConstraints();
		gbc_comboBoxDay.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDay.gridx = 4;
		gbc_comboBoxDay.gridy = 11;
		panel.add(comboBoxDay, gbc_comboBoxDay);
		
		GridBagConstraints gbc_comboBoxMonth = new GridBagConstraints();
		gbc_comboBoxMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMonth.gridx = 5;
		gbc_comboBoxMonth.gridy = 11;
		panel.add(comboBoxMonth, gbc_comboBoxMonth);
		
		GridBagConstraints gbc_comboBoxYear = new GridBagConstraints();
		gbc_comboBoxYear.gridwidth = 3;
		gbc_comboBoxYear.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYear.gridx = 6;
		gbc_comboBoxYear.gridy = 11;
		panel.add(comboBoxYear, gbc_comboBoxYear);
		
		JLabel lblRights = new JLabel("Rights:");
		GridBagConstraints gbc_lblRights = new GridBagConstraints();
		gbc_lblRights.gridwidth = 3;
		gbc_lblRights.anchor = GridBagConstraints.EAST;
		gbc_lblRights.insets = new Insets(0, 0, 5, 5);
		gbc_lblRights.gridx = 1;
		gbc_lblRights.gridy = 12;
		panel.add(lblRights, gbc_lblRights);
		
		
		GridBagConstraints gbc_rdbtnUser = new GridBagConstraints();
		gbc_rdbtnUser.gridwidth = 2;
		gbc_rdbtnUser.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnUser.gridx = 4;
		gbc_rdbtnUser.gridy = 12;
		buttonGroup.add(rdbtnUser);
		panel.add(rdbtnUser, gbc_rdbtnUser);
		

		GridBagConstraints gbc_rdbtnAdmin = new GridBagConstraints();
		gbc_rdbtnAdmin.gridwidth = 3;
		gbc_rdbtnAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAdmin.gridx = 6;
		gbc_rdbtnAdmin.gridy = 12;
		buttonGroup.add(rdbtnAdmin);
		panel.add(rdbtnAdmin, gbc_rdbtnAdmin);

		
		JButton btnSave = new JButton("Save changes");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameError.setVisible(false);
				try {
					
					Calendar cld =  Calendar.getInstance();
					
					String newId = idField.getText();
					String newName = nameField.getText();
					String newSurname = surnameField.getText();
					String newEmail = emailField.getText();
					
					Country newnat = Country.values()[comboBoxNat.getSelectedIndex()];
					String city = cityTextField.getText();
					String address = addressTextField.getText();
					String newphn = phnPrefixTextField.getText() + " " + phnTextField.getText();
					
					int day = comboBoxDay.getSelectedIndex();
					int month = comboBoxMonth.getSelectedIndex();
					int year = comboBoxYear.getSelectedIndex() + 1899;
	
					cld.set(Calendar.YEAR, year);
					cld.set(Calendar.MONTH, month-1);
					cld.set(Calendar.DAY_OF_MONTH, day);
					cld.set(Calendar.HOUR_OF_DAY, 0);
					cld.set(Calendar.MINUTE, 0);
					cld.set(Calendar.SECOND, 0);
					cld.set(Calendar.MILLISECOND, 0);
					Date newbirthdate = cld.getTime();
			
					boolean isAdmin;
					if(rdbtnAdmin.isSelected()) {
						isAdmin = true;
					}
					else {
						isAdmin = false;
					}	
					
					BLFacade f = MainGUI.getBusinessLogic();
					f.updateUserInfo(ID, newId, newName, newSurname, newEmail,newnat, city,address,newphn,newbirthdate,isAdmin);
					dispose();
				}
				catch(invalidID i) {
					usernameError.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridwidth = 3;
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 14;
		panel.add(btnSave, gbc_btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idField.setText(ID);
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.NORTH;
		gbc_btnCancel.gridwidth = 4;
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 6;
		gbc_btnCancel.gridy = 14;
		panel.add(btnCancel, gbc_btnCancel);
		
		
		String[] days = new String[32];;
		days[0] = "Day";
		for(Integer i=1; i<32;i++) {
			days[i]=i.toString();
		}

		String[] months = new String[13];
		months[0] = "Month";
		for(Integer i=1; i<13; i++) {
			months[i]=i.toString();
		}

		Calendar now = Calendar.getInstance();
		int currentyear = now.get(Calendar.YEAR);
		String[] years = new String[currentyear-1898];
		years[0] = "Year";
		for(Integer i=1900; i<=currentyear; i++) {
			years[i-1899]=i.toString();
		}
		
		String[] splitbday = birthdate.split("/");
		
		comboBoxNat.setModel(new DefaultComboBoxModel<String>(Country.namesArray()));
		comboBoxNat.setSelectedIndex(Country.getValue(country).ordinal());
		comboBoxDay.setModel(new DefaultComboBoxModel<String>(days));
		comboBoxDay.setSelectedItem(splitbday[0].replaceAll("^0+", ""));
		comboBoxMonth.setModel(new DefaultComboBoxModel<String>(months));
		comboBoxMonth.setSelectedItem(splitbday[1].replaceAll("^0+", ""));
		comboBoxYear.setModel(new DefaultComboBoxModel<String>(years));
		comboBoxYear.setSelectedItem(splitbday[2]);
		
	}
	public String[] newData() {
		String status;
		if(rdbtnAdmin.isSelected()) {
			status = "Admin.";
		}
		else {
			status = "User";
		}
		String day = (String)comboBoxDay.getSelectedItem();
		String month = (String)comboBoxMonth.getSelectedItem();
		String year = (String)comboBoxYear.getSelectedItem();
		if(day.length() == 1) {
			day = "0" + day;
		}
		if(month.length() == 1) {
			month = "0" + month;
		}
		String date = day + "/" + month + "/" +year;
		
		String[] s = {idField.getText(), nameField.getText(), surnameField.getText(), emailField.getText(),
					Country.values()[comboBoxNat.getSelectedIndex()].getString()  ,cityTextField.getText(),addressTextField.getText(), phnPrefixTextField.getText() + phnTextField.getText(),date ,status};
		return s;
	}
}
