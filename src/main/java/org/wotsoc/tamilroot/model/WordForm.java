package org.wotsoc.tamilroot.model;

public class WordForm {
	
	private Long uid;
	private String  sentence;
	
	public WordForm() {
		
	}
	
	public WordForm(String uid,String sentence) {
		this.uid = Long.parseLong(uid);
		this.sentence = sentence;
	}

	public Long getUId() {
		return uid;
	}
	public void setUId(Long uid) {
		this.uid = uid;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
}
