package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobApplication;
import com.skilldistillery.enginex.entities.JobDetail;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.JobApplicationRepository;
import com.skilldistillery.enginex.repositories.JobDetailRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class JobDetailServiceImpl implements JobDetailService {

	@Autowired
	private JobDetailRepository jobDetailRepo;

	@Autowired
	private JobApplicationRepository jobAppRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public JobDetail getJobDetailById(int id) {

		Optional<JobDetail> opt = jobDetailRepo.findById(id);

		if (opt.isPresent()) {
			return opt.get();

		} else {
			return null;
		}

	}

	@Override
	public List<JobDetail> getAllDetails() {

		return jobDetailRepo.findAll();
	}

	@Override
	public JobDetail create(String username, JobDetail jobDetail, int jobAppId) {
		User user = userRepo.findByUsername(username);

		Optional<JobApplication> ja = jobAppRepo.findById(jobAppId);

		if (ja.isPresent() && ja.get().getJobPost().getUser().getId() == user.getId()) {
			JobApplication jobApplication = ja.get();
			jobDetail = jobDetailRepo.saveAndFlush(jobDetail);
			jobApplication.getDetails().add(jobDetail);
			jobAppRepo.saveAndFlush(jobApplication);
			return jobDetail;

		} else {
			return null;
		}

	}

	@Override
	public JobDetail update(String username, JobDetail jobDetail, int jobDetailId) {
		User user = userRepo.findByUsername(username);

		Optional<JobDetail> opt = jobDetailRepo.findById(jobDetailId);
		if (opt.isPresent() && opt.get().getApplication().getJobPost().getUser().getId() == user.getId()) {
			JobDetail dbJobDetail = opt.get();

			dbJobDetail.setComment(jobDetail.getComment());
			dbJobDetail.setFinishDate(jobDetail.getFinishDate());
			dbJobDetail.setStartDate(jobDetail.getStartDate());
			dbJobDetail.setRating(jobDetail.getRating());

			return jobDetailRepo.saveAndFlush(dbJobDetail);

		} else {

			return null;
		}

	}

	@Override
	public boolean delete(String username, int id) {
		User user = userRepo.findByUsername(username);

		Optional<JobDetail> opt = jobDetailRepo.findById(id);

		if (opt.isPresent() && user.getId() == opt.get().getApplication().getJobPost().getUser().getId()) {

			JobDetail jobDetail = opt.get();
			
				Optional<JobApplication> optJobApp = jobAppRepo.findById(jobDetail.getApplication().getId());
				
				JobApplication jobApp = optJobApp.get();
				
				if(jobApp.getDetails().size() > 0) {
					for (JobDetail detail : jobApp.getDetails()) {
						detail = null;
					}
				}
				
				jobAppRepo.saveAndFlush(jobApp);
				
				jobDetailRepo.delete(jobDetail);

			
			return true;
		
		} else {

			return false;

		}

	}
}
