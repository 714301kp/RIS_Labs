package com.zabello.sessions;

import com.zabello.entities.RailwayTicket;

import javax.ejb.Singleton;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class Tickets {

    static ArrayList<RailwayTicket> tickets = new ArrayList<>(
            Arrays.asList(
                    new RailwayTicket(1L, "soft", 1, new BigDecimal("100"), "lux"),
                    new RailwayTicket(2L, "soft", 1, new BigDecimal("110"), "lux"),
                    new RailwayTicket(3L, "super-soft", 2, new BigDecimal("120"), "lux"),
                    new RailwayTicket(4L, "soft", 3, new BigDecimal("110"), "lux")
                    )
    );

    public List<RailwayTicket> getTicketsByCost(BigDecimal cost) {
        List<RailwayTicket> foundTickets = new ArrayList<>();
        for (RailwayTicket ticket : tickets) {
            if (ticket.getCost().compareTo(cost) < 0) {
                foundTickets.add(ticket);
            }
        }
        return foundTickets;
    }

    public void getTicketsByTrainNumber(Integer trainNumber) {
        List<RailwayTicket> foundTickets = new ArrayList<>();
        for (RailwayTicket ticket : tickets) {
            if (ticket.getTrainNumber().compareTo(trainNumber) == 0) {
                foundTickets.add(ticket);
            }
        }
        writeTicketsToFile(foundTickets);
    }


    private void writeTicketsToFile(List<RailwayTicket> tickets) {
        try (FileWriter writer = new FileWriter("E:/вучоба/РИС/labs/ris2/tickets.txt", false)) {
            for (RailwayTicket ticket : tickets) {
                writer.write(ticket.toString());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}