package baza;

import java.sql.Time;

public class Izraz {
	double x; 
	double y;
	char operator;
	double resenje;
	
	public Izraz() {
	}

	public Izraz(double x, double y, char operator, double resenje) {
		this.x = x;
		this.y = y;
		this.resenje = resenje;
		this.operator = operator;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getResenje() {
		return resenje;
	}

	public void setResenje(double resenje) {
		this.resenje = resenje;
	}
	
	public char getOperator() {
		return operator;
	}
	
	public void setOperator(char operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return x + " " + operator + " " + y + " = " + resenje;
	}
}
