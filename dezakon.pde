/*

      デザコン2017
    構造デザイン部門
 カウントアッププログラム
 制作：村重海月(2014E36)
 
 
*/

//txt宣言
String team;
String[] team_list;
int ln = 0;
String college_name;
String team_name1;
String team_name2;
String team_name3;

//画像宣言
PImage background;
PImage stage_img;
PImage next_stage_img;
PImage ready;
PImage end;
PImage challenge;
PImage emblem;
PImage final_result;
PImage tenkajin;

//フォント宣言
PFont MS_Gothic;
PFont Arial;
PFont Gyosho;

//変数宣言
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

void setup(){
  size(1024, 768);  //画面サイズ（4:3）
  frameRate(30);
  
  //txt定義
  
  //画像定義
  background = loadImage("backgroud" + group + ".png");
  ready = loadImage("ready.png");
  end = loadImage("end.png");
  challenge = loadImage("challenge.png");
  tenkajin = loadImage("stage5.png");
  
  //フォント定義
  MS_Gothic = createFont("MS-Gothic", 48, true);
  Arial = createFont("Arial", 100, true);
  Gyosho = createFont("HGPGyoshotai", 48, true);
  
}

void draw(){
  //画像定義
  image(background, 0, 0);//背景画像の読み込み
  
  //グループ登録
  if(status == 10){
    color(255,255,255);
    textFont(MS_Gothic,48);
    textAlign(CENTER);
    text("グループを選択", 400, 250, 600, 350);
    textSize(30);
    text("1,2,3,4を入力\nENTERで決定", 400, 600, 600, 250);
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
  
  //高専名・チーム名読み込み
  if(status == 0 && setting == 0){
    color(255,255,255);
    textFont(MS_Gothic,48);
    text("チームを選択してください", 400, 300, 600, 350);
    textSize(30);
    text("上下キーで選択\nENTERで決定", 400, 400, 600, 250);
    fill(200, 50, 50);
    text("※ENTERを押すと90秒\nのカウントが始まります", 400, 550, 600, 250);
    if((keyPressed == true) && (key == 's') && (timer%15 == 0)){
      stage = stage + 1;
      if(stage > 4){
        stage = 1;
      }
    }
    fill(0, 255, 0);
    text("(ステージスキップ[s長押し] :STAGE " + stage + ")", 400, 650, 600, 250);
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
  
  //準備時間の処理
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
    text("布陣まであと", 400, 150, 600, 100);
    textFont(MS_Gothic, 48);
    textSize(30);
    textAlign(CENTER);
    text("完了したらスイッチを押して下さい", 400, 680, 600, 300);
    textFont(Arial, 100);
    textAlign(CENTER);
    textSize(500);
    text(int(timer/30), 700, 620);
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
    //学校名・チーム名を常時表示
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
    text(int(score), 200, 700);
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
    stage_img = loadImage("stage" + stage + ".png");//ステージ表示
    if(stage < 4){
      next_stage_img = loadImage("stage" + next_stage + ".png");//次ステージ表示
    }
    image(stage_img, 30, 450, 350, 110);
    
    //READY画面（1秒は入力を受け付けない）
    if(section == 0){
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("スイッチを押して\nカウント開始", 400, 320, 600, 300);
      timer++;
      if((keyPressed == true) && (key == ' ') && (timer >= 30)){
        section = 1;
        timer = 0;
      }
    }
    
    //カウント部分
    if(section == 1){
      textFont(Arial, 100);
      textAlign(CENTER);
      textSize(500);
      text(int(sec/30), 700, 590);
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
    
    //カウント終了後
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
      text(int(sec/30), 700, 500);
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("10秒経過", 400, 160, 600, 100);
      sec++;
      if(stage >= 2){
        textFont(Gyosho, 48);
        textSize(80);
        text("砲弾流し", 400, 525, 600, 100);
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
      text(int(sec/30), 700, 500);
      textFont(MS_Gothic, 48);
      textSize(70);
      sec++;
      if((hodan != 0) && (stage != 1)){
          image(next_stage_img, 400, 500, 350, 110);
          fill(255, 255, 255);
          textFont(Gyosho, 48);
          textSize(60);
          text("しますか？", 730, 525, 250, 80);
          textFont(MS_Gothic, 48);
          textSize(30);
          fill(0, 255, 0);
          text("(宣言コマンド: 宣言:[y] 終了:[n])", 400, 600, 600, 75);
        }
        else if((hodan == 1) && (stage == 1)){
          image(next_stage_img, 400, 500, 350, 110);
          fill(255, 255, 255);
          textFont(Gyosho, 48);
          textSize(60);
          text("しますか？", 730, 525, 250, 80);
          textFont(MS_Gothic, 48);
          textSize(30);
          fill(0, 255, 0);
          text("(宣言コマンド: 宣言:[y] 終了:[n])", 400, 600, 600, 75);
          fill(255, 0, 0);
          textSize(30);
          textAlign(RIGHT);
          text("(10秒載荷失敗：[f])", 400, 680, 600, 100);
          fill(255);
          textAlign(CENTER);
        }
      if(stage >= 2){
        textFont(Gyosho, 48);
        textSize(80);
        fill(255);
        text("砲弾流し", 400, 160, 600, 100);
        textFont(MS_Gothic, 48);
        textSize(30);
        fill(0, 255, 0);
        if(stage == 2){
          if(hodan == 0){
            text("(砲弾流し: やらない:[x] CLEAR:[c] NG:[g])", 400, 640, 600, 250);
            fill(255, 0, 0);
            textSize(30);
            textAlign(RIGHT);
            text("(10秒載荷失敗：[f])", 400, 680, 600, 100);
            fill(255);
            textAlign(CENTER);
          }
        }
        else if(stage == 3){
          if(hodan == 0){
            text("(砲弾流し: CLEAR:[c] NG:[g])", 400, 640, 600, 250);
            fill(255, 0, 0);
            textSize(30);
            textAlign(RIGHT);
            text("(10秒載荷失敗：[f])", 400, 680, 600, 100);
            fill(255);
            textAlign(CENTER);
          }
        }
      }
        
      else{
        fill(255, 150, 0);
        textFont(MS_Gothic, 48);
        textSize(70);
        text("10秒経過", 400, 160, 600, 100);
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
      
      //時間切れの場合
      if(sec >= 900){
        sec = 900;
        //section = 6;
      }
      
      if((timer/30) % 2 == 0){
        textFont(MS_Gothic, 48);
        textSize(30);
        fill(255);
        textAlign(LEFT);
        text("入力待機中", 400, 680, 200, 100);
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
    
    //途中終了処理
    if(section == 4){
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("競技結果", 400, 200, 600, 200);
      image(stage_img, 500, 290);
      textSize(50);
      if(flag == 1){
        fill(255, 0, 0);
        text("失敗", 600, 300, 600, 100);
        fill(255);
      }
      else{
        fill(255, 150, 0);
        text("成功", 600, 300, 600, 100);
        fill(255);
      }

      textSize(30);
      text("rキーでリセット\n(チーム選択に戻ります)", 400, 600, 600, 200);
      textSize(200);
      text(int(score)+"点", 700, 550);
      
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
      text(int(sec/30), 700, 500);
      sec++;
      textFont(Gyosho, 48);
      textSize(80);
      text("砲弾流し", 400, 160, 600, 100);
      fill(0, 255, 0);
      textFont(MS_Gothic, 48);
      textSize(30);
      if(stage == 4){
        if(hodan == 0){
          text("(砲弾流し: やらない:[x] CLEAR:[c] NG:[g])", 400, 640, 600, 250);
          fill(255, 0, 0);
          textSize(30);
          textAlign(RIGHT);
          text("(10秒載荷失敗：[f])", 400, 680, 600, 100);
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
      
      //時間切れの場合
      if(sec >= 900){
        sec = 900;
        //section = 6;
      }
      
      textFont(MS_Gothic, 48);
      textSize(30);
      fill(255);
      text("入力待機中", 400, 680, 200, 100);
    }
    
    //天下人到達処理
    if(section == 6){
      textFont(MS_Gothic, 48);
      textSize(70);
      textAlign(CENTER);
      text("競技結果", 400, 200, 600, 200);
      image(tenkajin, 530, 290, 300, 80);
      textSize(30);
      text("rキーでリセット\n(チーム選択に戻ります)", 400, 600, 600, 200);
      textSize(200);
      text(int(score)+"点", 700, 550);
      
      //リセット処理
      if((keyPressed == true) && (key == 'r')){
        section = 0;
        stage = 1;
        status = 0;
        sec = 0;
        score = 0;
        hodan = 0;
      }
    }
    
    //時間切れ処理
    if(section == 7){
        textFont(Arial, 48);
        textSize(100);
        text("TIME OVER", 400, 300, 600, 250);
        textFont(MS_Gothic, 48);
        textSize(30);
        text("rキーでリセット\n(チーム選択に戻ります)", 400, 600, 600, 200);
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
  
    
    //終了処理
  }
}

void keyPressed() {
  if (key == CODED){      // コード化されているキーが押された
    if (keyCode == UP){    // キーコードを判定
      if(ln == 13){ //55を13に変更
        ln = 0;
      }
      else{
        ln++;
      }
    } 
    
    else if (keyCode == DOWN){
      if(ln == 0){
        ln = 13; //55を13に変更
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