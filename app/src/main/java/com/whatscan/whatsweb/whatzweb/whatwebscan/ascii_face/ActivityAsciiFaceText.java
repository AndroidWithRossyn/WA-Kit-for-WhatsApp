package com.whatscan.whatsweb.whatzweb.whatwebscan.ascii_face;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.AdManager;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.UserHelper;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.EventNotifier;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.EventState;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.IEventListener;
import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.notifier.NotifierFactory;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.ActAsciiFaceTextBinding;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.screens.ActivityMain;


public class ActivityAsciiFaceText extends AppCompatActivity implements View.OnClickListener {

    public static String[] strArr = new String[142];
    ActAsciiFaceTextBinding binding;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActAsciiFaceTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        strArr[0] = "(●´∀｀●)";
        strArr[1] = "(｀・ω・´)”";
        strArr[2] = "ヽ(；▽；)ノ";
        strArr[3] = "(*´・ｖ・)";
        strArr[4] = "(((o(*ﾟ▽ﾟ*)o)))";
        strArr[5] = "☆*:.｡. o(≧▽≦)o .｡.:*☆";
        strArr[6] = "(⌒▽⌒)☆";
        strArr[7] = "⊂((・▽・))⊃";
        strArr[8] = "(≧∇≦)/";
        strArr[9] = "(´∇ﾉ｀*)ノ";
        strArr[10] = "（・◇・）";
        strArr[11] = "( ´ ▽ ` )ﾉ";
        strArr[12] = "（＾_＾）";
        strArr[13] = "（￣ー￣）";
        strArr[14] = "(*^▽^*)";
        strArr[15] = "(＾▽＾)";
        strArr[16] = "（’-’*)";
        strArr[17] = "∩( ・ω・)∩";
        strArr[18] = "(*≧▽≦)";
        strArr[19] = "＼（＾ ＾）／";
        strArr[20] = "Ｏ(≧∇≦)Ｏ";
        strArr[21] = "（　´∀｀）";
        strArr[22] = "(^～^)";
        strArr[23] = "＼（＠￣∇￣＠）／";
        strArr[24] = "(☆^O^☆)";
        strArr[25] = "(★^O^★)";
        strArr[26] = "(☆^ー^☆)";
        strArr[27] = "(´ω｀★)";
        strArr[28] = "＼（Ｔ∇Ｔ）／";
        strArr[29] = "ヽ(*≧ω≦)ﾉ";
        strArr[30] = "*(*´∀｀*)☆";
        strArr[31] = "Ｏ(≧▽≦)Ｏ";
        strArr[32] = "ヽ(*⌒∇⌒*)ﾉ";
        strArr[33] = "d=(´▽｀)=b";
        strArr[34] = "＼(*T▽T*)／";
        strArr[35] = "ヽ(‘ ∇‘ )ノ";
        strArr[36] = "（*＾ワ＾*）";
        strArr[37] = "ヽ(＾Д＾)ﾉ";
        strArr[38] = "(´∀`)";
        strArr[39] = "(°◇°;)";
        strArr[40] = "(゜▽゜;)";
        strArr[41] = "(/^▽^)/";
        strArr[42] = "(ﾉ´ｰ`)ﾉ";
        strArr[43] = "ヽ(´ー`)ﾉ";
        strArr[44] = "(　＾∇＾)";
        strArr[45] = "＼( ｀.∀´)／";
        strArr[46] = "(●⌒∇⌒●)";
        strArr[47] = "o(≧∇≦o)";
        strArr[48] = "ヽ(｀◇´)/";
        strArr[49] = "ヽ(*・ω・)ﾉ";
        strArr[50] = "（＾ω＾）";
        strArr[51] = "｡◕‿◕｡";
        strArr[52] = "⊙ω⊙";
        strArr[53] = "⊙△⊙";
        strArr[54] = "⊙▽⊙";
        strArr[55] = "o (◡‿◡✿)";
        strArr[56] = "(◕‿◕✿)";
        strArr[57] = "(∩_∩)";
        strArr[58] = "｡◕‿◕｡";
        strArr[59] = "(•ิ_•ิ)";
        strArr[60] = "(/•ิ_•ิ)/";
        strArr[61] = "（ΦωΦ）";
        strArr[62] = "（*＾＾*)";
        strArr[63] = "（＾⊆＾）";
        strArr[64] = "(-^〇^-)";
        strArr[65] = "(ノ*゜▽゜*)";
        strArr[66] = "ヾ(´▽｀;)ゝ";
        strArr[67] = "(゜▼゜＊）";
        strArr[68] = "(￣个￣)";
        strArr[69] = "＼(^▽^＠)ノ";
        strArr[70] = "(“⌒∇⌒”)";
        strArr[71] = "へ(゜∇、°)へ";
        strArr[72] = "（-＾〇＾-）";
        strArr[73] = "（＞ｙ＜）";
        strArr[74] = "ヽ(^。^)丿";
        strArr[75] = "(ヘ。ヘ)";
        strArr[76] = "（＾ｖ＾）";
        strArr[77] = "ヾ(@^▽^@)ノ";
        strArr[78] = "ヾ(@°▽°@)ノ";
        strArr[79] = "ヾ（＠＾▽＾＠）ノ";
        strArr[80] = "ヾ(＠^∇^＠)ノ";
        strArr[81] = "o((*^▽^*))o";
        strArr[82] = "ヾ(@゜∇゜@)ノ";
        strArr[83] = "ヾ(＠゜▽゜＠）ノ";
        strArr[84] = "（＠´＿｀＠）";
        strArr[85] = "ヾ(＠† ▽ †＠）ノ";
        strArr[86] = "(ノ＞▽＜。)ノ";
        strArr[87] = "＼（＠；◇；＠）／";
        strArr[88] = "＼(☆o◎)／";
        strArr[89] = "(((＼（＠v＠）／)))";
        strArr[90] = "(°◇°;)";
        strArr[91] = "(゜▽゜;)";
        strArr[92] = "(o;TωT)o";
        strArr[93] = "ヽ(;^o^ヽ)";
        strArr[94] = "（⌒▽⌒ゞ";
        strArr[95] = "“ヽ(´▽｀)ノ”";
        strArr[96] = "(((o(*ﾟ▽ﾟ*)o)))";
        strArr[97] = "ヾ(＠⌒ー⌒＠)ノ";
        strArr[98] = "Ψ(´▽｀)Ψ";
        strArr[99] = "（ｖ＾＿＾）ｖ";
        strArr[100] = "(ી(΄◞ิ౪◟ิ‵)ʃ)♥";
        strArr[101] = "(❁´◡`❁)*✲ﾟ*";
        strArr[102] = "(ღ˘⌣˘ღ) ♫･*:.｡. .｡.:*･";
        strArr[103] = "ლ(╹◡╹ლ)";
        strArr[104] = "(✿◠‿◠)";
        strArr[105] = "(◑‿◐)";
        strArr[106] = "ლ(́◉◞౪◟◉‵ლ)";
        strArr[107] = "✖‿✖";
        strArr[108] = "(*~▽~)";
        strArr[109] = "(─‿‿─)";
        strArr[110] = "∩(︶▽︶)∩";
        strArr[111] = "(︶ω︶)";
        strArr[112] = "(∩▂∩)";
        strArr[113] = "(¬‿¬)";
        strArr[114] = "(n˘v˘•)¬";
        strArr[115] = "ಥ‿ಥ";
        strArr[116] = "≖‿≖";
        strArr[117] = "ʘ‿ʘ";
        strArr[118] = "☆ﾐ(o*･ω･)ﾉ";
        strArr[119] = "(ﾉ´ヮ´)ﾉ*:･ﾟ✧";
        strArr[120] = "(ᅌᴗᅌ* )";
        strArr[121] = "(;ﾞ°´ω°´)";
        strArr[122] = "°˖✧◝(⁰▿⁰)◜✧˖°";
        strArr[123] = "( ͡° ͜ʖ ͡°)";
        strArr[124] = "(•‿•)";
        strArr[125] = "(⊙ヮ⊙)";
        strArr[126] = "☆*･゜ﾟ･*\\(^O^)/*･゜ﾟ･*☆";
        strArr[127] = "(*¬*)";
        strArr[128] = "(*^▽^*)";
        strArr[129] = "(＝⌒▽⌒＝)";
        strArr[130] = "(≡^∇^≡)";
        strArr[131] = "o(〃＾▽＾〃)o";
        strArr[132] = "≧(´▽｀)≦";
        strArr[133] = "（‐＾▽＾‐）";
        strArr[134] = "(￣▽+￣*)";
        strArr[135] = "ヾ(＠^▽^＠)ﾉ";
        strArr[136] = "o(^▽^)o";
        strArr[137] = "(°∀°)b";
        strArr[138] = "о(ж＞▽＜)ｙ ☆";
        strArr[139] = "(*⌒∇⌒*)";
        strArr[140] = "°˖✧◝(⁰▿⁰)◜✧˖°";
        strArr[141] = "(๑╹っ╹๑)";

        binding.cardHappy.setOnClickListener(this);
        binding.cardLove.setOnClickListener(this);
        binding.cardExcited.setOnClickListener(this);
        binding.cardHugging.setOnClickListener(this);
        binding.cardAngry.setOnClickListener(this);
        binding.cardSad.setOnClickListener(this);
        binding.cardCrying.setOnClickListener(this);
        binding.cardWorried.setOnClickListener(this);
        binding.cardWhatever.setOnClickListener(this);
        binding.cardTableFlip.setOnClickListener(this);
        binding.cardTroll.setOnClickListener(this);
        binding.cardSurprised.setOnClickListener(this);
        binding.cardEmbarrassed.setOnClickListener(this);
        binding.cardConfused.setOnClickListener(this);
        binding.cardCat.setOnClickListener(this);
        binding.cardBear.setOnClickListener(this);
        binding.cardRabbit.setOnClickListener(this);
        binding.cardBird.setOnClickListener(this);
        binding.cardDog.setOnClickListener(this);
        binding.cardSea.setOnClickListener(this);


        binding.toolbar.mToolBarThumb.setOnClickListener(v -> onBackPressed());
        binding.toolbar.mToolBarText.setText("Ascii Face");


        if (!UserHelper.getBooleanValue(UserHelper.IS_PRO) && UserHelper.getBooleanValue(UserHelper.Adshow) && UserHelper.getBooleanValue(UserHelper.showInterstitial) && UserHelper.getStringValue(UserHelper.bannerAdId) != null) {
            Log.d("databseConfig", "load Inters");
            AdManager.loadInterAd(ActivityAsciiFaceText.this, UserHelper.getStringValue(UserHelper.interstitialAdId));

            AdManager.loadNativeAds(ActivityAsciiFaceText.this, binding.flAdplaceholder, UserHelper.getStringValue(UserHelper.nativeAdId));
        }
    }

    public void startActivityes(Intent intent) {
        AdManager.adCounter++;
        AdManager.showInterAd(ActivityAsciiFaceText.this, UserHelper.getStringValue(UserHelper.interstitialAdId), intent, 0);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(ActivityAsciiFaceText.this, ActivitySubAsciiFaceText.class);
        switch (v.getId()) {
            case R.id.cardHappy:
                intent.putExtra("AsciiFace", strArr);
                startActivityes(intent);
                break;
            case R.id.cardLove:
                String[] Love = {"( c//”-}{-*\\x)", "(｡-_-｡ )人( ｡-_-｡)", "(‘∀’●)♡", "(*´∀｀*人*´∀｀*)", "(/^-^(^ ^*)/", "(´ ▽｀).。ｏ♡", "(´∀｀)♡", "ヽ(愛´∀｀愛)ノ", "(●♡∀♡)", "( ˘ ³˘)♥", "( ⋆•ิ ᴈ-ิ(ᵕ❥ ᵕ⁎ ॢ)", "(・_・)❤(-_-)", "(｡・//ε//・｡)", "(｡･ω･｡)ﾉ♡", "(｡’▽’｡)♡", "（。ˇ ⊖ˇ）♡", "(｡♥‿♥｡)", "(* ˘⌣˘)◞[_]♥[_]ヽ(•‿• )", "（*´▽｀*）", "（*＾3＾）/～♡", "（〃・ω・〃）", "（´・｀ ）♡", "(´▽`ʃƪ)♡", "(´ε｀ )♡", "（´ω｀♡%）", "(˘▼˘>ԅ( ˘⌣ƪ)", "(ˆˇˆ)-c<˘ˑ˘)", "(>’o’)> ♥ <(‘o’<)", "(>^_^)><(^o^<)", "(∩˃o˂∩)♡", "(∿°○°)∿ ︵ ǝʌol", "(◍•ᴗ•◍)❤", "(♥ó㉨ò)ﾉ♡", "(♥ω♥ ) ~♪", "(♥ω♥*)", "(Ɔ ˘⌣˘)♥(˘⌣˘ C)", "(ɔˆ ³(ˆ⌣ˆc)", "(ʃƪ ˘ ³˘)", "(ʃƪ˘ﻬ˘)", "(ღ˘⌣˘ღ)", "(ી(΄◞ิ౪◟ิ‵)ʃ)♥", "(づ￣ ³￣)づ", "(っ˘з(˘⌣˘ )", "(人-ω-)｡o.ﾟ｡*･♡Good Night♡･*｡ﾟo｡(-ω-人)", "♡〜٩(^▿^)۶〜♡", "♡｡ﾟ.(*♡´‿` 人´‿` ♡*)ﾟ♡ °・", "♡(*´･ω･)(･ω･`*)♡", "♡(*´∀｀*)人(*´∀｀*)♡", "♡(˃͈ દ ˂͈ ༶ )", "♡´･ᴗ･`♡", "♡＾▽＾♡", "♡꒰*･ω･人･ω･*꒱♡", "♡o｡(๑๏‿ฺ๏๑)｡o♡", "♥(ˆ⌣ˆԅ)", "♥(✿ฺ´∀`✿ฺ)ﾉ", "♥（ﾉ´∀`）", "♥～(‘▽^人)", "♥╣[-_-]╠♥", "✿♥‿♥✿", "꒰˘̩̩̩⌣˘̩̩̩๑꒱♡", "꒰♡ˊ͈ ु꒳ ूˋ͈꒱.⑅*♡", "ლ(́◉◞౪◟◉‵ლ)", "웃+웃=❤", "乂❤‿❤乂", "( ᵒ̴̶̷̤̀ुωᵒ̴̶̷̤́ू )❤”", "♡ლ(-༗‿༗-)ლ♡", "☆*✲ﾟ*｡(((´♡‿♡`+)))｡*ﾟ✲*☆", "٩(๑ơలơ)۶♡", "♡ლζ(♛ε♛*ζლ♡", "(ෆ ͒•∘̬• ͒)◞", "ॱ॰⋆(˶ॢ‾᷄﹃‾᷅˵ॢ)ӵᵘᵐᵐᵞ♡♡♡", "♡(㋭ ਊ ㋲)♡", "੯ूᵕ̤ू U॒॒॒॒॒୭ℒℴѵℯ❤", "(♡´͈༝`͈)ฅ˒˒", "(๑′ᴗ‵๑)Ｉ Lᵒᵛᵉᵧₒᵤ♥", "♡ℒฺℴฺνℯฺ♡", "(*´o`*)ʖˋʖˋʖˋ～♡", "(๑•з•)))⋆*♡*⋆ฺ=͟͟͞͞=͟͟͞͞", "♡+:｡.｡❣LﾛVЁ❣｡.｡:+♡", "˚‧*♡ॢ˃̶̤̀◡˂̶̤́♡ॢ*‧˚", "(๑′ڡ‵๑)۶४४yϋᵐᵐӵ♡॰⋆̥", "( ˭̵̵̵̵͈́◡ु͂˭̵̵̵͈̀ )ˉ̞̭♡", "ლ(´◉❥◉｀ლ)", "(๑・ω-)～♥”", "( ＾◡＾)っ✂❤", "（●´∀｀）ノ♡", "◌⑅⃝●♡⋆♡⃝ ˻˳˯ₑ♡⃝⋆●♡⑅⃝◌", "", "(♡ᵉ̷͈ัॢωᵉ̷͈ัॢ )‧₊°♡", "(✿ ♥‿♥)", "ฅ ̳͒•ˑ̫• ̳͒ฅ♡", "(｡・‧̫・｡).*＊♡", "(♡ˊ͈ ॢ꒳ ॢˋ͈)♪", "♡+* Ɗɑɫë*+♡", "(͒ ॢ ›⚇‹ ॢ)͒୭♡", "(*°∀°)=3", "( ๑ ᴖ ᴈ ᴖ)ᴖ ᴑ ᴖ๑)❣", "(/∇＼*)｡o○♡", "(۶•౪•)۶❤٩(•౪•٩)", "(ღ˘⌣˘)❛ั◡❛ัღ)", "(*´ ˘ `*).｡oO ( ♡ )", "´͈ ᵕ `͈ ♡°◌̊", "(*• ुᴗ•ධ̢•͈ꄃ̑•͈ ධ̡੭ु⁾⁾·°♡", "( ´͈ ૢᐜ `͈ૢ)･*♡੭ੇ৸♡੭ੇ৸♡", "( ′ॢ◡̶͂‵ ॢ )♡*.", "˄̻ ̊σ(˃̴͈◡ुමੈॆ⋆)˄̻ ̊~♡⃛", "(୨୧•͈ᴗ•͈)◞ᵗʱᵃᵑᵏઽ*♡", "*♡೫̥͙*:・ℋɑppყ Ϣәԁԁıɲɠﾟ･:* ೫̥͙♡*", "٩(ó｡ò۶ ♡)))♬", "(≚ᄌ≚)ℒℴѵℯ❤", "◟( ˊ̱˂˃ˋ̱ )◞♡⃛◟( ˊ̱˂˃ˋ̱ )◞", "(๑॔˃̶ॢ◟◞ ˂̶ॢ๑॓)♡", "", "₍՞◌′ᵕ‵ू◌₎♡", "（人´∀`*）", "( •ॢ◡-ॢ)-♡", "( •ॢ◡-ॢ)-♡", "ପ(๑•̀ᴗ-♡ॢ)fෆr yෆu*೨⋆*✩", "٩(๑•◡-๑)۶ⒽⓤⒼ❤", "～(^з^)-♡", "ヾ(◍’౪`◍)ﾉﾞ♡", "(●´□`)♡", "ლ(|||⌒εー|||)ლ", "ƪ(♥ﻬ♥)ʃ", "ღゝ◡╹)ノ♡", "(‾̴̴͡͡▿•‾̴̴͡͡ʃƪ)", "(ෆ❛ั ु▿❛ั੯ू❛ัू ໒꒱⁼³₌₃", "♡(.◜ω◝.)♡"};
                intent.putExtra("AsciiFace", Love);
                startActivityes(intent);
                break;
            case R.id.cardExcited:
                String[] Excited = {"(((o(*ﾟ▽ﾟ*)o)))", "(★^O^★)", "(ﾉ･ｪ･)ﾉ", "＼（＠￣∇￣＠）／", "＼(^▽^＠)ノ", "ヾ(@^▽^@)ノ", "o((*^▽^*))o", "Ｏ(≧▽≦)Ｏ", "(((＼（＠v＠）／)))", "(((o(*ﾟ▽ﾟ*)o)))", "(* >ω<)", "(*≧▽≦)", "(/^▽^)/", "(ﾉ´ヮ´)ﾉ*:･ﾟ✧", "(ﾉ≧∀≦)ﾉ", "(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧", "\\( ‘з’)/", "＼(*T▽T*)／", "＼（＾▽＾）／", "＼（Ｔ∇Ｔ）／", "☆*･゜ﾟ･*\\(^O^)/*･゜ﾟ･*☆", "☆*:.｡. o(≧▽≦)o .｡.:*☆", "ヽ( ★ω★)ノ", "ヽ(;^o^ヽ)", "ヽ(；▽；)ノ", "ヾ(。◕ฺ∀◕ฺ)ノ", "ヾ(＠† ▽ †＠）ノ", "ヾ(＠^∇^＠)ノ", "ヾ(＠^▽^＠)ﾉ", "ヾ（＠＾▽＾＠）ノ", "ヾ(＠゜▽゜＠）ノ", "ヾ(@°▽°@)ノ", "ヾ(＠°▽°＠)ﾉ", "ヽ(*≧ω≦)ﾉ", "ヽ(*⌒∇⌒*)ﾉ", "ヽ(^。^)丿", "ヽ(＾Д＾)ﾉ", "ヽ(=^･ω･^=)丿", "o(〃＾▽＾〃)o", "o(^▽^)o", "Ｏ(≧∇≦)Ｏ", "o(≧∇≦o)", "о(ж＞▽＜)ｙ ☆", "٩(^ᴗ^)۶", "(๑>ᴗ<๑)", "٩(•̤̀ᵕ•̤́๑)ᵒᵏᵎᵎᵎᵎ", "୧(﹒︠ᴗ﹒︡)୨", "˚₊*(ˊॢo̶̶̷̤◡ुo̴̶̷̤ˋॢ)*₊˚⁎", "∗˚(* ˃̤൬˂̤ *)˚∗", "•ू(ᵒ̴̶̷ωᵒ̴̶̷*•ू) ​ )੭ु⁾", "(❛ัॢᵕ❛ั ॢ)✩*ೃ.⋆", "⁺✧.(˃̶ ॣ⌣ ॣ˂̶∗̀)ɞ⁾", "٩(●˙▿˙●)۶…⋆ฺ", "*✲ﾟ*｡✧٩(･ิᴗ･ิ๑)۶*✲ﾟ*｡✧", "σ(≧ε≦ｏ)", "٩(๑ơలơ)۶♡", "˚‧*♡ॢ˃̶̤̀◡˂̶̤́♡ॢ*‧", "( ˃̶ω˂̶ ૃ)", "(٭°̧̧̧ω°̧̧̧٭)", "૮(ᶿ̴͈᷇ॢ௰ᶿ̴͈᷆ॢ)ა✧", "٩(ó｡ò۶ ♡)))♬", "␟␏(ɲ˃ ˈ̫̮ ˂ɳ)␟␏ෆ"};
                intent.putExtra("AsciiFace", Excited);
                startActivityes(intent);
                break;
            case R.id.cardHugging:
                String[] Hugging = {"⊂((・▽・))⊃ ", "(>^_^)><(^o^<)", "(Ɔ˘⌣˘)(˘⌣˘)˘⌣˘ C)", "(づ｡◕‿‿◕｡)づ", "(づ￣ ³￣)づ", "(っ˘̩╭╮˘̩)っ", "＼(^o^)／ ", "d=(´▽｀)=b", "ლ(́◉◞౪◟◉‵ლ)", "٩꒰ ˘ ³˘꒱۶ⒽⓤⒼ♥♡̷♡̷", "٩(๑•◡-๑)۶ⒽⓤⒼ❤", "(ღ˘⌣˘)❛ั◡❛ัღ)"};
                intent.putExtra("AsciiFace", Hugging);
                startActivityes(intent);
                break;
            case R.id.cardAngry:
                String[] Angry = {"(*≧m≦*)", "(>_<)", "(,,#ﾟДﾟ)", "ヽ(ｏ`皿′ｏ)ﾉ", "o(>< )o", "ｏ( ><)o", "ヽ(≧Д≦)ノ", "（＞д＜）", "（;≧皿≦）", "[○･｀Д´･○]", "ヽ(#`Д´)ﾉ", "Σ(-`Д´-ﾉ；)ﾉ", "(((p(>o<)q)))", "(/ﾟДﾟ)/", "(¬д¬。)", "ヽ(#`Д´)ﾉ", "(¬､¬)", "（；¬＿¬)", "(;¬_¬)", "(；¬д¬)", "(≧σ≦)", "o(-`д´- ｡)", "ヽ(●-`Д´-)ノ", "(*￣m￣)", "(´Д｀)", "(；￣Д￣）", "(¬_¬)ﾉ", "(＃｀д´)ﾉ", "(」゜ロ゜)」", "Σ(▼□▼メ)", "(━┳━ _ ━┳━)", "(┳◇┳)", "{{|└(>o< )┘|}}", "＼(＠O＠)／", "（≧▼≦；)", "＼(〇O〇)／", "s(・｀ヘ´・;)ゞ", "（▼へ▼メ）", "＼(`O´θ／", "θ＼(；￢_￢)", "ヽ(｀⌒´メ)ノ", "凸(-0-メ)", "凸(｀△´＋）", "凸(｀0´)凸", "凸(｀⌒´メ)凸", "＼(｀0´)／", "-`д´-", "(>人<)", "щ(ಠ益ಠщ)", "(ノಠ益ಠ)ノ", "щ(ಥДಥщ)", "o(≧o≦)o", "(⋋▂⋌)", "(◣_◢)", "ᕙ(⇀‸↼‶)ᕗ", "ᕦ(ò_óˇ)ᕤ", "( ಠ ಠ )", "(¬▂¬)", "(¬_¬)", "ಥ⌣ಥ", "ಠ_ಠ", "s(・｀ヘ´・；)", "ლ(ಠ益ಠლ", "ლಠ益ಠ)ლ", "凸ಠ益ಠ)凸", "╭(๑¯д¯๑)╮", "ಠ▃ಠ", "t(-_-t)", "ヽ(#ﾟДﾟ)ﾉ┌┛", "(҂⌣̀_⌣́)", "ͼ(ݓ_ݓ)ͽ", "(ಥ⌣ಥ)"};
                intent.putExtra("AsciiFace", Angry);
                startActivity(intent);
                break;
            case R.id.cardSad:
                String[] Sad = {"（ ; ; ）", "(T＿T)", "（ ＴДＴ）", "(ToT)", "(Ｔ▽Ｔ)", "(‘A`)", "(T_T)", "((T.T； )", "(； T.T))", "（；へ：）", "(ノД`)・゜・。", "・(/Д`)・", "(´＿｀。)", "(´Д｀。)", "(´Ａ｀。)", "(´∩｀。)", "｡：ﾟ(｡ﾉω＼｡)ﾟ･｡", "(┳Д┳)", "(´；д；`)", "(*´；ェ；`*)", "｡･ﾟﾟ･(>д<)･ﾟﾟ･｡", "(。┰ω┰。)", "(゜´Д｀゜)", "（ｉДｉ）", "(´；ω；`)", "。ﾟ(ﾟﾉД｀ﾟ)ﾟ｡", "ヽ(´□｀。)ﾉ", "(ﾟ´Д｀ﾟ)ﾟ", "{{p´Д｀q}}", "ヽ(●ﾟ´Д｀ﾟ●)ﾉﾟ", "( p_q)", "(╯︵╰,)", "(个_个)", "((´д｀))", "( ≧Д≦)", "｡゜(｀Д´)゜｡", "(。┰ω┰。)", "p(´⌒｀｡q)", "(/□＼*)・゜", "((o(;△;)o))", "（；￣д￣）", "(ㄒoㄒ)", "⊙︿⊙", "o(╥﹏╥)o", "o(；△；)o", "(;*△*;)", "（´＿｀）", "╥﹏╥", "(∩︵∩)", "(╯︵╰,)", "(︶︹︺)", "(╥_╥)", "(っ˘̩╭╮˘̩)っ", "ಥ_ಥ", "╥﹏╥", "(T⌓T)"};
                intent.putExtra("AsciiFace", Sad);
                startActivityes(intent);
                break;
            case R.id.cardCrying:
                String[] Crying = {"｡ﾟ(ﾟ´(00)`ﾟ)ﾟ｡", "( p_q)", "(;*△*;)", "(´°̥̥̥̥̥̥̥̥ω°̥̥̥̥̥̥̥̥｀)", "(╥_╥)", "(╯︵╰,)", "(oT-T)尸", "·´¯`(>▂<)´¯`·", "｡･ﾟﾟ･(>д<)･ﾟﾟ･｡", "｡･ﾟヾ(✦థ ｪ థ)ﾉ｡ﾟ･｡", "｡：ﾟ(｡ﾉω＼｡)ﾟ･｡", "。：゜(；´∩｀；)゜：。", "｡ﾟ( ﾟஇ‸இﾟ)ﾟ｡", "。ﾟ(ﾟﾉД｀ﾟ)ﾟ｡", "｡゜(｀Д´)゜｡", "（ ; ; ）", "（ ＴДＴ）", "(-̩̩-̩̩͡_-̩̩-̩̩͡)", "(-̩̩̩-̩̩̩-̩̩̩-̩̩̩-̩̩̩___-̩̩̩-̩̩̩-̩̩̩-̩̩̩-̩̩̩)", "(； T.T))", "(； T.T))", "(。┰ω┰。)", "((o(;△;)o))", "((T.T； )", "(*´；ェ；`*)", "(*´°̥̥̥̥̥̥̥̥﹏°̥̥̥̥̥̥̥̥ )人(´°̥̥̥̥̥̥̥̥ω°̥̥̥̥̥̥̥̥｀)", "(/ _ ; )", "(/□＼*)・゜", "(´；ω；`)", "(´；д；`)", "(´；Д；｀)", "(┳Д┳)", "(╥_╥)", "（ｉДｉ）", "(o;TωT)o", "(Ｔ▽Ｔ)", "(ToT)", "（πーπ）", "(இ﹏இ`｡)", "(ノД`)・゜・。", "(ㄒoㄒ)", "(个_个)", "༼ ༎ຶ ෴ ༎ຶ༽", "༼ ༏༏ີཻ༾ﾍ ༏༏ີཻ༾༾༽༽", "＼（＠；◇；＠）／", "＼(*T▽T*)／", "＼（Ｔ∇Ｔ）／", "❣◕ ‿ ◕❣", "ヽ(；▽；)ノ", "ヾ(＠† ▽ †＠）ノ", "o(；△；)o", "o(╥﹏╥)o", "o(TヘTo)", "ͼ(ݓ_ݓ)ͽ", "(づ-̩̩̩-̩̩̩_-̩̩̩-̩̩̩)づ", "( ͒ ඉ .̫ ඉ ͒)", "ლ(ٱ٥ٱლ)", "(٭°̧̧̧ω°̧̧̧٭)", "(●⌇ຶ ཅ⌇ຶ●)", "( •́દ•̩̥̀ )", "(ఠ్ఠ ˓̭ ఠ్ఠ)", "(๑′̥̥̥▵‵̥̥̥ ૂ๑)", "(๑•́₋•̩̥̀๑)", "(T＿T)", "(T_T)", "⁽ƈ ͡ (ुŏ̥̥̥̥םŏ̥̥̥̥) ु", "…(•̩̩̩̩＿•̩̩̩̩)", "°(ಗдಗ。)°.", "╥﹏╥", "˚‧⁺(͘๑̊/﹏\\)⁺‧˚", "̨(༎ິ῀̫ ༎ິ ̨ )͞˭̳̳̳˭̳̳̳ˍ̿̿ˍ̿ˌ˳ˏ̇⋅∴༣", "(≖͞_≖̥)", "⁽⁽ƈ ͡ (ु ˲̥̥̥́ ˱̥̥̥̀) ु⁾⁾", "(๑˃̥̩̥̥̥̥̆ಐ˂̩̩̥̥̩̥̆৭)", "‧⁺◟( ᵒ̴̶̷̥́ ·̫ ᵒ̴̶̷̣̥̀ )", "ƈ ͡ (ुŏ̥̥̥̥ ‸ ŏ̥̥̥̥) ु"};
                intent.putExtra("AsciiFace", Crying);
                startActivity(intent);
                break;
            case R.id.cardWorried:
                String[] Worried = {"(ーー;)", "( ；´Д｀)", "（；￣ェ￣）", "ヽ(￣д￣;)ノ", "(￣◇￣;)", "ヽ(￣д￣;)ノ", "（−＿−；）", "(~_~;)", "((*゜Д゜)ゞ”", "(･_-｡ )", "⊙﹏⊙", "ミ●﹏☉ミ", "(-’๏_๏’-)", "(⊙…⊙ )", "( ´△｀)", "(;° ロ°)", "ヽ（゜ロ゜；）ノ", "（°o°；）", "｡:ﾟ*+;(●´･д･`●);+*ﾟ:｡", "(;´Д`)", "\\(￣□￣)\\", "(￣□￣;)!!", "(＠￣Д￣＠；)", "(⊙︿⊙✿)", "(⊙△⊙✿)", "(꒪⌓꒪)", "Σ(ﾟﾛﾟ｣)｣", "Σ（ﾟдﾟlll）"};
                intent.putExtra("AsciiFace", Worried);
                startActivityes(intent);
                break;
            case R.id.cardWhatever:
                String[] Whatever = {"╮(─▽─)╭", "¯\\_(ツ)_/¯", "٩(-̮̮̃-̃)۶", "ヽ（´ー｀）┌", "┗┐ヽ(′Д、`*)ﾉ┌┛", "ヽ( ´¬`)ノ", "┗┃・ ■ ・┃┛", "ヾ(´A｀)ノﾟ", "ヽ（・＿・；)ノ", "ヽ(　￣д￣;)ノ", "＼（〇_ｏ）／", "ヽ(。_°)ノ", "＼(;´□｀)/", "ヾ(*´ー`)ノ", "ヽ(‘ー`)ノ", "ヽ(ー_ー )ノ", "ヽ(´～｀；）", "┐(‘～`；)┌", "ヽ（*ω。）ノ", "(;´・`)>", "（＾～＾）", "٩(-̮̮̃•̃)۶", "┐(￣ー￣)┌", "t(ツ)_/¯", "┐(￣ヮ￣)┌", "╰(　´◔　ω　◔ `)╯", "ಠ_ಠ", "ლ(ಠ_ಠლ)", "¯\\(°_o)/¯", "╮(╯▽╰)╭"};
                intent.putExtra("AsciiFace", Whatever);
                startActivity(intent);
                break;
            case R.id.cardTableFlip:
                String[] TableFlip = {"(╯°□°）╯︵ ┻━┻", "┬──┬◡ﾉ(° -°ﾉ)", "(╯°Д°）╯︵/(.□ . \\)", "(ノಠ益ಠ)ノ彡┻━┻", "(ノಠ ∩ಠ)ノ彡( \\o°o)\\", "(╯°□°)╯︵ ʞooqǝɔɐℲ", "ノ┬─┬ノ ︵ ( \\o°o)\\", "/( .□.)\\ ︵╰(゜益゜)╯︵ /(.□. /)", "(∿°○°)∿ ︵ ǝʌol", "ʕノ•ᴥ•ʔノ ︵ ┻━┻", "(/¯◡ ‿ ◡)/¯ ~ ┻━┻", "( ｀o)ﾉﾉ ┫", "( ﾉo|o)ﾉ ┫｡ﾟ:.:", "（；－－）ノノ ┫：・゜’", "(/-o-)/ ⌒ ┤", "(/#-_-)/~┻┻〃", "(/｀ο´)/ ⌒ ┫:’ﾟ:｡･,. 。゜", "（/＞□＜）/亠亠", "(/ToT)/ ~┻┻", "(/ToT)/_┫・..", "(ノ ﾟДﾟ)ノ　＝＝＝＝　┻━━┻", "(ノ-_-)ノ ~┻━┻", "(ノ-_-)ノ ~┻━┻ (/o＼)", "(ﾉ-_-)ﾉ ~┻━┻ ☆`", "(ノ－＿－）ノ　┫〝〟∵", "（ノ－＿－）ノ・・・~~~┻┻", "(ノ-_-)ノ・・・~~┻━┻", "(ノ-。-）ノ┻━┻☆(　　^)", "(ノ-0-)ノ　┫∵：．", "（ノ－ｏ－）ノ　”″┻━┻☆（>○<）", "(ﾉ-ｏ-)ﾉ ~┫：・’.：：・", "(ﾉ-ｏ-)ﾉ ~┫：・’.：：・", "(ノ-o-)ノ⌒┳ ┫┻┣", "(ノ-o-)ノ┸┸)`3゜)・;’.", "(ﾉ；；)ﾉ~┻━┻", "(ノ；o；)ノ ┫:･’.::･┻┻:･’.::･", "(ノ；ω；)ノ ┫:･’.::･┻┻:･’.::･", "(ﾉ*’‐’)ﾉ ﾐ ┸┸", "(ノ#-_-)ノ ミ　┴┴", "(ノ#-◇-)ノ ~~~~┻━┻☆(x _ x)ノ", "（ノ｀_´）ﾉ~~┴┴", "(ノ｀´）ノミ┻┻", "ノToT)ノ ~┻┻", "(ﾉ｀□´)ﾉ⌒┻━┻", "(ノ｀０)ノ ⌒┫ ┻ ┣ ┳☆(x x)", "（ノ｀_´）ﾉ~~┴┴", "(ノ｀´）ノミ┻┻", "(ノ｀m´)ノ ~┻━┻ (/o＼)", "(ﾉ｀Д)ﾉ:・’∵:.┻┻", "（ﾉ｀Д´）ﾉ－－－－－┻┻　-３-３", "(ﾉ`Д´)ﾉ.:･┻┻)｀з゜)･:ﾞ;", "┻━┻ミ＼（≧ロ≦＼）", "(ノ￣＿￣）ノ　┫〝〟∵", "(ノ￣＿￣)ノ＼。:・゛。", "(丿>ロ<)丿 ┤∵:.", "(ﾉToT)ﾉ ﾐ ┸┸", "(ﾉ￣□￣)ﾉ ~┻━┻", "(ノ￣▽￣)ノ┻━┻☆)*￣□)ノ))", "(ノ￣◇￣)ノ~┻━┻/(×。×)", "（ノ￣ー￣）ノ　┫：・’.::", "(ノ￣ー￣）ノ　┫〝〟∵", "(メ–)ノノ。。。┻┻", "(ﾉ＝ﾟﾛﾟ)ﾉ ⌒┫:･’.::", "(ノ＞o＜)ノ ┫:･’.::", "（ノ≧∇≦）ノ　┫　゜・∵。", "(ﾉ≧∇≦)ﾉ ﾐ ┸┸", "（ノ≧ο≦）ノ　┫　゜・∵。", "（ノ○Д○）ノ＝＝＝┠", "（ノ♯｀△´）ノ~’┻━┻", "（ノー”ー）ノ　┫　゜・∵。", "（ノT＿T)ノ ＾┻━┻", "(ノToT)ノ ~┻┻", "(ノToT)ノ ┫:・’.::・", "(ﾉToT)ﾉ ┫:･’.::･＼┻┻(･_＼)", "(ノToT)ノ ┫:・’.::・┻┻:・’.::・"};
                intent.putExtra("AsciiFace", TableFlip);
                startActivity(intent);
                break;
            case R.id.cardTroll:
                String[] Troll = {"ヽ༼ ಠ益ಠ ༽ﾉ", "(((༼•̫͡•༽)))", "ヽ༼ຈل͜ຈ༽ﾉ", " ༼(⁽͇ˊ̑⁾ ἴृ ⁽ˋ̑⁾͇)༽", "(ી(΄◞ิ౪◟ิ‵)ʃ)", "༼ ͒ ͓ ͒༽", "༼ ͒ ̶ ͒༽", "༼ ༎ຶ ෴ ༎ຶ༽", "༼ ༏༏ີཻ༾ﾍ ༏༏ີཻ༾༾༽༽", "༼･ิɷ･ิ༽", "(̿▀̿ ̿Ĺ̯̿̿▀̿ ̿)̄", "༼༼;; ;°;ਊ°;༽", "༼•͟ ͜ •༽", "༼•̃͡ ɷ•̃͡༽", "༼∵༽", "༼≖ɷ≖༽", "༼⍢༽", "༼⍤༽", "༼⍨༽", "༼✷ɷ✷༽", "˚✧₊⁎❝᷀ົཽ≀ˍ̮ ❝᷀ົཽ⁎⁺˳✧༚", "༼ԾɷԾ༽", "༼இɷஇ༽", "༼;´༎ຶ ༎ຶ ༽", "˓ ू༼ ்ͦ॔ཀ ்ͦ॓ू༽", "", "༼ ु ்ͦ॔ཫ ்ͦ॓༽ु˒˒", "૮(ㅍ◞◟ㅍ)ა", "ཥ•̫͡•ཤ", "♡(•ི̛ᴗ•̛)ྀ", "♡*(ू•‧̫•ू⑅)♡⋆*ೃ:.✧", "ˁ ¨̫͌ ˀ"};
                intent.putExtra("AsciiFace", Troll);
                startActivityes(intent);
                break;
            case R.id.cardSurprised:
                String[] Surprised = {"( ꒪Д꒪)ノ", "(((( ;°Д°))))", "((((；゜Д゜)))", "（゜◇゜）", "（￣□￣；）", "∑(O_O；)", "＼(>o<)ノ", "━Σ(ﾟДﾟ|||)━", "Σ(゜ロ゜;)", "Σ(゜゜)", "(*ﾟﾛﾟ)", "(」゜ロ゜)」", "щ(゜ロ゜щ)", "(ﾉﾟ0ﾟ)ﾉ~", "⊙▂⊙", "⊙０⊙", "w(°ｏ°)w", "L(・o・)」", "(○o○)", "（・□・；）", "((((；゜Д゜)))", "∑(;°Д°)", "(´⊙ω⊙`)！", "\\(*0*)/"};
                intent.putExtra("AsciiFace", Surprised);
                startActivity(intent);
                break;
            case R.id.cardEmbarrassed:
                String[] Embarrassed = {"(^_^;)", "(^^ゞ", "(^^;)", "(#^.^#)", "(‘-’*)", "(*^^*)", "（＠´＿｀＠）", "(⌒_⌒;)", "(#｀ε´#ゞ", "（*/∇＼*）", "(〃￣ω￣〃ゞ", "(*´ｪ｀*)", "(*´∀`*)", "(*´_ゝ｀)", "(*´∀`*)", "(/ω＼)", "(*ﾉ▽ﾉ)", "(*ﾉωﾉ)", "（/｡＼)", "（。-＿-。）", "(#／。＼#)", "(╯_╰)", "(╯ಊ╰)", "(ﾉ)´∀｀(ヾ)", "(*/ω＼*)", "\\(//∇//)\\", "(*ﾟｰﾟ)ゞ", "\\(///Σ///)\\", "（//･_･//)", "(⊙﹏⊙✿)", "(<o///v>)"};
                intent.putExtra("AsciiFace", Embarrassed);
                startActivity(intent);
                break;
            case R.id.cardConfused:
                String[] Confused = {"(´･_･`)", "(◎_◎;)", "(｀_´)ゞ", "(◎-◎；)", "(・_・ヾ", "(￣(エ)￣)ゞ", "(-_-)ゞ゛", "(‘◇’)?", "ヽ(゜Q。)ノ？", "(´｀;) ？", "【・ヘ・?】", "【・_・?】", "( ・◇・)？", "(゜-゜)", "ヾ(´･ ･｀｡)ノ”", "｢(ﾟﾍﾟ)", "( ?´_ゝ｀)", "o(´^｀)o", "(´−｀) ﾝｰ", "(」ﾟヘﾟ)」", "(•ิ_•ิ)?", "「(°ヘ°)", "(。ヘ°)", "ヽ(゜Q。)ノ？", "(´｀;) ？", "〈(゜。゜)", "(゜。゜)", "く（＾_・）ゝ", "(^～^;)ゞ", "٩(͡๏̯͡๏)۶", "٩(̾●̮̮̃̾•̃̾)۶", "(⊙_◎)", "ಠ_ರೃ", "щ(ºДºщ)", "﴾͡๏̯͡๏﴿", "ఠ_ఠ", "(ﾟｰﾟ;", "(●´ω｀●)ゞ", "(＃⌒∇⌒＃)ゞ", "(・・。)ゞ", "⁀⊙﹏☉⁀", "(♠_♦)", "(⊙_☉)", "(⊙.☉)7", "`(๑ △ ๑)`*", "(」ﾟﾛﾟ)｣", "(C_C)"};
                intent.putExtra("AsciiFace", Confused);
                startActivityes(intent);
                break;
            case R.id.cardCat:
                String[] Cat = {"(=^･ｪ･^=)", "(=^‥^=)", "(=^･^=)", "V(=^･ω･^=)v", "(=｀ω´=)", "o(^・x・^)o", "＼(=^‥^)/’`", "( =①ω①=)", "d(=^･ω･^=)b", "(=ＴェＴ=)", "(=;ェ;=)", "ヽ(=^･ω･^=)丿", "(=^･ω･^)y＝", "(^-人-^)", "ヽ(^‥^=ゞ)", "(^・ω・^ )", "(=^-ω-^=)", "b(=^‥^=)o", "(=^・ェ・^=)", "（=´∇｀=）", "ヾ(=ﾟ･ﾟ=)ﾉ", "~(=^‥^)ノ", "(=ｘェｘ=)", "(=；ェ；=)", "(=｀ェ´=)", "(^･o･^)ﾉ”", "(^._.^)ﾉ", "└(=^‥^=)┐", "=’①。①’=", "ㅇㅅㅇ", "(・∀・)", "(^人^)"};
                intent.putExtra("AsciiFace", Cat);
                startActivity(intent);
                break;
            case R.id.cardBear:
                String[] Bear = {"(￣(エ)￣)", "(｡･ω･｡)", "(●｀･(ｴ)･´●)", "(*ノ・ω・）", "川´･ω･`川", "( (ﾐ´ω`ﾐ))", "ヾ(T(エ)Tヽ)", "＼(・｀(ｪ)・)/", "⊂(ο･㉨･ο）⊃", "(^(エ)^)", "(^(I)^)", "(￣(ｴ)￣)ﾉ", "⊂(￣(工)￣)⊃", "⊂(^(工)^)⊃", "⊂(・(ェ)・)⊃", "(*￣(ｴ)￣*)", "ヾ(´(ｴ)｀ﾉﾞ", "(／(ｴ)＼)", "⊂(￣(ｴ)￣)⊃", "“(`(エ)´)ノ", "(｀(エ)´)ﾉ", "(ó㉨ò)", "(♥ó㉨ò)ﾉ♡", "(/-(ｴ)-＼)", "(´(エ)｀)", "ヽ(￣(ｴ)￣)ﾉ", "(／￣(ｴ)￣)／", "⊂(◎(工)◎)⊃", "(●￣(ｴ)￣●)", "《/(￣(ｴ)￣)ゞ》", "⁝⁞⁝⁞ʕु•̫͡•ʔु☂⁝⁞⁝⁝", "(✪㉨✪)", "ᶘ ᵒᴥᵒᶅ"};
                intent.putExtra("AsciiFace", Bear);
                startActivity(intent);
                break;
            case R.id.cardRabbit:
                String[] Rabbit = {"U・x・U", "U｡･.･｡U", "U=･ x ･=U", "U=๏ x ๏=U", "(,,๏ ⋏ ๏,,)", "(•ㅅ•)", "(๏ᆺ๏υ)", "(ㅇㅅㅇ❀)", "／(･ × ･)＼", "／(^ x ^)＼", "／(^ x ^=)＼", "／(=๏ x ๏=)＼", "／(=´x`=)＼", "／(=∵=)＼", "／(=⌒x⌒=)＼", "／(=✪ x ✪=)＼", "／(≡・ x ・≡)＼", "／(≧ x ≦)＼", "／(v x v)＼", "⌒(・x・)⌒", "⌒(｡･.･｡)⌒", "⌒(=･ x ･=)⌒", "⌒(=๏ x ๏=)⌒", "U ´꓃ ` U", "⁽⁽˙˟˙⁾⁾", "(⁎˃ᆺ˂)", "૮⍝◜•˕̮•◝⍝ა", "૮( ᵒ̌ૢ௰ᵒ̌ૢ )ა", "૮(⋆❛ہ❛⋆)ა", "( ்̓˟்̓)"};
                intent.putExtra("AsciiFace", Rabbit);
                startActivityes(intent);
                break;
            case R.id.cardBird:
                String[] Bird = {"（・⊝・）", "（・⊝・∞）", "（・θ・）", "（＠◇＠）", "(•∋•)", "（`･⊝･´ ）", "(｀Θ´)", "(°<°)", "（ﾟ∈ﾟ）", "(◉Θ◉)", "(●∈∋●)", "⊹⋛⋋( ՞ਊ ՞)⋌⋚⊹", "◎▼◎", "ˏ₍•ɞ•₎ˎ", "ˎ₍•ʚ•₎ˏ", "♡(㋭ ਊ ㋲)♡", "꜀( ˊ̠˂˃ˋ̠ )꜆", "ꉂ (๑¯ਊ¯)σ"};
                intent.putExtra("AsciiFace", Bird);
                startActivity(intent);
                break;
            case R.id.cardDog:
                String[] Dog = {"Uo･ｪ･oU", "∪･ω･∪", "U｡･ｪ･｡U", "▽・ω・▽", "▽・ｗ・▽", "Ｕ^皿^Ｕ", "ｖ・。・Ｖ", "ＵＴｪＴＵ", "(^・(I)・^)", "U・♀・U", "ｏ（Ｕ・ω・）⊃", "(^・x・^)", "(U・x・U)", "▼o・ェ・o▼", "U＾ェ＾U", "U￣ｰ￣U", "┌U･ｪ･U┘", "└@(･ｪ･)@┐", "ヾ(●ω●)ノ", "(〓￣(∵エ∵)￣〓)"};
                intent.putExtra("AsciiFace", Dog);
                startActivity(intent);
                break;
            case R.id.cardSea:
                String[] Sea = {">=<", ">゜))))彡", "ζ°)))彡", ">_)))彡", ">^)))< ～～", "(°)#))<<", "（：。）ミ", "Ｃ：。ミ", "(Q )) >", "(°))<<", ">°))))彡", "≧〔゜゜〕≦", "ミ(・・)ミ", "V=(° °)=V", "＜コ：ミ", "(゜))<<", "ミ[°°]ミ", "くコ:彡", "ミ．．ミ", "(/)(;,,;)(/)"};
                intent.putExtra("AsciiFace", Sea);
                startActivityes(intent);
                break;

        }
    }
}