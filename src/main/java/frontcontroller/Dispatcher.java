package frontcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.ToDoController;
import io.javalin.Javalin;
import models.ToDo;

import static io.javalin.apibuilder.ApiBuilder.*;

/*
* This is where we are going to route our endpoints to the specific methods
* */
public class Dispatcher {

    public Dispatcher(Javalin app){
        /*
        * This is where we will route to the correct methods
        * */

        /*
        * REST
        *   - Representational State Transfer
        *   - Architectural standard for HTTP
        *
        * Below is what Restful endpoints look like
        *
        * */

//        app.get("/todo", ToDoController::getAllTodos); //get all todos
//
//        app.get("/todo/{id}", ToDoController::getOneTodo); //get one todo
//
//        app.post("/todo", ToDoController:: createToDo); // create todpo
//
//        app.patch("/todo/{id}", ToDoController:: updateToDo); // update todo
//
//        app.delete("/todo/{id}", ToDoController:: deleteToDo); // delete todo

         /*
        * Restful endpoints
        * - a restful standard
        * -
        * */

        ///////////////////THIS IS THE SAME AS WHAT IS COMMENTED ABOVE
        app.routes(() -> {
           path("todo",() -> {
               get(ToDoController::getAllTodos); //todo GET     //The method reference operator(::) allows
                                                                // us to call a certain method  void method passing a context object
               post(ToDoController::createToDo); //todo POST
               path("{id}", () -> {
                   get(ToDoController::getOneTodo); // /todo/3 GET          get todo with todo id that is 3
                   patch(ToDoController::updateToDo); //  /todo/3 PATCH     update todo with todo id that is 3
                   delete(ToDoController::deleteToDo); // /todo/3 DELETE    delete todo with todo id that is 3
               });
           });
        });

    }

}
