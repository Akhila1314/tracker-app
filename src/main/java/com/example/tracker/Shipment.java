package com.example.tracker;

import java.util.List;

public class Shipment {
    private static final List<String> STAGES =
            List.of("Ordered", "Shipped", "Out for delivery", "Delivered");

    private final int id;
    private final String item;
    private final String location;
    private int stage = 0;

    public Shipment(int id, String item, String location) {
        this.id = id;
        this.item = item;
        this.location = location;
    }

    public void advance() {
        if (stage < STAGES.size() - 1) {
            stage++;
        }
    }

    public int getId() { return id; }
    public String getItem() { return item; }
    public String getLocation() { return location; }
    public String getStatus() { return STAGES.get(stage); }
    public boolean isDelivered() { return stage == STAGES.size() - 1; }
}
