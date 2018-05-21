package pl.training.bank.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleAccountNumberGeneratorTest {

    private static final String ACCOUNT_NUMBER = "00000000000000000000000001";

    @DisplayName("Should return predefined account number ")
    @Test
    void shouldReturnPredefinedAccountNumber() {
        SingleAccountNumberGenerator accountNumberGenerator = new SingleAccountNumberGenerator(ACCOUNT_NUMBER);
        assertEquals(ACCOUNT_NUMBER, accountNumberGenerator.getNext());
    }

}
