package pl.example.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.example.test.ManagerListy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerListyTest {

    private ManagerListy managerListy;

    @BeforeEach
    void setUp() {
        managerListy = new ManagerListy();
    }

    @Test
    void testDodajElementPoprawnie() {
        managerListy.dodajElement("A");
        managerListy.dodajElement("B");
        managerListy.dodajElement("C");

        assertEquals(3, managerListy.pobierzRozmiar());
    }

    @Test
    void testPustaListaNaPoczatku() {
        assertEquals(0, managerListy.pobierzRozmiar());
    }
}
