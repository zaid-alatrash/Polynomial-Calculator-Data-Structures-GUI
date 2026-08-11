
public class Node {

	private double coefficient;
	private int getExponent;
	private Node next;


	public Node(double coefficient, int exponent) {
		this.coefficient = coefficient;
		this.getExponent = exponent;
		this.next = null;
	}

	public Node(double coefficient,int exponent,Node next) {
		this.coefficient = coefficient;
		this.getExponent = exponent;
		this.next = next;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public int getExponent() {
		return getExponent;
	}

	public void setExponent(int exponent) {
		this.getExponent = exponent;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return coefficient + "x^" + getExponent;
	}

}




