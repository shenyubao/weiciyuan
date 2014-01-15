package org.qii.weiciyuan.ui.vip;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.qii.weiciyuan.R;
import org.qii.weiciyuan.bean.UserBean;
import org.qii.weiciyuan.support.asyncdrawable.TaskCache;
import org.qii.weiciyuan.support.file.FileLocationMethod;
import org.qii.weiciyuan.support.file.FileManager;
import org.qii.weiciyuan.support.utils.GlobalContext;
import org.qii.weiciyuan.support.utils.Utility;
import org.qii.weiciyuan.ui.interfaces.AbstractAppActivity;
import org.qii.weiciyuan.ui.interfaces.AbstractAppFragment;
import org.qii.weiciyuan.ui.main.MainTimeLineActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shenyubao on 13-12-29.
 */
public class VipActivity extends AbstractAppActivity {

    private UserBean userBean;
    private ListView listView;
    private ImageView userHead;
    private TextView userName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(getString(R.string.vip));
        setContentView(R.layout.vip);

        userBean = GlobalContext.getInstance().getAccountBean().getInfo();
        listView = (ListView)findViewById(R.id.ltvVIPDesc);
        listView.setAdapter(new VipDescAdapter());

        userHead = (ImageView)findViewById(R.id.imgVIPHead);
        userName = (TextView)findViewById(R.id.txvVIPName);

        String path = FileManager.getFilePathFromUrl(userBean.getAvatar_large(), FileLocationMethod.avatar_large);
        TaskCache.waitForPictureDownload(userBean.getAvatar_large(), null, path, FileLocationMethod.avatar_large);
        userHead.setImageBitmap(BitmapFactory.decodeFile(path));
        userName.setText(userBean.getScreen_name());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = MainTimeLineActivity.newIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return false;
    }

    public void initListView(){

    }

    private class VipDesc{
        String desc;
        Bitmap icon;

        public VipDesc(String mdesc,int mIconId){
            desc = mdesc;
            icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),mIconId);
        }
    }

    private static class ViewHold{
        ImageView icon;
        TextView desc;
    }

    public class VipDescAdapter extends BaseAdapter{
        List<VipDesc> list ;
        private Context context;
        private LayoutInflater inflater;


        public VipDescAdapter(){
            context = getApplicationContext();
            inflater = LayoutInflater.from(context);
            list = new ArrayList<VipDesc>();
            list.add(new VipDesc("客户端个性化模板，彰显个性空间",R.drawable.vip_icon_1));
            list.add(new VipDesc("主要微博置顶显示，炫耀提醒两不误",R.drawable.vip_icon_2));
            list.add(new VipDesc("短信提醒特别关注，好友动态尽掌握",R.drawable.vip_icon_3));
            list.add(new VipDesc("最高2.0倍等级加速，上升快人一步",R.drawable.vip_icon_4));
            list.add(new VipDesc("账号变动短信告知，安全放心不丢失",R.drawable.vip_icon_5));
        }

        public int getCount(){
            return list.size();
        }

        public Object getItem(int i){
            return list.get(i);
        }

        public long getItemId(int i){
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent){
            ViewHold viewHold;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.vip_desc_item,null);
                viewHold = new ViewHold();
                viewHold.icon = (ImageView)convertView.findViewById(R.id.imgIcon);
                viewHold.desc = (TextView)convertView.findViewById(R.id.txvDesc);
                convertView.setTag(viewHold);
            }else {
                viewHold = (ViewHold)convertView.getTag();
            }

            VipDesc  vipDesc= list.get(i);
            viewHold.desc.setText(vipDesc.desc);
            viewHold.icon.setImageBitmap(vipDesc.icon);
            return  convertView;
        }
    }
}
