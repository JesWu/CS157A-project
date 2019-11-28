import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

/**
 * Class file containing the TFI questionnaire.
 * @author Jeffrey Wu
 *
 */
public class TFI extends JFrame{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the TFI questionnaire including connection to database.
     * Contains action listeners for submit button and submits entered information into mySQL database.
     * 
     * @param con the connection being used through this GUI
     */
    public TFI(Connection con)
    {
        this.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        
        /* name and date portion */
        JPanel top = new JPanel();
        JLabel name = new JLabel("Your Name: ");
        JTextField nameText = new JTextField("First Last", 15);
        JLabel date = new JLabel("Date: ");
        JTextField dateText = new JTextField("YYYY-MM-DD",10);
        JButton submit = new JButton("Submit");
        
		dateText.setText(DataModel.date);
		dateText.setEditable(false);
		dateText.setBackground(Color.WHITE);
		nameText.setText(DataModel.patient);
		nameText.setEditable(false);
		nameText.setBackground(Color.WHITE);
        
        TreeMap<Integer, JSlider> sliderMap = new TreeMap<>();
        
        
        submit.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String query = "SELECT MAX(id) FROM TFI";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                int id = rs.first() ? rs.getInt(1) + 1 : 0;
                Integer i = 0;
                Integer sc = 0;
                Integer c = 0;
                Integer sl = 0;
                Integer a = 0;
                Integer r = 0;
                Integer q = 0;
                Integer em = 0;
                Integer total = 0;
                int invalids = 0;
                int subInvalids = 0;
                int sum = 0;
                //System.out.println("You just submitted your entry!, id of " + id);
                String[] namep = nameText.getText().split(" ");
                StringBuilder s = new StringBuilder();
                s.append("insert into TFI values(" + id + ",'" + dateText.getText() + "','" + namep[0] + "','" + namep[1] + "',");
                for(Map.Entry<Integer, JSlider> entry: sliderMap.entrySet()) {
                    if(entry.getValue().getValue() == -1 || entry.getValue().getValue() == -10) {
                        s.append("null,");
                    }else { 
                        s.append(entry.getValue().getValue() + ",");
                    }
                    if(entry.getKey() < 3 && entry.getValue().getValue() != -1 && entry.getValue().getValue() != -10) {
                        if(entry.getKey() == 0 || entry.getKey() == 2) {
                            i += (entry.getValue().getValue() / 10);
                        }else {
                            i += entry.getValue().getValue();
                        }
                    }else if(entry.getKey() < 6 && entry.getValue().getValue() != -1) {
                        sc += entry.getValue().getValue();
                    }else if(entry.getKey() < 9 && entry.getValue().getValue() != -1) {
                        c += entry.getValue().getValue();
                    }else if(entry.getKey() < 12 && entry.getValue().getValue() != -1) {
                        sl += entry.getValue().getValue();
                    }else if(entry.getKey() < 15 && entry.getValue().getValue() != -1) {
                        a += entry.getValue().getValue();
                    }else if(entry.getKey() < 18     && entry.getValue().getValue() != -1) {
                        r += entry.getValue().getValue();
                    }else if(entry.getKey() < 22 && entry.getValue().getValue() != -1) {
                        q += entry.getValue().getValue();
                    }else if(entry.getKey() < 25 && entry.getValue().getValue() != -1) {
                        em += entry.getValue().getValue();
                    }
                }
                
                System.out.println(i + " " + sc + " " + c + " " + sl + " " + a + " " + r + " " + q + " " + em);
                for(Map.Entry<Integer, JSlider> entry: sliderMap.entrySet()) {
                    if(entry.getValue().getValue() == -1) {
                        invalids++;
                        subInvalids++;
                    }else {
                        sum += entry.getValue().getValue();
                    }
                    if(entry.getKey() == 2) {
                        if(subInvalids == 3) {
                            s.append("null,");
                        }else {
                            i = (i / (3 - subInvalids)) * 10;
                            s.append(i + ",");
                        }
                        subInvalids = 0;
                    }else if(entry.getKey() == 5) {
                        if(subInvalids == 3) {
                            s.append("null,");
                        }else {
                            sc = (sc / (3 - subInvalids)) * 10;
                            s.append(sc + ",");
                        }
                        subInvalids = 0;
                    }else if(entry.getKey() == 8) {
                        if(subInvalids == 3) {
                            s.append("null,");
                        }else {
                            c = (c / (3 - subInvalids)) * 10;
                            s.append(c + ",");
                        }
                        subInvalids = 0;
                    }
                    else if(entry.getKey() == 11) {
                        if(subInvalids == 3) {
                            s.append("null,");
                        }else {
                            sl = (sl / (3 - subInvalids)) * 10;
                            s.append(sl + ",");
                        }
                        subInvalids = 0;
                    }
                    else if(entry.getKey() == 14) {
                        if(subInvalids == 3) {
                            s.append("null,");
                        }else {
                            a = (a / (3 - subInvalids)) * 10;
                            s.append(a + ",");
                        }
                        subInvalids = 0;
                    }
                    else if(entry.getKey() == 17) {
                        if(subInvalids == 3) {
                            s.append("null,");
                        }else {
                            r = (r / (3 - subInvalids)) * 10;
                            s.append(r + ",");
                        }
                        subInvalids = 0;
                    }else if(entry.getKey() == 21) {
                        if(subInvalids == 4) {
                            s.append("null,");
                        }else {
                            q = (q / (4 - subInvalids)) * 10;
                            s.append(q + ",");
                        }
                        subInvalids = 0;
                    }else if(entry.getKey() == 24) {
                        if(subInvalids == 3) {
                            s.append("null,");
                        }else {
                            em = (em / (3 - subInvalids)) * 10;
                            s.append(em + ",");
                        }
                        subInvalids = 0;
                    }
                    
                }
                if(invalids > 6) {
                    s.append("null");
                }else {
                    total = (sum / (25 - invalids)) * 10;
                    s.append(total);
                }
                s.append(")");
                System.out.println(s.toString());
                st.execute(s.toString());
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        }
        });
        
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
            "10. How often did your tinnitus make it difficult to FALL ASLEEP or STAY ASLEEP?",
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
                JSlider slider;
                if(i == 1 || i == 3) {
                    slider = new JSlider(-10, 100);
                    slider.setMajorTickSpacing(10);
                    slider.setValue(-10);
                }else {
                    slider = new JSlider(-1, 10);
                    slider.setMajorTickSpacing(1);
                    slider.setValue(-1);
                }
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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(qPanel, c);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // adjusted for faster scrolling speed
        
        this.add(scrollPane, BorderLayout.CENTER);      
        
        this.setTitle("Project 3 - TFI");
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = env.getMaximumWindowBounds();
        System.out.println("Screen Bounds: " + bounds );
        
        this.pack();                                            // pack margins
        this.setLocationRelativeTo(null);                       // centered relative to monitor screen
        this.setVisible(true);                                  // display the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // close on exit
    }
}

