package Klausur.vierzen;

public class Methoden {

    public static int countPairs(int[] arr) {
        int q = 0;
        for(int i = 0; i < arr.length-1; i++) {
            if (arr[i] == arr[i+1]) {
                q++;
            }
        }
        return q;
    }
    /*


     */
    public static int[] countInSecond(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++) {
             int val = arr1[i];
             int count = 0;
             for(int j = 0; j < arr2.length; j++) {
                 if(arr2[j] == val) count++;
             }
             result[i] = count;
        }
        return result;
    }

    public static void compress(Object[] arr) {
        int positionNextNull = 0, positionNextObject = 0;
        while (positionNextNull < arr.length && positionNextObject < arr.length) {
            if(arr[positionNextNull] != null) {
                positionNextNull++;
            }else{
                positionNextObject = positionNextNull+1;
                while (positionNextObject < arr.length && arr[positionNextObject] == null) {
                    positionNextObject++;
                }
                if(positionNextObject >= arr.length) return;
                arr[positionNextNull] = arr[positionNextObject];
                arr[positionNextObject] = null;

            }
        }
    }

    public static boolean isPositiv(int[] arr, int limit) {
        if(limit < 0 || limit >= arr.length) {
            throw new RuntimeException();
        }else{
            if(limit == 0) {
                return arr[limit] > 0;
            }else{
                boolean a = arr[limit] > 0;
                return a && isPositiv(arr,limit-1);
            }
        }
    }

}
