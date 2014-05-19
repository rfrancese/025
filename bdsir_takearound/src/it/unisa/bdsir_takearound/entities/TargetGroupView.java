package it.unisa.bdsir_takearound.entities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

public class TargetGroupView extends ViewGroup{

	int widthView,heightView=0;
	
	public TargetGroupView(Context context){
		super(context);
		init();
	}
	
	public TargetGroupView(Context context, AttributeSet attrs){
		super(context, attrs);
		init();
	}
	
	public TargetGroupView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init();
	}
	
	private void init(){
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		
//		At this time we need to call setMeasuredDimensions(). Lets just call the 
//		parent View's method (see https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/View.java) 
//		that does:
//		setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//		
		
		widthView=MeasureSpec.getSize(widthMeasureSpec);
		heightView=MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(widthView,heightView);
		for(int i=0; i< this.getChildCount(); i++){
			TargetView v = (TargetView)getChildAt(i);
			v.measure(v.widthView, v.heightView);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for(int i=0; i< this.getChildCount(); i++){
			TargetView v = (TargetView)getChildAt(i);
			v.layout(l, t, r, b);
		}
	}

}
