package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;

/* JADX INFO: renamed from: androidx.recyclerview.widget.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerC0337h0 extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9146a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0337h0(RecyclerView recyclerView, Looper looper) {
        super(looper);
        this.f9146a = recyclerView;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i5;
        if (message.what != 0) {
            return;
        }
        RecyclerView recyclerView = this.f9146a;
        if (recyclerView.mAdapter == null) {
            Log.e("SeslRecyclerView", "No adapter attached; skipping MSG_HOVERSCROLL_MOVE");
            return;
        }
        recyclerView.mHoverRecognitionCurrentTime = System.currentTimeMillis();
        recyclerView.mHoverRecognitionDurationTime = (recyclerView.mHoverRecognitionCurrentTime - recyclerView.mHoverRecognitionStartTime) / 1000;
        if (!recyclerView.mIsPenHovered || recyclerView.mHoverRecognitionCurrentTime - recyclerView.mHoverScrollStartTime >= recyclerView.mHoverScrollTimeInterval) {
            if (!recyclerView.mIsPenPressed || recyclerView.mHoverRecognitionCurrentTime - recyclerView.mHoverScrollStartTime >= recyclerView.mPenDragScrollTimeInterval) {
                if (recyclerView.mIsPenHovered && !recyclerView.mIsSendHoverScrollState) {
                    if (recyclerView.mScrollListener != null) {
                        recyclerView.mHoverScrollStateForListener = 1;
                        recyclerView.mScrollListener.onScrollStateChanged(recyclerView, 1);
                    }
                    recyclerView.mIsSendHoverScrollState = true;
                }
                boolean zCanScrollVertically = recyclerView.mLayout.canScrollVertically();
                boolean zCanScrollHorizontally = recyclerView.mLayout.canScrollHorizontally();
                boolean z9 = recyclerView.mLayout.getLayoutDirection() == 1;
                boolean zE = recyclerView.e();
                boolean zF = recyclerView.f();
                recyclerView.mHoverScrollSpeed = (int) (TypedValue.applyDimension(1, RecyclerView.HOVERSCROLL_SPEED, recyclerView.mContext.getResources().getDisplayMetrics()) + 0.5f);
                if (recyclerView.mHoverRecognitionDurationTime > 2 && recyclerView.mHoverRecognitionDurationTime < 4) {
                    recyclerView.mHoverScrollSpeed += (int) (((double) recyclerView.mHoverScrollSpeed) * 0.1d);
                } else if (recyclerView.mHoverRecognitionDurationTime >= 4 && recyclerView.mHoverRecognitionDurationTime < 5) {
                    recyclerView.mHoverScrollSpeed += (int) (((double) recyclerView.mHoverScrollSpeed) * 0.2d);
                } else if (recyclerView.mHoverRecognitionDurationTime >= 5) {
                    recyclerView.mHoverScrollSpeed += (int) (((double) recyclerView.mHoverScrollSpeed) * 0.3d);
                }
                if (recyclerView.mHoverScrollDirection == 2) {
                    i5 = (zCanScrollHorizontally && z9) ? recyclerView.mHoverScrollSpeed : recyclerView.mHoverScrollSpeed * (-1);
                    if ((recyclerView.mPenTrackedChild == null && recyclerView.mCloseChildByBottom != null) || (recyclerView.mOldHoverScrollDirection != recyclerView.mHoverScrollDirection && recyclerView.mIsCloseChildSetted)) {
                        recyclerView.mPenTrackedChild = recyclerView.mCloseChildByBottom;
                        recyclerView.mPenDistanceFromTrackedChildTop = recyclerView.mDistanceFromCloseChildBottom;
                        recyclerView.mPenTrackedChildPosition = recyclerView.mCloseChildPositionByBottom;
                        recyclerView.mOldHoverScrollDirection = recyclerView.mHoverScrollDirection;
                        recyclerView.mIsCloseChildSetted = true;
                    }
                } else {
                    i5 = (zCanScrollHorizontally && z9) ? recyclerView.mHoverScrollSpeed * (-1) : recyclerView.mHoverScrollSpeed;
                    if ((recyclerView.mPenTrackedChild == null && recyclerView.mCloseChildByTop != null) || (recyclerView.mOldHoverScrollDirection != recyclerView.mHoverScrollDirection && recyclerView.mIsCloseChildSetted)) {
                        recyclerView.mPenTrackedChild = recyclerView.mCloseChildByTop;
                        recyclerView.mPenDistanceFromTrackedChildTop = recyclerView.mDistanceFromCloseChildTop;
                        recyclerView.mPenTrackedChildPosition = recyclerView.mCloseChildPositionByTop;
                        recyclerView.mOldHoverScrollDirection = recyclerView.mHoverScrollDirection;
                        recyclerView.mIsCloseChildSetted = true;
                    }
                }
                if (recyclerView.getChildAt(recyclerView.getChildCount() - 1) == null) {
                    return;
                }
                if ((i5 < 0 && zF) || (i5 > 0 && zE)) {
                    recyclerView.startNestedScroll(zCanScrollHorizontally ? 1 : 2, 1);
                    if (this.f9146a.dispatchNestedPreScroll(zCanScrollHorizontally ? z9 ? -i5 : i5 : 0, zCanScrollVertically ? i5 : 0, null, null, 1)) {
                        recyclerView.c(i5);
                    } else {
                        int i7 = zCanScrollHorizontally ? z9 ? -i5 : i5 : 0;
                        if (!zCanScrollVertically) {
                            i5 = 0;
                        }
                        recyclerView.scrollByInternal(i7, i5, null, 0);
                        recyclerView.setScrollState(1);
                        if (recyclerView.mIsLongPressMultiSelection) {
                            recyclerView.C(recyclerView.mPenDragEndX, recyclerView.mPenDragEndY, false);
                        }
                    }
                    recyclerView.mHoverHandler.sendEmptyMessageDelayed(0, 0L);
                    return;
                }
                int overScrollMode = recyclerView.getOverScrollMode();
                boolean z10 = overScrollMode == 0 || (overScrollMode == 1 && !RecyclerView.access$4000(recyclerView));
                if (z10 && !recyclerView.mIsHoverOverscrolled) {
                    if (zCanScrollHorizontally) {
                        recyclerView.ensureLeftGlow();
                        recyclerView.ensureRightGlow();
                    } else {
                        recyclerView.ensureTopGlow();
                        recyclerView.ensureBottomGlow();
                    }
                    if (recyclerView.mHoverScrollDirection == 2) {
                        if (zCanScrollHorizontally) {
                            recyclerView.mLeftGlow.onAbsorb(10000);
                            if (!recyclerView.mRightGlow.isFinished()) {
                                recyclerView.mRightGlow.onRelease();
                            }
                        } else {
                            recyclerView.mTopGlow.onAbsorb(10000);
                            if (!recyclerView.mBottomGlow.isFinished()) {
                                recyclerView.mBottomGlow.onRelease();
                            }
                        }
                    } else if (recyclerView.mHoverScrollDirection == 1) {
                        if (zCanScrollHorizontally) {
                            recyclerView.mRightGlow.onAbsorb(10000);
                            if (!recyclerView.mLeftGlow.isFinished()) {
                                recyclerView.mLeftGlow.onRelease();
                            }
                        } else {
                            recyclerView.mBottomGlow.onAbsorb(10000);
                            recyclerView.setupGoToTop(1);
                            recyclerView.d(1);
                            if (!recyclerView.mTopGlow.isFinished()) {
                                recyclerView.mTopGlow.onRelease();
                            }
                        }
                    }
                    recyclerView.invalidate();
                    recyclerView.mIsHoverOverscrolled = true;
                }
                if (recyclerView.mScrollState == 1) {
                    recyclerView.setScrollState(0);
                }
                if (z10 || recyclerView.mIsHoverOverscrolled) {
                    return;
                }
                recyclerView.mIsHoverOverscrolled = true;
            }
        }
    }
}
