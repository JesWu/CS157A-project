import java.awt.*;
import javax.swing.*;

public class THI extends JFrame
{	
	public THI()
	{
		this.setLayout(new BorderLayout());				// allows window to resize relative to center of jframe
		
		JPanel panel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		
		/* name and date portion */
		JPanel top = new JPanel();
		JLabel name = new JLabel("Your Name: ");
		JTextField nameText = new JTextField(15);
		JLabel date = new JLabel("Date: ");
		JTextField dateText = new JTextField(10);

		/* instruction portion */
		JTextArea instructions = new JTextArea();
		instructions.setLineWrap(true);				// add newlines				
        instructions.setWrapStyleWord(true);		// add newlines by word
        instructions.setEditable(false);			// read-only
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
		JPanel qPanel = new JPanel(new GridBagLayout());
		
		for (int i = 0; i < questions.length; i++)
		{
			JRadioButton rb1 = new JRadioButton("Yes (4)");
			JRadioButton rb2 = new JRadioButton("Sometimes (2)");
			JRadioButton rb3 = new JRadioButton("No (0)");
			ButtonGroup group = new ButtonGroup();
			JTextArea text = new JTextArea(questions[i]);
			
			text.setSize(500, 50);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			text.setBorder(null);
			text.setEditable(false);
			if (i % 2 == 0)
				text.setBackground(null);
			
			group.add(rb1);
			group.add(rb2);
			group.add(rb3);
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 3+i*4;
			c.weightx = 1;
			qPanel.add(text, c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3;
			c.gridy = 3+i*4;
			qPanel.add(rb1, c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 4;
			c.gridy = 3+i*4;
			qPanel.add(rb2, c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 5;
			c.gridy = 3+i*4;
			qPanel.add(rb3, c);
		}
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(qPanel, c);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);	// adjusted for faster scrolling speed
		
		this.add(scrollPane, BorderLayout.CENTER);		
		
		this.setTitle("Tinnitus Handicap Inventory (THI)");
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		System.out.println("Screen Bounds: " + bounds );
		
		this.pack();											// pack margins
		this.setLocationRelativeTo(null); 						// centered relative to monitor screen
		this.setVisible(true);									// display the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// close on exit
	}
}
