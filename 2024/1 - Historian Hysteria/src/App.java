
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // List<String> Lines = Files.readAllLines(Paths.get("input/test_input.txt"));
        List<String> Lines = Files.readAllLines(Paths.get("input/puzzle_input.txt"));
        
        List<Integer> ListA = new ArrayList<>();
        List<Integer> ListB = new ArrayList<>();

        for (String Line : Lines) {
            String[] Values = Line.split("\s+");
            int A = Integer.parseInt(Values[0]);
            ListA.add(A);
            int B = Integer.parseInt(Values[1]);
            ListB.add(B);
        }

        ListA.sort((a,b) -> a.compareTo(b) );
        ListB.sort((a,b) -> a.compareTo(b) );

        int dist = Distance(ListA, ListB);
        System.out.printf("Distance %d%n",dist);

        int sim = Similarity(ListA, ListB);
        System.out.printf("Similarity: %d%n", sim);
    }

    private static Integer Distance(List<Integer> ListA, List<Integer> ListB) throws Exception {
        int total = 0;
        for (int i = 0; i < ListA.size() ; i++) {
            int A = ListA.get(i);
            int B = ListB.get(i);
            int D = Math.abs(A - B);
            total += D;
        }
        return total;
    }

    private static Integer Similarity(List<Integer> ListA, List<Integer> ListB) throws Exception {
        int total = 0;
        for (int A : ListA) {
            int Count = 0;
            for (int B : ListB ) {
                if (A == B) Count++;
            }
            int D = A * Count;
            total += D;
        }
        return total;
    }
}
