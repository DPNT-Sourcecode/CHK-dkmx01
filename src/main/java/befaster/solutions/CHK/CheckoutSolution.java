package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private static final int NUM_AS_FOR_OFFER = 3;
    private static final int A_OFFER = 130;
    private static final int NUM_BS_FOR_OFFER = 2;
    private static final int B_OFFER = 45;

    private static Map<Character, Integer> values = new HashMap<>();
    static {
        values.put('A', 50);
        values.put('B', 30);
        values.put('C', 20);
        values.put('D', 15);
    }

    public Integer checkout(String skus) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char character : skus.toCharArray()) {
            Integer count = counts.getOrDefault(character, Integer.valueOf(0));
            counts.put(character, ++count);
        }

        int total = 0;

        // Handle A offers.
        int numAs = counts.getOrDefault('A', Integer.valueOf(0));
        int numAOffers = numAs / NUM_AS_FOR_OFFER;
        total += numAOffers * A_OFFER;
        counts.put('A', numAs - numAOffers * NUM_AS_FOR_OFFER);

        // Handle B offers.
        int numBs = counts.getOrDefault('B', Integer.valueOf(0));
        int numBOffers = numBs / NUM_BS_FOR_OFFER;
        total += numBOffers * B_OFFER;
        counts.put('B', numBs - numAOffers * NUM_BS_FOR_OFFER);

        // Handle remaining skus
        for (Map.Entry<Character, Integer> result : counts.entrySet()) {
            Character type = result.getKey();
            Integer value = values.get(type);
            if (value != 0) {
                Integer count = result.getValue();
                total += count * value;
            }
        }

        return total;
    }

}
