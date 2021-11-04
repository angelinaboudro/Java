import java.util.*;

public class Main
{

    //function to check if all numbers are equal

    public static Boolean allEqual(int[] numbers, int n)

    {
        int i;
        Boolean equal = false;



        for(i=0;i<n-1;i++)

        {

            if(numbers[i] == numbers[i+1])

            {

                //if true, continue and check for every element

                equal = true;

            }

            else

            {

                //even if one is false, break out of loop

                equal = false;

                break;

            }

        }



        return equal;

    }



    //function to count moves

    //LOGIC IS SIMPLE..

    //Take the largest element for each iteration

    //increment every other element by 1 except largest

    //count moves by incrementing moves

    //repeat until all elements are equal

    public static int countMoves(int[] numbers, int n)

    {
        int moves = 1;

        int i,largest,index;

        //start and loop until all elements are not equal

        while(!allEqual(numbers,n))

        {
            //find the largest element in array and its index

            largest = numbers[0];

            index = 0;

            for(i=0;i<n;i++)

            {
                if(numbers[i]>largest) {

                    largest = numbers[i];

                    index = i;

                }
            }



            System.out.print("\n\nIteration - " + moves +"\nSelected index: " + index);
            //increment every values of array except largest (element at position index), so, we increment n-1 elements
            for(i=0;i<n;i++)

            {
                if(i != index)
                {

                    numbers[i] = numbers[i] + 1;

                }
            }

            //Printing array elements after each iteration

            System.out.print("\nArray elements after this iteration: ");

            for(i=0;i<n;i++)

            {
                System.out.print(numbers[i] + " ");
            }

            //increment moves only if all elements are not equal
            if(!allEqual(numbers,n))

                moves++;
            //Loop goes on until allEqual() returns true
        }
        //when loop breaks,return moves
        return moves;
    }

    public static void main(String[] args)

    {

        int n, i, moves;



        Scanner in = new Scanner(System.in);



        System.out.print("Enter the number of elements: ");

        n = in.nextInt();



        //Declare an array for n integers

        int numbers[] = new int[n];



        //Now read the elements for array

        System.out.print("\nEnter " + n + " elements: ");

        for(i=0;i<n;i++)

        {

            numbers[i] = in.nextInt();

        }



        System.out.print("\n\nGiven Array elements. Iteration - 0: ");

        for(i=0;i<n;i++)

        {

            System.out.print(numbers[i] + " ");

        }



        moves = countMoves(numbers,n);



        System.out.print("\n\nIt took " + moves + " moves to make all elements of the array equal");

        System.out.print("\nFinal Array elements: ");

        for(i=0;i<n;i++)

        {

            System.out.print(numbers[i] + " ");

        }

    }

}
