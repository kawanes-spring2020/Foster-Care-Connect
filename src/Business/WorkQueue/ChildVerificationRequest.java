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



public class ChildVerificationRequest extends WorkRequest{
    private String backgroundCheck;
    private Map<String, String> personalInfoMap;
    private BackgroundVerificationWorkRequest backgroundVerificationWorkRequest;

    public BackgroundVerificationWorkRequest getBackgroundVerificationWorkRequest() {
        return backgroundVerificationWorkRequest;
    }

    public void setBackgroundVerificationWorkRequest(BackgroundVerificationWorkRequest backgroundVerificationWorkRequest) {
        this.backgroundVerificationWorkRequest = backgroundVerificationWorkRequest;
    }
    
    
    
    public ChildVerificationRequest() {
        personalInfoMap = new HashMap<>();
        
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
    public String getCHILD_FIRST_NAME() {
        return personalInfoMap.get(CHILD_FIRST_NAME);
    }

    public String getCHILD_LAST_NAME() {
        return personalInfoMap.get(CHILD_LAST_NAME);
    }

    public String getCHILD_GENDER() {
        return personalInfoMap.get(CHILD_GENDER);
    }

    public String getCHILD_CITIZENSHIP() {
        return personalInfoMap.get(CHILD_CITIZENSHIP);
    }

    public String getCHILD_BIRTH() {
        return personalInfoMap.get(CHILD_BIRTH);
    }
    
    
    public String getCHILD_AGE() {
        return personalInfoMap.get(CHILD_AGE);
    }
    
    
    public String getCHILD_IMAGE() {
        return personalInfoMap.get(CHILD_IMAGE);
    }
    public String getCHILD_AGE_TYPE() {
        return personalInfoMap.get(CHILD_AGE_TYPE);
    }

   
}
