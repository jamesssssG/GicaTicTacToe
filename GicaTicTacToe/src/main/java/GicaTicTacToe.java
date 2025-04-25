// Tic Tac Toe is fun!

import java.awt.*;
import java.awt.event.*;

public class GicaTicTacToe extends Frame implements ActionListener {
    private Button[][] buttons = new Button[3][3];
    private boolean xTurn = true;
    private Label statusLabel = new Label("Player X's Turn");
    private Button newGameButton = new Button("New Game");

    public GicaTicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 350);
        setLayout(new BorderLayout());

        Panel gridPanel = new Panel(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 40);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Button("");
                buttons[row][col].setFont(buttonFont);
                buttons[row][col].addActionListener(this);
                gridPanel.add(buttons[row][col]);
            }
        }

        Panel bottomPanel = new Panel(new BorderLayout());
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            resetGame();
        }
});
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(newGameButton, BorderLayout.EAST);

        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        if (!clicked.getLabel().equals("")) return;

        clicked.setLabel(xTurn ? "X" : "O");
        if (checkWinner()) {
            statusLabel.setText("Player " + (xTurn ? "X" : "O") + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText("Player " + (xTurn ? "X" : "O") + "'s Turn");
        }
    }

    private boolean checkWinner() {
        String currentPlayer = xTurn ? "X" : "O";

        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getLabel().equals(currentPlayer) &&
                buttons[i][1].getLabel().equals(currentPlayer) &&
                buttons[i][2].getLabel().equals(currentPlayer)) return true;

            if (buttons[0][i].getLabel().equals(currentPlayer) &&
                buttons[1][i].getLabel().equals(currentPlayer) &&
                buttons[2][i].getLabel().equals(currentPlayer)) return true;
        }

        if (buttons[0][0].getLabel().equals(currentPlayer) &&
            buttons[1][1].getLabel().equals(currentPlayer) &&
            buttons[2][2].getLabel().equals(currentPlayer)) return true;

        if (buttons[0][2].getLabel().equals(currentPlayer) &&
            buttons[1][1].getLabel().equals(currentPlayer) &&
            buttons[2][0].getLabel().equals(currentPlayer)) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                if (b.getLabel().equals("")) return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setLabel("");
                b.setEnabled(true);
            }
        }
        xTurn = true;
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        new GicaTicTacToe();
    }
}