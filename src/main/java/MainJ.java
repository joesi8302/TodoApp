import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDaoImpl;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import models.ToDo;
import services.ToDoService;

import java.util.List;

public class MainJ {


    public static void main(String[] args) {
        //Javalin app = Javalin.create().start(9001);


        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/fontend", Location.CLASSPATH);
        }).start(9000);
        new FrontController(app); //This runs the contructor for each class which allows everything to be created


        /*
        * middleware
        * */
//        app.before("/protected/*",context -> {
//            //check for authentication here
//            System.out.println("before middleware hit");
//        });
//
//        app.after("/protected/*",context -> {
//            System.out.println("After middleware hit");
//        });


        /*
        * What is Json?
        *   JSON stands for JavaScript Object Notation
        *   - JSON is just a universal format for storing and transporting data
        *   - JSON stores data in key value pairs
        * Basic JSON Syntax:
        * {
        *   "id":1,
        *   "task": "This is my task!",
        *   "completed": false
        * }
        * */

//        app.get("/getAllTodos",context -> {
//            System.out.println("endpoint hit");
//            context.contentType("application/json"); //sending back json
//
//            List<ToDo> toDoList = toDoService.getAllTodos(); //get all todos from database
//
//            String jsonString = new ObjectMapper().writeValueAsString(toDoList); //we used jackson to convert our list object to a json string
//
//
//            context.result(jsonString); //send data back
//            //context.result("{\"id\":1, \"task\":\"this is my task\"}");
//        });


//        app.get("/getAllTodos", MainJ::getAllTodos);
//
//
//
//        app.get("/getOneTodo",context -> {
//            Integer todoId = Integer.parseInt(context.queryParam("id")); //This requiers you to put in a queryParam "?id=4"
//            context.contentType("application/json"); //sending back json
//
//            ToDo todo = toDoService.getOneToDo(todoId); //get one todo from database
//
//            context.result(new ObjectMapper().writeValueAsString(todo)); //send data back
//            //http://localhost:9000/getOneTodo?id=4   this is how you would look for it
//        });
//
//        app.post("/createToDo", context -> {
//            ToDo todo = context.bodyAsClass(ToDo.class); //This will convert the json request body to the class equivalent and set it equal to that class.
//            if(toDoService.createToDo(todo)){
//                context.result("Todo Created");
//            }
//            else{
//                context.result("Task was too long... make a task less than 20 characters");
//            }
//        });
//
//        app.patch("/updateToDo",context -> {
//            Integer todoId = Integer.parseInt(context.queryParam("id"));
//
//            toDoService.completeToDo(todoId);
//            context.result("Todo with id " + todoId.toString() + " has been completed");
//        });
//
//        app.delete("/deleteToDo",context -> {
//            Integer todoId = Integer.parseInt(context.queryParam("id"));
//
//            toDoService.deleteToDo(todoId);
//            context.result("Deleted todo with id " + todoId.toString() + " if exists");
//        });


//        app.error(404, context -> {
//            context.result("This is not a valid endpoint.");
//        });

    }

//    public static void getAllTodos(Context context) throws JsonProcessingException {
//        context.contentType("application/json"); //sending back json
//
//        List<ToDo> toDoList = toDoService.getAllTodos(); //get all todos from database
//
//        String jsonString = new ObjectMapper().writeValueAsString(toDoList); //we used jackson to convert our list object to a json string
//
//        context.result(jsonString); //send data back
//    }

}
