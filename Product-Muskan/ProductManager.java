import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import java.util.Iterator;
import java.util.StringTokenizer;
 import javax.swing.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muskan
 */
public class ProductManager {
    private final ArrayList<Product> ProductList;
    public static ProductManager instance;
    
    public ProductManager(){
        this.ProductList=new ArrayList<Product>();
        this.init("");
    }
    public static ProductManager getInstanceOfProductManager()
    {
        if(instance==null)
        {
            instance=new ProductManager();
        }
        return instance;
    }
    public boolean search(DefaultListModel<String> df,String x)
    {
        
        for(int i=0;i<ProductList.size();i++)
        {
            if(x.equals(ProductList.get(i).getName()))
            {
                Product p=ProductList.get(i);
                df.add(df.size(), p.getID()+","+p.getName()+","+p.getGroup()+","+p.getPrice()+","+p.getUnitOfMeasurement());
                return true;
            }
        }
        return false;
    }
    public boolean init(String filename)
    {
        boolean result=false;
        FileReader reader = null;
        BufferedReader bufReader = null;
        try{
             reader = new FileReader("C:\\Users\\Muskan\\Documents\\Product.csv");
            bufReader = new BufferedReader(reader);

            bufReader.readLine();
            String oneLine = bufReader.readLine();
                        while(oneLine != null){
                //reader.read() may throw IOException
                System.out.println(oneLine);
                StringTokenizer st = new StringTokenizer(oneLine, ",");
                ArrayList<String> arrayl = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    //System.out.println(st.nextToken());
                    arrayl.add(st.nextToken());              
                }
                System.out.println(arrayl);
                this.ProductList.add(new Product(Integer.parseInt(arrayl.get(0)),arrayl.get(1),arrayl.get(2),Integer.parseInt(arrayl.get(3)),arrayl.get(1)));
                // System.out.println(this.userList);
                oneLine = bufReader.readLine();
                
            }
            
          //System.out.println(userList);
            bufReader.close();
            reader.close();
           

        } catch (FileNotFoundException e) {
                //do something clever with the exception
                System.out.println("File Not Found");
        } catch (IOException e) {
                //do something clever with the exception
                System.out.println("IO Exception");              
        } 
            
        result=true;
        return result;
    }
    
    public List<Product> getProductDetails()
    {
        List<Product> result=null;
        result=Collections.unmodifiableList(this.ProductList);
        return result;
    }
    public boolean addProductDetails(Product newProduct)
    {
        boolean result=false;
        this.ProductList.add(newProduct);
        try (FileWriter writer = new FileWriter("C:\\Users\\Muskan\\Documents\\Product.csv")) {  
            BufferedWriter bw = new BufferedWriter(writer);
            //bw.newLine();
            
            String p="";
            for (Iterator<Product> it = this.ProductList.iterator(); it.hasNext();) {
                String var = it.next().save();
                p=p+var+'\n';
                
            }
            bw.append(p);
            bw.close();
        }
        catch (FileNotFoundException e) {
                //do something clever with the exception
                System.out.println("File Not Found");
        } catch (IOException e) {
                //do something clever with the exception
                System.out.println("IO Exception");              
        } 
        System.out.println("added"+newProduct.toString());
        result=true;
        return result;
    }
    public boolean doHousekeeping(){
        return true;
    }
    public static void main(String args[]) {
       ProductManager mgr=new ProductManager();
        mgr.init("C:\\Users\\Muskan\\Documents\\Product.csv");
        
        JFrame Search = new Search(mgr);
         Search.setVisible(true);
}
}


