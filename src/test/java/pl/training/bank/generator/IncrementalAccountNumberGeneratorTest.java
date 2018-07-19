package pl.training.bank.generator;

import org.junit.Test;
import pl.training.bank.generator.service.IncrementalAccountNumberGenerator;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IncrementalAccountNumberGeneratorTest {

    private static final String ACCOUNT_NUMBER_FORMAT = "\\d{26}";

    private IncrementalAccountNumberGenerator accountNumberGenerator = new IncrementalAccountNumberGenerator();

    @Test
    public void shouldGenerateValidAccountNumber() {
        assertTrue(accountNumberGenerator.getNext().matches(ACCOUNT_NUMBER_FORMAT));
    }

    @Test
    public void shouldGenerateNewAccountNumberIncreasingPreviousOne() {
        String initialAccountNumber = accountNumberGenerator.getNext();
        assertEquals(parseInt(initialAccountNumber) + 1, parseInt(accountNumberGenerator.getNext()));
    }

}
