/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

/**
 *
 * @author raunak
 */
public class LabTestWorkRequest extends WorkRequest{
    
    private String testResult;
    private String bgResultID;
    private String drug;
    private String alcohol;
    private String opiod;
    private String toxins;
    private String rbc;
    private String wbc; 

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getOpiod() {
        return opiod;
    }

    public void setOpiod(String opiod) {
        this.opiod = opiod;
    }

    public String getToxins() {
        return toxins;
    }

    public void setToxins(String toxins) {
        this.toxins = toxins;
    }

    public String getRbc() {
        return rbc;
    }

    public void setRbc(String rbc) {
        this.rbc = rbc;
    }

    public String getWbc() {
        return wbc;
    }

    public void setWbc(String wbc) {
        this.wbc = wbc;
    }
    
    

    public String getBgResultID() {
        return bgResultID;
    }

    public void setBgResultID(String bgResultID) {
        this.bgResultID = bgResultID;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
    
    
}
