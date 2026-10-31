package com.example.tracker;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrackerController {

    private final List<Shipment> shipments = new CopyOnWriteArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    public TrackerController() {
        addShipment("Laptop", "Hyderabad warehouse");
        addShipment("Headphones", "Chennai hub");
    }

    private void addShipment(String item, String location) {
        shipments.add(new Shipment(counter.incrementAndGet(), item, location));
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("shipments", shipments);
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam String item, @RequestParam String location) {
        addShipment(item, location);
        return "redirect:/";
    }

    @PostMapping("/advance/{id}")
    public String advance(@PathVariable int id) {
        shipments.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .ifPresent(Shipment::advance);
        return "redirect:/";
    }
}
