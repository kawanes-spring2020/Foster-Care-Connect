/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.FosterCaseworkerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.FosterCaseworkerOrganization;
import Business.Organization.FosterHomeInspectorOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.BackgroundVerificationWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
 * @author divya
 */

public class FosterCaseWorkerManageFamilyJPanel extends javax.swing.JPanel {

    /**
     * Creates new form FosterCaseWorkerManageFamilyJPanel
     */
    private JPanel userProcessContainer;
    private FosterCaseworkerOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem business;
    private BackgroundVerificationWorkRequest request;
    private Network network;
    public FosterCaseWorkerManageFamilyJPanel(JPanel userProcessContainer, UserAccount account, FosterCaseworkerOrganization organization, Enterprise enterprise,Network network, EcoSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.enterprise = enterprise;
        this.organization = organization;
        this.business = business;
        this.network = network;
        populateBackgroundTable();
        tableFormatter();
    }
    private void tableFormatter(){
        
        //basic formatting
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255,255,255));
        jScrollPane4.getViewport().setBackground(new java.awt.Color(255,255,255));
        jScrollPane5.getViewport().setBackground(new java.awt.Color(255,255,255));
        
        //custom renderes, static class below
        tblFosterFamBackgroundReq.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblFosterFamHomeInsReq.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblFosterFamMedicalReq.getTableHeader().setDefaultRenderer(new HeaderColor());
        DefaultTableCellRenderer cellRenderer = new TableCellColor();
        tblFosterFamBackgroundReq.setDefaultRenderer(Object.class, cellRenderer);
        tblFosterFamHomeInsReq.setDefaultRenderer(Object.class, cellRenderer);
        tblFosterFamMedicalReq.setDefaultRenderer(Object.class, cellRenderer);
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

        btnReqHouseInspection = new javax.swing.JButton();
        btnAddNewFamily = new javax.swing.JButton();
        btnReqMedicalAppt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFosterFamBackgroundReq = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblFosterFamMedicalReq = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblFosterFamHomeInsReq = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnInitiateFosterProcess = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        btnReqHouseInspection.setBackground(new java.awt.Color(0, 0, 0));
        btnReqHouseInspection.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnReqHouseInspection.setForeground(new java.awt.Color(255, 255, 255));
        btnReqHouseInspection.setText("Request Home Inspection");
        btnReqHouseInspection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReqHouseInspectionActionPerformed(evt);
            }
        });

        btnAddNewFamily.setBackground(new java.awt.Color(0, 0, 0));
        btnAddNewFamily.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnAddNewFamily.setForeground(new java.awt.Color(255, 255, 255));
        btnAddNewFamily.setText("Add New Foster Family");
        btnAddNewFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewFamilyActionPerformed(evt);
            }
        });

        btnReqMedicalAppt.setBackground(new java.awt.Color(0, 0, 0));
        btnReqMedicalAppt.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnReqMedicalAppt.setForeground(new java.awt.Color(255, 255, 255));
        btnReqMedicalAppt.setText("Request Medical Appointment");
        btnReqMedicalAppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReqMedicalApptActionPerformed(evt);
            }
        });

        tblFosterFamBackgroundReq.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        tblFosterFamBackgroundReq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Organization", "Receiver", "Result"
            }
        ));
        jScrollPane1.setViewportView(tblFosterFamBackgroundReq);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Manage Foster Family(s)");

        tblFosterFamMedicalReq.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        tblFosterFamMedicalReq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Organization", "Receiver", "Result"
            }
        ));
        jScrollPane4.setViewportView(tblFosterFamMedicalReq);

        tblFosterFamHomeInsReq.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        tblFosterFamHomeInsReq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Organization", "Receiver", "Result"
            }
        ));
        jScrollPane5.setViewportView(tblFosterFamHomeInsReq);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Background Verfication Requests");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Home Inspection Requests");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Medical Appointment Requests");

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Refresh All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("<<Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnInitiateFosterProcess.setBackground(new java.awt.Color(0, 0, 0));
        btnInitiateFosterProcess.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnInitiateFosterProcess.setForeground(new java.awt.Color(255, 255, 255));
        btnInitiateFosterProcess.setText("Initiate Foster Process");
        btnInitiateFosterProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInitiateFosterProcessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1734, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBack)
                                .addGap(50, 50, 50)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btnAddNewFamily, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(btnReqHouseInspection, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addComponent(btnReqMedicalAppt)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                    .addComponent(btnInitiateFosterProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap())))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNewFamily, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReqHouseInspection, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReqMedicalAppt, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInitiateFosterProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnBack))
                .addGap(36, 36, 36))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddNewFamily, btnReqHouseInspection, btnReqMedicalAppt});

    }// </editor-fold>//GEN-END:initComponents

    public void populateBackgroundTable(){
        DefaultTableModel Backgroundmodel = (DefaultTableModel) tblFosterFamBackgroundReq.getModel();
        DefaultTableModel HomeInsmodel = (DefaultTableModel) tblFosterFamHomeInsReq.getModel();
        DefaultTableModel Medicalmodel = (DefaultTableModel) tblFosterFamMedicalReq.getModel();
        
        Backgroundmodel.setRowCount(0);
        HomeInsmodel.setRowCount(0);
        Medicalmodel.setRowCount(0);
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()){
            if(request.getOrgName()!=null){
                if(request.getOrgName().equals("Police Organization")){
                    Object[] row = new Object[4];
                    row[0] = request;
                    row[1] = request.getOrgName();
                    row[2] = request.getReceiver();
                    String result = ((BackgroundVerificationWorkRequest) request).getBackgroundCheck();
                    row[3] = result == null ? "--" : result;

                    Backgroundmodel.addRow(row);
                }
                else if(request.getOrgName().equals("HomeInspector Organization")){
                    Object[] row = new Object[4];
                    row[0] = request;
                    row[1] = request.getOrgName();
                    row[2] = request.getReceiver();
                    String result = ((BackgroundVerificationWorkRequest) request).getBackgroundCheck();
                    row[3] = result == null ? "--" : result;

                    HomeInsmodel.addRow(row);
                }
                else if(request.getOrgName().equals("Doctor Organization")){
                    Object[] row = new Object[4];
                    row[0] = request;
                    row[1] = request.getOrgName();
                    row[2] = request.getReceiver();
                    String result = ((BackgroundVerificationWorkRequest) request).getBackgroundCheck();
                    row[3] = result == null ? "--" : result;

                    Medicalmodel.addRow(row);
                }
            }
            
        }
    }
    
    private void btnReqHouseInspectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReqHouseInspectionActionPerformed
        // TODO add your handling code here:
        int row = tblFosterFamBackgroundReq.getSelectedRow();
        if(row!=-1){
            BackgroundVerificationWorkRequest backgroundReq = (BackgroundVerificationWorkRequest)tblFosterFamBackgroundReq.getValueAt(row, 0);
            System.out.println("backgroundReq.getBackgroundCheck()"+backgroundReq.getBackgroundCheck());
            if(backgroundReq.getBackgroundCheck() == null ){
                JOptionPane.showMessageDialog(null, "Before Proceeding with House Inspection Background verification is Mandatory");
            }
            else if(backgroundReq.getBackgroundCheck().equals("Rejected") ){
                JOptionPane.showMessageDialog(null, "Cannot Proceed with House Inspection, as Background verification is Rejected");
            }
            else{
                Organization org = null;
                for(Network net : business.getNetworkList()){
                    if(net.equals(network)){
                    for(Enterprise enterprise : net.getEnterpriseDirectory().getEnterpriseList()){
                        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()){
                            if (organization instanceof FosterHomeInspectorOrganization){
                                org = organization;
                                break;
                            }
                        }
                    }
                    }
                }
                backgroundReq.setOrgName(org.toString());
                backgroundReq.setBackgroundCheck("--");
                backgroundReq.setStatus("--");
                backgroundReq.setReceiver(null);
                if (org!=null){
                    System.out.println(org);
                    org.getWorkQueue().getWorkRequestList().add(backgroundReq);
//                    userAccount.getWorkQueue().getWorkRequestList().add(backgroundReq);
                }
               JOptionPane.showMessageDialog(null, "Successfully Requested Home Inspection! Please click Refresh All"); 
            }
            
            
        }
        
        else{
            JOptionPane.showMessageDialog(null, "Please select a row first");
        }
    }//GEN-LAST:event_btnReqHouseInspectionActionPerformed

    private void btnReqMedicalApptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReqMedicalApptActionPerformed
        // TODO add your handling code here:
        
        int row = tblFosterFamBackgroundReq.getSelectedRow();
        int row1 = tblFosterFamHomeInsReq.getSelectedRow();      
        if(row!=-1){
            BackgroundVerificationWorkRequest backgroundReq = (BackgroundVerificationWorkRequest)tblFosterFamBackgroundReq.getValueAt(row, 0);
            if(backgroundReq.getBackgroundCheck() == null){
                JOptionPane.showMessageDialog(null, "Before Proceeding with Medical Appointment Background verification is Mandatory");
            }
            
        }
        
        else{
            if(row1!=-1){
                BackgroundVerificationWorkRequest backgroundReq = (BackgroundVerificationWorkRequest)tblFosterFamHomeInsReq.getValueAt(row1, 0);
                System.out.println("backgroundReq.getBackgroundCheck()"+backgroundReq.getBackgroundCheck());
                if(backgroundReq.getBackgroundCheck() == null || backgroundReq.getBackgroundCheck().equals("--")){
                    JOptionPane.showMessageDialog(null, "Before Proceeding with Medical Appointment Home Inspection is Mandatory");
                    return;
                }
                else if(backgroundReq.getBackgroundCheck().equals("Rejected")){
                    JOptionPane.showMessageDialog(null, "House Inspection is Rejected, Cannot proceed with medical appointment");
                    return;
                }
                else{
                    this.request = backgroundReq;
                    System.out.println(request.getPersonalInfoMap());       
                    FosterFamilyMedHistJPanel fosterFamilyMedHistJPanel = new FosterFamilyMedHistJPanel(userProcessContainer, userAccount,  organization,  enterprise, business,request,network);
                    userProcessContainer.add("FosterFamilyMedHistJPanel", fosterFamilyMedHistJPanel);
                    CardLayout layout = (CardLayout) userProcessContainer.getLayout();
                    layout.next(userProcessContainer);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please select a request first");
            }
            
        }
    }//GEN-LAST:event_btnReqMedicalApptActionPerformed

    private void btnAddNewFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewFamilyActionPerformed
        // TODO add your handling code here:
        FosterFamilyWorkAreaJPanel fosterFamilyWorkAreaJPanel = new FosterFamilyWorkAreaJPanel(userProcessContainer, userAccount,  organization,  enterprise, network, business);
        userProcessContainer.add("FosterFamilyWorkAreaJPanel", fosterFamilyWorkAreaJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnAddNewFamilyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        populateBackgroundTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnInitiateFosterProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInitiateFosterProcessActionPerformed
        // TODO add your handling code here:
        FosterJPanel fosterJPanel = new FosterJPanel(userProcessContainer, userAccount,  organization,  enterprise, business,request,network);
        userProcessContainer.add("FosterJPanel", fosterJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnInitiateFosterProcessActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewFamily;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnInitiateFosterProcess;
    private javax.swing.JButton btnReqHouseInspection;
    private javax.swing.JButton btnReqMedicalAppt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblFosterFamBackgroundReq;
    private javax.swing.JTable tblFosterFamHomeInsReq;
    private javax.swing.JTable tblFosterFamMedicalReq;
    // End of variables declaration//GEN-END:variables
}
