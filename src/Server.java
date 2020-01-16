import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/*
 * @author: Chhai Chivon on Jan 8, 2020
 * Senior Application Developer
 */

public class Server {

	private JFrame frmChatServer;
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
		BindThread bindThread = new BindThread();
		bindThread.start();
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
		tfMessage.setText("ABC");
		tfMessage.setBounds(10, 230, 320, 20);
		frmChatServer.getContentPane().add(tfMessage);
		tfMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendThread sendThread  = new SendThread();
				sendThread.start();
			}
		});
		btnSend.setBounds(335, 229, 89, 23);
		frmChatServer.getContentPane().add(btnSend);
		
		lblMsgReciever = new JLabel("Hello,");
		lblMsgReciever.setBounds(10, 11, 205, 14);
		frmChatServer.getContentPane().add(lblMsgReciever);
		
		lblMsgSender = new JLabel("Hello, How are you");
		lblMsgSender.setBounds(219, 11, 205, 14);
		frmChatServer.getContentPane().add(lblMsgSender);
	}
	
	class BindThread extends Thread {
		
		@Override
		public void run() {
			try {
				// Bind to port
				ServerSocket serverSocket = new ServerSocket(1234);
				System.out.println("Wait client request...");
				connection = serverSocket.accept();
				System.out.println("Connection Server Accept...");
				
				ReceiveThread receiveThread  = new ReceiveThread();
				receiveThread.start();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class SendThread extends  Thread {
		
		@Override
		public void run() {
			try {
				outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
				String message = tfMessage.getText();
				String msgSend = "msg=>"+message;
				tfMessage.setText("");
				System.out.println("Send Message => " +  msgSend);
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
				ReadDataThread readDataThread  = new ReadDataThread();
				readDataThread.start();
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}
	}
	
	class ReadDataThread extends Thread {
		
		@Override
		public void run() {
			try {
				Scanner scanner = new Scanner(connection.getInputStream());
				while (scanner.hasNext()){
					String message = scanner.nextLine();
					System.out.println("Receive Message => "+ message);
					message  = message.replace("msg=>", "");
					lblMsgSender.setText(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}
}
