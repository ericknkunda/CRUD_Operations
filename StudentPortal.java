/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students_management_app;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Uc
 */
public class StudentPortal extends javax.swing.JFrame implements ActionListener {
    Connection connection;
    StudentsDB studentsDB;
    StudentModel studentModel;
    JButton btnInsert;
    JButton btnView;
    JButton btnUpdate;
    JButton btnDel;
    JTextField txtReg;
    JTextField txtFacult;
    JTextField txtYear;
    JTable jtable;

    /**
     * Creates new form StudentPortal
     */
    public StudentPortal() {
        initComponents();
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        btnInsert =new JButton("Insert");
        btnView =new JButton("View");
        btnUpdate =new JButton("Update");
        btnDel =new JButton("Delete");
        txtReg =new JTextField();
        txtFacult =new JTextField();
        txtYear =new JTextField();
        jtable =new JTable();
        studentsDB =new StudentsDB();
        //
        btnView.addActionListener(this);
    }
    public ArrayList<StudentModel> studentList(){
        ArrayList<StudentModel> StudentList= new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/students?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root",null );
            System.out.println("Connection established");
            String querry ="SELECT * FROM university";
            Statement st =connection.createStatement();
            ResultSet rs =st.executeQuery(querry);
            StudentModel studentModel;
            while(rs.next()){
                studentModel =new StudentModel(rs.getInt("StudentNum"),rs.getString("Registration"),rs.getString("Facult"),rs.getString("Year"));
                StudentList.add(studentModel);
            }   
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return StudentList;
        
    }
    public void showStudents(){
        ArrayList<StudentModel> StudentLists =studentList();
        DefaultTableModel model =(DefaultTableModel)Students_Table.getModel();
        Object[] row =new Object[4];
        for(int i =0; i<StudentLists.size(); i++){
            row[0] =StudentLists.get(i).getStudentNum();
            row[1] =StudentLists.get(i).getREG_NO();
            row[2] =StudentLists.get(i).getFacult();
            row[3] =StudentLists.get(i).getYearOfStudy();
            model.addRow(row);
        }
    }
    public void onUpdate(){
        try{
            DefaultTableModel model= (DefaultTableModel)Students_Table.getModel();
            int rows =model.getRowCount();
            for(int i =0;i<rows;i++){
                String year =model.getValueAt(i, 3).toString();
                 String num = model.getValueAt(i, 0).toString();
                 
                //Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/students?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root",null );
            
                String querry ="UPDATE university SET Year ="+year+" WHERE StudentNum ="+num+"";
                PreparedStatement ps =connection.prepareStatement(querry);
                ps.executeUpdate();
                
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void onDelete(){
       
        try{
             DefaultTableModel model= (DefaultTableModel)Students_Table.getModel();
             int row =Students_Table.getSelectedRow();
             if(row ==0){
                 JOptionPane.showMessageDialog(this, "Please select at least a row");
             }
             else{
                 if(Students_Table.getRowCount() ==0){
                    JOptionPane.showMessageDialog(this, "empty table"); 
                 }
             }
             String value =Students_Table.getModel().getValueAt(row, 0).toString();
             String querry ="DELETE FROM university WHERE StudentNum ="+value;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/students?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root",null );
            //System.out.println("Row Deletred");
            
                PreparedStatement ps =connection.prepareStatement(querry);
                ps.executeUpdate();
               model.setRowCount(0);
               showStudents();
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    public void actionPerformed(ActionEvent e){
         
            if(e.getSource().equals(jButton1)){
                showStudents();
            }
            if(e.getSource().equals(jButton2)){
                onUpdate();
            }
            if(e.getSource().equals(jButton3)){
                onDelete();
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Students_Table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Students Registration Portal");
        setBounds(new java.awt.Rectangle(100, 100, 400, 400));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Registratin Number");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Facult");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Year");

        jTextField1.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jTextField1.setName("txtReg"); // NOI18N

        jTextField2.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jTextField2.setName("txtFacult"); // NOI18N

        jTextField3.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jTextField3.setName("txtYear"); // NOI18N

        jButton1.setText("view");
        jButton1.setName("btnView"); // NOI18N

        jButton2.setText("update");
        jButton2.setName("btnUpdate"); // NOI18N

        jButton3.setText("delete");
        jButton3.setName("btnDel"); // NOI18N

        jButton4.setText("insert");
        jButton4.setName("btnInsert"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Students_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUDENT NUMBER", "REG_NO", "FACULT", "YEAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Students_Table.setName("jTable"); // NOI18N
        jScrollPane1.setViewportView(Students_Table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton2)
                        .addGap(45, 45, 45)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{    
           String reg =jTextField1.getText();
            String fac =jTextField2.getText();
            String year =jTextField3.getText();
            studentModel =new StudentModel(reg,fac,year);
            JOptionPane.showMessageDialog(null, "You are about to save");
            studentsDB.InsertData(studentModel); 
            showStudents();
        }
        catch(NullPointerException exc){
            exc.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(StudentPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)throws Exception{
   showStudents();
    
}
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentPortal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Students_Table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

  
   
}
