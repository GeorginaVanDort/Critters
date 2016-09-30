import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class AnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  Animal animal = new Animal("Raccoon");

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

  

}
