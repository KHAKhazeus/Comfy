package com.kha.cbc.comfy.presenter;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.avos.avoscloud.*;
import com.kha.cbc.comfy.view.common.AvatarView;
import com.kha.cbc.comfy.view.plus.PlusCardActivity;
import com.kha.cbc.comfy.view.plus.PlusCardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABINGCBC
 * on 2018/11/5
 */

public class PlusCardPresenter extends AvatarPresenter {

    PlusCardView view;

    public PlusCardPresenter(PlusCardView view) {
        super((AvatarView) view);
        this.view = view;
    }

    //将新增卡片信息上传至服务器
    public void postCard(String title,
                         String description,
                         String executorObjectId,
                         String stageObjectId) {
        AVObject card = new AVObject("TeamCard");
        AVObject user = AVObject.createWithoutData("ComfyUser",
                executorObjectId);
        card.put("Executor", user);
        AVObject stage = AVObject.createWithoutData("Stage",
                stageObjectId);
        card.put("Stage", stage);
        card.put("CardTitle", title);
        card.put("Description", description);
        card.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e != null)
                    e.printStackTrace();
                Log.d("sss", "success");
            }
        });
    }

    public void setLocalReminder(LinearLayout linearLayout) {

    }

    public void setCloudReminder(LinearLayout linearLayout) {

    }

    public void queryMember(String memberName) {
        List<String> nameList = new ArrayList<>();
        nameList.add(memberName);
        super.loadAvatar(nameList);
    }
}
