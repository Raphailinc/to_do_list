


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kemal
 */
public class Task {
    private int task_id;
    private String task_name;
    private String description;
    private String date_create;
    private String date_done;
    private String priority;
    private String status;

    Task() {
        
    }
    
  
  
  public int task_id() {
        return task_id;
    }
  public void settask_id(int task_id) {
        this.task_id = task_id;
    }
   public String getname() {
        return task_name;
    }
   public void settask_name(String task_name) {
        this.task_name = task_name;
    }
   public String description() {
        return description;
            }
   public void setdescription(String description) {
        this.description = description;
    }
   public String date_create() {
        return date_create;
    }
   public void setdate_create(String date_create) {
        this.date_create = date_create;
    }
   public String date_done() {
        return date_done;
    }
   public void setdate_done(String date_done) {
        this.date_done = date_done;
    }
   public String priority() {
        return priority;
    }
   public void setpriority(String priority) {
        this.priority = priority;
    }
   public String status() {
        return status;
    }
   public void setstatus(String status) {
        this.priority = status;
    }
   

    /*public Task(int task_id, String task_name,String description,String date_create,String date_done,String priority,String status){
        task_id = task_id;
        task_name = task_name;
        description = description;
        date_create = date_create;
        date_done = date_done;
        priority = priority;
        status = status;
    }*/

    

}

