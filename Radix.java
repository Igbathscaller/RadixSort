public class Radix {

    //get nth digit of an int, where 0 is the ones column, 1 is the tens column etc.

    public static int nth(int n, int col){
        n = Math.abs(n);
        for (int i = 0; i < col; i++){
            n /= 10;
        } 
        return n % 10;
    }

    //return the number of digits in n.

    public static int length(int n){
        if (n == 0){
            return 1;
        }
        n = Math.abs(n);
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
        int max = 0;
        int current;
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        //initialize each bucket;
        for(int i = 0; i<10; i++){
            buckets[i]=new SortableLinkedList();
        }
        //throwing stuff into buckets
        //also finding maxlength
        while(data.size() > 0){
            current = data.remove(0);
            buckets[nth(current,0)].add(current);
            if (current > max){
                max=current;
            }
        }

        int maxlength = length(max);
        
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

    private static void radixSortNegative(SortableLinkedList data){
        int min = 0;
        int current;
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        //initialize each bucket;
        for(int i = 0; i<10; i++){
            buckets[i]=new SortableLinkedList();
        }
        //throwing stuff into buckets
        //also finding maxlength
        while(data.size() > 0){
            current = data.remove(0);
            buckets[nth(current,0)].add(current);
            if (current < min){
                min=current;
            }
        }

        int maxlength = length(min);
        
        //extending the original data
        for(int i = 9; i>=0; i--){
            data.extend(buckets[i]);
        }

        //looping for each digit
        for(int i = 1; i<maxlength ;i++){
            while(data.size() > 0){
                current = data.remove(0);
                buckets[nth(current,i)].add(current);
            }
            for(int j = 9; j>=0; j--){
                data.extend(buckets[j]);
            }
        }
    }

    //Write a method that sorts any integer values
    public static void radixSort(SortableLinkedList data){
        SortableLinkedList positives = new SortableLinkedList();
        SortableLinkedList negatives = new SortableLinkedList();
        int current;
        while (data.size() > 0){
            current = data.remove(0);
            if (current >= 0){
                positives.add(current);
            }
            else{
                negatives.add(current);
            }
        }
        radixSortSimple(positives);
        radixSortNegative(negatives);
        
        data.extend(negatives);
        data.extend(positives);
    }

}
