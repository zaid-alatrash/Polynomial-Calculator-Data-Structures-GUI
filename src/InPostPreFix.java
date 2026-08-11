public class InPostPreFix {
     
    public String preprocessExpression(String exp) {
        String ans = "";
        int i = 0;
        int length = exp.length();

        while(i < length) {
            char currentTerm = exp.charAt(i);
            ans += currentTerm;

            if(i + 1 < length) {
                char next = exp.charAt(i + 1);
                boolean needsMultiply = 
                    (Character.isDigit(currentTerm) && Character.isLetter(next)) 
                  ||(Character.isLetter(currentTerm) && Character.isDigit(next)) 
                  ||(Character.isLetter(currentTerm) && Character.isLetter(next));
                if(needsMultiply) {
                    ans += '*';}                
            }
            i++;}             
        return ans;
    }

    public String convertToPostfix(String infix) {
        Stack s = new Stack();
        String ans = "";
        int len = infix.length();
        int i = 0;

        while(i < len) {
            char c = infix.charAt(i);

            if(Character.isLetterOrDigit(c)) {
                ans += c;
            } else if(isOperator(c)) {
                while(!s.isEmpty() &&  getPrecedence(c) <= getPrecedence((Character)s.peek()) &&  c != '^') {                     
                    ans += s.pop();
                }
                s.push(c);
            }
            i++; }        

        while(!s.isEmpty()) {
            ans += s.pop();
        }
        return ans;
    }

    public String reverse(String original) {
        StringBuilder sb = new StringBuilder(original);
        return sb.reverse().toString();
    }
    

    public String convertToPrefix(String infix) {
        Stack s = new Stack();
        String rev = reverse(infix);
        String ans = "";
        int i = 0;
        int length = rev.length();

        while(i < length) {
            char c = rev.charAt(i);

            if(Character.isLetterOrDigit(c)) {
                ans += c;
            } else if(isOperator(c)) {
                while(!s.isEmpty() && getPrecedence(c) < getPrecedence((Character)s.peek())) {                      
                    ans += s.pop();
                }
                s.push(c); }            
            i++;
        }
        while(!s.isEmpty()) {
            ans += s.pop();
        }

        return reverse(ans);
    }
    /*
       -_- كنت اريد استخدام هذه الطريقة لكن هناك مشكلة لم اعف لها يخرج الناتج خاطئ بشكل بسيط ولكن خاطئ
      
    public String convertToPrefix(String infix) {
        String rev = reverse(infix);
        String post = convertToPostfix(rev);      
        return reverse(post);
    }
*/
    public int getPrecedence(char c) {
    	switch (c) {
        case '+':
        case '-':
            return 5;
        case '*':
        case '/':
            return 6;
        case '^':
            return 7;
        default:
            return 0;
    }
    }

    public boolean isOperator(char c) {
        switch(c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
            default:
                return false;
        }}
    }    
