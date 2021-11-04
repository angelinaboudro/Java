public class InsertionSort {

    public static void insertSort(int arr[])

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

    public static void main(String[] args) {

        int[] list;
        list = new int[]{ 6, 4, 1, 2, 3, 8, 11};

        insertSort(list);

        int i = 0;
        while (i < list.length) {
            System.out.print(list[i] + " ");
            i++;
        }

    }

}