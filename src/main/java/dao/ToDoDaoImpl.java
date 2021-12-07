package dao;

import models.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class ToDoDaoImpl implements ToDoDao {

    static Logger logger = Logger.getLogger(ToDoDaoImpl.class);
    /*
    * What is a DAO?
    *   Data Access Object
    *       - The dao isolates the persistence layer from the actual application logic
    *       - REVIEW THE DEFINITION OF PERSITENCE LAYER (says it duriung this time)
    *       - We will (generally) have a DAO for each model
    *
    * What is JDBC?
    *   - Java Database Connection
    *
    * JDBC is a technology that allows java to connect to a database
    *   - JDBC is modular. It doesn't just connect to one specific database... it can connect depending on database driver
    *
    * What is needed to connect to a database using JDBC?
    *   - database URL
    *   - db username
    *   - db password
    *   - specific driver for your dbms
    *
    * Important classes and interfaces that utilize JDBC:
    *   - DriverManager (class)
    *       Class for managing JDBC drivers
    *       NOTE: DriverManager.getConnection() will generate our connect object
    *   - Connection (interface)
    *       - Active connection with our database
    *   - Statement (interface)
    *       - The object that represent our SQL statement
    *          - The statement interface itself does not protect against SQL injection
    *          - PreparedStatement (interface) (WHAT WE WILL BE USING) (******)
    *               - Protects against SQL injection (this is what we will be using)
    *          - CallableStatement (interface) (we wont really use)
    *               - Used for stored procedures
    *   - ResultSet (interface)
    *       - object that will have our results
    *
    *
    * The most common error i see...
    *   "No suitable driver found"
    *       -your url has a typo
    *       - you forgot to add the dependency
    *
    * */


    //     jdbc:postgresql://[your endpoint here]/[the specific database you want to connect to]

    String url;
    String username;
    String password;


    public ToDoDaoImpl(){
      this.url = "jdbc:postgresql://" +System.getenv("AWS_RDS_ENDPOINT")+ "/tododatabase";
      this.username = System.getenv("RDS_USERNAME");
      this.password = System.getenv("RDS_PASSWORD");
    }

    public ToDoDaoImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<ToDo> getAllToDos() {
        List<ToDo> todos = new ArrayList<>();

        //Creates active connection to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            //generating our connection
            //Connection conn = DriverManager.getConnection(url, username, password);

            //sql that we will be executing
            String sql = "SELECT * FROM todos;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute the sql statement and return the result set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){ //While there is still a next value
                todos.add(new ToDo(rs.getInt(1), rs.getString(2),rs.getBoolean(3)));
            }
            conn.close();
        }
        catch(SQLException e){
            logger.error(e);
        }
        return todos;
    }

    @Override
    public ToDo getOneToDo(Integer toDoId) {

        ToDo toDo = null;
        //Creates active connection to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            //generating our connection
            //Connection conn = DriverManager.getConnection(url, username, password);

            //sql that we will be executing
            String sql = "SELECT * FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //We are setting the value of the question mark   //It is setInt because we are passing an Int for the method
            ps.setInt(1,toDoId); //We can have multiple "?" and that parameterindex: 1 would mean for the first "?" that is used

            //execute the sql statement and return the result set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){ //While there is still a next value
                toDo = new ToDo(rs.getInt(1), rs.getString(2),rs.getBoolean(3));
            }
            conn.close(); //would need this if didn't open active connection next to try statement
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return toDo;
    }

    @Override
    public void createToDo(ToDo toDo) {
        //Creates active connection to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            //generating our connection
            //Connection conn = DriverManager.getConnection(url, username, password);

            //sql that we will be executing
            String sql = "INSERT INTO todos VALUES(DEFAULT, ?, DEFAULT);";
            PreparedStatement ps = conn.prepareStatement(sql);

            //Set value of question mark, the parameter index is which question mark you want to assign
            ps.setString(1, toDo.getTask());   // it is ".getTask()" because we are calling an object

            //execute the update
            ps.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateAToDo(Integer toDoId) {
        //Creates active connection to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            //generating our connection
            //Connection conn = DriverManager.getConnection(url, username, password);

            //sql that we will be executing
            String sql = "UPDATE todos SET completed = true WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //Set value of question mark, the parameter index is which question mark you want to assign
            ps.setInt(1, toDoId);   // it is ".getTask()" because we are calling an object

            //execute the update
            ps.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAToDo(Integer toDoId) {
        //Creates active connection to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            //generating our connection
            //Connection conn = DriverManager.getConnection(url, username, password);

            //sql that we will be executing
            String sql = "DELETE FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //Set value of question mark, the parameter index is which question mark you want to assign
            ps.setInt(1, toDoId);   // it is ".getTask()" because we are calling an object

            //execute the update
            ps.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

}
