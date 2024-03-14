




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author kemal
 */
public class todolist extends javax.swing.JFrame {
 String username;
 String password;
 TaskDatabase p = new TaskDatabase();  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jButton1.setText("Авторизация");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Регистрация");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Добавить задачу");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 290, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
username =  JOptionPane.showInputDialog("Имя пользователя");          // авторизация 
         password = JOptionPane.showInputDialog("Пароль");
        
        
        
       if ("".equals(username)) {JOptionPane.showMessageDialog(rootPane, "Неверное имя пользователя или пароль.");} else {      
    try {
        if (p.proverka(username,password) != false ) {
           
            JOptionPane.showMessageDialog(rootPane, "Вход в систему успешный.");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Неверное имя пользователя или пароль.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(todolist.class.getName()).log(Level.SEVERE, null, ex);
    }}
     try {
          /*String s = "Select * FROM tasklist;";
         try {
         p.getAllTasks(s);
         } catch (SQLException ex) {
         Logger.getLogger(todolist.class.getName()).log(Level.SEVERE, "69", ex);
         }*/
         // TODO add your handling code here:
         view();
     } catch (SQLException ex) {
         Logger.getLogger(todolist.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 // регистрация новых пользователей
      
         username = JOptionPane.showInputDialog(rootPane, "Имя пользователя:");
         password = JOptionPane.showInputDialog(rootPane, "Пароль:");
        String email = JOptionPane.showInputDialog(rootPane, "Электронная почта:");
        String query = " INSERT INTO users (username,password,e_mail) \n"
                    + " VALUES ('" + username + "','" + password + "','" + email + "');";
        System.out.println(query);
     try {
         if ( false != p.insert(query)) {
             JOptionPane.showMessageDialog(rootPane, "Регистрация прошла успешно.");}        // TODO add your handling code here:
     } catch (SQLException ex) {
         Logger.getLogger(todolist.class.getName()).log(Level.SEVERE, null, ex);
     }
      
    }//GEN-LAST:event_jButton2ActionPerformed

   public void view () throws SQLException
    {
   MyModel tmod = new MyModel();   
 jTable1.setModel(tmod);
 if (p.TaskDatabase1() != false) { System.out.println("  Тут всё работает   ");
         String s = "SELECT * FROM tasklist;";
          ResultSet rs;
            rs = p.state.executeQuery(s);
         Task task = new Task();
           
        try {
            while (rs.next())
            {
                task.settask_id(rs.getInt(1));
                task.settask_name(rs.getString(2));
                task.setdescription(rs.getString(2));
                task.setdate_create(rs.getString(4));
                task.setdate_done(rs.getString(5));
                task.setpriority(rs.getString(6));
                        task.setstatus(rs.getString(7));
                        tmod.addTask(task);
                        
                /* Task table_temp = new Task (rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                }
                }
                for(Task elem:tables){
                System.out.println(elem + "\n");
                }*/ 
            }
        } catch (SQLException ex) {
            Logger.getLogger(todolist.class.getName()).log(Level.SEVERE, null, ex);
        }
             
   p.TaskDatabase2();}
     
    }
    
  
    
    
    
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     try {                                       // добавление задачи
         if (p.proverka(username,password) != false) {  start_window page = new start_window();
         page.setVisible(true);} else { JOptionPane.showMessageDialog(rootPane, "Авторизуйтесь пожалуйста");
         
         }    } catch (SQLException ex) {
         Logger.getLogger(todolist.class.getName()).log(Level.SEVERE, null, ex);
}
     
    }//GEN-LAST:event_jButton3ActionPerformed

    
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(todolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(todolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(todolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(todolist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new todolist().setVisible(true);
        
 }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
