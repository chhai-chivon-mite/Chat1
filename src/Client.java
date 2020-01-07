import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

/*
 * @author: Chhai Chivon on Jan 8, 2020
 * Senior Application Developer
 */

public class Client {

	private JFrame frmChatClient;
	private JTextField tfMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frmChatClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatClient = new JFrame();
		frmChatClient.setTitle("Chat Client");
		frmChatClient.setBounds(100, 100, 450, 300);
		frmChatClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmChatClient.setLocationRelativeTo(null);
		frmChatClient.getContentPane().setLayout(null);
		
		tfMessage = new JTextField();
		tfMessage.setBounds(10, 230, 315, 20);
		frmChatClient.getContentPane().add(tfMessage);
		tfMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(335, 229, 89, 23);
		frmChatClient.getContentPane().add(btnSend);
		
		JLabel lblMsgReciever = new JLabel("Hello,");
		lblMsgReciever.setBounds(10, 11, 201, 14);
		frmChatClient.getContentPane().add(lblMsgReciever);
		
		JLabel lblMsgSender = new JLabel("Hello, How are you !");
		lblMsgSender.setBounds(223, 11, 201, 14);
		frmChatClient.getContentPane().add(lblMsgSender);

	}

}
