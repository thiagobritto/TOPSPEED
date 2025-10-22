package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.view.components.ButtonSidebar;
import com.tmb.view.components.SidebarMainPanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(MainView.class);
	private JPanel contentPane;
	private JDesktopPane desktopPane;

	public MainView() {
		initComponents();
	}

	private void initComponents() {
		setSize(1024, 720);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Principal");
		setIconImage(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnRegisters = new JMenu("Cadastros");
		menuBar.add(mnRegisters);

		JMenuItem mbRegisterCustomer = new JMenuItem("Clientes");
		mnRegisters.add(mbRegisterCustomer);

		JMenuItem mbRegisterVehicle = new JMenuItem("Veiculos");
		mnRegisters.add(mbRegisterVehicle);

		JMenuItem mbRegisterOrderService = new JMenuItem("OS");
		mnRegisters.add(mbRegisterOrderService);

		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);

		JPanel aside = new SidebarMainPanel();
		contentPane.add(aside, BorderLayout.WEST);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainView.class.getResource("/images/logos/logo_top_main_aside_138x42.png")));
		lblLogo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Expande horizontalmente
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o texto
		aside.add(lblLogo);

		aside.add(Box.createVerticalStrut(6));

		JButton btnHome = new ButtonSidebar("Home");
		btnHome.addActionListener(e -> showSingleInternalFrame(new HomeView()));
		aside.add(btnHome);

		aside.add(Box.createVerticalStrut(6));

		JButton btnCustomer = new ButtonSidebar("Cliente");
		btnCustomer.addActionListener(e -> showSingleInternalFrame(new CustomerView()));
		aside.add(btnCustomer);

		aside.add(Box.createVerticalStrut(6));

		JButton btnOs = new ButtonSidebar("OS");
		aside.add(btnOs);
		
		JPanel mainPane = new JPanel(new BorderLayout(0, 0));
		contentPane.add(mainPane, BorderLayout.CENTER);

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		//header.setBackground(new Color(23, 0, 70));
		mainPane.add(header, BorderLayout.NORTH);

		ImageIcon userIcon = new ImageIcon(MainView.class.getResource("/images/icons/avatar_icon_48x48.png"));
		
		JLabel lblName = new JLabel("Username");
		lblName.setIcon(new ImageIcon(userIcon.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH)));
		header.add(lblName);

		desktopPane = new JDesktopPane();
		mainPane.add(desktopPane, BorderLayout.CENTER);

		JPanel footer = new JPanel();
		mainPane.add(footer, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("New label");
		footer.add(lblNewLabel_1);
		
		showSingleInternalFrame(new HomeView());
	}

	private <T extends JInternalFrame> void showSingleInternalFrame(T internalFrame) {
		try {
			for (JInternalFrame fr: desktopPane.getAllFrames()) {
				if (fr.getClass().equals(internalFrame.getClass())) {
					fr.dispose();
				}
			}
			
			
			desktopPane.add(internalFrame);
		    internalFrame.setVisible(true);
		    internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			logger.info("Erro ao selecionar JInternalFrame.", e);
		}
	}
	
}
