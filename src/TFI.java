import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

public class TFI extends JFrame{
    
    public TFI()
    {
        this.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        
        /* name and date portion */
        JPanel top = new JPanel();
        JLabel name = new JLabel("Your Name: ");
        JTextField nameText = new JTextField(15);
        JLabel date = new JLabel("Date: ");
        JTextField dateText = new JTextField(10);
        JButton submit = new JButton("Submit");
        
        TreeMap<Integer, JSlider> sliderMap = new TreeMap<>();
        
        submit.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            System.out.println("You just submitted your entry!");
            StringBuilder s = new StringBuilder();
            for(Map.Entry<Integer, JSlider> entry: sliderMap.entrySet()) {
                s.append("|" + entry.getValue().getValue());
            }
            System.out.println(s.toString());
        }
        });
//      name.setBounds(10, 10, 10, 20);
//      nameText.setBounds(30, 10, 100, 20);
//      date.setBounds(10, 30, 10, 20);
//      dateText.setBounds(30, 30, 100, 20);
        
        /* instruction portion */
        JTextArea instructions = new JTextArea();
        instructions.setLineWrap(true);                 // add newlines             
        instructions.setWrapStyleWord(true);            // add newlines by word
        instructions.setEditable(false);                // read-only
        instructions.setText(
            "Please read each question below carefully. To answer a question, select ONE of the numbers that "
            + " is listed for that question."
        );
        
        /* questions portion */
        String[] questions = {
            "I Over the PAST WEEK... (0 = low, 10 = high)",
            "1. What percentage of your time awake were you consciously AWARE OF your tinnitus?",
            "2. How STRONG or LOUD was your tinnitus?",
            "3. What percentage of your time awake were you ANNOYED by your tinnitus?",
            "SC Over the PAST WEEK... (0 = low, 10 = high)",
            "4. Did you feel IN CONTROL in regard to your tinnitus?",
            "5. How easy was it to COPE with your tinnitus?",
            "6. How easy was it to IGNORE your tinnitus?",
            "C Over the PAST WEEK... (0 = low, 10 = high)",
            "7. Your ability to CONCENTRATE?",
            "8. Your ability to THINK CLEARLY?",
            "9. Your ability to FOCUS ATTENTION on other things besides your tinnitus?",
            "SL Over the PAST WEEK... (0 = low, 10 = high)",
            "10. How often did your tinnitus make id difficult to FALL ASLEEP or STAY ASLEEP?",
            "11. How often did your tinnitus cause you difficult in getting AS MUCH SLEEP as you needed?",
            "12. How much of the time did your tinnitus keep you from SLEEPING as DEEPLY or as PEACEFULLY as you would have liked?",
            "A Over the PAST WEEK, how much has your tinnitus interfered with... (0 = low, 10 = high)",
            "13. Your ability to HEAR CLEARLY?",
            "14. Your ability to UNDERSTAND PEOPLE who are talking?",
            "15. Your ability to FOLLOW CONVERSATIONS in a group or at meetings?",
            "R Over the PAST WEEK, how much has your tinnitus interfered with... (0 = low, 10 = high)",
            "16. Your QUIET RESTING ACTIVITIES?",
            "17. Your ability to RELAX?",
            "18. Your ability to enjoy \"PEACE AND QUIET\"?",
            "Q Over the PAST WEEK, how much has your tinnitus interfered with... (0 = low, 10 = high)",
            "19. Your enjoyment of SOCIAL ACTIVITIES?",
            "20. Your ENJOYMENT OF LIFE?",
            "21. Your RELATIONSHIPS with family, friends, and other people?",
            "22. How often did your tinnitus cause you to have difficulty performing your WORK OR OTHER TASKS, such as home maintenance, school work, or caring for children or others?",
            "E Over the PAST WEEK... (0 = low, 10 = high)",
            "23. How ANXIOUS or WORRIED has your tinnitus made you feel?",
            "24. How BOTHERED or UPSET have you been because of your tinnitus?",
            "25. How DEPRESSED were you because of your tinnitus?"
            
            
        };
        //JPanel[] qPanels = new JPanel[25];
//      JPanel qPanel = new JPanel(new GridLayout(25, 4));
        JPanel qPanel = new JPanel(new GridBagLayout());
        int question = 0;
        
        for (int i = 0; i < questions.length; i++)
        {
            JTextArea text = new JTextArea(questions[i]);
            
            text.setSize(500, 20);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setEditable(false);
            text.setBorder(null);
            
            if(questions[i].charAt(0) >= '0' && questions[i].charAt(0) <= '9') {
                JSlider slider = new JSlider(0, 10);
                slider.setMajorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
                slider.setSnapToTicks(true);
                slider.setBackground(Color.WHITE);
                
                sliderMap.put(question++, slider);
                
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 4+i*4;
                c.weightx = 1;
                qPanel.add(slider, c);
                
            }else {
                text.setBackground(null);
            }
            
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3+i*4;
            c.weightx = 1;
            
            qPanel.add(text, c);
            
            if(i == questions.length - 1) {
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 5+i*4;
                c.weightx = 1;
                qPanel.add(submit, c);
            }
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
//      for (int i = 0; i < qPanels.length; i++)
//          panel.add(qPanels[i]);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(qPanel, c);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // adjusted for faster scrolling speed
//        JPanel contentPane = (JPanel) this.getContentPane();
//        contentPane.add(scrollPane);
        
        this.add(scrollPane, BorderLayout.CENTER);      
//      this.setContentPane(contentPane);
        
        this.setTitle("Project 3 - TFI");
//      this.setSize(6000, 6000);                                   // width x height
//      Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize(); // set max size of screen
//      this.setMaximumSize(DimMax);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = env.getMaximumWindowBounds();
        System.out.println("Screen Bounds: " + bounds );
        
//      this.setLayout(new BorderLayout());                     // best layout
//      this.setSize(Math.min(600, bounds.width),Math.min(600, bounds.height));
        this.pack();                                            // pack margins
//      this.setSize(Math.min(this.getWidth(), bounds.width),Math.min(this.getHeight(), bounds.height));
        this.setLocationRelativeTo(null);                       // centered relative to monitor screen
        this.setVisible(true);                                  // display the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // close on exit
    }
}

