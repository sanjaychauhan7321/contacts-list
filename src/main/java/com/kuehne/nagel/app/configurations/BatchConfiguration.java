package com.kuehne.nagel.app.configurations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import com.kuehne.nagel.app.helpers.ContactItemProcessor;
import com.kuehne.nagel.app.models.Contact;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	ResourceLoader resourceLoader;

	@Bean
	public FlatFileItemReader<Contact> reader() {

		return new FlatFileItemReaderBuilder<Contact>().name("contactItemReader")
				.resource(new PathResource(new File("corrected_people.csv").getAbsolutePath())).delimited()
				.names(new String[] { "name", "url" }).fieldSetMapper(new BeanWrapperFieldSetMapper<Contact>() {
					{
						setTargetType(Contact.class);
					}
				}).build();
	}

	@Bean
	public ContactItemProcessor processor() {
		return new ContactItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Contact> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Contact>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO TBL_CONTACTS (NAME,URL) " + "VALUES (:name, :url)").dataSource(dataSource).build();
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("firstJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Contact> writer) {

		processAndCorrectCSVData();
		return stepBuilderFactory.get("step1").<Contact, Contact>chunk(100).reader(reader()).processor(processor())
				.writer(writer).build();
	}

	private void processAndCorrectCSVData() {

		PrintWriter printWriter = null;
		Resource resource = resourceLoader.getResource("classpath:people.csv");

		String rawData = "";
		try {
			InputStream inputStream = resource.getInputStream();
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			rawData = new String(bdata, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// create corrected file
		try {
			File correctedCsv = new File("corrected_people.csv");
			correctedCsv.delete();
			correctedCsv.createNewFile();
			printWriter = new PrintWriter(correctedCsv);

		} catch (IOException e1) {
			// Do Nothing
			e1.printStackTrace();
		}
		String correctedData = "";
		if(rawData.trim().length() != 0){
			
			if (rawData.contains("name,url,")) {
				rawData = rawData.replace("name,url,\\s\n", "");
			}
		    
			rawData = rawData.replaceAll(",\\s\n", "\n");
			
			if (rawData.contains(", Jr.,")) {
				rawData = rawData.replaceAll(", Jr.,", " Jr.,");
			}
			if (rawData.contains(", Sr.,")) {
				rawData = rawData.replaceAll(", Sr.,", " Sr.,");
			}
		}
		
		correctedData = rawData.trim();

		printWriter.write(correctedData);

		if (null != printWriter) {
			printWriter.flush();
			printWriter.close();
		}

	}

}