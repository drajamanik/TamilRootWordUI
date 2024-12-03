package org.wotsoc.tamilroot.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Word<T> implements Comparable<T>{
	String key;
	Integer order;
	List<String> valueList;
	String  item1;
	String  item2;
	String  item3;
	String  item4;
	String  item5;
	String  item6;
	String  item7;
	String  item8;
	String  item9;
	String  item10;
	String  item11;
	String  item12;
	String  item13;
	String  item14;
	String  item15;
	String  item16;
	
	String  item1_1;
	String  item2_1;
	String  item3_1;
	String  item4_1;
	String  item5_1;
	String  item6_1;
	String  item7_1;
	String  item8_1;
	String  item9_1;
	String  item10_1;
	String  item11_1;
	String  item12_1;
	String  item13_1;
	String  item14_1;
	String  item15_1;
	String  item16_1;

	
	static Map<String,Integer> colMap = new HashMap<String,Integer>();
	static {
		colMap.put("Unknown",0);
		colMap.put("=குறி",-1);
		colMap.put("குறி",0);
		colMap.put("பெயர்",1);
		colMap.put("வினை",2);
		colMap.put("வினை or பெயர்",3);
		colMap.put("வேற்றுமை",4);
		colMap.put("காலம்",5); 
		colMap.put("ஓற்று",6); 
		colMap.put("விகுதி",7); 
		colMap.put("சாரியை",8); 
		colMap.put("பன்மை",9); 
		colMap.put("உடம்படுமெய்",10); 
		colMap.put("ஓற்று",11); 
		colMap.put("ஏகாரம்",12); 
		colMap.put("எதிர்மறை",13); 
		colMap.put("வினா",14); 
		colMap.put("வி. பெ. விகுதி",15); 
		colMap.put("தொ.பெ.விகுதி",16); 	
	}
	
	public Word(){
		
	}

	public Word(String key,List<String> valueList, Integer order){
		this.key = key;
		if(this.key.contains(":"))
			this.key  = this.key.substring(this.key.indexOf(":")+1);
		this.order = order;
		this.valueList = valueList;
		
		if(this.valueList==null)
			return;
		//getPositionValue(valueList);
		getWithOutPositionValue(valueList);
	}
	
	public Object[] getValuesArray(String str) {
		String strArr[]= str.split("\\(");
		if(strArr!=null && strArr.length>1) {
			strArr[1] = strArr[1].replace(")","");
			return new Object[] {colMap.get(strArr[1].trim()),strArr[0].trim()};
		}
		return new Object[] {0,str};
	}
	
	public static List<String> convertList(String str) {
		String strVal[] = str.split(",");
		return Arrays.asList(strVal);
	}
	
	public static void main(String[] args) {
		Word w = new Word();
		//w.getPositionValue(convertList("வாழ்த்து(வினை), கிற்(காலம் ), ஆர்கள்(விகுதி), , , , , , , , , ]"));
		w.getPositionValue(convertList("(=குறி), , , , , , , , , , ,"));
		w.getPositionValue(convertList(".(குறி), , , , , , , , , , ,"));
		w.getPositionValue(convertList("?(குறி), , , , , , , , , , ,"));
		
	}
	
	public void getPositionValue(List<String> valueList) {
		int position = 0;
		String strValue =null;
		Object[] objArr = null;
		for(String str:valueList) {
			objArr = getValuesArray(str);
			if(objArr!=null && objArr.length>0) {
				try {
					position = (Integer)objArr[0];
					strValue = (String)objArr[1];
				}catch(Exception exp) {
					System.out.println("Exception:"+valueList);
				}
				if(position==-1)
					this.key = this.key+strValue+"(இடைவெளி)";
				else if(position==0) {
					if(strValue!=null && !strValue.trim().equals(""))
						this.key = this.key+"("+strValue+")";
				}
				else if(position==1)
					this.item1 = strValue;
				else if(position==2)
					this.item2 = strValue;
				else if(position==3)
					this.item3 = strValue;
				else if(position==4)
					this.item4 = strValue;
				else if(position==5)
					this.item5 = strValue;
				else if(position==6)
					this.item6 = strValue;
				else if(position==7)
					this.item7 = strValue;
				else if(position==8)
					this.item8 = strValue;
				else if(position==9)
					this.item9 = strValue;
				else if(position==10)
					this.item10 = strValue;
				else if(position==11)
					this.item11 = strValue;
				else if(position==12)
					this.item12 = strValue;
				else if(position==13)
					this.item13 = strValue;
				else if(position==14)
					this.item14 = strValue;
				else if(position==15)
					this.item15 = strValue;
				else if(position==16)
					this.item16 = strValue;
			}
		}
	}
	
	public void getWithOutPositionValue(List<String> valueList) {
		String strValue =null;
		int index = 0;
		String item[] = new String[2];
		for(int position=0;position<valueList.size();position++) {
			strValue = valueList.get(position);
			if(position==0){
				fillValues(strValue, item);
				this.item1 = item [0];
				this.item1_1 = item[1];
			}
			else if(position==1) {
				fillValues(strValue, item);
				this.item2 = item [0];
				this.item2_1 = item[1];
			}else if(position==2) {
				fillValues(strValue, item);
				this.item3 = item [0];
				this.item3_1 = item[1];
			}else if(position==3) {
				fillValues(strValue, item);
				this.item4 = item [0];
				this.item4_1 = item[1];
			}else if(position==4) {
				fillValues(strValue, item);
				this.item5 = item [0];
				this.item5_1 = item[1];
			}else if(position==5) {
				fillValues(strValue, item);
				this.item6 = item [0];
				this.item6_1 = item[1];
			}else if(position==6) {
				fillValues(strValue, item);
				this.item7 = item [0];
				this.item7_1 = item[1];
			}else if(position==7) {
				fillValues(strValue, item);
				this.item8 = item [0];
				this.item8_1 = item[1];
			}else if(position==8) {
				fillValues(strValue, item);
				this.item9 = item [0];
				this.item9_1 = item[1];
			}else if(position==9) {
				fillValues(strValue, item);
				this.item10 = item [0];
				this.item10_1 = item[1];
			}else if(position==10) {
				fillValues(strValue, item);
				this.item11 = item [0];
				this.item11_1 = item[1];
			}else if(position==11) {
				fillValues(strValue, item);
				this.item12 = item [0];
				this.item12_1 = item[1];
			}else if(position==12) {
				fillValues(strValue, item);
				this.item13 = item [0];
				this.item13_1 = item[1];
			}else if(position==13) {
				fillValues(strValue, item);
				this.item14 = item[0];
				this.item14_1= item[1];
			}else if(position==14) {
				fillValues(strValue,item);
				this.item15 = item[0];
				this.item15_1 = item[1];
			}else if(position==15) {
				fillValues(strValue, item);
				this.item16 = item[0];
				this.item16_1 = item[1];
			}
		}
	}
	
	private void fillValues(String strValue, String[] item) {
		if(strValue!=null) {
			strValue=strValue.replace("[", "");
			strValue=strValue.replace("]", "");
			int index = strValue.indexOf("/");
			if(index>0) {
				item[0] = strValue.substring(0,index);
				item[1] = strValue.substring(index,strValue.length());
			}else {
				item[0] = "";
				item[1] = "";
			}
		}
	}

	public void getWord(String key,List<String[]> valueList){
		this.key = key;
		
		if(valueList==null)
			return;
		
		for(String[] strArr:valueList) {
			if(strArr!=null && strArr.length>0) 
				this.item1 = strArr[0];
			if(strArr!=null && strArr.length>3)
				this.item2 = strArr[3];
			if(strArr!=null && strArr.length>7)
				this.item3 = strArr[7];
			if(strArr!=null && strArr.length>8)
				this.item4 = strArr[8];
			 
				this.item5 = "";
				this.item6 = "";
				this.item7 = "";
				this.item8 = "";
				this.item9 = "";
				this.item10 = "";
				this.item11 = "";
				this.item12 = "";
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValueList() {
		return valueList;
	}

	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getItem4() {
		return item4;
	}

	public void setItem4(String item4) {
		this.item4 = item4;
	}

	public String getItem5() {
		return item5;
	}

	public void setItem5(String item5) {
		this.item5 = item5;
	}

	public String getItem6() {
		return item6;
	}

	public void setItem6(String item6) {
		this.item6 = item6;
	}

	public String getItem7() {
		return item7;
	}

	public void setItem7(String item7) {
		this.item7 = item7;
	}

	public String getItem8() {
		return item8;
	}

	public void setItem8(String item8) {
		this.item8 = item8;
	}

	public String getItem9() {
		return item9;
	}

	public void setItem9(String item9) {
		this.item9 = item9;
	}

	public String getItem10() {
		return item10;
	}

	public void setItem10(String item10) {
		this.item10 = item10;
	}

	public String getItem11() {
		return item11;
	}

	public void setItem11(String item11) {
		this.item11 = item11;
	}

	public String getItem12() {
		return item12;
	}

	public void setItem12(String item12) {
		this.item12 = item12;
	}

	public String getItem13() {
		return item13;
	}

	public void setItem13(String item13) {
		this.item13 = item13;
	}

	public String getItem14() {
		return item14;
	}

	public void setItem14(String item14) {
		this.item14 = item14;
	}

	public String getItem15() {
		return item15;
	}

	public void setItem15(String item15) {
		this.item15 = item15;
	}

	public String getItem16() {
		return item16;
	}

	public void setItem16(String item16) {
		this.item16 = item16;
	}

	public String getItem1_1() {
		return item1_1;
	}

	public void setItem1_1(String item1_1) {
		this.item1_1 = item1_1;
	}

	public String getItem2_1() {
		return item2_1;
	}

	public void setItem2_1(String item2_1) {
		this.item2_1 = item2_1;
	}

	public String getItem3_1() {
		return item3_1;
	}

	public void setItem3_1(String item3_1) {
		this.item3_1 = item3_1;
	}

	public String getItem4_1() {
		return item4_1;
	}

	public void setItem4_1(String item4_1) {
		this.item4_1 = item4_1;
	}

	public String getItem5_1() {
		return item5_1;
	}

	public void setItem5_1(String item5_1) {
		this.item5_1 = item5_1;
	}

	public String getItem6_1() {
		return item6_1;
	}

	public void setItem6_1(String item6_1) {
		this.item6_1 = item6_1;
	}

	public String getItem7_1() {
		return item7_1;
	}

	public void setItem7_1(String item7_1) {
		this.item7_1 = item7_1;
	}

	public String getItem8_1() {
		return item8_1;
	}

	public void setItem8_1(String item8_1) {
		this.item8_1 = item8_1;
	}

	public String getItem9_1() {
		return item9_1;
	}

	public void setItem9_1(String item9_1) {
		this.item9_1 = item9_1;
	}

	public String getItem10_1() {
		return item10_1;
	}

	public void setItem10_1(String item10_1) {
		this.item10_1 = item10_1;
	}

	public String getItem11_1() {
		return item11_1;
	}

	public void setItem11_1(String item11_1) {
		this.item11_1 = item11_1;
	}

	public String getItem12_1() {
		return item12_1;
	}

	public void setItem12_1(String item12_1) {
		this.item12_1 = item12_1;
	}

	public String getItem13_1() {
		return item13_1;
	}

	public void setItem13_1(String item13_1) {
		this.item13_1 = item13_1;
	}

	public String getItem14_1() {
		return item14_1;
	}

	public void setItem14_1(String item14_1) {
		this.item14_1 = item14_1;
	}

	public String getItem15_1() {
		return item15_1;
	}

	public void setItem15_1(String item15_1) {
		this.item15_1 = item15_1;
	}

	public String getItem16_1() {
		return item16_1;
	}

	public void setItem16_1(String item16_1) {
		this.item16_1 = item16_1;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public int compareTo(T o) {
		return this.order.compareTo(((Word<?>)o).getOrder());
	}
}
