package org.wotsoc.tamilroot.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

/**
 * @author Rajamani David
 * @since	Jun 2, 2019
 *
 */
public class WordResponse {
	private long requestId;
	private long wordCount;
	private boolean statusCompleted =false;
	private String requestWords;
	
	private Map<String,List<String>> wordMapOfList = new ConcurrentHashMap<String,List<String>>();
	private Map<String,List<List<String>>> wordMapOfListOfList = new ConcurrentHashMap<String,List<List<String>>>();
	private Map<String,Integer> wordSortOrder = new ConcurrentHashMap<String,Integer>();
	private Map<String,List<Integer>> wordKeySortOrder = new ConcurrentHashMap<String,List<Integer>>();
	
	private Map<String, List<String[]>> resultMaps = new ConcurrentHashMap<String, List<String[]>>();
	
	public WordResponse(Long requestId,String requestWords){
		this.requestId = requestId;
		this.requestWords = requestWords;
	}
	
	public Map<String, List<String[]>> getResultMaps(){
		return resultMaps; 
	}
	
	public List<String[]> getResultMaps(String word){
		return resultMaps.get(word); 
	}

	public  void setResultMaps(String word,List<String[]> rList){
		if(word!=null && rList!=null)
			this.resultMaps.put(word,rList); 
	}
	
	public List<String> getWordMapOfList(String key){
		return wordMapOfList.get(key);
	}

	public void setWordMapOfList(String key,List<String> wordList){
		this.wordMapOfList.put(key, wordList);
	}
	
	public List<List<String>> getWordMapOfListOfList(String key){
		return wordMapOfListOfList.get(key);
	}
	
	public void setWordMapOfListOfList(String key,List<List<String>> wordListOfList){
		this.wordMapOfListOfList.put(key, wordListOfList);
	}
	
	public Long getRequestId(){
		return requestId;
	}
	
	public String getRequestWords(){
		return requestWords;
	}
	
	public boolean getStatusCompleted(){
		return statusCompleted;
	}
	
	public void completed(){
		this.statusCompleted = true;
	}
	
	public long getWordCount() {
		return wordCount;
	}

	public void setWordCount(long wordCount) {
		this.wordCount = wordCount;
	}

	public Integer getWordSortOrder(String key) {
		String keyArr[]= key.split(":");
		if(keyArr.length>0) {
			return Integer.parseInt(keyArr[0]);
		}
		return 0;
	}

	public void setWordSortOrder(String key,Integer  order) {
		this.wordSortOrder.put(key,order);
	}

	public Integer getWordKeySortOrder(String key) {
		return getWordSortOrder(key);
	}

	public void setWordKeySortOrder(String key, Integer value) {
		List<Integer> intList= this.wordKeySortOrder.get(key);
		if(intList==null)
			intList = new ArrayList<Integer>();
		intList.add(value);
		this.wordKeySortOrder.put(key,intList);
	}

	public String toJSON(){
		return new Gson().toJson(resultMaps.values());
	}
}
