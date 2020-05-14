/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.FosterHomeInspectorRole;

import Business.AppointmentFrame.AppointmentInspectionDetails;
import Business.Enterprise.Enterprise;
import Business.Organization.FosterHomeInspectorOrganization;
import Business.StringConstants.ImmutableStringConstants;
import static Business.StringConstants.ImmutableStringConstants.*;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.BackgroundVerificationWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.RadioButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

/**
 *
 * @author divya
 */
public class HomeInspectionFormJPanel extends javax.swing.JPanel {

    /**
     * Creates new form HomeInspectionFormJPanel
     */
    private BackgroundVerificationWorkRequest backgroundVerWorkReq;
    private JPanel userProcessContainer;
    private UserAccount account;
    private FosterHomeInspectorOrganization organization;
    private Enterprise enterprise;
    
    private Map<String, List> fireSafetyChecks;
    private Map<String, List> generalSafetyChecks;
    private AppointmentInspectionDetails homeInsDetails;
    
    public HomeInspectionFormJPanel(JPanel userProcessContainer, BackgroundVerificationWorkRequest backgroundVerWorkReq,
            UserAccount account, FosterHomeInspectorOrganization organization, Enterprise enterprise) {
        initComponents();
        //lblRequestID.setText(backgroundVerWorkReq.getRequestId());
        this.backgroundVerWorkReq = backgroundVerWorkReq;
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;
        
        homeInsDetails = new AppointmentInspectionDetails();
        populateFamilyForm();
        if(!(backgroundVerWorkReq).getBackgroundCheck().equals("--")){
            disableFormFields();
        }
        
        else{
        
        }
    }

    private void disableFormFields(){
        Map<String, List> generalDetailMap = backgroundVerWorkReq.getAppointment().
                                             getAppointmentInspectionDetails().getHome_generalMap();
        List<String> genRadioList = generalDetailMap.get(GENERAL_RADIO);
        for(Component c : jLayeredPane2.getComponents()){
            if(c instanceof JRadioButton){
                if(genRadioList.contains(c.getName()))
                        ((JRadioButton) c).setSelected(true);
                c.setEnabled(false);
            }
            else{
               txtGenRemark.setText(generalDetailMap.get(GENERAL_REMARK).get(0).toString());
               txtGenRemark.setEnabled(false);
            }
        }
        
        Map<String, List> safetyDetailMap = backgroundVerWorkReq.getAppointment().
                                             getAppointmentInspectionDetails().getHome_safetyMap();
        List<String> safeRadioList = safetyDetailMap.get(FIRE_SAFETY_RADIO);
        for(Component c : jLayeredPane3.getComponents()){
            if(c instanceof JRadioButton){
                if(safeRadioList.contains(c.getName()))
                        ((JRadioButton) c).setSelected(true);
                c.setEnabled(false);
            }
            else{
               textSafeRemark.setText(safetyDetailMap.get(FIRE_SAFETY_REMARK).get(0).toString());
               textSafeRemark.setEnabled(false);
            }
        }
        
        Map<String, String> refDetailMap = backgroundVerWorkReq.getAppointment().
                                             getAppointmentInspectionDetails().getHome_referenceMap();
        chkRefVerified.setEnabled(false);
        chkRefVerified.setSelected(true);
        txtRefRemark.setText(refDetailMap.get(REFERENCE_CHECK_REMARK));
        txtRefRemark.setEnabled(false);
        
        Map<String, String> finalDetailMap = backgroundVerWorkReq.getAppointment().
                                             getAppointmentInspectionDetails().getHome_finalMap();
        
        chkFinalInspectionVerify.setEnabled(false);
        chkFinalInspectionVerify.setSelected(true);
        txtFinalRemark.setText(finalDetailMap.get(FINAL_REMARK));
        txtFinalRemark.setEnabled(false);
        dtInspection.setEditable(false);
        dtInspection.setDate(new Date(finalDetailMap.get(INSPECTION_DATE)));
    }
    
    private void populateFamilyForm(){
        Map<String, String> PersonalInfoMap = ((BackgroundVerificationWorkRequest)backgroundVerWorkReq).getPersonalInfoMap();
        Map<String, String> ContactInfoMap = ((BackgroundVerificationWorkRequest)backgroundVerWorkReq).getContactInfoMap();
        Map<String, String> ReferenceInfoMap = ((BackgroundVerificationWorkRequest)backgroundVerWorkReq).getReferenceMap();

        BackgroundVerificationWorkRequest breq = (BackgroundVerificationWorkRequest)backgroundVerWorkReq;
        //btnGrp1.getSelection().getSelectedObjects();
        
        String requestId = lblRequestID.getText();
        requestId = requestId + "  " +breq.getRequestId();
        lblRequestID.setText(requestId);
        
        
        txtFName.setText(PersonalInfoMap.get(PARENT1_FIRST_NAME));
        txtLName.setText(PersonalInfoMap.get(PARENT1_LAST_NAME));
        
        txtEmail.setText(ContactInfoMap.get(PARENT_EMAIL));
        txtPhone.setText(ContactInfoMap.get(PARENT_PHONE));
        txtAddress1.setText(ContactInfoMap.get(PARENT_ADDRESS1));
        txtAddress2.setText(ContactInfoMap.get(PARENT_ADDRESS2));
        txtCity.setText(ContactInfoMap.get(PARENT_CITY));
        txtState.setText(ContactInfoMap.get(PARENT_STATE));
        txtCountry.setText(ContactInfoMap.get(PARENT_COUNTRY));
        txtZipCode.setText(ContactInfoMap.get(PARENT_ZIPCODE));
        
       
        txtRefFName.setText(ReferenceInfoMap.get(REFERENCE_NAME));
        txtRefRelation.setText(ReferenceInfoMap.get(REFERENCE_RELATION));
        txtRefPhone.setText(ReferenceInfoMap.get(REFERENCE_PHONE));
        txtRefAddress.setText(ReferenceInfoMap.get(REFERENCE_ADDRESS));
        txtRefCity.setText(ReferenceInfoMap.get(REFERENCE_CITY));
        txtRefState.setText(ReferenceInfoMap.get(REFERENCE_STATE));
        txtRefZipCode.setText(ReferenceInfoMap.get(REFERENCE_ZIPCODE));

        /*txtFName.setText(PersonalInfoMap.get("p1fname"));
        txtLName.setText(PersonalInfoMap.get("p1lname"));
        
        txtEmail.setText(ContactInfoMap.get("email"));
        txtPhone.setText(ContactInfoMap.get("phone"));
        txtAddress1.setText(ContactInfoMap.get("address1"));
        txtAddress2.setText(ContactInfoMap.get("address2"));
        txtCity.setText(ContactInfoMap.get("city"));
        txtState.setText(ContactInfoMap.get("state"));
        txtCountry.setText(ContactInfoMap.get("country"));
        txtZipCode.setText(ContactInfoMap.get("zipcode"));

        txtRefFName.setText(ReferenceInfoMap.get("nameReference"));
        txtRefRelation.setText(ReferenceInfoMap.get("relationReference"));
        txtRefPhone.setText(ReferenceInfoMap.get("telephoneReference"));
        txtRefAddress.setText(ReferenceInfoMap.get("addressReference"));
        txtRefCity.setText(ReferenceInfoMap.get("cityReference"));
        txtRefState.setText(ReferenceInfoMap.get("stateReference"));
        txtRefZipCode.setText(ReferenceInfoMap.get("zipcodeReference"));*/
        
    }
    
    private boolean verifyInfoFilled(){
        boolean flag = false;
        return flag;
    }
    
    private void saveHomeInspectionDecision(boolean result){
        
        boolean flag = verifyInfoFilled();
        
        if(chkFinalInspectionVerify.isSelected()==false){
            JOptionPane.showMessageDialog(null, "Please Check The Inspection Verification Acknowledgement");
            return;
        }
        
        else if(dtInspection.getDate()==null){
            JOptionPane.showMessageDialog(null, "Please select a date of inspection");
            return;
        }
        
        else{
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date inspectionDate = dtInspection.getDate();
                if(inspectionDate.before(backgroundVerWorkReq.getAppointment().getAppointmentDate()))
                    JOptionPane.showMessageDialog(null, "Inspection date cannot be older than Appointment date!");
                
                else{
                        Map<String, String> finalDetailsMap = homeInsDetails.getHome_finalMap();
                        finalDetailsMap.put(FINAL_CHECK_VERIFIED, chkFinalInspectionVerify.isSelected()+"");
                        finalDetailsMap.put(FINAL_REMARK, txtFinalRemark.getText());
                        finalDetailsMap.put(INSPECTION_DATE, dtInspection.getDate().toString());
                        homeInsDetails.setHome_finalMap(finalDetailsMap);
                        backgroundVerWorkReq.getAppointment().setAppointmentInspectionDetails(homeInsDetails);
                        JOptionPane.showMessageDialog(null, "Home Inspection Completed Successfully");
                        if(result)
                            backgroundVerWorkReq.setBackgroundCheck("Approved");
                        else
                            backgroundVerWorkReq.setBackgroundCheck("Rejected");
                        backgroundVerWorkReq.setStatus("Completed");
                        
                        //next
                        FosterHomeInspectorWorkAreaJPanel fosterHomeInspectorWorkAreaJPanel = new FosterHomeInspectorWorkAreaJPanel(userProcessContainer, account,  organization,  enterprise);
                        userProcessContainer.add("FosterHomeInspectorWorkAreaJPanel", fosterHomeInspectorWorkAreaJPanel);
                        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
                        layout.next(userProcessContainer);
                }                
                }
            
                catch(Exception e){
                JOptionPane.showMessageDialog(null, "Opps! Error while parsing. Try again");
                return;
            }
            }
            

    }
    
    private void addRadioChecks2(){
        Map<String, List> fireSafetyInfoMap = homeInsDetails.getHome_safetyMap();
        String fireSafetyRemark = textSafeRemark.getText();
        List<String> fireSafetyResponseList = new ArrayList<>();
        List<String> fireSafetyRemarkList = new ArrayList<>();
        try{
           fireSafetyResponseList.add(btnGrp1.getSelection().getActionCommand());
        fireSafetyResponseList.add(buttonGroup2.getSelection().getActionCommand());
        fireSafetyResponseList.add(buttonGroup3.getSelection().getActionCommand());
        fireSafetyResponseList.add(buttonGroup4.getSelection().getActionCommand());
        fireSafetyResponseList.add(buttonGroup5.getSelection().getActionCommand());
        fireSafetyResponseList.add(buttonGroup6.getSelection().getActionCommand());
        fireSafetyInfoMap.put(FIRE_SAFETY_RADIO, fireSafetyResponseList);
        
        fireSafetyRemarkList.add(fireSafetyRemark);
        fireSafetyInfoMap.put(FIRE_SAFETY_REMARK, fireSafetyRemarkList);
        System.out.println(fireSafetyResponseList);
        
            JOptionPane.showMessageDialog(null,"Fire Safety Checks successfully saved!");
            //next
            jTabbedPane1.setSelectedIndex(3);
        }
        
        catch(NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Please select values for each field");
        }
        homeInsDetails.setHome_safetyMap(fireSafetyInfoMap);
    }

    
    private void addRadioChecks1(){
        Map<String, List> generalInfoMap = homeInsDetails.getHome_generalMap();
        List<String> generalResponseList = new ArrayList<>();
        String generalSafetyRemark = txtGenRemark.getText();
        List<String> generalSafetyRemarkList = new ArrayList<>();
        try{
           generalResponseList.add(buttonGroup7.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup8.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup9.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup10.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup11.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup12.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup13.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup14.getSelection().getActionCommand());
           generalResponseList.add(buttonGroup15.getSelection().getActionCommand());
           
           generalInfoMap.put(GENERAL_RADIO, generalResponseList);
           
           generalSafetyRemarkList.add(generalSafetyRemark);
           generalInfoMap.put(GENERAL_REMARK, generalSafetyRemarkList);
        System.out.println(generalResponseList);
        JOptionPane.showMessageDialog(null,"General Check successfully saved!");
        
        //next
            jTabbedPane1.setSelectedIndex(2);
        }
        
        catch(NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Please select values for each field");
            return;
        }
        homeInsDetails.setHome_generalMap(generalInfoMap);    
    }
    
    private void saveReferenceInformation(int tabIndex){
        if(chkRefVerified.isSelected()==false){
            JOptionPane.showMessageDialog(null, "Please check references verified acknowledgement");
            return;
        }
        else{
                Map<String, String> referenceDetailsMap = homeInsDetails.getHome_referenceMap();
        referenceDetailsMap.put(REFERENCE_CHECK_VERIFIED, chkRefVerified.isSelected()+"");
        referenceDetailsMap.put(REFERENCE_CHECK_REMARK, txtRefRemark.getText());
        homeInsDetails.setHome_referenceMap(referenceDetailsMap);
        
            JOptionPane.showMessageDialog(null, "References successfully saved!");
            jTabbedPane1.setSelectedIndex(tabIndex);
        }

    }
    
    private void saveGeneralHomeInspectionDetails(AppointmentInspectionDetails homeInsDetails){
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        btnGrp1 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        buttonGroup13 = new javax.swing.ButtonGroup();
        buttonGroup14 = new javax.swing.ButtonGroup();
        buttonGroup15 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        txtFName = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtLName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtAddress1 = new javax.swing.JTextField();
        txtAddress2 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        txtState = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JTextField();
        txtZipCode = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        lblRequestID = new javax.swing.JLabel();
        btnNext1 = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rad11 = new javax.swing.JRadioButton();
        rad12 = new javax.swing.JRadioButton();
        rad13 = new javax.swing.JRadioButton();
        rad21 = new javax.swing.JRadioButton();
        rad22 = new javax.swing.JRadioButton();
        rad23 = new javax.swing.JRadioButton();
        rad31 = new javax.swing.JRadioButton();
        rad32 = new javax.swing.JRadioButton();
        rad33 = new javax.swing.JRadioButton();
        rad41 = new javax.swing.JRadioButton();
        rad42 = new javax.swing.JRadioButton();
        rad43 = new javax.swing.JRadioButton();
        rad51 = new javax.swing.JRadioButton();
        rad52 = new javax.swing.JRadioButton();
        rad53 = new javax.swing.JRadioButton();
        rad61 = new javax.swing.JRadioButton();
        rad62 = new javax.swing.JRadioButton();
        rad63 = new javax.swing.JRadioButton();
        rad71 = new javax.swing.JRadioButton();
        rad72 = new javax.swing.JRadioButton();
        rad73 = new javax.swing.JRadioButton();
        rad81 = new javax.swing.JRadioButton();
        rad82 = new javax.swing.JRadioButton();
        rad83 = new javax.swing.JRadioButton();
        rad91 = new javax.swing.JRadioButton();
        rad92 = new javax.swing.JRadioButton();
        rad93 = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGenRemark = new javax.swing.JTextArea();
        btnNextGeneral = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel20 = new javax.swing.JLabel();
        frad11 = new javax.swing.JRadioButton();
        frad12 = new javax.swing.JRadioButton();
        frad13 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        frad21 = new javax.swing.JRadioButton();
        frad22 = new javax.swing.JRadioButton();
        frad23 = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        frad31 = new javax.swing.JRadioButton();
        frad32 = new javax.swing.JRadioButton();
        frad33 = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        frad41 = new javax.swing.JRadioButton();
        frad42 = new javax.swing.JRadioButton();
        frad43 = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        frad51 = new javax.swing.JRadioButton();
        frad52 = new javax.swing.JRadioButton();
        frad53 = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        frad61 = new javax.swing.JRadioButton();
        frad62 = new javax.swing.JRadioButton();
        frad63 = new javax.swing.JRadioButton();
        frad71 = new javax.swing.JRadioButton();
        frad72 = new javax.swing.JRadioButton();
        frad73 = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textSafeRemark = new javax.swing.JTextArea();
        btnNext5 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        txtRefFName = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtRefRelation = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtRefPhone = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtRefAddress = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtRefCity = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtRefState = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtRefZipCode = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        chkRefVerified = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRefRemark = new javax.swing.JTextArea();
        jLabel38 = new javax.swing.JLabel();
        btnNext7 = new javax.swing.JButton();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtFinalRemark = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        btnApprove = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        dtInspection = new org.jdesktop.swingx.JXDatePicker();
        jLabel41 = new javax.swing.JLabel();
        chkFinalInspectionVerify = new javax.swing.JCheckBox();

        jRadioButton7.setText("YES");

        jRadioButton8.setText("NO");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        jRadioButton9.setText("N/A");

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusLost(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtFName.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtFName.setForeground(new java.awt.Color(51, 51, 51));
        txtFName.setEnabled(false);

        jLabel42.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(102, 102, 102));
        jLabel42.setText("First Name");

        jLabel43.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setText("Last Name");

        txtLName.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtLName.setForeground(new java.awt.Color(51, 51, 51));
        txtLName.setEnabled(false);

        txtEmail.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setEnabled(false);

        jLabel44.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setText("Email ID");

        jLabel45.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setText("Phone Number");

        txtPhone.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtPhone.setForeground(new java.awt.Color(51, 51, 51));
        txtPhone.setEnabled(false);

        jLabel46.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setText("Adress Line 1");

        txtAddress1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtAddress1.setForeground(new java.awt.Color(51, 51, 51));
        txtAddress1.setEnabled(false);

        txtAddress2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtAddress2.setForeground(new java.awt.Color(51, 51, 51));
        txtAddress2.setEnabled(false);

        jLabel47.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setText("Adress Line 2");

        jLabel48.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 102, 102));
        jLabel48.setText("City");

        txtCity.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtCity.setForeground(new java.awt.Color(51, 51, 51));
        txtCity.setEnabled(false);

        txtState.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtState.setForeground(new java.awt.Color(51, 51, 51));
        txtState.setEnabled(false);

        jLabel49.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setText("State");

        jLabel50.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(102, 102, 102));
        jLabel50.setText("Country");

        txtCountry.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtCountry.setForeground(new java.awt.Color(51, 51, 51));
        txtCountry.setEnabled(false);

        txtZipCode.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtZipCode.setForeground(new java.awt.Color(51, 51, 51));
        txtZipCode.setEnabled(false);

        jLabel51.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
        jLabel51.setText("Zip Code");

        btnNext.setBackground(new java.awt.Color(0, 0, 0));
        btnNext.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText("Next >>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblRequestID.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblRequestID.setForeground(new java.awt.Color(102, 102, 102));
        lblRequestID.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblRequestID.setText("Request ID: ");

        btnNext1.setBackground(new java.awt.Color(0, 0, 0));
        btnNext1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnNext1.setForeground(new java.awt.Color(255, 255, 255));
        btnNext1.setText("<<Back");
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(txtFName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel42, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel43, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtLName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtEmail, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel44, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel45, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtPhone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel46, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtAddress1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtAddress2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel47, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel48, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtCity, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtState, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel49, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel50, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtCountry, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtZipCode, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel51, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnNext, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblRequestID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnNext1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))
                        .addGap(61, 61, 61)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtState)
                            .addComponent(txtFName)
                            .addComponent(txtLName)
                            .addComponent(txtEmail)
                            .addComponent(txtPhone)
                            .addComponent(txtAddress1)
                            .addComponent(txtAddress2)
                            .addComponent(txtCity)
                            .addComponent(txtCountry)
                            .addComponent(txtZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(btnNext1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                .addComponent(lblRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtLName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtState, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext)
                    .addComponent(btnNext1))
                .addContainerGap(464, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Basic Information", jLayeredPane1);

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Safe Neighbourhood");

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Water Clearance Obtained");

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Apartment Reasonably Neat And Clutter Free");

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Electrical Switches And Receptacles In Good Working Order");

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Heating Source In Working Order And Well Vented");

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Smoke Detectors In Working Condition");

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Sharp Edge Free Furniture");

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Fire Extinguishers Readily Available And Fully Charged");

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Firearms And Other Weapons Owned Are Unloaded And Locked");

        rad11.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup7.add(rad11);
        rad11.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad11.setText("YES");
        rad11.setName("rad11"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad11, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad11, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad11ActionPerformed(evt);
            }
        });

        rad12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup7.add(rad12);
        rad12.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad12.setText("NO");
        rad12.setName("rad12"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad12, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad12, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad12ActionPerformed(evt);
            }
        });

        rad13.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup7.add(rad13);
        rad13.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad13.setText("N/A");
        rad13.setName("rad13"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad13, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad13, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad21.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup8.add(rad21);
        rad21.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad21.setText("YES");
        rad21.setName("rad21"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad21, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad21, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad22.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup8.add(rad22);
        rad22.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad22.setText("NO");
        rad22.setName("rad22"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad22, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad22, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad22ActionPerformed(evt);
            }
        });

        rad23.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup8.add(rad23);
        rad23.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad23.setText("N/A");
        rad23.setName("rad23"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad23, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad23, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad31.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup9.add(rad31);
        rad31.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad31.setText("YES");
        rad31.setName("rad31"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad31, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad31, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad32.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup9.add(rad32);
        rad32.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad32.setText("NO");
        rad32.setName("rad32"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad32, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad32, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad32ActionPerformed(evt);
            }
        });

        rad33.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup9.add(rad33);
        rad33.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad33.setText("N/A");
        rad33.setName("rad33"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad33, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad33, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad41.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup10.add(rad41);
        rad41.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad41.setText("YES");
        rad41.setName("rad41"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad41, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad41, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad42.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup10.add(rad42);
        rad42.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad42.setText("NO");
        rad42.setName("rad42"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad42, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad42, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad42ActionPerformed(evt);
            }
        });

        rad43.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup10.add(rad43);
        rad43.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad43.setText("N/A");
        rad43.setName("rad43"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad43, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad43, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad51.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup11.add(rad51);
        rad51.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad51.setText("YES");
        rad51.setName("rad51"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad51, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad51, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad52.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup11.add(rad52);
        rad52.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad52.setText("NO");
        rad52.setName("rad52"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad52, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad52, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad52ActionPerformed(evt);
            }
        });

        rad53.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup11.add(rad53);
        rad53.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad53.setText("N/A");
        rad53.setName("rad53"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad53, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad53, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad53ActionPerformed(evt);
            }
        });

        rad61.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup12.add(rad61);
        rad61.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad61.setText("YES");
        rad61.setName("rad61"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad61, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad61, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad62.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup12.add(rad62);
        rad62.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad62.setText("NO");
        rad62.setName("rad62"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad62, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad62, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad62ActionPerformed(evt);
            }
        });

        rad63.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup12.add(rad63);
        rad63.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad63.setText("N/A");
        rad63.setName("rad63"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad63, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad63, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad71.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup13.add(rad71);
        rad71.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad71.setText("YES");
        rad71.setName("rad71"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad71, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad71, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad72.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup13.add(rad72);
        rad72.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad72.setText("NO");
        rad72.setName("rad72"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad72, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad72, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad72ActionPerformed(evt);
            }
        });

        rad73.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup13.add(rad73);
        rad73.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad73.setText("N/A");
        rad73.setName("rad73"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad73, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad73, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad81.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup14.add(rad81);
        rad81.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad81.setText("YES");
        rad81.setName("rad81"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad81, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad81, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad82.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup14.add(rad82);
        rad82.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad82.setText("NO");
        rad82.setName("rad82"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad82, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad82, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad82ActionPerformed(evt);
            }
        });

        rad83.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup14.add(rad83);
        rad83.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad83.setText("N/A");
        rad83.setName("rad83"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad83, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad83, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad83ActionPerformed(evt);
            }
        });

        rad91.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup15.add(rad91);
        rad91.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad91.setText("YES");
        rad91.setName("rad91"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad91, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad91, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad91ActionPerformed(evt);
            }
        });

        rad92.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup15.add(rad92);
        rad92.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad92.setText("NO");
        rad92.setName("rad92"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad92, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad92, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        rad92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad92ActionPerformed(evt);
            }
        });

        rad93.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup15.add(rad93);
        rad93.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        rad93.setText("N/A");
        rad93.setName("rad93"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rad93, org.jdesktop.beansbinding.ELProperty.create("${name}"), rad93, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        jLabel36.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setText("Additional Remarks");

        txtGenRemark.setColumns(20);
        txtGenRemark.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtGenRemark.setRows(5);
        jScrollPane1.setViewportView(txtGenRemark);

        btnNextGeneral.setBackground(new java.awt.Color(0, 0, 0));
        btnNextGeneral.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnNextGeneral.setForeground(new java.awt.Color(255, 255, 255));
        btnNextGeneral.setText("Next >>");
        btnNextGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextGeneralActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad41, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad42, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad43, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad51, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad52, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad53, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad61, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad62, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad63, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad71, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad72, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad73, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad81, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad82, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad83, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad91, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad92, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rad93, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel36, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnNextGeneral, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad11)
                                .addGap(33, 33, 33)
                                .addComponent(rad12)
                                .addGap(35, 35, 35)
                                .addComponent(rad13))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad21)
                                .addGap(33, 33, 33)
                                .addComponent(rad22)
                                .addGap(35, 35, 35)
                                .addComponent(rad23))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad31)
                                .addGap(33, 33, 33)
                                .addComponent(rad32)
                                .addGap(35, 35, 35)
                                .addComponent(rad33))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad41)
                                .addGap(33, 33, 33)
                                .addComponent(rad42)
                                .addGap(35, 35, 35)
                                .addComponent(rad43))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad51)
                                .addGap(33, 33, 33)
                                .addComponent(rad52)
                                .addGap(35, 35, 35)
                                .addComponent(rad53))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad61)
                                .addGap(33, 33, 33)
                                .addComponent(rad62)
                                .addGap(35, 35, 35)
                                .addComponent(rad63))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad71)
                                .addGap(33, 33, 33)
                                .addComponent(rad72)
                                .addGap(35, 35, 35)
                                .addComponent(rad73))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad81)
                                .addGap(33, 33, 33)
                                .addComponent(rad82)
                                .addGap(35, 35, 35)
                                .addComponent(rad83))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(rad91)
                                .addGap(33, 33, 33)
                                .addComponent(rad92)
                                .addGap(35, 35, 35)
                                .addComponent(rad93))))
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnNextGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad11)
                    .addComponent(rad12)
                    .addComponent(rad13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad21)
                    .addComponent(rad22)
                    .addComponent(rad23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad31)
                    .addComponent(rad32)
                    .addComponent(rad33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad41)
                    .addComponent(rad42)
                    .addComponent(rad43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad51)
                    .addComponent(rad52)
                    .addComponent(rad53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad61)
                    .addComponent(rad62)
                    .addComponent(rad63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad71)
                    .addComponent(rad72)
                    .addComponent(rad73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad81)
                    .addComponent(rad82)
                    .addComponent(rad83))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad91)
                    .addComponent(rad92)
                    .addComponent(rad93))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNextGeneral)
                .addContainerGap(373, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jLayeredPane2);

        jLayeredPane3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLayeredPane3FocusLost(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Apartment Has 2 Exits");

        btnGrp1.add(frad11);
        frad11.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad11.setText("YES");
        frad11.setName("frad11"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad11, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad11, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        btnGrp1.add(frad12);
        frad12.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad12.setText("NO");
        frad12.setName("frad12"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad12, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad12, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad12ActionPerformed(evt);
            }
        });

        btnGrp1.add(frad13);
        frad13.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad13.setText("N/A");
        frad13.setName("frad13"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad13, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad13, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad13ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Multi-storey Homes Have Escape From Upper Floor");

        buttonGroup1.add(frad21);
        frad21.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad21.setText("YES");
        frad21.setName("frad21"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad21, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad21, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad21ActionPerformed(evt);
            }
        });

        buttonGroup1.add(frad22);
        frad22.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad22.setText("NO");
        frad22.setName("frad22"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad22, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad22, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad22ActionPerformed(evt);
            }
        });

        buttonGroup1.add(frad23);
        frad23.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad23.setText("N/A");
        frad23.setName("frad23"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad23, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad23, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        jLabel22.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Flammable Materials And Poisons Inaccessible To Children");

        buttonGroup2.add(frad31);
        frad31.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad31.setText("YES");
        frad31.setName("frad31"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad31, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad31, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad31ActionPerformed(evt);
            }
        });

        buttonGroup2.add(frad32);
        frad32.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad32.setText("NO");
        frad32.setName("frad32"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad32, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad32, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad32ActionPerformed(evt);
            }
        });

        buttonGroup2.add(frad33);
        frad33.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad33.setText("N/A");
        frad33.setName("frad33"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad33, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad33, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        jLabel23.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Family Has Written Evacuation Plan For Home");

        buttonGroup3.add(frad41);
        frad41.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad41.setText("YES");
        frad41.setName("frad41"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad41, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad41, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        buttonGroup3.add(frad42);
        frad42.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad42.setText("NO");
        frad42.setName("frad42"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad42, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad42, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad42ActionPerformed(evt);
            }
        });

        buttonGroup3.add(frad43);
        frad43.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad43.setText("N/A");
        frad43.setName("frad43"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad43, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad43, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        jLabel24.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Fire Evacuation Plan Approved By Fire Marshal/Officer");

        buttonGroup4.add(frad51);
        frad51.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad51.setText("YES");
        frad51.setName("frad51"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad51, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad51, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        buttonGroup4.add(frad52);
        frad52.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad52.setText("NO");
        frad52.setName("frad52"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad52, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad52, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad52ActionPerformed(evt);
            }
        });

        buttonGroup4.add(frad53);
        frad53.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad53.setText("N/A");
        frad53.setName("frad53"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad53, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad53, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Smoke Detectors In Working Condition");

        buttonGroup5.add(frad61);
        frad61.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad61.setText("YES");
        frad61.setName("frad61"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad61, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad61, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        buttonGroup5.add(frad62);
        frad62.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad62.setText("NO");
        frad62.setName("frad62"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad62, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad62, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad62ActionPerformed(evt);
            }
        });

        buttonGroup5.add(frad63);
        frad63.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad63.setText("N/A");
        frad63.setName("frad63"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad63, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad63, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        buttonGroup6.add(frad71);
        frad71.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad71.setText("YES");
        frad71.setName("frad71"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad71, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad71, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        buttonGroup6.add(frad72);
        frad72.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad72.setText("NO");
        frad72.setName("frad72"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad72, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad72, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        frad72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frad72ActionPerformed(evt);
            }
        });

        buttonGroup6.add(frad73);
        frad73.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        frad73.setText("N/A");
        frad73.setName("frad73"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, frad73, org.jdesktop.beansbinding.ELProperty.create("${name}"), frad73, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        jLabel27.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Fire Extinguishers Readily Available And Fully Charged");

        jLabel37.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setText("Additional Remarks");

        textSafeRemark.setColumns(20);
        textSafeRemark.setRows(5);
        jScrollPane2.setViewportView(textSafeRemark);

        btnNext5.setBackground(new java.awt.Color(0, 0, 0));
        btnNext5.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnNext5.setForeground(new java.awt.Color(255, 255, 255));
        btnNext5.setText("Next >>");
        btnNext5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext5ActionPerformed(evt);
            }
        });

        jLayeredPane3.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad41, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad42, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad43, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad51, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad52, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad53, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad61, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad62, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad63, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad71, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad72, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(frad73, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel37, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(btnNext5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(frad11)
                                .addGap(33, 33, 33)
                                .addComponent(frad12)
                                .addGap(35, 35, 35)
                                .addComponent(frad13))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(frad21)
                                .addGap(33, 33, 33)
                                .addComponent(frad22)
                                .addGap(35, 35, 35)
                                .addComponent(frad23))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(frad31)
                                .addGap(33, 33, 33)
                                .addComponent(frad32)
                                .addGap(35, 35, 35)
                                .addComponent(frad33))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(frad41)
                                .addGap(33, 33, 33)
                                .addComponent(frad42)
                                .addGap(35, 35, 35)
                                .addComponent(frad43))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(frad51)
                                .addGap(33, 33, 33)
                                .addComponent(frad52)
                                .addGap(35, 35, 35)
                                .addComponent(frad53))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(frad61)
                                .addGap(33, 33, 33)
                                .addComponent(frad62)
                                .addGap(35, 35, 35)
                                .addComponent(frad63))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(frad71)
                                .addGap(33, 33, 33)
                                .addComponent(frad72)
                                .addGap(35, 35, 35)
                                .addComponent(frad73))))
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnNext5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frad11)
                    .addComponent(frad12)
                    .addComponent(frad13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frad21)
                    .addComponent(frad22)
                    .addComponent(frad23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frad31)
                    .addComponent(frad32)
                    .addComponent(frad33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frad41)
                    .addComponent(frad42)
                    .addComponent(frad43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frad51)
                    .addComponent(frad52)
                    .addComponent(frad53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frad61)
                    .addComponent(frad62)
                    .addComponent(frad63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frad71)
                    .addComponent(frad72)
                    .addComponent(frad73)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext5)
                .addContainerGap(443, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fire Safety", jLayeredPane3);

        txtRefFName.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtRefFName.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Name");

        jLabel28.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Relationship");

        txtRefRelation.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtRefRelation.setEnabled(false);

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Phone Number");

        txtRefPhone.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtRefPhone.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Address");

        txtRefAddress.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtRefAddress.setEnabled(false);

        jLabel32.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("City");

        txtRefCity.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtRefCity.setEnabled(false);

        jLabel33.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setText("State");

        txtRefState.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtRefState.setEnabled(false);

        jLabel34.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Zip Code");

        txtRefZipCode.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtRefZipCode.setEnabled(false);

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setText("The Reference Details Provided By The Family Has Been Verified And Reviewed");

        chkRefVerified.setBackground(new java.awt.Color(255, 255, 255));

        txtRefRemark.setColumns(20);
        txtRefRemark.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        txtRefRemark.setRows(5);
        jScrollPane3.setViewportView(txtRefRemark);

        jLabel38.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setText("Additional Remarks");

        btnNext7.setBackground(new java.awt.Color(0, 0, 0));
        btnNext7.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnNext7.setForeground(new java.awt.Color(255, 255, 255));
        btnNext7.setText("Next >>");
        btnNext7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext7ActionPerformed(evt);
            }
        });

        jLayeredPane4.setLayer(txtRefFName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtRefRelation, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel30, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtRefPhone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtRefAddress, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtRefCity, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtRefState, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel34, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtRefZipCode, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel35, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(chkRefVerified, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel38, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(btnNext7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(61, 61, 61)
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRefState, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRefFName, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRefRelation, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRefPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRefAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRefCity, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRefZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addComponent(chkRefVerified, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnNext7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtRefFName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtRefRelation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRefPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRefAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRefCity, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRefState, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRefZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRefVerified))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext7)
                .addContainerGap(424, Short.MAX_VALUE))
        );

        jLayeredPane4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chkRefVerified, jLabel35});

        jTabbedPane1.addTab("References", jLayeredPane4);

        txtFinalRemark.setColumns(20);
        txtFinalRemark.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        txtFinalRemark.setRows(5);
        jScrollPane4.setViewportView(txtFinalRemark);

        jLabel39.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("Final Remarks");

        btnApprove.setBackground(new java.awt.Color(0, 0, 0));
        btnApprove.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnApprove.setForeground(new java.awt.Color(255, 255, 255));
        btnApprove.setText("Approve");
        btnApprove.setEnabled(false);
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        btnReject.setBackground(new java.awt.Color(0, 0, 0));
        btnReject.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnReject.setForeground(new java.awt.Color(255, 255, 255));
        btnReject.setText("Reject");
        btnReject.setEnabled(false);
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setText("Date Of Inspection");

        dtInspection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtInspectionActionPerformed(evt);
            }
        });

        jLabel41.setBackground(new java.awt.Color(255, 255, 255));
        jLabel41.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
        jLabel41.setText("I HereBy Verify That Applicant's Neighbourhood And Residential Place Is Inspected According To The Guidelines Provided By The State");

        chkFinalInspectionVerify.setBackground(new java.awt.Color(255, 255, 255));
        chkFinalInspectionVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFinalInspectionVerifyActionPerformed(evt);
            }
        });

        jLayeredPane5.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLabel39, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnApprove, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnReject, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLabel40, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(dtInspection, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLabel41, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(chkFinalInspectionVerify, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                .addGap(0, 89, Short.MAX_VALUE)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                .addComponent(btnApprove)
                                .addGap(43, 43, 43)
                                .addComponent(btnReject, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                        .addComponent(jLabel40)
                                        .addGap(18, 18, 18)
                                        .addComponent(dtInspection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(chkFinalInspectionVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41)
                        .addGap(20, 20, 20))))
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtInspection, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFinalInspectionVerify))
                .addGap(38, 38, 38)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApprove)
                    .addComponent(btnReject))
                .addContainerGap(461, Short.MAX_VALUE))
        );

        jLayeredPane5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chkFinalInspectionVerify, jLabel41});

        jTabbedPane1.addTab("Remarks and Approval", jLayeredPane5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void frad72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad72ActionPerformed

    private void frad62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad62ActionPerformed

    private void frad52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad52ActionPerformed

    private void frad42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad42ActionPerformed

    private void frad32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad32ActionPerformed

    private void frad22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad22ActionPerformed

    private void frad12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad12ActionPerformed

    private void rad92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad92ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad92ActionPerformed

    private void rad82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad82ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad82ActionPerformed

    private void rad72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad72ActionPerformed

    private void rad62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad62ActionPerformed

    private void rad52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad52ActionPerformed

    private void rad42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad42ActionPerformed

    private void rad32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad32ActionPerformed

    private void rad22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad22ActionPerformed

    private void rad12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad12ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnNextGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextGeneralActionPerformed
        // TODO add your handling code here:
        //System.out.println(btnGrp1.getSelection().getActionCommand());
        addRadioChecks1();
        
    }//GEN-LAST:event_btnNextGeneralActionPerformed

    private void btnNext5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext5ActionPerformed
        // TODO add your handling code here:
         addRadioChecks2();
         
    }//GEN-LAST:event_btnNext5ActionPerformed

    private void btnNext7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext7ActionPerformed
        // TODO add your handling code here:
        saveReferenceInformation(4);
        //jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_btnNext7ActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
        FosterHomeInspectorWorkAreaJPanel fosterHomeInspectorWorkAreaJPanel = new FosterHomeInspectorWorkAreaJPanel(userProcessContainer, account,  organization,  enterprise);
        userProcessContainer.add("FosterHomeInspectorWorkAreaJPanel", fosterHomeInspectorWorkAreaJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        // TODO add your handling code here:
        saveHomeInspectionDecision(true);
    }//GEN-LAST:event_btnApproveActionPerformed

    private void rad11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad11ActionPerformed
        // TODO add your handling code here:
        /*evt.getActionCommand();
        buttonGroup7.setSelected(bm, true);
        for(Component c : jLayeredPane2.getComponents()){
            if(c instanceof JRadioButton){
                list.contains(c.getName())
                        ((JRadioButton) c).setSelected(true);
            }
        }
        System.out.println(buttonGroup7.getSelection().getActionCommand());*/
    }//GEN-LAST:event_rad11ActionPerformed

    private void rad53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad53ActionPerformed

    private void rad83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad83ActionPerformed

    private void rad91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad91ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad91ActionPerformed

    private void frad31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad31ActionPerformed

    private void frad13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad13ActionPerformed

    private void frad21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frad21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frad21ActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        // TODO add your handling code here:
        saveHomeInspectionDecision(false);
    }//GEN-LAST:event_btnRejectActionPerformed

    private void jTabbedPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusLost
        // TODO add your handling code here:
        //System.out.println("Focus lost");
        //addRadioChecks2();
    }//GEN-LAST:event_jTabbedPane1FocusLost

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        //addRadioChecks1();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jLayeredPane3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLayeredPane3FocusLost
        // TODO add your handling code here:
        System.out.println("Focus lost");
    }//GEN-LAST:event_jLayeredPane3FocusLost

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        System.out.println("Tab changed to: " + index+ "   " +sourceTabbedPane.getTitleAt(index));
//        switch(index){
//            case 2:
//                addRadioChecks1();
//                jTabbedPane1.setSelectedIndex(1);
//                break;
//            case 3:
//                addRadioChecks2();
//                jTabbedPane1.setSelectedIndex(2);
//                break;
//        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void chkFinalInspectionVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFinalInspectionVerifyActionPerformed
        // TODO add your handling code here:
        if(chkFinalInspectionVerify.isSelected() == true){
            btnApprove.setEnabled(true);
            btnReject.setEnabled(true);
        } else{
            btnApprove.setEnabled(false);
            btnReject.setEnabled(false);
        }
    }//GEN-LAST:event_chkFinalInspectionVerifyActionPerformed

    private void dtInspectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtInspectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dtInspectionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.ButtonGroup btnGrp1;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnNext5;
    private javax.swing.JButton btnNext7;
    private javax.swing.JButton btnNextGeneral;
    private javax.swing.JButton btnReject;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup13;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.ButtonGroup buttonGroup15;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JCheckBox chkFinalInspectionVerify;
    private javax.swing.JCheckBox chkRefVerified;
    private org.jdesktop.swingx.JXDatePicker dtInspection;
    private javax.swing.JRadioButton frad11;
    private javax.swing.JRadioButton frad12;
    private javax.swing.JRadioButton frad13;
    private javax.swing.JRadioButton frad21;
    private javax.swing.JRadioButton frad22;
    private javax.swing.JRadioButton frad23;
    private javax.swing.JRadioButton frad31;
    private javax.swing.JRadioButton frad32;
    private javax.swing.JRadioButton frad33;
    private javax.swing.JRadioButton frad41;
    private javax.swing.JRadioButton frad42;
    private javax.swing.JRadioButton frad43;
    private javax.swing.JRadioButton frad51;
    private javax.swing.JRadioButton frad52;
    private javax.swing.JRadioButton frad53;
    private javax.swing.JRadioButton frad61;
    private javax.swing.JRadioButton frad62;
    private javax.swing.JRadioButton frad63;
    private javax.swing.JRadioButton frad71;
    private javax.swing.JRadioButton frad72;
    private javax.swing.JRadioButton frad73;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblRequestID;
    private javax.swing.JRadioButton rad11;
    private javax.swing.JRadioButton rad12;
    private javax.swing.JRadioButton rad13;
    private javax.swing.JRadioButton rad21;
    private javax.swing.JRadioButton rad22;
    private javax.swing.JRadioButton rad23;
    private javax.swing.JRadioButton rad31;
    private javax.swing.JRadioButton rad32;
    private javax.swing.JRadioButton rad33;
    private javax.swing.JRadioButton rad41;
    private javax.swing.JRadioButton rad42;
    private javax.swing.JRadioButton rad43;
    private javax.swing.JRadioButton rad51;
    private javax.swing.JRadioButton rad52;
    private javax.swing.JRadioButton rad53;
    private javax.swing.JRadioButton rad61;
    private javax.swing.JRadioButton rad62;
    private javax.swing.JRadioButton rad63;
    private javax.swing.JRadioButton rad71;
    private javax.swing.JRadioButton rad72;
    private javax.swing.JRadioButton rad73;
    private javax.swing.JRadioButton rad81;
    private javax.swing.JRadioButton rad82;
    private javax.swing.JRadioButton rad83;
    private javax.swing.JRadioButton rad91;
    private javax.swing.JRadioButton rad92;
    private javax.swing.JRadioButton rad93;
    private javax.swing.JTextArea textSafeRemark;
    private javax.swing.JTextField txtAddress1;
    private javax.swing.JTextField txtAddress2;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextArea txtFinalRemark;
    private javax.swing.JTextArea txtGenRemark;
    private javax.swing.JTextField txtLName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtRefAddress;
    private javax.swing.JTextField txtRefCity;
    private javax.swing.JTextField txtRefFName;
    private javax.swing.JTextField txtRefPhone;
    private javax.swing.JTextField txtRefRelation;
    private javax.swing.JTextArea txtRefRemark;
    private javax.swing.JTextField txtRefState;
    private javax.swing.JTextField txtRefZipCode;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtZipCode;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
