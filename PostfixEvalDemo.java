import java.util.*;

public class PostfixEvalDemo
{
	public static void main(String[] args) {
		String exp = "643-5*+";
		System.out.println(eval(exp));
	}
	
	public static int eval(String exp){
	    Stack<Integer> nums = new Stack<>();
	    for(int i = 0; i < exp.length(); i++){
	        char curr = exp.charAt(i);
	        if(Character.isDigit(curr)){
	            nums.push(Integer.parseInt(curr+""));
	        }
	        else{
	            int rightOp = nums.pop();
	            int leftOp = nums.pop();
	            switch(curr){
	                case '+':
	                   nums.push(leftOp + rightOp);
	                   break;
	                case '-':
	                    nums.push(leftOp - rightOp);
	                   break;
	                case '*':
	                    nums.push(leftOp*rightOp);
	                   break;
	                case '/':
	                    nums.push(leftOp/rightOp);
	                   break;
	            }
	        }
	    }
	    return nums.pop();
	}
}
