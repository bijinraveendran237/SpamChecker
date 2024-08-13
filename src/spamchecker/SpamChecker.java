package spamchecker;

import java.util.ArrayList;
import java.util.List;


/**
 * The SpamChecker class provides functionality to detect spam emails
 * by analyzing the similarity of email body texts.
 */
public class SpamChecker {
	private List<EmailBody> emails;

	/**
     * Constructs a new SpamChecker instance.
     */
    public SpamChecker() {
        this.emails = new ArrayList<>();
    }

    /**
     * Adds an email to the set of emails to be analyzed.
     *
     * @param email The email to be added.
     */
    public void addEmail(EmailBody email) {
        emails.add(email);
    }

    /**
     * Calculates the similarity between two emails.
     * The similarity is based on the number of common words.
     *
     * @param text1 The first text.
     * @param text2 The second text.
     * @return A double value representing the similarity between the two texts.
     */
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

    /**
     * Calculates the spam probability for each email in the set.
     * The spam probability is based on the similarity to other emails.
     * If there is only one email, the spam probability is set to 0.0.
     *
     * @return A list of double values representing the spam probabilities.
     */
    public List<Double> calculateSpamProbabilities() {
        List<Double> spamProbabilities = new ArrayList<>();
        int emailCount = emails.size();
        for (EmailBody email : emails) {
        	 if (emailCount == 1) {
                 spamProbabilities.add(0.0); // No other emails to compare with, so spam probability is 0%
                 continue;
             }
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
