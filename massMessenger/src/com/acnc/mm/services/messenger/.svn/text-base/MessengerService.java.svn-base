package com.acnc.mm.services.messenger;


import java.util.List;

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
import com.acnc.mm.domain.messenger.UpdateCandidateInfo;
import com.acnc.mm.domain.messenger.UpdateContract;



/**
 * @author Kunta L.
 *
 */
public interface MessengerService  {
	
	public List<JobRecord> getAllJobs();
    //Populate candidate table
	public void saveCandidate(CandidateInfo candidateInfo);

	public void saveJob(JobRecord jobRecord);

	public void saveEmailQueue(List<EmailRepo> allAddedEmailsInfo);

	public List<EmailRepo> getAllEmailRepos();

	public void deleteCandidate(UpdateCandidateInfo updateCandidateinfo);

	public void updateJob(JobRecord jobRecord);

	public void deleteJob(JobRecord jobRecord);

	public void saveContract(ContractRecord contractRecord);

	public List<ContractRecord> getAllContracts();

	public List<BusinessRecord> getAllBusiness();

	public void saveBusiness(BusinessRecord businessRecord);

	public void updateBusiness(UpdateBusiness updateBusiness);

	public void deleteBusiness(BusinessRecord businessRecord);

	public void deleteContract(ContractRecord contractRecord);

	public void updateContract(UpdateContract updateContract);

	public List<EmailRepo2> getAllEmailRepos2();

	public void saveEmailQueue2(List<EmailRepo2> allAddedEmailsInfo2);
	
	public void saveCandidateInfo(EmployResume employResume);

	public void saveCandidateInfo(EducationResume educationResume);

	public void saveCertResume(CertResume certResume);

	public List<CandidateInfo> getAllCandidateInfo();

	public void saveEmployInfo(List<EmployResume> mainER);

	public void saveCertificationInfo(List<CertResume> cert);

	public void saveEducationInfo(List<EducationResume> empEdu);
	//Resume save candidate wizard
	public void saveCandidateInfo(List<CandidateInfo> cnd);

	public void svcandidateinfo(CandidateInfo candidateInfo);

	public void sveducationinfo(EducationResume educationResume);

	public void svcertresume(CertResume certResume);

	public void svemployresume(EmployResume employResume);

	public List<EducationResume> getAllEducation();

	public List<EmployResume> getAllEmploy();

	public List<CertResume> getAllCert();

	public void getAllCert(CertResume certResume);

	public List<CandidateInfo> getAllQueryRecord();

	public void updateCandidateInfo(CandidateInfo candidateInfo);

	public void updateEducationResume(EducationResume educationResume);

	public void updateCertResume(CertResume certResume);

	public void updateEmployResume(EmployResume employResume);

	public void candID(long passID);

	public List<CertResume> getSelectedCertsInfo(Long passID);

	public List<EducationResume> getSelectedEducationInfo(Long passID);

	public List<EmployResume> getSelectedEmployInfo(Long passID);

	public void updateEmploy(EmployResume employResume);

	public void updateEducation(EducationResume educationResume);

	public void updateCert(CertResume certResume);

	public void upDateCandidate(CandidateInfo candidateInfo);
  
	public void saveFlatFile(List<FlatFile> flat);
	//Pass the ID to match the Category table
	public void passID(Category category);
	//Save Excel sheet info Business Record
	public void saveBusinessFile(List<BusinessRecord> br);
	//Save Excel sheet info Contract File
	public void saveContractFile(List<ContractRecord> cr);

		
}
	
	
