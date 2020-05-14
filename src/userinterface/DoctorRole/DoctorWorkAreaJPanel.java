/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DoctorRole;

import Business.AppointmentFrame.Appointment;
import Business.Enterprise.Enterprise;
import Business.Organization.DoctorOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.BackgroundVerificationWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shahd
 */
public class DoctorWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoctorWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private DoctorOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private List<Integer> time;
    public DoctorWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, DoctorOrganization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        //valueLabel.setText(enterprise.getName());
        populateTable();
        populateTable1();
        tableFormatter();
    }
    
    public void populateTable(){
        DefaultTableModel model = (DefaultTableModel)tblPendingRequests.getModel();
        model.setRowCount(0);
        for(WorkRequest request : organization.getWorkQueue().getWorkRequestList()){
             if(request.getReceiver() == null ){
                // || !request.getReceiver().toString().equals(this.userAccount.getUsername())
                 
                Object[] row = new Object[4];
                row[0] = request;
                row[1] = request.getSender().getEmployee().getName();
                row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
                row[3] = request.getStatus();
                model.addRow(row);
                System.out.println(((BackgroundVerificationWorkRequest)request).getPersonalInfoMap());
           }
        }
    }
    
    public void populateTable1(){
        DefaultTableModel model = (DefaultTableModel)tblOngoingRequests.getModel();
        model.setRowCount(0);
        for(WorkRequest request : organization.getWorkQueue().getWorkRequestList()){
            if(request.getReceiver() != null ){
             if(request.getReceiver().toString().equals(this.userAccount.getUsername())){
                Object[] row = new Object[4];
                row[0] = request;
                row[1] = request.getSender().getEmployee().getName();
                row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
                row[3] = request.getStatus();
                model.addRow(row);
            }
            }
        }
            
    }
    
        private void populateTableTimeSlots(){
tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"                       9:30   AM"},
                {"                       10:00 AM"},
                {"                       10:30 AM"},
                {"                       11:00 AM"},
                {"                       11:30 AM"},
                {"                       12:00 PM"},
                {"                       12:30 PM"},
                {"                       01:00 PM"},
                {"                       01:30 PM"},
                {"                       02:00 PM"},
                {"                       02:30 PM"},
                {"                       03:00 PM"},
                {"                       03:30 PM"},
                {"                       04:00 PM"},
                {"                       04:30 PM"},
                {"                       05:00 PM"}
            },
            new String [] {
                "Your Schedule"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSchedule.setRowHeight(45);
        panelSchedule.setViewportView(tblSchedule);
        tblSchedule.setRowSelectionAllowed(true);
        tblSchedule.setVisible(false);
    }
    
    private void disableTableTimeSlots(List<Integer> rowIndexes){
        tblSchedule.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                    if(rowIndexes.contains(row)){
                        c.setBackground(Color.darkGray);
                        c.setForeground(new Color (0,102,102));
                        c.setEnabled(false);
                    }
                else{
                        c.setBackground(new Color (0,102,102));
                        c.setForeground(Color.WHITE);
                        c.setEnabled(true);
                    }
                   // setForeground(Color.WHITE);
     
                return this;
            }   
        });
    }
        
    private void populateSchedule(){
        populateTableTimeSlots();
        tblSchedule.setVisible(true);
        time = new ArrayList<>();
        for(WorkRequest request : organization.getWorkQueue().getWorkRequestList()){
            if(request.getReceiver()==null)
                break;
            else if(request.getReceiver().toString().equals(this.userAccount.getUsername())){
                 Appointment app = request.getAppointment();
                 if(app!=null && app.isMedicalAppointment()){
                        int[] timeSlots = app.getAppointmentTime();
                         Date appointmentDate = app.getAppointmentDate();
                 if(appointmentDate.equals(dtAppointment.getDate())){
                    String familyName = request.getRequestId();//((BackgroundVerificationWorkRequest)request).getPersonalInfoMap().get("p1fname") + " " +
                                     //((BackgroundVerificationWorkRequest)request).getPersonalInfoMap().get("p1lname");
                 for(int i : timeSlots){
                       String valueAtRow = tblSchedule.getValueAt(i, 0).toString();
                       time.add(i);
                       valueAtRow = valueAtRow + "    " + familyName;
                       tblSchedule.setValueAt("", i, 0);
                       tblSchedule.setValueAt(valueAtRow, i, 0);
                  }

                 }
                 }
             }
        }
        disableTableTimeSlots(time);
    }
    
        
    private void tableFormatter(){
        
        //basic formatting
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255,255,255));
        jScrollPane2.getViewport().setBackground(new java.awt.Color(255,255,255));
        panelSchedule.getViewport().setBackground(new java.awt.Color(255,255,255));
        
        //custom renderes, static class below
        
        DefaultTableCellRenderer cellRenderer = new TableCellColor();
        tblOngoingRequests.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblSchedule.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblPendingRequests.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblOngoingRequests.setDefaultRenderer(Object.class, cellRenderer);
        tblPendingRequests.setDefaultRenderer(Object.class, cellRenderer);
    }
    
    
    
    static public class HeaderColor extends DefaultTableCellRenderer{
        public HeaderColor(){
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable mytable, Object value, boolean selected, boolean focused, int row, int column){
            JLabel c = (JLabel)super.getTableCellRendererComponent(mytable, value, selected, focused, row, column);
            c.setBackground(new java.awt.Color(0,102,102));
            c.setFont(new Font("Verdana", Font.BOLD, 12));
            c.setForeground(Color.white);
            c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), 
                      BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            setHorizontalAlignment(SwingConstants.CENTER);
            return c;
        }
    }
    
    static public class TableCellColor extends DefaultTableCellRenderer{
        public TableCellColor(){
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable mytable, Object value, boolean selected, boolean focused, int row, int column){
            JLabel c = (JLabel)super.getTableCellRendererComponent(mytable, value, selected, focused, row, column);
            if(row%2 == 0)
                c.setBackground(new java.awt.Color(221,234,234));
            else
              c.setBackground(new java.awt.Color(189,215,215));
            c.setFont(new Font("Verdana", Font.PLAIN, 12));
            c.setForeground(Color.black);
            c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), 
                      BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            setHorizontalAlignment(SwingConstants.CENTER);
            if(selected) 
            {
                c.setBackground(Color.BLACK);
                c.setForeground(Color.WHITE);
            }
            return c;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOngoingRequests = new javax.swing.JTable();
        btnDetails = new javax.swing.JButton();
        btnProce = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPendingRequests = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        refreshJButton = new javax.swing.JButton();
        refreshJButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnAssign1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelSchedule = new javax.swing.JScrollPane();
        tblSchedule = new javax.swing.JTable();
        dtAppointment = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 102));
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("My Requests");

        tblOngoingRequests.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        tblOngoingRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request ID", "Sender", "Receiver", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblOngoingRequests);

        btnDetails.setBackground(new java.awt.Color(0, 0, 0));
        btnDetails.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnDetails.setText("History");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        btnProce.setBackground(new java.awt.Color(0, 0, 0));
        btnProce.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnProce.setForeground(new java.awt.Color(255, 255, 255));
        btnProce.setText("Process");
        btnProce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceActionPerformed(evt);
            }
        });

        tblPendingRequests.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        tblPendingRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request ID", "Sender", "Receiver", "Status"
            }
        ));
        tblPendingRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPendingRequestsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPendingRequests);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Currently Pending Requests");

        refreshJButton.setBackground(new java.awt.Color(0, 0, 0));
        refreshJButton.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        refreshJButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });

        refreshJButton1.setBackground(new java.awt.Color(0, 0, 0));
        refreshJButton1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        refreshJButton1.setForeground(new java.awt.Color(255, 255, 255));
        refreshJButton1.setText("Refresh");
        refreshJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButton1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnAssign1.setBackground(new java.awt.Color(0, 0, 0));
        btnAssign1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnAssign1.setForeground(new java.awt.Color(255, 255, 255));
        btnAssign1.setText("Book");
        btnAssign1.setEnabled(false);
        btnAssign1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssign1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblSchedule.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"                       9:30   AM"},
                {"                       10:00 AM"},
                {"                       10:30 AM"},
                {"                       11:00 AM"},
                {"                       11:30 AM"},
                {"                       12:00 PM"},
                {"                       12:30 PM"},
                {"                       01:00 PM"},
                {"                       01:30 PM"},
                {"                       02:00 PM"},
                {"                       02:30 PM"},
                {"                       03:00 PM"},
                {"                       03:30 PM"},
                {"                       04:00 PM"},
                {"                       04:30 PM"},
                {"                       05:00 PM"}
            },
            new String [] {
                "Your Schedule"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSchedule.setRowHeight(45);
        panelSchedule.setViewportView(tblSchedule);
        tblSchedule.setVisible(false);
        tblSchedule.setRowSelectionAllowed(true);

        dtAppointment.setForeground(new java.awt.Color(102, 102, 102));
        dtAppointment.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        dtAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtAppointmentActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("View My Schedule");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dtAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(refreshJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(refreshJButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAssign1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel2))
                .addGap(35, 35, 35)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDetails, btnProce, refreshJButton, refreshJButton1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(refreshJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAssign1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(refreshJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProce)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnDetails, btnProce, refreshJButton, refreshJButton1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        jLabel3.setBackground(new java.awt.Color(0, 102, 102));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Welcome, Doctor!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblOngoingRequests.getSelectedRow();
        
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a row first!!");
            return;
        }
        
        BackgroundVerificationWorkRequest request = (BackgroundVerificationWorkRequest)tblOngoingRequests.getValueAt(selectedRow, 0);
        
        MedicalVerificationViewJPanel medicalVerificationViewJPanel = new MedicalVerificationViewJPanel(userProcessContainer, request);
        userProcessContainer.add("MedicalVerificationViewJPanel", medicalVerificationViewJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnProceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblOngoingRequests.getSelectedRow();
        
        if (selectedRow < 0){
            return;
        }
        if(tblOngoingRequests.getValueAt(selectedRow,3).equals("Completed")){
            JOptionPane.showMessageDialog(null, "Request Already Completed!");
            return;
        } 
        else{
            BackgroundVerificationWorkRequest request = (BackgroundVerificationWorkRequest)tblOngoingRequests.getValueAt(selectedRow, 0);
            request.setStatus("Processing");
            MedicalVerificationProcessJPanel medicalVerificationWorkRequestJPanel = new MedicalVerificationProcessJPanel(userProcessContainer, request,userAccount,organization,enterprise);
            userProcessContainer.add("MedicalVerificationWorkRequestJPanel", medicalVerificationWorkRequestJPanel);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
    }//GEN-LAST:event_btnProceActionPerformed

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButtonActionPerformed
        populateTable();
    }//GEN-LAST:event_refreshJButtonActionPerformed

    private void refreshJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButton1ActionPerformed
        // TODO add your handling code here:
        populateTable1();
    }//GEN-LAST:event_refreshJButton1ActionPerformed

    private void dtAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtAppointmentActionPerformed
        // TODO add your handling code here:
        Date appDate = dtAppointment.getDate();
                //Date date1 = sdf.parse(inspectionDate.toString());
                Date date2 = ((new Date()));
                if(appDate.before(date2)){
                    JOptionPane.showMessageDialog(null, "Cannot book an appointment for date older than today!");
                    return;
                }
                else{
                    populateSchedule();
                }
        
    }//GEN-LAST:event_dtAppointmentActionPerformed

    private void btnAssign1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssign1ActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = tblPendingRequests.getSelectedRow();
        
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row first");
            return;
        } else{
            
        int requestRow = tblPendingRequests.getSelectedRow();
        int[] timeSlotRow = tblSchedule.getSelectedRows();
        boolean non_clash = true;
        if(timeSlotRow.length==0){
            JOptionPane.showMessageDialog(null, "Please select a date and non-conflicting slot for this appointment");
            return;
        }
        for(int i : timeSlotRow){
            if(time.contains(i)){
                non_clash = false;
                JOptionPane.showMessageDialog(null, "Please select a non-conflicting slot for this appointment");
                break;
            }
        }

        if(non_clash){
            if(requestRow!=-1){
                if(timeSlotRow.length!=0){
                    WorkRequest backgroundRequest = (WorkRequest)tblPendingRequests.getValueAt(requestRow,0);
                    backgroundRequest.setReceiver(userAccount);

                    Date appointmentDate = dtAppointment.getDate();//new Date(tblSchedule.getValueAt(timeSlotRow, 0).toString().trim());
                    Appointment appointment = new Appointment();
                    appointment = new Appointment();
                    appointment.setUserAccount(userAccount);
                    appointment.setRequestID(backgroundRequest.toString());
                    appointment.setAppointmentDate(appointmentDate);
                    appointment.setAppointmentTime(timeSlotRow);
                    appointment.setMedicalAppointment(true);
                    backgroundRequest.setAppointment(appointment);
                  
                    JOptionPane.showMessageDialog(null, "Appointment for request booked successfully");
                    
                    
                    
                    //
                    
                    backgroundRequest.setReceiver(userAccount);
                    backgroundRequest.setStatus("Pending");
                    
                    populateSchedule();
                    populateTable();
                    populateTable1();
                }else{
                    JOptionPane.showMessageDialog(null, "Please select a time slot");
                }

            }
        }
        }

    }//GEN-LAST:event_btnAssign1ActionPerformed

    private void tblPendingRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPendingRequestsMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
      JTable target = (JTable)evt.getSource();
      int row = target.getSelectedRow();
      btnAssign1.setEnabled(true);
        }
    }//GEN-LAST:event_tblPendingRequestsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssign1;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnProce;
    private org.jdesktop.swingx.JXDatePicker dtAppointment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane panelSchedule;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JButton refreshJButton1;
    private javax.swing.JTable tblOngoingRequests;
    private javax.swing.JTable tblPendingRequests;
    private javax.swing.JTable tblSchedule;
    // End of variables declaration//GEN-END:variables
}
