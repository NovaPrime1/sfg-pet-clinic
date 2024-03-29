package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


// Service annotation is the same as component but the service is more an indication of the behavior
@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long > implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    /*** This was not happy with how it was called. I need to create a new method saveByID which takes and ID
     // This is a different implementation which I need to remember.
     // @Override
     // public Owner save(Owner object) {
     // return super.save(object.getId(), object);
     } **/
    @Override
    public Owner save(Owner object) {

        Owner saveOwner = null;
        // Review that logic again. It to replace the ID that would be generated by RDS
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    } if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
        .stream()
        .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
        .findFirst()
        .orElse( null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        //todo - impl
        return null;
    }
}
