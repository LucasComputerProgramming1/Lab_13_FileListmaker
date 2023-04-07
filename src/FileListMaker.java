import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileListMaker {
    //Declaring Class Variables
    static ArrayList<String> list = new ArrayList<>();
    static boolean empty=true;

    //Main Class
    public static void main(String[] args) throws IOException {
        //Declaring Variables
        String fileName = "data";
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath()+"data.txt");
        final String menu = "O - Open | S - Save | C - Clear | A - Add   |   D - Delete   |   V - View   |   Q - Quit";
        boolean done = false;
        int delItem = 0;
        Scanner console = new Scanner(System.in);
        String cmd = "";
        String item = "";
        boolean needsToBeSaved = false;
        boolean canEdit = true;
        boolean ifOpen = false;

        do {
            //Prompt User & Get Choice
            displayList();
            cmd = SafeInput.getRegExString(console, menu, "[OoSsCcAaDdVvQq]");
            cmd = cmd.toUpperCase();

            //Executing Choice
            switch(cmd)
            {
                case "O": //OPEN
                    ifOpen = true;
                    empty=false;
                    //For No Save Needed
                    if (!needsToBeSaved) {
                        try {
                            chooser.setCurrentDirectory(workingDirectory);
                            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                                selectedFile = chooser.getSelectedFile();
                                file = selectedFile.toPath();
                                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                list.removeAll(list);
                                while (reader.ready()) {
                                    String rec = reader.readLine();
                                    list.add(rec);
                                }
                                reader.close();
                                System.out.println("\n\nData file read!");
                                System.out.println();
                            }
                            canEdit = true;
                        } finally {
                            break;
                        }
                    }

                    //For Save Needed
                    else
                    {
                    boolean yN = SafeInput.getYNConfirm(console, "WARNING: YOU HAVE UNSAVED CHANGES! Are you sure you want to open a new file? Please enter Y for yes and N for no");
                    if (!yN) {
                        try {
                            chooser.setCurrentDirectory(workingDirectory);
                            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                                selectedFile = chooser.getSelectedFile();
                                file = selectedFile.toPath();
                                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                list.removeAll(list);
                                while (reader.ready()) {
                                    String rec = reader.readLine();
                                    list.add(rec);
                                }
                                reader.close();
                                System.out.println("\n\nData file read!");
                                System.out.println();
                            }
                            canEdit = true;
                        } finally {
                            break;
                        }
                    }
                    break;
                }

                case "S": //SAVE
                    //For New List
                    if (!ifOpen) {
                        fileName = SafeInput.getNonZeroLenString(console, "Please enter your file name");
                        file = Paths.get(workingDirectory.getPath()+"\\"+fileName+".txt");
                    }
                    OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                    //For Opened List
                    for (String rec : list) {
                        writer.write(rec, 0, rec.length());
                        writer.newLine();
                    }
                    writer.close();
                    System.out.println();
                    System.out.println("Data file written!");
                    System.out.println();
                    needsToBeSaved = false;
                    break;

                case "C": //CLEAR
                    empty=true;
                    list.removeAll(list);
                    needsToBeSaved = true;
                    break;

                case "A": //ADD
                    empty=false;
                    if (canEdit==true) {
                        item = SafeInput.getNonZeroLenString(console, "What item would you like to add?");
                        list.add(item);
                        needsToBeSaved = true;
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("You are viewing this file. Please open to make changes.");
                        System.out.println();
                    }
                    break;

                case "D": //DELETE
                    empty=false;
                    if (canEdit==true) {
                        delItem = SafeInput.getRangedInt(console, "What number item would you like to delete?", 1, list.size());
                        delItem = delItem - 1;
                        list.remove(delItem);
                        needsToBeSaved = true;
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("You are viewing this file. Please open to make changes.");
                        System.out.println();
                    }
                    break;

                case "V": //VIEW
                    chooser.setCurrentDirectory(workingDirectory);
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    {
                        selectedFile = chooser.getSelectedFile();
                        file = selectedFile.toPath();
                        InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    }
                    canEdit = false;
                    break;

                case "Q": //QUIT
                    //For No Save Needed
                    if (!needsToBeSaved) {
                        boolean yN = SafeInput.getYNConfirm(console, "Are you sure you want to quit? Please enter Y for yes and N for no");
                        if (!yN) {
                            System.exit(0);
                        }
                        break;
                    }

                    //For Save Needed
                    else
                    {
                        boolean yN = SafeInput.getYNConfirm(console, "WARNING: YOU HAVE UNSAVED CHANGES! Are you sure you want to quit? Please enter Y for yes and N for no");
                        if (!yN) {
                            System.exit(0);
                        }
                        break;
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + cmd);
            }
        }
        while (!done);
    }

    //Helper Methods
    private static void displayList() {
        System.out.println("----------------------------------------------------------------------------");

        //For Empty List
        if (empty)
        {
            System.out.println("[ Your List Will Go Here ]");
        }

        //Display List
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

