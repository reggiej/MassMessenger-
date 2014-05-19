package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

public class UpdateCandidateInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1031650634944146094L;

	/**
	 * 
	 */
	public Long candinfoID;

	public Long candinfo_id;	
				
	public String candinfoFirstName;
	
	public String candinfoLastName;
	
	public String candinfoMiddle;
	
	public String candinfoEmail;
	
	public String candinfoQuestone;
	
	public String candinfoQuesttwo;
	
	public String candinfoQuesthree; 
	
	public String candinfoQuestfour;
	
	public String candinfoQuestfive;
	
	public String candinfoSkills;
	
	private Date candinfoCreated;
	
	
	
	
	public UpdateCandidateInfo(){
		
	}

	
	public UpdateCandidateInfo(Long candinfoID,Long candinfo_id, String candinfoFirstName, String candinfoLastName, String candinfoMiddle, String candinfoEmail, String candinfoQuestone,
			String candinfoQuesttwo, String candinfoQuesthree, String candinfoQuestfour, String candinfoQuestfive, String candinfoSkills, Date candinfoCreated ) {
		this.candinfoID = candinfoID;
		this.candinfo_id = candinfo_id;
		this.candinfoFirstName = candinfoFirstName;
		this.candinfoLastName = candinfoLastName;
		this.candinfoMiddle = candinfoMiddle;
		this.candinfoEmail = candinfoEmail;
		this.candinfoQuestone = candinfoQuestone;
		this.candinfoQuesttwo = candinfoQuesttwo;
		this.candinfoQuesthree = candinfoQuesthree;
		this.candinfoQuestfour = candinfoQuestfour;
		this.candinfoQuestfive = candinfoQuestfive;
		this.candinfoSkills = candinfoSkills;
		this.candinfoCreated = candinfoCreated;
		
		
		
	}
    
	

	public Long getCandinfoID() {
		return candinfoID;
	}


	public void setCandinfoID(Long candinfoID) {
		this.candinfoID = candinfoID;
	}


	public Long getCandinfo_id() {
		return candinfo_id;
	}


	public void setCandinfo_id(Long candinfo_id) {
		this.candinfo_id = candinfo_id;
	}


	public String getCandinfoFirstName() {
		return candinfoFirstName;
	}


	public void setCandinfoFirstName(String candinfoFirstName) {
		this.candinfoFirstName = candinfoFirstName;
	}


	public String getCandinfoLastName() {
		return candinfoLastName;
	}


	public void setCandinfoLastName(String candinfoLastName) {
		this.candinfoLastName = candinfoLastName;
	}


	public String getCandinfoMiddle() {
		return candinfoMiddle;
	}


	public void setCandinfoMiddle(String candinfoMiddle) {
		this.candinfoMiddle = candinfoMiddle;
	}


	public String getCandinfoEmail() {
		return candinfoEmail;
	}


	public void setCandinfoEmail(String candinfoEmail) {
		this.candinfoEmail = candinfoEmail;
	}


	public String getCandinfoQuestone() {
		return candinfoQuestone;
	}


	public void setCandinfoQuestone(String candinfoQuestone) {
		this.candinfoQuestone = candinfoQuestone;
	}


	public String getCandinfoQuesttwo() {
		return candinfoQuesttwo;
	}


	public void setCandinfoQuesttwo(String candinfoQuesttwo) {
		this.candinfoQuesttwo = candinfoQuesttwo;
	}


	public String getCandinfoQuesthree() {
		return candinfoQuesthree;
	}


	public void setCandinfoQuesthree(String candinfoQuesthree) {
		this.candinfoQuesthree = candinfoQuesthree;
	}


	public String getCandinfoQuestfour() {
		return candinfoQuestfour;
	}


	public void setCandinfoQuestfour(String candinfoQuestfour) {
		this.candinfoQuestfour = candinfoQuestfour;
	}


	public String getCandinfoQuestfive() {
		return candinfoQuestfive;
	}


	public void setCandinfoQuestfive(String candinfoQuestfive) {
		this.candinfoQuestfive = candinfoQuestfive;
	}


	public String getCandinfoSkills() {
		return candinfoSkills;
	}


	public void setCandinfoSkills(String candinfoSkills) {
		this.candinfoSkills = candinfoSkills;
	}


	public Date getCandinfoCreated() {
		return candinfoCreated;
	}


	public void setCandinfoCreated(Date candinfoCreated) {
		this.candinfoCreated = candinfoCreated;
	}


	

}
