package fileContentGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertFileGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	private JLabel title;
	private JTextField fileinput;
	private JButton button;
	
	public InsertFileGUI() {
		frame = new JFrame("File Reader");
		
		frame.setSize(500,500);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		
		
		frame.add(panel , BorderLayout.CENTER);
		
		title = new JLabel("Enter a Text File");
		title.setBounds(500,200,200,30);
		panel.add(title);
		
		fileinput = new JTextField();
		fileinput.setBounds(500,50,200,30);
		panel.add(fileinput);
		
		button = new JButton("submit");
		button.addActionListener(this);
		panel.add(button);
		
		//Scanner inp = new Scanner(fileinput.getText());
		//String filename = fileinput.getText();
		//if (!filename.contains(".txt")) {
			//filename = filename + ".txt";
		//}
		//File file = new File(filename);
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InsertFileGUI();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String filename = fileinput.getText();
		if (!filename.contains(".txt")) {
			filename = filename + ".txt";
		}
		File file = new File(filename);
		
		try {
			Scanner sr = new Scanner(file);
			while(sr.hasNextLine()) {
				System.out.println(sr.nextLine());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(sr.nextLine());
		//System.out.println(filename);
	}

}
