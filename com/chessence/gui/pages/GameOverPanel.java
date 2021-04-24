package com.chessence.gui.pages;

import com.chessence.gui.pages.components.Board;
import com.chessence.gui.pages.components.HorizontalLine;
import com.chessence.gui.pages.components.HorizontalSpace;
import com.chessence.gui.pages.components.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameOverPanel extends ParentPanel implements ActionListener {

    public static JLabel heading;
    private JButton goBackButton;

    public GameOverPanel(JFrame frame, CardLayout cardLayout) {
        super(frame, cardLayout);
        this.setLayout(new FlowLayout());

        //getting current frame size:
        Rectangle r = frame.getBounds();
        int heightOfFrame = r.height;
        int widthOfFrame = r.width;

        this.add(new HorizontalSpace(widthOfFrame, (int) (heightOfFrame * 0.4)));

        //Creating the heading:
        heading = new JLabel("Player WINS!");
        heading.setLayout(new BorderLayout());
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(getFont("Roboto-Medium", getResponsiveFontSize(68)));
        heading.setForeground(new Color(0x895158));
        this.add(heading, BorderLayout.CENTER);

        //adding a horizontal line:
        this.add(new HorizontalLine((int) (0.90 * widthOfFrame), 5, new Color(0x895158)));
        this.add(new HorizontalSpace(widthOfFrame, (int) (heightOfFrame * 0.074074)));

        //Creating the button to go back to lobby:
        int buttonWidth = (int) (widthOfFrame * ((float) 600 / (float) 1920));
        int buttonHeight = (int) (heightOfFrame * ((float) 100 / (float) 1080));
        int buttonFontSize = getResponsiveFontSize(40);

        goBackButton = new RoundedButton("Go Back To Lobby", new Color(0xEE9946), new Color(0xbd6e22), 30);
        goBackButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        goBackButton.setBounds(0, 0, buttonWidth, buttonHeight);
        goBackButton.setFont(getFont("Rambla-Bold", buttonFontSize));
        goBackButton.setForeground(new Color(0x321F28));
        goBackButton.addActionListener(this);


        this.add(goBackButton);
    }

    public static void updateWinner(String winnersName) {
        heading.setText(winnersName + " WINS!");
    }

    public static void forfeitUpdate(String forfeiterPlayersName){
        heading.setText(forfeiterPlayersName + " has forfeited!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == goBackButton) {
            //clear the game data (boardMatrix etc etc) here before going to CreateRoom
            //------------------
            Board.initializeBoard();
            Board.initializeTiles();
            GameScreenPanel.board.repaint();
            //-----------------

            cardLayout.show(container, "CreateRoom");
        }
    }
}

