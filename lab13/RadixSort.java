/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int[] arcount;
        int[] arrstarts = new int[256];
        int max = 0;
        for(String x : asciis){
            if(x.length() > max){
                max = x.length();
            }
        }int leng = asciis.length;
        String[] output = new String[leng];
        for(int i = 0; i < leng; i++){
            output[i] = asciis[i];
        }
        char b;
        for(int i = 0; i < max; i++){
            String[] input = new String[leng];
            arcount = new int[256];
            for(String x : output){
                if(x.length()- i - 1 < 0){
                    arcount[0]++;
                }else{
                    b = x.charAt(x.length()- i - 1);
                    arcount[b]++;
                }
            }
            int pos = 0;
            for(int j = 0 ; j < 256; j++){
                arrstarts[j] = pos;
                pos = pos + arcount[j];
            }
            for(String x : output){
                if(x.length()- i - 1 < 0){
                    input[arrstarts[0]] = x;
                    arrstarts[0]++;
                }else{
                    b = x.charAt(x.length()- i - 1);
                    input[arrstarts[b]] = x;
                    arrstarts[b]++;
                }
            }output = input;
        }

        return output;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort\
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
