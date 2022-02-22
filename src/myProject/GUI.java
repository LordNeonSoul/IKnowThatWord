package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Juan Pablo Pantoja Gutierrez juan.pablo.pantoja@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header headerProject;
    ModelIKnowThatWord modelIKnowThatWord;
    private JTextField userNameField;
    private JButton yes,no, help, exit, continueButton, register;
    private JPanel gamePanel, dataPanel;
    private JTextArea data, level, start, word, time, hits;
    private Escucha escucha;
    private Timer timer, tempo;
    private int seconds, interf, flag, hitsLevel, hitsWonLevel;
    private boolean info;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("I know that word");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object and Control Object
        escucha = new Escucha();
        //Set up JComponents

        data = new JTextArea(1, 2);
        start = new JTextArea(1, 2);
        time = new JTextArea(1, 2);
        hits = new JTextArea(1, 2);

        interf=1;
        flag=0;
        info=false;

        headerProject = new Header("I Know That Word!", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        help = new JButton("?");
        help.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(help,constraints);

        exit = new JButton("Exit");
        exit.addActionListener(escucha);
        constraints.gridx=2;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(exit,constraints);

        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(500,220));
        gamePanel.setBorder(BorderFactory.createTitledBorder("Your words"));
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(gamePanel,constraints);

        dataPanel = new JPanel();
        dataPanel.setPreferredSize(new Dimension(500,100));
        dataPanel.setBorder(BorderFactory.createTitledBorder("Your data"));
        constraints.gridx=1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(dataPanel,constraints);

        userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(250, 20));
        constraints.gridx=1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(userNameField,constraints);
        gamePanel.add(userNameField);

        start.setText("Into an User or create one");
        start.setBackground(null);
        start.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(start,constraints);
        dataPanel.add(start);

        register = new JButton("Register");
        constraints.gridx=2;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        add(register,constraints);
        register.addActionListener(escucha);
        gamePanel.add(register);

        continueButton = new JButton("Continue");
        continueButton.addActionListener(escucha);

        yes = new JButton("Yes");
        yes.addActionListener(escucha);

        no = new JButton("No");
        no.addActionListener(escucha);

        tempo = new Timer(1000, escucha);
        timer = new Timer(3000, escucha);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==register){
                String usernameTag = JOptionPane.showInputDialog("Digite su nombre");
                if (!usernameTag.equals("")) {
                    if(flag==0){
                        modelIKnowThatWord = new ModelIKnowThatWord(userNameField.getText());
                        gamePanel.remove(userNameField);
                        if (modelIKnowThatWord.verifyUser){
                            start.setText("Bienvenido "+ userNameField.getText()+
                                    "\n¡entrenemos tu memoria!");
                        }else{
                            start.setText("Bienvenido de nuevo "+ userNameField.getText()+
                                    "\nHas aprobado el nivel "+ modelIKnowThatWord.getPassedLevels());
                            dataPanel.repaint();
                            if (modelIKnowThatWord.getPassedLevels()==0){
                                start.setText("Bienvenido de nuevo "+ userNameField.getText()+
                                        "\nno aprobaste el nivel 1");
                            }
                        }
                        register.setVisible(false);
                    }else{

                    }

                }
            }
        }
    }
}
