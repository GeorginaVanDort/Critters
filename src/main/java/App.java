import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("endangered", EndangeredAnimal.all());
      model.put("animals", Animal.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        Animal newAnimal = new Animal(name);
        newAnimal.save();
        model.put("animal", newAnimal);
        response.redirect("/");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/animal/new-endangered", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        String age = request.queryParams("age");
        String health = request.queryParams("health");
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(name, health, age);
        newEndangeredAnimal.save();
        model.put("endangeredAnimal", newEndangeredAnimal);
        response.redirect("/");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/sighting/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String rangername = request.queryParams("rangername");
        int animalId = Integer.parseInt(request.queryParams("animalId"));
        String location = request.queryParams("location");
        Sighting newSighting = new Sighting(rangername, location, animalId);
        newSighting.save();
        model.put("sighting", newSighting);
        response.redirect("/");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    get("/animals/sightings", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // Animal animal = Animal.searchByName(request.params("name"));
      int animalId = Integer.parseInt(request.queryParams("animalId"));
      List<Sighting> sightings = Sighting.findByAnimal(animalId);
      // model.put("animal", animal);
      model.put("sightings", sightings);
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    exception(IllegalArgumentException.class, (exc, request, response) -> {
       response.status(500);
       VelocityTemplateEngine engine = new VelocityTemplateEngine();
       Map<String, Object> model = new HashMap<String, Object>();
       model.put("rangerName", request.session().attribute("rangerName"));
       model.put("message", exc.getMessage());
       model.put("template", "templates/error.vtl");
       String html = engine.render(new ModelAndView(model, layout));
       response.body(html);
     });
  }
}
