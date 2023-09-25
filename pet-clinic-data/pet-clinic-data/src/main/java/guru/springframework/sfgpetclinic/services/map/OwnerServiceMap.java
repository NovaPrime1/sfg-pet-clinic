package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;


// Service annotation is the same as component but the service is more an indication of the behavior
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long > implements OwnerService {

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
        return super.save(object);
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
        return null;
    }
}
