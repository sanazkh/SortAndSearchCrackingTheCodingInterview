package com.sjsu.sanaz;

public class Main {

    public static int[] bubbleSort(int[] toBeSortedArray){
        for(int i = 0; i < toBeSortedArray.length - 1; i++){
            for(int j = i+1; j < toBeSortedArray.length; j++){
                if(toBeSortedArray[i] > toBeSortedArray[j]){
                    int temp = toBeSortedArray[i];
                    toBeSortedArray[i] = toBeSortedArray[j];
                    toBeSortedArray[j] = temp;
                }
            }
        }
        return toBeSortedArray;
    }

    public static int[] selectionSort(int[] toBeSortedArray){


        for(int i = 0; i < toBeSortedArray.length - 1; i++){
            int minIndex = i;
            for(int j = i+1; j < toBeSortedArray.length; j++){
                if(toBeSortedArray[j] < toBeSortedArray[minIndex]){
                    minIndex = j;
                }
            }
            int temp = toBeSortedArray[minIndex];
            toBeSortedArray[minIndex] = toBeSortedArray[i];
            toBeSortedArray[i] = temp;

        }

        return toBeSortedArray;
    }


    //MergeSort
    public static void mergeSort(int[] array){
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
    }

    public static void mergeSort(int[] array, int[] helper, int low, int high){

        if(low < high){
            int middle = (low + high) /2;
            mergeSort(array, helper, 0, middle);
            mergeSort(array, helper, middle+1, high);
            merge(array, helper, low, middle, high);
        }
    }

    public static void merge(int[] array, int[] helper, int low, int middle, int high){
       for(int i = low; i <= high; i++){
           helper[i] = array[i];
       }

       int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while(helperLeft <= middle && helperRight <= high){
            if(helper[helperLeft] <= helper[helperRight]){
                array[current] = helper[helperLeft];
                helperLeft++;
            }else{
                array[current] = helper[helperRight];
                helperRight++;
            }

            current++;
        }

        /*copy the rest of the left side of the array into the target
        the right half does need to be copied because it is already there.
         */


        int remaining = middle - helperLeft;
        for(int i = 0; i <= remaining; i++){
            array[current +i] = helper[helperLeft +i];
        }
    }

    //Quick sort
    public static void quickSort(int[] array, int left, int right){
        int index = partition(array, left, right);
        if(left < index - 1){
            quickSort(array, left, index - 1);
        }
        if(index < right){
            quickSort(array, index, right);
        }
    }
    public static int partition(int[] array, int left, int right){
        int pivot = array[(left + right) /2];

        while (left <= right){
            while (array[left] <= pivot){
                left++;
            }
            while (array[right] > pivot){
                right--;
            }

            if(left <= right){
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                right--;
                left++;
            }
        }
        return left;
    }

    //Binary search iterative
    public static int binarySearch(int[] a, int x){
        int low = 0;
        int high = a.length - 1;
        int mid;

        while(low <= high){
            mid = (low + high) / 2;
             if(a[mid] > x){
                 high = mid - 1;
             }else if(a[mid] < x){
                 low = mid +1;
             }else{
                 return mid;
             }
        }
        return -1;
    }

    //Binary search recursive
    public static int binarySearchRecursive(int[] a, int x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = (low + high) /2;

        if(a[mid] > x){
            return binarySearchRecursive(a, x, low, mid-1);
        }else if(x > a[mid]){
            return binarySearchRecursive(a, x, mid+1, high);
        }else{
            return mid;
        }
    }


    //10.1 Sorted Merge

    int[] sortedMerge(int[] arrayA, int[] arrayB, int lastIndexA, int lastIndexB){

        int currentIndexA = lastIndexA;
        int currentIndexB = lastIndexB;
        int lastIndexMerged = lastIndexA + lastIndexB - 1;
        while (currentIndexB >= 0){
            if(arrayB[currentIndexB] > arrayA[currentIndexA]){
                arrayA[lastIndexMerged] = arrayB[currentIndexB];
                currentIndexB--;
            }else if(arrayB[currentIndexB] < arrayA[currentIndexA] && currentIndexA >= 0){
                arrayA[lastIndexMerged] = arrayA[currentIndexA];
                currentIndexA--;
            }
            lastIndexMerged--;
        }
        return arrayA;
    }


    //10.3 Search in rotated array
    public static int search(int[] a, int x, int low, int high){
        if(low > high){
            return -1;
        }

        int mid = (low + high) /2;
        if(a[mid] == x){
            return mid;
        }

       if(a[low] < a[mid]){ //left is normally sorted
           if(x < a[mid] && x >= a[low]){
               //search left half
               return search(a, x, low, mid-1);
           }else{
               return search(a, x, mid+1, high);
           }

       }else if(a[mid] < a[high]){ //right is normally sorted
           if(x > a[mid] && x <= a[high]){
               //search right half
               return search(a, x, mid+1, high);
           }else{
               //search left half
               return search(a, x, low, mid-1);
           }
       }else if(a[low] == a[mid]){ //left half is all repeated
           if(a[mid] != a[high]){
               //if right half is different then search it
               return search(a, x, mid+1, high);
           }else{
               //we have to search both halves
               int result = search(a, x, low, mid - 1);
               if(result == -1){
                   //search right
                   return search(a, x, mid+1, high);
               }else{
                   return result;
               }
           }
       }
       return -1;
    }

    public static void main(String[] args) {

        int[] result = bubbleSort(new int[]{ 4, 6, 5, 2, 3, 9, 7, 8, 1});
        int[] result1 = selectionSort(new int[]{ 4, 6, 5, 2, 3, 9, 7, 8, 1});


      //  for(int i : result1){
     //       System.out.println(i);
      //  }

       // mergeSort(new int[]{ 4, 6, 5, 2, 3, 9, 7, 8, 1});

        quickSort(new int[]{ 4, 6, 5, 2, 3, 9, 7, 8, 1}, 4, 1);
        System.out.println(search(new int[]{ 50, 5, 20, 30, 40}, 5, 0, 4));
    }
}
