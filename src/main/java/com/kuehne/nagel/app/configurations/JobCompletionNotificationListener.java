package com.kuehne.nagel.app.configurations;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kuehne.nagel.app.models.ContactEntity;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

			jdbcTemplate
					.query("SELECT NAME, URL FROM TBL_CONTACTS",
							(rs, row) -> new ContactEntity(rs.getString(1), rs.getString(2)))
					.forEach(contactEntity -> System.out.println("Found <" + contactEntity + "> in the database."));
		}
	}
}