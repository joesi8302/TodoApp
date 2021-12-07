package services;

//Can have multiple daos references within the service package

import dao.ToDoDao;
import dao.ToDoDaoImpl;
import models.ToDo;

import java.util.List;

/*
* Service layer is where we do our business logic.
*
*
* Example: If we had a limitation on the length of the todo task, we would do that logic here
* */


public class ToDoService {
    ToDoDao toDoDao;

    public ToDoService(ToDoDao toDoDao){
        this.toDoDao = toDoDao;
    }

    public ToDoService(){
        this.toDoDao = new ToDoDaoImpl();
    }

    public List<ToDo> getAllTodos(){
        return toDoDao.getAllToDos();
    }

    public ToDo getOneToDo(Integer id){
        return toDoDao.getOneToDo(id);
    }


    //if we wanted to do any check statements or methods, this is where we would do our logic

    //business logic example  //What we want to check before throwing into the DAO
    public Boolean createToDo(ToDo toDo){
        if(toDo.getTask().length() >20)
            return false;
        toDoDao.createToDo(toDo);
        return true;
    }

    public void completeToDo(Integer toDoId){
        toDoDao.updateAToDo(toDoId);
    }

    public void deleteToDo(Integer toDoId){
        toDoDao.deleteAToDo(toDoId);
    }


}
