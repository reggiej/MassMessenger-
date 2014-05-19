package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;



public class JobRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8293214337324639611L;

	/**
	 * 
	 */

	
	private Long jobID;
		
	private Date jobDate;
	
	private String jobTitle;
	
	private String jobDescription;
	
	private Long jobRequest;
	
	private String jobSkill;
	
	private String jobPosition;
	
	private String jobClear;
	
	private String jobLocation;
	
	private String jobSkillsMandatory;
	
	private String jobSkillsDesired;
	
	private String jobCertRequired;
	
	private String jobConus;
	
	private String jobOconus;
	
	private Long jobbHoursYear;
	
	private String jobSchedComment;
	
	private String jobMissionCritical;
	
	private String jobWorkNight;
	
	private String jobLocalTravel;
	
	private String jobPager;
	
	private String jobPagerComment;
	
	private String jobWorkHoliday;
	
	private String jobWorkWeekend;
	
	private String jobShiftWork;
	
	private String jobWarZone;
	
	private String jobCoop;
	
	private Date jobStatusChange;
	
	private Date jobUpdateDate;
	
	private Date jobCloseDate;
	
	
	
	
	
	public JobRecord(){
		
	}

	public JobRecord(Long jobID, Date jobDate, String jobTitle, String jobDescription, String jobRequest,String jobSkill,String jobPosition,	
	String jobClear, String jobLocation,String jobSkillsMandatory, String jobSkillsDesired, String jobCertRequired, String jobConus, String jobOconus,	
	Long jobbHoursYear, String jobSchedComment, String jobMissionCritical, String jobWorkNight, String jobLocalTravel, String jobPager, String jobPagerComment,	
	String jobWorkHoliday, String jobWorkWeekend, String jobShiftWork, String jobWarZone, String jobCoop, Date jobStatusChange, Date jobUpdateDate, Date jobCloseDate) {
		this.jobID = jobID;
		this.jobDate = jobDate;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
	}

	public Long getJobID() {
		return jobID;
	}

	public void setJobID(Long jobID) {
		this.jobID = jobID;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Long getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(Long jobRequest) {
		this.jobRequest = jobRequest;
	}

	public String getJobSkill() {
		return jobSkill;
	}

	public void setJobSkill(String jobSkill) {
		this.jobSkill = jobSkill;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getJobClear() {
		return jobClear;
	}

	public void setJobClear(String jobClear) {
		this.jobClear = jobClear;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getJobSkillsMandatory() {
		return jobSkillsMandatory;
	}

	public void setJobSkillsMandatory(String jobSkillsMandatory) {
		this.jobSkillsMandatory = jobSkillsMandatory;
	}

	public String getJobSkillsDesired() {
		return jobSkillsDesired;
	}

	public void setJobSkillsDesired(String jobSkillsDesired) {
		this.jobSkillsDesired = jobSkillsDesired;
	}

	public String getJobCertRequired() {
		return jobCertRequired;
	}

	public void setJobCertRequired(String jobCertRequired) {
		this.jobCertRequired = jobCertRequired;
	}

	public String getJobConus() {
		return jobConus;
	}

	public void setJobConus(String jobConus) {
		this.jobConus = jobConus;
	}

	public String getJobOconus() {
		return jobOconus;
	}

	public void setJobOconus(String jobOconus) {
		this.jobOconus = jobOconus;
	}

	public Long getJobbHoursYear() {
		return jobbHoursYear;
	}

	public void setJobbHoursYear(Long jobbHoursYear) {
		this.jobbHoursYear = jobbHoursYear;
	}

	public String getJobSchedComment() {
		return jobSchedComment;
	}

	public void setJobSchedComment(String jobSchedComment) {
		this.jobSchedComment = jobSchedComment;
	}

	public String getJobMissionCritical() {
		return jobMissionCritical;
	}

	public void setJobMissionCritical(String jobMissionCritical) {
		this.jobMissionCritical = jobMissionCritical;
	}

	public String getJobWorkNight() {
		return jobWorkNight;
	}

	public void setJobWorkNight(String jobWorkNight) {
		this.jobWorkNight = jobWorkNight;
	}

	public String getJobLocalTravel() {
		return jobLocalTravel;
	}

	public void setJobLocalTravel(String jobLocalTravel) {
		this.jobLocalTravel = jobLocalTravel;
	}

	public String getJobPager() {
		return jobPager;
	}

	public void setJobPager(String jobPager) {
		this.jobPager = jobPager;
	}

	public String getJobPagerComment() {
		return jobPagerComment;
	}

	public void setJobPagerComment(String jobPagerComment) {
		this.jobPagerComment = jobPagerComment;
	}

	public String getJobWorkHoliday() {
		return jobWorkHoliday;
	}

	public void setJobWorkHoliday(String jobWorkHoliday) {
		this.jobWorkHoliday = jobWorkHoliday;
	}

	public String getJobWorkWeekend() {
		return jobWorkWeekend;
	}

	public void setJobWorkWeekend(String jobWorkWeekend) {
		this.jobWorkWeekend = jobWorkWeekend;
	}

	public String getJobShiftWork() {
		return jobShiftWork;
	}

	public void setJobShiftWork(String jobShiftWork) {
		this.jobShiftWork = jobShiftWork;
	}

	public String getJobWarZone() {
		return jobWarZone;
	}

	public void setJobWarZone(String jobWarZone) {
		this.jobWarZone = jobWarZone;
	}

	public String getJobCoop() {
		return jobCoop;
	}

	public void setJobCoop(String jobCoop) {
		this.jobCoop = jobCoop;
	}

	public Date getJobStatusChange() {
		return jobStatusChange;
	}

	public void setJobStatusChange(Date jobStatusChange) {
		this.jobStatusChange = jobStatusChange;
	}

	public Date getJobUpdateDate() {
		return jobUpdateDate;
	}

	public void setJobUpdateDate(Date jobUpdateDate) {
		this.jobUpdateDate = jobUpdateDate;
	}

	public Date getJobCloseDate() {
		return jobCloseDate;
	}

	public void setJobCloseDate(Date jobCloseDate) {
		this.jobCloseDate = jobCloseDate;
	}

	
	
	
	
}