 package apriorigeneric; 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.util.*; 
/** * * @author VIDHYA 
*/ public class AprioriGeneric { 
/** 
* @param args the command line arguments 
*/ 
FileReader fr; 
BufferedReader br; 
String filePath = "C:\\Users\\Chandini Ganesan\\eclipse-workspace\\apriorigeneric\\src\\Db1.txt"; 
float sT; 
int tCount; 
List<ItemDesc> freqItems = new ArrayList<>(); 
List<ItemDesc> assoList = new ArrayList<>(); 
public AprioriGeneric() 
{ 
} 
public AprioriGeneric(float st) 
{ 
sT = st; 
} 
public List<ItemDesc> countOccur(List<ItemDesc> tL) 
{ 
List<ItemDesc> tL1 = new ArrayList<>(); 
String line; 
String[] temp; 
int flag; 
// List<String> itemlists = new ArrayList<>(); 
try{ 
fr = new FileReader(filePath); 
br = new BufferedReader(fr); 
while((line = br.readLine())!=null) 
{ 
for(ItemDesc id: tL) 
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
System.out.println(e); 
} 
for(int i =0;i<tL.size();i++) 
{ 
tL.get(i).support = calc_Support(tCount,tL.get(i).count); 
} 
tL1=supportThres(tL); 
return tL1; 
} 
public static float calc_Support(float tcount,float cnt) 
{ 
float support; 
support = (cnt/tcount)*100; 
return support; 
} 
public static int check_Threshold(float sT,float support) 
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
/* for(String t:temp) 
{ 
System.out.println(".."+t); 
}*/ 
for(int i =0;i<temp.length;i++) 
{ 
if(!tempStr.contains(temp[i])) 
{ 
tempStr = tempStr+temp[i]+","; 
} 
} 
//System.out.println(tempStr); 
return tempStr.substring(0, tempStr.length()-1); 
} 
public List<ItemDesc> supportThres(List<ItemDesc> tLC) 
{ 
int flag1=0; 
String temp; 
for( int i=tLC.size()-1;i>=0;i--) 
{ 
flag1 = check_Threshold(sT,tLC.get(i).support); 
temp = tLC.get(i).item; 
if(flag1 == -1) 
{ 
tLC.remove(tLC.get(i)); 
} 
//tLC.remove(tLC.get(i)) 
} 
return tLC; 
} 
public void showList(List<ItemDesc> tL) 
{ 
for(ItemDesc id:tL) 
{ 
System.out.println(id.item+" \t "+(int)id.count+"\t"+(int)id.support); 
} 
} 
public List<ItemDesc> makeCombinations(List<ItemDesc> tL1,List<ItemDesc> tL2) 
{ 
List<ItemDesc> tLC = new ArrayList<>(); 
List<ItemDesc> tLC1 = new ArrayList<>(); 
List<String> items = new ArrayList<>(); 
String str1,str2,str3; 
for(ItemDesc id:tL1) 
{ 
str1 = id.item; 
for(ItemDesc id2:tL2) 
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
ItemDesc a=new ItemDesc(); 
a.item = s; 
a.count = 0; 
a.support=0; 
tLC.add(a); 
} 
showList(countOccur(tLC)); 
for(ItemDesc id :tLC) 
{ 
freqItems.add(id); 
} 
return tLC; 
} 
public List<ItemDesc> getTransaction() 
{ 
List<ItemDesc> transactionList = new ArrayList<>(); 
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
for(ItemDesc id : transactionList) 
{ 
if(id.item.contentEquals(itemsList[k])) 
{ 
id.count++; 
flag = 1; 
} 
} 
if(flag==0) 
{ 
ItemDesc a = new ItemDesc(itemsList[k],1,0); 
transactionList.add(a); 
} 
} 
} 
System.out.println("\nItem \t Count \t Support"); 
showList(transactionList); 
for(ItemDesc id:transactionList) 
{ 
id.support = calc_Support(tCount,id.count); 
} 
String temp; 
for(i=0;i<transactionList.size();i++) 
{ 
flag1 = check_Threshold(sT,transactionList.get(i).support); 
if(flag1 == -1) 
{ 
temp = transactionList.get(i).item; 
transactionList.remove(i); 
} 
flag1=0; 
} 
System.out.println("\nItem \t\t Count \t Support"); 
} 
catch(Exception e) 
{ 
System.out.println(e); 
} 
return transactionList; 
} 
public void makeAsso(float conThres) 
{ 
String temp,temp1,temp2,swaptemp; 
String[] tempArr1,tempArr2; 
int t=0; 
for(ItemDesc id: freqItems) 
{ 
int i; 
ItemDesc a = new ItemDesc(); 
ItemDesc b = new ItemDesc(); 
temp = id.item; 
t=temp.lastIndexOf(","); 
i=temp.indexOf(",",0); 
if(i==t) 
{ 
temp1 = temp.substring(0, i); 
temp2 = temp.substring(i+1); 
a.item = temp1+"->"+temp2; 
b.item = temp2+"->"+temp1; 
List<ItemDesc> supList = new ArrayList<>(); 
ItemDesc obj = new ItemDesc(temp1,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp2,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = countOccur(supList); 
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
ItemDesc c = new ItemDesc(); 
ItemDesc d = new ItemDesc(); 
temp1 = temp.substring(0, i); 
temp2 = temp.substring(i+1); 
if(temp1.length()==temp2.length() && temp1.length()>2) 
{ 
tempArr1 = temp1.split(","); 
tempArr2 = temp2.split(","); 
swaptemp=tempArr1[1]; 
tempArr1[1]=tempArr2[0]; 
tempArr2[0]=swaptemp; 
temp1=tempArr1.toString(); 
temp2=tempArr2.toString(); 
c.item = temp1+"->"+temp2; 
d.item = temp2+"->"+temp1; 
List<ItemDesc> supList = new ArrayList<>(); 
ItemDesc obj = new ItemDesc(temp1,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp2,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = countOccur(supList); 
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
ItemDesc e = new ItemDesc(); 
ItemDesc f = new ItemDesc(); 
e.item = temp1+"->"+temp2; 
f.item = temp2+"->"+temp1; 
List<ItemDesc> supList = new ArrayList<>(); 
ItemDesc obj = new ItemDesc(temp1,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp2,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = countOccur(supList); 
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
if(tmp1.length<tmp2.length) 
{ 
ItemDesc g = new ItemDesc(); 
ItemDesc h = new ItemDesc(); 
g.item = tmp2[0]+"->"+tmp1[0]+","+tmp2[1]; 
h.item = tmp1[0]+","+tmp2[1]+"->"+tmp2[0]; 
supList = new ArrayList<>(); 
obj = new ItemDesc(temp1,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp2,0,0); 
supList.add(obj); 
obj = new ItemDesc(temp1+","+temp2,0,0); 
supList.add(obj); 
supList = countOccur(supList); 
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
System.out.println("\nAssociation rules \t confidence"); 
for(ItemDesc id: assoList) 
{ 
System.out.println(id.item+"\t\t"+(int)id.support); 
} 
} 
public static void main(String[] args) { 
// TODO code application logic here 
java.awt.EventQueue.invokeLater(new Runnable() { 
public void run() { 
new AprioriG().setVisible(true); 
} 
}); 
} 
} 
