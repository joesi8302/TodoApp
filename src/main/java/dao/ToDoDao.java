package dao;

//This package can also be called repository

import models.ToDo;

import java.util.List;

/*
* Defining out CRUD operations
*   Create, Read, Update, Delete operations
*
* */

/*
* DAO IS OUR PERSISTENCE LAYER /
* */


public interface ToDoDao {
    List<ToDo> getAllToDos();
    ToDo getOneToDo(Integer toDoId);
    void createToDo(ToDo toDo);
    void updateAToDo(Integer toDoId);
    void deleteAToDo(Integer toDoId);
}
