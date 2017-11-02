import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the main class. It first creates two circuits, checks their
 * equivalence and prints the result to the console. It also prompts the user to
 * enter the number of random circuits to create (say n). It then creates n
 * random circuits of maximal depth 5, and prints the circuits and their truth
 * values to the console.
 * 
 * @author musard
 * 
 */

public class CircuitMain {
    private static final String[] SET_OF_VARIABLES = new String[]{ "v", "w", "x", "y", "z", "a", "b" };
    private static final String[] TRUE_VARIABLES = new String[]{ "v", "y", "a" };

    private static final int CIRCUIT_DEPTH = 5;

    private static Assignment assignment;

    private static final Font defaultFont = new Font("Andale Mono", Font.PLAIN, 10);

    private static final int HEIGHT = 427;
    private static final int WIDTH = 600;

    private static JScrollPane scrollPane;
    private static JPanel contentPane;

	public static void main(String[] args) {
        assignment = new Assignment(TRUE_VARIABLES);

        drawGUI();
        initialize();
	}

    static Variable[] freeVariables(Circuit circuit) {
        ArrayList<Variable> varList = new ArrayList<>();
        for (String varName : CircuitMain.SET_OF_VARIABLES) {
            if (circuit.toString().contains(varName)) {
                varList.add(new Variable(varName));
            }
        }

        Variable[] varArray = new Variable[varList.size()];
        for (int i = 0; i < varList.size(); i++) {
            varArray[i] = varList.get(i);
        }

        return varArray;
    }

    private static void drawGUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame();
        frame.setFont(defaultFont);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds((screenSize.width - WIDTH) /2, (screenSize.height - HEIGHT) /2, WIDTH, HEIGHT);

        JLabel label = new JLabel("Number of random circuits:");
        label.setBounds(5, 5, 160, 20);
        label.setFont(defaultFont);
        frame.add(label);

        JTextField textField = new JTextField("2000");
        textField.setBounds(170, 5, 45, 20);
        textField.setFont(defaultFont);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateCircuits(Integer.parseInt(textField.getText()), CIRCUIT_DEPTH);
                frame.requestFocus();
            }
        });
        frame.add(textField);

        JButton button = new JButton("Generate");
        button.setBounds(WIDTH -275, 5, 100, 20);
        button.setFont(defaultFont);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateCircuits(Integer.parseInt(textField.getText()), CIRCUIT_DEPTH);
            }
        });
        frame.add(button);

        JButton buttonReset = new JButton("Reset");
        buttonReset.setBounds(WIDTH -170, 5, 80, 20);
        buttonReset.setFont(defaultFont);
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialize();
            }
        });
        frame.add(buttonReset);

        JButton buttonClose = new JButton("Close");
        buttonClose.setBounds(WIDTH -85, 5, 80, 20);
        buttonClose.setFont(defaultFont);
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        frame.add(buttonClose);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 30, WIDTH -10, HEIGHT -55);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBar(new JScrollBar(Adjustable.VERTICAL));
        scrollPane.setViewportBorder(null);
        scrollPane.setVisible(true);

        contentPane = new JPanel();
        contentPane.setLayout(new GroupLayout(contentPane));
        contentPane.setBackground(new Color(244, 244, 244));

        scrollPane.setViewportView(contentPane);
        frame.add(scrollPane);

        JLabel debug = new JLabel();
        debug.setBounds(0, 0, 0, 0);

        frame.add(debug);
        frame.setVisible(true);
        frame.requestFocus();
    }

    private static void initialize() {
        Circuit one = new Not(new And(new Variable("a"), new Variable("b")));
        Circuit two = new Or(new Not(new Variable("a")), new Not(new Variable("b")));

        while (contentPane.getComponentCount() > 0) {
            contentPane.remove(0);
        }

        JLabel welcome = new JLabel("Welcome to the DIT948 Circuit Oracle!");
        welcome.setBounds(0, 16 *0, WIDTH -10, 14);
        welcome.setFont(defaultFont);
        contentPane.add(welcome);

        JLabel l0 = new JLabel("I will now check equivalence for the following circuits:");
        l0.setBounds(0, 16 *2, WIDTH -10, 14);
        l0.setFont(defaultFont);
        contentPane.add(l0);

        JLabel l1 = new JLabel("-------------------------------------------------------------------------------------------");
        l1.setBounds(0, 16 *3, WIDTH -10, 14);
        l1.setFont(defaultFont);
        contentPane.add(l1);

        JLabel l2 = new JLabel("  1. " + one.toString() +" : "+ one.isTrueIn(assignment) +", free variables: ");
        for (Variable var : one.freeVariables()) {
            l2.setText(l2.getText() + var + " ");
        }
        l2.setBounds(0, 16 *5, WIDTH -10, 14);
        l2.setFont(defaultFont);
        contentPane.add(l2);

        JLabel l3 = new JLabel("  2. " + two.toString() +" : "+ two.isTrueIn(assignment) +", free variables: ");
        for (Variable var : two.freeVariables()) {
            l3.setText(l3.getText() + var + " ");
        }
        l3.setBounds(0, 16 *6, WIDTH -10, 14);
        l3.setFont(defaultFont);
        contentPane.add(l3);

        JLabel l4 = new JLabel("-------------------------------------------------------------------------------------------");
        l4.setBounds(0, 16 *8, WIDTH -10, 14);
        l4.setFont(defaultFont);
        contentPane.add(l4);

        JLabel l5 = new JLabel("The given circuits are considered to be " + (one.equals(two) ? "equal" : "not equal") + ".");
        l5.setBounds(0, 16 *9, WIDTH -10, 14);
        l5.setFont(defaultFont);
        contentPane.add(l5);

        contentPane.repaint();
        contentPane.setPreferredSize(new Dimension(WIDTH -30, 6 * 16));
        contentPane.scrollRectToVisible(new Rectangle(0, 0, scrollPane.getWidth(), scrollPane.getHeight() -4));
    }

    private static void generateCircuits(int count, int depth) {
        while (contentPane.getComponentCount() > 0) {
            contentPane.remove(0);
        }

        JLabel l0 = new JLabel("I have generated the following random circuits:");
        l0.setBounds(0, 0, WIDTH -10, 14);
        l0.setFont(defaultFont);
        contentPane.add(l0);

        JLabel l1 = new JLabel("-------------------------------------------------------------------------------------------");
        l1.setBounds(0, 16, WIDTH -10, 14);
        l1.setFont(defaultFont);
        contentPane.add(l1);

        for (int i = 0; i < count; i++) {
            Circuit circuit = Circuit.mkRandom(depth, SET_OF_VARIABLES);
            JLabel label = new JLabel(String.format(" %"+Integer.toString(count).length()+"d. ", i+1) + circuit.toString() +" : "+ circuit.isTrueIn(assignment));
            label.setBounds(0, (i+3) * 16, WIDTH -10, 14);
            label.setFont(defaultFont);
            contentPane.add(label);
        }

        contentPane.repaint();
        contentPane.setPreferredSize(new Dimension(WIDTH -30, (count+3) * 16));
        contentPane.scrollRectToVisible(new Rectangle(0, 0, scrollPane.getWidth(), scrollPane.getHeight() -4));
    }
}