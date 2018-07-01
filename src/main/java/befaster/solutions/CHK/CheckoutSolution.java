package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private static final int NUM_AS_FOR_5_OFFER = 5;
    private static final int A_5_OFFER = 200;
    private static final int NUM_AS_FOR_3_OFFER = 3;
    private static final int A_3_OFFER = 130;
    private static final int NUM_BS_FOR_OFFER = 2;
    private static final int B_OFFER = 45;

    private static Map<Character, Integer> values = new HashMap<>();
    static {
        values.put('A', 50);
        values.put('B', 30);
        values.put('C', 20);
        values.put('D', 15);
        values.put('E', 40);
    }

    public Integer checkout(String skus) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char character : skus.toCharArray()) {
            Integer count = counts.getOrDefault(character, Integer.valueOf(0));
            counts.put(character, ++count);
        }

        int total = 0;
        total += handleOffer(counts, 'A', NUM_AS_FOR_5_OFFER, A_5_OFFER);
        total += handleOffer(counts, 'A', NUM_AS_FOR_3_OFFER, A_3_OFFER);
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
