package com.acnc.mm.domain.messenger;

public class UpdateBusiness {

	
	
	public Long businessID;
	
	public String businessFirstName;
	
	public String businessLastName;
	
	public String businessPhoneNumber;
	
	public String businessEmail;
	
	
	
	public UpdateBusiness(){
		
	}


	public UpdateBusiness(Long businessID, String businessFirstName, String businessLastName, String businessPhoneNumber, String businessEmail) {
		this.businessID = businessID;
		this.businessFirstName = businessFirstName;
		this.businessLastName = businessLastName;
		this.businessPhoneNumber = businessPhoneNumber;
		this.businessEmail = businessEmail;
		
		
	}


	public Long getBusinessID() {
		return businessID;
	}


	public void setBusinessID(Long businessID) {
		this.businessID = businessID;
	}


	public String getBusinessFirstName() {
		return businessFirstName;
	}


	public void setBusinessFirstName(String businessFirstName) {
		this.businessFirstName = businessFirstName;
	}


	public String getBusinessLastName() {
		return businessLastName;
	}


	public void setBusinessLastName(String businessLastName) {
		this.businessLastName = businessLastName;
	}


	public String getBusinessPhoneNumber() {
		return businessPhoneNumber;
	}


	public void setBusinessPhoneNumber(String businessPhoneNumber) {
		this.businessPhoneNumber = businessPhoneNumber;
	}


	public String getBusinessEmail() {
		return businessEmail;
	}


	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	
	
	
	
	
}
