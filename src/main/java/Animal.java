import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Animal {
  public String name;
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

  public static List<Animal> all() {
    String sql = "SELECT * FROM animals";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Animal.class);
    }
  }

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id";
      Animal animal = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  public static Animal searchByName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where name=:name";
      Animal animal = con.createQuery(sql)
        .addParameter("name", name)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Animal.class);
        if (animal == null) {
          throw new NullPointerException("That animal wasn't found in the database. Check your spelling and try again.");
        }
      return animal;
    }
  }

}
