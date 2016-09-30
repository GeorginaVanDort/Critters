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

  // @Test
  // public void save_insertsObjectIntoDatabase_EndangeredAnimal() {
  //   EndangeredAnimal testEndangeredAnimal = endangered;
  //   testEndangeredAnimal.save();
  //   assertEquals(true, EndangeredAnimal.all().get(0).equals(testBlog));
  // }
  //
  // @Test
  // public void equals_returnsTrueIfNameAndContentAreSame_true() {
  //   Blog testBlog = blog;
  //   Blog anotherBlog = blog;
  //   assertTrue(testBlog.equals(anotherBlog));
  // }

}
