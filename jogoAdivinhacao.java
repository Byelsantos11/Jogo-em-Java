import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JogoAdivinhacao extends JFrame {

    private JTextField inputField;
    private JLabel infoLabel;
    private JButton guessButton;
    private JButton restartButton;
    private int numeroAleatorio;
    private int tentativas;

    public JogoAdivinhacao() {
        setTitle("Jogo de Adivinhação");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        initGame();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        infoLabel = new JLabel("Tente adivinhar um número entre 1 e 100:");
        add(infoLabel, BorderLayout.NORTH);

        inputField = new JTextField(10);
        add(inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        guessButton = new JButton("Adivinhar");
        restartButton = new JButton("Reiniciar");
        restartButton.setEnabled(false); 

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarAdivinhacao();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
            }
        });

        buttonPanel.add(guessButton);
        buttonPanel.add(restartButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initGame() {
        Random random = new Random();
        numeroAleatorio = random.nextInt(100) + 1; 
        tentativas = 0;
    }

    private void verificarAdivinhacao() {
        try {
            int guess = Integer.parseInt(inputField.getText());
            tentativas++;

            if (guess == numeroAleatorio) {
                JOptionPane.showMessageDialog(this, "Parabéns! Você acertou em " + tentativas + " tentativas.");
                restartButton.setEnabled(true); 
            } else if (guess < numeroAleatorio) {
                infoLabel.setText("Tente um número maior.");
            } else {
                infoLabel.setText("Tente um número menor.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um número válido.");
        }
        inputField.setText(""); 
    }

    private void reiniciarJogo() {
        initGame();
        infoLabel.setText("Tente adivinhar um número entre 1 e 100:");
        restartButton.setEnabled(false); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JogoAdivinhacao().setVisible(true);
            }
        });
    }
}
