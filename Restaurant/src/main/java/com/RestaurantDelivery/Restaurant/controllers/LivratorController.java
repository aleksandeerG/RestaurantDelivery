package com.restaurantdelivery.restaurant.controllers;

import com.restaurantdelivery.restaurant.entities.Comanda;
import com.restaurantdelivery.restaurant.entities.Livrator;
import com.restaurantdelivery.restaurant.exceptions.*;
import com.restaurantdelivery.restaurant.services.LivratorService;
import com.sun.net.httpserver.Authenticator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@Validated
@RequestMapping(path = "/livratori/")
public class LivratorController {

    //private static final String LivratorCreateOrUpdateForm = "livratori/createOrUpdateLivratorForm";
    @Autowired
    private LivratorService livratorService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("{livratorId}")
    public ResponseEntity<Livrator> findLivrator(@PathVariable(name = "livratorId", required = false) Integer livratorId) {
        if (livratorService.findLivrator(livratorId) != null) {
            return ResponseEntity.ok(livratorService.findLivrator(livratorId));
        } else
            throw new LivratorNotFoundException(livratorId);
        //return livratorId == null ? throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livrator not found")  : this.livratori.findById(livratorId);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<Livrator>> getAllLivratori() {
        return ResponseEntity.ok(livratorService.getAllLivratori());
    }

    @PostMapping("new")
    public ResponseEntity<Livrator> save(@RequestBody @Valid Livrator livrator) {

        return ResponseEntity.ok(livratorService.saveNewLivrator(livrator));
    }

    @PutMapping("{livratorId}")
    public ResponseEntity<Livrator> updateLivrator(@PathVariable(name = "livratorId") Integer id, @RequestBody @Valid Livrator livratorDetails) {
        return ResponseEntity.ok(livratorService.updateLivrator(id, livratorDetails));
    }

    @PostMapping("{livratorId}/comenzi/new")
    public ResponseEntity<Comanda> save(@RequestBody Comanda comanda, @PathVariable("livratorId") int livratorId) {
        return ResponseEntity.ok(livratorService.saveComanda(comanda, livratorId));

    }


    //    @GetMapping("/livratori/new")
//    public String initCreationForm(Map<String, Object> model) {
//        Livrator livrator = new Livrator();
//        model.put("livrator", livrator);
//        return LivratorCreateOrUpdateForm;
//    }
//
//    @PostMapping("/livratori/new")
//    public String processCreationForm(@Valid Livrator livrator, BindingResult result) {
//        if (result.hasErrors()) {
//            return LivratorCreateOrUpdateForm;
//        }
//        this.livratori.save(livrator);
//        return "redirect:/livratori/" + livrator.getId();
//    }
}
