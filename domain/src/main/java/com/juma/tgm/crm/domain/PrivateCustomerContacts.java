package com.juma.tgm.crm.domain;



import com.juma.tgm.base.domain.BaseDomain;
/**
 * private_customer_contacts - private_customer_contacts
 * 
 * @author  2017-03-13
 * @version 1.0 
 */
public class PrivateCustomerContacts extends BaseDomain{
    
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5589027686442211235L;
    private Integer privateCustomerContactsId;
	private Integer privateCustomerId;
	private String contactsName;
	private String contactsPhone;
	private String contactsBackupPhone;

	public Integer getPrivateCustomerContactsId() {
		return privateCustomerContactsId;
	}

	public void setPrivateCustomerContactsId(Integer privateCustomerContactsId) {
		this.privateCustomerContactsId = privateCustomerContactsId;
	}

	public Integer getPrivateCustomerId() {
		return privateCustomerId;
	}

	public void setPrivateCustomerId(Integer privateCustomerId) {
		this.privateCustomerId = privateCustomerId;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getContactsBackupPhone() {
		return contactsBackupPhone;
	}

	public void setContactsBackupPhone(String contactsBackupPhone) {
		this.contactsBackupPhone = contactsBackupPhone;
	}

}