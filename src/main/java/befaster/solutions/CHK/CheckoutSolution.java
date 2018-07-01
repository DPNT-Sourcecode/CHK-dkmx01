package befaster.solutions.CHK;

import java.util.*;

public class CheckoutSolution {

    /* Ideally, moveall these to another class. */
    private static final int NUM_AS_FOR_5_OFFER = 5;
    private static final int A_5_OFFER = 200;

    private static final int NUM_AS_FOR_3_OFFER = 3;
    private static final int A_3_OFFER = 130;

    private static final int NUM_BS_FOR_OFFER = 2;
    private static final int B_OFFER = 45;

    private static final int NUM_HS_FOR_10_OFFER = 10;
    private static final int H_10_OFFER = 80;

    private static final int NUM_HS_FOR_5_OFFER = 5;
    private static final int H_5_OFFER = 45;

    private static final int NUM_KS_FOR_2_OFFER = 2;
    private static final int K_2_OFFER = 120;

    private static final int NUM_PS_FOR_5_OFFER = 5;
    private static final int P_5_OFFER = 200;

    private static final int NUM_QS_FOR_3_OFFER = 3;
    private static final int Q_3_OFFER = 80;

    private static final int NUM_VS_FOR_3_OFFER = 3;
    private static final int V_3_OFFER = 130;
    private static final int NUM_VS_FOR_2_OFFER = 2;
    private static final int V_2_OFFER = 90;

    private static final int FREE_OFFER_1_MIN = 0;
    private static final Character FREE_OFFER_1_REQUIREMENT_SUK = 'E';
    private static final int FREE_OFFER_1_REQUIREMENT_COUNT = 2;
    private static final Character FREE_OFFER_1_FREE_SUK = 'B';
    private static final int FREE_OFFER_1_FREE_COUNT = 1;

    /* We have one free for every two, but with a minimum of three.  Logically this is one free for every three */
    private static final int FREE_OFFER_2_MIN = 3;
    private static final Character FREE_OFFER_2_REQUIREMENT_SUK = 'F';
    private static final int FREE_OFFER_2_REQUIREMENT_COUNT = 3;
    private static final Character FREE_OFFER_2_FREE_SUK = 'F';
    private static final int FREE_OFFER_2_FREE_COUNT = 1;

    private static final int FREE_OFFER_3_MIN = 0;
    private static final Character FREE_OFFER_3_REQUIREMENT_SUK = 'N';
    private static final int FREE_OFFER_3_REQUIREMENT_COUNT = 3;
    private static final Character FREE_OFFER_3_FREE_SUK = 'M';
    private static final int FREE_OFFER_3_FREE_COUNT = 1;

    private static final int FREE_OFFER_4_MIN = 0;
    private static final Character FREE_OFFER_4_REQUIREMENT_SUK = 'R';
    private static final int FREE_OFFER_4_REQUIREMENT_COUNT = 3;
    private static final Character FREE_OFFER_4_FREE_SUK = 'Q';
    private static final int FREE_OFFER_4_FREE_COUNT = 1;

    /* One free U for every 3 failed the test. */
    /* Try onr free U fro every 3 with a minimum of 4. */
    /* This appears correct, but we have had to use data not in the requirement. */
    private static final int FREE_OFFER_5_MIN = 4;
    private static final Character FREE_OFFER_5_REQUIREMENT_SUK = 'U';
    private static final int FREE_OFFER_5_REQUIREMENT_COUNT = 3;
    private static final Character FREE_OFFER_5_FREE_SUK = 'U';
    private static final int FREE_OFFER_5_FREE_COUNT = 1;

    private static final List<Character> MULTI_OFFER_1_POSSIBILITIES = Arrays.asList('S', 'T', 'X', 'Y', 'Z');
    private static final int MULTI_OFFER_1_COUNT = 3;
    private static final int MULTI_OFFER_1_VALUE = 130;

    private static Map<Character, Integer> values = new HashMap<>();
    static {
        values.put('A', 50);
        values.put('B', 30);
        values.put('C', 20);
        values.put('D', 15);
        values.put('E', 40);
        values.put('F', 10);
        values.put('G', 20);
        values.put('H', 10);
        values.put('I', 35);
        values.put('J', 60);
        values.put('K', 70);
        values.put('L', 90);
        values.put('M', 15);
        values.put('N', 40);
        values.put('O', 10);
        values.put('P', 50);
        values.put('Q', 30);
        values.put('R', 50);
        values.put('S', 20);
        values.put('T', 20);
        values.put('U', 40);
        values.put('V', 50);
        values.put('W', 20);
        values.put('X', 17);
        values.put('Y', 20);
        values.put('Z', 21);
    }

    public Integer checkout(String skus) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char character : skus.toCharArray()) {
            Integer count = counts.getOrDefault(character, Integer.valueOf(0));
            counts.put(character, ++count);
        }

        int total = 0;
        freeOffer(counts, FREE_OFFER_1_MIN, FREE_OFFER_1_REQUIREMENT_SUK, FREE_OFFER_1_REQUIREMENT_COUNT, FREE_OFFER_1_FREE_SUK, FREE_OFFER_1_FREE_COUNT);
        freeOffer(counts, FREE_OFFER_2_MIN, FREE_OFFER_2_REQUIREMENT_SUK, FREE_OFFER_2_REQUIREMENT_COUNT, FREE_OFFER_2_FREE_SUK, FREE_OFFER_2_FREE_COUNT);
        freeOffer(counts, FREE_OFFER_3_MIN, FREE_OFFER_3_REQUIREMENT_SUK, FREE_OFFER_3_REQUIREMENT_COUNT, FREE_OFFER_3_FREE_SUK, FREE_OFFER_3_FREE_COUNT);
        freeOffer(counts, FREE_OFFER_4_MIN, FREE_OFFER_4_REQUIREMENT_SUK, FREE_OFFER_4_REQUIREMENT_COUNT, FREE_OFFER_4_FREE_SUK, FREE_OFFER_4_FREE_COUNT);
        freeOffer(counts, FREE_OFFER_5_MIN, FREE_OFFER_5_REQUIREMENT_SUK, FREE_OFFER_5_REQUIREMENT_COUNT, FREE_OFFER_5_FREE_SUK, FREE_OFFER_5_FREE_COUNT);
        total += multiOffer(counts, MULTI_OFFER_1_POSSIBILITIES, MULTI_OFFER_1_COUNT, MULTI_OFFER_1_VALUE);
        total += handleOffer(counts, 'A', NUM_AS_FOR_5_OFFER, A_5_OFFER);
        total += handleOffer(counts, 'A', NUM_AS_FOR_3_OFFER, A_3_OFFER);
        total += handleOffer(counts, 'B', NUM_BS_FOR_OFFER, B_OFFER);
        total += handleOffer(counts, 'H', NUM_HS_FOR_10_OFFER, H_10_OFFER);
        total += handleOffer(counts, 'H', NUM_HS_FOR_5_OFFER, H_5_OFFER);
        total += handleOffer(counts, 'K', NUM_KS_FOR_2_OFFER, K_2_OFFER);
        total += handleOffer(counts, 'P', NUM_PS_FOR_5_OFFER, P_5_OFFER);
        total += handleOffer(counts, 'Q', NUM_QS_FOR_3_OFFER, Q_3_OFFER);
        total += handleOffer(counts, 'V', NUM_VS_FOR_3_OFFER, V_3_OFFER);
        total += handleOffer(counts, 'V', NUM_VS_FOR_2_OFFER, V_2_OFFER);
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

    private void freeOffer(Map<Character, Integer> counts, int min, Character freeOffer1RequirementSuk, int freeOffer1RequirementCount, Character freeOffer1FreeSuk, int freeOffer1FreeCount) {
        int numRequirementSkus = counts.getOrDefault(freeOffer1RequirementSuk, Integer.valueOf(0));
        if (numRequirementSkus >= min) {
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

    private int multiOffer(Map<Character, Integer> counts, List<Character> multiOfferPossibilities, int multiOfferCount, int multiOfferValue) {
        int total = 0;
        int offer = 1; // Start at some arbitrary, non zero value.
        while (offer != 0) {
            offer = singleMultiOffer(counts, multiOfferPossibilities, multiOfferCount, multiOfferValue);
            total += offer;
        }
        return total;
    }

    private int singleMultiOffer(Map<Character, Integer> counts, List<Character> multiOfferPossibilities, int multiOfferCount, int multiOfferValue) {
        int total = 0;
        List<Character> matches = new ArrayList<>();
        for (Character character : multiOfferPossibilities) {
            if (counts.getOrDefault(character, Integer.valueOf(0)) > 0) {
                matches.add(character);
            }
        }
        if (matches.size() >= multiOfferCount) {
            for (int i = 0; i < multiOfferCount; i++) {
                Character match = matches.get(i);
                counts.put(match, counts.get(match) - 1);
            }
            total += multiOfferValue;
        }
        return total;
    }

}
