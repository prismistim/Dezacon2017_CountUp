import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class dezakon extends PApplet {

/*

      \u30c7\u30b6\u30b3\u30f32017
    \u69cb\u9020\u30c7\u30b6\u30a4\u30f3\u90e8\u9580
 \u30ab\u30a6\u30f3\u30c8\u30a2\u30c3\u30d7\u30d7\u30ed\u30b0\u30e9\u30e0
 \u5236\u4f5c\uff1a\u6751\u91cd\u6d77\u6708(2014E36)
 
 
*/

//txt\u5ba3\u8a00
String team;
String[] team_list;
int ln = 0;
String college_name;
String team_name1;
String team_name2;
String team_name3;

//\u753b\u50cf\u5ba3\u8a00
PImage background;
PImage stage_img;
PImage next_stage_img;
PImage ready;
PImage end;
PImage challenge;
PImage emblem;
PImage final_result;
PImage tenkajin;

//\u30d5\u30a9\u30f3\u30c8\u5ba3\u8a00
PFont MS_Gothic;
PFont Arial;
PFont Gyosho;

//\u5909\u6570\u5ba3\u8a00
int status = 10;
int stage = 1;
int next_stage = 0;
int section = 0;
int sec = 0;
int timer = 0;
int setting = 0;
int score = 0;
int group = 1;
int flag = 0;
int hodan = 0;

public void setup(){
    //\u753b\u9762\u30b5\u30a4\u30ba\uff084:3\uff09
  frameRate(30);
  
  //txt\u5b9a\u7fa9
  
  //\u753b\u50cf\u5b9a\u7fa9
  background = loadImage("backgroud" + group + ".png");
  ready = loadImage("ready.png");
  end = loadImage("end.png");
  challenge = loadImage("challenge.png");
  tenkajin = loadImage("stage5.png");
  
  //\u30d5\u30a9\u30f3\u30c8\u5b9a\u7fa9
  MS_Gothic = createFont("MS-Gothic", 48, true);
  Arial = createFont("Arial", 100, true);
  Gyosho = createFont("HGPGyoshotai", 48, true);
  
}

public void draw(){
  //\u753b\u50cf\u5b9a\u7fa9
  image(background, 0, 0);//\u80cc\u666f\u753b\u50cf\u306e\u8aad\u307f\u8fbc\u307f
  
  //\u30b0\u30eb\u30fc\u30d7\u767b\u9332
  if(status == 10){
    color(255,255,255);
    textFont(MS_Gothic,48);
    textAlign(CENTER);
    text("\u30b0\u30eb\u30fc\u30d7\u3092\u9078\u629e", 400, 250, 600, 350);
    textSize(30);
    text("1,2,3,4\u3092\u5165\u529b\nENTER\u3067\u6c7a\u5b9a", 400, 600, 600, 250);
    textFont(Arial, 100);
    textSize(300);
    if(key == '1'){
      text("A", 400, 300, 600, 400);
      group = 1;
    }
    
    else if(key == '2'){
      text("B", 400, 300, 600, 400);
      group = 2;
    }
    
    else if(key == '3'){
      text("C", 400, 300, 600, 400);
      group = 3;
    }
    
    else if(key == '4'){
      text("D", 400, 300, 600, 400);
      group = 4;
    }
     background = loadImage("backgroud" + group + ".png");
    if((keyPressed == true) && (key == ENTER) && (group >= 1)){
      team_list = loadStrings("team_list" + group + ".txt");
      status = 0;
    }
  }
  
  //\u9ad8\u5c02\u540d\u30fb\u30c1\u30fc\u30e0\u540d\u8aad\u307f\u8fbc\u307f
  if(status == 0 && setting == 0){
    color(255,255,255);
    textFont(MS_Gothic,48);
    text("\u30c1\u30fc\u30e0\u3092\u9078\u629e\u3057\u3066\u304f\u3060\u3055\u3044", 400, 300, 600, 350);
    textSize(30);
    text("\u4e0a\u4e0b\u30ad\u30fc\u3067\u9078\u629e\nENTER\u3067\u6c7a\u5b9a", 400, 400, 600, 250);
    fill(200, 50, 50);
    text("\u203bENTER\u3092\u62bc\u3059\u306890\u79d2\n\u306e\u30ab\u30a6\u30f3\u30c8\u304c\u59cb\u307e\u308a\u307e\u3059", 400, 550, 600, 250);
    if((keyPressed == true) && (key == 's') && (timer%15 == 0)){
      stage = stage + 1;
      if(stage > 4){
        stage = 1;
      }
    }
    fill(0, 255, 0);
    text("(\u30b9\u30c6\u30fc\u30b8\u30b9\u30ad\u30c3\u30d7[s\u9577\u62bc\u3057] :STAGE " + stage + ")", 400, 650, 600, 250);
    switch(stage){
      case 1:
        score = 0;
        break;
      case 2:
        score = 25;
        break;
      case 3:
        score = 35;
        break;
      case 4:
        score = 50;
        break;
    }
    
    team = team_list[ln];
    String[] co = split(team, ',');
    college_name = co[0];
    team_name1 = co[1];
    team_name2 = co[2];
    team_name3 = co[3];
    
    fill(255);
    textFont(MS_Gothic, 48);
    textSize(70);
    textAlign(CENTER);
    text(college_name, 30, 160, 350, 100);
    textSize(32);
    text(team_name1, 30, 290, 350, 80);
    textSize(20);
    text(team_name2, 30, 330, 350, 40);
    text(team_name3, 30, 350, 350, 40);
    textFont(Arial, 48);
    textSize(60);
    switch(group){
      case 1:
        text("A", 590, 20, 100, 500);
        break;
      case 2:
        text("B", 590, 20, 100, 500);
        break;
      case 3:
        text("C", 590, 20, 100, 500);
        break;
      case 4:
        text("D", 590, 20, 100, 500);
        break; 
    }
    
    timer++;
    
    if((keyPressed == true) && (key == ENTER) && (timer >= 30)){
      setting = 1;
      timer = 2729; //2729
      if(stage > 1){
         status = 1;
         setting = 0;
      }
    }
  }
  
  //\u6e96\u5099\u6642\u9593\u306e\u51e6\u7406
  else if(status == 0 && setting == 1){
    textFont(MS_Gothic, 48);
    textSize(70);
    textAlign(CENTER);
    text(college_name, 30, 160, 350, 100);
    textSize(32);
    text(team_name1, 30, 290, 350, 80);
    textSize(20);
    text(team_name2, 30, 330, 350, 40);
    text(team_name3, 30, 350, 350, 40);
    textSize(60);
    textFont(Gyosho, 48);
    textSize(80);
    text("\u5e03\u9663\u307e\u3067\u3042\u3068", 400, 150, 600, 100);
    textFont(MS_Gothic, 48);
    textSize(30);
    textAlign(CENTER);
    text("\u5b8c\u4e86\u3057\u305f\u3089\u30b9\u30a4\u30c3\u30c1\u3092\u62bc\u3057\u3066\u4e0b\u3055\u3044", 400, 680, 600, 300);
    textFont(Arial, 100);
    textAlign(CENTER);
    textSize(500);
    text(PApplet.parseInt(timer/30), 700, 620);
    textSize(60);
    switch(group){
      case 1:
        text("A", 590, 20, 100, 500);
        break;
      case 2:
        text("B", 590, 20, 100, 500);
        break;
      case 3:
        text("C", 590, 20, 100, 500);
        break;
      case 4:
        text("D", 590, 20, 100, 500);
        break; 
    }
    
    timer--;
    
    if((keyPressed == true) && (key == ' ')){
       status = 1;
       timer = 0;
       setting = 0;
    }
    else if(timer == 0){
       status = 1;
       section = 7;
       timer = 0;
       setting = 0;
    }
  }
  
  if(status == 1){
    //\u5b66\u6821\u540d\u30fb\u30c1\u30fc\u30e0\u540d\u3092\u5e38\u6642\u8868\u793a
    fill(255, 255, 255);
    textFont(MS_Gothic, 48);
    textSize(70);
    textAlign(CENTER);
    text(college_name, 30, 160, 350, 100);
    textSize(32);
    text(team_name1, 30, 290, 350, 80);
    textSize(20);
    text(team_name2, 30, 330, 350, 40);
    text(team_name3, 30, 350, 350, 40);
    textFont(Arial, 100);
    textSize(100);
    text(PApplet.parseInt(score), 200, 700);
    textSize(60);
    switch(group){
      case 1:
        text("A", 590, 20, 100, 500);
        break;
      case 2:
        text("B", 590, 20, 100, 500);
        break;
      case 3:
        text("C", 590, 20, 100, 500);
        break;
      case 4:
        text("D", 590, 20, 100, 500);
        break; 
    }
    
    next_stage = stage + 1;
    stage_img = loadImage("stage" + stage + ".png");//\u30b9\u30c6\u30fc\u30b8\u8868\u793a
    if(stage < 4){
      next_stage_img = loadImage("stage" + next_stage + ".png");//\u6b21\u30b9\u30c6\u30fc\u30b8\u8868\u793a
    }
    image(stage_img, 30, 450, 350, 110);
    
    //READY\u753b\u9762\uff081\u79d2\u306f\u5165\u529b\u3092\u53d7\u3051\u4ed8\u3051\u306a\u3044\uff09
    if(section == 0){
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("\u30b9\u30a4\u30c3\u30c1\u3092\u62bc\u3057\u3066\n\u30ab\u30a6\u30f3\u30c8\u958b\u59cb", 400, 320, 600, 300);
      timer++;
      if((keyPressed == true) && (key == ' ') && (timer >= 30)){
        section = 1;
        timer = 0;
      }
    }
    
    //\u30ab\u30a6\u30f3\u30c8\u90e8\u5206
    if(section == 1){
      textFont(Arial, 100);
      textAlign(CENTER);
      textSize(500);
      text(PApplet.parseInt(sec/30), 700, 590);
      sec++;
      if(sec == 300){
        section = 2;
      }
      
      if((keyPressed == true) && (key == 'r')){
        timer++;
        if(timer >= 10){
          sec = 0;
          section = 0;
          timer = 0;
        }
      }
    }
    
    //\u30ab\u30a6\u30f3\u30c8\u7d42\u4e86\u5f8c
    if(section == 2){
      switch(stage){
        case 1:
          score = 40;
          break;
        case 2:
          score = 50;
          break;
        case 3:
          score = 65;
          break;
        case 4:
          score = 70;
          break;
      }
      
      fill(255, 0, 0);
      textFont(Arial, 100);
      textAlign(CENTER);
      textSize(300);
      text(PApplet.parseInt(sec/30), 700, 500);
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("10\u79d2\u7d4c\u904e", 400, 160, 600, 100);
      sec++;
      if(stage >= 2){
        textFont(Gyosho, 48);
        textSize(80);
        text("\u7832\u5f3e\u6d41\u3057", 400, 525, 600, 100);
        score = score - 20;
      }
      if(sec == 359){
        section = 3;
        timer = 0;
        if(stage == 4){
          section = 5;
          timer = 0;
        }
      }
    }
    
    if(section == 3){
      fill(255, 150, 0);
      textFont(Arial, 100);
      textAlign(CENTER);
      textSize(300);
      text(PApplet.parseInt(sec/30), 700, 500);
      textFont(MS_Gothic, 48);
      textSize(70);
      sec++;
      if((hodan != 0) && (stage != 1)){
          image(next_stage_img, 400, 500, 350, 110);
          fill(255, 255, 255);
          textFont(Gyosho, 48);
          textSize(60);
          text("\u3057\u307e\u3059\u304b\uff1f", 730, 525, 250, 80);
          textFont(MS_Gothic, 48);
          textSize(30);
          fill(0, 255, 0);
          text("(\u5ba3\u8a00\u30b3\u30de\u30f3\u30c9: \u5ba3\u8a00:[y] \u7d42\u4e86:[n])", 400, 600, 600, 75);
        }
        else if((hodan == 1) && (stage == 1)){
          image(next_stage_img, 400, 500, 350, 110);
          fill(255, 255, 255);
          textFont(Gyosho, 48);
          textSize(60);
          text("\u3057\u307e\u3059\u304b\uff1f", 730, 525, 250, 80);
          textFont(MS_Gothic, 48);
          textSize(30);
          fill(0, 255, 0);
          text("(\u5ba3\u8a00\u30b3\u30de\u30f3\u30c9: \u5ba3\u8a00:[y] \u7d42\u4e86:[n])", 400, 600, 600, 75);
          fill(255, 0, 0);
          textSize(30);
          textAlign(RIGHT);
          text("(10\u79d2\u8f09\u8377\u5931\u6557\uff1a[f])", 400, 680, 600, 100);
          fill(255);
          textAlign(CENTER);
        }
      if(stage >= 2){
        textFont(Gyosho, 48);
        textSize(80);
        fill(255);
        text("\u7832\u5f3e\u6d41\u3057", 400, 160, 600, 100);
        textFont(MS_Gothic, 48);
        textSize(30);
        fill(0, 255, 0);
        if(stage == 2){
          if(hodan == 0){
            text("(\u7832\u5f3e\u6d41\u3057: \u3084\u3089\u306a\u3044:[x] CLEAR:[c] NG:[g])", 400, 640, 600, 250);
            fill(255, 0, 0);
            textSize(30);
            textAlign(RIGHT);
            text("(10\u79d2\u8f09\u8377\u5931\u6557\uff1a[f])", 400, 680, 600, 100);
            fill(255);
            textAlign(CENTER);
          }
        }
        else if(stage == 3){
          if(hodan == 0){
            text("(\u7832\u5f3e\u6d41\u3057: CLEAR:[c] NG:[g])", 400, 640, 600, 250);
            fill(255, 0, 0);
            textSize(30);
            textAlign(RIGHT);
            text("(10\u79d2\u8f09\u8377\u5931\u6557\uff1a[f])", 400, 680, 600, 100);
            fill(255);
            textAlign(CENTER);
          }
        }
      }
        
      else{
        fill(255, 150, 0);
        textFont(MS_Gothic, 48);
        textSize(70);
        text("10\u79d2\u7d4c\u904e", 400, 160, 600, 100);
        fill(255);
      }
      
      if((keyPressed == true) && (key == 'c') && (hodan == 0) && (stage >= 2)){
        if(stage == 3){
          score = score + 15;
        }
        else{
          score = score + 20;
        }
        hodan = 1;
      }
      
      if((keyPressed == true) && (key == 'x') && (hodan == 0) && (stage == 2) || (stage == 4)){
          score = score + 5;
          hodan = 2;
      }
      
      if((keyPressed == true) && (key == 'g') && (hodan == 0)){
          hodan = 3;
      }
      
      //if((keyPressed == true) && (key == 'n') && (hodan == 3) && (stage == 3)){
      //    section = 4;
      //    hodan = 0;
      //    timer = 0;
      //    sec = 0;
      //}
      
      if(hodan == 1){
        fill(255, 0, 0);
        textFont(Arial, 100);
        textSize(50);
        text("OK", 860, 180, 100, 80);
        fill(255);
      }
      
      else if(hodan == 2){
        fill(0, 0, 255);
        textFont(Arial, 100);
        textSize(50);
        text("NO", 860, 180, 100, 80);
        fill(255);
        timer++;
        if(timer >= 60){
          section = 4;
          timer = 0;
          sec = 0;
          hodan = 0;
        }
      }
      
      else if(hodan == 3){
        fill(0, 150, 255);
        textFont(Arial, 100);
        textSize(50);
        text("NG", 860, 180, 100, 80);
        fill(255);
        timer++;
        if(timer >= 60){
          section = 4;
          timer = 0;
          sec = 0;
          hodan = 0;
        }
      }
      
      if(stage == 1){
        hodan = 1;
      }
      
      //\u6642\u9593\u5207\u308c\u306e\u5834\u5408
      if(sec >= 900){
        sec = 900;
        //section = 6;
      }
      
      if((timer/30) % 2 == 0){
        textFont(MS_Gothic, 48);
        textSize(30);
        fill(255);
        textAlign(LEFT);
        text("\u5165\u529b\u5f85\u6a5f\u4e2d", 400, 680, 200, 100);
      }
      
      if((keyPressed == true) && (key == 'y') && (hodan == 1)){
        stage++;
        section = 0;
        timer = 0;
        sec = 0;
        if(stage < 4){
          score = score - 15;
        }
        else if(stage == 4){
          score = score - 10;
        }
        hodan = 0;
      }
      else if((keyPressed == true) && (key == 'n')){
        section = 4;
        timer = 0;
        sec = 0;
        hodan = 0;
      }
      timer++;
    }
    
    //\u9014\u4e2d\u7d42\u4e86\u51e6\u7406
    if(section == 4){
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("\u7af6\u6280\u7d50\u679c", 400, 200, 600, 200);
      image(stage_img, 500, 290);
      textSize(50);
      if(flag == 1){
        fill(255, 0, 0);
        text("\u5931\u6557", 600, 300, 600, 100);
        fill(255);
      }
      else{
        fill(255, 150, 0);
        text("\u6210\u529f", 600, 300, 600, 100);
        fill(255);
      }

      textSize(30);
      text("r\u30ad\u30fc\u3067\u30ea\u30bb\u30c3\u30c8\n(\u30c1\u30fc\u30e0\u9078\u629e\u306b\u623b\u308a\u307e\u3059)", 400, 600, 600, 200);
      textSize(200);
      text(PApplet.parseInt(score)+"\u70b9", 700, 550);
      
      if((keyPressed == true) && (key == 'r')){
        section = 0;
        stage = 1;
        status = 0;
        score = 0;
        flag = 0;
        sec = 0;
        hodan = 0;
      }
    }
    
    if(section == 5){
      fill(255, 150, 0);
      textFont(Arial, 100);
      textAlign(CENTER);
      textSize(300);
      text(PApplet.parseInt(sec/30), 700, 500);
      sec++;
      textFont(Gyosho, 48);
      textSize(80);
      text("\u7832\u5f3e\u6d41\u3057", 400, 160, 600, 100);
      fill(0, 255, 0);
      textFont(MS_Gothic, 48);
      textSize(30);
      if(stage == 4){
        if(hodan == 0){
          text("(\u7832\u5f3e\u6d41\u3057: \u3084\u3089\u306a\u3044:[x] CLEAR:[c] NG:[g])", 400, 640, 600, 250);
          fill(255, 0, 0);
          textSize(30);
          textAlign(RIGHT);
          text("(10\u79d2\u8f09\u8377\u5931\u6557\uff1a[f])", 400, 680, 600, 100);
          fill(255);
          textAlign(CENTER);
        }
      }
      
      if((keyPressed == true) && (key == 'c') && (hodan == 0)){
        score = score + 20;
        hodan = 1;
      }
      
      if((keyPressed == true) && (key == 'x') && (hodan == 0)){
        score = score + 5;
        hodan = 2;
      }
      
      if((keyPressed == true) && (key == 'g') && (hodan == 0)){
        score = score - 10;
        hodan = 3;
      }
      
      if(hodan == 1){
        fill(255, 0, 0);
        textFont(Arial, 100);
        textSize(80);
        text("OK", 400, 550, 600, 100);
        fill(255);
        timer++;
        if(timer >= 60){
          section = 6;
          timer = 0;
          sec = 0;
        }
      }
      
      else if(hodan == 2){
        fill(0, 0, 255);
        textFont(Arial, 100);
        textSize(80);
        text("NO", 400, 550, 600, 100);
        fill(255);
        timer++;
        if(timer >= 60){
          section = 4;
          timer = 0;
          sec = 0;
        }
      }
      
      else if(hodan == 3){
        fill(0, 100, 255);
        textFont(Arial, 100);
        textSize(80);
        text("NG", 400, 550, 600, 100);
        fill(255);
        timer++;
        if(timer >= 60){
          section = 4;
          timer = 0;
          sec = 0;
        }
      }
      
      //\u6642\u9593\u5207\u308c\u306e\u5834\u5408
      if(sec >= 900){
        sec = 900;
        //section = 6;
      }
      
      textFont(MS_Gothic, 48);
      textSize(30);
      fill(255);
      text("\u5165\u529b\u5f85\u6a5f\u4e2d", 400, 680, 200, 100);
    }
    
    //\u5929\u4e0b\u4eba\u5230\u9054\u51e6\u7406
    if(section == 6){
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("\u7af6\u6280\u7d50\u679c", 400, 200, 600, 200);
      image(tenkajin, 530, 290, 300, 80);
      textSize(30);
      text("r\u30ad\u30fc\u3067\u30ea\u30bb\u30c3\u30c8\n(\u30c1\u30fc\u30e0\u9078\u629e\u306b\u623b\u308a\u307e\u3059)", 400, 600, 600, 200);
      textSize(200);
      text(PApplet.parseInt(score)+"\u70b9", 700, 550);
      
      //\u30ea\u30bb\u30c3\u30c8\u51e6\u7406
      if((keyPressed == true) && (key == 'r')){
        section = 0;
        stage = 1;
        status = 0;
        sec = 0;
        score = 0;
        hodan = 0;
      }
    }
    
    //\u6642\u9593\u5207\u308c\u51e6\u7406
    if(section == 7){
        textFont(Arial, 48);
        textSize(100);
        text("TIME OVER", 400, 300, 600, 250);
        textFont(MS_Gothic, 48);
        textSize(30);
        text("r\u30ad\u30fc\u3067\u30ea\u30bb\u30c3\u30c8\n(\u30c1\u30fc\u30e0\u9078\u629e\u306b\u623b\u308a\u307e\u3059)", 400, 600, 600, 200);
        sec = 0;
        if((keyPressed == true) && (key == 'r')){
          section = 0;
          stage = 1;
          status = 0;
          sec = 0;
          score = 0;
          hodan = 0;
        }
    }
  
    
    //\u7d42\u4e86\u51e6\u7406
  }
}

public void keyPressed() {
  if (key == CODED){      // \u30b3\u30fc\u30c9\u5316\u3055\u308c\u3066\u3044\u308b\u30ad\u30fc\u304c\u62bc\u3055\u308c\u305f
    if (keyCode == UP){    // \u30ad\u30fc\u30b3\u30fc\u30c9\u3092\u5224\u5b9a
      if(ln == 13){ //55\u309213\u306b\u5909\u66f4
        ln = 0;
      }
      else{
        ln++;
      }
    } 
    
    else if (keyCode == DOWN){
      if(ln == 0){
        ln = 13; //55\u309213\u306b\u5909\u66f4
      }
      else{
        ln--;
      }
    }
  }
  
    if((key == 'f') && (status == 1) && (section == 1)){
      section = 4;
      timer = 0;
      sec = 0;
      flag = 1;
    }
    
     if((key == 'f') && (status == 1) && (section == 3)){
      section = 4;
      timer = 0;
      sec = 0;
      flag = 1;
      if(hodan == 0){
        section = 4;
        timer = 0;
        sec = 0;
        flag = 1;
        if(stage == 2)
          score = score - 5;
        if(stage == 3)
          score = score - 10;
      }
      if((hodan == 1) && (stage == 1)){
        section = 4;
        timer = 0;
        sec = 0;
        flag = 1;
        score = 0;
      }
    }
    
    else if((key == 'f') && (status == 1) && (section == 5)){
      section = 4;
      timer = 0;
      sec = 0;
      flag = 1;
    }
  
  if(key == 'q'){
      exit();
  }
}
  public void settings() {  size(1024, 768); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#000000", "--hide-stop", "dezakon" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
