package de.htwsaar.prog3.carrental.service;

import de.htwsaar.prog3.carrental.model.Costumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 * @author Julian Quint
 */
class CostumerServiceTest {
    private CostumerService costumerService;
    private Costumer expectedCostumer;

    @BeforeEach
    void setUp() {
        this.costumerService = new CostumerService();

        this.expectedCostumer = new Costumer();
        expectedCostumer.setDateOfBirth("01.01.1970");
        expectedCostumer.setDriverLicenseId("31415926535");
        expectedCostumer.setEmailAddress("wbraun@htwsaar.de");
        expectedCostumer.setHouseNumber("141");
        expectedCostumer.setName("Braun");
        expectedCostumer.setPhoneNumber("+492718281828");
        expectedCostumer.setResidence("Saarbrücken");
        expectedCostumer.setStreet("Otto-Hahn Straße");
        expectedCostumer.setSurname("Wolfgang");

        costumerService.persist(expectedCostumer);
    }

    @AfterEach
    void tearDown() {
        costumerService.remove(expectedCostumer);
    }

    @Test
    void testRead() {
        Costumer actualCostumer = costumerService.findById(1L);

        assertThat(actualCostumer, is(equalTo(expectedCostumer)));
    }
}
