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

    private static final Character FREE_OFFER_1_REQUIREMENT_SUK = 'E';
    private static final int FREE_OFFER_1_REQUIREMENT_COUNT = 2;
    private static final Character FREE_OFFER_1_FREE_SUK = 'B';
    private static final int FREE_OFFER_1_FREE_COUNT = 1;

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
        freeOffer(counts, FREE_OFFER_1_REQUIREMENT_SUK, FREE_OFFER_1_REQUIREMENT_COUNT, FREE_OFFER_1_FREE_SUK, FREE_OFFER_1_FREE_COUNT);
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

    private void freeOffer(Map<Character, Integer> counts, Character freeOffer1RequirementSuk, int freeOffer1RequirementCount, Character freeOffer1FreeSuk, int freeOffer1FreeCount) {
        int numRequirementSkus = counts.getOrDefault(freeOffer1RequirementSuk, Integer.valueOf(0));
        int numFreeSkus = numRequirementSkus / freeOffer1RequirementCount;
        if (numFreeSkus > 0) {
            int numOfferSkus = counts.getOrDefault(freeOffer1FreeSuk, Integer.valueOf(0));
            numOfferSkus -= numFreeSkus * freeOffer1FreeCount;
            if (numOfferSkus < 0) {
                numOfferSkus = 0;
            }
            counts.put(freeOffer1FreeSuk, numOfferSkus);
        }
    }

}
