import java.awt.*;
import javax.swing.*;

/**
 * A simple GUI-based bank account application that allows users to deposit,
 * withdraw, and view their current balance.
 */
public class BankAppGUI {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel balanceLabel;
    private final BankAccount account;

    /**
     * Constructs the BankAppGUI and initializes the GUI components.
     */
    public BankAppGUI() {
        account = new BankAccount(0.0);

        frame = new JFrame("Bank Balance Application");
        panel = new JPanel();
        balanceLabel = new JLabel("Current Balance: $0.00");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        panel.setLayout(new GridLayout(3, 1));
        panel.add(balanceLabel);
        panel.add(depositButton);
        panel.add(withdrawButton);

        frame.add(panel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        depositButton.addActionListener(_e -> {
            String input = JOptionPane.showInputDialog("Enter deposit amount:");
            try {
                double amount = Double.parseDouble(input);
                account.deposit(amount);
                updateBalance();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        withdrawButton.addActionListener(_e -> {
            String input = JOptionPane.showInputDialog("Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(input);
                account.withdraw(amount);
                updateBalance();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });
    }

    /**
     * Updates the balance label with the current balance.
     */
    private void updateBalance() {
        balanceLabel.setText(String.format("Current Balance: $%.2f", account.getBalance()));
    }

    /**
     * Launches the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankAppGUI::new);
    }
}
