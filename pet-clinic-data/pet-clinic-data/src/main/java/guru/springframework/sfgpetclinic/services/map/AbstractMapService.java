package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class AbstractMapService <T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    // We don't know what is coming back so we need to pass in (ID id) and take in an object
    T saveById(ID id, T object) {
        map.put(id, object);

        return object;
    }

    void deleteById(ID id){
        map.remove(id);

    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

}

