import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

class CompareCases {

    @Test

    void testCase1() { //test1

//array sorted in ascending order

        System.out.println("....................................");

        System.out.println("Test1");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int[] temp = new int[100000];

        for(int i=0;i<temp.length;i++)

            temp[i] = i;

//current time function

        LocalDateTime before = LocalDateTime.now();

        System.out.println("Started the Insertion Sort at: "+before.format(formatter));

        LocalDateTime after = LocalDateTime.now();

        System.out.println("Stopped the Insertion Sort at: "+after.format(formatter));

        long milliSeconds = ChronoUnit.MILLIS.between(before, after);

        System.out.println("The Insertion Sort took: "+milliSeconds+" milliseconds.");

        System.out.println("....................................");

//this is the merge sort algorithm

        temp = new int[100000];

        for(int i=0;i<temp.length;i++)

            temp[i] = i;

        before = LocalDateTime.now();

        System.out.println("Started the Merge Sort at: "+before.format(formatter));

        after = LocalDateTime.now();

        System.out.println("Stopped the Merge Sort at: "+after.format(formatter));

        milliSeconds = ChronoUnit.MILLIS.between(before, after);

        System.out.println("The Merge Sort took: "+milliSeconds+" milliseconds.");

        System.out.println("....................................");

        System.out.println();

    }

    @Test

    void testCase2() { //test2

//array sorted in ascending order

        System.out.println("....................................");

        System.out.println("Test2");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int[] temp = new int[100000];

        for(int i=0;i<temp.length;i++)

            temp[i] = temp.length - i;

//current time function

        LocalDateTime before = LocalDateTime.now();

        System.out.println("Started the Insertion Sort at: "+before.format(formatter));

        LocalDateTime after = LocalDateTime.now();

        System.out.println("Stopped the Insertion Sort at: "+after.format(formatter));

        long milliSeconds = ChronoUnit.MILLIS.between(before, after);

        System.out.println("The Insertion Sort took: "+milliSeconds+" milliseconds.");

        System.out.println("....................................");

//this is the merge sort algorithm

        temp = new int[100000];

        for(int i=0;i<temp.length;i++)

            temp[i] = temp.length - i;

        before = LocalDateTime.now();

        System.out.println("Started the Merge Sort at "+before.format(formatter));

        after = LocalDateTime.now();

        System.out.println("Stopped the Merge Sort at "+after.format(formatter));

        milliSeconds = ChronoUnit.MILLIS.between(before, after);

        System.out.println("The Merge Sort took: "+milliSeconds+" milliseconds.");

        System.out.println("....................................");

        System.out.println();

    }

    @Test

    void testCase3() { //test3

//array is sorted in ascending order

        System.out.println("....................................");

        System.out.println("Test3");

        Random ran = new Random();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int[] temp = new int[100000];

        for (int i = 0; i < temp.length; i++)

            temp[i] = ran.nextInt(1000000) + 1;

//current time function

        LocalDateTime before = LocalDateTime.now();

        System.out.println("Started the Insertion Sort at: " + before.format(formatter));

        LocalDateTime after = LocalDateTime.now();

        System.out.println("Stopped the Insertion Sort at: " + after.format(formatter));

        long milliSeconds = ChronoUnit.MILLIS.between(before, after);

        System.out.println("The Insertion Sort took: " + milliSeconds + " milliseconds.");

        System.out.println("....................................");

//this is the merge sort algorithm

        temp = new int[100000];

        for (int i = 0; i < temp.length; i++)

            temp[i] = ran.nextInt(1000000) + 1;

        before = LocalDateTime.now();

        System.out.println("Started the Merge Sort at: " + before.format(formatter));

        after = LocalDateTime.now();

        System.out.println("Stopped the Merge Sort at: " + after.format(formatter));

        milliSeconds = ChronoUnit.MILLIS.between(before, after);

        System.out.println("The Merge Sort took: " + milliSeconds + " milliseconds.");

        System.out.println("....................................");
    }


        @Test

        void testCase4() { //test4

//array is sorted in ascending order

            System.out.println("....................................");

            System.out.println("Test4");

            Random ran = new Random();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            int[] temp = new int[100000];

            for(int i=0;i<temp.length;i++)

                temp[i] = ran.nextInt(1000000) + 1;

//current time function

            LocalDateTime before = LocalDateTime.now();

            System.out.println("Started the Insertion Sort at: "+before.format(formatter));

            LocalDateTime after = LocalDateTime.now();

            System.out.println("Stopped the Insertion Sort at: "+after.format(formatter));

            long milliSeconds = ChronoUnit.MILLIS.between(before, after);

            System.out.println("The Insertion Sort took: "+milliSeconds+" milliseconds.");

            System.out.println("....................................");

//this is the merge sort algorithm

            temp = new int[100000];

            for(int i=0;i<temp.length;i++)

                temp[i] = ran.nextInt(1000000) + 1;

            before = LocalDateTime.now();

            System.out.println("Started the Merge Sort at: "+before.format(formatter));

            after = LocalDateTime.now();

            System.out.println("Stopped the Merge Sort at: "+after.format(formatter));

            milliSeconds = ChronoUnit.MILLIS.between(before, after);

            System.out.println("The Merge Sort took: "+milliSeconds+" milliseconds.");

            System.out.println("....................................");

    }

}

