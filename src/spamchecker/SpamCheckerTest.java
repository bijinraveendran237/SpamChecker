package spamchecker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit test cases for the SpamChecker class.
 */
class SpamCheckerTest {

	@Test
    public void testSingleEmail() {
		SpamChecker checker = new SpamChecker();
		checker.addEmail(new EmailBody("This is the only email."));

        List<Double> spamProbabilities = checker.calculateSpamProbabilities();

        assertEquals(1, spamProbabilities.size());
        assertEquals(0.0, spamProbabilities.get(0), 0.001);
    }

    @Test
    public void testSimilarEmails() {
    	SpamChecker checker = new SpamChecker();
    	checker.addEmail(new EmailBody("This is a spam email. Please buy our product."));
    	checker.addEmail(new EmailBody("This is a spam email. Get our product now."));

        List<Double> spamProbabilities = checker.calculateSpamProbabilities();

        assertEquals(2, spamProbabilities.size());
        assertTrue(spamProbabilities.get(0) > 0.0);
        assertTrue(spamProbabilities.get(1) > 0.0);
    }

    @Test
    public void testDissimilarEmails() {
        SpamChecker checker = new SpamChecker();
        checker.addEmail(new EmailBody("This is a spam email."));
        checker.addEmail(new EmailBody("Hello, how are you?"));

        List<Double> spamProbabilities = checker.calculateSpamProbabilities();

        assertEquals(2, spamProbabilities.size());
        assertEquals(0.0, spamProbabilities.get(0), 0.001);
        assertEquals(0.0, spamProbabilities.get(1), 0.001);
    }

    @Test
    public void testEmptyEmails() {
    	SpamChecker checker = new SpamChecker();

        List<Double> spamProbabilities = checker.calculateSpamProbabilities();

        assertTrue(spamProbabilities.isEmpty());
    }
}
