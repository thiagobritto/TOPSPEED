package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.function.Function;

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
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.controller.MainController;
import com.tmb.view.components.ButtonSidebar;
import com.tmb.view.components.SidebarMainPanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(MainView.class);
	private final MainController controller;
	
	private String primaryTitle = "Dashboard";
	private JPanel contentPane;
	private JDesktopPane desktopPane;

	public MainView(Function<MainView, MainController> controller) {
		this.controller = controller.apply(this);
		this.initComponents();
	}

	private void initComponents() {
		setTitle(primaryTitle);
		setIconImage(null);
		setSize(1024, 720);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnRegisters = new JMenu("Cadastros");
		menuBar.add(mnRegisters);

		JMenuItem mbRegisterCustomer = new JMenuItem("Clientes");
		mbRegisterCustomer.addActionListener(e -> showSingleInternalFrame(ScreenFactory.createCustomerView()));
		mnRegisters.add(mbRegisterCustomer);

		JMenuItem mbRegisterOrderService = new JMenuItem("OS");
		mbRegisterOrderService.addActionListener(e -> showSingleInternalFrame(ScreenFactory.createOSFormView()));
		mnRegisters.add(mbRegisterOrderService);

		JMenu mnReports = new JMenu("Relatórios");
		menuBar.add(mnReports);

		JMenuItem mbReportCustomer = new JMenuItem("Clientes");
		mbReportCustomer.addActionListener(e -> {
			controller.generateCustomerReport();
		});
		mnReports.add(mbReportCustomer);

		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);

		// SIDEBAR

		JPanel aside = new SidebarMainPanel();
		contentPane.add(aside, BorderLayout.WEST);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainView.class.getResource("/images/logos/logo_top_main_aside_138x42.png")));
		lblLogo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45)); // Expande horizontalmente
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o texto
		aside.add(lblLogo);

		aside.add(Box.createVerticalStrut(5));

		JButton btnHome = new ButtonSidebar("Home");
		btnHome.addActionListener(e -> {
			for (JInternalFrame fr : desktopPane.getAllFrames()) {
				fr.dispose();
			}

			HomeView homeView = new HomeView();
			homeView.setVisible(true);

			desktopPane.add(homeView);
		});
		aside.add(btnHome);

		aside.add(Box.createVerticalStrut(5));

		JButton btnOs = new ButtonSidebar("OS");
		btnOs.addActionListener(e -> showSingleInternalFrame(ScreenFactory.createOSFormView()));
		aside.add(btnOs);

		aside.add(Box.createVerticalStrut(5));

		JButton btnCustomer = new ButtonSidebar("Cliente");
		btnCustomer.addActionListener(e -> showSingleInternalFrame(ScreenFactory.createCustomerView()));
		aside.add(btnCustomer);

		// MAIN PANEL

		JPanel mainPane = new JPanel(new BorderLayout(0, 0));
		contentPane.add(mainPane, BorderLayout.CENTER);

		// MAIN PANEL -> HEADER

		 //JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		 //header.setBackground(new Color(23, 0, 70));
		 //mainPane.add(header, BorderLayout.NORTH);

		 //ImageIcon userIcon = new
		 //ImageIcon(MainView.class.getResource("/images/icons/avatar_icon_48x48.png"));

		 //JLabel lblName = new JLabel("Home");
		 //lblName.setIcon(new ImageIcon(userIcon.getImage().getScaledInstance(36, 36,
		// Image.SCALE_SMOOTH)));
		 //header.add(lblName);

		// MAIN PANEL -> BODY

		desktopPane = new JDesktopPane();
		mainPane.add(desktopPane, BorderLayout.CENTER);

		// MAIN PANEL -> FOOTER

		JPanel footer = new JPanel();
		mainPane.add(footer, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("New label");
		footer.add(lblNewLabel_1);

		SwingUtilities.invokeLater(() -> showSingleInternalFrame(new HomeView()));
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(String.join(" / ", primaryTitle, title));
	}

	public void setPrimaryTitle(String title) {
		primaryTitle = title;
	}

	private <T extends JInternalFrame> void showSingleInternalFrame(T internalFrame) {
		try {
			for (JInternalFrame fr : desktopPane.getAllFrames()) {
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
