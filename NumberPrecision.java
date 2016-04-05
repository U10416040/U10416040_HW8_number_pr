import java.util.Scanner;

public class NumberPrecision {
	static float input1,input2;
	static double result;	
	
	//all mark statement for debug
	public static void main(String[] args){		
		Scanner input = new Scanner(System.in);
		divide divi=new divide();
		String smod=new String();
		String realmentisa=new String();
		int prcisionnum,i;		
		
		//input divisor and division
		System.out.print("Enter first number: ");
	    String str1 = input.next();
	    System.out.print("Enter second number: ");
	    String str2 = input.next();
	    
	    //which percision place do you want
	    System.out.print("How much number of precision: ");
	    prcisionnum= input.nextInt();
	    
	    smod=divi.knowmod(str1, str2);	
	    
	    //System.out.println("mod is:" + smod);			    	    	    
	    
	    for(i=0;i<prcisionnum;i++){
	    	smod=smod + "0";
	    }
	    //System.out.println("mod is:" + smod);
	    
	    realmentisa=divi.knowmentisa(smod, str2);
	    
	    //System.out.println("realmentisa is:" + realmentisa);
	    //System.out.println("divi.intcont is:" + divi.intcont);
	    
	    System.out.println(divi.intcont + "." + realmentisa);
	    
		/*
		test calculatemethod=new test();
		
		System.out.print("Enter first number which is smaller: ");
	    input1 = input.nextFloat();
	    System.out.print("Enter second number: ");
	    input2 = input.nextFloat();
	    
	    result=calculatemethod.calculate(input1, input2);
	    
	    System.out.println(result);
	    */
	}
}

/*
class test{
	
	
	double calculate(float num1, float num2){
		return num1/num2;
	}
}
*/

class subfordivid{
	String unsign_add(String a, String b){
		int num1[];
		int num2[];
		int ret[];
		String result=new String();
		int maxlen, len1, len2;
		
		//find the max length
		len1=a.length();
		len2=b.length();
		maxlen=len1;
		if(maxlen<len2)
			maxlen=len2;						
		
		num1=new int[maxlen];
		num2=new int[maxlen];				
		ret=new int[maxlen+1];
			
		//turn string to int and put them in a array
		int i;
		for(i=0;i<=maxlen-1;i++){
			if(len1-i>0)				
				num1[maxlen-1-i]=Integer.valueOf(a.substring(len1-i-1, len1-i));
			else
				num1[maxlen-1-i]=0;	
		}
		
		for(i=0;i<=maxlen-1;i++){
			if(len2-i>0)				
				num2[maxlen-1-i]=Integer.valueOf(b.substring(len2-i-1, len2-i));
			else
				num2[maxlen-1-i]=0;	
		}
		
		//if two number plus together is bigger than 9 need to plus one in the next number
		int j, sum;
		sum=0;
		for(j=maxlen-1;j>=0;j--){
			sum=num1[j]+num2[j]+sum/10;
			ret[j+1]=sum%10;						
		}
		ret[0]=sum/10;
				
		result="";
		for(i=0;i<=maxlen;i++){
			if((ret[0]!=0) || i>0){
				result=result + Integer.toString(ret[i]);
			}			
			
		}
		
		
		return result;
	}
	
	String add(String a, String b){
		int a_sign=0, b_sign=0;//positive
		String a_prx, b_prx;
		String retstr;
		
		a_prx=new String();
		b_prx=new String();
		retstr=new String();
			
		a_prx=a.substring(0, 1);
		b_prx=b.substring(0, 1);			
		
		if(a_prx.compareTo("-")==0)
			a_sign=1;//negative		
		if(b_prx.compareTo("-")==0)
			b_sign=1;//negative				
		
		//different input for different calculate term
		if((a_sign==0 && b_sign==1)){
			b=b.substring(1, b.length());
			retstr=unsign_sub(a,b);
		}
		else if((a_sign==1 && b_sign==0)){
			a=a.substring(1, a.length());
			retstr=unsign_sub(b,a);
		}
		else if(a_sign==0 && b_sign==0){			
			retstr=unsign_add(a,b);
		}
		else if(a_sign==1 && b_sign==1){			
			a=a.substring(1, a.length());
			b=b.substring(1, b.length());
			retstr="-" + unsign_add(a,b);
		}
		
		return retstr;
	}
	
	String unsign_sub(String a, String b){
		int num1[], num2[], tmpnum[];
		int ret[];
		String result=new String();
		int maxlen, len1, len2;
		
		//find the max length
		len1=a.length();
		len2=b.length();
		maxlen=len1;
		if(maxlen<len2)
			maxlen=len2;						
		
		num1=new int[maxlen];
		num2=new int[maxlen];				
		ret=new int[maxlen];
		
		//turn string to int and put them in a array
		int i;
		for(i=0;i<=maxlen-1;i++){
			if(len1-i>0)				
				num1[maxlen-1-i]=Integer.valueOf(a.substring(len1-i-1, len1-i));
			else
				num1[maxlen-1-i]=0;	
		}
		
		for(i=0;i<=maxlen-1;i++){
			if(len2-i>0)				
				num2[maxlen-1-i]=Integer.valueOf(b.substring(len2-i-1, len2-i));
			else
				num2[maxlen-1-i]=0;	
		}
		
		int negflag, comp;
		negflag=0;
		comp=comparestr(a, b);
		if(comp<0){
			tmpnum=num1;
			num1=num2;
			num2=tmpnum;			
			negflag=1;
		}		
		
		//to see if the two number after subtract will it be negative if yes need to borrow 1  
		int j, reg, bor;		
		bor=0;
		for(j=maxlen-1;j>=0;j--){
			reg=num1[j]-bor-num2[j];
			if(reg>=0){
				bor=0;
			}
			else{
				bor=1;
				reg=reg+10;
			}
			ret[j]=reg;									
		}		
		
		
		//print Ans
		if(negflag==1){
			System.out.printf("-");
		}
		int zeroflg;
		zeroflg=1;
		for(i=0;i<maxlen;i++){			
			if(ret[i]!=0){
				zeroflg=0;
			}
			
			if(zeroflg==0 || i==maxlen-1){
				result=result + Integer.toString(ret[i]);
				//System.out.printf("%d", ret[i]);//for debug
			}			 			
		}
						
		return result;
	}
	
	String sub(String a, String b){
		int a_sign=0, b_sign=0;
		String a_prx, b_prx;
		String retstr;
		
		a_prx=new String();
		b_prx=new String();
		retstr=new String();
			
		a_prx=a.substring(0, 1);
		b_prx=b.substring(0, 1);			
		
		if(a_prx.compareTo("-")==0)
			a_sign=1;//negative		
		if(b_prx.compareTo("-")==0)
			b_sign=1;//negative				
		
		//different input for different calculate term		
		if((a_sign==0 && b_sign==1)){
			b=b.substring(1, b.length());
			retstr=unsign_add(a,b);
		}
		else if((a_sign==1 && b_sign==0)){
			a=a.substring(1, a.length());			
			retstr="-" + unsign_add(b,a);			
		}
		else if(a_sign==0 && b_sign==0){			
			retstr=unsign_sub(a,b);
		}
		else if(a_sign==1 && b_sign==1){						
			b=b.substring(1, b.length());
			retstr=add(a,b);
		}
		
		return retstr;
	}
	
	int comparestr(String a, String b){
		String str1, str2;
		int len1,len2;
		int ret;
		ret=0;
		int i;
		int comp;
		
		len1=a.length();
		len2=b.length();			
		
		if(len1>len2){
			ret=1;//str1 bigger than str2 cause the length are different
		}
		
		//same length need to compare
		else if(len1==len2){
			for(i=0;i<len1;i++){
				str1=a.substring(i);
				str2=b.substring(i);
				comp=str1.compareTo(str2);
				if(comp>0){
					ret=1;//str1 bigger than str2
					break;
				}
				else if(comp==0){
					ret=0;//same
					continue;
				}
				else{
					ret=-1;
					break;//str1 smaller than str2
				}
			}
		}
		
		else{
			ret=-1;//str1 smaller than str2
		}
			
		return ret;
	}		
	
	String subdecimal(String a, String b){
		int dotposition_a, dotposition_b;
		String a_intstr=new String();
		String a_mtisastr=new String();
		String b_intstr=new String();		
		String b_mtisastr=new String();
		String a_num=new String();
		String b_num=new String();
		int maxlen, len1, len2;		
		String retstr=new String();
		String retint=new String();
		String retmtisa=new String();
		
		//add .0 to those numbers which are integer
		if(have_dot(a)==0){		
			a=a+".0";
		}
		if(have_dot(b)==0){		
			b=b+".0";
		}
				
		
		//get dot position
		dotposition_a=a.indexOf(".");
		dotposition_b=b.indexOf(".");
		
		a_intstr=a.substring(0, dotposition_a);
		
		a_mtisastr=a.substring(dotposition_a+1, a.length());		
		
		b_intstr=b.substring(0, dotposition_b);
		
		b_mtisastr=b.substring(dotposition_b+1, b.length());							
		
		//get maximum length for putting 0 to the less one
		len1=a_mtisastr.length();
		len2=b_mtisastr.length();		
		maxlen=len1;
		if(maxlen<len2)
			maxlen=len2;							
		
		int i;
		if(maxlen>len1){			
			for(i=len1;i<maxlen;i++){
				a_mtisastr=a_mtisastr + "0";						
			}
		}
		else{
			for(i=len2;i<maxlen;i++){
				b_mtisastr=b_mtisastr + "0";
			}
		}				
		
		a_num=a_intstr + a_mtisastr;
		b_num=b_intstr + b_mtisastr;
		
		retstr=sub(a_num, b_num);	
		retstr.substring(0,1);
		
		//different case like there won't have int part so need to decide
		int retlen=retstr.length();
		if(retlen>maxlen){
			retint=retstr.substring(0, retlen-maxlen);
			retmtisa=retstr.substring(retlen-maxlen, retlen);
			retstr=retint + "." + retmtisa;
		}
		else if(retlen<=maxlen){			
			for(i=0;i<(maxlen-retlen);i++){
					retstr="0" + retstr; 								
			}
			retstr="0." + retstr;
		}				
		
		return retstr;
	}
	
	int have_dot(String a){
		int i;
		char c1;
		
		//to see if there had a dot
		for(i=0;i<a.length();i++){
			c1=a.charAt(i);
			if(c1=='.'){
				return 1;
			}
		}
		return 0;
	}

}

class divide{
	subfordivid sfd=new subfordivid();
	int i,j;
	String cont=new String();
	int compare;
	int intcont;
	
	//this method to know the mod and quotient
	String knowmod(String a, String b){
		intcont=1;
		compare=1;
		
		//System.out.println("hi");
		//to know which number is bigger
		if(comparestr(a,b)<0){
			intcont=0;
			return a;			
		}
		
		//use subtract method
		cont=sfd.sub(a, b);
		while(compare>=0){
			cont=sfd.sub(cont, b);
			compare=comparestr(cont,b);
			//System.out.println("cont:" + cont);
			intcont++;
		}		
		//System.out.println(cont);
		//System.out.println("Quotient:" + intcont);
		return cont;
	}
	
	//know the mentisa
	String knowmentisa(String a, String b){
		String mentisa=new String();
		compare=1;
		mentisa="0";
		
		//System.out.println("hi");

				
		cont=sfd.sub(a, b);
		compare=comparestr(cont,b);
		//the same 
		while(compare>=0){
			cont=sfd.sub(cont, b);
			compare=comparestr(cont,b);			
			//System.out.println(cont);
			mentisa=sfd.add(mentisa, "1");
		}		
		//System.out.println(cont);
		//System.out.println("Quotient:" + intcont);
		
		if(comparestr(cont, "0")==0){
			mentisa=sfd.add(mentisa, "1");
			return mentisa;
		}	
		mentisa=sfd.add(mentisa, "1");
		return mentisa;
		
	}
	
	int comparestr(String a, String b){
		String str1, str2;
		int len1,len2;
		int ret;
		ret=0;
		int i;
		int comp;
		
		len1=a.length();
		len2=b.length();			
		
		if(len1>len2){
			ret=1;//str1 bigger than str2 cause the length are different
		}
		
		//same length need to compare
		else if(len1==len2){
			for(i=0;i<len1;i++){
				str1=a.substring(i);
				str2=b.substring(i);
				comp=str1.compareTo(str2);
				if(comp>0){
					ret=1;//str1 bigger than str2
					break;
				}
				else if(comp==0){
					ret=0;//same
					continue;
				}
				else{
					ret=-1;
					break;//str1 smaller than str2
				}
			}
		}
		return ret;
	}	
}	