package com.muksia.model;

import org.springframework.data.annotation.Id;

/**
 * @author yurzav
 */
public class Word {

	@Id
	private String id;

	final private String value;

	final private String translation;

	final private String personName;

	public Word(final String value, final String translation, final String personName) {
		this.value = value;
		this.translation = translation;
		this.personName = personName;
	}

	public String getValue() {
		return value;
	}

	public String getTranslation() {
		return translation;
	}

	public String getPersonName() {
		return personName;
	}
}
