package projetolp2.hotelriviera;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.UIManager;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;


public class InterfaceGraf extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraf frame = new InterfaceGraf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceGraf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(UIManager.getColor("windowBorder"));
		setBounds(100, 100, 1160, 627);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		contentPane.setToolTipText("Oi");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button button = new Button("Check in");
		button.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 32));
		button.setBackground(new Color(204, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("foi :)");
			}
		});
		button.setBounds(157, 220, 421, 165);
		contentPane.add(button);
		
		Button button_1 = new Button("Adicionais");
		button_1.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 32));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBackground(new Color(204, 255, 255));
		button_1.setBounds(597, 220, 421, 165);
		contentPane.add(button_1);
		
		Button button_2 = new Button("Consulta");
		button_2.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 32));
		button_2.setBackground(new Color(204, 255, 255));
		button_2.setBounds(157, 404, 421, 165);
		contentPane.add(button_2);
		
		Button button_3 = new Button("Check out");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 32));
		button_3.setBackground(new Color(204, 255, 255));
		button_3.setBounds(597, 404, 421, 165);
		contentPane.add(button_3);
		
		Panel panel = new Panel();
		panel.setBounds(311, 34, 554, 142);
		contentPane.add(panel);
		
		TextField textField = new TextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 90));
		textField.setText("Hotel Rivera");
		textField.setEditable(false);
		panel.add(textField);
	}
}
