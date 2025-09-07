public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World from Jenkins Pipeline!");
        System.out.println("This is a demonstration of Jenkins pipeline with Git and Ant configuration.");
        
        // Simple calculation to make the application more interesting
        int result = addNumbers(5, 10);
        System.out.println("5 + 10 = " + result);
    }
    
    public static int addNumbers(int a, int b) {
        return a + b;
    }
}
