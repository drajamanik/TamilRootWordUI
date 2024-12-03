package org.wotsoc.tamilroot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wotsoc.tamilroot.dao.EmployeeDAO;
import org.wotsoc.tamilroot.model.Word;
import org.wotsoc.tamilroot.model.WordForm;
import org.wotsoc.tamilroot.wrapper.WordRequest;
import org.wotsoc.tamilroot.wrapper.WordResponse;

@RestController
@RequestMapping("/word")
public class MainRESTController {
  
    @Autowired
    private EmployeeDAO employeeDAO;
    Map<Long,WordResponse> responseGlobalMap = new ConcurrentHashMap<Long,WordResponse>();
   
	@RequestMapping(value = "/read", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Word> getWords(@RequestParam("uid") Long uid) throws Exception {
    	System.out.println("Now:"+uid);
    	List<Word> wordList = new ArrayList<Word>();
    	if(responseGlobalMap!=null && responseGlobalMap.size()>0 && responseGlobalMap.get(uid)!=null) {
	    	WordResponse  wordResp= responseGlobalMap.get(uid);
	    	Map<String, List<String[]>> mapWordResp = wordResp.getResultMaps();
	    	
			Iterator<String> iter= mapWordResp.keySet().iterator();
			String key ="";
			Word<Integer> word = null;
			int wordCount = 0;
			while(iter.hasNext()) { 
				key = iter.next();
				if(key!=null) {
					List<List<String>> valueListOfList = wordResp.getWordMapOfListOfList(key);
					if(valueListOfList!=null && valueListOfList.size()>0) {
						for(List<String> valueList:valueListOfList) {
							if(valueList.size()>0) {
								word = new Word<Integer>(key,valueList,wordResp.getWordSortOrder(key));
//								System.out.println("$$$$$$$$$$$$$$$$$$"+key+":"+wordResp.getWordSortOrder(key));
								wordList.add(word);
							}
						}
						wordCount ++;
					}
 				}
			}
			System.out.println("$$$$$$$$$$$$$$$$$$"+wordCount+":"+wordResp.getWordCount());
			if(wordResp.getWordCount() == wordCount) {
				wordList.add(new Word<Integer>("Done",null,wordList.size()+1));
	    	}
			Collections.sort(wordList);
    	}else {
			wordList.add(new Word<Integer>("Done",null,1));
    	}
        return wordList;
    }

    @RequestMapping(value = "/add", //
            method = RequestMethod.POST) //,
            //produces = { MediaType.APPLICATION_JSON_VALUE, //
            //        MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    //public void addWord(@RequestBody  @RequestParam(name="uid") String uid,@RequestParam(name="sentence") String sentence) throws Exception {
    public void addWord(@RequestBody  WordForm wordForm) throws Exception {
    	//WordForm wordForm = new WordForm(uid+"",sentence);
        System.out.println("(Service Side) Creating employee with empNo: " + wordForm.getSentence());
        //wordForm.setSentence("இன்றைக்கு சோழ நாட்டு மக்கள் இவர் பெயரைக் கூறி வாழ்த்துகிறார்கள். இவரைச் சிங்காதனம் ஏற வேண்டும் என்று வற்புறுத்துகிறார்கள். பொது ஜனங்களின் மனம் இப்படியே என்றுமிருக்குமா? மக்களின் உள்ளம் அடிக்கடி சலிக்கும் இயல்புடையதல்லவா? இதே ஜனங்கள் நாளைக்கு இவர் பேரிலேயே பழி கூறினாலும் கூறுவார்கள். சிங்காதனம் ஏறுவதற்காகச் சித்தப்பன் மதுராந்தகனைக் கொலை செய்வித்த பாதகன் என்று சொன்னாலும் சொல்வார்கள். ஏன்? கடம்பூர் மாளிகையில் ஆதித்த கரிகாலர் இறந்ததற்குக் கூடத் தம் பேரில் பழி சுமத்தினாலும் சுமத்துவார்கள்.தெய்வமே! இத்தகைய பயங்கரமான பழிகளைச் சுமப்பதற்காகவா என்னைக் காவேரியில் முழுகிச் சாகாமல் மந்தாகினி தேவி காப்பாற்றினாள்? இன்று தெய்வமாகியிருக்கும் அந்தப் பெண்ணரசிதான் இந்த இக்கட்டான நிலையிலிருந்து என்னைக் காப்பாற்ற வேண்டும். வாழ்க்கையில் ஒருவன் அடையக்கூடிய அபகீர்த்திகளில் மிகக் கொடிய அபகீர்த்தி என்னைச் சாராமல் தடுத்து அருள் புரிய வேண்டும்.");
        if(wordForm.getSentence()!=null && !wordForm.getSentence().trim().equals("")) {
	        WordRequest wr = new WordRequest();
			responseGlobalMap.put(wordForm.getUId(),wr.createRequest(wordForm.getUId(),wordForm.getSentence()));
        }
    }
}