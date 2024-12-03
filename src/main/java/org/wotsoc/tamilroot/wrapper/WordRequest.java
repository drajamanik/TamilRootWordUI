/**
 * @author Rajamani David
 * @since	Apr 30, 2019
 *
 */
package org.wotsoc.tamilroot.wrapper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.wotsoc.nlp.parser.TamilRootWordParser;

/**
 * @author rdavid
 *
 */
public class WordRequest {

	public WordRequest() throws Exception{
		new TamilRootWordParser();
		//Thread.sleep(20000);
	}
	
	public void runSingeThread(List<String> wordList){
		
	}

	Map<String, List<String[]>> resultMaps = new ConcurrentHashMap<String, List<String[]>>();
	public void request(String requestId, String wordString){
		resultMaps.get(requestId);
	}

	/**
	 * Executor Service and response map should be 
	 * */
	static Map<Long,WordResponse> wordResponseMap = new ConcurrentHashMap<Long,WordResponse>();
	static ExecutorService executorService = Executors.newCachedThreadPool();

	/**
	 * Use this method create new request with id and word string
	 * */
	public WordResponse createRequest(long requestId, String requestWord){
		WordResponse wp = new WordResponse(requestId,requestWord);
		wordResponseMap.put(requestId,wp);
		WordTask wt =new WordTask(wp);
		executorService.execute(wt);
		return wp;
	}
	
	public String getResponse(Long requestId){
		WordResponse wp = wordResponseMap.get(requestId);
		if(requestId!=null)
			return wp.toJSON();
		return requestId+"";
	}
	
	public Map<String, List<String[]>> getResponseMap(Long requestId){
		WordResponse wp = wordResponseMap.get(requestId);
		return wp.getResultMaps();
	}

	public static void main(String args[]) throws Exception{
		StringBuilder str= new StringBuilder();
		str.append("இன்றைக்கு சோழ நாட்டு மக்கள் இவர் பெயரைக் கூறி வாழ்த்துகிறார்கள். இவரைச் சிங்காதனம் ஏற வேண்டும் என்று வற்புறுத்துகிறார்கள். பொது ஜனங்களின் மனம் இப்படியே என்றுமிருக்குமா? மக்களின் உள்ளம் அடிக்கடி சலிக்கும் இயல்புடையதல்லவா? இதே ஜனங்கள் நாளைக்கு இவர் பேரிலேயே பழி கூறினாலும் கூறுவார்கள். சிங்காதனம் ஏறுவதற்காகச் சித்தப்பன் மதுராந்தகனைக் கொலை செய்வித்த பாதகன் என்று சொன்னாலும் சொல்வார்கள். ஏன்? கடம்பூர் மாளிகையில் ஆதித்த கரிகாலர் இறந்ததற்குக் கூடத் தம் பேரில் பழி சுமத்தினாலும் சுமத்துவார்கள்.தெய்வமே! இத்தகைய பயங்கரமான பழிகளைச் சுமப்பதற்காகவா என்னைக் காவேரியில் முழுகிச் சாகாமல் மந்தாகினி தேவி காப்பாற்றினாள்? இன்று தெய்வமாகியிருக்கும் அந்தப் பெண்ணரசிதான் இந்த இக்கட்டான நிலையிலிருந்து என்னைக் காப்பாற்ற வேண்டும். வாழ்க்கையில் ஒருவன் அடையக்கூடிய அபகீர்த்திகளில் மிகக் கொடிய அபகீர்த்தி என்னைச் சாராமல் தடுத்து அருள் புரிய வேண்டும்.");
		str.append("ஈழநாட்டு இராஜ வம்சத்தில் நெருங்கிய உறவினர் ஒருவரையொருவர் கொன்று விட்டுச் சிங்காதனம் ஏறிய வரலாறுகள் அருள்மொழிவர்மனின் உள்ளத்தில் பதிந்திருந்தன. ஆகையால் அம்மாதிரியான இழிவைத் தரும் அபகீர்த்தி தன்னை யும் அடையலாம் என்ற எண்ணமே அவருக்குச் சகிக்க முடியாத வேதனையை உண்டாக்கிற்று. யாரிடமாவது தம் அந்தரங்கத்தைச் சொல்லி யோசனை கேட்கலாம் என்றால், அதற்கும் தகுதியுள்ளவராக யாரும் தென்படவில்லை. அவரைச் சுற்றியுள்ளவர்கள் அனைவருமே அவருக்கு விரோதமாகச் சதி செய்கிறார்கள் என்று தோன்றியது. அவர்களில் சிலர் உண்மையாகவே அவரிடம் விரோத பாவம் கொண்டிருந்தார்கள். மற்றும் சிலர் அவருக்கு நன்மை செய்வதாக எண்ணிக் கொண்டு பயங்கரமான பழியை அவர் தலையில் சுமத்திவிடுவதற்குப் பிரயத்தனம் செய்து கொண்டிருந்தார்கள்."); 
		str.append("இந்த நிலைமையில் யாரை நம்புவது, தம் அந்தரங்கத்தை யாரிடம் வெளியிட்டு ஆலோசனை கேட்பது என்று அவரால் நிர்ணயம் செய்யமுடியவில்லை. ஏன்? அவரிடம் இணையில்லா அன்பு கொண்டவரும் அவருடைய பக்திக்கு உரியவருமான தமக்கை குந்தவைப் பிராட்டியிடங்கூட அவருக்கு நம்பிக்கை குன்றிவிட்டது. அவரும் தனக்குத் தெரியாமல் ஏதோ காரியம் செய்து கொண்டிருப்பதாகத் தோன்றியது. ஏன்! அவருடைய உயிருக்கு உயிரான வானதிகூட அல்லவா அவருக்குத் தெரியாமல் எதையோ மறைத்து வைக்கப் பார்க்கிறாள்? திருட்டுத்தனமாக எங்கேயோ போய்விட்டு மர்மமான முகபாவத்துடன் திரும்பி வருகிறாள்?...."); 

		WordRequest ll1 = new WordRequest();
		ll1.createRequest(1,str.toString());
//		WordRequest ll2 = new WordRequest();
//		ll2.createRequest("2",str.toString());
		
		Thread.sleep(5000);
		for(int i=0;i<10;i++){
			Long requestId =0l;
			if(i%2==0){
				requestId=1l;
				ll1.getResponse(requestId);
			}
//			else{
//				requestId="1";
//				ll1.getResponse(requestId);
//			}
			Thread.sleep(5000);
		}
	}
}
