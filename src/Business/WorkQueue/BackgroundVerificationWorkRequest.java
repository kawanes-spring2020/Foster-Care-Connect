/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.HashMap;
import java.util.Map;
import static Business.StringConstants.ImmutableStringConstants.*;

/**
 *
 * @author divya
 * 
 */



public class BackgroundVerificationWorkRequest extends WorkRequest{
    private String backgroundCheck;
    private Map<String, String> personalInfoMap;
    private Map<String, String> contactInfoMap;
    private Map<String, String> residentialHistoryMap;
    private Map<String, String> employmentHistoryMap;
    private Map<String, String> referenceMap;
    private Map<String, String> parent1InfoMap;
    private Map<String, String> parent2InfoMap;
    private Map<String, String> parent1GeneralTestMap;
    private Map<String, String> parent2GeneralTestMap;
    private Map<String, String> medicalConsentMap;
    private ChildVerificationRequest adoptedChildRecord;

    public ChildVerificationRequest getAdoptedChildRecord() {
        return adoptedChildRecord;
    }

    public void setAdoptedChildRecord(ChildVerificationRequest adoptedChildRecord) {
        this.adoptedChildRecord = adoptedChildRecord;
    }
    
    
    public BackgroundVerificationWorkRequest() {
        personalInfoMap = new HashMap<>();
        contactInfoMap = new HashMap<>();
        residentialHistoryMap = new HashMap<>();
        employmentHistoryMap = new HashMap<>();
        referenceMap = new HashMap<>();
        parent1InfoMap = new HashMap<>();
        parent2InfoMap = new HashMap<>();
        parent2GeneralTestMap = new HashMap<>();
        parent1GeneralTestMap = new HashMap<>();
        medicalConsentMap = new HashMap<>();
    }

    public Map<String, String> getParent1GeneralTestMap() {
        return parent1GeneralTestMap;
    }

    public void setParent1GeneralTestMap(Map<String, String> parent1GeneralTestMap) {
        this.parent1GeneralTestMap = parent1GeneralTestMap;
    }

    public Map<String, String> getParent2GeneralTestMap() {
        return parent2GeneralTestMap;
    }

    public void setParent2GeneralTestMap(Map<String, String> parent2GeneralTestMap) {
        this.parent2GeneralTestMap = parent2GeneralTestMap;
    }

    public Map<String, String> getMedicalConsentMap() {
        return medicalConsentMap;
    }

    public void setMedicalConsentMap(Map<String, String> medicalConsentMap) {
        this.medicalConsentMap = medicalConsentMap;
    }
    
    public Map<String, String> getParent1InfoMap() {
        return parent1InfoMap;
    }

    public void setParent1InfoMap(Map<String, String> parent1InfoMap) {
        this.parent1InfoMap = parent1InfoMap;
    }

    public Map<String, String> getParent2InfoMap() {
        return parent2InfoMap;
    }

    public void setParent2InfoMap(Map<String, String> parent2InfoMap) {
        this.parent2InfoMap = parent2InfoMap;
    }

    public Map<String, String> getResidentialHistoryMap() {
        return residentialHistoryMap;
    }

    public void setResidentialHistoryMap(Map<String, String> residentialHistoryMap) {
        this.residentialHistoryMap = residentialHistoryMap;
    }

    public Map<String, String> getEmploymentHistoryMap() {
        return employmentHistoryMap;
    }

    public void setEmploymentHistoryMap(Map<String, String> employmentHistoryMap) {
        this.employmentHistoryMap = employmentHistoryMap;
    }

    public Map<String, String> getReferenceMap() {
        return referenceMap;
    }

    public void setReferenceMap(Map<String, String> referenceMap) {
        this.referenceMap = referenceMap;
    }

    public Map<String, String> getContactInfoMap() {
        return contactInfoMap;
    }

    public void setContactInfoMap(Map<String, String> contactInfoMap) {
        this.contactInfoMap = contactInfoMap;
    }
    
    public Map<String, String> getPersonalInfoMap() {
        return personalInfoMap;
    }

    public void setPersonalInfoMap(Map<String, String> personalInfoMap) {
        this.personalInfoMap = personalInfoMap;
    }

    public String getBackgroundCheck() {
        return backgroundCheck;
    }

    public void setBackgroundCheck(String backgroundCheck) {
        this.backgroundCheck = backgroundCheck;
    }
    
/** GETTER SETTER BEGINS **/

    //Set PersonalInfo
    public String getPARENT1_FIRST_NAME() {
        return personalInfoMap.get(PARENT1_FIRST_NAME);
    }

    public String getPARENT1_LAST_NAME() {
        return personalInfoMap.get(PARENT1_LAST_NAME);
    }

    public String getPARENT1_GENDER() {
        return personalInfoMap.get(PARENT1_GENDER);
    }

    public String getPARENT1_CITIZENSHIP() {
        return personalInfoMap.get(PARENT1_CITIZENSHIP);
    }

    public String getPARENT1_BIRTH() {
        return personalInfoMap.get(PARENT1_BIRTH);
    }

    public String getPARENT2_FIRST_NAME() {
        return personalInfoMap.get(PARENT2_FIRST_NAME);
    }

    public String getPARENT2_LAST_NAME() {
        return personalInfoMap.get(PARENT2_LAST_NAME);
    }

    public String getPARENT2_GENDER() {
        return personalInfoMap.get(PARENT2_GENDER);
    }

    public String getPARENT2_CITIZENSHIP() {
        return personalInfoMap.get(PARENT2_CITIZENSHIP);
    }

    public String getPARENT2_BIRTH() {
        return personalInfoMap.get(PARENT2_BIRTH);
    }

    public String getPARENT_EMAIL() {
        return contactInfoMap.get(PARENT_EMAIL);
    }

    public String getPARENT_PHONE() {
        return contactInfoMap.get(PARENT_PHONE);
    }

    public String getPARENT_ADDRESS1() {
        return contactInfoMap.get(PARENT_ADDRESS1);
    }

    public String getPARENT_ADDRESS2() {
        return contactInfoMap.get(PARENT_ADDRESS2);
    }

    public String getPARENT_CITY() {
        return contactInfoMap.get(PARENT_CITY);
    }

    public String getPARENT_STATE() {
        return contactInfoMap.get(PARENT_STATE);
    }

    public String getPARENT_ZIPCODE() {
        return contactInfoMap.get(PARENT_ZIPCODE);
    }

    public String getPARENT_COUNTRY() {
        return contactInfoMap.get(PARENT_COUNTRY);
    }
    
    public void setPARENT1_FIRST_NAME(String value){
        personalInfoMap.put(PARENT1_FIRST_NAME, value);
    }
    
    public void setPARENT1_LAST_NAME(String value){
        personalInfoMap.put(PARENT1_LAST_NAME, value);
    }
    
   public void setPARENT2_FIRST_NAME(String value){
        personalInfoMap.put(PARENT2_FIRST_NAME, value);
    }
    
    public void setPARENT2_LAST_NAME(String value){
        personalInfoMap.put(PARENT2_LAST_NAME, value);
    }
    
    public void setPARENT1_GENDER(String value) {
        personalInfoMap.put(PARENT1_GENDER, value);
    }

    public void setPARENT1_CITIZENSHIP(String value) {
        personalInfoMap.put(PARENT1_CITIZENSHIP, value);
    }

    public void setPARENT1_BIRTH(String value) {
        personalInfoMap.put(PARENT1_BIRTH, value);
    }

    public void setPARENT2_GENDER(String value) {
        personalInfoMap.put(PARENT2_GENDER, value);
    }

    public void setPARENT2_CITIZENSHIP(String value) {
        personalInfoMap.put(PARENT2_CITIZENSHIP, value);
    }

    public void setPARENT2_BIRTH(String value) {
        personalInfoMap.put(PARENT2_BIRTH, value);
    }
    
    public void getPARENT_EMAIL(String value) {
        contactInfoMap.put(PARENT_EMAIL, value);
    }

    public void setPARENT_PHONE(String value) {
        contactInfoMap.put(PARENT_PHONE, value);
    }

    public void setPARENT_ADDRESS1(String value) {
        contactInfoMap.put(PARENT_ADDRESS1, value);
    }

    public void setPARENT_ADDRESS2(String value) {
        contactInfoMap.put(PARENT_ADDRESS2, value);
    }

    public void setPARENT_CITY(String value) {
        contactInfoMap.put(PARENT_CITY, value);
    }

    public void setPARENT_STATE(String value) {
        contactInfoMap.put(PARENT_STATE, value);
    }

    public void setPARENT_ZIPCODE(String value) {
        contactInfoMap.put(PARENT_ZIPCODE, value);
    }

    public void setPARENT_COUNTRY(String value) {
        contactInfoMap.put(PARENT_COUNTRY, value);
    }
    
    public String getPARENT_OLD_ADDRESS1() {
        return residentialHistoryMap.get(PARENT_OLD_ADDRESS1);
    }

    public void setPARENT_OLD_ADDRESS1(String value) {
        residentialHistoryMap.put(PARENT_OLD_ADDRESS1, value);
    }

    public String getPARENT_OLD_ADDRESS2() {
        return residentialHistoryMap.get(PARENT_OLD_ADDRESS2);
    }

    public void setPARENT_OLD_ADDRESS2(String value) {
        residentialHistoryMap.put(PARENT_OLD_ADDRESS2 , value);
    }

    public static String getPARENT_OLD_CITY() {
        return PARENT_OLD_CITY;
    }

    public void setPARENT_OLD_CITY(String value) {
        residentialHistoryMap.put(PARENT_OLD_CITY, value);
    }

    public String getPARENT_OLD_STATE() {
        return residentialHistoryMap.get(PARENT_OLD_STATE);
    }

    public void setPARENT_OLD_STATE(String value) {
        residentialHistoryMap.put(PARENT_OLD_STATE, value);
    }

    public String getPARENT_OLD_ZIPCODE() {
        return residentialHistoryMap.get(PARENT_OLD_ZIPCODE);
    }

    public void setPARENT_OLD_ZIPCODE(String value) {
        residentialHistoryMap.put(PARENT_OLD_ZIPCODE, value);
    }

    public String getPARENT_OLD_COUNTRY() {
        return residentialHistoryMap.get(PARENT_OLD_COUNTRY);
    }

    public void setPARENT_OLD_COUNTRY(String value) {
        residentialHistoryMap.put(PARENT_OLD_COUNTRY, value);
    }

    public String getADDRESS_MOVED_ON() {
        return residentialHistoryMap.get(ADDRESS_MOVED_ON);
    }

    public void setADDRESS_MOVED_ON(String value) {
        residentialHistoryMap.put(ADDRESS_MOVED_ON, value);
    }
    
    public String getPARENT_CURRENT_EMPLOYER() {
        return employmentHistoryMap.get(PARENT_CURRENT_EMPLOYER);
    }

    public void setPARENT_CURRENT_EMPLOYER(String value) {
        employmentHistoryMap.put(PARENT_CURRENT_EMPLOYER, value);
    }

    public String getPARENT_JOB_TITLE() {
        return employmentHistoryMap.get(PARENT_JOB_TITLE);
    }

    public void setPARENT_JOB_TITLE(String value) {
        employmentHistoryMap.put(PARENT_JOB_TITLE, value);
    }

    public String getDATE_OF_CURRENT_JOB() {
        return employmentHistoryMap.get(DATE_OF_CURRENT_JOB);
    }

    public void setDATE_OF_CURRENT_JOB(String value) {
        employmentHistoryMap.put(DATE_OF_CURRENT_JOB, value);
    }

    public String getPARENT_PREVIOUS_EMPLOYER() {
        return employmentHistoryMap.get(PARENT_PREVIOUS_EMPLOYER);
    }

    public void setPARENT_PREVIOUS_EMPLOYER(String value) {
        employmentHistoryMap.put(PARENT_PREVIOUS_EMPLOYER, value);
    }

    public String getPARENT_PREVIOUS_JOB_TITLE() {
       return employmentHistoryMap.get(PARENT_PREVIOUS_JOB_TITLE);
    }

    public void setPARENT_PREVIOUS_JOB_TITLE(String value) {
       employmentHistoryMap.put(PARENT_PREVIOUS_JOB_TITLE, value);
    }

    public String getDATE_OF_PREVIOUS_JOB() {
        return employmentHistoryMap.get(DATE_OF_PREVIOUS_JOB);
    }

    public void setDATE_OF_PREVIOUS_JOB(String value) {
        employmentHistoryMap.put(DATE_OF_PREVIOUS_JOB, value);
    }
    
    
    public String getREFERENCE_NAME() {
        return referenceMap.get(REFERENCE_NAME);
    }

    public void setREFERENCE_NAME(String value) {
        referenceMap.put(REFERENCE_NAME, value);
    }

    public String getREFERENCE_RELATION() {
        return referenceMap.get(REFERENCE_RELATION);
    }

    public void setREFERENCE_RELATION(String value) {
        referenceMap.put(REFERENCE_RELATION, value);
    }

    public String getREFERENCE_PHONE() {
        return referenceMap.get(REFERENCE_PHONE);
    }

    public void setREFERENCE_PHONE(String value) {
        referenceMap.put(REFERENCE_PHONE, value);
    }

    public String getREFERENCE_ADDRESS() {
        return referenceMap.get(REFERENCE_ADDRESS);
    }

    public void setREFERENCE_ADDRESS(String value) {
        referenceMap.put(REFERENCE_ADDRESS, value);
    }

    public String getREFERENCE_CITY() {
       return referenceMap.get(REFERENCE_CITY);
    }

    public void setREFERENCE_CITY(String value) {
        referenceMap.put(REFERENCE_CITY, value);
    }

    public String getREFERENCE_STATE() {
        return referenceMap.get(REFERENCE_STATE);
    }

    public void setREFERENCE_STATE(String value) {
        referenceMap.put(REFERENCE_STATE, value);
    }

    public String getREFERENCE_ZIPCODE() {
        return referenceMap.get(REFERENCE_ZIPCODE);
    }

    public void setREFERENCE_ZIPCODE(String value) {
        referenceMap.put(REFERENCE_ZIPCODE, value);
    }

    public String getREFERENCE_COUNTRY() {
        return referenceMap.get(REFERENCE_COUNTRY);
    }

    public void setREFERENCE_COUNTRY(String value) {
        referenceMap.put(REFERENCE_COUNTRY, value);
    }
    
    //get set medical history
    public String getPARENT1_CHRONIC() {
       return parent1InfoMap.get(PARENT1_CHRONIC);
    }
    
    public String getPARENT1_MENTAL() {
       return parent1InfoMap.get(PARENT1_MENTAL);
    }
    
    public String getPARENT1_ALCHOHOL() {
       return parent1InfoMap.get(PARENT1_ALCOHOL);
    }
    
    public String getPARENT1_REASON() {
       return parent1InfoMap.get(PARENT1_REASON);
    }
    
    public String getPARENT1_CANCER() {
       return parent1InfoMap.get(PARENT1_CANCER);
    }
    
    public String getPARENT1_DIABETES() {
       return parent1InfoMap.get(PARENT1_DIABETES);
    }
    
    public String getPARENT1_HEARTDISEASE() {
       return parent1InfoMap.get(PARENT1_HEART_DISEASE);
    }
    
    public String getPARENT1_ARTHRITIS() {
       return parent1InfoMap.get(PARENT1_ARTHRITIS);
    }
    
    public String getPARENT1_ASTHAMA() {
       return parent1InfoMap.get(PARENT1_ASTHAMA);
    }
    
    public String getPARENT1_HYPERTENSION() {
       return parent1InfoMap.get(PARENT1_HYPERTENSION);
    }
    
    public String getPARENT1_ULSERS() {
       return parent1InfoMap.get(PARENT1_ULSERS);
    }
    
    public String getPARENT1_TUBERCULOSIS() {
       return parent1InfoMap.get(PARENT1_TUBERCULOSIS);
    }
    public String getPARENT1_HYPOTENSION() {
       return parent1InfoMap.get(PARENT1_HYPOTENSION);
    }
    
    public void setPARENT1_CHRONIC(String value) {
       parent1InfoMap.put(PARENT1_CHRONIC, value);
    }
    
    public void setPARENT1_MENTAL(String value) {
       parent1InfoMap.put(PARENT1_MENTAL, value);
    }
    
    public void setPARENT1_ALCHOHOL(String value) {
       parent1InfoMap.put(PARENT1_ALCOHOL, value);
    }
    
    public void setPARENT1_REASON(String value) {
       parent1InfoMap.put(PARENT1_REASON, value);
    }
    
    public void setPARENT1_CANCER(String value) {
       parent1InfoMap.put(PARENT1_CANCER, value);
    }
    
    public void setPARENT1_DIABETES(String value) {
       parent1InfoMap.put(PARENT1_DIABETES, value);
    }
    
    public void setPARENT1_HEARTDISEASE(String value) {
       parent1InfoMap.put(PARENT1_HEART_DISEASE, value);
    }
    
    public void setPARENT1_ARTHRITIS(String value) {
       parent1InfoMap.put(PARENT1_ARTHRITIS, value);
    }
    
    public void setPARENT1_ASTHAMA(String value) {
       parent1InfoMap.put(PARENT1_ASTHAMA, value);
    }
    
    public void setPARENT1_HYPERTENSION(String value) {
       parent1InfoMap.put(PARENT1_HYPERTENSION, value);
    }
    
    public void setPARENT1_ULSERS(String value) {
       parent1InfoMap.put(PARENT1_ULSERS, value);
    }
    
    public void setPARENT1_TUBERCULOSIS(String value) {
       parent1InfoMap.put(PARENT1_TUBERCULOSIS, value);
    }
    
    public void setPARENT1_HYPOTENSION(String value) {
       parent1InfoMap.put(PARENT1_HYPOTENSION, value);
    }
    
    //for parent 2 med hist
    
    public String getPARENT2_CHRONIC() {
       return parent2InfoMap.get(PARENT2_CHRONIC);
    }
    
    public String getPARENT2_MENTAL() {
       return parent2InfoMap.get(PARENT2_MENTAL);
    }
    
    public String getPARENT2_ALCHOHOL() {
       return parent2InfoMap.get(PARENT2_ALCOHOL);
    }
    
    public String getPARENT2_REASON() {
       return parent2InfoMap.get(PARENT2_REASON);
    }
    
    public String getPARENT2_CANCER() {
       return parent2InfoMap.get(PARENT2_CANCER);
    }
    
    public String getPARENT2_DIABETES() {
       return parent2InfoMap.get(PARENT2_DIABETES);
    }
    
    public String getPARENT2_HEARTDISEASE() {
       return parent2InfoMap.get(PARENT2_HEART_DISEASE);
    }
    
    public String getPARENT2_ARTHRITIS() {
       return parent2InfoMap.get(PARENT2_ARTHRITIS);
    }
    
    public String getPARENT2_ASTHAMA() {
       return parent2InfoMap.get(PARENT2_ASTHAMA);
    }
    
    public String getPARENT2_HYPERTENSION() {
       return parent2InfoMap.get(PARENT2_HYPERTENSION);
    }
    
    public String getPARENT2_ULSERS() {
       return parent2InfoMap.get(PARENT2_ULSERS);
    }
    
    public String getPARENT2_TUBERCULOSIS() {
       return parent2InfoMap.get(PARENT2_TUBERCULOSIS);
    }
    public String getPARENT2_HYPOTENSION() {
       return parent2InfoMap.get(PARENT2_HYPOTENSION);
    }
    
    public void setPARENT2_CHRONIC(String value) {
       parent2InfoMap.put(PARENT2_CHRONIC, value);
    }
    
    public void setPARENT2_MENTAL(String value) {
       parent2InfoMap.put(PARENT2_MENTAL, value);
    }
    
    public void setPARENT2_ALCHOHOL(String value) {
       parent2InfoMap.put(PARENT2_ALCOHOL, value);
    }
    
    public void setPARENT2_REASON(String value) {
       parent2InfoMap.put(PARENT2_REASON, value);
    }
    
    public void setPARENT2_CANCER(String value) {
       parent2InfoMap.put(PARENT2_CANCER, value);
    }
    
    public void setPARENT2_DIABETES(String value) {
       parent2InfoMap.put(PARENT2_DIABETES, value);
    }
    
    public void setPARENT2_HEARTDISEASE(String value) {
       parent2InfoMap.put(PARENT2_HEART_DISEASE, value);
    }
    
    public void setPARENT2_ARTHRITIS(String value) {
       parent2InfoMap.put(PARENT2_ARTHRITIS, value);
    }
    
    public void setPARENT2_ASTHAMA(String value) {
       parent2InfoMap.put(PARENT2_ASTHAMA, value);
    }
    
    public void setPARENT2_HYPERTENSION(String value) {
       parent2InfoMap.put(PARENT2_HYPERTENSION, value);
    }
    
    public void setPARENT2_ULSERS(String value) {
       parent2InfoMap.put(PARENT2_ULSERS, value);
    }
    
    public void setPARENT2_TUBERCULOSIS(String value) {
       parent2InfoMap.put(PARENT2_TUBERCULOSIS, value);
    }
    
    public void setPARENT2_HYPOTENSION(String value) {
       parent2InfoMap.put(PARENT2_HYPOTENSION, value);
    }
    
    //medical result
    
    //parent 1
    
    public String getPARENT1_PULSE(){
        return parent1GeneralTestMap.get(PARENT1_PULSE);
    }
    
    public String getPARENT1_BP(){
        return parent1GeneralTestMap.get(PARENT1_BP);
    }
    
    public String getPARENT1_APPEARANCE(){
        return parent1GeneralTestMap.get(PARENT1_APPEARANCE);
    }
    
    public String getPARENT1_HEART(){
        return parent1GeneralTestMap.get(PARENT1_HEART);
    }
    
    public String getPARENT1_LUNGS(){
        return parent1GeneralTestMap.get(PARENT1_LUNGS);
    }
    
    public String getPARENT1_SKIN(){
        return parent1GeneralTestMap.get(PARENT1_SKIN);
    }
    
    public String getPARENT1_ABDOMEN(){
        return parent1GeneralTestMap.get(PARENT1_ABDOMEN);
    }
    
    public String getPARENT1_NEUROLOGICAL(){
        return parent1GeneralTestMap.get(PARENT1_NEUROLOGICAL);
    }
    
    public String getPARENT1_COMMENTS(){
        return parent1GeneralTestMap.get(PARENT1_COMMENTS);
    }
    
    public void setPARENT1_PULSE(String value) {
       parent1GeneralTestMap.put(PARENT1_PULSE, value);
    }
    
    public void setPARENT1_BP(String value) {
       parent1GeneralTestMap.put(PARENT1_BP, value);
    }
    
    public void setPARENT1_APPEARANCE(String value) {
       parent1GeneralTestMap.put(PARENT1_APPEARANCE, value);
    }
    
    public void setPARENT1_HEART(String value) {
       parent1GeneralTestMap.put(PARENT1_HEART, value);
    }
    public void setPARENT1_LUNGS(String value) {
       parent1GeneralTestMap.put(PARENT1_LUNGS, value);
    }
    
    public void setPARENT1_SKIN(String value) {
       parent1GeneralTestMap.put(PARENT1_SKIN, value);
    }
    public void setPARENT1_ABDOMEN(String value) {
       parent1GeneralTestMap.put(PARENT1_ABDOMEN, value);
    }
    public void setPARENT1_NEUROLOGICAL(String value) {
       parent1GeneralTestMap.put(PARENT1_NEUROLOGICAL, value);
    }
    
    public void setPARENT1_COMMENTS(String value) {
       parent1GeneralTestMap.put(PARENT1_COMMENTS, value);
    }
    //parent 2
    
    public String getPARENT2_PULSE(){
        return parent2GeneralTestMap.get(PARENT2_PULSE);
    }
    
    public String getPARENT2_BP(){
        return parent2GeneralTestMap.get(PARENT2_BP);
    }
    
    public String getPARENT2_APPEARANCE(){
        return parent1GeneralTestMap.get(PARENT2_APPEARANCE);
    }
    
    public String getPARENT2_HEART(){
        return parent2GeneralTestMap.get(PARENT2_HEART);
    }
    
    public String getPARENT2_LUNGS(){
        return parent2GeneralTestMap.get(PARENT2_LUNGS);
    }
    
    public String getPARENT2_SKIN(){
        return parent2GeneralTestMap.get(PARENT2_SKIN);
    }
    
    public String getPARENT2_ABDOMEN(){
        return parent2GeneralTestMap.get(PARENT2_ABDOMEN);
    }
    
    public String getPARENT2_NEUROLOGICAL(){
        return parent2GeneralTestMap.get(PARENT2_NEUROLOGICAL);
    }
    
    public String getPARENT2_COMMENTS(){
        return parent2GeneralTestMap.get(PARENT2_COMMENTS);
    }
    
    public void setPARENT2_PULSE(String value) {
       parent2GeneralTestMap.put(PARENT2_PULSE, value);
    }
    
    public void setPARENT2_BP(String value) {
       parent2GeneralTestMap.put(PARENT2_BP, value);
    }
    
    public void setPARENT2_APPEARANCE(String value) {
       parent2GeneralTestMap.put(PARENT2_APPEARANCE, value);
    }
    
    public void setPARENT2_HEART(String value) {
       parent2GeneralTestMap.put(PARENT2_HEART, value);
    }
    public void setPARENT2_LUNGS(String value) {
       parent2GeneralTestMap.put(PARENT2_LUNGS, value);
    }
    
    public void setPARENT2_SKIN(String value) {
       parent2GeneralTestMap.put(PARENT2_SKIN, value);
    }
    public void setPARENT2_ABDOMEN(String value) {
       parent2GeneralTestMap.put(PARENT2_ABDOMEN, value);
    }
    public void setPARENT2_NEUROLOGICAL(String value) {
       parent2GeneralTestMap.put(PARENT2_NEUROLOGICAL, value);
    }
    
    public void setPARENT2_COMMENTS(String value) {
       parent2GeneralTestMap.put(PARENT2_COMMENTS, value);
    }
}
