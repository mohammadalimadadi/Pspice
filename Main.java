import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        String directory;
        Scanner input = new Scanner(System.in);
        directory=input.nextLine();
        File file = new File(directory);
        Circuit.readingFile(file);
    }//end of main
}//end of Main
