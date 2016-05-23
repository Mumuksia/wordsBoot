package com.muksia.model;

import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;

/**
 *
 * @author yurzav
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Person {

    @Id
    private String id;
    
    final private String name;
    
    final private String comment;
    
    final private List<Word> words;

    public Person(final String name, final String comment, final List<Word> words) {
        this.name = name;
        this.comment = comment;
        this.words = words;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public List<Word> getWords() {
        return words;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.comment);
        hash = 43 * hash + Objects.hashCode(this.words);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        return Objects.equals(this.words, other.words);
    }
    
    
 
    
}
