package activity.dengwenbin.com.practice_notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.readystatesoftware.viewbadger.BadgeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import activity.dengwenbin.com.practice_notification.Util.FileUtil;

public class DashboardActivity extends AppCompatActivity {


    private JSONObject notificationJSON;
    private int appValue,tabKpiValue,tabAnalyseValue,tabAppValue,tabMessageValue,imgSettingValue;
    private TabView tabKpi,tabAnalyse,tabApp,tabMessage;
    private ImageView img_setting;
    private BadgeView bvKpi,bvAnalyse,bvApp,bvMessage,bvSetting;
    private String notificationFileName="notifition.json",notificationFileFoler="%s/%s/config";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        notificationFileFoler = String.format(notificationFileFoler,getFilesDir().getPath(),"123");
        testShowState();
        init();
        checkNotificationFile();
    }

    public void init(){
        tabKpi = (TabView) findViewById(R.id.tabKPI);
        tabAnalyse = (TabView) findViewById(R.id.tabAnalyse);
        tabApp = (TabView) findViewById(R.id.tabApp);
        tabMessage = (TabView) findViewById(R.id.tabMessage);

        img_setting = (ImageView) findViewById(R.id.img_setting);

        bvKpi = new BadgeView(DashboardActivity.this,tabKpi);
        bvAnalyse = new BadgeView(DashboardActivity.this,tabAnalyse);
        bvApp = new BadgeView(DashboardActivity.this,tabApp);
        bvMessage = new BadgeView(DashboardActivity.this,tabMessage);

        bvSetting = new BadgeView(DashboardActivity.this,img_setting);

        tabKpi.setOnClickListener(mTabChangeListener);
        tabAnalyse.setOnClickListener(mTabChangeListener);
        tabApp.setOnClickListener(mTabChangeListener);
        tabMessage.setOnClickListener(mTabChangeListener);

    }

    private final View.OnClickListener mTabChangeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                switch (v.getId()) {
                    case R.id.tabKPI:
                        bvKpi.setVisibility(View.INVISIBLE);
                        if (new File (notificationFileFoler,notificationFileName).exists()){
                            notificationJSON.put("tab_kpi",0);
                            FileUtil.writeFile(notificationJSON.toString(),notificationFileFoler+"/"+notificationFileName);
                        }
                        break;
                    case R.id.tabAnalyse:
                        bvAnalyse.setVisibility(View.INVISIBLE);
                        if (new File (notificationFileFoler,notificationFileName).exists()){
                            notificationJSON.put("tab_analyse",0);
                            FileUtil.writeFile(notificationJSON.toString(), notificationFileFoler+"/"+notificationFileName);
                        }
                        break;
                    case R.id.tabApp:
                        bvApp.setVisibility(View.INVISIBLE);
                        if (new File (notificationFileFoler,notificationFileName).exists()){
                            notificationJSON.put("tab_app",0);
                            FileUtil.writeFile(notificationJSON.toString(),notificationFileFoler+"/"+notificationFileName);
                        }
                        break;
                    case R.id.tabMessage:
                        bvMessage.setVisibility(View.INVISIBLE);
                        if (new File (notificationFileFoler,notificationFileName).exists()){
                            notificationJSON.put("tab_message",0);
                            FileUtil.writeFile(notificationJSON.toString(), notificationFileFoler+"/"+notificationFileName);
                        }
                        break;
                    default:
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void checkNotificationFile(){
        File file = new File (notificationFileFoler,notificationFileName);
        if(file.exists()){
            try {
                notificationJSON = new JSONObject(FileUtil.readFile(notificationFileFoler, notificationFileName));
                if(notificationJSON.has("app")){
                    appValue = notificationJSON.getInt("app");
                }
                if(notificationJSON.has("tab_kpi")){
                    tabKpiValue = notificationJSON.getInt("tab_kpi");
                }
                if(notificationJSON.has("tab_analyse")){
                    tabAnalyseValue = notificationJSON.getInt("tab_analyse");
                }
                if(notificationJSON.has("tab_app")){
                    tabAppValue = notificationJSON.getInt("tab_app");
                }
                if(notificationJSON.has("tab_message")){
                    tabMessageValue = notificationJSON.getInt("tab_message");
                }
                if(notificationJSON.has("setting")){
                    imgSettingValue = notificationJSON.getInt("setting");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            checkNotificationValue();
        }
    }

    public void checkNotificationValue(){
        if(tabKpiValue>0){
            setRedDot(bvKpi);
        }

        if(tabAnalyseValue>0){
            setRedDot(bvAnalyse);
        }

        if(tabAppValue>0){
            setRedDot(bvApp);
        }
        if(tabMessageValue>0){
            setRedDot(bvMessage);
        }
        if(imgSettingValue>0){
            setRedDot(bvSetting);
        }
    }

    public void setRedDot(BadgeView badgeView){
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badgeView.setWidth(10);
        badgeView.setHeight(10);
        badgeView.setBadgeMargin(0, 0);
        badgeView.show();
    }

    public void testShowState(){
        JSONObject fileInfo = new JSONObject();
        try {
            fileInfo.put("app",0);
            fileInfo.put("tab_kpi",0);
            fileInfo.put("tab_analyse",1);
            fileInfo.put("tab_app",1);
            fileInfo.put("tab_message",1);
            fileInfo.put("setting",1);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        File file = new File (notificationFileFoler,notificationFileName);
        if (file.exists()){
            try {
                FileUtil.writeFile(fileInfo.toString(),notificationFileFoler+"/"+notificationFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                FileUtil.isFileDir(notificationFileFoler,notificationFileName);
                FileUtil.writeFile(fileInfo.toString(), notificationFileFoler+"/"+notificationFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
