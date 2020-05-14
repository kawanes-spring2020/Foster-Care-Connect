package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class FosterEnterprise extends Enterprise {
    
    public FosterEnterprise(String name){
        super(name,Enterprise.EnterpriseType.Foster);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
}
