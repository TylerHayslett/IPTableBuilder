package Main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class Window extends JFrame implements ActionListener{
	private JTextField username = new JTextField("Username", 25);
	private JPasswordField password = new JPasswordField("Password", 25);
	private JTextField IP = new JTextField("IP", 25);
	private JTextPane pane = new JTextPane();
	
	public Window(){
		pane.setMinimumSize(new Dimension(250,300));
		pane.setPreferredSize(new Dimension(250,300));;
		JButton b1 = new JButton("Get Table");
		b1.addActionListener(this);
		this.setMinimumSize(new Dimension(300,465));
		this.setPreferredSize(new Dimension(300,465));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		this.add(username);
		this.add(password);
		this.add(IP);
		this.add(b1);
		this.add(pane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DataFetcher.Fetch(username.getText(), new String(password.getPassword()), IP.getText());
		pane.setText((new OutputBuilder()).toString());
	}
}
