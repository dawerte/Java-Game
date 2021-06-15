package com.game.GUI;

import com.game.Config.ReadingFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeaderboardArray extends JPanel implements ActionListener {

    private JLabel nick,score,bestscores;
    private String[] nickcointainer;
    private int[] scorecointainer;
    private File leaderboardfile;
    private int temp=0;
    private JButton b;
    private Leaderboard2 leaderboard;
    private GamePanel panel;
    LeaderboardArray(GamePanel panel,Leaderboard2 leaderboard) throws FileNotFoundException {
        this.panel=panel;
        this.leaderboard=leaderboard;
        nickcointainer=new String[5];
        scorecointainer=new int[5];
        ReadLeaderboard();
        leaderboard.getContainer().setLayout(null);
        bestscores = new JLabel("Best scores");
        b=new JButton("Back to Menu");
        bestscores.setBounds(150,10,100,30);
        b.setBounds(150,400,100,30);
        leaderboard.getContainer().add(bestscores);
        leaderboard.getContainer().add(b);
        b.addActionListener(this);
        for(int i=0;i<5;i++) {
            nick = new JLabel("Nick: "+ nickcointainer[i]);
            score = new JLabel("Score: " + scorecointainer[i]);
            nick.setBounds(75,50+temp,100,30);
            score.setBounds(200,50+temp,100,30);
            leaderboard.getContainer().add(nick);
            leaderboard.getContainer().add(score);
            temp=temp+25;
        }


    }
    public void ReadLeaderboard() throws FileNotFoundException {
        String path = new ReadingFiles("Config").MaKeFile("leaderboard.txt").toString();
        leaderboardfile = new File(path);
        Scanner scanner = new Scanner(leaderboardfile);
        nickcointainer[0]=scanner.next();
        scorecointainer[0] = scanner.nextInt();
        nickcointainer[1]= scanner.next();
        scorecointainer[1]= scanner.nextInt();
        nickcointainer[2]= scanner.next();
        scorecointainer[2]= scanner.nextInt();
        nickcointainer[3]= scanner.next();
        scorecointainer[3]= scanner.nextInt();
        nickcointainer[4]= scanner.next();
        scorecointainer[4]= scanner.nextInt();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(b.getText())){
            leaderboard.setVisible(false);
            panel.setState(panel.getStateMenu());
            panel.setRunning(true);
        }
    }
}
