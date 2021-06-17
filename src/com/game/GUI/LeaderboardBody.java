package com.game.GUI;

import com.game.Config.ReadingFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * class which saves score to file
 *
 */

public class LeaderboardBody extends JPanel implements ActionListener {

    private JLabel nick,score;
    private JTextField textField;
    private JButton button;
    private Leaderboard leaderboard;
    private String userText;
    private GamePanel panel;
    private BufferedWriter writer;
    private File leaderboardfile;
    private ArrayList<Integer> scores;
    private ArrayList<String> nicknames;

    LeaderboardBody(GamePanel panel, Leaderboard leaderboard) throws IOException {
        this.leaderboard=leaderboard;
        this.panel=panel;
        nick=new JLabel("nick: ");
        textField=new JTextField();
        button=new JButton("Enter");
        score=new JLabel("Score: "+ panel.getScore());

        leaderboard.getContainer().setLayout(null);
        nick.setBounds(50,50,100,30);
        score.setBounds(50,100,100,30);
        textField.setBounds(100,50,150,30);
        button.setBounds(150,100,100,30);
        textField.addActionListener(this);
        button.addActionListener(this);
        leaderboard.getContainer().add(nick);
        leaderboard.getContainer().add(textField);
        leaderboard.getContainer().add(button);
        leaderboard.getContainer().add(score);

    }
    @Override

    /**
     * starts the operation of the buttons
     *
     */

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(button.getText())){
            userText = textField.getText();
            try {
                load();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            try {
                writer=new BufferedWriter(new FileWriter("C:\\Users\\Maciek\\IdeaProjects\\proze21l_dabkowski_304144\\Config\\leaderboard.txt"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                for(int i=0;i<5;i++){
                    if(scores.get(i)<panel.getScore()){
                        scores.add(i,panel.getScore());
                        nicknames.add(i,userText);
                        scores.remove(5);
                        nicknames.remove(5);
                        break;
                    }
                }
                for(int i=0;i<5;i++){
                    writer.write(nicknames.get(i)+" "+scores.get(i)+"\n");


                }
                writer.close();
            }catch (IOException a){
                System.out.println("Błąd");
            }
            JOptionPane.showMessageDialog(this, "Score saved succesfully!");
            panel.setRunning(true);
            panel.setScore(0);
            panel.setState(panel.getStateMenu());
            leaderboard.setVisible(false);

        }
    }

    /**
     * loading scores from file
     *
     * @throws FileNotFoundException
     */

    public void load() throws FileNotFoundException {
        String path = new ReadingFiles("Config").MaKeFile("leaderboard.txt").toString();
        leaderboardfile = new File(path);
        scores=new ArrayList<>();
        nicknames=new ArrayList<>();
        Scanner scanner = new Scanner(leaderboardfile);
        nicknames.add(scanner.next());
        scores.add(scanner.nextInt());
        nicknames.add(scanner.next());
        scores.add(scanner.nextInt());
        nicknames.add(scanner.next());
        scores.add(scanner.nextInt());
        nicknames.add(scanner.next());
        scores.add(scanner.nextInt());
        nicknames.add(scanner.next());
        scores.add(scanner.nextInt());
    }

    public String getUserText() {
        return userText;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public ArrayList<String> getNicknames() {
        return nicknames;
    }
}
