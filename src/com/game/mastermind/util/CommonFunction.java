package com.game.mastermind.util;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ImageView;

import com.game.mastermind.R;
import com.game.mastermind.model.ItemHolderMO;
import com.game.mastermind.quickaction.ActionItem;
import com.game.mastermind.quickaction.QuickAction;
import com.game.mastermind.quickaction.QuickAction.OnActionItemClickListener;


public class CommonFunction {

	private static String TAG = "CommonFunction";
	
	public static boolean getEnable(ArrayList<ItemHolderMO>item_holders, int p){
		ItemHolderMO item = item_holders.get(p);
		return item.getEnable();
	}
	
	public static void setEnable(ArrayList<ItemHolderMO>item_holders, int p, boolean value){
		ItemHolderMO item = item_holders.get(p);
		item.setEnable(value);
	}

	public static int [] getArrayBallHint(int slot, int array_1[], int array_2[]){
		
		int array_ball_hint[] = new int [slot];   
		
		int p = 0 ;
		
		boolean is_exist = false;
		
		for(int i=0; i<array_1.length; i++){
			for(int j=0; j<array_2.length; j++){
				if(array_1[i] == array_2[j]){
					if( i==j ){
						array_ball_hint[p] = R.drawable.ball_hint_match;
						p++;
						break;
					}
					else{
						array_ball_hint[p] = R.drawable.ball_hint_exist;
						
						if( j == (array_2.length-1) ){
							p++;
							is_exist = false;
							break;
						}
						
						is_exist = true;
						
					}
				}
				else if( (j == (array_2.length-1)) && is_exist){
					p++;
					is_exist = false;
				}
				
			}
		}
		return array_ball_hint;
	}
	
	/**
	 * 
	 * Compare 2 array
	 * 
	 * @param array_1
	 * @param array_2
	 * 
	 * @return boolean
	 * 
	 */
	public static boolean compare2Array(int array_1[], int array_2[]){
		
		for(int i=0; i<array_1.length; i++){
			if(array_1[i] != array_2[i]){
				return false;
			}
		}
		
		return true;
	}
	
	public static void dialogMessage(Context context, String title, String message) {
		try
		{			
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   //Do Nothing, just show message with OK button			        	   
			           }
			       });
			// Create the AlertDialog
			AlertDialog dialog = builder.create();			
			dialog.show();
			
		}catch(Exception ex)
		{			
			Log.v(TAG , ex.getMessage());
		}
		
	}
	
	/**
	 * 
	 * Count element NOT null in array
	 * 
	 * @param array
	 * @return int
	 * 
	 */
	public static int countElementArrayNotNull(int array[]){
		
		int count = 0;
		
		for(int i=0; i<array.length; i++){
			if(array[i] != 0){
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * 
	 * Check element null in array
	 * 
	 * @param array
	 * @return boolean
	 * 
	 */
	public static boolean checkElementArrayNull(int array[]){
		
		for(int i=0; i<array.length; i++){
			if(array[i] == 0){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * Show Quick Ation
	 * 
	 * @param context
	 * @param no_ball
	 * @param array_all_ball
	 * @param holder
	 * @param p
	 * @param array_holder_pick
	 * 
	 */
	public static void showQuickAction(Context context, int no_ball, int array_all_ball[], final ImageView holder, final int p, final int array_holder_pick[]){
		
		QuickAction quick_aciton = new QuickAction(context, QuickAction.HORIZONTAL);
		
		for(int i=0; i<=no_ball; i++){
			
			//
			//	Create item quick action
			//
			ActionItem action_item = new  ActionItem(array_all_ball[i], context.getResources().getDrawable(array_all_ball[i]));
			quick_aciton.addActionItem(action_item);
			
		}
		
		quick_aciton.setOnActionItemClickListener( new OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {
				
				holder.setImageResource(actionId);
				array_holder_pick[p] = actionId;
				
			}
		});
		
		quick_aciton.show(holder);
		
	}
	
	/**
	 * 
	 * Random [0, max)
	 * 
	 * @param float max
	 * 
	 * @return int
	 * 
	 **/
	public static int ranDom(int max){
		
		return (int)(max * Math.random());
		
	}
	
	/**
	 * 	
	 * Check Exist value in int[]
	 * 
	 * @param int 	value
	 * @param int[] array
	 * 
	 * @return boolean
	 * 
	 */
	public static boolean checkExisted(int value, int[]array, int no_allow_exist){
		
		int count_exist = 0;
		boolean exist = false;
		
		if(no_allow_exist == 0){no_allow_exist = 1;}
		
		if(array.length == 0){return false;}
		
		for(int i=0; i<array.length; i++){
			
			if(value == array[i]){
				count_exist++;
				
				if(count_exist == no_allow_exist){
					exist = true;
					break;
				}
				
			}
			
		}
		return exist;
	}
	
	/**
	 * 
	 * Get Ball
	 * 
	 * @param position
	 * 
	 * @return int
	 * 
	 */
	public static int getBall(int position){
		
		int ball = -1;
		
		for(int i=0; i < Constants.ARRAY_BALL.length; i++){
			
			if(position == i){
				ball = Constants.ARRAY_BALL[position];
				return ball;
			}
		}
		
		return ball;
		
	}
	
}
