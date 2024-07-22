package spamchecker;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SpamChecker detector = new SpamChecker();

        detector.addEmail(new EmailBody("This is a spam email. Please buy our product."));
        detector.addEmail(new EmailBody("This is a spam email. Get our product now."));
        detector.addEmail(new EmailBody("Hello, I hope you are doing well."));
        detector.addEmail(new EmailBody("Please review the attached document."));

        List<Double> spamProbabilities = detector.calculateSpamProbabilities();

        for (int i = 0; i < spamProbabilities.size(); i++) {
        	double spamProbability = spamProbabilities.get(i) * 100;
            System.out.println("Email " + (i + 1) + " spam probability: " + spamProbability);
        }
    }
}