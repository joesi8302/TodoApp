package dao;

import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/*
* What are integration tests? (IT)
*   - Integration tests are testing the parts of your code that connect to external services
*       - This is to test that the external services are behaving as expected.
*
* What is H2
*   H2 is an "in memory" database. This is essentially a database meant for testing and development
*
* When you work on a project with a team, there is a limited amount of connections that can be made to the db at ta time.
*   H2 allows you to have your own database that replicates the real one.
*
* We will be using H2 for out integration tests
*
* */

class ToDoDaoImplIT {

    ToDoDao toDoDao;

    public ToDoDaoImplIT(){
        this.toDoDao = new ToDoDaoImpl(H2Util.url, H2Util.username, H2Util.password);
    }

    @BeforeEach
    void setUp() {
        H2Util.createTable();

    }

    @AfterEach
    void tearDown() {
        H2Util.dropTable();
    }

    @Test
    void getAllToDosIT() {
        //arrange
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"Sweep", false));
        expectedResult.add(new ToDo(2,"mop", false));
        expectedResult.add(new ToDo(3,"Vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        List<ToDo> actualResult = toDoDao.getAllToDos();

        //assert
        assertEquals(expectedResult.toString(),actualResult.toString());
    }

    @Test
    void getOneToDoIT() {
        //arrange
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"Sweep", false));
        expectedResult.add(new ToDo(2,"mop", false));
        expectedResult.add(new ToDo(3,"Vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        ToDo actualResult = toDoDao.getOneToDo(2);

        //assert
        assertEquals(expectedResult.get(1),actualResult);

    }

    @Test
    void createToDoIT() {
        //arrange
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"Sweep", false));
        expectedResult.add(new ToDo(2,"mop", false));
        expectedResult.add(new ToDo(3,"Vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        Integer actualResult = toDoDao.getAllToDos().size();


        //assert

        assertEquals(expectedResult.size(),actualResult);


    }

    @Test
    void updateAToDoIT() {
        //arrange
        ToDo toDoToPass = new ToDo(1,"sweep", false);
        ToDo expectedResult = new ToDo(1, "sweep", true);
        toDoDao.createToDo(toDoToPass);

        //act
        toDoDao.updateAToDo(toDoToPass.getId());
        ToDo actualResult = toDoDao.getOneToDo(toDoToPass.getId());

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteAToDoIT() {
        //arrange
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"Sweep", false));
        expectedResult.add(new ToDo(2,"mop", false));
        expectedResult.add(new ToDo(3,"Vacuum", false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        toDoDao.deleteAToDo(2);
        expectedResult.remove(1);

        List<ToDo> actualResult = toDoDao.getAllToDos();

        //assert
        assertEquals(expectedResult,actualResult);
        assertNull(toDoDao.getOneToDo(2));
    }
}