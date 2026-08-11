import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PolynomialCalculator {
	Node head;

	public PolynomialCalculator() {
		this.head = null;
	}

	public PolynomialCalculator(Node head) {
		this.head = head;
	}

	public boolean addPolynomialTerm(double coeff, int exp) {
		if (coeff == 0.0) 
			return false;

		Node newNode = new Node(coeff, exp);
		if (head == null || head.getExponent() < exp) {
			newNode.setNext(head);
			head = newNode;
			return true;
		}
		Node temp = head;
		Node prev = null;

		while (temp != null && temp.getExponent() > exp) {
			prev = temp;
			temp = temp.getNext();
		}

		if (temp != null && temp.getExponent() == exp) {
			temp.setCoefficient(temp.getCoefficient() + coeff);
			if (temp.getCoefficient() == 0.0) {
				if (prev == null) {
					head = temp.getNext();
				} else {
					prev.setNext(temp.getNext());
				}
			}
			return true;
		}

		newNode.setNext(temp);
		if (prev == null) {
			head = newNode;
		} else {
			prev.setNext(newNode);
		}
		return true;
	}


	public static PolynomialCalculator getSummation(PolynomialCalculator link1, PolynomialCalculator link2) {
		PolynomialCalculator ans = new PolynomialCalculator();
		Node polink1 = link1.head;
		Node polink2 = link2.head;

		while (polink1 != null && polink2 != null) {
			if (polink1.getExponent() == polink2.getExponent()) {
				ans.addPolynomialTerm(polink1.getCoefficient() + polink2.getCoefficient(), polink1.getExponent());
				polink1 = polink1.getNext();
				polink2 = polink2.getNext();
			} else if (polink1.getExponent() > polink2.getExponent()) {
				ans.addPolynomialTerm(polink1.getCoefficient(), polink1.getExponent());
				polink1 = polink1.getNext();
			} else {
				ans.addPolynomialTerm(polink2.getCoefficient(), polink2.getExponent());
				polink2 = polink2.getNext();
			}
		}

		while (polink1 != null) {
			ans.addPolynomialTerm(polink1.getCoefficient(), polink1.getExponent());
			polink1 = polink1.getNext();
		}
		while (polink2 != null) {
			ans.addPolynomialTerm(polink2.getCoefficient(), polink2.getExponent());
			polink2 = polink2.getNext();
		}
		return ans;
	}

	public static PolynomialCalculator getSubtracttion(PolynomialCalculator link1, PolynomialCalculator link2) {
		PolynomialCalculator ans = new PolynomialCalculator();
		Node polink1 = link1.head;
		Node polink2 = link2.head;

		while (polink1 != null && polink2 != null) {
			if (polink1.getExponent() == polink2.getExponent()) {
				ans.addPolynomialTerm(polink1.getCoefficient() - polink2.getCoefficient(), polink1.getExponent());
				polink1 = polink1.getNext();
				polink2 = polink2.getNext();
			} else if (polink1.getExponent() > polink2.getExponent()) {
				ans.addPolynomialTerm(polink1.getCoefficient(), polink1.getExponent());
				polink1 = polink1.getNext();
			} else {
				ans.addPolynomialTerm(-polink2.getCoefficient(), polink2.getExponent());
				polink2 = polink2.getNext();
			}
		}

		while (polink1 != null) {
			ans.addPolynomialTerm(polink1.getCoefficient(), polink1.getExponent());
			polink1 = polink1.getNext();
		}
		while (polink2 != null) {
			ans.addPolynomialTerm(-polink2.getCoefficient(), polink2.getExponent());
			polink2 = polink2.getNext();
		}
		return ans;
	}

	public static PolynomialCalculator multiply(PolynomialCalculator link1, PolynomialCalculator link2) {
		PolynomialCalculator ans = new PolynomialCalculator();
		Node polink1 = link1.head;
		while (polink1 != null) {
			Node polink2 = link2.head;
			while (polink2 != null) {
				double coef = polink1.getCoefficient() * polink2.getCoefficient();
				int exp = polink1.getExponent() + polink2.getExponent();
				ans.addPolynomialTerm(coef, exp);
				polink2 = polink2.getNext();
			}
			polink1 = polink1.getNext();
		}
		return ans;
	}

	public static PolynomialCalculator[] divide(PolynomialCalculator numerator, PolynomialCalculator denominator) {
		PolynomialCalculator quotient = new PolynomialCalculator();
		PolynomialCalculator remainder = new PolynomialCalculator();
		Node temp = numerator.head;
		while (temp != null) {
			remainder.addPolynomialTerm(temp.getCoefficient(), temp.getExponent());
			temp = temp.getNext();
		}
		if (denominator.head == null || denominator.head.getCoefficient() == 0.0) {
			throw new ArithmeticException("Division by zero polynomial");
		}

		while (remainder.head != null && remainder.head.getExponent() >= denominator.head.getExponent()) {
			double termCoefficient = remainder.head.getCoefficient() / denominator.head.getCoefficient();
			int termExponent = remainder.head.getExponent() - denominator.head.getExponent();

			quotient.addPolynomialTerm(termCoefficient, termExponent);

			PolynomialCalculator term = new PolynomialCalculator();
			term.addPolynomialTerm(termCoefficient, termExponent);

			PolynomialCalculator toSubtract = multiply(term, denominator);
			remainder = getSubtracttion(remainder, toSubtract);
		}

		return new PolynomialCalculator[]{quotient, remainder};
	}

	public double compensationX(double x) {
		double result = 0.0;
		Node temp = head;
		while (temp != null) {
			result += temp.getCoefficient() * Math.pow(x, temp.getExponent());
			temp = temp.getNext();
		}
		return (result == -0.0) ? 0.0 : result;
	}


	public int getDiscriminant() {
		if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
			throw new IllegalStateException("Not a quadratic equation");
		}
		double a = head.getCoefficient();         
		double b = head.getNext().getCoefficient(); 
		double c = head.getNext().getNext().getCoefficient(); 
		double discriminant = b * b - 4 * a * c;
		return (int) discriminant; 
	}

	public int getDegree() {
		int degree = 0;
		Node temp = head;
		while (temp != null) {
			degree = Math.max(degree, temp.getExponent());
			temp = temp.getNext();
		}
		return degree;
	}

	private double getCoefficientByExponent(int exponent) {
		Node temp = head;
		while (temp != null) {
			if (temp.getExponent() == exponent) return temp.getCoefficient();
			temp = temp.getNext();
		}
		return 0.0;
	}

	public static PolynomialCalculator parseEquation(String equation) {

		PolynomialCalculator result = new PolynomialCalculator();
		StringBuilder noSpaces = new StringBuilder();
		int i = 0;
		while (i < equation.length()) {
			char ch = equation.charAt(i);
			if (!Character.isWhitespace(ch)) {
				noSpaces.append(ch);
			}
			i++;
		}
		equation = noSpaces.toString();
		if (equation.isEmpty()) return result;

		List<String> terms = new ArrayList<String>();
		StringBuilder temp = new StringBuilder();

		for (i=0 ; i < equation.length(); i++) {
			char ch = equation.charAt(i);

			if ((ch == '+' || ch == '-') && i != 0) {
				terms.add(temp.toString());
				temp.setLength(0); 
			}
			temp.append(ch);
		}
		if (temp.length() > 0) {
			terms.add(temp.toString());
		}

		int index = 0;
		while (index < terms.size()) {
			String term = terms.get(index);
			double coef = 0;
			int exp = 0;
			if (term.contains("x")) {
				int indexOfX = term.indexOf("x");
				String coefPart = term.substring(0, indexOfX);
				String expPart = term.substring(indexOfX + 1);
				if (coefPart.isEmpty() || coefPart.equals("+")) {
					coef = 1;}
				else if (coefPart.equals("-")) {
					coef = -1;}
				else {
					coef = Double.parseDouble(coefPart);
				}
				if (expPart.startsWith("^")) {
					exp = Integer.parseInt(expPart.substring(1));
				} else {
					exp = 1;
				}
			} else {
				coef = Double.parseDouble(term);
				exp = 0; }
			
			result.addPolynomialTerm(coef, exp);
			index++;
		}
		return result;
	}

	public double[] solveEquation() {
		if (head == null) return new double[0];
		int degree = getDegree();
		
		if (degree == 1) {
			double a = getCoefficientByExponent(1);
			double b = getCoefficientByExponent(0);
			if (a == 0) return new double[0];
			double root = -b / a;
			return new double[]{(root == -0.0) ? 0.0 : root};
		}

		if (degree == 2) {
			double a = getCoefficientByExponent(2);
			double b = getCoefficientByExponent(1);
			double c = getCoefficientByExponent(0);

			double discriminant = b * b - 4 * a * c;
			if (discriminant < 0) return new double[0];

			double sqrtDiscriminant = Math.sqrt(discriminant);
			double denominator = 2 * a;
			double root1 = (-b + sqrtDiscriminant) / denominator;
			double root2 = (-b - sqrtDiscriminant) / denominator;

			return new double[]{(root1 == -0.0) ? 0.0 : root1,(root2 == -0.0) ? 0.0 : root2};
		}

		double[] integerRoots = findIntegerRoots();
		if (integerRoots.length == degree) {
			for (int i = 0; i < integerRoots.length; i++) {
				if (integerRoots[i] == -0.0) {
					integerRoots[i] = 0.0;
				}
			}
			return integerRoots;
		}

		List<Double> roots = new ArrayList<Double>();
		double[] initialGuesses = {-2, 0, 1.5, 2.5, 4};

		int guessIndex = 0;
		while (guessIndex < initialGuesses.length) {
		    double guess = initialGuesses[guessIndex];
		    try {
		        double root = newtonRaphson(guess);
		        root = (root == -0.0) ? 0.0 : root;

		        boolean isUnique = true;
		        int checkIndex = 0;
		        while (checkIndex < roots.size()) {
		            double r = roots.get(checkIndex);
		            if (Math.abs(r - root) < 0.001) {
		                isUnique = false;
		                break;
		            }
		            checkIndex++;
		        }

		        if (isUnique) roots.add(root);
		    } catch (Exception e) {
		    }
		    guessIndex++;
		}

		double[] result = new double[roots.size()];
		int i = 0;
		while (i < roots.size()) {
		    double value = roots.get(i);
		    result[i] = (value == -0.0) ? 0.0 : value;
		    i++;
		}
		return result;
	}


	private double newtonRaphson(double initialGuess) {
	    final int maxIterations = 100;
	    final double tolerance = 0.000001;
	    double x = initialGuess;

	    for (int i = 0; i < maxIterations; i++) {
	        double fx = compensationX(x);
	        double fpx = derivative().compensationX(x);

	        if (Math.abs(fpx) < tolerance) {
	            break;
	        }
	        double xNew = x - fx / fpx;
	        
	        if (Math.abs(xNew - x) < tolerance) {
	            return (xNew == -0.0) ? 0.0 : xNew;}	        
	        x = xNew;
	    }
	    return (x == -0.0) ? 0.0 : x;
	}


	public PolynomialCalculator derivative() {
		PolynomialCalculator derivative = new PolynomialCalculator();
		Node temp = head;

		while (temp != null) {
			if (temp.getExponent() != 0) {
				derivative.addPolynomialTerm(temp.getCoefficient() * temp.getExponent(),temp.getExponent() - 1);
			}
			temp = temp.getNext();
		}
		return derivative;
	}

	private double[] findIntegerRoots() {
		List<Double> roots = new ArrayList<Double>();
		int constantTerm = (int) Math.round(getCoefficientByExponent(0));

		for (int i = -Math.abs(constantTerm); i <= Math.abs(constantTerm); i++) {
			if (i != 0 && constantTerm % i == 0) {
				if (Math.abs(compensationX(i)) < 0.0001) {
					roots.add((double) i);
				}
			}
		}

		double[] result = new double[roots.size()];
		for (int i = 0; i < roots.size(); i++) {
		    result[i] = roots.get(i); 
		}
		return result;
	}

	@Override
	public String toString() {
		if (head == null) return "0";

		StringBuilder ans = new StringBuilder();
		Node temp = head;
		boolean isFirstTerm = true;

		while (temp != null) {
			double coeff = temp.getCoefficient();
			int exp = temp.getExponent();

			if (coeff == 0.0) {
				temp = temp.getNext();
				continue;
			}

			if (!isFirstTerm) {
				ans.append(coeff > 0 ? "+" : "-");
			} else {
				if (coeff < 0) {
					ans.append("-");
				}
				isFirstTerm = false;
			}

			double absCoeff = Math.abs(coeff);
			String coeffStr = formatCoefficient(absCoeff);

			if (exp == 0) {
				ans.append(coeffStr);
			} else {
				if (absCoeff != 1.0) {
					ans.append(coeffStr);
				}
				ans.append("x");
				if (exp != 1) {
					ans.append("^").append(exp);
				}
			}
			temp = temp.getNext();
		}
		return ans.length() == 0 ? "0" : ans.toString();
	}

	private String formatCoefficient(double coeff) {
		String formatted = String.format(Locale.US, "%.4f", coeff);
		int dotIndex = formatted.indexOf('.');
		if (dotIndex >= 0) {
			int end = formatted.length() - 1;
			while (end > dotIndex && formatted.charAt(end) == '0') {
				end--;
			}
			if (formatted.charAt(end) == '.') {
				end--; 
			}
			formatted = formatted.substring(0, end + 1);
		}
		return formatted;
	}


}
