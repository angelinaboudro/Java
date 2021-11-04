/*
 Angelina Boudro
 Project - Exploring Multiple Processes and IPC.
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;


public class Memory {

    static int[] memory; // declare int array for memory
    public static void main(String[] args) throws FileNotFoundException // this array will represent memory
    {
        // checking length of parameter
        if (args.length >= 1) // in case of no input prog found, the program will exit
        {
        } else {
            System.err.println("No input program found, please include an input program."); // error string for no input prog
            System.exit(1); //exit the system
        }
        String inputPath = args[0]; // java command line arguments for inputPath
        // memory initializes by reading a program file
        initializeMemory(inputPath);

        // parameters are ascii representations of integers
        // parent process for process input file
        Scanner input; //read each line input
        input = new Scanner(System.in);
        // loop
        while (input.hasNextLine()) {
            String line = input.nextLine();
            char command = line.charAt(0); // create command character
            int address; //declare address
            int data; //declare data
            if (command == 'r') {  // command declared for read
                address = Integer.parseInt(line.substring(1));
                System.out.println(read(address));
            } else if (command == 'w') {  // command declared for write
                String[] params = line.substring(1).split(",");
                address = Integer.parseInt(params[0]);
                data = Integer.parseInt(params[1]);
                write(address, data);
            } else if (command == 'e') {  // command 'e' for end process
                System.exit(0);
            }

        }
        input.close();
    }

    /**
     * For Memory allocation and file is read to load program.
     * @param inputFilePath path for the loading program
     * @throws FileNotFoundException
     */
    private static void initializeMemory(String inputFilePath) throws FileNotFoundException
    {
        memory = new int[2000]; // memory allocation
        Scanner scan = new Scanner(new File(inputFilePath));
        int memIndex = 0;
        while(scan.hasNextLine())
        {
            String line = scan.nextLine().trim();
            if(line.length() < 1) // skip empty line
                continue;
            // e.g. lines that are .1000, move to the designated position
            if(line.charAt(0) == '.')
            {
                memIndex = Integer.parseInt(line.substring(1).split("\\s+")[0]);
                continue;
            }
            // if beginning of line is not a number, skip
            if(line.charAt(0) < '0' || line.charAt(0) > '9')
                continue;
            // split to retrieve first number
            String[] split = line.split("\\s+");
            if(split.length < 1) // An empty line, skip it
                continue;
            else // read first int into memory
                memory[memIndex++] = Integer.parseInt(split[0]);
        }
        scan.close(); //close scanner
    }

    private static void log(String str)  //generate log method for computer.java
    {

    }

    /**
     * @param address
     * @return data that is contained in add
     */
    // generate address to read from memory
    private static int read(int address)
    {
        log("Reading "+address+" = "+memory[address]);
        return memory[address];
    }
    // add returned

    /**
     * @param address what address destination to write
     * @param data data that needs to be written
     */
    private static void write(int address, int data)
    {
        // to write the data into the required address
        log("Writing "+data+" to "+address);
        memory[address] = data;
    }

}
