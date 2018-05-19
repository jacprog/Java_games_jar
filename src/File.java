
/*
Jacques Favre
April-23-2018
COP2552.001
Final Project 
Java
Version 1.8
JavaFx GUI interface

credit:
	Java Programming Y David Liang Companion website at pearsonhighered.com/liang/
				
	Sort function in File.java


*/



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;




public class File {
	
//	static String name = "";
//	static int playerWin = 0;
	

	
	    
	//this adds to existing file or create file if not one
			public static void allScoreFile(String name, int playerWin) throws IOException {
				System.out.println("NAME in file OUT: "	+ name);// check for name
				System.out.println("SCORE  in file OUT: "	+ playerWin);// check for name
				
				
				// TODO Auto-generated method stub
//				java.util.Date date = new java.util.Date(); //create date object
				StringBuilder sb = new StringBuilder();

				//READ file for processing and ordering, but first read file, add new latest score, ovewrite file
				 // Create a File instance
			    java.io.File file = new java.io.File("blackjackName.txt");
				if(!file.exists()){// IF no file, create one
			  	   file.createNewFile();
			  	}
			  //APPEND to file

				FileWriter fr = null;
				BufferedWriter br = null;
				PrintWriter pr = null;
				try {
					// to append to file, you need to initialize FileWriter using below constructor
					fr = new FileWriter(file, true);
					br = new BufferedWriter(fr);
					pr = new PrintWriter(br);
					pr.print(playerWin + " "); //add sccore
//					pr.println(date);		//add date
					pr.println(name);		//add name
				}
			    catch (IOException e) {
					e.printStackTrace();
				}
			    finally {
					try {
						pr.close();
						br.close();
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				//END APPEND
				
				    //  Get scores in order, need to pass nothing, read from file
				    topScoreFile();// call sorting function for Top Scores
				
			}// end allScorefile()
			/********************************************************** Then: The sorting  **********************************/
			private static void topScoreFile() throws IOException {
				// TODO Auto-generated method stub
				StringBuilder sbOut = new StringBuilder();// string builder for top scores
				
				//READ file for processing and ordering, but first read file, do some sorting  and  write file
					 // Create a File instance
//				    java.io.File file = new java.io.File("bestScore.txt");
				    java.io.File file = new java.io.File("blackjackName.txt");
					if(!file.exists()){// IF no file, create one
				  	   file.createNewFile();
				  	}

				    try (Scanner s = new Scanner(file); ) {// Create a Scanner for the file
				      // Read data from a file

				    	
				    	
				    	int ctr = 0;
				    		while(s.hasNext()) {//first while loop to count element to set up size of array idea from: https://www.youtube.com/watch?v=k9pf8KKPcwI
				    			ctr++;//counter for size of arrays
				    			s.nextInt();
				    			s.nextLine();
//				    			 System.out.println("ctr"+ ctr);
				    		}
				    		//now I know the size, I can create array, ctr is the size
				    		int[] scorelist = new int[ctr];
				   		String[] datelist = new String[ctr];
				   			Scanner s1 = new Scanner(file);
				  					int k= 0;//to count number of entries...Think I need only one of theses?

				  					
							       while (s1.hasNext()) {//second while loop to read and pass int score and string date

							        int score1 = s1.nextInt();// split score and date for sorting
							         String date1 = s1.nextLine();

										 scorelist[k] = score1;// array of score for sorting
										 datelist[k] = date1;//array of date to follow score

								         k++;
			 
							      }//end second while, finished reading

							       
							    	   //sorting and processing....find smaller score, append date with same index, pass it , find the next smaller score...
							    	   //selection sort.java p 270// Fliped max to min!!!!!!
							    	   for (int i = 0; i < scorelist.length; i++) {//??CHANGE HERE took off length - 1 !!!!!! IF only 1 in array nothing appens
							    		      // Find the maximum in the list[i..list.length-1]NOW find MAX
							    		      int currentMAX = scorelist[i];// start with max is element 1, TRUE if only 1 element
							    		      		String dateMAX = datelist[i];//date follow score
			      
				    		      											int currentMAXIndex = i;
							    		      								scorelist[i] = currentMAX;
							    		      								dateMAX = datelist[i];//date follow score

							    		      for (int j = i+1; j < scorelist.length; j++) {//j = l +1 or j = l ??
							    		        if (currentMAX < scorelist[j]) {// made change here was > now <, switch min to max  low to high
							    		          currentMAX = scorelist[j];
							    		          		dateMAX = datelist[j];//date follow score checking index, so not needed her
							    		          currentMAXIndex = j;
							    		        }
							    		      }
							    		      // Swap list[j] with list[currentMAXIndex] if necessary;
							    		      if (currentMAXIndex != i) {
							    		        scorelist[currentMAXIndex] = scorelist[i];
							    		        				datelist[currentMAXIndex] = datelist[i];//swap date to follow score
							    		        scorelist[i] = currentMAX;
							    		      }
							    		      //after sorting score, rebuild score plus date
										    	sbOut.append(currentMAX);//create ordered outout, combine score and date, then pass
										    	sbOut.append(dateMAX);
										    	sbOut.append("\n");
							    		            
							    		    }//end sorting loop
							    	   		// Print to file
							    	    		java.io.File file11 = new java.io.File("blackjackName.txt");
							    		    try (java.io.PrintWriter output1 = new java.io.PrintWriter(file11); ) { // Write formatted output to the file
							    		    	output1.print(sbOut);

							    		    }
			}	    
		}//end top score file	
}
