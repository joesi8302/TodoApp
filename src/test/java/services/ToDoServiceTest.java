package services;

import dao.ToDoDao;
import dao.ToDoDaoImpl;
import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    ToDoDao toDoDao = Mockito.mock(ToDoDao.class); //This is how we mock an object

    ToDoService toDoService;

    public ToDoServiceTest(){
        this.toDoService = new ToDoService(toDoDao);
    }

    @Test
    void getAllTodos() {
        //arrange
        List<ToDo> todos = new ArrayList<>();
        todos.add(new ToDo(1,"sweep",false));
        todos.add(new ToDo(2,"mop", false));
        todos.add(new ToDo(3, "Listen to Kevin Talk",false));
        List<ToDo> expectedValue = todos;
        Mockito.when(toDoDao.getAllToDos()).thenReturn(todos);

        //act
        List<ToDo> actualResult = toDoService.getAllTodos();

        //assert
        assertEquals(expectedValue, actualResult);
    }

    @Test
    void getOneToDo() {
        //arrange
        ToDo expectedResult = new ToDo(1,"sweep",false);
        Mockito.when(toDoDao.getOneToDo(expectedResult.getId())).thenReturn(expectedResult);

        //act
        ToDo actualResult = toDoService.getOneToDo(expectedResult.getId()); //using the .getId() to mimic the actual code and can change easier

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createToDoGreaterThan20() {
        //arrange
        ToDo toDoToTest = new ToDo(1,"Is this greater than?",false);
            //boolean expectedResult = false;

        //act
        boolean actualResult = toDoService.createToDo(toDoToTest);

        //assert
            //assertEquals(expectedResult,actualResult);
        assertFalse(actualResult);
    }

    @Test
    void createToDoLessThanOrEqualTo20() {
        //arrange
        ToDo toDoToTest = new ToDo(1,"Is this less than???",false);

        //act
        boolean actualResult = toDoService.createToDo(toDoToTest);

        //assert
        Mockito.verify(toDoDao).createToDo(toDoToTest);
        assertTrue(actualResult);

    }

    @Test
    void completeToDo(){
        //arrange
        Integer idToTest = 1;

        //act
        toDoService.completeToDo(idToTest);

        //assert

        //Mockito.verify validates that a method was invoked
        Mockito.verify(toDoDao, Mockito.times(1)).updateAToDo(idToTest);
    }

    @Test
    void deleteToDo() {
        //arrange
        Integer idToTest = 1;

        //act
        toDoService.deleteToDo(idToTest);

        //assert

        Mockito.verify(toDoDao).deleteAToDo(idToTest);

    }
}