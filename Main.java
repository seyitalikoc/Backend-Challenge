//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i <= 5; i++) {
            int j=0;
            do{
                System.out.print("*");
                j++;
            }while (j < 2*i);
            System.out.println();
        }
    }
} /*if (i == 0) {
                System.out.println("*");
            }
            else{
                for (int j = 0; j < 2*i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }*/