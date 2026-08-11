import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class TheCalculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEq1, txtEq2, txtX;
	private JLabel lblEq1, lblEq2, lblOperation, lblFormat, lblREq1;
	private JLabel lblResult1, lblX, lblTheOutput;
	private JLabel lblResultX, lblResultRoot;
	private JButton btnCalc1, btnCalc2;
	private JComboBox combFormat, combOperation;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TheCalculator frame = new TheCalculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TheCalculator() {
		setBackground(new Color(240, 240, 240));
		setTitle("Math Equation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 678);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(249, 246, 244));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEq1 = new JLabel("First Equation");
		lblEq1.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblEq1.setBounds(25, 10, 138, 42);
		contentPane.add(lblEq1);

		lblEq2 = new JLabel("Second Equation");
		lblEq2.setForeground(new Color(30, 58, 138));
		lblEq2.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblEq2.setBounds(25, 62, 138, 42);
		contentPane.add(lblEq2);

		lblOperation = new JLabel("Operation Selected");
		lblOperation.setForeground(new Color(30, 58, 138));
		lblOperation.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblOperation.setBounds(25, 114, 146, 42);
		contentPane.add(lblOperation);

		btnCalc1 = new JButton("Calculation");
		btnCalc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PolynomialCalculator result = calcResult();
					if (result == null) {
						return;}

					String str = result.toString();
					String combSelected = (String) combFormat.getSelectedItem();

					if (str == null || str.trim().isEmpty()) {
						JOptionPane.showMessageDialog(TheCalculator.this, "There's no result to show. Please verify your inputs.", "Missing Result",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					String eqFormat = "";
					InPostPreFix adapter = new InPostPreFix();
					String curing = adapter.preprocessExpression(str);		            

					if ("Infix".equals(combSelected)) {
						eqFormat = str;
					} else if ("Postfix".equals(combSelected)) {
						eqFormat = adapter.convertToPostfix(curing);
					} else {
						eqFormat = adapter.convertToPrefix(curing);
					}

					lblResult1.setText(eqFormat);

				} catch (Exception ee) {
					JOptionPane.showMessageDialog(TheCalculator.this, ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});



		btnCalc1.setBackground(new Color(128, 128, 255));
		btnCalc1.setFont(new Font("Tahoma", Font.BOLD, ١٧));
		btnCalc1.setBounds(97, 285, 146, 52);
		contentPane.add(btnCalc1);

		txtEq1 = new JTextField();
		txtEq1.setForeground(new Color(51, 51, 51));
		txtEq1.setBounds(181, 21, 152, 27);
		contentPane.add(txtEq1);
		txtEq1.setColumns(10);

		txtEq2 = new JTextField();
		txtEq2.setColumns(10);
		txtEq2.setBounds(181, 73, 152, 27);
		contentPane.add(txtEq2);

		combOperation = new JComboBox();
		combOperation.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		combOperation.setModel(new DefaultComboBoxModel(new String[] {"+", "-", "*", "/"}));
		combOperation.setBounds(181, 118, 152, 34);
		contentPane.add(combOperation);

		lblFormat = new JLabel("Equation Format");
		lblFormat.setForeground(new Color(30, 58, 138));
		lblFormat.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblFormat.setBounds(25, 167, 138, 42);
		contentPane.add(lblFormat);

		combFormat = new JComboBox();
		combFormat.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		combFormat.setModel(new DefaultComboBoxModel(new String[] {"Infix", "Postfix", "Prefix"}));
		combFormat.setBounds(181, 171, 152, 34);
		contentPane.add(combFormat);

		lblREq1 = new JLabel("Result Equation");
		lblREq1.setForeground(new Color(30, 58, 138));
		lblREq1.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblREq1.setBounds(25, 219, 146, 42);
		contentPane.add(lblREq1);

		lblResult1 = new JLabel("");
		lblResult1.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblResult1.setBounds(176, 215, 360, 42);
		contentPane.add(lblResult1);

		lblX = new JLabel("Enter x value");
		lblX.setForeground(new Color(30, 58, 138));
		lblX.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblX.setBounds(25, 347, 146, 42);
		contentPane.add(lblX);

		lblTheOutput = new JLabel("The output");
		lblTheOutput.setForeground(new Color(30, 58, 138));
		lblTheOutput.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblTheOutput.setBounds(10, 452, 86, 42);
		contentPane.add(lblTheOutput);

		txtX = new JTextField();
		txtX.setColumns(10);
		txtX.setBounds(176, 355, 152, 34);
		contentPane.add(txtX);

		btnCalc2 = new JButton("Calculation");
		btnCalc2.setBackground(new Color(128, 128, 255));
		btnCalc2.setFont(new Font("Tahoma", Font.BOLD, ١٧));
		btnCalc2.setBounds(97, 399, 138, 52);
		contentPane.add(btnCalc2);
		btnCalc2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PolynomialCalculator result = calcResult();
					if (result == null) {
						JOptionPane.showMessageDialog(TheCalculator.this,
								"You cannot evaluate x for a division result.\nPlease select another operation",
								"Invalid Operation", JOptionPane.WARNING_MESSAGE);
						return;
					}

					String xInput = txtX.getText();
					double valueX = Double.parseDouble(xInput);
					double evaluated = result.compensationX(valueX);
					lblResultX.setText("" + evaluated);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(TheCalculator.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});




		lblResultX = new JLabel("");
		lblResultX.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblResultX.setBounds(181, 452, 141, 42);
		contentPane.add(lblResultX);
		Color background = new Color(244, 246, 249);
		Color buttonColor = new Color(79, 70, 229);
		contentPane.setBackground(background);
		lblEq1.setForeground(new Color(30, 58, 138));
		btnCalc1.setBackground(buttonColor);
		btnCalc1.setForeground(new Color(255, 255, 255));
		btnCalc2.setBackground(buttonColor);
		btnCalc2.setForeground(Color.WHITE);

		JButton btnRoot = new JButton("Root");
		btnRoot.setIcon(new ImageIcon("images\\TheRoot.png"));
		btnRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String combSelected = (String) combFormat.getSelectedItem();
					if (!"Infix".equals(combSelected)) {
						JOptionPane.showMessageDialog(TheCalculator.this,
								"Roots can only be calculated when the equation is in Infix format.\nPlease switch to Infix format.",
								"Invalid Format", JOptionPane.WARNING_MESSAGE);
						return;
					}

					String equation = lblResult1.getText();
					if (equation == null || equation.trim().isEmpty()) {
						JOptionPane.showMessageDialog(TheCalculator.this,
								"No equation to solve, Please calculate an equation first.","Error", JOptionPane.ERROR_MESSAGE);								
						return;
					}

					PolynomialCalculator poly = PolynomialCalculator.parseEquation(equation);
					double[] roots = poly.solveEquation();

					if (roots.length == 0) {
						lblResultRoot.setText("No real roots found for: " + equation);
					} else {
						StringBuilder sb = new StringBuilder("The Roots is : ");
						int i = 0;
						while (i < roots.length) {
							sb.append(String.format(Locale.US, "x = %.2f, ", roots[i]));
							i++;
						}

						String resultText = sb.substring(0, sb.length() - 2); 
						lblResultRoot.setText(resultText);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(TheCalculator.this,"Error solving equation: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});



		btnRoot.setForeground(Color.WHITE);
		btnRoot.setFont(new Font("Tahoma", Font.BOLD, ١٧));
		btnRoot.setBackground(new Color(128, 128, 255));
		btnRoot.setBounds(97, 525, 146, 52);
		contentPane.add(btnRoot);

		lblResultRoot = new JLabel("");
		lblResultRoot.setFont(new Font("Tahoma", Font.PLAIN, ١٧));
		lblResultRoot.setBounds(10, 599, 526, 42);
		contentPane.add(lblResultRoot);
		validate();

	}
	public PolynomialCalculator calcResult() throws Exception {
		String eq1 = txtEq1.getText();
		String eq2 = txtEq2.getText();
		String operation = (String) combOperation.getSelectedItem();

		PolynomialCalculator poly1 = PolynomialCalculator.parseEquation(eq1);
		PolynomialCalculator poly2 = PolynomialCalculator.parseEquation(eq2);
		PolynomialCalculator result = new PolynomialCalculator();

		switch (operation) {
		case "+":
			result = PolynomialCalculator.getSummation(poly1, poly2);
			break;
		case "-":
			result = PolynomialCalculator.getSubtracttion(poly1, poly2);
			break;
		case "*":
			result = PolynomialCalculator.multiply(poly1, poly2);
			break;
		case "/":
			PolynomialCalculator[] divResult = PolynomialCalculator.divide(poly1, poly2);
			PolynomialCalculator quotient = divResult[0];
			PolynomialCalculator remainder = divResult[1];
			lblResult1.setText("Quotient: " + quotient + " , Remainder: " + remainder);
			return null;
		}

		return result;
	}

}