
package models.ddm;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ddm.models" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="User">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="firstName"/>
 *     &lt;xs:element type="xs:string" name="surName" minOccurs="0"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:long" name="Id"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class User
{
    private String firstName;
    private String surName;
    private Long id;

    /** 
     * Get the 'firstName' element value.
     * 
     * @return value
     */
    public String getFirstName() {
        return firstName;
    }

    /** 
     * Set the 'firstName' element value.
     * 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** 
     * Get the 'surName' element value.
     * 
     * @return value
     */
    public String getSurName() {
        return surName;
    }

    /** 
     * Set the 'surName' element value.
     * 
     * @param surName
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /** 
     * Get the 'Id' attribute value.
     * 
     * @return value
     */
    public Long getId() {
        return id;
    }

    /** 
     * Set the 'Id' attribute value.
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
