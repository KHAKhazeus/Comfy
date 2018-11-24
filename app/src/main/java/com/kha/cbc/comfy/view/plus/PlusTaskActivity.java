package com.kha.cbc.comfy.view.plus;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.avos.avoscloud.AVUser;
import com.google.android.material.textfield.TextInputEditText;
import com.kha.cbc.comfy.ComfyApp;
import com.kha.cbc.comfy.R;
import com.kha.cbc.comfy.entity.GDPersonalTask;
import com.kha.cbc.comfy.model.PersonalTask;
import com.kha.cbc.comfy.greendao.gen.GDPersonalTaskDao;
import com.kha.cbc.comfy.model.TeamTask;
import com.kha.cbc.comfy.presenter.PlusTaskPresenter;
import com.kha.cbc.comfy.view.main.MainActivity;

public class PlusTaskActivity extends AppCompatActivity implements PlusTaskView{

    PlusTaskPresenter presenter = new PlusTaskPresenter(this);
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_plus_task);

        Toolbar toolbar = findViewById(R.id.personal_plus_toolbar);
        setSupportActionBar(toolbar);

        Intent intentType = getIntent();
        Bundle bundle = intentType.getExtras();
        type = bundle.getInt("type");

        TextInputEditText editText = findViewById(R.id.input_task_title);
        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE ||
                    (keyEvent!=null &&
                            keyEvent.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                String taskTitle = textView.getText().toString();
                if (type == 0) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    GDPersonalTaskDao taskDao = ((ComfyApp) getApplication())
                            .getDaoSession().getGDPersonalTaskDao();
                    GDPersonalTask personalTask = new GDPersonalTask(new PersonalTask(taskTitle));
                    taskDao.insert(personalTask);
                } else {
                    presenter.postTask(new TeamTask(taskTitle, MainActivity.user, null, null));
                }
                finish();
                return true;
            }
            return false;
        });
    }

    //TODO:如果使用API做网络申请的话再调用
    @Override
    public void onPostSuccess(TeamTask teamTask) {
        finish();
    }

    @Override
    public void onPostError(Throwable e) {
        e.printStackTrace();
    }
}
