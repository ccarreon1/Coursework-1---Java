/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatheranalysis;

/*Imports scanner tool*/
import java.util.Scanner;
/**
 *
 * @author christian
 */
public class WeatherAnalysis {
    /*creates array to store weather readings*/
    public static float[] monthlyTotalRain = new float[12];
    public static float[] monthlyHighestTemp = new float[12];
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Scanner keyboard = new Scanner(System.in);
        
        /*Loop used to store reading into array, for month order*/
        /*counter used to determine location to add reading*/
        int counter = 0;
        for (int month = 1; month < 13;month ++){
            counter = month -1;
            System.out.println("Enter the total rainfall for month "+ month + ":");
            /*totaRain uses validation method to make sure data entered is in the right range*/
            /*Place data entered to right location in array*/
            float totalRain = validate(0,300);
            monthlyTotalRain[counter] = totalRain;
            System.out.println("Enter the highest temperature for month "+ month + ":");
            /*totaRain uses validation method to make sure data entered is in the right range*/
            /*Place data entered to right location in array*/
            float highestTemp = validate(-30,60);
            monthlyHighestTemp[counter] = highestTemp;
        }
        System.out.println("Data entered");
        
        /*Loop acts as main menu to code, each input leads to method*/
        int exit = 1;
        int choice;
        while (exit != 0){
            /*runs the displayMenu method*/
            displayMenu();
            System.out.print("Enter your choice:");
            choice = keyboard.nextInt();
            switch (choice){
                /*Sets exit to 0, this exits program*/
                case 1:
                    exit = 0;
                    break;
                case 2:
                /*Displays readings by running displayData method*/
                    displayData();
                    break;
                case 3:
                    
                    /*Asks user for new month,used to determine location in array to place new reading
                    *Ask user for new reading
                    *uses validate method to make sure data enter is valid
                    *runs change reading function
                    */
                    System.out.println("Enter the month you want to change:");
                    int month = (int)validate(1,12);
                    System.out.println("Enter new temperature:");
                    float nTemp = validate(-30,60);
                    changeTemp(month,nTemp);
                    break;
                case 4:
                    /*Asks user for new month,used to determine location in array to place new reading
                    *Ask user for new reading
                    *uses validate method to make sure data enter is valid
                    *runs change reading function
                    */
                    System.out.println("Enter the month you want to change:");
                    int nMonth = (int)validate(1,12);
                    System.out.println("Enter new total rainfall:");
                    float nRain = validate(0,300);
                    changeRain(nMonth,nRain);
                    break;
                case 5:
                    /*runs getTotal to find total annual rainfall*/
                    System.out.println("The total annual rainfall is "+ getTotal(monthlyTotalRain));
                    break;
                case 6:
                    /*uses getMean to calculate mean rainfall*/
                    System.out.println("The mean rainfall is "+ getMean(monthlyTotalRain)+", The mean highest temperature is "+ getMean(monthlyHighestTemp));
                    break;
                case 7:
                    /*displays temperature elow 3 degrees*/
                    System.out.println("Months with highest temperature below 3 degrees:");
                    displayLowerThan(monthlyHighestTemp,3);
                    break;
                case 8:
                    /*Displays months that are below daily drought*/
                    System.out.println("Months below drought level(0.075mm):");
                    displayLowerThan(returnMeanArray(monthlyTotalRain), (float) 0.75);
                    break;
                case 9:
                    /*Displays highest and lowest readings*/
                    displayHighestLowest(monthlyHighestTemp);
                    break;
                case 10:
                    /*Displays highest and lowest readings*/
                    displayHighestLowest(monthlyTotalRain);
                    break;
                default:
                    /*Displays error message if option entered is invalid*/
                    System.out.println("Incorrect input");
            }
            
        }
    }
    
    public static void displayMenu(){
        /*Displays menu option*/
        System.out.println("1. Quit \n 2. Display all Data \n 3. Change a temperature value \n 4. Change a rainfall value \n 5. Display annual rainfall total \n 6.Display mean temperature and mean total rainfall \n 7. Display month with temp below 3 degrees \n 8. Display month below drought level \n 9. Display month(s) with highest temp and lowest temp \n 10. Display driest and wettest month");
    }
    public static float validate(float min, float max){
        /*valid variable used to exit loop if data is valid
        *scanner retreives and saves data enered
        *loop until data entered is valid
        *makes sure data entred does not exceed ranges
        */
        int valid = 0;
        Scanner sc = new Scanner(System.in);
        float temp = sc.nextFloat();
        while (valid != 1){
            if(temp < min || temp > max){
                System.out.println("Invalid reading, enter valid reading:");
                temp = sc.nextFloat();
            }
            else{
                valid = 1;
            }
        }
        return temp;
    }
    public static void displayData(){
        /*Loop 12 times, to display readings for each month*/
        for (int i = 0; i < 12; i++){
            int month = i + 1;
            System.out.println("Month "+ month +"; Total rainfall: "+ monthlyTotalRain[i]+"; Highest temperature: "+ monthlyHighestTemp[i] );
        }
    }
    
    public static void changeTemp(int month, float change){
        /*Change reading in array to new reading*/
        monthlyHighestTemp[month - 1] = change;
    }
    
    public static void changeRain(int month, float change){
        /*Change reading in array to new reading*/
        monthlyTotalRain[month - 1] = change;
    }
    
    public static float getTotal(float[] weatherArray){
        /*Loop to add each data in array to calculate total*/
        float total = 0;
        for (int month = 0;month < 12;month++){
            total += weatherArray[month];
        }
        return total;
    }
    
    public static float getMean(float[] weatherArray){
        /*Uses get total, then divides it with the weather array length to return mean*/
        float mean = getTotal(weatherArray)/weatherArray.length;
        return mean;
    }
    
    public static  float[] returnMeanArray (float[] weatherArray){
        /*Returns array to with mean for each month*/
        float[] meanArray = new float[12];
        for (int i = 0; i < 12; i++){
            meanArray[i] = weatherArray[i]/30;
        }
        return meanArray;
    }
    
    public static void displayLowerThan(float[] weatherArray,float lowest){
        /*Checks every data in array, then returns if reading is lower than lowest range entered*/
        int counter = 0;
        for (int month = 0;month < 12;month ++){
            if (weatherArray[month] < lowest){
                int displayMonth = month + 1;
                System.out.println("Month " + displayMonth +" with " + weatherArray[month]);
                counter +=1;
            }
        }
        if (counter == 0){
            System.out.println("No month with reading lower than" + lowest);
        }
    }
    public static void displayHighestLowest(float[] weatherArray){
        /*Checks all readings in array, if it is lower or higher than current highest or lowest, it is saved then displayed*/
        int lowestMonth = 0;
        int highestMonth = 0;
        for (int month = 1; month < 12; month ++){
            if (weatherArray[month]< weatherArray[lowestMonth]){
                lowestMonth = month;
            }
            if (weatherArray[month]> weatherArray[highestMonth]){
                highestMonth = month;
            }
        }
        int displayLowestMonth =lowestMonth+ 1;
        int displayHighestMonth = highestMonth + 1;
        System.out.println("Month "+ displayLowestMonth +" has the lowest reading of "+ weatherArray[lowestMonth]+",Month "+ displayHighestMonth +" has the highest reading of "+ weatherArray[highestMonth]);
        
    }
}