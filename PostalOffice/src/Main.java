import static java.lang.System.*;

/*
 Threads and Semaphores.
 */
public class Main{

    public static void main(String... args) {

        new PostOffice(); // creating post office

        {
            int i = 0;
            while (i < PostOffice.customerNumber) {
                PostOffice.objCust[i] = new Customer(i);
                PostOffice.t[i] = new Thread(PostOffice.objCust[i]);
                PostOffice.t[i].start();

                i++;
            }
        }


        {
            int i = 0;
            while (i < PostOffice.Num) {
                PostOffice.objWork[i] = new Worker(i);
                PostOffice.t2[i] = new Thread(PostOffice.objWork[i]);
                PostOffice.t2[i].start();
                i++;
            }
        }


        int i = 0;
        while (i < PostOffice.customerNumber) {
            try {
                PostOffice.t[i].join();
                out.println("Joined customer " + i);
            } catch (InterruptedException e) {

            }
            i++;
        }
        exit(0);
    }
}

