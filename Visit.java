import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Visit extends JFrame{

	public Visit() {

		this.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Interview form");	//idk
		this.add(title, BorderLayout.NORTH);
		
		//JPanel stuff
		JPanel panel = new JPanel();
			
		/*
		 labels and text boxes for:
		 name
		 date
		 visit id
		 thc#
		 visit#
		 */
		JLabel name = new JLabel("Your Name: ");
		JTextField nameText = new JTextField(15);		
		
		JLabel date = new JLabel("Date: ");				
		JTextField dateText = new JTextField(10);		
		
		JLabel VisitId = new JLabel("Patient ID: ");	
		JTextField IdText = new JTextField(6);			
		
		JLabel thc = new JLabel("Patient THC#: ");		
		JTextField thcText = new JTextField(6);			
		
		JLabel VisitNum = new JLabel("Visit number: ");	
		JTextField visitNumText= new JTextField(3);		
			
		
		//buttons
		/*
		 buttons added for:
		 interview initial/follow-up
		 THI
		 TFI
		 */
		
		/*
		JButton InteviewButton = new JButton("Inital/ Follow-up Interview");
		InteviewButton.setPreferredSize(new Dimension(250, 75));
		
		*/
		
		JButton THIButton = new JButton("Tinnitus Handicap Inventory (THI)");
		THIButton.setPreferredSize(new Dimension(250, 75));
		
		THIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new THI();
            }
        });
		
		JButton TFIButton = new JButton("Tinnitus Functional Index (TFI)");
		TFIButton.setPreferredSize(new Dimension(250, 75));
		
		TFIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new TFI(null);
            }
        });
		
		
		
		//Addding components to the panel
		panel.add(name);
		panel.add(nameText);
		panel.add(date);
		panel.add(dateText);
		
		panel.add(VisitId);
		panel.add(IdText);
		
		panel.add(thc);
		panel.add(thcText);
		
		panel.add(VisitNum);
		panel.add(visitNumText);
		
		//panel.add(InterButton);
		panel.add(THIButton);
		panel.add(TFIButton);
		
		//adding panel to jframe
		this.add(panel);	
	
		
		//Interface settings
		
		this.setTitle("Project 3 - Visit");						// title
		this.pack();											// pack margins		
		this.setSize(510, 300);
		this.setLocationRelativeTo(null); 						// centered relative to monitor screen
		this.setVisible(true);									// display the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// close on exit
	}
}
