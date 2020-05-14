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
import Business.UserAccount.UserAccount;
import Business.WorkQueue.BackgroundVerificationWorkRequest;
import Business.WorkQueue.ChildVerificationRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import static Business.StringConstants.ImmutableStringConstants.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Shubham
 */
public class FosterJPanel extends javax.swing.JPanel {

    /**
     * Creates new form FosterJPanel
     */
    private JPanel userProcessContainer;
    private FosterCaseworkerOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem business;
    private BackgroundVerificationWorkRequest request;
    private Network network;
    
    public FosterJPanel(JPanel userProcessContainer, UserAccount account, FosterCaseworkerOrganization organization, Enterprise enterprise, EcoSystem business, BackgroundVerificationWorkRequest backgroundReq, Network network) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.business = business;
        this.request = backgroundReq;
        this.network = network;
        populateBackgroundTable();
        tableFormatter();
    }
    
        private void tableFormatter(){
        
        //basic formatting
        jScrollPane4.getViewport().setBackground(new java.awt.Color(255,255,255));
        jScrollPane5.getViewport().setBackground(new java.awt.Color(255,255,255));
        
        //custom renderes, static class below
        
        DefaultTableCellRenderer cellRenderer = new TableCellColor();
        tblApprovedChildrenRecords.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblApprovedChildrenRecords.setDefaultRenderer(Object.class, cellRenderer);
        tblApprovedParentRecords.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblApprovedParentRecords.setDefaultRenderer(Object.class, cellRenderer);
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

    public void populateBackgroundTable(){
        DefaultTableModel HomeInsmodel = (DefaultTableModel) tblApprovedParentRecords.getModel();
        DefaultTableModel Medicalmodel = (DefaultTableModel) tblApprovedChildrenRecords.getModel();
        
        HomeInsmodel.setRowCount(0);
        Medicalmodel.setRowCount(0);
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()){
            if(request.getOrgName()!=null){
                
                if(request.getOrgName().equals("Doctor Organization")){
                    Object[] row = new Object[4];
                    row[0] = request;
                    row[1] = ((BackgroundVerificationWorkRequest) request).getPersonalInfoMap().get(PARENT1_FIRST_NAME);
                    row[2] = ((BackgroundVerificationWorkRequest) request).getPersonalInfoMap().get(PARENT2_FIRST_NAME);
                    ChildVerificationRequest result = ((BackgroundVerificationWorkRequest) request).getAdoptedChildRecord();
                    row[3] = result == null ? "Not Closed" : "Closed";

                    HomeInsmodel.addRow(row);
                }
                else if(request.getOrgName().equals("Child")){
                    Object[] row = new Object[3];
                    row[0] = request;
                    row[1] = ((ChildVerificationRequest)request).getCHILD_FIRST_NAME()+" "+((ChildVerificationRequest)request).getCHILD_LAST_NAME();
                    BackgroundVerificationWorkRequest result = ((ChildVerificationRequest)request).getBackgroundVerificationWorkRequest();
                    row[2] = result == null ? "Not Adopted" : "Adopted";
                    Medicalmodel.addRow(row);
                }
            }
            
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tblApprovedChildrenRecords = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblApprovedParentRecords = new javax.swing.JTable();
        btnAddNewFamily1 = new javax.swing.JButton();
        btnAddNewFamily2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblApprovedChildrenRecords.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        tblApprovedChildrenRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Name", "Adopt Status"
            }
        ));
        jScrollPane4.setViewportView(tblApprovedChildrenRecords);

        tblApprovedParentRecords.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        tblApprovedParentRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Parent 1", "Parent 2", "Case"
            }
        ));
        jScrollPane5.setViewportView(tblApprovedParentRecords);

        btnAddNewFamily1.setBackground(new java.awt.Color(0, 0, 0));
        btnAddNewFamily1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnAddNewFamily1.setForeground(new java.awt.Color(255, 255, 255));
        btnAddNewFamily1.setText("View Child Info");
        btnAddNewFamily1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewFamily1ActionPerformed(evt);
            }
        });

        btnAddNewFamily2.setBackground(new java.awt.Color(0, 0, 0));
        btnAddNewFamily2.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnAddNewFamily2.setForeground(new java.awt.Color(255, 255, 255));
        btnAddNewFamily2.setText("Adopt Child");
        btnAddNewFamily2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewFamily2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Refresh All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("<<Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Foster Family(s)");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Foster Child");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Manage Foster Process");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddNewFamily1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddNewFamily2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddNewFamily1, btnAddNewFamily2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addComponent(btnAddNewFamily1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddNewFamily2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddNewFamily1, btnAddNewFamily2, jButton1, jButton2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNewFamily1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewFamily1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblApprovedChildrenRecords.getSelectedRow();

        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a row");
            return;
        }

        ChildVerificationRequest request = (ChildVerificationRequest)tblApprovedChildrenRecords.getValueAt(selectedRow, 0);

        FosterChildViewDataJPanel fosterChildViewDataJPanel = new FosterChildViewDataJPanel(userProcessContainer, request);
        userProcessContainer.add("FosterChildViewDataJPanel", fosterChildViewDataJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnAddNewFamily1ActionPerformed

    private void btnAddNewFamily2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewFamily2ActionPerformed
        // TODO add your handling code here:
        int selectedParent = tblApprovedParentRecords.getSelectedRow();
        int selectedChild = tblApprovedChildrenRecords.getSelectedRow();

        if (selectedParent < 0 || selectedChild < 0){
            JOptionPane.showMessageDialog(null, "Please select a Parent Record and Child Record");
            return;
        }
        
        
        ChildVerificationRequest childRequest = (ChildVerificationRequest)tblApprovedChildrenRecords.getValueAt(selectedChild, 0);
        BackgroundVerificationWorkRequest parentRequest = (BackgroundVerificationWorkRequest)tblApprovedParentRecords.getValueAt(selectedParent, 0);
        
        if(parentRequest.getAdoptedChildRecord()!=null){
            JOptionPane.showMessageDialog(null, "This parent status is already closed");
            return;
        }
        else if(childRequest.getBackgroundVerificationWorkRequest()!=null){
            JOptionPane.showMessageDialog(null, "This child is already being adopted");
            return;
        }
        else{
        parentRequest.setAdoptedChildRecord(childRequest);
        childRequest.setBackgroundVerificationWorkRequest(parentRequest);
        JOptionPane.showMessageDialog(null, "Child successfully adopted! Please refresh to see child record");
        }
//        userAccount.getWorkQueue().getWorkRequestList().add(parentRequest);
//        userAccount.getWorkQueue().getWorkRequestList().add(childRequest);
        
    }//GEN-LAST:event_btnAddNewFamily2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        populateBackgroundTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewFamily1;
    private javax.swing.JButton btnAddNewFamily2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblApprovedChildrenRecords;
    private javax.swing.JTable tblApprovedParentRecords;
    // End of variables declaration//GEN-END:variables
}
