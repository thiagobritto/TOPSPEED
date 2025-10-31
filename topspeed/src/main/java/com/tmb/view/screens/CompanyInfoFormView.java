package com.tmb.view.screens;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.SwingConstants;

public class CompanyInfoFormView extends AbstractFormLayout {

	private static final long serialVersionUID = 1L;
	private JTextField txtNameFake;
	private JTextField txtName;
	private JFormattedTextField txtCnpj;
	private JFormattedTextField txtIE;
	private JTextField txtStreet;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JLabel lblLogo;
	private JButton btnLoadLogo;

	public CompanyInfoFormView() {
		this.initComponents();
	}

	private void initComponents() {
		setTitle("Empresa");
		setContentPane(body);
		
		JLabel lblNameFake = new JLabel("Nome fantasia");
		lblNameFake.setBounds(10, 10, 106, 15);
		sectionMain.add(lblNameFake);
		
		txtNameFake = new JTextField();
		txtNameFake.setBounds(10, 30, 444, 25);
		sectionMain.add(txtNameFake);
		txtNameFake.setColumns(10);
		
		JLabel lblName = new JLabel("Razão social");
		lblName.setBounds(10, 60, 106, 15);
		sectionMain.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(10, 80, 444, 25);
		sectionMain.add(txtName);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(10, 110, 106, 15);
		sectionMain.add(lblCnpj);
		
		txtCnpj = new JFormattedTextField();
		txtCnpj.setBounds(10, 130, 220, 25);
		sectionMain.add(txtCnpj);
		
		JLabel lblIE = new JLabel("Inscrição estadual");
		lblIE.setBounds(238, 110, 127, 15);
		sectionMain.add(lblIE);
		
		txtIE = new JFormattedTextField();
		txtIE.setBounds(238, 130, 216, 25);
		sectionMain.add(txtIE);
		
		JLabel lblStreet = new JLabel("Endereço");
		lblStreet.setBounds(10, 160, 106, 15);
		sectionMain.add(lblStreet);
		
		txtStreet = new JTextField();
		txtStreet.setColumns(10);
		txtStreet.setBounds(10, 180, 444, 25);
		sectionMain.add(txtStreet);
		
		JLabel lblPhone = new JLabel("Telefone");
		lblPhone.setBounds(10, 210, 106, 15);
		sectionMain.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(10, 230, 220, 25);
		sectionMain.add(txtPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(240, 210, 106, 15);
		sectionMain.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(240, 230, 375, 25);
		sectionMain.add(txtEmail);
		
		lblLogo = new JLabel("");
		lblLogo.setBounds(465, 30, 150, 150);
		lblLogo.setIcon(new ImageIcon(CompanyInfoFormView.class.getResource("/images/logos/logo.png")));
		lblLogo.setVerticalAlignment(SwingConstants.CENTER);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		sectionMain.add(lblLogo);
		
		btnLoadLogo = new JButton("Carregar logo");
		btnLoadLogo.setBounds(465, 180, 150, 25);
		sectionMain.add(btnLoadLogo);
		
		
	}
}
