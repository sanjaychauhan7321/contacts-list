package com.kuehne.nagel.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kuehne.nagel.app.exceptions.RecordNotFoundException;
import com.kuehne.nagel.app.models.ContactDto;
import com.kuehne.nagel.app.models.ContactEntity;
import com.kuehne.nagel.app.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository repository;

	public ContactDto getAllContacts(Integer pageNo, Integer pageSize, String sortBy) throws RecordNotFoundException {

		final Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ContactEntity> pagedResult = repository.findAll(pageable);
		ContactDto contactData = new ContactDto();

		if (pagedResult.hasContent()) {

			contactData.setContacts(pagedResult.getContent());
			contactData.setTotalRecords((int) pagedResult.getTotalElements());
			contactData.setCurrentPageIndex(pagedResult.getNumber());
			contactData.setPageSize(pageSize);
			contactData.setTotalPages(pagedResult.getTotalPages());
			contactData.setRecordsOnCurrentPage(pagedResult.getNumberOfElements());
			
			return contactData;
		} else {
			throw new RecordNotFoundException("No contact data is available in Database.");
		}
	}

	public ContactDto getContactByName(String name, Integer pageNo, Integer pageSize) throws RecordNotFoundException {

		final Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<ContactEntity> pagedResult = repository.findByName(name, pageable);
		ContactDto contactData = new ContactDto();

		if (pagedResult.hasContent()) {

			contactData.setContacts(pagedResult.getContent());
			contactData.setTotalRecords((int) pagedResult.getTotalElements());
			contactData.setCurrentPageIndex(pagedResult.getNumber());
			contactData.setPageSize(pageSize);
			contactData.setTotalPages(pagedResult.getTotalPages());
			contactData.setRecordsOnCurrentPage(pagedResult.getNumberOfElements());
			
			return contactData;
		} else {
			throw new RecordNotFoundException("No contact record exist for name : " + name + " in Database.");
		}

	}

}
