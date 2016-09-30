import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  Animal animal = new Animal("Raccoon");
  Animal donkey = new Animal("Donkey");


  @Test
  public void animal_instantiatesCorrectly_true() {
    Animal testAnimal = animal;
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void getName_animalInstantiatesWithName_true() {
    Animal testAnimal = animal;
    assertEquals("Raccoon", testAnimal.getName());
  }


  @Test
  public void equals_returnsTrueIfNameAndContentAreSame_true() {
    Animal testAnimal = animal;
    Animal anotherAnimal = animal;
    assertTrue(testAnimal.equals(anotherAnimal));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Animal() {
    Animal testAnimal = animal;
    testAnimal.save();
    assertTrue(Animal.all().get(0).equals(testAnimal));
  }

  @Test
  public void save_assignsIdToAnimal() {
    Animal testAnimal = animal;
    testAnimal.save();
    Animal savedAnimal = Animal.all().get(0);
    assertEquals(savedAnimal.getId(), testAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    Animal firstAnimal = animal;
    firstAnimal.save();
    Animal secondAnimal = donkey;
    secondAnimal.save();
    assertEquals(true, Animal.all().get(0).equals(firstAnimal));
    assertEquals(true, Animal.all().get(1).equals(secondAnimal));
  }

  @Test
  public void find_returnsAnimalWithSameId_secondAnimal() {
    Animal firstAnimal = animal;
    firstAnimal.save();
    Animal secondAnimal = donkey;
    secondAnimal.save();
    assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
  }

  @Test
  public void getName_animalInstantiatesWithCorrectName_true() {
    Animal firstAnimal = animal;
    firstAnimal.save();
    Animal secondAnimal = donkey;
    secondAnimal.save();
    assertEquals("Raccoon", firstAnimal.getName());
    assertEquals("Donkey", secondAnimal.getName());

  }

}
