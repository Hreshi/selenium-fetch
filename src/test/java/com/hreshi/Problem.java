package com.hreshi;

import java.util.List;

public class Problem {
	String code;
	String link;
	String name;
	List<String> tags;
	Integer rating;
	String solvedBy;

	Problem () {

	}
	public void setCode (String code) {
		this.code = code;
	}
	public void setLink (String link) {
		this.link = link;
	}
	public void setName (String name) {
		this.name = name;
	}
	public void setTags (List<String> tags) {
		this.tags = tags;
	}
	public void setRating (Integer rating) {
		this.rating = rating;
	}
	public void setSolvedBy (String solvedBy) {
		this.solvedBy = solvedBy;
	}
}