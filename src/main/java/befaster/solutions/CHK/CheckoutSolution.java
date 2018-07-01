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
        total += handleOffer(counts, 'A', NUM_AS_FOR_OFFER, A_OFFER);
        total += handleOffer(counts, 'B', NUM_BS_FOR_OFFER, B_OFFER);
        total = handleSkus(counts, total);

        return total;
    }

    private int handleOffer(Map<Character, Integer> counts, Character character, int count, int value) {
        int total = 0;
        int numSkus = counts.getOrDefault(character, Integer.valueOf(0));
        int numOffers = numSkus / count;
        total += numOffers * value;
        counts.put(character, numSkus - numOffers * count);
        return total;
    }

    private int handleSkus(Map<Character, Integer> counts, int total) {
        for (Map.Entry<Character, Integer> result : counts.entrySet()) {
            Character type = result.getKey();
            if (values.containsKey(type)) {
                Integer value = values.get(type);
                Integer count = result.getValue();
                total += count * value;
            } else {
                return -1;
            }
        }
        return total;
    }

}
