import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  Sighting sightA = new Sighting("freddy", "in the bushes", 34);
  Sighting sightB = new Sighting("Charlene", "on the hill", 12);


  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = sightA;
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test
  public void getRangerName_sightAInstantiatesWithRangerName_true() {
    Sighting testSighting = sightA;
    assertEquals("freddy", testSighting.getRangerName());
  }

  @Test
  public void getLocation_sightAInstantiatesWithLocation_true() {
    Sighting testSighting = sightA;
    assertEquals("in the bushes", testSighting.getLocation());
  }

  @Test
  public void equals_returnsTrueIfRangerNameAndLocationAreSame_true() {
    Sighting testSighting = sightA;
    Sighting anotherSighting = sightA;
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test
  public void save_insertsSightingIntoDatabase_Sighting() {
    Sighting testSighting = sightA;
    testSighting.save();
    assertTrue(Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void save_assignsIdToSighting() {
    Sighting testSighting = sightA;
    testSighting.save();
    Sighting savedSighting = Sighting.all().get(0);
    assertEquals(savedSighting.getId(), testSighting.getId());
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    Sighting firstSighting = sightA;
    firstSighting.save();
    Sighting secondSighting = sightB;
    secondSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(firstSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    Sighting firstSighting = sightA;
    firstSighting.save();
    Sighting secondSighting = sightB;
    secondSighting.save();
    assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
  }
}
