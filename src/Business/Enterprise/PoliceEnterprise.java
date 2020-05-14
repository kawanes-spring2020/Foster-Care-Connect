package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class PoliceEnterprise extends Enterprise {
    
    public PoliceEnterprise(String name){
        super(name,Enterprise.EnterpriseType.Police);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
}
