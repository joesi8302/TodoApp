import dao.ToDoDao;
import dao.ToDoDaoImpl;
import models.ToDo;
import services.ToDoService;

public class Main {

    public static void main(String[] args) {
//        ToDoDao toDoDao = new ToDoDaoImpl(); //upcasting    we don't want to tightly couple this to postgresql (gives flexibility to mango and other sqls)

        ToDoService toDoService = new ToDoService( new ToDoDaoImpl());

        //toDoService.createToDo(new ToDo(0,"Is this more than",false));

        //toDoService.deleteToDo(3);

        System.out.println(toDoService.getAllTodos());



//        System.out.println(toDoDao.getAllToDos());
//        System.out.println(toDoDao.getOneToDo(2));
//        System.out.println(toDoDao.getOneToDo(3));
//
//        toDoDao.createToDo(new ToDo(0,"do nothing", false));
//
//        toDoDao.updateAToDo(2);
//        toDoDao.deleteAToDo(1);
//
//        System.out.println(toDoDao.getAllToDos());
    }
}
