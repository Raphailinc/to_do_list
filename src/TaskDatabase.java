





import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Papech
 */
public class TaskDatabase {
    public Statement state;
    public Connection connection;
     //ArrayList<Task> tables = new ArrayList<>();
    String url = "jdbc:sqlite:C:\\Users\\kemal\\OneDrive\\Документы\\NetBeansProjects\\todolist\\user.db";
   
    

    boolean TaskDatabase1() {
        
        try {
            // Инициализация подключения к базе данных
            connection = DriverManager.getConnection(url);
            state = connection.createStatement();
            System.out.println("\n Подключено");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TaskDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     return false;
    }
    
    void TaskDatabase2() throws SQLException
    { connection.close(); 
    state.close();System.out.println("ЗАкрыта ");
    }
    
    boolean insert( String s) throws SQLException { // тут добавление на базу (обращаемся на базу любым запросом от которого не ждем кого го либо ответа кроме boolean)
       if (TaskDatabase1() != false) {
                
           try {
               state.executeUpdate(s); System.out.println("Rows added"); TaskDatabase2();return true;
           } catch (SQLException ex) {
               Logger.getLogger(TaskDatabase.class.getName()).log(Level.SEVERE, null, ex);
           }
            }
     if (connection != null){TaskDatabase2();}return false;}

    

    public boolean proverka(String username,String password) throws SQLException { 
    String s = " SELECT username , password FROM users WHERE \n" + 
"(username = '"+username+"')AND(password = '"+password+"');";
    
      if (TaskDatabase1() != false) {
          
          ResultSet rs = state.executeQuery(s);
         
          while(rs.next())
          {
              String usename = rs.getString("username");
              String pasword = rs.getString("password");
              
             if ((usename.equals(username))&&(pasword.equals(password))) {TaskDatabase2();return true;}
          }
        
           
      }
      if (connection != null){TaskDatabase2();} return false; };


    /*public void getAllTasks(String s) throws SQLException {
     
       
       if (TaskDatabase1() != false) { System.out.println("  Тут всё работает   ");
         
          ResultSet rs = state.executeQuery(s);
         
           
          while (rs.next())
          { 
           
 Task table_temp = new Task (rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                    tables.add(table_temp);  
                    
                }
                
          }
          
          for(Task elem:tables){
                    System.out.println(elem + "\n");
                } 
          
          if (connection != null){TaskDatabase2();}
       } */
         
    }
    

