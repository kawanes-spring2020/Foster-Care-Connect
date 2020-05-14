/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.PoliceOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.PoliceRole.PoliceWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class PoliceRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise,Network network, EcoSystem business) {
        return new PoliceWorkAreaJPanel(userProcessContainer, account, (PoliceOrganization)organization, enterprise);
    }
    
    
}
