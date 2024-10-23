
package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TestPersonService {

  private PersonRepository personRepository;
  private PersonService personService;

  @BeforeEach
  public void setup() {
    personRepository = Mockito.mock(PersonRepository.class);
    personService = new PersonService(personRepository);
  }

  @Test
  public void testGetPersonById() {
    Person person = new Person();
    person.setId(1L);
    when(personRepository.findById(1L)).thenReturn(Optional.of(person));

    Person result = personService.getPersonById(1L);

    assertEquals(person, result);
  }

  @Test
  public void testGetPersonByIdNotFound() {
    when(personRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(1L));
  }

  @Test
  public void testGetPersonByUsername() {
    Person person = new Person();
    person.setUsername("username");
    when(personRepository.findByUsername("username")).thenReturn(Optional.of(person));

    Person result = personService.getPersonByUsername("username");

    assertEquals(person, result);
  }

  @Test
  public void testGetPersonByUsernameNotFound() {
    when(personRepository.findByUsername("username")).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername("username"));
  }

  @Test
  public void testCreate() {
    Person person = new Person();
    when(personRepository.save(person)).thenReturn(person);

    Person result = personService.create(person);

    assertEquals(person, result);
  }
}