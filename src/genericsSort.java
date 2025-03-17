import java.util.ArrayList;
import java.util.List;

public class genericsSort {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Invalid input. Please provide arguments.");
            System.out.println("Example: java -jar genericsSort.jar <int, float, char, string> 1 2 3 4 5");
            return;
        }

        String type = args[0].toLowerCase();         // checks type of input
        String[] input = new String[args.length - 1];   // makes array with length of input
        System.arraycopy(args, 1, input, 0, args.length - 1);  // copies input to array

        if (type.equals("int")) {             // creats array of respective type and calls sortAndPrint
            Integer[] arr = new Integer[input.length];
            for (int i = 0; i < input.length; i++) {
                arr[i] = Integer.parseInt(input[i]);
            }
            sortAndPrint(arr);
        } else if (type.equals("float")) {
            Float[] arr = new Float[input.length];
            for (int i = 0; i < input.length; i++) {
                arr[i] = Float.parseFloat(input[i]);
            }
            sortAndPrint(arr);
        } else if (type.equals("char")) {
            Character[] arr = new Character[input.length];
            for (int i = 0; i < input.length; i++) {
                arr[i] = input[i].charAt(0);
            }
            sortAndPrint(arr);
        } else if (type.equals("string")) {
            sortAndPrint(input);
        } else {
            System.out.println("Invalid type.");
        }
    }

    private static <E extends Comparable<E>> void sortAndPrint(E[] arr) {  // sort and print with generics
        System.out.println("\nSorting with mergeSort:");
        mergeSort(arr, 0, arr.length - 1);
        for (E e : arr) {
            System.out.print(e.toString() + " ");
        }

        System.out.println("\n\nSorting with bubbleSort:");
        bubbleSort(arr);
        for (E e : arr) {
            System.out.print(e.toString() + " ");
        }
    }

    public static <E extends Comparable<E>> void bubbleSort(E[] arr) {  // bubble sort with generics
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    E temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static <E extends Comparable<E>> void mergeSort(E[] arr, int l, int r){  // merge sort with generics
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public static <E extends Comparable<E>> void merge(E[] arr, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        List<E> L = new ArrayList<>();
        List<E> R = new ArrayList<>();
        for(int i = 0; i < n1; i++){
            L.add(arr[l + i]);
        }
        for(int j = 0; j < n2; j++){
            R.add(arr[m + 1 + j]);
        }

        int i = 0, j = 0, k = l;

        while(i < n1 && j < n2){
            if(L.get(i).compareTo(R.get(j)) <= 0){
                arr[k] = L.get(i);
                i++;
            }else{
                arr[k] = R.get(j);
                j++;
            }
            k++;
        }

        while(i < n1){
            arr[k] = L.get(i);
            i++;
            k++;
        }

        while(j < n2){
            arr[k] = R.get(j);
            j++;
            k++;
        }
    }
}
