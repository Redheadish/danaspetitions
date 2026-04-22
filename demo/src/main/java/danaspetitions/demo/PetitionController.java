package com.dana.danaspetitions.controller;

import com.dana.danaspetitions.model.Petition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PetitionController {

    private final List<Petition> petitions = new ArrayList<>();

    public PetitionController() {
        petitions.add(new Petition(1L, "Save Local Park", "Protect our local park from redevelopment.", "Dana"));
        petitions.add(new Petition(2L, "More Bike Lanes", "The city needs safer bike lanes.", "Mark"));
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/petitions")
    public String listPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "petitions";
    }

    @GetMapping("/petitions/new")
    public String createForm() {
        return "create-petition";
    }

    @PostMapping("/petitions")
    public String createPetition(@RequestParam String title,
                                 @RequestParam String author,
                                 @RequestParam String description) {
        long id = petitions.size() + 1L;
        petitions.add(new Petition(id, title, description, author));
        return "redirect:/petitions";
    }

    @GetMapping("/petitions/search")
    public String searchForm() {
        return "search";
    }

    @GetMapping("/petitions/result")
    public String searchResult(@RequestParam(required = false) String keyword, Model model) {
        List<Petition> results = petitions;

        if (keyword != null && !keyword.isBlank()) {
            String k = keyword.toLowerCase();
            results = petitions.stream()
                    .filter(p ->
                            p.getTitle().toLowerCase().contains(k) ||
                                    p.getAuthor().toLowerCase().contains(k) ||
                                    p.getDescription().toLowerCase().contains(k))
                    .toList();
        }

        model.addAttribute("petitions", results);
        model.addAttribute("keyword", keyword);
        return "search-result";
    }
}