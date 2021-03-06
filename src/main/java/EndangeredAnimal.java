import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class EndangeredAnimal extends Animal {
  private String health;
  private String age;
  private boolean endangered;

  public static final String HEALTH_HIGH = "Healthy";
  public static final String HEALTH_MED = "Medium Health";
  public static final String HEALTH_LOW = "Poor Health";

  public static final String AGE_HIGH = "Adult";
  public static final String AGE_MED = "Youth";
  public static final String AGE_LOW = "Baby";

  public EndangeredAnimal (String name, String health, String age) {
    super(name);
    this.health = health;
    this.age = age;
    this.endangered = true;
  }

  public String getHealth(){
    return health;
  }

  public String getAge(){
    return age;
  }

  public boolean isEndangered() {
    return endangered;
  }

  @Override
  public void save() {
    if ((this.name.length())<=2) {
      throw new IllegalArgumentException("Sorry, the inputted Name is too short");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, endangered, health, age) VALUES (:name, :endangered, :health, :age)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("endangered", this.endangered)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<EndangeredAnimal> allEndangered() {
  String sql = "SELECT * FROM animals WHERE endangered = 'true'";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).throwOnMappingFailure(false)
      .executeAndFetch(EndangeredAnimal.class);
  }
}
}
