/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DoctorRole;

import Business.Enterprise.Enterprise;
import Business.Organization.DoctorOrganization;
import Business.Organization.LabOrganization;
import Business.Organization.Organization;
import static Business.StringConstants.ImmutableStringConstants.*;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.BackgroundVerificationWorkRequest;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.WorkRequest;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashSet;
import java.util.Map;
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
public class MedicalVerificationProcessJPanel extends javax.swing.JPanel {

    /**
     * Creates new form MedicalVerificationProcessJPanel
     */
    private JPanel userProcessContainer;
    private BackgroundVerificationWorkRequest request;
    private UserAccount userAccount;
    private DoctorOrganization organization;
    private Enterprise enterprise;
    private int labtestCount;
    private String labtestStatus = "Pending";
    
    public MedicalVerificationProcessJPanel(JPanel userProcessContainer, BackgroundVerificationWorkRequest request,UserAccount userAccount, DoctorOrganization organization,Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.request = request;
        this.userAccount = userAccount;
        this.organization = organization;
        this.enterprise = enterprise;
        System.out.println(request.getParent1InfoMap());
        System.out.println(request.getRequestId());
        System.out.println(enterprise.getOrganizationDirectory());
        populateLabRequestTable();
        tableFormatter();
        txtRequestId.setText(request.getRequestId()+"");
        System.out.println(request.getParent1GeneralTestMap());
        if(!request.getParent1GeneralTestMap().isEmpty()){
            System.out.println("i am here"); 
            populateGeneralTest();
        }
        
    }
    
    public void populateGeneralTest(){
        if(!request.getParent1GeneralTestMap().get(PARENT1_PULSE).isEmpty()){
            txtPulse1.setText(request.getParent1GeneralTestMap().get(PARENT1_PULSE));
            txtPulse1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_BP).isEmpty()){
            txtBP1.setText(request.getParent1GeneralTestMap().get(PARENT1_BP));
            txtBP1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_APPEARANCE).isEmpty()){
            txtAppearance1.setText(request.getParent1GeneralTestMap().get(PARENT1_APPEARANCE));
            txtAppearance1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_HEART).isEmpty()){
            txtHeart1.setText(request.getParent1GeneralTestMap().get(PARENT1_HEART));
            txtHeart1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_LUNGS).isEmpty()){
            txtLungs1.setText(request.getParent1GeneralTestMap().get(PARENT1_LUNGS));
            txtLungs1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_SKIN).isEmpty()){
            txtSkin1.setText(request.getParent1GeneralTestMap().get(PARENT1_SKIN));
            txtSkin1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_ABDOMEN).isEmpty()){
            txtAbdomen1.setText(request.getParent1GeneralTestMap().get(PARENT1_ABDOMEN));
            txtAbdomen1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_NEUROLOGICAL).isEmpty()){
            txtNeurological1.setText(request.getParent1GeneralTestMap().get(PARENT1_NEUROLOGICAL));
            txtNeurological1.setEnabled(false);
        }
        if(!request.getParent1GeneralTestMap().get(PARENT1_COMMENTS).isEmpty()){
            txtComments1.setText(request.getParent1GeneralTestMap().get(PARENT1_COMMENTS));
            txtComments1.setEnabled(false);
        }
        
        //parent 2 starts here
        
        if(!request.getParent2GeneralTestMap().get(PARENT2_PULSE).isEmpty()){
            txtPulse2.setText(request.getParent2GeneralTestMap().get(PARENT2_PULSE));
            txtPulse2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_BP).isEmpty()){
            txtBP2.setText(request.getParent2GeneralTestMap().get(PARENT2_BP));
            txtBP2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_APPEARANCE).isEmpty()){
            txtAppearance2.setText(request.getParent2GeneralTestMap().get(PARENT2_APPEARANCE));
            txtAppearance2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_HEART).isEmpty()){
            txtHeart2.setText(request.getParent2GeneralTestMap().get(PARENT2_HEART));
            txtHeart2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_LUNGS).isEmpty()){
            txtLungs2.setText(request.getParent2GeneralTestMap().get(PARENT2_LUNGS));
            txtLungs2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_SKIN).isEmpty()){
            txtSkin2.setText(request.getParent2GeneralTestMap().get(PARENT2_SKIN));
            txtSkin2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_ABDOMEN).isEmpty()){
            txtAbdomen2.setText(request.getParent2GeneralTestMap().get(PARENT2_ABDOMEN));
            txtAbdomen2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_NEUROLOGICAL).isEmpty()){
            txtNeurological2.setText(request.getParent2GeneralTestMap().get(PARENT2_NEUROLOGICAL));
            txtNeurological2.setEnabled(false);
        }
        if(!request.getParent2GeneralTestMap().get(PARENT2_COMMENTS).isEmpty()){
            txtComments2.setText(request.getParent2GeneralTestMap().get(PARENT2_COMMENTS));
            txtComments2.setEnabled(false);
        }
    }
    
    public void populateLabRequestTable(){
        DefaultTableModel model = (DefaultTableModel) LabworkRequestJTable.getModel();
        
        Organization org = null;
        for (Organization organ : enterprise.getOrganizationDirectory().getOrganizationList()){
            if (organ instanceof LabOrganization){
                org = organ;
                break;
            }
        }
        
        model.setRowCount(0);
        
        System.out.println(org);
        String requestid = request.getRequestId()+"";
        
        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()){
        String bgID = ((LabTestWorkRequest) request).getBgResultID();
        
            if(requestid.equals(bgID))
            {
                Object[] row = new Object[4];
                row[0] = request;
                row[1] = request.getReceiver();
                row[2] = request.getStatus();
                String result = ((LabTestWorkRequest) request).getTestResult();
                row[3] = result == null ? "Waiting" : result;
                model.addRow(row);
            }
        }
        
        this.labtestCount = model.getRowCount();
        txtPhysicalTestCount.setText(labtestCount+"");
        txtName1.setText(request.getPARENT1_FIRST_NAME());
        txtName2.setText((request.getPARENT2_FIRST_NAME()));
        
        int colNumber = 2; 
        int counter = 0;
        for(int row = 0; row < model.getRowCount();row++) {
                String testResult = model.getValueAt(row, colNumber)+"";
                System.out.println(testResult);
                if(testResult.equals("Completed"))
                    counter = counter + 1;
        }
        if(counter == this.labtestCount && counter > 0)
        {
            this.labtestStatus = "Completed"; 
        }

        txtPhysicalTestStatus.setText(this.labtestStatus);
    }
    
    private void tableFormatter(){
        
        //basic formatting
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255,255,255));
        
        //custom renderes, static class below
        
        DefaultTableCellRenderer cellRenderer = new TableCellColor();
        LabworkRequestJTable.getTableHeader().setDefaultRenderer(new HeaderColor());
        LabworkRequestJTable.setDefaultRenderer(Object.class, cellRenderer);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        requestTestJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        LabworkRequestJTable = new javax.swing.JTable();
        refreshTestJButton = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtRequestId = new javax.swing.JTextField();
        btnNext1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        btnDetails = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPulse1 = new javax.swing.JTextField();
        txtBP1 = new javax.swing.JTextField();
        txtAppearance1 = new javax.swing.JTextField();
        txtHeart1 = new javax.swing.JTextField();
        txtLungs1 = new javax.swing.JTextField();
        txtSkin1 = new javax.swing.JTextField();
        txtAbdomen1 = new javax.swing.JTextField();
        txtNeurological1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtName1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName2 = new javax.swing.JTextField();
        txtPulse2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBP2 = new javax.swing.JTextField();
        txtAppearance2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtHeart2 = new javax.swing.JTextField();
        txtLungs2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSkin2 = new javax.swing.JTextField();
        txtAbdomen2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtNeurological2 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComments1 = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtComments2 = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        submitJButton1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        chkFinalInspectionVerify1 = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        txtPhysicalTestCount = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        txtPhysicalTestStatus = new javax.swing.JTextField();
        comboResult = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel2.setText("Request Medical Test");

        requestTestJButton.setBackground(new java.awt.Color(0, 0, 0));
        requestTestJButton.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        requestTestJButton.setForeground(new java.awt.Color(255, 255, 255));
        requestTestJButton.setText("Request Test");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });

        LabworkRequestJTable.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        LabworkRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Test ID", "Receiver", "Status", "Result"
            }
        ));
        jScrollPane1.setViewportView(LabworkRequestJTable);

        refreshTestJButton.setBackground(new java.awt.Color(0, 0, 0));
        refreshTestJButton.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        refreshTestJButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshTestJButton.setText("Refresh");
        refreshTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTestJButtonActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel27.setText("Request ID:");

        txtRequestId.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        txtRequestId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRequestId.setEnabled(false);

        btnNext1.setBackground(new java.awt.Color(0, 0, 0));
        btnNext1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnNext1.setForeground(new java.awt.Color(255, 255, 255));
        btnNext1.setText("Next>>");
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "General Blood Test", "Substance Use Test", "Urine Test", "Advance Tox Screen " }));

        btnDetails.setBackground(new java.awt.Color(0, 0, 0));
        btnDetails.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnDetails.setText("Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnNext1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(requestTestJButton))))
                            .addComponent(jLabel27)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(refreshTestJButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDetails))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addComponent(txtRequestId, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtRequestId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshTestJButton)
                    .addComponent(btnDetails))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(requestTestJButton)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Physical Tests", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("BP");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Appreance");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Heart");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Lungs");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Skin");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Abdomen");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Neurological");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Pulse");

        txtBP1.setText(" ");
        txtBP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBP1ActionPerformed(evt);
            }
        });

        txtAppearance1.setText(" ");

        txtHeart1.setText(" ");

        txtLungs1.setText(" ");

        txtSkin1.setText(" ");

        txtAbdomen1.setText(" ");

        txtNeurological1.setText(" ");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Name");

        txtName1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtName1.setText(" ");
        txtName1.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Name");

        txtName2.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtName2.setText(" ");
        txtName2.setEnabled(false);

        txtPulse2.setText(" ");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Pulse");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("BP");

        txtBP2.setText(" ");

        txtAppearance2.setText(" ");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Appreance");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Heart");

        txtHeart2.setText(" ");

        txtLungs2.setText(" ");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Lungs");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Skin");

        txtSkin2.setText(" ");

        txtAbdomen2.setText(" ");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Abdomen");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Neurological");

        txtNeurological2.setText(" ");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel23.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Comments");

        txtComments1.setColumns(20);
        txtComments1.setRows(5);
        jScrollPane2.setViewportView(txtComments1);

        jLabel24.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Comments");

        txtComments2.setColumns(20);
        txtComments2.setRows(5);
        jScrollPane3.setViewportView(txtComments2);

        btnSave.setBackground(new java.awt.Color(0, 0, 0));
        btnSave.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(0, 0, 0));
        btnNext.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText("Next>>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdate.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNeurological1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtAbdomen1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtBP1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtPulse1)
                        .addComponent(txtAppearance1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtHeart1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtLungs1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtSkin1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtName1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNeurological2)
                    .addComponent(txtAbdomen2)
                    .addComponent(txtBP2)
                    .addComponent(txtPulse2)
                    .addComponent(txtAppearance2)
                    .addComponent(txtHeart2)
                    .addComponent(txtLungs2)
                    .addComponent(txtSkin2)
                    .addComponent(txtName2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave)
                            .addComponent(btnUpdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel10, jLabel11, jLabel13, jLabel23, jLabel4, jLabel5, jLabel7, jLabel8, jLabel9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel12, jLabel14, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel24, jLabel3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jScrollPane3, txtAbdomen1, txtAbdomen2, txtAppearance1, txtAppearance2, txtBP1, txtBP2, txtHeart1, txtHeart2, txtLungs1, txtLungs2, txtName1, txtName2, txtNeurological1, txtNeurological2, txtPulse1, txtPulse2, txtSkin1, txtSkin2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSave, btnUpdate});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtPulse1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtBP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAppearance1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtHeart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtLungs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtSkin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtAbdomen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNeurological1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtPulse2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtBP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtAppearance2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtHeart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtLungs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtSkin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtAbdomen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtNeurological2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnSave)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(122, 122, 122))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel10, jLabel11, jLabel13, jLabel4, jLabel5, jLabel7, jLabel8, jLabel9, txtAbdomen1, txtAppearance1, txtBP1, txtHeart1, txtLungs1, txtName1, txtNeurological1, txtPulse1, txtSkin1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel12, jLabel14, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel3, txtAbdomen2, txtAppearance2, txtBP2, txtHeart2, txtLungs2, txtName2, txtNeurological2, txtPulse2, txtSkin2});

        jTabbedPane1.addTab("General tests", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel15.setText("Medical Verification Result");

        submitJButton1.setBackground(new java.awt.Color(0, 102, 102));
        submitJButton1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        submitJButton1.setForeground(new java.awt.Color(255, 255, 255));
        submitJButton1.setText("Submit Result");
        submitJButton1.setEnabled(false);
        submitJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButton1ActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Physical Tests");

        jLabel42.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("I Hereby Verify That Examination Was Performed According To The Guidelines Provided By The State");

        chkFinalInspectionVerify1.setBackground(new java.awt.Color(255, 255, 255));
        chkFinalInspectionVerify1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFinalInspectionVerify1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Additional Remarks");

        txtPhysicalTestCount.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtPhysicalTestCount.setText(" ");
        txtPhysicalTestCount.setEnabled(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Physical Test Status");

        txtPhysicalTestStatus.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtPhysicalTestStatus.setText(" ");
        txtPhysicalTestStatus.setEnabled(false);

        comboResult.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        comboResult.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Approved", "Rejected" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitJButton1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkFinalInspectionVerify1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(66, 66, 66))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhysicalTestCount, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhysicalTestStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(comboResult, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel16, jLabel25, jLabel26});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {comboResult, jLabel15});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtPhysicalTestCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtPhysicalTestStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(comboResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkFinalInspectionVerify1)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitJButton1)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboResult, jLabel15});

        jTabbedPane1.addTab("Consent", jPanel3);

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Medical Verification Form");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed

        
        String message = jComboBox1.getSelectedItem()+"";

        LabTestWorkRequest labRequest = new LabTestWorkRequest();
        labRequest.setMessage(message);
        labRequest.setBgResultID(request.getRequestId()+"");
        labRequest.setSender(userAccount);
        labRequest.setReceiver(null);
        labRequest.setStatus("Sent");

        Organization org = null;
        for (Organization organizations : enterprise.getOrganizationDirectory().getOrganizationList()){
            if (organizations instanceof LabOrganization){
                org = organizations;
                break;
            }
        }
        if (org!=null){
            org.getWorkQueue().getWorkRequestList().add(labRequest);
            //userAccount.getWorkQueue().getWorkRequestList().add(labRequest);
            JOptionPane.showMessageDialog(null, "Test Successfully Requested! Refresh to view status!");
        } else{
            JOptionPane.showMessageDialog(null, "Kindly Request the admin to create Lab Organization!");
        }
        
        System.out.println(org);
        System.out.println(labRequest.getBgResultID() + "   " + labRequest.getRequestId());
        System.out.println(request.getRequestId());
       
    }//GEN-LAST:event_requestTestJButtonActionPerformed

    private void refreshTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTestJButtonActionPerformed

        populateLabRequestTable();

    }//GEN-LAST:event_refreshTestJButtonActionPerformed

    private void submitJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButton1ActionPerformed
        // TODO add your handling code here:
        String status=txtPhysicalTestStatus.getText();
        
        if(status.equals("Pending")){
                JOptionPane.showMessageDialog(null, "Cannot proceed as Test result is still pending");
                submitJButton1.setEnabled(false);
                return;
         }
        
        
        if(chkFinalInspectionVerify1.isSelected() == false){
            JOptionPane.showMessageDialog(null, "Please Agree to consent first!");
            submitJButton1.setEnabled(false);
            return;
        }
        else{
            request.setBackgroundCheck(comboResult.getSelectedItem()+"");
            request.setStatus("Completed");
            System.out.println(request.getBackgroundCheck());
            updateResult();
            
            // next
            JOptionPane.showMessageDialog(null, "Doctor Verification completed Successfully!!");
        
            DoctorWorkAreaJPanel doctorWorkAreaJPanel = new DoctorWorkAreaJPanel(userProcessContainer, userAccount,  organization,  enterprise);
            userProcessContainer.add("FosterCaseWorkerManageFamilyJPanel", doctorWorkAreaJPanel);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
        
    }//GEN-LAST:event_submitJButton1ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(txtPulse1.getText().equals("")||txtBP1.getText().equals("")||txtAppearance1.getText().equals("")
                ||txtHeart1.getText().equals("")||txtLungs1.getText().equals("")
                ||txtSkin1.getText().equals("")||txtAbdomen1.getText().equals("")
                ||txtNeurological1.getText().equals("")||txtComments1.getText().equals("")||
                txtPulse2.getText().equals("")||txtBP2.getText().equals("")||txtAppearance2.getText().equals("")
                ||txtHeart2.getText().equals("")||txtLungs2.getText().equals("")
                ||txtSkin2.getText().equals("")||txtAbdomen2.getText().equals("")
                ||txtNeurological2.getText().equals("")||txtComments2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "General Exam Results cannot be blank!");
            return;
        }
        else{
            
            btnUpdate.setEnabled(true);
            btnSave.setEnabled(false);
            
            txtPulse1.setEnabled(false);
            txtBP1.setEnabled(false);
            txtAppearance1.setEnabled(false);  
            txtSkin1.setEnabled(false);
            txtAbdomen1.setEnabled(false);
            txtNeurological1.setEnabled(false);
            txtHeart1.setEnabled(false);
            txtLungs1.setEnabled(false);
            txtComments1.setEnabled(false);
            
            txtPulse2.setEnabled(false);
            txtBP2.setEnabled(false);
            txtAppearance2.setEnabled(false);  
            txtSkin2.setEnabled(false);
            txtAbdomen2.setEnabled(false);
            txtHeart2.setEnabled(false);
            txtLungs2.setEnabled(false);
            txtNeurological2.setEnabled(false);
            txtComments2.setEnabled(false);
            
            JOptionPane.showMessageDialog(null, "General Exam Results Saved Sucessfully!");
            btnSave.setEnabled(false);
            saveGeneralTest();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if(txtPulse1.getText().equals("")||txtBP1.getText().equals("")||txtAppearance1.getText().equals("")
                ||txtHeart1.getText().equals("")||txtLungs1.getText().equals("")
                ||txtSkin1.getText().equals("")||txtAbdomen1.getText().equals("")
                ||txtNeurological1.getText().equals("")||txtComments1.getText().equals("")||
                txtPulse2.getText().equals("")||txtBP2.getText().equals("")||txtAppearance2.getText().equals("")
                ||txtHeart2.getText().equals("")||txtLungs2.getText().equals("")
                ||txtSkin2.getText().equals("")||txtAbdomen2.getText().equals("")
                ||txtNeurological2.getText().equals("")||txtComments2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "General Exam Results cannot be blank!");
            return;
        }
        else {
            jTabbedPane1.setSelectedIndex(2);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
        if(this.labtestCount == 0){
            JOptionPane.showMessageDialog(null,"Please Request for atleast one of the mandatory tests");
            return;
        }else{
            jTabbedPane1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void chkFinalInspectionVerify1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFinalInspectionVerify1ActionPerformed
        // TODO add your handling code here:
        submitJButton1.setEnabled(true);
    }//GEN-LAST:event_chkFinalInspectionVerify1ActionPerformed

    private void txtBP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBP1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        btnSave.setEnabled(true);
        
        txtPulse1.setEnabled(true);
        txtBP1.setEnabled(true);
        txtAppearance1.setEnabled(true);  
        txtSkin1.setEnabled(true);
        txtAbdomen1.setEnabled(true);
        txtNeurological1.setEnabled(true);
        txtHeart1.setEnabled(true);
        txtLungs1.setEnabled(true);
        txtComments1.setEnabled(true);
            
        
        txtPulse2.setEnabled(true);
        txtBP2.setEnabled(true);
        txtAppearance2.setEnabled(true);  
        txtSkin2.setEnabled(true);
        txtAbdomen2.setEnabled(true);
        txtNeurological2.setEnabled(true);
        txtHeart2.setEnabled(true);
        txtLungs2.setEnabled(true);
        txtComments2.setEnabled(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = LabworkRequestJTable.getSelectedRow();
        
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please Select a row First!!");
            return;
        } else if(!(LabworkRequestJTable.getValueAt(selectedRow, 2)+"").equals("Completed")){
            JOptionPane.showMessageDialog(null,"Lab Test is still in progress!!");
            return;
        }else{
        
            LabTestWorkRequest labRequest = (LabTestWorkRequest)LabworkRequestJTable.getValueAt(selectedRow, 0);
            LabResultsViewJPanel labResultsViewJPanel = new LabResultsViewJPanel(userProcessContainer,labRequest);
            userProcessContainer.add("LabResultsViewJPanel", labResultsViewJPanel);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
        
    }//GEN-LAST:event_btnDetailsActionPerformed
    
    private void saveGeneralTest(){
        
        Map<String, String> parent1GeneralTestMap = request.getParent1GeneralTestMap();
        
        
        parent1GeneralTestMap.put(PARENT1_PULSE, txtPulse1.getText());
        parent1GeneralTestMap.put(PARENT1_BP, txtBP1.getText());
        parent1GeneralTestMap.put(PARENT1_APPEARANCE, txtAppearance1.getText());
        parent1GeneralTestMap.put(PARENT1_HEART, txtHeart1.getText());
        parent1GeneralTestMap.put(PARENT1_LUNGS, txtLungs1.getText());
        parent1GeneralTestMap.put(PARENT1_SKIN, txtSkin1.getText());
        parent1GeneralTestMap.put(PARENT1_ABDOMEN, txtAbdomen1.getText());
        parent1GeneralTestMap.put(PARENT1_NEUROLOGICAL, txtNeurological1.getText());
        parent1GeneralTestMap.put(PARENT1_COMMENTS, txtComments1.getText());
        request.setParent1GeneralTestMap(parent1GeneralTestMap);
        
        System.out.println(parent1GeneralTestMap.get(PARENT1_BP));
        
        Map<String, String> parent2GeneralTestMap = request.getParent2GeneralTestMap();
        parent2GeneralTestMap.put(PARENT2_PULSE, txtPulse2.getText());
        parent2GeneralTestMap.put(PARENT2_BP, txtBP2.getText());
        parent2GeneralTestMap.put(PARENT2_APPEARANCE, txtAppearance2.getText());
        parent2GeneralTestMap.put(PARENT2_HEART, txtHeart2.getText());
        parent2GeneralTestMap.put(PARENT2_LUNGS, txtLungs2.getText());
        parent2GeneralTestMap.put(PARENT2_SKIN, txtSkin2.getText());
        parent2GeneralTestMap.put(PARENT2_ABDOMEN, txtAbdomen2.getText());
        parent2GeneralTestMap.put(PARENT2_NEUROLOGICAL, txtNeurological2.getText());
        parent2GeneralTestMap.put(PARENT2_COMMENTS, txtComments2.getText());
        request.setParent2GeneralTestMap(parent2GeneralTestMap);
        
        System.out.println(request.getParent1GeneralTestMap());
        
    }
    private void updateResult(){
        
        Map<String, String> medicalConsentMap = request.getMedicalConsentMap();
        medicalConsentMap.put(PHYSICAL_TEST_COUNT, txtPhysicalTestCount.getText());
        medicalConsentMap.put(PHYSICAL_TEST_STATUS, txtPhysicalTestStatus.getText());
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable LabworkRequestJTable;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkFinalInspectionVerify1;
    private javax.swing.JComboBox comboResult;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton refreshTestJButton;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JButton submitJButton1;
    private javax.swing.JTextField txtAbdomen1;
    private javax.swing.JTextField txtAbdomen2;
    private javax.swing.JTextField txtAppearance1;
    private javax.swing.JTextField txtAppearance2;
    private javax.swing.JTextField txtBP1;
    private javax.swing.JTextField txtBP2;
    private javax.swing.JTextArea txtComments1;
    private javax.swing.JTextArea txtComments2;
    private javax.swing.JTextField txtHeart1;
    private javax.swing.JTextField txtHeart2;
    private javax.swing.JTextField txtLungs1;
    private javax.swing.JTextField txtLungs2;
    private javax.swing.JTextField txtName1;
    private javax.swing.JTextField txtName2;
    private javax.swing.JTextField txtNeurological1;
    private javax.swing.JTextField txtNeurological2;
    private javax.swing.JTextField txtPhysicalTestCount;
    private javax.swing.JTextField txtPhysicalTestStatus;
    private javax.swing.JTextField txtPulse1;
    private javax.swing.JTextField txtPulse2;
    private javax.swing.JTextField txtRequestId;
    private javax.swing.JTextField txtSkin1;
    private javax.swing.JTextField txtSkin2;
    // End of variables declaration//GEN-END:variables
}
