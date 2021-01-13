public class Radix {

    //get nth digit of an int, where 0 is the ones column, 1 is the tens column etc.

    public static int nth(int n, int col){
        int divisor = 1;
        for (int i = 0; i < col; i++){
            divisor *= 10;
        } 
        return (Math.abs(n) / divisor) % 10;
    }

    //return the number of digits in n.

    public static int length(int n){
        if (n == 0){
            return 1;
        }
        return (int)Math.floor(Math.log(n))+1;
    }
    /*
    Merge all of the linked lists in the bucket array into your original linked list. The original may have elements, and those should be kept.
    This is O(buckets.length) which should be 10 when we use this later.
    The bucket[0] list will be merged first, then the bucket[1] etc.
    */    
    public static void merge(SortableLinkedList original, SortableLinkedList[]buckets){
        for( int i = 0; i < buckets.length; i++){
            original.extend(buckets[i]);
        }
    }

    //Write a method that sorts non-negative integer values
    public static void radixSortSimple(SortableLinkedList data){
        int maxlength = 1;
        int current;
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        //throwing stuff into buckets
        //also finding maxlength
        while(data.size() > 0){
            current = data.remove(0);
            buckets[current%10].add(current);
            if (length(current) > maxlength){
                maxlength=length(current);
            }
        }
        //extending the original data
        for(int i = 0; i<10; i++){
            data.extend(buckets[i]);
        }

        //looping for each digit
        for(int i = 1; i<maxlength ;i++){
            while(data.size() > 0){
                current = data.remove(0);
                buckets[nth(current,i)].add(current);
            }
            for(int j = 0; j<10; j++){
                data.extend(buckets[j]);
            }
        }
    }

    //Write a method that sorts any integer values
    
}
