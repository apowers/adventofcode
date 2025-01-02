import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        // List<String> Lines = Files.readAllLines(Paths.get("input/test.txt"));
        List<String> Lines = Files.readAllLines(Paths.get("input/puzzle.txt"));
        
        int safe_count = 0;
        for (String Line : Lines) {
            // Convert an array of strings into an array of integers
            String[] Numbers =  Line.split("\s+");
            Integer[] Values = new Integer[Numbers.length];

            for ( int i = 0; i < Numbers.length; i++ ) {
                Values[i] = Integer.valueOf(Numbers[i]);
            }

            PrintValues(Values);
            if (Safe(Values, true)) {
                safe_count++;
                System.out.println("Safe");
            } else {
                System.out.println("UnSafe");
            }
        }

        System.out.printf("%nSafe %d%n",safe_count);
    }

    // Determine if the list of values is "Safe"
    private static boolean Safe(Integer[] Values, boolean Dampener) throws Exception {
        boolean ascending = (Values[1] > Values [0]);
        for (int i = 1; i < Values.length; i++ ) {
            if ( ascending && Values[i-1] >= Values[i] ) {
                if (!Dampener) return false;
                else {
                    if (i >= 1 && Safe(Dampen(Values, i-1),false) ) return true;
                    if ( Safe(Dampen(Values, i),false) ) return true;
                    if (i >= 2 && Safe(Dampen(Values, i-2),false) ) return true;
                    return false;
                }
            }
            if ( !ascending && Values[i-1] <= Values[i] ) {
                if (!Dampener) return false;
                else {
                    if (i >= 1 && Safe(Dampen(Values, i-1),false) ) return true;
                    if ( Safe(Dampen(Values, i),false) ) return true;
                    if (i >= 2 && Safe(Dampen(Values, i-2),false) ) return true;
                    return false;
                }
            }
            if ( Math.abs(Values[i] - Values[i-1]) > 3 ) {
                if (!Dampener) return false;
                else {
                    if (i >= 1 && Safe(Dampen(Values, i-1),false) ) return true;
                    if ( Safe(Dampen(Values, i),false) ) return true;
                    if (i >= 2 && Safe(Dampen(Values, i-2),false) ) return true;
                    return false;
               }
            }
        }   
        return true;
    }

    // "Dampen" a value from the array at the given position, returning a new array
    private static Integer[] Dampen(Integer[] Values, int position) throws Exception {
        Integer[] NewValues = new Integer[Values.length-1];
        for (int i=0, j=0; i < Values.length; i++, j++ ) {
            if (i == position) {
                j--;
                continue;
            }
            NewValues[j] = Values[i];
        }
        PrintValues(NewValues);
        return NewValues;
    }

    private static void PrintValues(Integer[] Values) throws Exception {
        for (int v : Values) {
            System.out.printf("%d ", v);
        }
        System.out.println();
    }
}
