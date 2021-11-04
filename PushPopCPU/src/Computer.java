/*
 Angelina Boudro
 Project - Exploring Multiple Processes and IPC.
 */

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

public class Computer {

    public static void main(String[] args)
    {
        if (args.length >= 2) {
        } else {
            System.err.println("Arguments missing, needs timer length and input program.");
            System.exit(1); // exiting system
        }
        String inputProgram = args[0]; // retrieve program arguments to proceed
        // timeout statement
        int timeout = Integer.parseInt(args[1]);
        // retrieve runtime
        Runtime runtime = Runtime.getRuntime();
        // memory process called with input program argument
        try {
            Process memory = runtime.exec("java Memory "+inputProgram);
            final InputStream error;
            error = memory.getErrorStream(); // error to retrieve memory
            new Thread(new Runnable(){
                //method for run
                public void run(){
                    byte[] buffer = new byte[8192];
                    // declare length
                    int len = -1;
                    // loop statement for error proceeds to catch exception
                    try {
                        while(true){
                            if ((len = error.read(buffer)) <= 0) break;
                            System.err.write(buffer, 0, len);
                        }
                    } catch (IOException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
            // scanning memory
            // create new CPU
            Scanner memoryIn;
            memoryIn = new Scanner(memory.getInputStream());
            PrintWriter memoryOut;
            memoryOut = new PrintWriter(memory.getOutputStream());
            //CPU method
            CPU cpu;
            cpu = new CPU(memoryIn, memoryOut, timeout);
            cpu.run(); // run new CPU
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR: Can't create new process."); // error string
            System.exit(1); // exit system
        }

    }
    private static void log(Object... str)
    {

    }

    /**
     * Simple CPU class
     * with registers & timer: PC, SP, IR, AC, X, Y, timer, timeout as per requirement
     * with kernel mode = false
     * runs user program at address 0
     */
    private static class CPU
    {
        // Registers + timer + kernel mode
        private int PC, SP, IR, AC, X, Y, timer, timeout; // declared registers and timer
        private boolean kernelMode;
        private Scanner memoryIn;
        private PrintWriter memoryOut;

        public CPU(Scanner memoryIn, PrintWriter memoryOut, int timeout) {
            this.memoryIn = memoryIn;
            this.memoryOut = memoryOut;
            this.timeout = timeout;
            kernelMode = false;
            PC = IR = AC = X = Y = timer = 0;
            SP = 1000;
        }
        // read memory at address, create bounds for address to decide what mode is accessed in
        private int readMemory(int address)
        {
            if (address < 1000 || kernelMode) {
            } else {
                //User program cannot access system memory, exit w error msg
                System.err.println("ERROR: The system memory accessed in user mode.");
                System.exit(-1);
            }
            memoryOut.println("r"+address);
            memoryOut.flush();
            return Integer.parseInt(memoryIn.nextLine());
        }

        // write data into address

        private void writeMemory(int address, int data)
        {
            memoryOut.printf("w%d,%d\n", address, data);
            memoryOut.flush();
        }
        //program ends with end instruction is executed
        private void endMemoryProcess()
        {
            memoryOut.println("e");
            memoryOut.flush();
        }

        public void run()
        {
            boolean running = true;
            while(true)
            {
                if (!running) break;
                fetch();
                running = execute();
                timer++;
        // timer interrupt causing execution at address 1000
                if (timer < timeout) {
                    continue;
                }
                // no nested interrupts
                if (kernelMode)
                {
                    continue;
                }
                timer = 0;
                kernelMode();
                PC = 1000; // program counter
            }
        }

        private void fetch()
        {
            // instruction register read instruction, program counter incremented
            IR = readMemory(PC++);
        }
        // push
        private void push(int data)
        {
            writeMemory(--SP, data);
        }
        //pop
        private int pop()
        {
            return readMemory(SP++);
        }
        // to enter kernel mode
        private void kernelMode()
        {
            log("Initializing Kernel mode."); // to enter kernel mode
            kernelMode = true;
            int tempSP = SP;
            SP = 2000;
            push(tempSP);
            push(PC);
            push(IR);
            push(AC);
            push(X);
            push(Y);
        }

        // loading execute instructions
        private boolean execute()
        {
            //  instruction invalid
            if (IR == 1) { // value loaded into the AC
                fetch();
                log("Load the value " + IR + " into the AC");
                AC = IR;
            } else if (IR == 2) { //  value loaded at address into the AC
                fetch();
                AC = readMemory(IR);
                log("Load the value at address " + IR + " into the AC: " + AC);
            } else if (IR == 3) { // LoadInd addr: Load value from address found in given address into the AC
                fetch();
                AC = readMemory(readMemory(IR));
                log("Load the value from the address found in given address" + IR + " into the AC: " + AC);
            } else if (IR == 4) { // LoadIdxX addr: Load the value at (address + X) into the AC
                fetch();
                AC = readMemory(IR + X);
                log("LoadIdxX", IR, X, "->", AC);
            } else if (IR == 5) { // LoadIdxY addr: Load the value at (address + Y) into the AC
                fetch();
                AC = readMemory(IR + Y);
                log("LoadIdxY", IR, Y, "->", AC);
            } else if (IR == 6) { // LoadSpX: Load from (SP+X) into the AC
                AC = readMemory(SP + X);
                log("LoadSpX", SP, X, "->", AC);
            } else if (IR == 7) { // Store addr: Store the value in the AC into the address
                fetch();
                writeMemory(IR, AC);
                log("Store the value in the AC into the address", IR, AC);
            } else if (IR == 8) { // Get: Get random int 1-100 into the AC
                AC = (int) (Math.random() * 100 + 1);
                log("Get random int from 1 to 100 into the AC", AC);
            } else if (IR == 9) { // Put port: If port=1, write AC as int to screen, if port=2, write AC as char to screen
                fetch();
                if (IR == 1) {
                    System.out.print(AC);
                    log("Put", "int", AC);
                } else if (IR == 2) {
                    System.out.print((char) AC);
                    log("Put", "char", (char) AC);
                }
            } else if (IR == 10) { // AddX: Add the value in X to the AC
                AC += X;
            } else if (IR == 11) { // AddY: Add the value in Y to the AC
                AC += Y;
            } else if (IR == 12) { // SubX: Subtract the value in X to the AC
                AC -= X;
            } else if (IR == 13) { // SubY: Subtract the value in Y to the AC
                AC -= Y;
            } else if (IR == 14) { // CopyToX: Copy value in the AC to X
                X = AC;
            } else if (IR == 15) { // CopyFromX: Copy value in X to the AC
                AC = X;
            } else if (IR == 16) { // CopyToY: Copy the value in the AC to Y
                Y = AC;
            } else if (IR == 17) { // CopyFromY: Copy the value in Y to the AC
                AC = Y;
            } else if (IR == 18) { // CopyToSp: Copy the value in the AC to SP
                SP = AC;
            } else if (IR == 19) { // CopyFromSp: Copy the value in SP to the AC
                AC = SP;
            } else if (IR == 20) { // Jump addr: Jump to address
                fetch();
                PC = IR;
            } else if (IR == 21) { // JumpIfEqual addr: Jump to the addr only if the value in the AC is zero
                fetch();
                if (AC == 0)
                    PC = IR;
            } else if (IR == 22) { // JumpIfNotEqual addr: Jump to the addr only if the value in AC is not zero
                fetch();
                if (AC != 0)
                    PC = IR;
            } else if (IR == 23) { // Call addr: Push return addr onto stack, jump to addr
                fetch();
                push(PC);
                PC = IR;
            } else if (IR == 24) { // Ret: Pop return addr from stack, jump back to the addr
                PC = pop();
            } else if (IR == 25) { // IncX: Increment the value in X
                X++;
            } else if (IR == 26) { // DecX: Decrement the value in X
                X--;
            } else if (IR == 27) { // Push: Push the AC onto stack
                push(AC);
                log("Push the AC onto stack ", AC);
            } else if (IR == 28) { // Pop: Pop from stack into the AC
                AC = pop();
                log("Pop AC from stack into the AC", AC);
            } else if (IR == 29) { // Int: Perform System call
                if (!kernelMode) {
                    kernelMode();
                    PC = 1500;
                }
            } else if (IR == 30) { // IRet: Return from system call (set user mode, restore registers)
                log("Quitting kernel mode.");
                Y = pop();
                X = pop();
                AC = pop();
                IR = pop();
                PC = pop();
                SP = pop();

                kernelMode = false;

            } else if (IR == 50) { // end execution
                endMemoryProcess();

                return false;

            } else {
                System.err.println("ERROR: Given instruction is invalid.");
                endMemoryProcess();

                return false;` `
            }
            return true;
        }
    }
}

//end of program

