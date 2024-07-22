package spamchecker;

import java.util.ArrayList;
import java.util.List;

public class SpamChecker {
	private List<EmailBody> emails;

    public SpamChecker() {
        this.emails = new ArrayList<>();
    }

    public void addEmail(EmailBody email) {
        emails.add(email);
    }

    public double calculateSimilarity(String text1, String text2) {
        String[] words1 = text1.split("\\s+");
        String[] words2 = text2.split("\\s+");

        int commonWords = 0;
        for (String word1 : words1) {
            for (String word2 : words2) {
                if (word1.equalsIgnoreCase(word2)) {
                    commonWords++;
                }
            }
        }

        return (double) commonWords / Math.max(words1.length, words2.length);
    }

    public List<Double> calculateSpamProbabilities() {
        List<Double> spamProbabilities = new ArrayList<>();
        for (EmailBody email : emails) {
            double similaritySum = 0.0;
            for (EmailBody otherEmail : emails) {
                if (email != otherEmail) {
                    similaritySum += calculateSimilarity(email.getBody(), otherEmail.getBody());
                }
            }
            double spamProbability = similaritySum / (emails.size() - 1);
            spamProbabilities.add(spamProbability);
        }
        return spamProbabilities;
    }
}
