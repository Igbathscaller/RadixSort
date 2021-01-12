public class Radix {

    //get nth digit of an int, where 0 is the ones column, 1 is the tens column etc.

    public static int nth(int n, int col){
        int divisor = 10;
        for (int i = 0; i < col; i++){
            divisor *= 10;
        } 
        return Math.abs(n) % divisor;
    }

    //return the number of digits in n.

    public static int length(int n){
        return (int)Math.floor(Math.log(n))+1;
    }
    
    //Merge all of the linked lists in the bucket array into your original linked list. The original may have elements, and those should be kept.

    public static void merge(MyLinkedList original,MyLinkedList[]buckets){

    }

}
