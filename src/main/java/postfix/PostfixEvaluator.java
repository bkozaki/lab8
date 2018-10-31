package postfix;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {
	
	private String arithmeticExpr;
	
	/**
	 * This is the only constructor for this class.
	 * It takes a string that represents an arithmetic expression
	 * as input argument.
	 * 
	 * @param expr is a string that represents an arithmetic expression 
	 * <strong>in Postfix notation</strong>.
	 */
	public PostfixEvaluator( String expr ) {
		arithmeticExpr = expr;
	}
	
	/**
	 * This method evaluates the arithmetic expression that 
	 * was passed as a string to the constructor for this class.
	 * 
	 * @return the value of the arithmetic expression
	 * @throws MalformedExpressionException if the provided expression is not
	 * 	a valid expression in Postfix notation
	 */
	double eval( ) throws MalformedExpressionException {
	    Stack<Double> operands = new Stack<>();
	    Double operand1;
	    Double operand2;
	    Double answer;
		Scanner scanner = new Scanner(arithmeticExpr);

		while (!scanner.isEmpty()) {
            Token currToken = scanner.getToken();
            scanner.eatToken();
            if (currToken.isDouble()) {
                operands.push(currToken.getValue());
            } else {
                try {
                    operand1 = operands.pop();
                    operand2 = operands.pop();
                } catch(EmptyStackException e) {
                    throw new MalformedExpressionException();
                }
                if (currToken.getName().equals("+")) {
                    operands.push(operand1 + operand2);
                } else if (currToken.getName().equals("-")) {
                    operands.push(operand2 - operand1);
                } else if (currToken.getName().equals("*")) {
                    operands.push(operand1 * operand2);
                } else {
                    operands.push(operand2 / operand1);
                }
            }
        }
        answer = operands.pop();

		if (!operands.empty()) {
		    throw new MalformedExpressionException();
        }
		
		return answer;
	}
	
}