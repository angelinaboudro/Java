/*
 Data Structures
 Angelina Boudro, July 17, 2019
 Project, Linear Probing Hash Table
 Work with hash tables by creating a hash table using linear probing.
 */


public class LinearProbingHashTable<K,V> {

    private static final int DEFAULT_SIZE = 4;
    int size;           //Table size
    int N;               //Amount of elements in table
    Entry<K,V> table[];   //Entries sorted by array

    LinearProbingHashTable(){
        size = DEFAULT_SIZE;
        table = new Entry[size];
    }
    LinearProbingHashTable(int s){
        size = s;
        table = new Entry[size];
    }
//Entry will be inserted, if it is half full- rehashes, deleted entries are reusable
//throws an exception if key is NULL, if true then is inserted, otherwise false if duplicate.

    public boolean insert(K key, V value){
        Entry<K,V> newEntry = new Entry<K,V>(key, value);

        if(key == null) throw new NullPointerException("\nKey cannot be null."); //throws exception if key is null

        if(N >= size/2)       rehash(); //If entries>half size, REHASH

        for(int x=getHashValue(key); x < size; x = (x+1)%size) //Finding the place to insert entry
        {

            if( table[x] != null) //If the place has been taken
            {
                //End the loop if the value to be inserted exists in table
                if(table[x].value == value){
                    System.out.println(value.toString() + "- a duplicate value. ");
                    return false;
                }

                //If statement for where we are located
                if(table[x].key == newEntry.key)
                {
                    N++;
                    table[x].value = newEntry.value;
                    return true;
                }
            }
            else{
                N++;
                table[x] = newEntry;
                return true;
            }
        }

        return false;

    }

    //Value will be returned for the key, otherwise returns null if it is not found
    public V find(K key){
        System.out.println("\nSearching for the value associated with -" + key.toString() + "");

        for (int i = getHashValue(key); table[i] != null; i = (i + 1) % size)
            if (table[i].key == key)
                return table[i].value;


        System.out.println("\nValue associated with -" + key.toString() + ", was not found");
        return null;
    }


    public boolean delete(K key){
        if (find(key) == null)
            return false;
        //the entry is marked deleted but proceeds to leave it there,
        //will return true if deleted, otherwise false if not found

        int i = getHashValue(key); //Searching position i of key

        while (!key.equals(table[i].key)) {
            i = (i + 1) % size;
        }

        //To delete key and its associate value
        table[i] = null;

        //Keys in the cluster to be rehashed
        N--;
        rehash();

        return true;
    }

    //Table size to be double, new table to be hashed, removing items which previously marked deleted
    private void rehash(){

        LinearProbingHashTable<K, V> temp;

        if(N > size/2)
            temp = new LinearProbingHashTable<K, V>(size*2);
        else if(N < size/4)
            temp = new LinearProbingHashTable<K, V>(size/2);
        else
            temp = new LinearProbingHashTable<K, V>(size);

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                temp.insert(table[i].key, table[i].value);
            }
        }
        size = temp.size;
        table = temp.table;
    }

    //Returns the hash value for the given key, otherwise -1 if not found,
    //PRIOR TO PROBING

    public int getHashValue(K key){
        return (key.hashCode() & 0x7fffffff) % size;
    }

    //Location of the given key, otherwise -1 if it is not found
    //POST PROBING
    public int getLocation(K key){
        for(int i = 0; i < size; i++){
            if(table[i] != null)
                if(table[i].key == key)
                    return i;
        }
        return -1;

    }

    // Formatting string of the hash table

    public String toString(){

        System.out.println("\n     Hash table: ");

        for(int x =0; x<size; x++){
            if(table[x] != null)
                System.out.println(" " + table[x].key + " " + table[x].value);
        }

        return "";
    }

    private static class Entry<K,V>{
        K key;
        V value;

        Entry(K k, V v){
            key = k;
            value = v;
        }
    }

    public static void main(String[] args) {

        //Generating a New Hash Table
        LinearProbingHashTable<String, Integer> test = new LinearProbingHashTable<String, Integer>();


        //Test Cases
        test.insert("\nName" , 10);
        test.insert("\nUserID" , 4);
        test.insert("\nIndex" , 77);
        test.insert("\nDate" , 35);
        test.insert("\nLoginID" , 1203);

        test.toString(); //Hash Table

        test.insert("\nCourse", 6); //Changing index to a new position

        test.find("\nDate"); //Where is located
        test.find("\nCourse"); //Where is located

        test.find("\nLoginInfo "); //What is not found to be printed

        test.toString(); //Hash Table

        test.delete("\nIndex "); //Value mentioned deleted from Table

        test.toString(); //Hash Table

        System.out.println("\nSize: " + test.size);  //Displays size of the Hash Table

        System.out.println("\nLocating Name in the Hash Table: ");
        System.out.println(test.getLocation("\nName")); //Locates the input value in Table

        System.out.println("\nProgram successful! ");
    }
}
