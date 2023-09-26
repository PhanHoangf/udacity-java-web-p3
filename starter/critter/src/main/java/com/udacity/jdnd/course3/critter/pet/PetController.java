package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet (@RequestBody PetDTO petDTO) {
        return petService.createPet( petDTO );
    }

    @GetMapping("/{petId}")
    public PetDTO getPet (@PathVariable long petId) {
        return petService.findPetById( petId );
    }

    @GetMapping
    public List<PetDTO> getPets () {
        return petService.getAllPet();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner (@PathVariable long ownerId) {
        return petService.getPetsByOwnerId( ownerId );
    }
}
