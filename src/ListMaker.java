import java.util.Scanner;
import java.util.ArrayList;
public class ListMaker {
    //Declaring Class Variables
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {

        //Declaring Variables
        final String menu = "A - Add   |   D - Delete   |   P - Print   |   Q - Quit";
        boolean done = false;
        int delItem = 0;
        Scanner console = new Scanner(System.in);
        String cmd = "";
        String item = "";

        do {
            //Prompt User & Get Choice
            displayList();
            cmd = SafeInput.getRegExString(console, menu, "[AaDdPpQq]");
            cmd = cmd.toUpperCase();
            //Executing Choice
            switch(cmd)
            {
                //For Add
                case "A":
                    item = SafeInput.getNonZeroLenString(console, "What item would you like to add?");
                    list.add(item);
                    break;
                //For Delete
                case "D":
                    delItem = SafeInput.getRangedInt(console, "What number item would you like to delete?", 1, list.size());
                    delItem = delItem -1;
                    list.remove(delItem);
                    break;
                //For Print
                case "P":
                    break;
                //For Quit
                case "Q":
                    boolean yN = SafeInput.getYNConfirm(console, "Are you sure you want to quit? Please enter Y for yes and N for no.");
                            if (!yN)
                            {
                                System.exit(0);
                            }
                    break;
            }
        }
        while (!done);
    }

    //Helper Methods
    private static void displayList() {
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 0; i < list.size(); i++)
            if (list.size() !=0) {
                {
                    System.out.printf("%3d%35s", i + 1, list.get(i));
                    System.out.println("");
                }
            }
            else
            {
                System.out.println("There are no items.");
            }
        System.out.println("----------------------------------------------------------------------------");
    }
}

