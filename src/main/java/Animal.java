import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Animal {
  public static String name;
  public int id;

  public Animal(String name) {
    this.name = name;
  }

  public int getId(){
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
    public boolean equals(Object otherAnimal){
      if (!(otherAnimal instanceof Animal)) {
        return false;
      } else {
        Animal newAnimal = (Animal) otherAnimal;
        return this.id == newAnimal.id && this.name.equals(newAnimal.name);
      }
    }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }
}
