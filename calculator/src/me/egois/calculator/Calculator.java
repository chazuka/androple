package me.egois.calculator;

import android.content.Context;
import android.widget.TextView;

/**
 * Math operation class
 * 
 * @author chz <chazzuka@gmail.com>
 */
public class Calculator {

	protected MathOperation mOperation;
	protected TextView mDisplay;
	protected Context mContext;

	/**
	 * Calculator class constructor
	 * 
	 * @param op instance of MathOperation
	 * @param view reference of TextView where the statement displayed
	 */
	public Calculator(MathOperation op, TextView view) {

		mOperation = op;
		mDisplay = view;
	}
	
	public void setContext(Context c) {
		mContext = c;
	}

	/**
	 * Reset operands and displays
	 */
	public void reset() {
		mOperation.reset();
		show();
	}

	/**
	 * Add number operand and update displays
	 * 
	 * @param op added operand to statement
	 */
	public void operand(String op) {
		mOperation.operand(op);
		show();
	}

	/**
	 * Add math symbol to statement and update displays
	 * 
	 * @param m math symbol added to statement
	 */
	public void math(String m) {
		mOperation.math(m);
		show();
	}

	/**
	 * Explicitly evaluate math statement and update displays
	 */
	public void evaluate() {
		try {
			mOperation.evaluate();
		} catch (Exception e) {
		}
		show();
	}
	
	/**
	 * Change polarization of number operand and update display
	 */
	public void polarize()
	{
		mOperation.polarize();
		show();
	}
	
	/**
	 * Check if current statement has left operand
	 * 
	 * @return boolean
	 */
	public boolean hasLeftOperand()
	{
		String s = mOperation.leftOperand();
		return (!s.isEmpty() && !s.equals("-"));
	}
	
	/**
	 * Check if current statement has right operand
	 * 
	 * @return boolean
	 */
	public boolean hasRightOperand()
	{
		String s = mOperation.rightOperand();
		return (!s.isEmpty() && !s.equals("-"));
	}
	
	/**
	 * Check if current statement has math operand
	 * 
	 * @return boolean
	 */
	public boolean hasMathOperand()
	{
		return !mOperation.mathOperand().isEmpty();
	}

	/**
	 * Update displays with current statement
	 */
	protected void show() {
		String d = (new StringBuilder())
				.append(mOperation.leftOperand())
				.append(mOperation.mathOperand())
				.append(mOperation.rightOperand()).toString();
		mDisplay.setText(d);
	}
	
	/**
	 * Evaluate percentage and update displays
	 */
	public void percent() {
		if (!hasLeftOperand()) return;

		math(MathOperation.DIVISION);
		operand("100");
		evaluate();
	}
	
	/**
	 * Evaluate square root and update displays
	 */
	public void sqrt() {
		if (!hasLeftOperand()) return;

		math(MathOperation.SQRT);
		evaluate();
	}
}
