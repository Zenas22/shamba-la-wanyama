import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import spark.ModelAndView;
import static spark.Spark.*;

public class App{
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }



    public static void main(String[] args){

        port(getHerokuAssignedPort());


        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("endangered", Endangered.all());
            model.put("template", "templates/form.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("rangers", Ranger.all());
            model.put("endangered", Endangered.all());
            model.put("locations", Location.all());
            model.put("template", "templates/sightings.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        post("/species", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int population =Integer.parseInt(request.queryParams("population"));
            boolean endangered =Boolean.parseBoolean(request.queryParams("endangered")) ;
            Endangered danger = new Endangered(name,population,endangered);
            danger.save();
            model.put("endangered", Endangered.all());
            String url = String.format("/views");
            response.redirect(url);
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        post("/ranger" , (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int badge = Integer.parseInt(request.queryParams("badge"));
            Ranger ranger = new Ranger(name, badge);
            ranger.save();
            String url = String.format("/views");
            response.redirect(url);
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        post("/animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int species_id = Integer.parseInt(request.queryParams("species_id"));
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            Animal animal = new Animal(name, age, health, species_id);
            animal.save();
            String url = String.format("/views");
            response.redirect(url);
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        post("/sighted", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int animal = Integer.parseInt(request.queryParams("animalid"));
            int species = Integer.parseInt(request.queryParams("species_id"));
            int location_id = Integer.parseInt(request.queryParams("locationid"));
            int ranger = Integer.parseInt(request.queryParams("rangerid"));
            Sighting sighting = new Sighting(animal,location_id,ranger,species );
            sighting.save();
            String url = String.format("/views");
            response.redirect(url);
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        post("/location", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            Location location = new Location(name);
            location.save();
            String url = String.format("/views");
            response.redirect(url);
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/views", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("sightings", Sighting.all());
            model.put("animals", Animal.all());
            model.put("rangers", Ranger.all());
            model.put("endangered", Endangered.all());
            model.put("locations", Location.all());
            model.put("template", "templates/views.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());
    }




}
