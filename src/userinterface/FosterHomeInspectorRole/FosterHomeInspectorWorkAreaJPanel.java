/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.FosterHomeInspectorRole;

import Business.AppointmentFrame.Appointment;
import Business.Enterprise.Enterprise;
import Business.Organization.FosterHomeInspectorOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.BackgroundVerificationWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author shahd
 */

public class FosterHomeInspectorWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private FosterHomeInspectorOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private final TableModel tabelModel;
    private List<Integer> time;
    private Appointment appointment;
    
    public FosterHomeInspectorWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, FosterHomeInspectorOrganization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.tabelModel = tblSchedule.getModel();
        //Calendar calendar = dtAppointment.getMonthView().getCalendar();
        //dtAppointment.getMonthView().setLowerBound(calendar.getTime());
        populatePendingRequestTable();
        populateMyRequestTable();
        tableFormatter();
    }
    
    private void tableFormatter(){
        
        //basic formatting
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255,255,255));
        jScrollPane2.getViewport().setBackground(new java.awt.Color(255,255,255));
        panelSchedule.getViewport().setBackground(new java.awt.Color(255,255,255));
        
        //custom renderes, static class below
        tblPendingRequests.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblOngoingRequests.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblSchedule.getTableHeader().setDefaultRenderer(new HeaderColor());
        DefaultTableCellRenderer cellRenderer = new TableCellColor();
        tblPendingRequests.setDefaultRenderer(Object.class, cellRenderer);
        tblOngoingRequests.setDefaultRenderer(Object.class, cellRenderer);
        
        
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
    
    public void populatePendingRequestTable(){
        DefaultTableModel model = (DefaultTableModel)tblPendingRequests.getModel();
        model.setRowCount(0);
        for(WorkRequest request : organization.getWorkQueue().getWorkRequestList()){
             if(request.getReceiver() == null || !request.getReceiver().toString().equals(this.userAccount.getUsername())){
                Object[] row = new Object[2];
                row[0] = request;
                row[1] = request.getSender().getEmployee().getName();
//                row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
//                row[2] = request.getStatus()+"";
                 
                model.addRow(row);
                System.out.println(((BackgroundVerificationWorkRequest)request).getPersonalInfoMap());
           }
        }
    }
    
    private void populateMyRequestTable(){
        DefaultTableModel model = (DefaultTableModel)tblOngoingRequests.getModel();
        model.setRowCount(0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(WorkRequest request : organization.getWorkQueue().getWorkRequestList()){
              if(request.getReceiver() == null || request.getReceiver().toString().equals(this.userAccount.getUsername())){
                Object[] row = new Object[2];
                row[0] = (BackgroundVerificationWorkRequest)request;
                if(request.getAppointment()!=null){
                    Date date = request.getAppointment().getAppointmentDate();
                    String time = tblSchedule.getValueAt(request.getAppointment().getAppointmentTime()[0], 0).toString();
                    String strDate = dateFormat.format(date);
                    row[1] = strDate+""+time;
                }
//                row[1] = request.getSender().getEmployee().getName();
//                row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
//                row[2] = request.getStatus();
                model.addRow(row);
                System.out.println(((BackgroundVerificationWorkRequest)request).getPersonalInfoMap());
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
                 if(app!=null && (!app.isMedicalAppointment())){
                        int[] timeSlots = app.getAppointmentTime();
                         Date appointmentDate = app.getAppointmentDate();
                 if(appointmentDate.equals(dtAppointment.getDate())){
                    String familyName = request.getRequestId()+"";//((BackgroundVerificationWorkRequest)request).getPersonalInfoMap().get("p1fname") + " " +
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOngoingRequests = new javax.swing.JTable();
        btnDetails = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPendingRequests = new javax.swing.JTable();
        btnAssign = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelSchedule = new javax.swing.JScrollPane();
        tblSchedule = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        dtAppointment = new org.jdesktop.swingx.JXDatePicker();

        jLabel3.setText("jLabel3");

        setBackground(new java.awt.Color(255, 255, 255));

        tblOngoingRequests.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        tblOngoingRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Request ID", "Date of Visit"
            }
        ));
        jScrollPane1.setViewportView(tblOngoingRequests);

        btnDetails.setBackground(new java.awt.Color(0, 0, 0));
        btnDetails.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnDetails.setText("Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Currently Pending Requests");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Currently Ongoing Requests");

        tblPendingRequests.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        tblPendingRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Request ID", "Sender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPendingRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPendingRequestsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPendingRequests);

        btnAssign.setBackground(new java.awt.Color(0, 0, 0));
        btnAssign.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnAssign.setForeground(new java.awt.Color(255, 255, 255));
        btnAssign.setText("Schedule");
        btnAssign.setEnabled(false);
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Welcome, Home Inspector!");

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("View My Schedule");

        dtAppointment.setForeground(new java.awt.Color(102, 102, 102));
        dtAppointment.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        dtAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAssign))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1123, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAssign, btnDetails, jButton1});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jScrollPane1, jScrollPane2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dtAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAssign)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDetails)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAssign, btnDetails, jButton1});

    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        // TODO add your handling code here:
        int row = tblOngoingRequests.getSelectedRow();
        if(row!=-1){
            BackgroundVerificationWorkRequest backgroundRequest = (BackgroundVerificationWorkRequest)tblOngoingRequests.getValueAt(row,0);
            HomeInspectionFormJPanel homeInspectionJPanel = new HomeInspectionFormJPanel(userProcessContainer, backgroundRequest, userAccount, organization, enterprise);
            userProcessContainer.add("HomeInspectionFormJPanel", homeInspectionJPanel);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
        else{
            JOptionPane.showMessageDialog(null,"Please Select A Request To Assign");
            return;
        }
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
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
                    appointment.setMedicalAppointment(false);
                    backgroundRequest.setAppointment(appointment);
                  
                    JOptionPane.showMessageDialog(null, "Appointment for request booked successfully");
                    
                    
                    
                    //
                    
                    backgroundRequest.setReceiver(userAccount);
                    backgroundRequest.setStatus("Pending");
                    
                    populateSchedule();
                    populateMyRequestTable();
                    populatePendingRequestTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Please select a time slot");
                }

            }
        }
        }
        
    }//GEN-LAST:event_btnAssignActionPerformed
    
    private void tblPendingRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPendingRequestsMouseClicked
        // TODO add your handling code here:
      if (evt.getClickCount() == 1) {
      JTable target = (JTable)evt.getSource();
      int row = target.getSelectedRow();
      btnAssign.setEnabled(true);
     // do some stuff
    }
    }//GEN-LAST:event_tblPendingRequestsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        populatePendingRequestTable();
        populateMyRequestTable();
        //btnAssign.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
                    
        
        //btnAssign.setEnabled(true);
    }//GEN-LAST:event_dtAppointmentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnDetails;
    private org.jdesktop.swingx.JXDatePicker dtAppointment;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane panelSchedule;
    private javax.swing.JTable tblOngoingRequests;
    private javax.swing.JTable tblPendingRequests;
    private javax.swing.JTable tblSchedule;
    // End of variables declaration//GEN-END:variables
}
