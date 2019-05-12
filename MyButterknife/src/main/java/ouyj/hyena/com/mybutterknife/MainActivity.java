package ouyj.hyena.com.mybutterknife;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

import static android.widget.Toast.LENGTH_SHORT;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final ButterKnife.Action<View> ALPHA_FADE = new ButterKnife.Action<View>() {
        @Override public void apply(@NonNull View view, int index) {
            //设置动画
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setStartOffset(index * 100);
            view.startAnimation(alphaAnimation);
        }
    };
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.hello)
    Button hello;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.footer)
    TextView footer;

    @BindViews({ R.id.title, R.id.subtitle, R.id.hello })
    List<View> headerViews;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //将黄油刀绑定到活动
        ButterKnife.bind(this);

        //设置视图文本
        title.setText("Butter Knife");
        subtitle.setText("Field and method binding for Android views.");
        footer.setText("by Jake Wharton");
        hello.setText("Say Hello");

        //为列表视设置适配器
        adapter = new SimpleAdapter(this);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.hello)
    void sayHello() {
        Toast.makeText(this, "Hello, views!", LENGTH_SHORT).show();

        //执行动画
        ButterKnife.apply(headerViews, ALPHA_FADE);
    }
    @OnLongClick(R.id.hello)
    boolean sayGetOffMe() {
        Toast.makeText(this, "Let go of me!", LENGTH_SHORT).show();
        return true;
    }
    @OnItemClick(R.id.listView)
    void onItemClick(int position) {
        Toast.makeText(
                this,
                "You clicked: " + adapter.getItem(position),
                LENGTH_SHORT
        ).show();
    }
}
