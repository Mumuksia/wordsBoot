package com.muksia.model;

import java.util.Date;

import org.bson.Document;
import org.springframework.data.annotation.Id;

/**
 * @author yurzav
 */
public class Word {

	@Id
	private String id;

	final private String value;

	final private String translation;

	final private Date updated;

	public Word(final String value, final String translation, final Date updated) {
		this.value = value;
		this.translation = translation;
		this.updated = updated;
	}

	public Document createDocument() {
		return new Document("word",
							new Document()
									.append("value", value)
									.append("translation", translation)
									.append("updated", updated));
	}

	public String getValue() {
		return value;
	}

	public String getTranslation() {
		return translation;
	}


}
