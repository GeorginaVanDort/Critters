import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;


public class Sighting {
  private int id;
  private String location;
  private String rangerName;
  private int animalId;
  private Timestamp time;


  public Sighting(String rangerName, String location, int animalId){
    this.location = location;
    this.rangerName = rangerName;
    this.animalId = animalId;
  }

  public int getId(){
    return id;
  }

  public String getLocation(){
    return location;
  }

  public String getRangerName() {
    return rangerName;
  }

  public int getAnimalId() {
    return animalId;
  }

  public Timestamp getTime() {
    return time;
  }

  @Override
  public boolean equals(Object otherSighting){
    if (!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.id == newSighting.id && this.location.equals(newSighting.location) && this.rangerName.equals(newSighting.rangerName);
    }
  }

  public void save() {
    if ((this.rangerName.length())<=2) {
      throw new IllegalArgumentException("Please enter your full name");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (location, rangerName, animal_id, time) VALUES (:location, :rangerName, :animalId, now())";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("location", this.location)
        .addParameter("rangerName", this.rangerName)
        .addParameter("animalId", this.animalId)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    String sql = "SELECT * FROM sightings";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings where id=:id";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    }
  }

}
