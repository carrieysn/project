package com.cifpay.insurance;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatClient extends JFrame {
	Socket socket = null;
	OutputStream outputStream = null;
	InputStream inputStream = null;
	private boolean bConnected = false;

	JTextArea sendContentTextArea = new JTextArea(10, 10);
	JTextArea returnContentTextArea = new JTextArea(20, 20);

	AsynReceiveThread receiveThread = new AsynReceiveThread();
	String vendorId = "11";

	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}

	public void launchFrame() {
		this.setResizable(true);
        this.setBounds(230, 100, 800, 650);
        this.add(new JScrollPane(returnContentTextArea), BorderLayout.CENTER);
        JButton submit=new JButton("发送");
        submit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		try{
        			String sendContentStr = sendContentTextArea.getText().trim();
        			sendContentTextArea.setText("");
        			try {
        				outputStream.write(("{\"connectId\":\""+vendorId+"\"}").getBytes());
        				outputStream.flush();
        			} catch (IOException e1) {
        				e1.printStackTrace();
        			}
        		}catch(Exception e1){
        			e1.printStackTrace();
        		}
        	}
        });
        
        JPanel jpaneSouth= new JPanel(new BorderLayout());
        jpaneSouth.add(new JLabel(" "), BorderLayout.NORTH);
        JScrollPane jscrollPane = new JScrollPane(sendContentTextArea);
        jpaneSouth.add(jscrollPane, BorderLayout.CENTER);
        jpaneSouth.add(submit, BorderLayout.SOUTH);
		
        this.add(jpaneSouth, BorderLayout.SOUTH);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e) {
						try {
							disconnect();
							System.exit(0);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
	    );
		returnContentTextArea.setAutoscrolls(true);
		sendContentTextArea.setAutoscrolls(true); 
		this.setTitle("App调用模拟器");
        this.setVisible(true);
        
		this.connect();

		receiveThread.run();
	}

	public void connect() {
		try {
			socket = new Socket("192.168.4.248", 1201);
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			bConnected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void disconnect() {
		try {
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class AsynReceiveThread  {
		public void run() {
			try {
				while (bConnected) {
					inputStream = socket.getInputStream();
					byte[] byteArr = new byte[inputStream.available()];
					inputStream.read(byteArr);
					String str = new String(byteArr);
					if(str != null && str.length() > 0) {
						str = new Date().toLocaleString()+"-->" + str;
						returnContentTextArea.setText(returnContentTextArea.getText() + str + '\n');
					}
					//inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}