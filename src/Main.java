import java.awt.EventQueue;

public class Main {
    	public static void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					LoginEmail frame = new LoginEmail();
    					
    					frame.setVisible(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}
    
    
}