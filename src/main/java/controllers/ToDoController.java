package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.ExceptionThrower;
import dao.ToDoDaoImpl;
import io.javalin.http.Context;
import models.ToDo;
import services.ToDoService;

import java.util.List;

public class ToDoController {

    static ToDoService toDoService = new ToDoService();

    public static void getAllTodos(Context context) throws JsonProcessingException {
        context.contentType("application/json"); //sending back json

        List<ToDo> toDoList = toDoService.getAllTodos(); //get all todos from database

        String jsonString = new ObjectMapper().writeValueAsString(toDoList); //we used jackson to convert our list object to a json string

        context.result(jsonString); //send data back
    }

    public static void getOneTodo(Context context) throws JsonProcessingException {

        Integer todoId = Integer.parseInt(context.pathParam("id")); //This requires you to put in a queryParam "?id=4"
        context.contentType("application/json"); //sending back json

        ToDo todo = toDoService.getOneToDo(todoId); //get one todo from database

        context.result(new ObjectMapper().writeValueAsString(todo)); //send data back
        //http://localhost:9000/getOneTodo?id=4   this is how you would look for it
    }

    public static void createToDo(Context context) throws JsonProcessingException {
        ToDo todo = context.bodyAsClass(ToDo.class); //This will convert the json request body to the class equivalent and set it equal to that class.
        if(toDoService.createToDo(todo)){
            context.result("Todo Created" );
        }
        else{
            context.result("Task was too long... make a task less than 20 characters");
        }
    }

    public static void updateToDo(Context context) {
        Integer todoId = Integer.parseInt(context.pathParam("id"));

        toDoService.completeToDo(todoId);
        context.result("Todo with id " + todoId.toString() + " has been completed");
    }

    public static void deleteToDo(Context context) {
        Integer todoId = Integer.parseInt(context.pathParam("id"));

        toDoService.deleteToDo(todoId);
        context.result("Deleted todo with id " + todoId.toString() + " if exists");
    }
}
