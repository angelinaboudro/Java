/*
 Angelina Boudro
 Project - Threads and Semaphores.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class PostOffice {


    // creating semaphores

    public static Semaphore maximumCapacity;
    public static Semaphore Ready;
    public static Semaphore Desk;
    public static Semaphore mutex;
    public static Semaphore coord;
    public static Semaphore leave;
    public static Semaphore coord1;
    public static Semaphore scale;
    public static int count;
    public static Queue<Integer> queue;
    public static final int customerNumber = 50;
    public static final int Num;
    public static Semaphore[] finished;

    static {
        finished = new Semaphore[customerNumber];
        Num = 3;
        queue = new LinkedList<>();
        scale = new Semaphore(1, true);
        coord1 = new Semaphore(0, true);
        leave = new Semaphore(0, true);
        coord = new Semaphore(0, true);
        mutex = new Semaphore(1, true);
        Desk = new Semaphore(3, true);
        Ready = new Semaphore(0, true);
        maximumCapacity = new Semaphore(10, true);
        int i = 0;
        if (i < customerNumber) {
            do {
                finished[i] = new Semaphore(0, true);
                i++;
            } while (i < customerNumber);
        }
    }
    public static Customer[] objCust = new Customer[customerNumber];
    public static Worker[] objWork = new Worker[Num];
    public static Thread[] t = new Thread[customerNumber];
    public static Thread[] t2 = new Thread[Num];
}