package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name ) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    // Change the return type at the model level so when it get the to view it is already of object type String
    @Override
    public String toString() {
        return  name;
    }
}
