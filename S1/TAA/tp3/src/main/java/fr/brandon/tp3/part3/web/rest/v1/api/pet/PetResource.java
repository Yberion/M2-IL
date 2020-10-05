package fr.brandon.tp3.part3.web.rest.v1.api.pet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.brandon.tp3.part3.service.pet.PetDTO;

@RestController
@RequestMapping("/api/v1/pet")
public class PetResource
{
    @GetMapping(produces = "application/json" ,path = "/get/{petId}")
    @ResponseBody // Return Pet formated to JSON
    public PetDTO getPetById(@PathVariable("petId") Long petId)
    {
        // return pet
        return new PetDTO();
    }

    @PostMapping("/add")
    @ResponseBody
    public String addPet(@RequestBody PetDTO pet)
    {
        // add pet
        return "Pet properly added";
    }
}
