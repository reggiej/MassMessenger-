/**
 * 
 */
package com.acnc.mm.dao.db.jdbc;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.acnc.mm.dao.db.MessengerDAO;
import com.acnc.mm.domain.messenger.BusinessRecord;
import com.acnc.mm.domain.messenger.CandidateInfo;
import com.acnc.mm.domain.messenger.Category;
import com.acnc.mm.domain.messenger.CertResume;
import com.acnc.mm.domain.messenger.ContractRecord;
import com.acnc.mm.domain.messenger.EducationResume;
import com.acnc.mm.domain.messenger.EmailRepo;
import com.acnc.mm.domain.messenger.EmailRepo2;
import com.acnc.mm.domain.messenger.EmployResume;
import com.acnc.mm.domain.messenger.FlatFile;
import com.acnc.mm.domain.messenger.JobRecord;
import com.acnc.mm.domain.messenger.UpdateBusiness;
import com.acnc.mm.domain.messenger.UpdateCandidate;
import com.acnc.mm.domain.messenger.UpdateCandidateInfo;
import com.acnc.mm.domain.messenger.UpdateContract;
import com.acnc.mm.util.MessengerConstants;


/**
 * @author kalittl
 *
 */
public class MessengerDAOImpl extends BaseSpringJdbcDAO implements MessengerDAO, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5616029932808855232L;

	final static private Logger log = Logger.getLogger(MessengerDAOImpl.class.getName());
	
	
	private long candinfoID;

	private long sqlCERTID ;
	
	private long categoryID;
	
	private long flatfileID;
	
	
	private class JobParameterizedRowMapper<T> implements ParameterizedRowMapper<JobRecord> {

		public JobRecord mapRow(ResultSet resultSet, int row) throws SQLException {
			JobRecord jobRecord = new JobRecord();			
			jobRecord.setJobID(resultSet.getLong("JOB_ID"));					
			jobRecord.setJobTitle(resultSet.getString("JOB_TITLE"));
			jobRecord.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
			jobRecord.setJobDate(resultSet.getDate("JOB_DATE"));

			return jobRecord;

		}
	}	
	
	private class ContractParameterizedRowMapper<T> implements ParameterizedRowMapper<ContractRecord> {

		public ContractRecord mapRow(ResultSet resultSet, int row) throws SQLException {
			ContractRecord contractRecord = new ContractRecord();			
			contractRecord.setContractID(resultSet.getLong("Contract_ID"));					
			contractRecord.setContractTitle(resultSet.getString("Contract_TITLE"));
			contractRecord.setContractAgency(resultSet.getString("Contract_Agency"));
			contractRecord.setContractDate(resultSet.getDate("Contract_DATE"));
          
			return contractRecord;

		}
	}	
	
		
	private class CategoryParameterizedRowMapper<T> implements ParameterizedRowMapper<Category>{
		
		public Category mapRow(ResultSet resultSet, int row) throws SQLException{
			Category category = new Category();
			category.setCatID(resultSet.getLong("category_id"));
			category.setCatName(resultSet.getString("category_name"));
			category.setCatDescrp(resultSet.getString("category_description"));
			category.setDateCreated(resultSet.getDate("category_created"));
			
			return category;
		}
	}
	
	private class FlatFileParameterizedRowMapper<T> implements ParameterizedRowMapper<FlatFile>{
		
		public FlatFile mapRow(ResultSet resultSet, int row) throws SQLException{
		FlatFile flatfile = new FlatFile();
		flatfile.setFileID(resultSet.getLong("flat_file_id"));
		//flatfile.setFileClob(resultSet.getBytes("flat_file_clob"));
		flatfile.setClobFile(resultSet.getBytes("flat_file_clob"));
		//flatfile.setFileClob(resultSet.getBlob("flat_file_clob"));
		flatfile.setNewName(resultSet.getString("flat_file_new_name"));
		flatfile.setOldName(resultSet.getString("flat_file_old_name"));
		flatfile.setDateCreated(resultSet.getDate("flat_file_created"));
		
		return flatfile;
		}
		
	}
	
	private class CandInfoParameterizedRowMapper<T> implements ParameterizedRowMapper<CandidateInfo> {

		public CandidateInfo mapRow(ResultSet resultSet, int row) throws SQLException {
			CandidateInfo candidateInfo = new CandidateInfo();			
			candidateInfo.setCandinfo_id(resultSet.getLong("candinfo_ID"));								
			candidateInfo.setCandinfoFirstName(resultSet.getString("FIRSTNAME"));			
			candidateInfo.setCandinfoLastName(resultSet.getString("LASTNAME"));			
			candidateInfo.setCandinfoMiddle(resultSet.getString("Middle"));			
			candidateInfo.setCandinfoEmail(resultSet.getString("Email"));
			candidateInfo.setCandinfoQuestone(resultSet.getString("Questone"));
			candidateInfo.setCandinfoQuesttwo(resultSet.getString("Questtwo"));
			candidateInfo.setCandinfoQuesthree(resultSet.getString("Questthree"));
			candidateInfo.setCandinfoQuestfour(resultSet.getString("Questfour"));
			candidateInfo.setCandinfoQuestfive(resultSet.getString("Questfive"));
			candidateInfo.setCandinfoSkills(resultSet.getString("Skills"));
		    candidateInfo.setCandinfoCreated(resultSet.getDate("created"));
		
			return candidateInfo;

		}
	}	
	
	private class EmployInfoParameterizedRowMapper<T> implements ParameterizedRowMapper<EmployResume> {

		public EmployResume mapRow(ResultSet resultSet, int row) throws SQLException {
			EmployResume employResume = new EmployResume();
			
			employResume.setEmployID(resultSet.getLong("empID"));
			employResume.setCompName(resultSet.getString("compName"));
			employResume.setTitle(resultSet.getString("title"));
			employResume.setFromMonth(resultSet.getDate("frommon"));
			employResume.setToYear(resultSet.getDate("tomon"));
			employResume.setProjectRole(resultSet.getString("project"));
			employResume.setProjDescpt(resultSet.getString("projectdescrpt"));
			employResume.setProjfromMonth(resultSet.getDate("projfrom"));
			employResume.setProjtoYear(resultSet.getDate("projto"));
		
			return employResume;

		}
	}	
	
	private class CertInfoParameterizedRowMapper<T> implements ParameterizedRowMapper<CertResume>{
		
		public CertResume mapRow(ResultSet resultSet, int row) throws SQLException
		{
			CertResume certResume = new CertResume();
		
			certResume.setCandinfo_id(resultSet.getLong("candinfo_id"));
			certResume.setCerID(resultSet.getLong("certsid"));
			certResume.setCertName(resultSet.getString("certName"));
			certResume.setCertYear(resultSet.getDate("certYear"));
			
			return certResume;
			}
		}
	
	private class EducateInfoParametrizedRowMapper<T> implements ParameterizedRowMapper<EducationResume>{
		
		public EducationResume mapRow(ResultSet resultSet, int row) throws SQLException{
			EducationResume educationResume = new EducationResume();
			educationResume.setEdID(resultSet.getLong("edID"));
			educationResume.setDegree(resultSet.getString("degree"));
			educationResume.setSchoolName(resultSet.getString("schoolname"));
			educationResume.setMajor(resultSet.getString("major"));
			educationResume.setCompYear(resultSet.getDate("compYear"));
			
			return educationResume;
		}
	}
	
		
	private class EmailParameterizedRowMapper<T> implements ParameterizedRowMapper<EmailRepo> {

		public EmailRepo mapRow(ResultSet resultSet, int row) throws SQLException {
			EmailRepo emailRecord = new EmailRepo();
			emailRecord.setEmailRepoID(resultSet.getLong("EMAILREPO_ID"));
			
			emailRecord.setCandidateID(resultSet.getLong("CANDIDATE_ID"));
			emailRecord.setCandidateFirstName(resultSet.getString("FIRSTNAME"));
			emailRecord.setCandidateLastName(resultSet.getString("LASTNAME"));
			emailRecord.setCandidateEmail(resultSet.getString("EMAIL"));
			
			emailRecord.setJobID(resultSet.getLong("JOB_ID"));
			emailRecord.setJobTitle(resultSet.getString("JOB_TITLE"));
			emailRecord.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
			
			emailRecord.setDtCreated(resultSet.getDate("CREATE_DT"));			

			return emailRecord;

		}
	}	
	

	private class Email2ParameterizedRowMapper<T> implements ParameterizedRowMapper<EmailRepo2> {

		public EmailRepo2 mapRow(ResultSet resultSet, int row) throws SQLException {
			EmailRepo2 emailRecord2 = new EmailRepo2();
			emailRecord2.setEmailRepoID(resultSet.getLong("EMAILREPO_ID"));
			
			emailRecord2.setBusinessID(resultSet.getLong("BUSINESSDEV_ID"));
			emailRecord2.setBusinessFirstName(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
			emailRecord2.setBusinessLastName(resultSet.getString("BUSINESSDEV_LASTNAME"));
			emailRecord2.setBusinessEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
			
			emailRecord2.setContractID(resultSet.getLong("Contract_ID"));
			emailRecord2.setContractTitle(resultSet.getString("Contract_TITLE"));
			emailRecord2.setContractAgency(resultSet.getString("CONTRACT_AGENCY"));
			
			
			return emailRecord2;

		}
	}	
	
	    public UpdateCandidate mapRow(ResultSet resultSet, int row) throws SQLException{
	    	UpdateCandidate updateCandidate = new UpdateCandidate();
	    	updateCandidate.setCandidateID(resultSet.getLong("CANDIDATE_ID"));					
			updateCandidate.setCandidateFirstName(resultSet.getString("CANDIDATE_FIRSTNAME"));
			updateCandidate.setCandidateLastName(resultSet.getString("CANDIDATE_LASTNAME"));
			updateCandidate.setCandidateEmail(resultSet.getString("CANDIDATE_EMAIL"));
			updateCandidate.setCandidateCreated(resultSet.getDate("CANDIDATE_CREATED"));
			updateCandidate.setCandidateExperience(resultSet.getString("CANDIDATE_EXPERIENCE"));

			return updateCandidate;

	    }
	    
	    public UpdateCandidateInfo UpdateCandidateInfomapRow(ResultSet resultSet, int row) throws SQLException{
	    	UpdateCandidateInfo updateCandidateInfo = new UpdateCandidateInfo();
	    	updateCandidateInfo.setCandinfo_id(resultSet.getLong("candinfo_id"));
	    	updateCandidateInfo.setCandinfoFirstName(resultSet.getString("FIRSTNAME"));			
	    	updateCandidateInfo.setCandinfoLastName(resultSet.getString("LASTNAME"));			
	    	updateCandidateInfo.setCandinfoMiddle(resultSet.getString("Middle"));			
	    	updateCandidateInfo.setCandinfoEmail(resultSet.getString("Email"));
	    	updateCandidateInfo.setCandinfoQuestone(resultSet.getString("Questone"));
	    	updateCandidateInfo.setCandinfoQuesttwo(resultSet.getString("Questtwo"));
	    	updateCandidateInfo.setCandinfoQuesthree(resultSet.getString("Questthree"));
	    	updateCandidateInfo.setCandinfoQuestfour(resultSet.getString("Questfour"));
	    	updateCandidateInfo.setCandinfoQuestfive(resultSet.getString("Questfive"));
	    	updateCandidateInfo.setCandinfoSkills(resultSet.getString("Skills"));
	    	updateCandidateInfo.setCandinfoCreated(resultSet.getDate("created"));
		
	    	
	    	return updateCandidateInfo;
	    }
	    
	    private class BusinessParameterizedRowMapper<T> implements ParameterizedRowMapper<BusinessRecord> {  
	    
	    public BusinessRecord mapRow(ResultSet resultSet, int row) throws SQLException{
	    	BusinessRecord businessRecord = new BusinessRecord();
	    	businessRecord.setBusinessID(resultSet.getLong("BUSINESSDEV_ID"));					
	    	businessRecord.setBusinessFirstName(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
	    	businessRecord.setBusinessLastName(resultSet.getString("BUSINESSDEV_LASTNAME"));
	    	businessRecord.setBusinessPhoneNumber(resultSet.getString("BUSINESSDEV_PHONENUMBER"));
	    	businessRecord.setBusinessEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
			

			return businessRecord;

	    }
	    
	 } 
	
	final static private String SQL_GET_ALL_EMAIL_REPO =
			" SELECT e.EMAILREPO_ID, e.CANDIDATE_ID, e.JOB_ID, e.CREATE_DT, " +
			" c.FIRSTNAME, c.LASTNAME, c.EMAIL, " +
			" j.JOB_TITLE, j.JOB_DESCRIPTION" +
			" FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo e " +
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo c " +
					" ON e.CANDIDATE_ID = c.CANDINFO_ID " + 
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".jobs j " +
					" ON e.JOB_ID = j.JOB_ID";
	
	final static private String SQL_GET_ALL_EMAIL_REPO2 =
			" SELECT e.EMAILREPO_ID, e.CONTRACT_ID, e.BUSINESSDEV_ID, " +
			" b.BUSINESSDEV_FIRSTNAME, b.BUSINESSDEV_LASTNAME, b.BUSINESSDEV_EMAIL, " +
			" c.CONTRACT_TITLE, c.CONTRACT_AGENCY" +
			" FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo2 e " +
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".contracts c " +
					" ON e.CONTRACT_ID = c.CONTRACT_ID " + 
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".businessdev b " +
					" ON e.BUSINESSDEV_ID = b.BUSINESSDEV_ID";
	
	final static private String SQL_GET_ALL_JOBS =
		" SELECT JOB_ID, JOB_TITLE, JOB_DESCRIPTION, JOB_DATE FROM " +
		MessengerConstants.MM_SCHEMA_NAME + ".jobs ";
	
	final static private String SQL_GET_ALL_CONTRACTS =
			" SELECT CONTRACT_ID, CONTRACT_TITLE, CONTRACT_AGENCY, CONTRACT_DATE FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".contracts ";
	
	final static private String SQL_GET_MAX_ID_CANDINFO =
			"SELECT candinfo_id, firstname, lastname, middle, email, questone, questtwo, questthree, questfour, questfive, skills, created FROM " +
	        MessengerConstants.MM_SCHEMA_NAME + ".candinfo where enabled = 0";
	
	final static private String SQL_GET_ALL_EDUCATION = "Select CANDINFO_ID, EDID, DEGREE, SCHOOLNAME, MAJOR, COMPYEAR FROM "  +
			MessengerConstants.MM_SCHEMA_NAME + ".education ";
	               
	
	final static private String SQL_GET_ALL_EMPLOY = "Select CANDINFO_ID, EMPID, COMPNAME, TITLE, FROMMON, TOMON, PROJECT, PROJECTDESCRPT, PROJFROM, PROJTO FROM " 
	     + MessengerConstants.MM_SCHEMA_NAME + ".employer ";
	 
	
	final static private String SQL_GET_ALL_CERT = "Select CANDINFO_ID, CERTSID, CERTYEAR, CERTNAME from " +
		 MessengerConstants.MM_SCHEMA_NAME + ".certs ";
	 
			
	final static private String SQL_GET_ALL_BUSINESS =
			" SELECT BUSINESSDEV_ID, BUSINESSDEV_FIRSTNAME, BUSINESSDEV_LASTNAME, BUSINESSDEV_PHONENUMBER,BUSINESSDEV_EMAIL FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".businessdev ";

		
	/*public List<EmailRepo> getEmailRepo() {
		log.info("this is the email repo pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EmailRepo> newEmailRecord = template.query(SQL_GET_ALL_EMAIL_REPO, 
				new EmailParameterizedRowMapper<EmailRepo>());
		
		return newEmailRecord;
	}*/
		
	/*public List<CandidateInfo> getCandInfoID(){
		log.info("This is All of the candidates ");
		JdbcTemplate template = getJdbcTemplate();
		List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO, 
				new CandInfoParameterizedRowMapper<CandidateInfo>());
		
		return newCandidateInfo;
	}*/
	

     
    public long MaxID(CandidateInfo allCandidateInfoPosted) {
    	log.info("Max Id from candidate last step");
    	long candinfoID = allCandidateInfoPosted.getCandinfo_id();
    	if(candinfoID == 0){
    		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
    		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
    		System.out.println(sqlMaxID);
    	}
    	
    	return candinfoID;
	}
 
    /*@Override
	public List<CandidateInfo> getAllCandidateInfo() {
    	log.info("This is the max ID for ID");
		JdbcTemplate template = getJdbcTemplate();
		List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO , 
				new CandInfoParameterizedRowMapper<CandidateInfo>());
		
		return newCandidateInfo;
	}*/

	/*public List<JobRecord> getJobs() {
		log.info("this is the job id to query from ");
		JdbcTemplate template = getJdbcTemplate();
		List<JobRecord> newJobRecord = template.query(SQL_GET_ALL_JOBS, 
				new JobParameterizedRowMapper<JobRecord>());
		
		return newJobRecord;
	}*/
	
	/*public List<ContractRecord> getContracts() {
		log.info("this is the contract id to query from ");
		JdbcTemplate template = getJdbcTemplate();
		List<ContractRecord> newContractRecord = template.query(SQL_GET_ALL_CONTRACTS, 
				new ContractParameterizedRowMapper<ContractRecord>());
		
		return newContractRecord;
	}*/

	
	/*public List<BusinessRecord> getBusiness() {
		log.info("this is the business id to query from ");
		JdbcTemplate template = getJdbcTemplate();
		List<BusinessRecord> newBusinessRecord = template.query(SQL_GET_ALL_BUSINESS, 
				new BusinessParameterizedRowMapper<BusinessRecord>());
		
		return newBusinessRecord;
	}*/
	
	
	final static private String SQL_INSERT_CANDIDATE =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".candinfo " +
			" (firstname, lastname, middle, email, questone, questtwo, questthree, questfour, questfive, skills, created, enabled ) " +
			" VALUES (?,?,?,?,?,?,?,?,?,?,?,0)";
	// Populate Candidate information table
	public void svCandidate(CandidateInfo candidateInfo){
		log.info("this is the candidate data being saved ");
		candinfoID = Long.MAX_VALUE;
		if(candinfoID != 0){
   		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
   		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
   		System.out.println("This is what here : " + candidateInfo.getCandinfoFirstName());
		}
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_CANDIDATE, new Object[] {
				candidateInfo.getCandinfoFirstName(),
				candidateInfo.getCandinfoLastName(),
				candidateInfo.getCandinfoMiddle(),
				candidateInfo.getCandinfoEmail(),
				candidateInfo.getCandinfoQuestone(),
				candidateInfo.getCandinfoQuesttwo(),
				candidateInfo.getCandinfoQuesthree(),
				candidateInfo.getCandinfoQuestfour(),
				candidateInfo.getCandinfoQuestfive(),
				candidateInfo.getCandinfoSkills(),
				new Date()});
		// Make a cal to the other table to poplutae it with fake data
	}
	
	
	public void svBusiness(BusinessRecord businessRecord) {
		log.info("this is the Business data being saved ");
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_BUSINESS, new Object[] {
				businessRecord.getBusinessFirstName(),
				businessRecord.getBusinessLastName(),
				businessRecord.getBusinessPhoneNumber(),
				businessRecord.getBusinessEmail()});
	}
	
	
	final static private String SQL_INSERT_BUSINESS =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".businessdev " +
					" (BUSINESSDEV_FIRSTNAME,BUSINESSDEV_LASTNAME,BUSINESSDEV_PHONENUMBER, BUSINESSDEV_EMAIL ) " +
					" VALUES (?,?,?,?)";

	
	final static private String SQL_INSERT_CONTRACT =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".contracts " +
					" (CONTRACT_TITLE,CONTRACT_AGENCY,CONTRACT_DATE ) " +
					" VALUES (?,?,?)";
	
	public void svContract(ContractRecord contractRecord){
		log.info("this is the contract data being saved ");
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_CONTRACT, new Object[] {
				contractRecord.getContractTitle(),
				contractRecord.getContractAgency(),
				new Date()});
	}
	

	final static private String SQL_INSERT_JOB =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".jobs " +
					" (JOB_TITLE,JOB_DESCRIPTION,JOB_DATE ) " +
					" VALUES (?,?,?)";
	
	public void svJob(JobRecord jobRecord){
		log.info("this is the job data being saved ");
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_JOB, new Object[] {
				jobRecord.getJobTitle(),
				jobRecord.getJobDescription(),
				new Date()});
	}
	
	final static private String SQL_INSERT_EMAIL_REPO =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo " +
					" (CANDIDATE_ID,JOB_ID,CREATE_DT ) " +
					" VALUES (?,?,?)";
	
	final static private String SQL_INSERT_EMAIL_REPO2 =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo2 " +
					" (BUSINESSDEV_ID,CONTRACT_ID,CREATE_DT ) " +
					" VALUES (?,?,?)";
	//Insert into Candidate table
	/*final static private String SQL_INSERT_CANDINFO =
			" INSERT INTO " + 
					MessengerConstants.MM_SCHEMA_NAME + ".candinfo " +
					" (FIRSTNAME,LASTNAME,MIDDLE,EMAIL,QUESTONE,QUESTTWO,QUESTTHREE,QUESTFOUR,QUESTFIVE,SKILLS ) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?)";*/

	final static private String SQL_INSERT_CERT = 
			 " INSERT INTO " +
		               MessengerConstants.MM_SCHEMA_NAME + ".certs " +
		    		   "(CERTYEAR,CERTNAME, CANDINFO_CER_ID ) " +
		               "VALUES(?,?,?)";
	
	
	final static private String SQL_INSERT_EMPLOY =
			" INSERT INTO " + 
					MessengerConstants.MM_SCHEMA_NAME + ".employer" +
					" (COMPNAME,TITLE,FROMMON,TOMON,PROJECT,PROJDESCRPT,PROJFROM, PROJTO ) " +
					" VALUES (?,?,?,?,?,?,?,?)";
	
	final static private String SQL_INSERT_EDUCATE =
			" INSERT INTO " + 
					MessengerConstants.MM_SCHEMA_NAME + ".education" +
					" (DEGREE,SCHOOLNAME,MAJOR,COMPYEAR, ) " +
					" VALUES (?,?,?,?)";
	
	final static private String SQL_INSERT_CATEGORY =
			" INSERT INTO " +
	                 MessengerConstants.MM_SCHEMA_NAME + ".category" +
					"(CATEGORY_NAME,CATEGORY_DESCRIPTION,CATEGORY_CREATED)" +
	                 "VALUES(?,?,?)";
	
	
	
	public void svEmailQueue(final List<EmailRepo> allAddedEmailsInfo){
		log.info("this is the EMAIL REPO data being saved ");
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EMAIL_REPO, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0, int arg1) throws SQLException {
				EmailRepo emailRepo = allAddedEmailsInfo.get(arg1);
				arg0.setLong(1, emailRepo.getCandidateID());
				arg0.setLong(2, emailRepo.getJobID());
				arg0.setDate(3, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
				
			}
			
			@Override
			public int getBatchSize() {
				return allAddedEmailsInfo.size();
			}
		});
		
		
	}

	public void updateCandidate(UpdateCandidate updateCandidate){
		log.info("THIS IS THE UPDATED INFO");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".candidates " +
		"SET CANDIDATE_FIRSTNAME = ?, CANDIDATE_LASTNAME = ?, CANDIDATE_EMAIL = ? " +
		"WHERE CANDIDATE_ID =" + updateCandidate.getCandidateID() , new Object[] {
		updateCandidate.getCandidateFirstName(),
		updateCandidate.getCandidateLastName(),
		updateCandidate.getCandidateEmail(),
		});
		}

	

	public void updateJobinfo(JobRecord jobRecord) {
		log.info("this is the Job Info being updated");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".jobs" +
		    " SET JOB_TITLE = ?, JOB_DESCRIPTION = ? " + 
		  " WHERE JOB_ID = " + jobRecord.getJobID(), new Object[] {
		jobRecord.getJobTitle(),
		jobRecord.getJobDescription(),
		});
		
		}

	public void deleteJobinfo(JobRecord jobRecord) {
		log.info("this is the job info being deleted");
		JdbcTemplate template = getJdbcTemplate();
		template.update("Delete FROM " + MessengerConstants.MM_SCHEMA_NAME + ".jobs WHERE JOB_ID = " + jobRecord.getJobID());
		
		}

	public void updateBusinessinfo(UpdateBusiness updateBusiness) {
		log.info("THIS IS THE UPDATED BUSINESS INFO");
	
		log.info("id value for bdpoc: " + updateBusiness.getBusinessID());
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".businessdev " +
		" SET BUSINESSDEV_FIRSTNAME = ?, BUSINESSDEV_LASTNAME = ?, BUSINESSDEV_PHONENUMBER = ?, BUSINESSDEV_EMAIL = ? " +
		" WHERE BUSINESSDEV_ID =" + updateBusiness.getBusinessID(), new Object[] {
			updateBusiness.getBusinessFirstName(),
			updateBusiness.getBusinessLastName(),
			updateBusiness.getBusinessPhoneNumber(),
			updateBusiness.getBusinessEmail()});
		
	}

	public void deleteBusinessinfo(BusinessRecord businessRecord) {
		log.info("this is the business info being deleted");
		JdbcTemplate template = getJdbcTemplate();
		template.update("Delete FROM " + MessengerConstants.MM_SCHEMA_NAME + ".businessdev WHERE BUSINESSDEV_ID = " + businessRecord.getBusinessID());
		
	}

	public void deleteContractinfo(ContractRecord contractRecord) {
		log.info("this is the Contract info being deleted");
		JdbcTemplate template = getJdbcTemplate();
		template.update("Delete FROM " + MessengerConstants.MM_SCHEMA_NAME + ".contracts WHERE CONTRACT_ID = " + contractRecord.getContractID());
		
	}

	public void updateContractinfo(UpdateContract updateContract) {
		log.info("THIS IS THE UPDATED Contract INFO");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".contracts " +
		"SET CONTRACT_TITLE = ?, CONTRACT_AGENCY = ?" +
		"WHERE CONTRACT_ID =" + updateContract.getContractID() , new Object[] {
			updateContract.getContractTitle(),
			updateContract.getContractAgency(),		
		});
		
	}

	public List<EmailRepo2> getEmailRepo2() {
		log.info("this is the email2 repo pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EmailRepo2> newEmailRecord2 = template.query(SQL_GET_ALL_EMAIL_REPO2, 
				new Email2ParameterizedRowMapper<EmailRepo2>());
		
		return newEmailRecord2;
	}

	public void svEmailQueue2(final List<EmailRepo2> allAddedEmailsInfo2) {
         log.info("this is the EMAIL REPO2 data being saved ");
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EMAIL_REPO2, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0, int arg1) throws SQLException {
				EmailRepo2 emailRepo2 = allAddedEmailsInfo2.get(arg1);				
				arg0.setLong(1, emailRepo2.getBusinessID());
				arg0.setLong(2, emailRepo2.getContractID());
				arg0.setDate(3, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
				
			}
			
			
			public int getBatchSize() {
				return allAddedEmailsInfo2.size();
			}
		});
		
	}

	@Override
	public void svCandidateInfo(CandidateInfo candidateInfo) {
		log.info("Resume info data being saved ");
		candinfoID = Long.MAX_VALUE;
		if(candinfoID != 0){
   		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
   		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
   		System.out.println("This is what here : " + candidateInfo.getCandinfoFirstName());
   		}		
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_CANDIDATE, new Object[] {
				
				candidateInfo.getCandinfoFirstName(),
				candidateInfo.getCandinfoLastName(),
				candidateInfo.getCandinfoMiddle(),
				candidateInfo.getCandinfoEmail(),
				candidateInfo.getCandinfoQuestone(),
				candidateInfo.getCandinfoQuesttwo(),
				candidateInfo.getCandinfoQuesthree(),
				candidateInfo.getCandinfoQuestfour(),
				candidateInfo.getCandinfoQuestfive(),
				candidateInfo.getCandinfoSkills(),
				candidateInfo.getCandinfoCreated()
		});
	}
	
	@Override
	public void svCandidateInfo(EducationResume educationResume) {
		log.info("Education info data being saved !!!!");	    
		
		final String sqlEDUIN  = "INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + ".education (CANDINFO_ID, DEGREE, SCHOOLNAME, MAJOR, COMPYEAR) "
		    		    + "VALUES (?,?,?,?,?)";

		    	   getJdbcTemplate().update(
		    		  sqlEDUIN, candinfoID, educationResume.getDegree(), educationResume.getSchoolName(), educationResume.getMajor(), educationResume.getCompYear());
		
		

}
	@Override
	public void svCandidateInfo(EmployResume employResume) {
		log.info("Employ info data being saved ");
		
		final String sqlEMP = "INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + 
    			".employer  (CANDINFO_ID, COMPNAME,TITLE,FROMMON,TOMON,PROJECT,PROJECTDESCRPT,PROJFROM, PROJTO) " +
					" VALUES (?,?,?,?,?,?,?,?,?)";
    	
    	       getJdbcTemplate().update(
    		    sqlEMP, candinfoID, employResume.getCompName(), employResume.getTitle(), employResume.getFromMonth(), employResume.getToYear(), employResume.getProjectRole(), employResume.getProjDescpt(), 
    		    employResume.getProjfromMonth(), employResume.getProjtoYear());
		
			}
    // Trigger when populate the add candidates
	@Override
	public void svCertResume(CertResume certResume) {
		log.info("Certification info data being saved ");
		
		final String sqlCert = "INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + ".certs (CANDINFO_ID, CERTYEAR, CERTNAME) "
			    + "VALUES (?,?,?)";

			    getJdbcTemplate().update(
			    sqlCert, candinfoID,certResume.getCertYear(), certResume.getCertName());
		    	
	 }
	
	//Wizard trigger resume save.	
	public void saveCandidateInfo(List<CandidateInfo> cnd) {
		for(CandidateInfo om: cnd){
			System.out.println("Inside cnd loop last stage " + om.getCandinfoFirstName());
		}
		
		long candinfoID = Long.MAX_VALUE;
		if(candinfoID != 0){
   		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
   		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
   		System.out.println("Max Resume wizard id number is :" + candinfoID);
		 }

	}
	

	@Override
	public void saveEmployInfo(final List<EmployResume> mainER) {
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EMPLOY, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0, int arg1)
					throws SQLException {
				EmployResume employResume = mainER.get(arg1);
				arg0.setNString(1, employResume.getCompName());
				arg0.setNString(2, employResume.getTitle());
				arg0.setDate(3, (java.sql.Date) employResume.getFromMonth());
				arg0.setDate(4, (java.sql.Date) employResume.getToYear());
				arg0.setNString(5, employResume.getProjectRole());
				arg0.setNString(6, employResume.getProjDescpt());
				arg0.setDate(3, (java.sql.Date) employResume.getProjfromMonth());
				arg0.setDate(3, (java.sql.Date) employResume.getProjtoYear());
				
			}
			
			@Override
			public int getBatchSize() {
				return mainER.size();
			}
        });
		
	}


	@Override
	public void saveCertificationInfo(final List<CertResume> cert) {
		for(CertResume om : cert){
			System.out.println("Inside cert loop last stage " + om.getCertName());
		}
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_CERT, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0, int arg1)
					throws SQLException {
				CertResume certResume = cert.get(arg1);
				arg0.setNString(1, certResume.getCertName());
				arg0.setDate(2, (java.sql.Date) certResume.getCertYear());
				arg0.setLong(3, certResume.getCandinfo_id());
								
			}
			
			@Override
			public int getBatchSize() {
				return cert.size();
			}
        });

	}

	@Override
	public void saveEducationInfo(final List<EducationResume> empEdu) {
		for(EducationResume on : empEdu){
			System.out.println("Inside empEdu loop laststage " + on.getDegree());
		}

		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EDUCATE, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0, int arg1)
					throws SQLException {
				EducationResume educationResume = empEdu.get(arg1);
				arg0.setNString(1, educationResume.getDegree());
				arg0.setNString(2, educationResume.getSchoolName());
				arg0.setNString(2, educationResume.getMajor());
				arg0.setDate(2, (java.sql.Date) educationResume.getCompYear());
			}
			
			@Override
			public int getBatchSize() {
				return empEdu.size();
			}
        });
		
	}

	/*@Override
	public List<EducationResume> getAllEducation() {
		log.info("this is the education pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EducationResume> newEducationResume = template.query(SQL_GET_ALL_EDUCATION, 
				new EducateInfoParametrizedRowMapper<EducationResume>());
		
		return newEducationResume;
	}

	@Override
	public List<EmployResume> getAllEmploy() {
		log.info("this is the employ pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EmployResume> newEmployResume = template.query(SQL_GET_ALL_EMPLOY, 
				new EmployInfoParameterizedRowMapper<EmployResume>());
		
		return newEmployResume;
	}

	@Override
	public List<CertResume> getAllCert() {
		log.info("this is the cert pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<CertResume> newCertResume = template.query(SQL_GET_ALL_CERT, 
				new CertInfoParameterizedRowMapper<CertResume>());
		
		return newCertResume;
	}*/

	@Override
	public void getALLCert(CertResume certResume) {
        
	 sqlCERTID = certResume.getCandinfo_id();
	 System.out.println("This is the value I need " + sqlCERTID);
	 
	 findByForeignKey(sqlCERTID);
		
	}
	
	public CertResume findByForeignKey(long custId){
			System.out.println("This is what here " + custId);
		
		return null;
	}


	/*@Override
	public List<CandidateInfo> getAllQueryRecord() {
		log.info("This is the entire record call");
		
		JdbcTemplate template = getJdbcTemplate();
		List<CandidateInfo> newCandidateInfo = template.query("Select * from " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo where enabled = 0", 
				new CandInfoParameterizedRowMapper<CandidateInfo>());
		return newCandidateInfo;
	}*/

	
	@Override
	public void updateCandidateInfo(CandidateInfo candidateInfo) {
		log.info("THIS IS THE UPDATED Candidate INFO");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo " +
		"SET firstname = ?, lastname = ?, middle = ?, email = ?, questone = ?, questtwo = ?, questthree = ?, questfour = ?, questfive = ?, skills = ?" +
		"WHERE candinfo_id = " + candidateInfo.getCandinfo_id(), new Object[] {
			
			candidateInfo.getCandinfoFirstName(),
			candidateInfo.getCandinfoLastName(),
			candidateInfo.getCandinfoMiddle(),
			candidateInfo.getCandinfoEmail(),
			candidateInfo.getCandinfoQuestone(),
			candidateInfo.getCandinfoQuesttwo(),
			candidateInfo.getCandinfoQuesthree(),
			candidateInfo.getCandinfoQuestfour(),
			candidateInfo.getCandinfoQuestfive(),
			candidateInfo.getCandinfoSkills()
		});
		System.out.println("This is my value here at DAO IMPL = " + candidateInfo.getCandinfo_id());
		System.out.println("This is my value here at DAO IMPL First Name = " + candidateInfo.getCandinfoFirstName());
	}

	@Override
	public void updateEducationResume(EducationResume educationResume) {
		log.info("THIS IS THE UPDATED Education Resume");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".education " +
		"SET degree = ?, schoolname = ?, major = ?, compyear = ?" +
		"WHERE  edid = " + educationResume.getEdID(), new Object[] {
			educationResume.getDegree(),
			educationResume.getSchoolName(),
			educationResume.getMajor(),
			educationResume.getCompYear()
			
		});
		System.out.println("This is my value here at DAO IMPL ID = " + educationResume.getEdID());
		System.out.println("This is my value here at DAO IMPL Degree = " + educationResume.getDegree());
	}

	@Override
	public void updateCertResume(CertResume certResume) {
		log.info("THIS IS THE UPDATED CERT Resume");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".certs " +
		"SET certyear = ?, certname = ?" +
		"WHERE certsid = " + certResume.getCerID(), new Object[] {
			certResume.getCertYear(),
			certResume.getCertName()
		});
		System.out.println("This is my value cert statment" );
		System.out.println("This is my value cert statment = " + certResume.getCerID() );
	}

	@Override
	public void updateEmploynResume(EmployResume employResume) {
		log.info("THIS IS THE UPDATED EMPLOY Resume");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".employer " +
		"SET compname = ?, title = ?, frommon = ?, tomon = ?, project = ?, projectdescrpt = ?, projfrom = ?," +
		" projto = ?"	+	
		"WHERE empid = " + employResume.getEmployID(), new Object[] {
			employResume.getCompName(),
			employResume.getTitle(),
			employResume.getFromMonth(),
			employResume.getToYear(),
			employResume.getProjectRole(),
			employResume.getProjDescpt(),
			employResume.getProjfromMonth(),
			employResume.getProjtoYear()
		});
		
	}

	@Override
	public void candID(long passID) {
		
	}

	
	

	/*@Override
	public List<CertResume> getSelectedCertsInfomation(Long passID) {
		
		JdbcTemplate template = getJdbcTemplate();
		List<CertResume> newCertResume = template.query("Select * from " + MessengerConstants.MM_SCHEMA_NAME + ".certs where candinfo_id =" + passID, 
				new CertInfoParameterizedRowMapper<CertResume>());
			return newCertResume;
		
		 
	}


@Override
public List<EducationResume> getSelectedEducationInformation(Long passID) {
	log.info("this is the education pull ");
	JdbcTemplate template = getJdbcTemplate();
	List<EducationResume> newEducationResume = template.query("Select * from " + MessengerConstants.MM_SCHEMA_NAME + ".education where candinfo_id =" + passID, 
			new EducateInfoParametrizedRowMapper<EducationResume>());
	
	return newEducationResume;
}

@Override
public List<EmployResume> getSelectedEmployInfomation(Long passID) {
	JdbcTemplate template = getJdbcTemplate();
	List<EmployResume> newEmployResume = template.query("Select * from " + MessengerConstants.MM_SCHEMA_NAME + ".employer where candinfo_id ="  + passID,
			new EmployInfoParameterizedRowMapper<EmployResume>());
	
	return newEmployResume;
	
}*/

@Override
public void svUpdatedEmploy(EmployResume employResume) {
	
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".employer " +
	"SET compname = ?, title = ?, frommon = ?, tomon = ?, project = ?, projectdescrpt = ?, projfrom = ?," +
	" projto = ?"	+	
	"WHERE empid = " + employResume.getEmployID(), new Object[] {
		employResume.getCompName(),
		employResume.getTitle(),
		employResume.getFromMonth(),
		employResume.getToYear(),
		employResume.getProjectRole(),
		employResume.getProjDescpt(),
		employResume.getProjfromMonth(),
		employResume.getProjtoYear()
	});
	
	System.out.println("This is my value employ statment = " + employResume.getCompName() + " where ID = " + employResume.getEmployID());
}

@Override
public void svUpdatedEducation(EducationResume educationResume) {
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".education " +
	"SET degree = ?, schoolname = ?, major = ?, compyear = ?" +
	"WHERE  edid = " + educationResume.getEdID(), new Object[] {
		educationResume.getDegree(),
		educationResume.getSchoolName(),
		educationResume.getMajor(),
		educationResume.getCompYear()
		
	});
	System.out.println("This is my value here at DAO IMPL ID = " + educationResume.getEdID());
	System.out.println("This is my value here at DAO IMPL Degree = " + educationResume.getDegree());
	
}

@Override
public void svUpdatedCert(CertResume certResume) {
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".certs " +
	"SET certyear = ?, certname = ?" +
	"WHERE certsid = " + certResume.getCerID(), new Object[] {
		certResume.getCertYear(),
		certResume.getCertName()
	});
	System.out.println("This is my value cert statment = " + certResume.getCertName());
	System.out.println("This is my id cert statment = " + certResume.getCerID() );
	
}

@Override
public void svUpdatedCand(CandidateInfo candidateInfo) {
	
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo " +
	"SET firstname = ?, lastname = ?, middle = ?, email = ?, questone = ?, questtwo = ?, questthree = ?, questfour = ?, questfive = ?, skills = ?" +
	"WHERE candinfo_id = " + candidateInfo.getCandinfo_id(), new Object[] {
		
		candidateInfo.getCandinfoFirstName(),
		candidateInfo.getCandinfoLastName(),
		candidateInfo.getCandinfoMiddle(),
		candidateInfo.getCandinfoEmail(),
		candidateInfo.getCandinfoQuestone(),
		candidateInfo.getCandinfoQuesttwo(),
		candidateInfo.getCandinfoQuesthree(),
		candidateInfo.getCandinfoQuestfour(),
		candidateInfo.getCandinfoQuestfive(),
		candidateInfo.getCandinfoSkills()
	});
	System.out.println("This is my value here at DAO IMPL = " + candidateInfo.getCandinfo_id());
	System.out.println("This is my value here at DAO IMPL First Name = " + candidateInfo.getCandinfoFirstName());
	
	
}

@Override
public void deleteCandidateinfo(UpdateCandidateInfo updateCandidateinfo) {
	log.info("This is the deleted info");
	JdbcTemplate template = getJdbcTemplate ();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME  +".candinfo SET enabled=1 WHERE CANDINFO_ID = " + updateCandidateinfo.getCandinfo_id());
	
}



/*@Override
public void svCategory(Category category) {
	log.info("Save Category");
	categoryID = Long.MAX_VALUE;
	if(categoryID != 0){
		String sqlMaxID1 = "Select MAX(Category_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".category " ;
		categoryID = getJdbcTemplate().queryForLong(sqlMaxID1, new Object[0]);
		System.out.println("Max id number is :" + categoryID);
	}
	JdbcTemplate template = getJdbcTemplate();
	template.update(SQL_INSERT_CATEGORY, new Object[]{	
	  category.getCatName(),
	  category.getCatDescrp());
	
   }*/




final static private String SQL_INSERT_FLATFILE =
"INSERT INTO " +
  MessengerConstants.MM_SCHEMA_NAME + ".flat_file" +
 "(FLAT_FILE_CLOB, FLAT_FILE_NEW_NAME, FLAT_FILE_OLD_NAME, FLAT_FILE_CREATED, FLAT_FILE_CATID)" +
 "VALUES(?,?,?,?,?)";		

@Override
public void svFlatFile(final List<FlatFile> flat) {
	log.info("Save Flat File");
	for(FlatFile f : flat){
		System.out.println(f.getClobFile().length + " : " + f.getNewName() + " : " + f.getOldName());
	}
	//
	
	flatfileID = Long.MAX_VALUE;
	if(flatfileID != 0){
		String sqlMaxID = "Select MAX(flat_file_id) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".flat_file " ;
		flatfileID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
		System.out.println("This is what here : " + flatfileID);
		}		
	
	//
		
	JdbcTemplate template = getJdbcTemplate();	
	template.batchUpdate(SQL_INSERT_FLATFILE, new BatchPreparedStatementSetter() {

		@Override
		public void setValues(PreparedStatement arg0, int arg1)
				throws SQLException {
			FlatFile ffile =  flat.get(arg1);
			arg0.setBytes(1, ffile.getClobFile());
			arg0.setNString(2, ffile.getNewName());
			arg0.setNString(3, ffile.getOldName());
			arg0.setDate(4, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			arg0.setLong(5, categoryID);
		}
		
		@Override
		public int getBatchSize() {
			return flat.size();
		}
    });
	
}

@Override
public void svPassID(Category category) {
	categoryID = category.getCatID();
	System.out.println("The id is: " + categoryID);	
}

final static private String SQL_INSERT_BUSINESSFILE =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".businessdev " +
		" (BUSINESSDEV_FIRSTNAME,BUSINESSDEV_LASTNAME,BUSINESSDEV_PHONENUMBER, BUSINESSDEV_EMAIL, FLAT_ID) " +
		" VALUES (?,?,?,?,?)";

@Override
public void svBusFile(final List<BusinessRecord> br) {
	log.info("Save Buisness Excel");
	JdbcTemplate template = getJdbcTemplate();
	template.batchUpdate(SQL_INSERT_BUSINESSFILE, new BatchPreparedStatementSetter() {

		
		@Override
		public void setValues(PreparedStatement arg0, int arg1)
				throws SQLException {
			BusinessRecord brecord = br.get(arg1);
			arg0.setNString(1, brecord.getBusinessFirstName());
			arg0.setNString(2, brecord.getBusinessLastName());
			arg0.setNString(3, "555-55-5555");
			arg0.setNString(4, brecord.getBusinessEmail());
			arg0.setLong(5, flatfileID);
			
		}
		
		@Override
		public int getBatchSize() {
			return br.size();
		}
		
	});
}

final static private String SQL_INSERT_CONTRACTFILE =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".contracts " +
		" (CONTRACT_TITLE,CONTRACT_AGENCY,CONTRACT_DATE,FLATFILE_ID) " +
		" VALUES (?,?,?,?)";

@Override
public void svContFIle(final List<ContractRecord> cr) {
	log.info("Save Contracts Excel");
	
	JdbcTemplate template = getJdbcTemplate();
	template.batchUpdate(SQL_INSERT_CONTRACTFILE, new BatchPreparedStatementSetter() {

		
		@Override
		public void setValues(PreparedStatement arg0, int arg1)
				throws SQLException {
			ContractRecord crecord = cr.get(arg1);
			arg0.setNString(1, crecord.getContractTitle());
			arg0.setNString(2, crecord.getContractAgency());
			arg0.setDate(3, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			arg0.setLong(4, flatfileID);
			
		}
		
		@Override
		public int getBatchSize() {
			
			return cr.size();
		}
		
	});
	
	
	
	
}

}//end class


	