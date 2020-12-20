import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UI {

    private Controller ctrl = new Controller();

    private String[][] buttonTextData = {{"ADD", "ARG", "SINH"},
            {"SUB", "SIN", "COSH"},
            {"MUL", "COS", "SQRT"},
            {"DIV", "TAN", "LOG"},
            {"EXP", "MOD", "CHS"}};


    private JPanel mainPanelWindow() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));



        // Text
        JTextField rez = new JTextField();
        JTextField imz = new JTextField();
        JTextField res = new JTextField();
        JPanel textFiled = new JPanel();

        textFiled.setPreferredSize(new Dimension(350, 300));

        JLabel rezText = new JLabel("              Rez: ");
        JLabel blankText1 = new JLabel("    ");
        JLabel imzText = new JLabel("              Imz: ");
        JLabel blankText2 = new JLabel("    ");
        JLabel resText = new JLabel("         Res:");

        rezText.setHorizontalTextPosition(JLabel.CENTER);
        rezText.setVerticalTextPosition(JLabel.CENTER);
        rez.setPreferredSize(new Dimension(350, 30));
        rez.setAlignmentX(Component.LEFT_ALIGNMENT);
        rez.setText("Enter real part");
        rez.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (rez.getText().equals("Enter real part"))
                    rez.setText("");
            }

            public void focusLost(FocusEvent e) {
                if (rez.getText().equals(""))
                    rez.setText("Enter real part");
            }
        });

        imzText.setHorizontalTextPosition(JLabel.LEFT);
        imzText.setVerticalTextPosition(JLabel.CENTER);
        imz.setPreferredSize(new Dimension(350, 30));
        imz.setAlignmentX(Component.LEFT_ALIGNMENT);
        imz.setText("Enter imaginary part");
        imz.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (imz.getText().equals("Enter imaginary part"))
                    imz.setText("");
            }

            public void focusLost(FocusEvent e) {
                if (imz.getText().equals(""))
                    imz.setText("Enter imaginary part");
            }
        });

        res.setPreferredSize(new Dimension(350, 30));
        res.setAlignmentX(Component.LEFT_ALIGNMENT);
        res.setText("Result");
        res.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (res.getText().equals("Result"))
                    res.setText("");
            }

            public void focusLost(FocusEvent e) {
                if (res.getText().equals(""))
                    res.setText("Result");
            }
        });

        JButton help = new JButton();

        help.setPreferredSize(new Dimension(100, 50));
        help.setText("HELP");
        help.setAlignmentX(Component.CENTER_ALIGNMENT);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog info = new JDialog();
                String content = "ADD - Add TWO or more complex numbers.\n" +
                        "SUB - Subtract TWO or more complex numbers.\n" +
                        "MUL - Multiply TWO or more complex numbers.\n" +
                        "DIV - Divide TWO or more complex numbers.\n" +
                        "EXP - Exponential of ONE complex number.\n" +
                        "ARG - Argument of ONE complex number.\n" +
                        "SIN, COS, TAN - Trig. functions of ONE complex number.\n" +
                        "MOD - Modulus of ONE complex number.\n" +
                        "SINH, COSH - Hyperbolic trig. functions of ONE complex number.\n" +
                        "SQRT - Square root of ONE complex number.\n" +
                        "LOG - Log function for ONE complex number.\n" +
                        "CHS - Change sign of ONE complex number.\n\n" +
                        "   For add, sub, mul and div, you can enter " +
                        "multiple values, separated by a comma and a space.\n" +
                        "   For the rest of the operations, only one value will be taken. " +
                        "If more values are entered, only the first will be taken.\n";
                info.setTitle("Help window");
                info.setPreferredSize(new Dimension(500,420));
                info.setResizable(false);
                info.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                JTextArea txtArea = new JTextArea(content, 20, 8);
                txtArea.setFont(new Font("SansSerif", Font.BOLD, 16));
                txtArea.setLineWrap(true);
                txtArea.setWrapStyleWord(true);
                txtArea.setEditable(false);
                txtArea.setForeground(Color.blue);
                info.add(txtArea);
                info.pack();
                info.setVisible(true);
            }
        });

        textFiled.add(rezText);
        textFiled.add(rez);
        textFiled.add(blankText1);
        textFiled.add(imzText);
        textFiled.add(imz);
        textFiled.add(blankText2);
        textFiled.add(resText);
        textFiled.add(res);
        textFiled.add(help);

        // Button layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 3));
        buttonPanel.setMaximumSize(new Dimension(300, 350));
        JButton btn;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                btn = new JButton();
                btn.setPreferredSize(new Dimension(100, 50));
                btn.setText(buttonTextData[i][j]);
                JButton finalBtn = btn;
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String cmd = finalBtn.getText();
                        Complex result = ctrl.execCmd(cmd, rez.getText(), imz.getText());
                        res.setText(result.toString());
                    }
                });
                buttonPanel.add(btn);
            }
        }

        mainPanel.add(textFiled);
        mainPanel.add(buttonPanel);
        return mainPanel;
    }

    public void main_window() {
        JFrame frame = new JFrame(" Complex Number Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(mainPanelWindow());
        frame.setPreferredSize(new Dimension(800, 400));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}