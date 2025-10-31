package com.tmb.view.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.tmb.controller.LoginController;
import com.tmb.view.components.BackgroundPanel;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private final LoginController controller;
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	public LoginView(Function<LoginView, LoginController> loginController) {
		this.controller = loginController.apply(this);
		initComponents();
	}

	private void initComponents() {
		setSize(800, 600);
		setTitle("Login");
		setIconImage(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel(new GridLayout(0, 2, 0, 0));
		setContentPane(contentPane);
		
		JPanel leftPanel = new BackgroundPanel("/images/banners/banner_login_aside_400x600.png");
		leftPanel.setLayout(null);
		contentPane.add(leftPanel);
		
		JLabel lblTitle = new JLabel("<html>"
				+ "<h1>Bem vindo!</h1>"
				+ "<p>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>"
				+ "</html>");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(23, 25, 280, 162);
		leftPanel.add(lblTitle);
		
		JPanel rightPanel = new JPanel();
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
		
		JLabel lblTitle2 = new JLabel("<html><h2>Faça login na TopSpeed</h2></html>");
		lblTitle2.setBounds(66, 80, 261, 32);
		rightPanel.add(lblTitle2);
		
		JLabel lblUsername = new JLabel("Usuário:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(66, 170, 74, 14);
		rightPanel.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(66, 186, 261, 26);
		rightPanel.add(txtUsername);
		
		JLabel lblPassword = new JLabel("Senha:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(66, 230, 74, 14);
		rightPanel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(66, 246, 260, 26);
		rightPanel.add(txtPassword);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setBounds(66, 330, 89, 26);
		rightPanel.add(btnLogin);
	}
}
