import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  EndangeredAnimal endangered = new EndangeredAnimal("Secret Owl", "health", "youth");

  @Test
    public void endangeredAnimal_instantiatesCorrectly_true() {
      EndangeredAnimal testEndangeredAnimal = endangered;
      assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }

  @Test
  public void getName_endangeredInstantiatesWithName_true() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    assertEquals("Secret Owl", testEndangeredAnimal.getName());
  }

  @Test
  public void getHealth_endangeredInstantiatesWithHealth_true() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    assertEquals("health", testEndangeredAnimal.getHealth());
  }

  @Test
  public void getAge_endangeredInstantiatesWithAge_true() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    assertEquals("youth", testEndangeredAnimal.getAge());
  }

  @Test
  public void getEndangered_getEndangeRedreturnsTrue_boolean() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    assertEquals(true, testEndangeredAnimal.isEndangered());
  }

  @Test
  public void save_insertsObjectIntoDatabase_EndangeredAnimal() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    testEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
  }

  @Test
  public void equals_returnsTrueIfNameAndContentAreSame_true() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    EndangeredAnimal anotherEndangeredAnimal = endangered;
    assertTrue(testEndangeredAnimal.equals(anotherEndangeredAnimal));
  }

  @Test
  public void save_assignsIdToAnimal() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.allEndangered().get(0);
    assertEquals(savedEndangeredAnimal.getId(), testEndangeredAnimal.getId());
  }

  // @Test
  // public void all_returnsAllInstancesOfAnimal_true() {
  //   Animal firstAnimal = animal;
  //   firstAnimal.save();
  //   Animal secondAnimal = donkey;
  //   secondAnimal.save();
  //   assertEquals(true, Animal.all().get(0).equals(firstAnimal));
  //   assertEquals(true, Animal.all().get(1).equals(secondAnimal));
  // }
  //
  // @Test
  // public void find_returnsAnimalWithSameId_secondAnimal() {
  //   Animal firstAnimal = animal;
  //   firstAnimal.save();
  //   Animal secondAnimal = donkey;
  //   secondAnimal.save();
  //   assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
  // }
  //
  // @Test
  // public void getName_animalInstantiatesWithCorrectName_true() {
  //   Animal firstAnimal = animal;
  //   firstAnimal.save();
  //   Animal secondAnimal = donkey;
  //   secondAnimal.save();
  //   assertEquals("Raccoon", firstAnimal.getName());
  //   assertEquals("Donkey", secondAnimal.getName());
  //
  // }


}
