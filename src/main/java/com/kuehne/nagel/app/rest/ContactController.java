package com.kuehne.nagel.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuehne.nagel.app.exceptions.RecordNotFoundException;
import com.kuehne.nagel.app.models.ContactDto;
import com.kuehne.nagel.app.services.ContactService;

@RestController
@RequestMapping(value = "/contacts/apis/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {

	@Autowired
	private ContactService service;

	@GetMapping(value = "/searchContact")
	public ResponseEntity<ContactDto> searchContact(@RequestParam("contactName") String contactName,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize)
			throws RecordNotFoundException {

		ContactDto contactsData = service.getContactByName(contactName, pageNo, pageSize);
		return new ResponseEntity<ContactDto>(contactsData, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping(value = "/getContacts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContactDto> getContacts(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "name") String sortBy)
			throws RecordNotFoundException {

		ContactDto contactsData = service.getAllContacts(pageNo, pageSize, sortBy);
		return new ResponseEntity<ContactDto>(contactsData, new HttpHeaders(), HttpStatus.OK);

	}

}
