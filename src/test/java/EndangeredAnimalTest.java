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
  EndangeredAnimal endangered2 = new EndangeredAnimal("Hairy Faced Squrrrl", "healthy", "old");


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
  public void save_assignsIdToEndangeredAnimal() {
    EndangeredAnimal testEndangeredAnimal = endangered;
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.allEndangered().get(0);
    assertEquals(savedEndangeredAnimal.getId(), testEndangeredAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = endangered;
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = endangered2;
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.allEndangered().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.allEndangered().get(1).equals(secondEndangeredAnimal));
  }

  @Test
  public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
    EndangeredAnimal firstEndangeredAnimal = endangered;
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = endangered2;
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
  }

  @Test
  public void searchByName_returnsEndangeredAnimalWithSameName_secondEndangeredAnimal() {
    EndangeredAnimal firstEndangeredAnimal = endangered;
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = endangered2;
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.searchByName("Hairy Faced Squrrrl"), secondEndangeredAnimal);
  }

  @Test(expected = NullPointerException.class)
  public void searchByName_throwsExceptionIfEndangeredAnimalNotFound_true() {
    EndangeredAnimal firstEndangeredAnimal = endangered;
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = endangered2;
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.searchByName("Ducky"), secondEndangeredAnimal);
  }

}
