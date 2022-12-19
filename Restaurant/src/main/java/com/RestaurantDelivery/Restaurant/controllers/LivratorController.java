package com.restaurantdelivery.restaurant.controllers;

import com.restaurantdelivery.restaurant.entities.Livrator;
import com.restaurantdelivery.restaurant.repositories.LivratorRepository;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Map;

@Controller
public class LivratorController {

    private static final String LivratorCreateOrUpdateForm = "livratori/createOrUpdateLivratorForm";

    private final LivratorRepository livratori;

    public LivratorController(LivratorRepository livrareService) {
        this.livratori = livrareService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("livrator")
    public Livrator findLivrator(@PathVariable(name = "livratorId", required = false) Integer livratorId) {
        return livratorId == null ? new Livrator() : this.livratori.findById(livratorId);
    }

    @GetMapping("/livratori/new")
    public String initCreationForm(Map<String, Object> model) {
        Livrator livrator = new Livrator();
        model.put("livrator", livrator);
        return LivratorCreateOrUpdateForm;
    }

    @PostMapping("/livratori/new")
    public String processCreationForm(@Valid Livrator livrator, BindingResult result) {
        if (result.hasErrors()) {
            return LivratorCreateOrUpdateForm;
        }
        this.livratori.save(livrator);
        return "redirect:/livratori/" + livrator.getId();
    }

}
