import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class EndangeredAnimal extends Animal {
  private String health;
  private String age;
  private boolean endangered;

  public EndangeredAnimal (String name, String health, String age) {
    super(name);
    this.health = health;
    this.age = age;
    this.endangered = true;
  }

  public boolean isEndangered() {
    return endangered;
  }

  @Override
  public void save() {
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
}
