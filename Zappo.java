import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
class ProDetail{ // To store product details
	int productID;
	float productPrice;
}
public class Zappo {
	static HashMap<String,Integer> hm=new HashMap<String,Integer>(); // to store the product combination within the specified price 
	static int numberOfProducts; //Total combination
	static float totalPrice; //Total Price
    public static void main(String[] args) {  
    	Scanner sc=new Scanner(System.in);
    	Random rd=new Random();
    	ProDetail pd[]=new ProDetail[100]; 
    	System.out.print("Enter total number of products to be searched: ");
        numberOfProducts = sc.nextInt();
        System.out.print("Enter total price: ");
        totalPrice=sc.nextFloat();
        sc.close();
        if(numberOfProducts>100){ 
        	System.out.println("Exceeded total available products.");
        	return;
        }
        for(int i=0;i<100;i++){ //initializing the product details with random data 
        	pd[i]=new ProDetail();
        	pd[i].productID=i+1;
        	pd[i].productPrice=rd.nextInt((int)totalPrice)+1;
        }
        ProDetail data[]=new ProDetail[numberOfProducts];
        for(int i=0;i<numberOfProducts;i++)
        	data[i]=new ProDetail();
        comb(pd,data,0,pd.length-1,0,numberOfProducts); //get all the combination
        int maxValue=0;
        String maxkey="";
        System.out.println("Top 5 records: ");
        for(int i=0;i<5;i++){ //search for top 5 combinations
        	for(String a:hm.keySet()){
        		if(maxValue<hm.get(a)){
        			maxkey=a;
        			maxValue=hm.get(a);
        		}
        	}
        	if(maxValue!=0){
        		System.out.println("Product ID combination: " + maxkey);
        		System.out.println("Products Value: " + maxValue);
        		hm.remove(maxkey);
        		maxkey="";
        		maxValue=0;
        	}
        	else{
        		break;
        	}
        }
    }  
    static void comb(ProDetail arr[], ProDetail d[], int s, int e, int id, int r)
    {
        if (id == r) // for a combination
        {
        	int sum=0,j;
        	String key="";
            for (j=0; j<r; j++)
            {
            	if(sum>=totalPrice) //check for totalprice
            		break;
            	sum+=d[j].productPrice;
            	key+=d[j].productID + " ";
            }
            if(sum<=totalPrice && j==r){
            	hm.put(key, sum);
            }
            return;
        }
        for (int i=s; i<=e && e-i+1 >= r-id; i++)
        {
            d[id] = arr[i];
            comb(arr, d, i+1, e, id+1, r); //recursively call
        }
    }
}
