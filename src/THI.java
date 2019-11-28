import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

/**
 * THI class used for creating Tinnitus Handicap Inventory (THI) questionnaire
 * @author Team 7
 *
 */
public class THI extends JFrame
{	
	private int sum = 0;
	private int function = 0;
	private int emotion = 0;
	private int catastrophic = 0;
	private int[] values = new int[25];
	
	/**
	 * 
	 * THI constructor which is passed in a Connection object as a parameter.
	 * The logic and GUI portion is all done in the constructor
	 * 
	 * @param con the connection being used through this GUI
	 */
	public THI(Connection con)
	{
		this.setLayout(new BorderLayout());				// allows window to resize relative to center of jframe
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		/* name and date portion */
		JPanel top = new JPanel();
		JLabel name = new JLabel("Your Name: ");
		JTextField nameText = new JTextField(15);
		JLabel date = new JLabel("Date: ");
		JTextField dateText = new JTextField(10);
		
		dateText.setText(DataModel.date);
		dateText.setEditable(false);
		dateText.setBackground(Color.WHITE);
		nameText.setText(DataModel.patient);
		nameText.setEditable(false);
		nameText.setBackground(Color.WHITE);

		/* instruction portion */
		JTextArea instructions = new JTextArea();
		instructions.setLineWrap(true);				// add newlines				
        instructions.setWrapStyleWord(true);		// add newlines by word
        instructions.setEditable(false);			// read-only
        instructions.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // add width padding
		instructions.setText(
			"Instructions: The purpose of this questionaire is to identify, quantify, and "
			+ "evaluate the difficulties that you may be experiencing because of tinnitus. Please do not "
			+ "skip any questions. When you have answered all of the questions, add up your total score, based "
			+ "on the values for each response."
		);
		
		/* questions portion */
		String[] questions = {
			"1.  Because of your tinnitus, is it difficult for you to concentrate?",
			"2.  Does the loudness of your tinnitus make it difficult to hear people?",
			"3.  Does your tinnitus make you angry?",
			"4.  Does your tinnitus make you feel confused?",
			"5.  Because of your tinnitus, do you feel desperate?",
			"6.  Do you complain a great deal about your tinnitus?",
			"7.  Becuase of your tinnitus, do you have trouble falling asleep at night?",
			"8.  Do you feel as though you cannot escape your tinnitus?",
			"9.  Does your tinnitus interfere with your ability to enjoy your social activities (such as going out to dinner, to the movies)?",
			"10. Because of your tinnitus, do you feel frustrated?",
			"11. Because of your tinnitus, do you feel that you have a terrible disease?",
			"12. Does your tinnitus make it difficult for you to enjoy life?",
			"13. Does your tinnitus interfere with your job or household responsibilities?",
			"14. Because of your tinnitus, do you find that you are often irritable?",
			"15. Because of your tinnitus, is it difficult for you to read?",
			"16. Does your tinnitus make you upset?",
			"17. Do you feel that your tinnitus has placed stress on your relationships with your family or friends?",
			"18. Do you find it difficult to focus your attention away from your tinnitus and on other things?",
			"19. Do you feel that you have no control over your tinnitus?",
			"20. Because of your tinnitus, do you often feel tired?",
			"21. Because of your tinnitus, do you feel depressed?",
			"22. Does your tinnitus make you feel anxious?",
			"23. Do you feel that you can no longer cope with your tinnitus?",
			"24. Does your tinnitus get worse when you are under stress?",
			"25. Does your tinnitus make you feel insecure?"
		};
		JPanel[] qPanel = new JPanel[25];
		for (int i = 0; i < qPanel.length; i++)
			qPanel[i] = new JPanel(new GridBagLayout());
		
		c.weightx = 1;										// make panel scale when resizing
		c.insets = new Insets(0, 10, 0, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		for (int i = 0; i < questions.length; i++)
		{
			final int index = i;
			JRadioButton rb1 = new JRadioButton("Yes (4)");
			JRadioButton rb2 = new JRadioButton("Sometimes (2)");
			JRadioButton rb3 = new JRadioButton("No (0)", true); // set as default
			ButtonGroup group = new ButtonGroup();
			JTextArea text = new JTextArea(questions[i]);
			
			text.setSize(300, 50);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			text.setBorder(null);
			text.setEditable(false);
			if (i % 2 == 0)
				text.setBackground(null);
			else
			{
				qPanel[i].setBackground(Color.WHITE);
				rb1.setBackground(Color.WHITE);
				rb2.setBackground(Color.WHITE);
				rb3.setBackground(Color.WHITE);
			}
			
			rb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					values[index] = 4;
				}
			});
			rb2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					values[index] = 2;
				}
			});
			rb3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					values[index] = 0;
				}
			});
			
			group.add(rb1);
			group.add(rb2);
			group.add(rb3);
			
			c.gridx = 0;
			c.gridy = 3+i*4;
			qPanel[i].add(text, c);
			c.gridx = 3;
			c.gridy = 3+i*4;
			qPanel[i].add(rb1, c);
			c.gridx = 4;
			c.gridy = 3+i*4;
			qPanel[i].add(rb2, c);
			c.gridx = 5;
			c.gridy = 3+i*4;
			qPanel[i].add(rb3, c);
		}
		c.insets = new Insets(0, 0, 0, 0);
		
		top.add(name);
		top.add(nameText);
		top.add(date);
		top.add(dateText);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(top, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(instructions, c);
		c.gridx = 0;
		c.gridy = 3;
		for (int i = 0; i < qPanel.length; i++)
		{
			c.gridy = 4 + i;
			panel.add(qPanel[i], c);
		}
		JPanel scoresPanel = new JPanel();
		JFrame thisRef = this;
		
        StringBuilder s = new StringBuilder();
        s.append("INSERT INTO THI VALUES(");
		/* calculate THI scores */
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				for (int i = 0; i < values.length; i++)
					sum += values[i];
				function = values[0] + values[1] + values[3] + values[6] + values[8] + values[11] + values[12] + 
						values[14] + values[17] + values[19] + values[23]; 
				emotion = values[2] + values[6] + values[9] + values[13] + values[15] + values[16] + values[20] + values[21] + values[24];
				catastrophic = values[4] + values[7] + values[10] + values[18] + values[22];
				System.out.println("total " + sum);
				System.out.println("function " + function);
				System.out.println("emotion " + emotion);
				System.out.println("catastrophic " + catastrophic);
				
				JOptionPane.showMessageDialog(null,
					    "total: " + sum + "\nfunction: " + function + "\nemotion: " + emotion + "\ncatastrophic: " + catastrophic,
					    "Score",
					    JOptionPane.PLAIN_MESSAGE);
				c.gridy = 30;
				panel.add(scoresPanel, c);
				
				sum = 0;
				
				submit.setEnabled(false);
	            try {
	                String query = "SELECT MAX(VISIT_ID) FROM THI";
	                Statement st = con.createStatement();
	                ResultSet rs = st.executeQuery(query);
	                int id = rs.first() ? rs.getInt(1) + 1 : 0;
	                
	                s.append(id + ", ");
	                s.append(sum + ", ");
	                s.append(function + ", ");
	                s.append(emotion + ", ");
	                s.append(catastrophic + ", ");
	                s.append(values[0]);
	                for (int i = 1; i < values.length; i++)
						s.append(", " + values[i]);
	                s.append(")");
	                System.out.println(s.toString());
	                st.execute(s.toString());
	            } catch (SQLException e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
	            }
	            thisRef.dispose();
			}
		});
		c.gridy = 29;
		panel.add(submit, c);
		
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);	// adjusted for faster scrolling speed
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setTitle("Tinnitus Handicap Inventory (THI)");
		
		this.pack();											// pack margins
		this.setSize(this.getWidth() + 20, this.getHeight());	// +20px to prevent scroll bar from covering content
		this.setLocationRelativeTo(null); 						// centered relative to monitor screen
		this.setVisible(true);									// display the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// close on exit
	}
}
