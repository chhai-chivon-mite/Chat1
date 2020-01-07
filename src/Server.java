import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/*
 * @author: Chhai Chivon on Jan 8, 2020
 * Senior Application Developer
 */

public class Server {

	private JFrame frmChatServer;
	private JTextField tfMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frmChatServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatServer = new JFrame();
		frmChatServer.setTitle("Chat Server");
		frmChatServer.setBounds(100, 100, 450, 300);
		frmChatServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatServer.setLocationRelativeTo(null);
		frmChatServer.getContentPane().setLayout(null);
		
		tfMessage = new JTextField();
		tfMessage.setBounds(10, 230, 320, 20);
		frmChatServer.getContentPane().add(tfMessage);
		tfMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSend.setBounds(335, 229, 89, 23);
		frmChatServer.getContentPane().add(btnSend);
		
		JLabel lblMsgReciever = new JLabel("Hello,");
		lblMsgReciever.setBounds(10, 11, 205, 14);
		frmChatServer.getContentPane().add(lblMsgReciever);
		
		JLabel lblMsgSender = new JLabel("Hello, How are you");
		lblMsgSender.setBounds(219, 11, 205, 14);
		frmChatServer.getContentPane().add(lblMsgSender);
	}
}
