package apriorigeneric; 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.util.*;

import javax.swing.UIManager; 
/** * * @author CHANDINI 
*/ public class AprioriBE4772 { 
/** 
* @param args the command line arguments 
*/ 
	String finalOutput="";
FileReader fr; 
BufferedReader br; 
String filePath = "C:/Users/Chandini Ganesan/eclipse-workspace/apriorigeneric/src/Db1.txt"; //Default if no file given by user
float sT; 
int tCount; 
List<DescribeItem> freqItems = new ArrayList<>(); 
List<DescribeItem> assoList = new ArrayList<>(); 
public AprioriBE4772() 
{ 
} 
public AprioriBE4772(float st) 
{ 
sT = st; 
} 
public AprioriBE4772(float st,String s) 
{ 
sT = st; 
filePath="C:\\Users\\Chandini Ganesan\\eclipse-workspace\\apriorigeneric\\src\\"+s+".txt";
} 
public List<DescribeItem> myOccurances(List<DescribeItem> tL) 
{ 
List<DescribeItem> tL1 = new ArrayList<>(); 
String line; 
String[] temp; 
int flag; 

try{ 
fr = new FileReader(filePath); 
br = new BufferedReader(fr); 
while((line = br.readLine())!=null) 
{ 
for(DescribeItem id: tL) 
{ 
temp = id.item.split(","); 
flag= 0; 
for(int i =0; i<temp.length;i++) 
{ 
if(line.contains(temp[i])) 
flag=1; 
else 
{ 
flag =0; 
break; 
} 
} 
if(flag==1) 
{ 
id.count++; 
} 
} 
} 
br.close(); 
} 
catch(Exception e) 
{ 
	finalOutput=e.toString();

} 
for(int i =0;i<tL.size();i++) 
{ 
tL.get(i).support = supportCal(tCount,tL.get(i).count); 
} 
tL1=supportThres(tL); 
return tL1; 
} 
public static float supportCal(float tcount,float cnt) 
{ 
float support; 
support = (cnt/tcount)*100; 
return support; 
} 
public static int getThreshold(float sT,float support) 
{ 
if(support<sT) 
return -1; 
return 0; 
} 
public String inOrder(String str1,String str2) 
{ 
String str = new String(); 
String[] temp; 
if(str1.compareTo(str2)<0) 
{ 
str = str1+","+str2; 
} 
else if(str1.compareTo(str2)>0) 
{ 
str = str2+","+str1; 
} 
else if(str1.compareTo(str2)==0) 
{ 
str = str1; 
} 
String tempStr; 
temp = str.split(","); 
tempStr = temp[0]+","; 
Arrays.sort(temp); 

for(int i =0;i<temp.length;i++) 
{ 
if(!tempStr.contains(temp[i])) 
{ 
tempStr = tempStr+temp[i]+","; 
} 
} 

return tempStr.substring(0, tempStr.length()-1); 
} 
public List<DescribeItem> supportThres(List<DescribeItem> tLC) 
{ 
int flag1=0; 
String temp; 
for( int i=tLC.size()-1;i>=0;i--) 
{ 
flag1 = getThreshold(sT,tLC.get(i).support); 
temp = tLC.get(i).item; 
if(flag1 == -1) 
{ 
tLC.remove(tLC.get(i)); 
} 

} 
return tLC; 
} 
public void displayList(List<DescribeItem> tL) 
{ 
for(DescribeItem id:tL) 
{ 
	finalOutput=finalOutput+id.item+" \t\t "+(int)id.count+"\t"+(int)id.support+"\n";

} 
} 
public List<DescribeItem> getCombo(List<DescribeItem> tL1,List<DescribeItem> tL2) 
{ 
List<DescribeItem> tLC = new ArrayList<>(); 
List<DescribeItem> tLC1 = new ArrayList<>(); 
List<String> items = new ArrayList<>(); 
String str1,str2,str3; 
for(DescribeItem id:tL1) 
{ 
str1 = id.item; 
for(DescribeItem id2:tL2) 
{ 
str2 = id2.item; 
if(!(str1.equals(str2))) 
{ 
str3 = inOrder(str1,str2); 
if(!items.contains(str3)) 
{ 
items.add(str3); 
} 
} 
} 
} 
for(String s:items) 
{ 
DescribeItem a=new DescribeItem(); 
a.item = s; 
a.count = 0; 
a.support=0; 
tLC.add(a); 
} 
displayList(myOccurances(tLC)); 
for(DescribeItem id :tLC) 
{ 
freqItems.add(id); 
} 
return tLC; 
} 
public List<DescribeItem> getTranscDetail() 
{ 
List<DescribeItem> transactionList = new ArrayList<>(); 
String line = new String(); 
int i,flag,flag1; 
tCount = 0; 
try{ 
fr = new FileReader(filePath); 
br = new BufferedReader(fr); 
while((line = br.readLine())!=null){ 
tCount++; 
String[] itemsList = line.split(","); 
for(int k=1;k<itemsList.length;k++){ 
flag=0; 
for(DescribeItem id : transactionList) 
{ 
	
if(id.item.contentEquals(itemsList[k])) 
{ 
id.count++; 
flag = 1; 
} 
} 
if(flag==0) 
{ 
DescribeItem a = new DescribeItem(itemsList[k],1,0); 
transactionList.add(a); 
} 
} 
} 
finalOutput=finalOutput+"\nItem \t\t Count \t Support\n";

displayList(transactionList); 
for(DescribeItem id:transactionList) 
{ 
id.support = supportCal(tCount,id.count); 
} 
String temp; 
for(i=0;i<transactionList.size();i++) 
{ 
flag1 = getThreshold(sT,transactionList.get(i).support); 
if(flag1 == -1) 
{ 
temp = transactionList.get(i).item; 
transactionList.remove(i); 
} 
flag1=0; 
} 
finalOutput=finalOutput+"\nItem \t\t Count \t Support"+"\n";

} 
catch(Exception e) 
{
finalOutput=e.toString();	

} 
return transactionList; 
} 
public void AssociateRule(float conThres) 
{ 
String temp,temp1,temp2,swaptemp; 
String[] tempArr1,tempArr2; 
int t=0; 
for(DescribeItem id: freqItems) 
{ 
int i; 
DescribeItem a = new DescribeItem(); 
DescribeItem b = new DescribeItem(); 
temp = id.item; 
t=temp.lastIndexOf(","); 
i=temp.indexOf(",",0); 
if(i==t) 
{ 
temp1 = temp.substring(0, i); 
temp2 = temp.substring(i+1); 
a.item = temp1+"->"+temp2; 
b.item = temp2+"->"+temp1; 
List<DescribeItem> supList = new ArrayList<>(); 
DescribeItem obj = new DescribeItem(temp1,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp2,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = myOccurances(supList); 
float con; 
con = (supList.get(2).support*100)/supList.get(0).support; 
if(con>=conThres) 
{ 
a.support = con; 
assoList.add(a); 
} 
con = (supList.get(2).support*100)/supList.get(1).support; 
if(con>=conThres) 
{ 
b.support = con; 
assoList.add(b); 
} 
} 
else{ 
i = temp.indexOf(",", 0); 
for(;i!=-1;i=temp.indexOf(",",i+1)) 
{ 
	System.out.println("i:"+i);
DescribeItem c = new DescribeItem(); 
DescribeItem d = new DescribeItem(); 
temp1 = temp.substring(0, i);
temp2 = temp.substring(i+1); 
if(temp1.length()==temp2.length() && temp1.length()>2) 
{ 
	System.out.println("CheckPoint1->"+temp1+":"+temp2);
tempArr1 = temp1.split(","); 
tempArr2 = temp2.split(","); 
swaptemp=tempArr1[1]; 
tempArr1[1]=tempArr2[0]; 
tempArr2[0]=swaptemp; 
temp1=tempArr1.toString(); 
temp2=tempArr2.toString(); 
c.item = temp1+"->"+temp2; 
d.item = temp2+"->"+temp1; 
List<DescribeItem> supList = new ArrayList<>(); 
DescribeItem obj = new DescribeItem(temp1,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp2,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = myOccurances(supList); 
float con; 
con = (supList.get(2).support*100)/supList.get(0).support; 
if(con>=conThres) 
{ 
c.support = con; 
assoList.add(c); 
} 
con = (supList.get(2).support*100)/supList.get(1).support; 
if(con>=conThres) 
{ 
d.support = con; 
assoList.add(d); 
} 
} 
else if(temp1.length()>2 || temp2.length()>2) 
{ 
System.out.println("CheckPoint2->"+temp1+":"+temp2);
DescribeItem e = new DescribeItem(); 
DescribeItem f = new DescribeItem(); 
e.item = temp1+"->"+temp2; 
f.item = temp2+"->"+temp1; 
List<DescribeItem> supList = new ArrayList<>(); 
DescribeItem obj = new DescribeItem(temp1,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp2,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = myOccurances(supList); 
float con; 
con = (supList.get(2).support*100)/supList.get(0).support; 
if(con>=conThres) 
{ 
e.support= con; 
assoList.add(e); 
} 
con = (supList.get(2).support*100)/supList.get(1).support; 
if(con>=conThres) 
{ 
f.support = con; 
assoList.add(f); 
} String chtemp1,chtemp2; 
String[] tmp2,tmp1 ; 
tmp1 = temp1.split(","); 
tmp2 = temp2.split(","); 
System.out.println("CheckPoint->"+tmp1.length+":"+tmp2.length);
if(tmp1.length<tmp2.length) 
{ 
DescribeItem g = new DescribeItem(); 
DescribeItem h = new DescribeItem(); 
g.item = tmp2[0]+"->"+tmp1[0]+","+tmp2[1]; 
h.item = tmp1[0]+","+tmp2[1]+"->"+tmp2[0]; 
supList = new ArrayList<>(); 
temp1=tmp2[0];
temp2=tmp1[0]+","+tmp2[1];
obj = new DescribeItem(temp1,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp2,0,0); 
supList.add(obj); 
obj = new DescribeItem(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = myOccurances(supList); 
con = (supList.get(2).support*100)/supList.get(0).support; 
if(con>=conThres){ 
g.support = con; 
assoList.add(g); 
} 
con = (supList.get(2).support*100)/supList.get(1).support; 
if(con>=conThres) 
{ 
h.support = con; 
assoList.add(h); 
} 
} 

} 
} 
} 
} 
finalOutput=finalOutput+"\nAssociation rules \t confidence\n";
int cc=1;
for(DescribeItem id: assoList) 
{
	finalOutput=finalOutput+cc+":"+id.item+"\t\t"+(int)id.support+"\n";
	cc++;
} 
} 
public String getOutput()
{
	System.out.println(finalOutput);
	return finalOutput;
}
public static void main(String[] args) { 
// TODO code application logic here 
	 try {
	        UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		
	    } catch (Exception e) {
	        e.printStackTrace();}

java.awt.EventQueue.invokeLater(new Runnable() { 
public void run() { 
new AprioriFE4772().setVisible(true); 
} 
}); 
} 
} 
