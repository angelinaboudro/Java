import java.util.Random;

public class InsertionMerge {

    //Insertion Sort Method
    void insertSort(int arr[])
    {
        int vn;
        vn = arr.length;
        int vi=1;
        while (vi<vn) {
            int pivot = arr[vi];
            int vj = vi-1;

            while (vj>=0 && arr[vj] > pivot)
            {
                arr[vj+1] = arr[vj];
                vj = vj-1;
            }
            arr[vj+1] = pivot;
            ++vi;
        }
    }


    //the merge function
    void merge(int arr[], int len, int mer, int rand)
    {

        int n1;
        n1 = mer - len + 1;
        int n2 = rand - mer;

        int[] Left;
        Left = new int [n1];
        int[] Right;
        Right = new int [n2];

        {
            int vi=0;
            while (vi<n1) {
                Left[vi] = arr[len + vi];
                ++vi;
            }
        }
        {
            int vj=0;
            while (vj<n2) {
                Right[vj] = arr[mer + 1+ vj];
                ++vj;
            }
        }

        int vi = 0, vj = 0;

        int vk = len;
        while (vi < n1 && vj < n2)
        {
            if (Left[vi] > Right[vj]) {
                arr[vk] = Right[vj];
                vj++;
            } else {
                arr[vk] = Left[vi];
                vi++;
            }
            vk++;
        }


        while (vi < n1)
        {
            arr[vk] = Left[vi];
            vi++;
            vk++;
        }

        while (vj < n2)
        {
            arr[vk] = Right[vj];
            vj++;
            vk++;
        }
    }

    //merge sort function
    void mergeSort(int arr[], int len, int rand)
    {
        if (len >= rand) {
            return;
        }

        int mer = (len+rand)/2;


        mergeSort(arr, len, mer);
        mergeSort(arr , mer+1, rand);

        //halvles merge sort
        merge(arr, len, mer, rand);
    }

    // driver method
    public static void main(String args[])
    {
        InsertionMerge obj = new InsertionMerge ();
//100
        System.out.println("For 100 number array the timings are listed bellow.");
        System.out.println();
        int[] arr0;
        arr0 = new int[100];
        int[] iarr0;
        iarr0 = new int[100];

        Random rand = new Random();
        for (int vi = 0; vi < arr0.length; vi++)
        {
            arr0[vi] = rand.nextInt();
            iarr0[vi]=arr0[vi];
        }
        long startTime = System.nanoTime();
        obj.insertSort(arr0);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("This Insertion Sort took: "+totalTime+" nano seconds.");

        startTime = System.nanoTime();
        obj.mergeSort(iarr0, 0, iarr0.length-1);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("This Merge Sort took: "+totalTime+" nano seconds.");
        System.out.println();
//1000
        System.out.println("For 1000 number array the timings are listed bellow.");
        System.out.println();
        int[] arr1;
        arr1 = new int[1000];
        int[] iarr1;
        iarr1 = new int[1000];

        {
            int vi = 0;
            while (vi < arr1.length) {
                arr1[vi] = rand.nextInt();
                iarr1[vi]=arr1[vi];
                vi++;
            }
        }
        startTime = System.nanoTime();
        obj.insertSort(arr1);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("This Insertion Sort took: "+totalTime+" nano seconds.");

        startTime = System.nanoTime();
        obj.mergeSort(iarr1, 0, iarr1.length-1);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("This Merge Sort took: "+totalTime+" nano seconds.");
        System.out.println();
//10000
        System.out.println("For 10000 number array the timings are listed bellow.");
        System.out.println();
        int[] arr2;
        arr2 = new int[10000];
        int[] iarr2;
        iarr2 = new int[10000];

        {
            int vi = 0;
            while (vi < arr2.length) {
                arr2[vi] = rand.nextInt();
                iarr2[vi]=arr2[vi];
                vi++;
            }
        }
        startTime = System.nanoTime();
        obj.insertSort(arr2);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("This Insertion Sort took: "+totalTime+" nano seconds.");

        startTime = System.nanoTime();
        obj.mergeSort(iarr2, 0, iarr2.length-1);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("This Merge Sort took: "+totalTime+" nano seconds.");
        System.out.println();
//100000
        System.out.println("For 100000 number array the timings are listed bellow.");
        System.out.println();
        int[] arr3;
        arr3 = new int[100000];
        int[] iarr3;
        iarr3 = new int[100000];

        int vi = 0;
        while (vi < arr3.length) {
            arr3[vi] = rand.nextInt();
            iarr3[vi]=arr3[vi];
            vi++;
        }
        startTime = System.nanoTime();
        obj.insertSort(arr3);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("This Insertion Sort took: "+totalTime+" nano seconds.");

        startTime = System.nanoTime();
        obj.mergeSort(iarr3, 0, iarr3.length-1);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("This Merge Sort took: "+totalTime+" nano seconds.");
        System.out.println();
    }
}