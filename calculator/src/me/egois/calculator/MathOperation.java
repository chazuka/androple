package me.egois.calculator;

import java.text.DecimalFormat;

/**
 * Calculator operation class
 * 
 * @author chz <chazzuka@gmail.com>
 */
public class MathOperation {
	
	public static final String MULTIPLICATION = "*";
	public static final String DIVISION = "÷";
	public static final String ADDITION = "+";
	public static final String SUBSTRACTION = "-";
	public static final String SQRT = "√";
	public static final String SQUARED = "x²";
	
	public static final String[] SINGLE_OPERANDS = {SQRT,SQUARED};
	
    private String mLeftOperand = "";
    private String mRightOperand = "";
    private String mMathOperand = "";
    private boolean isAfterCalculation = false;
    
    /**
     * Add operand (left or right)
     * 
     * @param op the number operand
     */
    public void operand(String op)
    {
        if (isAfterCalculation) {
            mLeftOperand = "";
            isAfterCalculation = false;
        }
        
        if (mMathOperand.isEmpty()) {
            if (mLeftOperand.isEmpty() && op.equals(".")) {
                mLeftOperand = "0";
            }
            mLeftOperand = mLeftOperand.concat(op);
            return;
        }
        
        mRightOperand = mRightOperand.concat(op);
    }
    
	/**
	 * Change polarization of number operand
	 */
    public void polarize()
    {
		if (mMathOperand.isEmpty()) {
			if (!mLeftOperand.isEmpty() && mLeftOperand.startsWith("-")) {
				mLeftOperand = mLeftOperand.replace("-","");
			} else {
				mLeftOperand = "-".concat(mLeftOperand);
			}
		} else {
			if (!mRightOperand.isEmpty() && mRightOperand.startsWith("-")) {
				mRightOperand = mRightOperand.replace("-","");
			} else {
				mRightOperand = "-".concat(mRightOperand);
			}
		}
    }
    
    /**
     * Add math symbol
     * 
     * @param op the math operation symbol
     */
    public void math(String op)
    {
        if (mLeftOperand.isEmpty() || mLeftOperand.equals("-")) {
            return;
        }
        
        if (!mMathOperand.isEmpty()) {
            try {
                this.evaluate();
            } catch (Exception e) {}
        }
        
        mMathOperand = op;
        isAfterCalculation = false;
    }
    
    /**
     * Perform evaluation statement
     * 
     * @throws Exception
     */
    public void evaluate() throws Exception
    {
    	if (mMathOperand.isEmpty() || mLeftOperand.isEmpty() || mLeftOperand.equals("-")) {
    		return;
    	}
    	
    	double r, first, second;
    	first = Double.valueOf(mLeftOperand);
    	if (inArray(SINGLE_OPERANDS, mMathOperand)) {
    		second = 0; // silly!
    	} else {
            if(mRightOperand.isEmpty() || mRightOperand.equals("-")) {
                return;
            }
    		second = Double.valueOf(mRightOperand);
    	}

        if (mMathOperand.equals(ADDITION)) {
        	r = first + second;
        } else if (mMathOperand.equals(SUBSTRACTION)) {
        	r = first - second;
        } else if (mMathOperand.equals(MULTIPLICATION)) {
        	r = first * second;
        } else if (mMathOperand.equals(DIVISION)) {
        	r = first/second;
        } else if (mMathOperand.equals(SQRT)) {
        	r = Math.sqrt(first);
        } else if (mMathOperand.equals(SQUARED)) {
        	r = Math.pow(first, 2);
        } else {
        	throw new Exception("unsupported math operand");
        }
        
        this.reset();
        mLeftOperand = (new DecimalFormat("#.##")).format(r);
        isAfterCalculation = true;
    }
    
    /**
     * Reset all operands
     */
    public void reset()
    {
        mLeftOperand = "";
        mRightOperand = "";
        mMathOperand = "";
    }

    /**
     * Getter for left operand
     * 
     * @return string
     */
    public String leftOperand() {
        return mLeftOperand;
    }

    /**
     * Getter for right operand
     * 
     * @return string
     */
    public String rightOperand() {
        return mRightOperand;
    }

    /**
     * Getter for math operation symbol
     * 
     * @return string
     */
    public String mathOperand() {
        return mMathOperand;
    }
    
    /**
     * Check if a string is exists in array strings
     * 
     * @param haystack
     * @param needle
     * 
     * @return boolean
     */
    public boolean inArray(String[] haystack, String needle) {
    	for(String s: haystack){
    		if(s.equals(needle)) return true;
    	}
    	return false;
    }
	
}
