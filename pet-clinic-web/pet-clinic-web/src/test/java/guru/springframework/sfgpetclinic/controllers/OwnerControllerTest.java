package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    // Setting up a Mock service to test with.
    @Mock
    OwnerService ownerService;

    // Set up the controller with the mock injected inside of it.
    @InjectMocks
    OwnerController controller;

    Set<Owner> owner;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // object which we use for the return values.
        owner = new HashSet<>();
        owner.add(Owner.builder().id(1l).build());
        owner.add(Owner.builder().id(2l).build());

        // MockMvc builders to set up and MVC environment so it can run inside.
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();


    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owner);

        // Testing list owners by hitting the url, check status the checking view name and expecting Model to have a size 2
        mockMvc.perform(get( "/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect((model().attribute("owners", hasSize(2))));


    }


    // Testing alternate patch to see if the path is correct.
    @Test
    void listOwnersByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(owner);

        mockMvc.perform(get( "/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect((model().attribute("owners", hasSize(2))));


    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));

        // Make sure we have zero interaction with the Mock ownerService
        verifyNoInteractions(ownerService);



    }


    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }


}