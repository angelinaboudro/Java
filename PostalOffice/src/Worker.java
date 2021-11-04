/*
 Angelina Boudro
 Project - Threads and Semaphores.
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable {

    public int Num;
    public static Queue<Integer> Queue = new LinkedList<>();
    private int next_cust_number;

    // worker number assigned

    Worker(int Num) {
        this.Num = Num;
    }

    @Override
    public void run() {
        work_created();
        do {
            wait(PostOffice.Ready);
            wait(PostOffice.mutex);
            dequeue();
            signal(PostOffice.mutex);
            signal(PostOffice.coord);
            wait(PostOffice.coord1);
            serve_cust();
            signal(PostOffice.finished[next_cust_number]);
            wait(PostOffice.leave);
            signal(PostOffice.Desk);
        } while (true);
    }

    void dequeue() {
        next_cust_number = PostOffice.queue.remove(); // this will dequeue the cust #
        System.out.println("Postal worker " + Num + " serving Customer " + next_cust_number);
        PostOffice.objCust[next_cust_number].worker = Num;
    }

    void work_created() {
        System.out.println("Postal Worker " + Num + " created");  // this will print worker created
    }

    void signal(Semaphore s) {  // signaling semaphore

        s.release();

    }

    void serve_cust() {
        int task;
        task = Queue.remove();
        if (task == 1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Postal worker " + Num + " completed serving customer " + next_cust_number);
        } else if (task == 2) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Postal worker " + Num + " completed serving customer " + next_cust_number);
        } else if (task == 3) {
            wait(PostOffice.scale);
            System.out.println("Scales are in use by postal worker " + Num);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Scales are released by postal worker " + Num);
            signal(PostOffice.scale);
            System.out.println("Postal worker " + Num + " completed serving customer " + next_cust_number);
        }
    }

    void wait(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {

        }
    }
}