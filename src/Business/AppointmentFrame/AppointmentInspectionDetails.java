/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.AppointmentFrame;

import static Business.StringConstants.ImmutableStringConstants.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author divya
 */
public class AppointmentInspectionDetails {
    
    Map<String, List> home_generalMap;
    Map<String, List> home_safetyMap;
    Map<String, String> home_referenceMap;
    Map<String, String> home_finalMap;

    public AppointmentInspectionDetails() {
        this.home_generalMap = new HashMap<>();
        this.home_safetyMap = new HashMap<>();
        this.home_referenceMap = new HashMap<>();
        this.home_finalMap = new HashMap<>();
    }

    public Map<String, List> getHome_generalMap() {
        return home_generalMap;
    }

    public void setHome_generalMap(Map<String, List> home_generalMap) {
        this.home_generalMap = home_generalMap;
    }

    public Map<String, List> getHome_safetyMap() {
        return home_safetyMap;
    }

    public void setHome_safetyMap(Map<String, List> home_safetyMap) {
        this.home_safetyMap = home_safetyMap;
    }

    public Map<String, String> getHome_referenceMap() {
        return home_referenceMap;
    }

    public void setHome_referenceMap(Map<String, String> home_referenceMap) {
        this.home_referenceMap = home_referenceMap;
    }

    public Map<String, String> getHome_finalMap() {
        return home_finalMap;
    }

    public void setHome_finalMap(Map<String, String> home_finalMap) {
        this.home_finalMap = home_finalMap;
    }
    

    
    
}
