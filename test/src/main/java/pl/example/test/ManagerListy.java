package pl.example.test;

import java.util.ArrayList;
import java.util.List;

public class ManagerListy {

    private final List<String> lista = new ArrayList<>();

    public void dodajElement(String element) {
        lista.add(element);
    }

    public int pobierzRozmiar() {
        return lista.size();
    }
}
