package com.kuehne.nagel.app.helpers;

import org.springframework.batch.item.ItemProcessor;

import com.kuehne.nagel.app.models.Contact;

public class ContactItemProcessor implements ItemProcessor<Contact, Contact> {

	@Override
	public Contact process(final Contact contact) throws Exception {
		
		final Contact contactEntity = new Contact(contact.getName(),
				contact.getUrl());

		return contactEntity;
	}

}