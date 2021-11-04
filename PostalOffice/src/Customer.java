/*
 Angelina Boudro
 Project - Threads and Semaphores.
 */
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Customer implements Runnable {


    public int customerNumber;
    public int task;
    public int worker;


    Customer(int customerNumber) {
        this.customerNumber = customerNumber;
    }


    @Override
    public void run() {
        cust_created();
        wait(PostOffice.maximumCapacity);
        cust_enterShop();
        wait(PostOffice.Desk);
        wait(PostOffice.mutex);
        enqueue(customerNumber);
        signal(PostOffice.Ready);
        signal(PostOffice.mutex);
        wait(PostOffice.coord);
        cust_request();
        signal(PostOffice.coord1);
        wait(PostOffice.finished[customerNumber]);
        finish_work();
        leave();
        signal(PostOffice.leave);
        signal(PostOffice.maximumCapacity);
        PostOffice.count++;
    }
      // this will print customer leaving the postal office

    void leave() {
        System.out.println("Customer " + customerNumber + " exists Post Office");
    }
     // this will print customer finished task

    void finish_work() {
        System.out.println("Customer " + customerNumber + " finished " + task2(task));
    }
    // this will print a task giving 3 cases

    private String task2(int task) {
        String message = new String();
        if (task == 1) {
            message = "purchase stamps";
        } else if (task == 2) {
            message = "mailing a letter";
        } else if (task == 3) {
            message = "mailing a package";
        }
        return message;
    }
    // this will print customer requesting worker to complete a some work, this will add to the workers queue

    void cust_request() {
        System.out.println("Customer " + customerNumber + " requests postal worker " + worker + " to " + task1(task));
        PostOffice.objWork[worker].Queue.add(task);
    }


    private String task1(int task) {
        String message = new String();
        if (task == 1) {
            message = "purchase stamps";
        } else if (task == 2) {
            message = "mail a letter";
        } else if (task == 3) {
            message = "mail a package";
        }
        return message;
    }
    // adding the customer ID to the queue

    void enqueue(int custId) {
        PostOffice.queue.add(custId);
    }
    // this will print "customer created" and will give a random task

    void cust_created() {
        System.out.println("Customer " + customerNumber + " created");
        Random r;
        r = new Random();
        int low;
        low = 1;
        int high;
        high = 4;
        this.task = low + r.nextInt(high - low);
    }
    // wait semaphore

    void wait(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {

        }
    }


    void cust_enterShop() {
        System.out.println("Customer " + customerNumber + " enters Post Office");
    }


    void signal(Semaphore s) {
        s.release();
    }
}