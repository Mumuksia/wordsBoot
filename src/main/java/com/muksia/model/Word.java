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

	@Override
	public String toString() {
		return "Word{" +
			   "value='" + value + '\'' +
			   ", translation='" + translation + '\'' +
			   ", personName='" + personName + '\'' +
			   '}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final Word word = (Word) o;

		if (value != null ? !value.equals(word.value) : word.value != null) {
			return false;
		}
		if (translation != null ? !translation.equals(word.translation) : word.translation != null) {
			return false;
		}
		return personName != null ? personName.equals(word.personName) : word.personName == null;

	}

	@Override
	public int hashCode() {
		int result = value != null ? value.hashCode() : 0;
		result = 31 * result + (translation != null ? translation.hashCode() : 0);
		result = 31 * result + (personName != null ? personName.hashCode() : 0);
		return result;
	}
}
