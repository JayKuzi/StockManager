/**
 * This is a stock management application for a small stationery company.
 * @author Jody Kearns
 * Date: 15 October 2021
 */
package za.ac.cput.stockmanagementclient.gui;

import java.util.UUID;

/**
 *
 * @author jodykearns
 */
public class AdditionalMethods {

    public String generateRandomID() {
        return UUID.randomUUID().toString();
    }

}
