package fileContentGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class InsertFileGUI {
	
	String newline = System.getProperty("line.separator");
	
	//initializing objects
	private JFrame frame;
	
	private JPanel panel;
	
	private JPanel panel2;
	
	private JLabel title;
	
	private JFileChooser fileinput;
	
	private JButton choose;
	private JButton submit;
	private JButton back;
	private JButton save;
	private JButton check;
	private JButton edit;
	
	private JFileChooser choosedir;
	
	
	private JTextArea content;
	
	private JScrollPane scroll;
	
	private String lines = "";
	
	
	public InsertFileGUI() throws IOException {
		//creating frame
		frame = new JFrame("File Editor");
		
		frame.setSize(500,300);
		frame.setResizable(false);
		
		//create panel that is currently not visible to user
		panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		panel2.setVisible(false);	
		panel2.setBackground(new Color(184, 218, 255));
		
		//an editor pane to display the text is created with background color grey
		content = new JTextArea();
		content.setBackground(new Color(255, 120, 201));
		content.setEditable(false);
				
		//a scrollbar is created
		scroll = new JScrollPane(content,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				
		//set the position of the scroll pane
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.insets = new Insets(1,1,1,1);
		
		//scrollbar is added to panel2,its connected to the editor pane so thats displayed too
		panel2.add(scroll, gc);
		
		//create save button 
		save = new JButton("Save");
		
		//set the position of save button
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(1,1,0,1);
		
		//save button is added to panel2
		panel2.add(save, gc);
		
		//create check button
		check = new JButton("check");
		
		//set the position of check button
		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.insets = new Insets(0,0,1,0);
		
		//add check button to panel2
		panel2.add(check, gc);
		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(content.getText());
			}
			
			
		});
		
		
		//create back button to go back to original panel
		back = new JButton("<-");
				
		//set the position of back button
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.insets = new Insets(0,1,1,1);
				
		//back button is added to panel2
		panel2.add(back, gc);
		
		//edit button is created to edit the text
		edit = new JButton("edit");
		
		//set the position of the edit button
		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 0;
		//gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(1,1,0,1);
		
		//edit button is added to panel2
		panel2.add(edit, gc);
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(content.isEditable()) {
					content.setEditable(false);
				}
				else {
					content.setEditable(true);
				}
			}
			
		});
		
		//add panel2 to frame and setting it to be at the center when its visible
		frame.add(panel2, BorderLayout.CENTER);
		
		//create panel with starting view
		panel = new JPanel();
		panel.setLayout(null);
		
		panel.setSize(200,200);

		//add panel to frame and setting it to appear at the center
		frame.add(panel, BorderLayout.CENTER);
		
		//add label that tells the user to enter a text file 
		title = new JLabel("Enter a Text File");
		title.setBounds(200,10,150,30);
		panel.add(title);
		
		//create a file chooser that the user can use to add the file
		fileinput = new JFileChooser();
		
		fileinput.setBounds(70,50,200,40);
		fileinput.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		//add file chooser to panel
		panel.add(fileinput);
		
		//create a button that,when clicked, triggers file chooser to open
		choose = new JButton("choose");
		choose.setBounds(300, 62, 50, 20);
		
		choose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileinput.showOpenDialog(panel);
			}
			
		});
		
		//add b2 to panel
		panel.add(choose);
		
		//create a button that lets the user submit their file
		submit = new JButton("submit");
		
		//set button size & location
		submit.setBounds(230,100,50,20);
		//add button to panel
		panel.add(submit);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//the file selected by user is taken
				File filecontent = fileinput.getSelectedFile();
	
				panel2.setSize(500,650);
				
				//content.setLineWrap(true);		
				//check if the file is, indeed, a text file
				if(!filecontent.getName().endsWith(".txt")){
					//if not tell user to enter a valid text file
					System.out.println("Please enter a valid text file noob");
				}
				else {
					try {
						//scan file
						frame.setSize(500,700);
						Scanner sr = new Scanner(filecontent);
						
						//while file has lines -> add line to content and add 1 to numofls
						while(sr.hasNextLine()) {
							//lines.concat(sr.nextLine());
							content.setText(content.getText().concat(sr.nextLine() + newline));
						}
						
						//System.out.println(lines);
						frame.setResizable(true);
						panel2.setVisible(true);
						panel.setVisible(false);
					}
				catch(Exception m) {
					System.out.println(m);
					System.out.println("Please enter a valid text file loser" + newline);
				}
					
				}
			}
		});
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setResizable(false);
				panel2.setVisible(false);
				panel.setVisible(true);
				frame.setSize(500,300);
				content.setEditable(false);
			}
			
			
		});
		
		choosedir = new JFileChooser();
		choosedir.setDialogTitle("choose a directory : ");
		choosedir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				
				//create a scanner to scan input
				Scanner scan = new Scanner(System.in);
				
				//open the filechooser for choosing the directory in which the file should be saved
				choosedir.showOpenDialog(panel2);
				
				//ask the user for their preferred name for the new file
				System.out.println("Enter a name for the new file : ");
				
				//create a string variable to store the name for the file
				String filename = ""; 
				
				//if the user has selected a file in choosedir
				if(choosedir.getSelectedFile() != null){
					//assign the var filename to the chosen directory + / + the name entered by the user + .txt
					filename = choosedir.getSelectedFile() +"/"+scan.next()+ ".txt";
				}
				else {
					filename = choosedir.getCurrentDirectory() + "/" + scan.next() + ".txt";
				}
				System.out.println(filename);
				
				FileWriter writer;
				try {
					writer = new FileWriter(filename);
					writer.write(content.getText());
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				/*try {
					
					//enter the text inside "content" into a string variable
					lines = content.getText();
					
					//File newFile = new File(filename);
					//create a file writer to create a file with that name
					FileWriter writer = new FileWriter(filename);
					
					//add text in content to the new file
					for (int i = 0; i < content.getText().length(); i++) {
						writer.write(content.getText().charAt(i));
					}
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
			}
			
		});
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new InsertFileGUI();
	}


}
