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
      EndangeredAnimal enAnimal = endangered;
      assertEquals(true, enAnimal instanceof EndangeredAnimal);
    }

  @Test
  public void getName_endangeredInstantiatesWithName_true() {
    EndangeredAnimal enAnimal = endangered;
    assertEquals("Secret Owl", enAnimal.getName());
  }

  @Test
  public void getHealth_endangeredInstantiatesWithHealth_true() {
    EndangeredAnimal enAnimal = endangered;
    assertEquals("health", enAnimal.getHealth());
  }

  @Test
  public void getAge_endangeredInstantiatesWithAge_true() {
    EndangeredAnimal enAnimal = endangered;
    assertEquals("youth", enAnimal.getAge());
  }

  @Test
  public void getEndangered_getEndangeRedreturnsTrue_boolean() {
    EndangeredAnimal enAnimal = endangered;
    assertEquals(true, enAnimal.isEndangered());
  }
}
