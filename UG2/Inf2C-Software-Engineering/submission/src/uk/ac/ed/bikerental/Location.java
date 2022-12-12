package uk.ac.ed.bikerental;

/**
 * @author ZHOU HAO,YU HONG YU
 * @author S1862323,S1719616
 *
 * this class is used to store the postcode and address
 * <p>
 * It contains the getter and setter function for the String postcode and String address
 * The key function isNear(), which is used to compare two location whether is near or not.
 * In our perspective, the String address actually is not used in here, because it only need to compare the postcode rather than the address.
 *          
 *
 *
 */


public class Location {
    private String postcode;
    private String address;
    
/**
* 
* 
* @param postcode: data type is String
* @param address: data type is String
* 
* it is a constructor
*
*/
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
/**
 * This function is used to compare two location.
 * The determine factor is whether the first two index of the postcode of the location A is the same as the location B
 * if the index of the locations is the same, then return true,else false
 * <p>
 * @param other : data type is Location.
 * @return boolean to determine whether this location is near to another location
 * @see Boolean : true or false
 */
    public boolean isNearTo(Location other) {
        // TODO: Implement Location.isNearTo
        String p1 = other.getPostcode();
        if (postcode.charAt(0)==p1.charAt(0)) {
            if(postcode.charAt(1)==p1.charAt(1)) {
                return true;
            }
        }
        //assert false;
        return false;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    // You can add your own methods here
}
