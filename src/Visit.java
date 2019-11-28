import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Visit class which is used to generate the Interview screen, where the user can enter the
 * patient's name, THC, and visit number
 * 
 * @author Team 7
 *
 */
public class Visit extends JFrame{
	
	/**
	 * constructor which creates the GUI for Interview screen 
	 * @param con connection for connecting to the database
	 */
	public Visit(Connection con) {

		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
			
		ArrayList<JTextField> list = new ArrayList<>();
		
		/*
		 labels and text boxes for:
		 name
		 date
		 visit id
		 thc#
		 visit#
		 */
		JLabel visitID = new JLabel("Visit ID: ");
		JTextField visitIDText = new JTextField(3);
		
		JLabel date = new JLabel("Date: ");
		JTextField dateText = new JTextField(10);
		
		JLabel patient = new JLabel("Patient Name: ");	
		JTextField patientText = new JTextField(12);			
		
		JLabel thc = new JLabel("Patient THC#: ");		
		JTextField thcText = new JTextField(6);			
		
		JLabel visitNum = new JLabel("Visit number: ");	
		JTextField visitNumText= new JTextField(3);
		
		java.sql.Timestamp currentDate = new java.sql.Timestamp(new java.util.Date().getTime());
		dateText.setText(currentDate.toString().substring(0, 10));
		DataModel.date = dateText.getText();
		dateText.setEditable(false);
		
		int id = 1;
		try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(ID) FROM Visit");
            rs.next();
            id = rs.getInt("MAX(ID)") + 1;
            System.out.println("id: " + id);
        }catch (SQLException sq)
        {
            SQLUtil.printSQLExceptions(sq);      
        }
		visitIDText.setText("" + id);
		DataModel.id = id;
		visitIDText.setEditable(false);
			
		list.add(visitIDText); //0
		list.add(thcText); //1
		list.add(visitNumText); //2
		list.add(patientText); //3
		list.add(dateText); //4
		//buttons
		/*
		 buttons added for:
		 THI
		 TFI
		 */
		
		JButton THIButton = new JButton("Tinnitus Handicap Inventory (THI)");
		THIButton.setPreferredSize(new Dimension(250, 75));
		
		THIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder s = new StringBuilder();
                s.append("insert into Visit values(");
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).getText().isEmpty()) {
                        System.out.println("Empty field");
                    }else {
                        //name
                        if(i == 3) {
                            String[] name = list.get(i).getText().split(" ");
                            System.out.println(name[0]);
                            s.append("'" + name[0] + "',");
                            s.append("'" + name[1] + "',");
                            DataModel.patient = list.get(i).getText();
                            //date
                        }else if(i == 4) {
                            s.append("'" + list.get(i).getText() + "',");
                        }else {
                            s.append(list.get(i).getText() + ",");
                        }
                    }
                }
                s.setLength(s.length() - 1);
                s.append(")");
                try{
                    Statement st = con.createStatement();
                    System.out.println(s.toString());
                    st.execute(s.toString());
                }catch (SQLException sq)
                {
                    SQLUtil.printSQLExceptions(sq);      
                }
                new THI(con);
            }
        });
		
		JButton TFIButton = new JButton("Tinnitus Functional Index (TFI)");
		TFIButton.setPreferredSize(new Dimension(250, 75));
		
		TFIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder s = new StringBuilder();
                s.append("insert into Visit values(");
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).getText().isEmpty()) {
                        System.out.println("Empty field");
                    }else {
                        //name
                        if(i == 3) {
                            String[] name = list.get(i).getText().split(" ");
                            s.append("'" + name[0] + "',");
                            s.append("'" + name[1] + "',");
                            DataModel.patient = list.get(i).getText();
                            //date
                        }else if(i == 4) {
                            s.append("'" + list.get(i).getText() + "',");
                        }else {
                            s.append(list.get(i).getText() + ",");
                        }
                    }
                }
                s.setLength(s.length() - 1);
                s.append(")");
                try{
                    Statement st = con.createStatement();
                    System.out.println(s.toString());
                    st.execute(s.toString());
                    
                }catch (SQLException sq)
                {
                    SQLUtil.printSQLExceptions(sq);      
                 }
            	new TFI(con);
            }
        });
		
		
		
		//Adding components to the panel
		panel.add(visitID);
		panel.add(visitIDText);
		panel.add(date);
		panel.add(dateText);
		
		panel.add(patient);
		panel.add(patientText);
		
		panel.add(thc);
		panel.add(thcText);
		
		panel.add(visitNum);
		panel.add(visitNumText);
		
		//panel.add(InterButton);
		panel.add(THIButton);
		panel.add(TFIButton);
		
		//adding panel to jframe
		this.add(panel);	
	
		
		//Interface settings
		
		this.setTitle("Interview");								// title
		this.pack();											// pack margins		
		this.setSize(510, 300);
		this.setLocationRelativeTo(null); 						// centered relative to monitor screen
		this.setVisible(true);									// display the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// close on exit
	}
}
