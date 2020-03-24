package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greendao.Script;

/**
 * Display ending of story and the whole story;
 * Users can restart the story game.
 */
public class gameStoryResultPage extends AppCompatActivity {
    private LinearLayout layout;
    private Resources resources;
    private Drawable btnDrawable;
    private TextView result;
    private Button btn_tryAgain;
    private Button btn_checkStory;
    private ScrollView scrollView;
    private TextView story;
    private static Script script;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_story_result);


        /**
         * goto game interface
         */
        Button btn_game = (Button)findViewById(R.id.backToGame);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameStoryResultPage.this,MainActivity.class);
                intent.putExtra("fragFlag","2");
                startActivity(intent);
                finish();
            }
        });

        //initialize controls
        layout = findViewById(R.id.overall);
        result = findViewById(R.id.result);
        btn_tryAgain = findViewById(R.id.btn_tryagain);
        btn_checkStory = findViewById(R.id.checkStory);
        scrollView = findViewById(R.id.scroll);
        story = findViewById(R.id.story);
        script = gameStoryPage.getScript();

        //set layout background picture
        resources = layout.getContext().getResources();
        btnDrawable = resources.getDrawable(R.drawable.storybg2);
        layout.setBackgroundDrawable(btnDrawable);

        scrollView.setVisibility(View.INVISIBLE);
        setEnding();

        //restart the story game
        btn_tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameStoryResultPage.this,gameStoryPage.class);
                startActivity(intent);
            }
        });

        //display the whole story
        btn_checkStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.VISIBLE);
                setStory();
            }
        });
    }

    /**
     * Display the ending of story
     */
    private void setEnding(){
        String ending = script.getContent();
        String[] strArr = ending.split("\\*");
        result.setText(strArr[0] + "\n" +
                strArr[1] + "\n"+
                strArr[2]);
    }

    /**
     * Display the whole story
     */
    private void setStory(){
        if(script.getRole().equalsIgnoreCase("good ending1")
                ||script.getRole().equalsIgnoreCase("bad ending1")){
            story.setText("The whole stroy:\n" +
                    "Tang Xuanzong Tianbao early years, Li Bai recommended by Taoist priest Wu Yun, by Tang Xuanzong recruited into Beijing, Li Bai for the sacrifice of the hanlin.\n" +
                    "Soon after, due to the slander of the powerful, in the third year of Tianbao (744), Li Bai was forced out of Beijing.\n" +
                    "Since then, Li Bai in the area of the Yangtze river huai linger, thought extremely bored, and once again set foot on the long journey of the motherland.\n" +
                    "Li Bai wrote this poem eight years after he was \"given gold and released\" by emperor of the Tang dynasty.\n" +
                    "During this period, Li Bai and his friend Cen Xun were invited to live in Yingyang mountain of another good friend Yuan Danqiu in Songshan for several times.\n" +
                    "The poet was excluded from politics, frustrated, unable to realize his ideal, and often drank to vent his pent-up depression.\n" +
                    "The pleasure of life is not if buy cocktail party friend, the author is just when \"hold the ability of the world but not in distress\", then filled with inappropriate " +
                    "wine poetry, in order to express the full dissatisfaction\n"+
                    "\n\n" +
                    "The whole poem:\n"+
                    "《将进酒》 李白\n" +
                    "\n" +
                    "君不见黄河之水天上来，奔流到海不复回！ \n" +
                    "君不见高堂明镜悲白发，朝如青丝暮成雪！ \n" +
                    "人生得意须尽欢，莫使金樽空对月。 \n" +
                    "天生我材必有用，千金散尽还复来。 \n" +
                    "烹羊宰牛且为乐，会须一饮三百杯。 \n" +
                    "岑夫子，丹丘生，将进酒，君莫停。 \n" +
                    "与君歌一曲，请君为我倾耳听。 \n" +
                    "钟鼓馔玉不足贵，但愿长醉不复醒。 \n" +
                    "古来圣贤皆寂寞，惟有饮者留其名。 \n" +
                    "陈王昔时宴平乐，斗酒十千恣欢谑。 \n" +
                    "主人何为言少钱？径须沽取对君酌。 \n" +
                    "五花马，千金裘，呼儿将出换美酒，\n" +
                    "与尔同销万古愁。\n" +
                    "\n" +
                    "Cheers!  Li Bai\n" +
                    "\n" +
                    "Don't you see the Yellow River pouring from heaven,\n" +
                    "Rushing to the sea, never to return?\n" +
                    "Don't you see, in the hall the mirror is saddened\n" +
                    "By the grey hair?—\n" +
                    "Young and dark at dawn, but at dusk snowy!\n" +
                    "Ah, let's enjoy ourselves while we can—\n" +
                    "It's wrong to set goblets idle, in the glory of the moon.\n" +
                    "I was created by Heaven, certainly not for nothing.\n" +
                    "As for money, it scatters and gathers as money should.\n" +
                    "For the moment—mutton, beef, and wine—\n" +
                    "Three hundred cups make but one gulp.\n" +
                    "Cheers, my friend, my brother!\n" +
                    "Don't stop your cups.\n" +
                    "Let me sing a song for you,\n" +
                    "Listen carefully please.\n" +
                    "But music and delicacies are nothing:\n" +
                    "I long to dwell in drunkenness, and\n" +
                    "Never wake up to a sober world.\n" +
                    "In oblivion all those sages have died.\n" +
                    "History knows only great drinkers.\n" +
                    "Remember how flavour and humour flew\n" +
                    "At the wronged prince's drinking spree.\n" +
                    "And why should I now, the host, care about money?\n" +
                    "I've got everything, to pay for you and me—\n" +
                    "Here's my precious horse, here's my priceless fur.\n" +
                    "Tell the boy to give them for the wine.\n" +
                    "Let's drink on, to end the endless worry!\n");

        }else if(script.getRole().equalsIgnoreCase("good ending2")
                ||script.getRole().equalsIgnoreCase("bad ending2")){
            story.setText("The whole stroy:\n" +
                    "Du Fu lived in the historical period from prosperity to decline in the Tang Dynasty.\n" +
                    "His poems reflected the social contradictions and people's sufferings at that time. " +
                    "His poems recorded the great historical changes from prosperity to decline in the Tang Dynasty " +
                    "and expressed the lofty Confucian spirit of benevolence and strong sense of hardship.\n" +
                    "Therefore, he was praised as \"the history of poetry\".\n" +
                    "The \"Pressed Officials\" 《石壕吏》 is a poem written by Du Fu.\n" +
                    "This poem reveals the cruelty of the feudal rulers through the story that the author saw with " +
                    "his own eyes that the press officers took advantage of the night to catch people. \n" +
                    "It reflects the grave disaster brought to the broad masses of people by the war caused by the " +
                    "\"An Shi Rebellion\" in the Tang Dynasty and expresses the poet's deep sympathy for the working people. \n" +
                    "It contains lyricism and discussion in narration, with clear love and hate.  The description of scenes and details " +
                    "is natural and true, good at tailoring, prominent in the center, clear in style, solemn and stirring and gloomy. \n" +
                    "It is a model work of realistic literature." +
                    "\n\n" +
                    "The whole poem:\n"+
                    "《石壕吏》 杜甫 \n" +
                    "\n" +
                    "暮投石壕村，有吏夜捉人。\n" +
                    "老翁逾墙走，老妇出门看。\n" +
                    "吏呼一何怒！妇啼一何苦！\n" +
                    "听妇前致词：“三男邺城戍。\n" +
                    "一男附书至，二男新战死。\n" +
                    "存者且偷生，死者长已矣！\n" +
                    "室中更无人，惟有乳下孙。\n" +
                    "有孙母未去，出入无完裙。\n" +
                    "老妪力虽衰，请从吏夜归。\n" +
                    "急应河阳役，犹得备晨炊。\n" +
                    "夜久语声绝，如闻泣幽咽。\n" +
                    "天明登前途，独与老翁别。\n" +
                    "\n" +
                    "The Pressgang at Stone Moat Village  Du Fu\n" +
                    "\n" +
                    "I seek for shelter at nightfall.\n" +
                    "What is the pressgang coming for?\n" +
                    "My old host climbs over the wall;\n" +
                    "My old hostess answers the door.\n" +
                    "How angry is the sergeant’s shout!\n" +
                    "How bitter is the woman’s cry!\n" +
                    "I hear what she tries to speak out.\n" +
                    "“I’d three sons guarding the town high.\n" +
                    "One wrote a letter telling me\n" +
                    "That his brothers were killed in war.\n" +
                    "He’ll keep alive if he can be;\n" +
                    "The dead have passed and are no more.\n" +
                    "In the house there is no man left,\n" +
                    "Except my grandson in the breast\n" +
                    "Of his mother, of all bereft;\n" +
                    "She can’t come out, in tatters dressed.\n" +
                    "Though I’m a woman weak and old,\n" +
                    "I beg to go tonight with you,\n" +
                    "That I may serve in the stronghold\n" +
                    "And cook morning meals as my due.”\n" +
                    "With night her voices fade away;\n" +
                    "I seem to hear still sob and sigh.\n" +
                    "At dawn again I go my way\n" +
                    "And only bid my host goodbye.");
        }else if(script.getRole().equalsIgnoreCase("good ending3")
                ||script.getRole().equalsIgnoreCase("bad ending3")){
            story.setText("The whole stroy:\n" +
                    "Leaving Baidi Town《早发白帝城》is one of the seven masterpieces written by the great poet Li Bai " +
                    "in the Tang dynasty when he returned from his exile in year 759.\n" +
                    "This poem is intended to depict a section of the Yangtze river from the Baidi Town(白帝) " +
                    "to Jiangling(江陵), where the water flows rapidly. \n" +
                    "The poem describes the poet's cheerful mood after being pardoned by wring about the magnificence " +
                    "of the mountains and the smooth flow of the boat along the river."+
                    "\n\n" +
                    "The whole poem:\n"+
                    "《早发白帝城》 李白 \n" +
                    "\n" +
                    "朝辞白帝彩云间，\n" +
                    "千里江陵一日还。\n" +
                    "两岸猿声啼不住，\n" +
                    "轻舟已过万重山。\n" +
                    "\n" +
                    "Leaving Baidi Town  Li Bai\n" +
                    "\n" +
                    "Leaving at dawn Baidi in the colorful clouds,\n" +
                    "I've sailed a thousand li through canyons in a day.\n" +
                    "With monkeys' sad adieus the riverbanks are loud;\n" +
                    "My skiff has left ten thousand mountains far away.");
        }
    }
}