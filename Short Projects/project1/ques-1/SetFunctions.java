/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aditya
 */
public class SetFunctions {

    /**
     * @param args the command line arguments
     */
    
    public static <T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> out)
    {        
        Iterator<T> itr1 = l1.iterator();
        Iterator<T> itr2 = l2.iterator();
        
        if(itr1.hasNext() && itr2.hasNext())
        {
            T elem1 = itr1.next();
            T elem2 = itr2.next();
            while(true)
            {    
                if(elem2.compareTo(elem1)==0)
                {
                    //if both the elements are equal then add the element to the list
                    out.add(elem1);
                    if(itr1.hasNext())
                        elem1 = itr1.next();
                    else
                        break;
                    if(itr2.hasNext())
                        elem2 = itr2.next();
                    else
                        break;
                }
                else if(elem2.compareTo(elem1)<0)
                { //keep forwarding the pointer till elem2>elem1
                    if(itr2.hasNext())    
                        elem2 =itr2.next();
                    else
                        break;
                }
                else
                {//keep forwarding the pointer till elem1>elem2
                    if(itr1.hasNext())
                        elem1 =itr1.next();
                    else
                        break;
                }
            }
        }
    }
    
    public static <T extends Comparable<? super T>>void union(List<T> l1, List<T> l2, List<T> out)
    {        
        Iterator<T> itr1 = l1.iterator();
        Iterator<T> itr2 = l2.iterator();
        boolean notprocess1=false;
        boolean notprocess2=false;
        
        //if one of the lists is empty then the union is the non empty list
        if(l1.size()==0){
            for( T i:l2){
                out.add(i);
            }
            return;
        }
        if(l2.size()==0){
            for( T i:l1){
                out.add(i);
            }
            return;
        }
        
        if(itr1.hasNext() && itr2.hasNext())
        {
            T elem1 = itr1.next();
            T elem2 = itr2.next();
            int top=-1;
            while(true)
            {    
                if(elem2.compareTo(elem1)<0)//when elem2>elem1 then add elem2 to the out list and forward itr2 pointer
                {
                    if(out.isEmpty() || out.get(out.size()-1).compareTo(elem2)!=0)//check if the out list is empty or the last added element to the list is equal to current element
                    {
                        out.add(elem2);
                    }
                  
                    if(itr2.hasNext())    
                    {
                        elem2 =itr2.next();
                    }
                    else
                    {
                        notprocess2=true;
                        break;
                    }
                }
                else if(elem2.compareTo(elem1)==0)//when elem1==elem2 then add elem1 to the out list and forward itr1 and itr2 pointer
                {
                    if(out.isEmpty() || out.get(out.size()-1).compareTo(elem1)!=0)//check if the out list is empty or the last added element to the list is equal to current element 
                    {
                        out.add(elem1);
                    }    
                    

                    if(itr1.hasNext()){
                        elem1 =itr1.next();
                    }
                    else
                        notprocess1=true;//if the pointer is the last element then we won't process this element at the end
                    
                    if(itr2.hasNext()){
                        elem2 =itr2.next();
                    }
                    else
                        notprocess2=true;//if the pointer is the last element then we won't process this element at the end
                    
                    if(notprocess1 ||notprocess2)//break if one of the lists reaches the end
                        break;
                }
                else
                {   //when elem1 is less than elem2
                    if(out.isEmpty() || out.get(out.size()-1).compareTo(elem1)!=0)
                    {
                        out.add(elem1);
                    }    

                    if(itr1.hasNext())
                    {
                        elem1 =itr1.next();
                    }
                    else
                    {
                        notprocess1=true;
                        break;
                    }
                }
            }
            
            //if itr2 reached the end and the last element is not equal to the duplicate element
            if(!itr2.hasNext() && !notprocess1){
                while(true){  
                    if(out.isEmpty() || out.get(out.size()-1).compareTo(elem1)!=0){
                        out.add(elem1);
                    }
                    if(itr1.hasNext())
                        elem1=itr1.next();
                    else
                        break;
                }
            }
            
            //if itr1 reached the end and the last element is not equal to the duplicate element
            if(!itr1.hasNext() && !notprocess2)
            {
                while(true){
                    if(out.isEmpty() || out.get(out.size()-1).compareTo(elem2)!=0){
                        out.add(elem2);
                    }
                    if(itr2.hasNext())
                        elem2=itr2.next();
                    else
                        break;
                 }
            }
        }
    }
        
    
    public static<T extends Comparable<? super T>>
        void difference(List<T> l1, List<T> l2, List<T> out) {
            Iterator<T> itr1 = l1.iterator();
            Iterator<T> itr2 = l2.iterator();
            if(l2.size()==0||l1.size()==0)//if one of the list is empty then the out list is equal to the first list
            {
                for(T i:l1){
                    out.add(i);
                }
                return;
            }
            if(itr1.hasNext() && itr2.hasNext())
            {
                T elem1 = itr1.next();
                T elem2 = itr2.next();
                int top=-1;
                while(true)
                {    //if elem1<elem2 add it to the out list
                    if(elem1.compareTo(elem2)<0)
                    {
                        out.add(elem1);                 
                        if(itr1.hasNext())    
                        {
                            elem1 =itr1.next();
                        }
                        else
                            break;
                    }
                    else if(elem1.compareTo(elem2)==0){//both the elements are equal
                        //keep on iterating both the list till we either we reach the ends of one of the list 
                        while(itr1.hasNext() && itr2.hasNext() && elem1.compareTo(elem2)==0){
                            elem1=itr1.next();
                            elem2=itr2.next();
                        }
                        if(!itr1.hasNext() || !itr2.hasNext()){
                            break;
                        }
                    }
                    else
                    {   //while elem2<elem1 keep on forwarding the pointer
                        while(itr2.hasNext() && elem1.compareTo(elem2)>0){
                            elem2=itr2.next();
                        }
                        if(!itr2.hasNext())
                        {
                            break;
                        }
                    }
                }
                
                if(!itr2.hasNext()){//reached the end of list2
                    while(true){
                        if(elem1.compareTo(elem2)!=0){//compare both the elements
                            out.add(elem1);
                        }
                        if(itr1.hasNext())
                            elem1=itr1.next();
                        else
                            break;
                    }
                }
            }    
        }
        
    public static void main(String[] args) {
        
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> b=new ArrayList<Integer>();
        ArrayList<Integer>c=new ArrayList<Integer>();
        
        System.out.println("Enter the length of the first list, followed by the integers(separated by space)");
        
        Scanner s = new Scanner(System.in);

        int count = s.nextInt();
        

        for (int i = 0; i < count; i++) {
            if (s.hasNextInt()) {
                a.add(s.nextInt());
            } else {
                System.out.println("You didn't provide enough numbers");
                break;
            }
        }
        
        System.out.println("Enter the length of the second list, followed by the integers(separated by space)");
        s.nextLine(); // throw away the newline.
        count = s.nextInt();
        
        for (int i = 0; i < count; i++) {
            if (s.hasNextInt()) {
                b.add(s.nextInt());
            } else {
                System.out.println("You didn't provide enough numbers");
                break;
            }
        }        
        
        
//        a.add(-1);
//        a.add(2);
//        a.add(4);
//        a.add(5);
//        a.add(7);
        
//        b.add(-1);
//        b.add(2);
//        b.add(3);
//        b.add(5); 
//        b.add(6);
        
        
        //union
        c.clear();
        new SetFunctions().union(a,b,c);
        System.out.println("Union");
        if(c.size()!=0){
            for (Integer i:c){
                System.out.println(i.intValue());
            }
        }
        else
            System.out.println("No union between both the lists");

        
        //intersection
        c.clear();
        System.out.println("Intersection");
        new SetFunctions().intersect(a,b,c);
        if(c.size()!=0){
            for (Integer i:c){
                System.out.println(i.intValue());
            }
        }
        else
            System.out.println("No intersection between both the lists");
        
        
        
        //difference
        c.clear();
        System.out.println("Difference");
        new SetFunctions().difference(a,b,c);
        if(c.size()!=0){
            for (Integer i:c){
                System.out.println(i.intValue());
            }
        }
        else
            System.out.println("All the elements present in the first list are present in the second list");
        
        
        
    }
    
}
