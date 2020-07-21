
//=============================================================================
// PROGRAMMER: Jacob Williams
// PANTHER ID: 6182345
//
// CLASS: COP3337
// SECTION: RVDC
// SEMESTER: Summer 2020
// CLASSTIME: Online
//
// Project: Project 1
// DUE: May 24, 2020 11:59 pm
//
// CERTIFICATION: I understand FIU’s academic policies, and I certify that this 
//                work is my own and that none of it is the work of any other person.
//=============================================================================

package project1;

//--------------------------------------------------
// Imports
//--------------------------------------------------
import java.io.*;
import java.util.*;

//Testing GitHub
public class Project1 {

    public static void main(String[] args) {
        
        // The name of the file to open.
        // notice that the StockPrice_X_Data.txt is in the data package
        String fileName = "src/data/Stock_Data.txt";

        // This will reference one line at a time
        String line = null;
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =  new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            
           //PUT CODE HERE
           ArrayList<Double> x_stockPrices = new ArrayList<>();
           ArrayList<Double> ge_stockPrices = new ArrayList<>();
           ArrayList<Double> appl_stockPrices = new ArrayList<>();
           ArrayList<Double> goog_stockPrices = new ArrayList<>();          
           ArrayList<Double> f_stockPrices = new ArrayList<>();
           ArrayList<Double> x_Correlations = new ArrayList<>();
           ArrayList<Double> ge_Correlations = new ArrayList<>();
           ArrayList<Double> appl_Correlations = new ArrayList<>();
           ArrayList<Double> goog_Correlations = new ArrayList<>();
           ArrayList<Double> f_Correlations = new ArrayList<>();
           
           ArrayList<ArrayList<Double>> correlationMatrix = new ArrayList<ArrayList<Double>>();
           
           int lineCounter = 1; // line counter to know if I working header line
           String delims = "[,]"; // delimiter b/c data is a comma delimited file
           
           while ((line = bufferedReader.readLine())!= null) {
               if(lineCounter != 1){
                   String[] tokens = line.split(delims);
                   
                   x_stockPrices.add(Double.parseDouble(tokens[1]));

                   ge_stockPrices.add(Double.parseDouble(tokens[2]));

                   appl_stockPrices.add(Double.parseDouble(tokens[3]));

                   goog_stockPrices.add(Double.parseDouble(tokens[4]));

                   f_stockPrices.add(Double.parseDouble(tokens[5]));
               }
              lineCounter++;
           }
            // Always close files.
            bufferedReader.close(); 

            //------------------------------------------------------------------
            // Doing some calculations
            //------------------------------------------------------------------
            
			//PUT CODE HERE
        x_Correlations.add(findCorrelation(x_stockPrices,x_stockPrices));                       
        x_Correlations.add(findCorrelation(x_stockPrices,ge_stockPrices));
        x_Correlations.add(findCorrelation(x_stockPrices,appl_stockPrices));
        x_Correlations.add(findCorrelation(x_stockPrices,goog_stockPrices));
        x_Correlations.add(findCorrelation(x_stockPrices,f_stockPrices));
        
        ge_Correlations.add(findCorrelation(ge_stockPrices,x_stockPrices));                       
        ge_Correlations.add(findCorrelation(ge_stockPrices,ge_stockPrices));
        ge_Correlations.add(findCorrelation(ge_stockPrices,appl_stockPrices));
        ge_Correlations.add(findCorrelation(ge_stockPrices,goog_stockPrices));
        ge_Correlations.add(findCorrelation(ge_stockPrices,f_stockPrices));
        
        appl_Correlations.add(findCorrelation(appl_stockPrices,x_stockPrices));                       
        appl_Correlations.add(findCorrelation(appl_stockPrices,ge_stockPrices));
        appl_Correlations.add(findCorrelation(appl_stockPrices,appl_stockPrices));
        appl_Correlations.add(findCorrelation(appl_stockPrices,goog_stockPrices));
        appl_Correlations.add(findCorrelation(appl_stockPrices,f_stockPrices));
        
        goog_Correlations.add(findCorrelation(goog_stockPrices,x_stockPrices));                       
        goog_Correlations.add(findCorrelation(goog_stockPrices,ge_stockPrices));
        goog_Correlations.add(findCorrelation(goog_stockPrices,appl_stockPrices));
        goog_Correlations.add(findCorrelation(goog_stockPrices,goog_stockPrices));
        goog_Correlations.add(findCorrelation(goog_stockPrices,f_stockPrices));
        
        f_Correlations.add(findCorrelation(f_stockPrices,x_stockPrices));                       
        f_Correlations.add(findCorrelation(f_stockPrices,ge_stockPrices));
        f_Correlations.add(findCorrelation(f_stockPrices,appl_stockPrices));
        f_Correlations.add(findCorrelation(f_stockPrices,goog_stockPrices));
        f_Correlations.add(findCorrelation(f_stockPrices,f_stockPrices));
        
        correlationMatrix.add(x_Correlations);
        correlationMatrix.add(ge_Correlations);
        correlationMatrix.add(appl_Correlations);
        correlationMatrix.add(goog_Correlations);
        correlationMatrix.add(f_Correlations);
        
        for(int i = 0; i < correlationMatrix.size(); i++){
            System.out.println(correlationMatrix.get(i));
        }
        
        // handle errors if they arise
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        } catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }//end try

      
    }// end main()
    
//------------------------------------------------------------------------------
// helper functions/
//------------------------------------------------------------------------------
    
    public static double findAverage(ArrayList<Double> prices){
        
        double average = 0.0;
       
	   //PUT CODE HERE
           
        for(int i =0; i < prices.size();i++){
            average = average + prices.get(i);
        }
        
        average = average/prices.size();
                
       return average;
    }//end findAverage()
    
    //--------------------------------------------------------------------------
    public static double findStandardDeviation(ArrayList<Double> prices){
        
        double stdDev = 0.0;
        double average = findAverage(prices);
        double n = prices.size();
        double sigma = 0.0;
	
        for(int i =0;i < prices.size();i++){
            sigma = sigma + (Math.pow((prices.get(i)-average), 2));
        }   
        
        stdDev = Math.sqrt(sigma/(n-1));
           
        return stdDev;
    }

    //--------------------------------------------------------------------------
    
    public static double findCorrelation(ArrayList<Double> firstPrices, ArrayList<Double> secondPrices ){
        
        double correlation = 0.0 ;
        double sigma = 0.0;
        
        for(int i=0;i < firstPrices.size(); i++){
            sigma = sigma + ((firstPrices.get(i) - findAverage(firstPrices)) * (secondPrices.get(i) - findAverage(secondPrices))); 
        }
        
        double right = (sigma/(findStandardDeviation(firstPrices)*findStandardDeviation(secondPrices)));
        
        correlation = Math.round((1.0/(firstPrices.size()-1.0) * right )*100)/100.0;
         
       return correlation;
    }//end findCorrelation()
    
    
}
