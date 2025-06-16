package org.example;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        int Time1 = t1.getTimeTo() - t1.getTimeFrom();
        int Time2 = t2.getTimeTo() - t2.getTimeFrom();
        if (Time1 < Time2) {
            return -1;
        } else if (Time1 > Time2) {
            return 1;
        } else {
            return 0;
        }
    }
}
