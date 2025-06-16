package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AviaSoulsTest {
    @Test // сравнение дешевого с дорогим билетом
    public void CompareToFirstTicketCheaper() {
        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Красноярск", 10_000, 12, 14);

        assertTrue(ticket1.compareTo(ticket2) < 0);
    }

    @Test // сравнение одинаковой стоимости билетов
    public void testCompareToTicketsHaveSamePrice() {
        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Москва", 5_000, 5, 6);

        assertEquals(0, ticket1.compareTo(ticket2));
    }

    @Test // сравнение дорогово с дешевом билетом
    public void testCompareToFirstTicketExpensive() {
        Ticket ticket1 = new Ticket("Томск", "Красноярск", 7_000, 9, 10);
        Ticket ticket2 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);

        assertTrue(ticket1.compareTo(ticket2) > 0);
    }

    @Test //поиск и сартировка по наименьшей стоимости
    public void SearchSortedByPrice() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Красноярск", 7_000, 9, 10);
        Ticket ticket3 = new Ticket("Красноярск", "Якутия", 3_000, 8, 9);
        Ticket ticket4 = new Ticket("Томск", "Красноярск", 10_000, 12, 14);
        Ticket ticket5 = new Ticket("Томск", "Москва", 5_000, 5, 6);
        Ticket ticket6 = new Ticket("Томск", "Красноярск", 2_000, 18, 21);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] actual = manager.search("Томск", "Красноярск");
        Ticket[] expected = {ticket6, ticket2, ticket4};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //поиск и сартировка по наименьшей стоимости (нет подходящих билетов)
    public void SearchSortedByPriceZero() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Красноярск", 7_000, 9, 10);
        Ticket ticket3 = new Ticket("Красноярск", "Якутия", 3_000, 8, 9);
        Ticket ticket4 = new Ticket("Томск", "Красноярск", 10_000, 12, 14);
        Ticket ticket5 = new Ticket("Томск", "Москва", 5_000, 5, 6);
        Ticket ticket6 = new Ticket("Томск", "Красноярск", 2_000, 18, 21);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] actual = manager.search("Красноярск", "Москва");
        Ticket[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //поиск и сартировка по наименьшей стоимости (один подходящий билет)
    public void SearchSortedByPriceOne() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Красноярск", 7_000, 9, 10);
        Ticket ticket3 = new Ticket("Красноярск", "Якутия", 3_000, 8, 9);
        Ticket ticket4 = new Ticket("Томск", "Красноярск", 10_000, 12, 14);
        Ticket ticket5 = new Ticket("Томск", "Москва", 5_000, 5, 6);
        Ticket ticket6 = new Ticket("Томск", "Красноярск", 2_000, 18, 21);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] actual = manager.search("Красноярск", "Якутия");
        Ticket[] expected = {ticket3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //поиск и сартировка по наименьшей стоимости с компаратором
    public void SearchSortedByTimeComparator() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Красноярск", 7_000, 9, 11); // 2
        Ticket ticket3 = new Ticket("Красноярск", "Якутия", 3_000, 8, 9);
        Ticket ticket4 = new Ticket("Томск", "Красноярск", 10_000, 12, 13); // 1
        Ticket ticket5 = new Ticket("Томск", "Москва", 5_000, 5, 6);
        Ticket ticket6 = new Ticket("Томск", "Красноярск", 2_000, 18, 22); // 4

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.searchAndSortBy("Томск", "Красноярск", comparator);
        Ticket[] expected = {ticket4, ticket2, ticket6};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //поиск и сартировка по наименьшей стоимости с компаратором (нет подходящих билетов)
    public void SearchSortedByTimeComparatorZero() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Красноярск", 7_000, 9, 10);
        Ticket ticket3 = new Ticket("Красноярск", "Якутия", 3_000, 8, 9);
        Ticket ticket4 = new Ticket("Томск", "Красноярск", 10_000, 12, 14);
        Ticket ticket5 = new Ticket("Томск", "Москва", 5_000, 5, 6);
        Ticket ticket6 = new Ticket("Томск", "Красноярск", 2_000, 18, 21);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.searchAndSortBy("Красноярск", "Москва", comparator);
        Ticket[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //поиск и сартировка по наименьшей стоимости  с компаратором (один подходящий билет)
    public void SearchSortedByTimeComparatorOne() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Томск", "Новосибирск", 5_000, 8, 10);
        Ticket ticket2 = new Ticket("Томск", "Красноярск", 7_000, 9, 10);
        Ticket ticket3 = new Ticket("Красноярск", "Якутия", 3_000, 8, 9);
        Ticket ticket4 = new Ticket("Томск", "Красноярск", 10_000, 12, 14);
        Ticket ticket5 = new Ticket("Томск", "Москва", 5_000, 5, 6);
        Ticket ticket6 = new Ticket("Томск", "Красноярск", 2_000, 18, 21);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.searchAndSortBy("Красноярск", "Якутия", comparator);
        Ticket[] expected = {ticket3};

        Assertions.assertArrayEquals(expected, actual);
    }
}
