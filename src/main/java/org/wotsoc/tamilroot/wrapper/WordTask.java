package org.wotsoc.tamilroot.wrapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.wotsoc.nlp.parser.MorphologyParser;
import org.wotsoc.nlp.parser.ParserEnum;
import org.wotsoc.util.WordClass;

/**
 * @author Rajamani David
 * @param <SplitWithDelimiters>
 * @since	Jun 2, 2019
 *
 */
public class WordTask implements Runnable{
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(50);
	Map<WordOrder, List<WordClass>> wordMap = new ConcurrentHashMap<WordOrder, List<WordClass>>(); 
	
	private WordResponse wp;
	 
    public WordTask(WordResponse wp) {
        this.wp = wp;
    }
 
    public WordResponse getWordResponse() {
        return wp;
    }
    
    public void threadPerWord(String word,AtomicInteger counter) {
    	LocalDateTime now = LocalDateTime.now();
        LocalDateTime afterOneMinute = now.plusMinutes(5);
  
        Duration duration = Duration.between(now, afterOneMinute);
        long delay = Math.abs(duration.toMillis());
     
        ScheduledFuture<String> result = executor.schedule(new ExecuteWordTask(word,counter), delay, TimeUnit.MILLISECONDS);
	    if(result.isDone() == false) 
	    {
	        System.out.println("====Cancelling the task====");
	        result.cancel(false);
	    }
    }
    
    class ExecuteWordTask implements Callable<String> 
    {
        private final String word;
        private final AtomicInteger counter;
     
        public ExecuteWordTask(String word,AtomicInteger counter) {
            this.word = word;
            this.counter = counter;
        }
     
        @Override
        public String call() throws Exception {
			return "done";
        }
    }
 
    class WordOrder implements Comparable<Integer>{
    	String word;
    	Integer order;
    	public WordOrder(String word,Integer order) {
    		this.word = word;
    		this.order = order;
    	}
		@Override
		public int compareTo(Integer o) {
			return this.compareTo(o);
		}
    }
    
    List<ParserEnum> parserList = new ArrayList<ParserEnum>();
    public void run() {
		MorphologyParser mp = new MorphologyParser();
		parserList.add(ParserEnum.SymbolParser);
		parserList.add(ParserEnum.VerbParser);
		parserList.add(ParserEnum.NounParser);

		try{
	    	mp.buildParser(parserList);
			SplitWithDelimiters swd = new SplitWithDelimiters();
			List<String> wordList = swd.splitWords(wp.getRequestWords());
			wp.setWordCount(wordList.size());
			System.out.println(Thread.currentThread().getName() +":"+wordList);
	    	 
			AtomicInteger counter =new AtomicInteger();
			List<WordOrder> wordOrderList = new ArrayList<WordOrder>();
			for(String word:wordList) {
				WordOrder or = new WordOrder(word,counter.get());
				wordOrderList.add(or);
				wp.setWordSortOrder(counter.get()+":"+word, counter.get());
				wp.setWordKeySortOrder(word, counter.get());
				counter.incrementAndGet();
			}
			
			wordOrderList.stream().parallel().forEach(wordOrder -> {
			    System.out.println(wordOrder);
				try{
					List<WordClass> wcList = wordMap.get(wordOrder);
					if(wcList==null)
						wcList = mp.createParser(wordOrder.word, parserList);
					wordMap.put(wordOrder,wcList);
					
					List<List<String>> splitList = new ArrayList<List<String>>();
					List<List<String>> mergeSplitList = new ArrayList<List<String>>();
					List<String> splitSingleList = new ArrayList<String>();
					//Integer intVal = wp.getWordKeySortOrder(word);
					if(wcList!=null) {
						for(WordClass wc:wcList) {
							splitList.add(wc.getSplittedValToList());
							splitSingleList = wc.getSplittedValToList();
							mergeSplitList.addAll(wc.getRawSplitList());
							//wp.setWordMapOfListOfList(wordOrder.order+":"+wordOrder.word,wc.getRawSplitList());
							wp.setWordMapOfListOfList(wordOrder.order+":"+wordOrder.word,splitList);
						}
						wp.setResultMaps(wordOrder.order+":"+wordOrder.word,new ArrayList<String[]>());
						wp.setWordMapOfList(wordOrder.order+":"+wordOrder.word,splitSingleList);
						System.out.println("********************"+wordOrder.order+":"+wordOrder.word);
					}
				}catch(Exception exp) {
					exp.printStackTrace();
				}
			 });
			wp.completed();
	    } catch (Exception e) {
	    	System.out.println();
	        e.printStackTrace();
	    }
	    System.out.println(Thread.currentThread().getName() +",Completed.");
    }
    
    public List<List<String>> getSplittedList(List<String> valueList) {
    	List<List<String>> listOfList = new ArrayList<List<String>> (); 
		if(valueList.size()==1) {
			listOfList.add(valueList);
		}else {
			int size =valueList.size()/12;
			int count =0;
			for(int i=0; i<size;i++) {
				count = i * 12; 
				listOfList.add(valueList.subList(i+count, i+count+12));
			}
		}
		return listOfList;
    }
}
