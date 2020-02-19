import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	    System.out.println(decToBaseB(150, 20));
    }

    public static String decToBaseB(int num, int b){
        Stack<Integer> digits = new Stack<Integer>();

        while(num > 0){
            int digit = num % b;
            num /= b;
            digits.push(digit);
        }
        StringBuilder result = new StringBuilder();

        int n = digits.size();
        for(int i = 0; i < n; i++){
            if(digits.peek() >= 10){
                result.append((char)('0' + digits.pop() - 10));
            }else
                result.append(digits.pop());
        }
        return result.toString();
    }
}
