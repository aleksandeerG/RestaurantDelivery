package com.restaurantdelivery.restaurant.controllers;

import com.restaurantdelivery.restaurant.entities.Livrator;
import com.restaurantdelivery.restaurant.exceptions.BadStatusException;
import com.restaurantdelivery.restaurant.exceptions.LivratorNotFoundException;
import com.restaurantdelivery.restaurant.exceptions.ResourceNotFoundException;
import com.restaurantdelivery.restaurant.exceptions.SameStatusException;
import com.restaurantdelivery.restaurant.repositories.LivratorRepository;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@Validated
public class LivratorController {

    //private static final String LivratorCreateOrUpdateForm = "livratori/createOrUpdateLivratorForm";

    private final LivratorRepository livratori;

    public LivratorController(LivratorRepository livrareService) {
        this.livratori = livrareService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("livratori/{livratorId}")
    public Livrator findLivrator(@PathVariable(name = "livratorId", required = false) Integer livratorId) {
        if(this.livratori.findById(livratorId)!=null)
        {
            return this.livratori.findById(livratorId);
        }
        else
            throw new LivratorNotFoundException(livratorId);
        //return livratorId == null ? throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livrator not found")  : this.livratori.findById(livratorId);
    }
    @GetMapping("/livratori/get-all")
    public List<Livrator> getAllLivratori(){
        List<Livrator> livratorii = new ArrayList<>();
        Streamable.of(livratori.findAll()).forEach(livratorii::add);
        return livratorii;
    }

    @PostMapping("/livratori/new")
    public void save(@RequestBody @Valid Livrator livrator){
        livratori.save(livrator);
    }

    @PutMapping("livratori/{livratorId}")
    public ResponseEntity<Livrator> updateLivrator(@PathVariable(name = "livratorId") Integer id,@RequestBody @Valid Livrator livratorDetails){

            Livrator updateLivrator = livratori.findById(id);
            if (updateLivrator == null) {
                throw new LivratorNotFoundException(id);
            }
            if (updateLivrator.getStatus() == livratorDetails.getStatus())
                throw new SameStatusException();


            updateLivrator.setFirstName(livratorDetails.getFirstName());
            updateLivrator.setLastName(livratorDetails.getLastName());
            updateLivrator.setStatus(livratorDetails.getStatus());
            updateLivrator.setOrders(livratorDetails.getOrders());
            livratori.save(updateLivrator);
            return ResponseEntity.ok(updateLivrator);

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
