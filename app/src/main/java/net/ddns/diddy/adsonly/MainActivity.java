package net.ddns.diddy.adsonly;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.ddns.diddy.adsonly.view.CardAdapter;
import net.ddns.diddy.adsonly.view.CardView;
import net.ddns.diddy.adsonly.view.CardView.OnCardClickListener;

public class MainActivity extends FragmentActivity implements OnCardClickListener{
	List<String> list;
	private ViewFragment frag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}
	
	private void initUI() {
		CardView cardView = (CardView) findViewById(R.id.cardView1);
		cardView.setOnCardClickListener(this);
		cardView.setItemSpace(Utils.convertDpToPixelInt(this, 30));
		
		MyCardAdapter adapter = new MyCardAdapter(this);
		adapter.addAll(initData());
		cardView.setAdapter(adapter);
		
		FragmentManager manager = getSupportFragmentManager();
		frag = new ViewFragment();
		manager.beginTransaction().add(R.id.contentView, frag).commit();
	}
	
	@Override
	public void onCardClick(final View view, final int position) {
		Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
		Bundle bundle = new Bundle();
		bundle.putString("text", list.get(position%list.size()));
		frag.show(view,bundle);
	}
	
	private List<String> initData() {
		list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		return list;
	}

	public class MyCardAdapter extends CardAdapter<String>{

		public MyCardAdapter(Context context) {
			super(context);
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		protected View getCardView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
				convertView = inflater.inflate(R.layout.item_layout, parent, false);
			}

			/////////////////

			// 設定卡片大小
			ImageView tv = (ImageView) convertView.findViewById(R.id.imageView_CardItem);

			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			int ScreenWidth = dm.widthPixels;   //螢幕的寬
			int ScreenHeight = dm.heightPixels / 2;  //螢幕的高

			ViewGroup.LayoutParams params = tv.getLayoutParams();
			params.width = ScreenWidth;
			params.height = ScreenHeight;
			tv.setLayoutParams(params);


			return convertView;
		}
	}
}
