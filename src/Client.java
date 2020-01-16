import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/*
 * @author: Chhai Chivon on Jan 8, 2020
 * Senior Application Developer
 */

public class Client {

	private JFrame frmChatClient;
	private JTextField tfMessage;

	JLabel lblMsgSender;
	JLabel lblMsgReciever;
	
	private Socket connection;
	private Scanner scanner;
	private OutputStreamWriter outputStreamWriter;
	
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
		BindThread bindThread = new BindThread();
		bindThread.start();
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
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendThread sendThread  = new SendThread();
				sendThread.start();
			}
		});
		btnSend.setBounds(335, 229, 89, 23);
		frmChatClient.getContentPane().add(btnSend);
		
		lblMsgReciever = new JLabel("Hello,");
		lblMsgReciever.setBounds(10, 11, 201, 14);
		frmChatClient.getContentPane().add(lblMsgReciever);
		
		lblMsgSender = new JLabel("Hello, How are you !");
		lblMsgSender.setBounds(223, 11, 201, 14);
		frmChatClient.getContentPane().add(lblMsgSender);
	}

	
	class BindThread  extends Thread {
		
		@Override
		public void run() {
			try {
				// Bind to port
//				ServerSocket serverSocket = new ServerSocket(1234);
//				System.out.println("Wait client request...");
//				connection = serverSocket.accept();
				System.out.println("Connect to Server...");
				connection = new Socket("localhost",1234);
				
				ReceiveThread receiveThread = new ReceiveThread();
				receiveThread.start();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class SendThread extends Thread {
	
		@Override
		public void run() {
			try {
				outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
				String message = tfMessage.getText();
				String msgSend = "msg=>"+message;
				System.out.println("Message Send  => " + msgSend);
				tfMessage.setText("");
				outputStreamWriter.write(msgSend);
				outputStreamWriter.write("\n");
				outputStreamWriter.flush();
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}
	}
	
	class ReceiveThread extends Thread {
		
		@Override
		public void run() {
			try {
				while(true) {					
					scanner = new Scanner(connection.getInputStream());
					while (scanner.hasNext()){
						String message = scanner.nextLine();
						System.out.println("Message Receive => " +  message);
						message  = message.replace("msg=>", "");
						lblMsgSender.setText(message);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
