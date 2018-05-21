package pl.training.bank.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncrementalAccountNumberGeneratorTest {

    private static final String ACCOUNT_NUMBER_FORMAT = "\\d{26}";

    private IncrementalAccountNumberGenerator accountNumberGenerator = new IncrementalAccountNumberGenerator();

    @DisplayName("Should generate valid account number ")
    @Test
    void shouldGenerateValidAccountNumber() {
        assertTrue(accountNumberGenerator.getNext().matches(ACCOUNT_NUMBER_FORMAT));
    }

    @DisplayName("Should generate a new account number increasing the previous one")
    @Test
    void shouldGenerateNewAccountNumberIncreasingPreviousOne() {
        String initialAccountNumber = accountNumberGenerator.getNext();
        assertEquals(parseInt(initialAccountNumber) + 1, parseInt(accountNumberGenerator.getNext()));
    }

}
