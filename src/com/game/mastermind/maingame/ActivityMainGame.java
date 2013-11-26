package com.game.mastermind.maingame;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.game.mastermind.R;
import com.game.mastermind.model.ItemHolderMO;
import com.game.mastermind.util.CommonFunction;
import com.game.mastermind.util.CommonValue;
import com.game.mastermind.util.Constants;

public class ActivityMainGame extends Activity {

	public static String TAG = "ActivityMainGame";
	
	int number_slot = -1;
	int number_item = -1;
	
	private HashMap<String, View> 	mHashedItems = new HashMap<String, View>();
	private ArrayList<ItemHolderMO> item_holders = new ArrayList<ItemHolderMO>();
	
	LinearLayout linearlayout_unknown;
	LinearLayout linearlayout_listview;
	ImageView button_refresh;
	
	int array_all_ball[] = Constants.ARRAY_BALL;
	int array_ball_unknown[];
	
	//	More
	private int level = -1; 
	private boolean is_new = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_game);
		
		//
		//	Initialize()
		//
		Initialize();
		
		//
		//	Get controls
		//
		linearlayout_unknown 		= (LinearLayout) findViewById(R.id.linearlayout_unknown);
		linearlayout_listview 		= (LinearLayout) findViewById(R.id.linearlayout_listview);
		button_refresh 				= (ImageView) findViewById(R.id.button_refresh);
		
		//
		//	Set evetn button refresh
		//
		button_refresh.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(!is_new){
					
					is_new = true;
					
					newGame();
					
				}
				
			}
		});
		
		newGame();
		
	}

	//
	//	Initialize
	//
	private void Initialize() {
		Intent get_intent = getIntent();
		
		if(get_intent != null){
			
			number_slot = get_intent.getIntExtra(TAG, 0);
			
			//	Level easy
			if(number_slot == CommonValue.NUMBER_SLOT_EASY){
				
				number_item = CommonValue.NUMBER_ITEM_EASY;
				number_slot = CommonValue.NUMBER_SLOT_EASY;
				
				level = 1;
				
			}
			
			//	Level medium
			else if(number_slot == CommonValue.NUMBER_SLOT_MEDIUM){
				
				number_item = CommonValue.NUMBER_ITEM_MEDIUM;
				number_slot = CommonValue.NUMBER_SLOT_MEDIUM;
				
				level = 2;
				
			}
			
			//	Level hard
			else if(number_slot == CommonValue.NUMBER_SLOT_HARD){
				
				number_item = CommonValue.NUMBER_ITEM_HARD;
				number_slot = CommonValue.NUMBER_SLOT_HARD;
				
				level = 3;
				
			}
			
			array_ball_unknown = new int[number_slot];
			
		}
	}
	
	//
	//	New Game
	//
	public void newGame(){
		
		if(linearlayout_unknown.getChildCount() > 0){
			linearlayout_unknown.removeAllViews();
		}
		
		if(linearlayout_listview.getChildCount() > 0){
			linearlayout_listview.removeAllViews();
		}
		
		if(mHashedItems.size() > 0){
			mHashedItems.clear();
		}
		
		if(item_holders.size() > 0){
			item_holders.clear();
		}
		
		//
		//	Random array ball unknown
		//
		int p = 0;
		
		while( p < number_slot ){
			
			//	Random position ball [0, length-1)
			int position = CommonFunction.ranDom(number_slot+1);
			
			//	Get a ball in resoures
			int ball = CommonFunction.getBall(position);
			
			//	Check exist ball
			if( !CommonFunction.checkExisted(ball, array_ball_unknown, CommonValue.NUMBER_BALL_ALLOW_EXIST) ){
				array_ball_unknown[p] = ball;
				
				ImageView imageview_ball = new ImageView(this);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.weight 	= 1;
				params.width 	= 0;
				params.height 	= LayoutParams.WRAP_CONTENT;
				imageview_ball.setImageResource(R.drawable.ball_unknown);
				imageview_ball.setLayoutParams(params);
				linearlayout_unknown.addView(imageview_ball);
				
				p++;
			}
		}
		
		//
		//	Create item
		//
		for(int i=0; i<number_item; i++){
			
			try{
		        
	        	final ViewHolder holder = new ViewHolder();								
				//
				// Get Controls
				//
	        	LayoutInflater inflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	        	
	        	String k = String.valueOf(i);
		        View view = mHashedItems.get(k);
		        
		        if(view == null){
		        	
					view = inflater.inflate(R.layout.template_item, null);
					
					holder.main_item 					= (RelativeLayout) view.findViewById(R.id.main_item);
					holder.textview_number_item 		= (TextView) view.findViewById(R.id.textview_number_item);
					holder.textview_go_item 			= (TextView) view.findViewById(R.id.textview_go_item);
					holder.layout_hint 					= (RelativeLayout) view.findViewById(R.id.layout_hint);
					holder.layout_main_hint 			= (RelativeLayout) view.findViewById(R.id.layout_main_hint);
					holder.layout_hint_top 				= (LinearLayout) view.findViewById(R.id.layout_hint_top);
					
					holder.imageview_hint_top_left 		= (ImageView) view.findViewById(R.id.imageview_hint_top_left);
					holder.imageview_hint_top_right 	= (ImageView) view.findViewById(R.id.imageview_hint_top_right);
					holder.imageview_hint_center 		= (ImageView) view.findViewById(R.id.imageview_hint_center);
					holder.imageview_hint_bottom_left 	= (ImageView) view.findViewById(R.id.imageview_hint_bottom_left);
					holder.imageview_hint_bottom_right 	= (ImageView) view.findViewById(R.id.imageview_hint_bottom_right);
					holder.layout_item_holder 			= (LinearLayout) view.findViewById(R.id.layout_item_holder);
					
					//
					//	Set value
					//
					holder.textview_number_item.setText(String.valueOf( (number_item-i) ));
					holder.layout_item_holder.setEnabled(false);
					
					//
					//	
					//
					boolean enable = ( i==(number_item-1) ) ? true : false;
					ItemHolderMO item_holder = new ItemHolderMO(i, enable);
					item_holders.add(item_holder);
					
					if(number_slot == CommonValue.NUMBER_SLOT_EASY){
						holder.imageview_hint_bottom_left.setVisibility(View.GONE);
						holder.imageview_hint_bottom_right.setVisibility(View.GONE);
					}
					else if(number_slot == CommonValue.NUMBER_SLOT_MEDIUM){
						
						RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
						lp.setMargins(0, 0, 0, (int)getResources().getDimension(R.dimen.margin_bottom_tiny));
						
						holder.layout_hint_top.setLayoutParams(lp);
						holder.imageview_hint_center.setVisibility(View.GONE);
					}
					
					//
					//	Create slot
					//
					final int array_ball_holder_pick[] = new int[number_slot];
					
					for(int j=0; j<number_slot; j++){						
						//
						//	Get controls
						//
						final int slot = j;
						final ImageView imageview_ball 		= new ImageView(this);
						LinearLayout.LayoutParams params 	= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						params.weight 	= 1;
						params.width 	= 0;
						params.height 	= LayoutParams.WRAP_CONTENT;
						imageview_ball.setImageResource(R.drawable.style_ball_holder);
						imageview_ball.setLayoutParams(params);
						imageview_ball.setClickable(true);
						
						final int final_i = i;
						imageview_ball.setTag(String.valueOf(final_i));
						
						imageview_ball.setOnClickListener( new OnClickListener() {
							@Override
							public void onClick(View view) {
								
								int current_position = Integer.valueOf(view.getTag().toString());
								
								is_new = false;
								if(CommonFunction.getEnable(item_holders, (current_position) ) ){
									CommonFunction.showQuickAction(ActivityMainGame.this, number_slot, array_all_ball, imageview_ball, slot, array_ball_holder_pick);
								}
								
							}
						});
						
						holder.layout_item_holder.addView(imageview_ball);
						
					}
					
					//
					//	Set click action go
					//
					final int final_i = i;
					holder.layout_hint.setTag(String.valueOf(final_i));
					
					holder.layout_hint.setOnClickListener( new OnClickListener() {
						@Override
						public void onClick(View view) {
							
							int current_position = Integer.valueOf(view.getTag().toString());
							
							if(CommonFunction.getEnable(item_holders, (current_position) ) ){
								
								if(CommonFunction.checkElementArrayNull(array_ball_holder_pick)){
									//Toast.makeText(getBaseContext(), "Element null", Toast.LENGTH_SHORT).show();
									
									//	TODO
									
								}else{
									
									CommonFunction.setEnable(item_holders, current_position, false);
									
									// Set enalble next item
									int p_next = current_position-1;
									if(p_next >= 0){
										CommonFunction.setEnable(item_holders, p_next, true);
									}
									
									holder.layout_main_hint.setVisibility(View.VISIBLE);
									holder.textview_go_item.setVisibility(View.GONE);
									
									int array_ball_hint[] = CommonFunction.getArrayBallHint(number_slot, array_ball_unknown, array_ball_holder_pick);
									
									if(CommonFunction.compare2Array(array_ball_unknown, array_ball_holder_pick)){
										CommonFunction.dialogMessage(ActivityMainGame.this, "", "You wind !");
										
										linearlayout_unknown.removeAllViews();
										
										for(int i=0; i<array_ball_unknown.length; i++){
											
											ImageView imageview_ball = new ImageView(ActivityMainGame.this);
											LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
											params.weight 	= 1;
											params.width 	= 0;
											params.height 	= LayoutParams.WRAP_CONTENT;
											imageview_ball.setImageResource(array_ball_unknown[i]);
											imageview_ball.setLayoutParams(params);
											linearlayout_unknown.addView(imageview_ball);
										}
										
									}
									
									int p=0;
									int old_p = -1;
									int count = 0;
									
									while( (count = CommonFunction.countElementArrayNotNull(array_ball_hint) )  > 0){
										
										//	Random position ball [0, length-1)
										int position = CommonFunction.ranDom(number_slot);
										
										if( old_p != position){
											
	//										Get a ball in resoures
											int hint = array_ball_hint[position];
											
											if(hint != 0){
												array_ball_hint[position] = 0;
												
												setBallHint(p, hint, holder.imageview_hint_top_left, holder.imageview_hint_top_right, 
														holder.imageview_hint_center,
														holder.imageview_hint_bottom_left, holder.imageview_hint_bottom_right
												);
												
												p++;
												old_p = position;
											}
										}
									}
									
									array_ball_hint = new int[number_slot];
									holder.layout_hint.setEnabled(false);
								}
								
								
								
							}
						}
					});
					
					//
					// Set Tag & HashedItems
					//
					view.setTag(holder);						
		        	mHashedItems.put(k, view);
					
					//
					//	Add item to linear
					//
					linearlayout_listview.addView(view);
		        }
				
				
			} catch(Exception ex) {
				if (ex!=null)
				{
					Log.println(Log.DEBUG, ex.toString(), ex.getMessage());
				}
			}
			
		}
		
	}
	
	//
	//	Next level
	//
//	public void nextLevel(int ){
//		
//		if(level+1)
//		
//		
//	}
	
	public void setBallHint(int p, int hint, ImageView image_1, ImageView image_2, ImageView image_3, ImageView image_4, ImageView image_5){
		
		switch (p) {
		
		case 0:{
			image_1.setImageResource(hint);
			break;
		}
		case 1:{
			image_2.setImageResource(hint);											
			break;
		}
		case 2:{
			if(number_slot == 4){
				image_5.setImageResource(hint);
			}
			else{
				image_3.setImageResource(hint);
			}
			
			break;
		}
		case 3:{
			image_4.setImageResource(hint);
			break;
		}
		case 4:{
			image_5.setImageResource(hint);
			break;
		}

		default:
			break;
			
	}
		
	}
	
	
	public class ViewHolder{
		
		RelativeLayout 		main_item;
		
		LinearLayout 		layout_item_holder;
		
		TextView 			textview_number_item;
		TextView 			textview_go_item;
		
		RelativeLayout 		layout_hint;
		
		RelativeLayout 		layout_main_hint;
		
		LinearLayout 		layout_hint_top;
		ImageView 			imageview_hint_top_left;
		ImageView 			imageview_hint_top_right;
		
		ImageView 			imageview_hint_center;
		
		LinearLayout 		layout_hint_bottom;
		ImageView 			imageview_hint_bottom_left;
		ImageView 			imageview_hint_bottom_right;
		
	}
	
}
