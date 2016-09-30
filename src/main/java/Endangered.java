import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Endangered extends Animal {
  public String health;
  public int age;
  public boolean endangered;

  public Endangered (String health, int age) {
    super(name);
    this.health = health;
    this.age = age;
    endangered = true;
  }
}
