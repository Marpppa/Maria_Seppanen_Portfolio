import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;  
import javafx.scene.image.PixelReader; 
import javafx.scene.image.PixelWriter; 
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color; 
import javafx.scene.image.ImageView; 
import javafx.stage.Stage;  
import javafx.scene.control.Button;
import javafx.scene.control.TextField; 
import javafx.scene.text.Text; 
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent; 
import javafx.event.ActionEvent;
import java.util.Random;

import java.io.FileWriter; 
import java.io.IOException;
//import java.util.*;
//import java.lang.Math.*;

import harjoitustyo.jp.*;


//Tira 2020 harjoitusty√∂n pohja. 
//Muokattu https://www.tutorialspoint.com/javafx/index.htm esimerkeist√§.

public class Harkka2020 extends Application {  
   Button button1;
   Button button2;
   Button button3;
   Button button4;
   Image image;
   WritableImage wImage;
   double width;
   double height;
   ImageView imageView1;
   ImageView imageView2;

   int intEro; // k‰ytt‰j‰n antama intensiteettiero
   int kokEro; // k‰ytt‰j‰n antma kokonaisintensiteettiero
   int hiirenX; // hiiren klikkaus x-sarakkeella
   int hiirenY; // hiiren klikkaus y-sarakkeella
   
   int taul[][]; // harmaas‰vyesitys
   
   @Override 
   public void start(Stage stage) throws FileNotFoundException {         
      //Creating an image 	  	  
       image= new Image(new FileInputStream("Testikuva.jpg"));  
       width=image.getWidth();
	   height=image.getHeight();	   
      //Setting the image view 1 
       imageView1 = new ImageView(image); 
      
      //Setting the position of the image 
	  //HUOM! T√§m√§ vaikuttaa hiiren koordinaatteihin kuvassa.
      imageView1.setX(50); 
      imageView1.setY(25); 
      
      //setting the fit height and width of the image view 
      imageView1.setFitHeight(height/2); 
      imageView1.setFitWidth(width/2);         
      
      //Setting the preserve ratio of the image view 
      imageView1.setPreserveRatio(true); 
         
      //Setting the image view 2 
      imageView2 = new ImageView(image);
      
      //Setting the position of the image 
      imageView2.setX(width/2+60); 
      imageView2.setY(25); 
      
      //setting the fit height and width of the image view 
      imageView2.setFitHeight(height/2); 
      imageView2.setFitWidth(width/2);          
      
      //Setting the preserve ratio of the image view 
      imageView2.setPreserveRatio(true);          
      int delta=50;
	  Text text1 = new Text("Anna sallittu intensiteettiero");  
	  text1.setLayoutX(50);
	  text1.setLayoutY(height/2+delta);
	  TextField textField1 = new TextField(); 
	  textField1.setText("5");
	  textField1.setLayoutX(50);
	  textField1.setLayoutY(height/2+1.3*delta);
	  
	  Text text2 = new Text("Anna sallittu kokonaisero");  
	  text2.setLayoutX(50);
	  text2.setLayoutY(height/2+2.8*delta);
	  TextField textField2 = new TextField();
	  textField2.setText("5");
	  textField2.setLayoutX(50);
	  textField2.setLayoutY(height/2+3.1*delta);
	  
      button1 = new Button("Hae yksi komponentti syvyyshaulla");
	  button1.setLayoutX(50);
	  button1.setLayoutY(height/2+4*delta);
	  
	  button2 = new Button("Hae yksi komponentti leveyshaulla");
	  button2.setLayoutX(50);
	  button2.setLayoutY(height/2+4.8*delta);
	  
	  button3 = new Button("Hae yhden komponentin minimiVPuu");
	  button3.setLayoutX(50);
	  button3.setLayoutY(height/2+5.5*delta);
	  
	  button4 = new Button("Hae kaikki komponentit");
	  button4.setLayoutX(50);
	  button4.setLayoutY(height/2+6.2*delta);
	  
	  EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            //Luetaan tekstikenttien tiedot.
			  String txt1=textField1.getText();
			  String txt2=textField2.getText();
			  
			  intEro = Integer.parseInt(txt1);
			  kokEro = Integer.parseInt(txt2);
			  
			  //Valitaan suoritettava teht√§v√§.
			  if(e.getSource()==button1)
                Syvyyshaku(); 
			  if(e.getSource()==button2)
				  Leveyshaku();
			  if(e.getSource()==button3)
				  MinVPuu();			  
			  if(e.getSource()==button4)
				  Kaikki();
			  System.out.println("Eka teksti " + txt1);
			  System.out.println("Toka teksti "+ txt2);
			  
				
            } 
        }; 

	  button1.setOnAction(event);
	  button2.setOnAction(event);
	  button3.setOnAction(event);
	  button4.setOnAction(event);
	  
	  EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
         @Override 
         public void handle(MouseEvent e) {             
            int hiirenXX = (int)e.getX();
	        int hiirenYY = (int)e.getY();
	        
	        // "Kalibroidaan" hiiren klikkaus olemaan oikeassa kohdassa. 
	        hiirenX = hiirenXX*2-100;
	        hiirenY = hiirenYY*2-50;
			System.out.println("Hiiren klikkaus rivilla "+ hiirenY + " ja sarakkeella "+ hiirenX);
			
          //HUOM! N√§kyv√§ kuvan korkeus ja leveys on puolet varsinaisesta kuvasta.
		//	Lis√§ksi n√§kyv√§n kuvan vasen yl√§reuna on kohdassa(50,25).
		//Kuvassa tarvitaan kokonaislukuja.
         } 
      };  
	  
	  imageView1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
      //Creating a Group object  
      Group root = new Group(imageView1, imageView2, text1, textField1, 
	  text2, textField2,button1,button2,button3,button4);  
      
      //Creating a scene object 
      Scene scene = new Scene(root, width+100, height*0.85);  
      
      //Setting title to the Stage 
      stage.setTitle("Harkka 2020");  
      
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage
      stage.show(); 
   }  
   
   
   //Kuvan piirt√§mist√§ varten.
   public void ManipulateImage()
   {
	  
	  wImage = new WritableImage((int)width, (int)height);	   
      PixelReader pixelReader = image.getPixelReader();      
      PixelWriter writer = wImage.getPixelWriter();  
	  Random rand = new Random();
	  int rval=rand.nextInt(50);
	  rval=rval+15;
	  
	  // Luoaan taulukko harmaas‰vyesitykselle. 
	  taul = new int[(int)width][(int)height];
	  
	  //Reading through the image. 
      for(int y = 0; y < height; y++) { 
         for(int x = 0; x < width; x++) { 
        	
            //Retrieving the color of the pixel of the loaded image   
            Color color = pixelReader.getColor(x, y); 
		
	        int r = (int) Math.round(color.getRed() * 255);
            int g = (int) Math.round(color.getGreen() * 255);
            int b = (int) Math.round(color.getBlue() * 255);
			int grayscale=(int) Math.round(0.3*r+0.59*g+0.11*b);
			// Asetetaan grayscale-arvo taulukkkoon.
	        taul[x][y] = grayscale;
         }
      }	
   }
   
   // Metodi valmiin yhdistetyn komponentin piirt‰mist‰ varten.
   public void piirraKuva(boolean[][] kaydyt) {
	   PixelWriter writer = wImage.getPixelWriter(); 
	   for(int n = 0; n < width; n++) { 
		   for(int m = 0; m < height; m++) {
	
			   if (kaydyt[n][m] == true) {
		                
				   // Asetetaan yhdistetyn komponentin v‰riksi tumman sininen.
				   writer.setColor(n,m, Color.DARKBLUE);
		       }
		       else {
		    	   // Muualle valkoista.
		    	   writer.setColor(n,m, Color.WHITE);
		       }
	       }
	    }	

	   imageView2.setImage(wImage);
   }
  
   public void Syvyyshaku()
   {
	   ManipulateImage();
	   
   }
   
   
   public void Leveyshaku()
   {
	   // Haetaan harmaas‰vyeistys
	   ManipulateImage();
	   
	   // Luodaan taulukko, johon merkit‰‰n k‰ydyt solmut.
	   boolean[][] kaydyt = new boolean[(int)height][(int)width];
	   
	   // Luodaan graafi.
	   PainotettuGraafi painotettu = new PainotettuGraafi();
	   PainotettuGraafi.Graafi graafi = painotettu.new Graafi((int)height*(int)width);
	   
	   // Luodaan temp1 jono.
	   Jono temp1 = new Jono((int)width*(int)height);
	   
	   // Lis‰t‰‰n aloitussolmu graafiin.
	   PainotettuGraafi.Kaari kaari = graafi.lisaaKaari(hiirenX, hiirenY, 0);
	   temp1.enqueue(kaari.kaarenTiedot());
	   
	   // Luodaan temp2j-jono viereisi‰ silmukoita varten.
	   Jono temp2 = new Jono((int)width*(int)height);
	   boolean jatketaan = true;
	   
	   // Luodaan silmukka, jossa k‰yd‰‰n l‰pi kaikki yhdistetyn komponentin pikselit. Jatketaan = false, kun
	   // temp1 ja temp2 ovat tyhji‰, eli koko yhdistetty komponentti on k‰yty l‰pi.
		while(jatketaan) {
		   // K‰yd‰‰n l‰pi jonon temp1 kaikki solmut.
		   while (!temp1.isEmpty()) {
			   
			   // Otetaan talteen temp1-jonon ensimm‰isen solmun koordinaatit.
			   int p[] = (int[])temp1.front();
		
			   // Tarkistetaan kyseisen solmun viereiset solmut.
			   int x = p[0];
			   int y = p[1];
			   
			   // Nykyisen solmun kokonaisero.
			   int kokonaisEro = p[2];
			   
			   /* 
			    * K‰yd‰‰n l‰pi kaikki viereiset pikselit.
			    * 
			    */
			   // yl‰vasen pikseli
			   if (x-1 >= 0 && y-1 >= 0) {
				   int intensiteettiEro = Math.abs(taul[x][y]-taul[x-1][y-1]);
				   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
		           
				   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x-1][y-1]) {
					   // Lis‰t‰‰n toteutunut kaari graafiin.
					   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x-1, y-1, tempKokonaisEro);
					   
					   // Lis‰t‰‰n kaari temp2-jonoon.
					   temp2.enqueue(uusikaari.kaarenTiedot());
					   
					   // K‰yty.
					   kaydyt[x-1][y-1] = true;
				   }
			    }
			   
		       // yl‰keski
		       if (y-1 >= 0) {
		    	   int intensiteettiEro = Math.abs(taul[x][y]-taul[x][y-1]);
				   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
		           
				   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x][y-1]) {
					   // Lis‰t‰‰n toteutunut kaari graafiin.
					   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x, y-1, tempKokonaisEro);
					   
					   // Lis‰t‰‰n kaari temp2-jonoon.
					   temp2.enqueue(uusikaari.kaarenTiedot());
					   
					// K‰yty.
					   kaydyt[x][y-1] = true;
		            }
		       }
				   
		       // yl‰oikea
		       if (x+1 < width && y-1 >= 0) {
		    	   int intensiteettiEro = Math.abs(taul[x][y]-taul[x+1][y-1]);
				   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
		           
				   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x+1][y-1]) {
					   // Lis‰t‰‰n toteutunut kaari graafiin.
					   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x+1, y-1, tempKokonaisEro);
					   
					   // Lis‰t‰‰n kaari temp2-jonoon.
					   temp2.enqueue(uusikaari.kaarenTiedot());
					   
					// K‰yty.
					   kaydyt[x+1][y-1] = true;
		            }
		       }
		       
		       // keskivasen
		       if (x-1 >= 0) {
		    	   int intensiteettiEro = Math.abs(taul[x][y]-taul[x-1][y]);
				   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
		           
				   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x-1][y]) {
					   // Lis‰t‰‰n toteutunut kaari graafiin.
					   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x-1, y, tempKokonaisEro);
					   
					   // Lis‰t‰‰n kaari temp2-jonoon.
					   temp2.enqueue(uusikaari.kaarenTiedot());
					   
					   // K‰yty.
					   kaydyt[x-1][y] = true;
		            }
		       }
		       
		        //keskioikea
		        if (x+1 < width) {
		        	int intensiteettiEro = Math.abs(taul[x][y]-taul[x+1][y]);
					   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
			           
					   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x+1][y]) {
						   // Lis‰t‰‰n toteutunut kaari graafiin.
						   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x+1, y, tempKokonaisEro);
						   
						   // Lis‰t‰‰n kaari temp2-jonoon.
						   temp2.enqueue(uusikaari.kaarenTiedot());
						   
						// K‰yty.
						   kaydyt[x+1][y] = true;
			            }
		        }
		        
		        // alavasen
		        if (x-1 >= 0 && y+1 < height) {
		        	int intensiteettiEro = Math.abs(taul[x][y]-taul[x-1][y+1]);
					   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
			           
					   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x-1][y+1]) {
						   // Lis‰t‰‰n toteutunut kaari graafiin.
						   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x-1, y+1, tempKokonaisEro);
						   
						   // Lis‰t‰‰n kaari temp2-jonoon.
						   temp2.enqueue(uusikaari.kaarenTiedot());
						   
						// K‰yty.
						   kaydyt[x-1][y+1] = true;
			            }
		         }
		        
				 // alakeski
		         if (y+1 < height) {
		        	 int intensiteettiEro = Math.abs(taul[x][y]-taul[x][y+1]);
					   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
			           
					   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x][y+1]) {
						   // Lis‰t‰‰n toteutunut kaari graafiin.
						   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x, y+1, tempKokonaisEro);
						   
						   // Lis‰t‰‰n kaari temp2-jonoon.
						   temp2.enqueue(uusikaari.kaarenTiedot());
						   
						// K‰yty.
						   kaydyt[x][y+1] = true;
			            }
		          }
		          // alaoikea
		          if (x+1 < width && y+1 < height) {
		        	  int intensiteettiEro = Math.abs(taul[x][y]-taul[x+1][y+1]);
					   int tempKokonaisEro = kokonaisEro+intensiteettiEro;
			           
					   if ( intensiteettiEro < intEro && tempKokonaisEro < kokEro && !kaydyt[x+1][y+1]) {
						   // Lis‰t‰‰n toteutunut kaari graafiin.
						   PainotettuGraafi.Kaari uusikaari = graafi.lisaaKaari(x+1, y+1, tempKokonaisEro);
						   
						   // Lis‰t‰‰n kaari temp2-jonoon.
						   temp2.enqueue(uusikaari.kaarenTiedot());
						   
						   // K‰yty.
						   kaydyt[x+1][y+1] = true;
			            }
		          }
		          // Poistetaan juuri k‰sitelty solmu jonosta.
		          temp1.dequeue();
		   }
		   // Kun kaikki temp1-jonon solmut on k‰yty l‰pi, siirret‰‰n k‰ytyjen solmujen kaarella yhdistetyt solmut jonosta temp2 
		   // jonoon temp1, jotta niit‰ voidaan k‰sitell‰ seuraavalla kierroksella.
		   while(!temp2.isEmpty()) {
	     	  temp1.enqueue(temp2.front());
	     	  temp2.dequeue();
	       }
		   // Molemmat jonot tyhji‰ -> yhdistetty komponentti on k‰yty kokonaan l‰pi poistutaan silmukasta.
		   if (temp2.isEmpty() && temp1.isEmpty()) {
			   jatketaan = false;
		   }
	   }
       // Piirret‰‰n yhdistetty komponentti k‰ytt‰en apuna taulukkoa, johon on merkitty kaikki 
	   // komponentin solmut pikseleitt‰in. 
	   piirraKuva(kaydyt);
	   
	   
   }
   
   public void MinVPuu()
   {
	  ManipulateImage();
   }
   
   public void Kaikki()
   {
	  try {
      FileWriter myWriter = new FileWriter("Graafi.txt");
	  for(int i=0; i<10; i++)
      myWriter.write("Rivi"+String.valueOf(i)+"\n");
      myWriter.close();
      
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
     
   }
   public static void main(String args[]) { 
      launch(args); 

      Harkka2020.main(args);

   } 
}        