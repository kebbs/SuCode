package com.isunnyapp.midialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * Created by kexuebiao on 15/8/7.
 */
public class MiDialog {
    private Context mContext;
    private AlertDialog mAlertDialog;
    private Builder mBuilder;
    private View mView;
    private int mTitleResId;
    private boolean showProgressBar;
    private CharSequence mTitle;
    private int mMessageResId;
    private CharSequence mMessage;
    private Button mPositiveButton;
    private LinearLayout.LayoutParams mLayoutParams;
    private Button mNegativeButton;
    private Drawable mBackgroundDrawable;
    private int mBackgroundResId;
    private int mBackgroundColor;
    private View mMessageContentView;
    private boolean mCancel = true;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private boolean mHasShow = false;

    public boolean isShowProgressBar() {
        return showProgressBar;
    }

    public MiDialog setShowProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
        if (this.mBuilder != null) {
            this.mBuilder.showProgressbar(showProgressBar);
        }

        return this;
    }

    public MiDialog(Context context) {
        this.mContext = context;
    }

    public MiDialog setTitle(CharSequence title) {
        this.mTitle = title;
        if (this.mBuilder != null) {
            this.mBuilder.setTitle(title);
        }

        return this;
    }

    public boolean isHasShow() {
        return mHasShow;
    }


    public MiDialog setBackgroundColor(int color) {
        mBackgroundColor = color;
        if (this.mBuilder != null) {
            this.mBuilder.setBackgroundColor(this.mBackgroundColor);
        }
        return this;
    }

    public MiDialog setMessage(CharSequence message) {
        this.mMessage = message;
        if (this.mBuilder != null) {
            this.mBuilder.setMessage(message);
        }

        return this;
    }

    public void dismiss() {
        this.mAlertDialog.dismiss();
    }

    public MiDialog setPositiveButton(String text, View.OnClickListener listener) {
        this.mPositiveButton = new Button(this.mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        this.mPositiveButton.setLayoutParams(params);
        this.mPositiveButton.setBackgroundResource(R.drawable.slt_wheel_btn);
        this.mPositiveButton.setTextColor(Color.argb(255, 35, 159, 242));
        this.mPositiveButton.setText(text);
        this.mPositiveButton.setGravity(17);
        this.mPositiveButton.setTextSize(14.0F);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.dip2px(2.0F), 0, this.dip2px(12.0F), this.dip2px(9.0F));
        this.mPositiveButton.setLayoutParams(layoutParams);
        if (listener == null) {
            this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MiDialog.this.dismiss();
                }
            });
        } else {
            this.mPositiveButton.setOnClickListener(listener);
        }
//        if(isLollipop()) {
//            this.mPositiveButton.setBackgroundResource(17170445);
//        }

        return this;
    }

    public MiDialog setNegativeButton(String text, View.OnClickListener listener) {
        this.mNegativeButton = new Button(this.mContext);
        this.mLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.mNegativeButton.setLayoutParams(this.mLayoutParams);
        this.mNegativeButton.setBackgroundResource(R.drawable.slt_wheel_btn);
        this.mNegativeButton.setText(text);
        this.mNegativeButton.setTextColor(Color.argb(222, 0, 0, 0));
        this.mNegativeButton.setTextSize(14.0F);
        this.mNegativeButton.setGravity(17);
        if (listener == null) {
            this.mNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MiDialog.this.dismiss();
                }
            });
        } else {
            this.mNegativeButton.setOnClickListener(listener);
        }
//        if(isLollipop()) {
//            this.mNegativeButton.setBackgroundResource(17170445);
//        }

        return this;
    }

    public MiDialog setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        if (this.mBuilder != null) {
            this.mBuilder.setCanceledOnTouchOutside(this.mCancel);
        }

        return this;
    }

    public void show() {
        if (!this.mHasShow) {
            this.mBuilder = new Builder();
        } else {
            this.mAlertDialog.show();
        }

        this.mHasShow = true;
    }

    private int dip2px(float dpValue) {
        float scale = this.mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public MiDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    public MiDialog setBackgroundResource(int resId) {
        this.mBackgroundResId = resId;
        if (this.mBuilder != null) {
            this.mBuilder.setBackgroundResource(this.mBackgroundResId);
        }
        return this;
    }

    public MiDialog setView(View view) {
        this.mView = view;
        if (this.mBuilder != null) {
            this.mBuilder.setView(view);
        }

        return this;
    }

    private class Builder {
        private TextView mTitleView;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;
        private ScrollView mMessageScrollview;
        private ProgressBar mProgressBar;

        private Builder() {
            MiDialog.this.mAlertDialog = (new AlertDialog.Builder(MiDialog.this.mContext)).create();
            MiDialog.this.mAlertDialog.show();
            MiDialog.this.mAlertDialog.getWindow().clearFlags(131080);
            MiDialog.this.mAlertDialog.getWindow().setSoftInputMode(4);
            this.mAlertDialogWindow = MiDialog.this.mAlertDialog.getWindow();
            View contv = LayoutInflater.from(MiDialog.this.mContext).inflate(R.layout.layout_midialog, (ViewGroup) null);
            contv.setFocusable(true);
            contv.setFocusableInTouchMode(true);
            this.mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);
            this.mAlertDialogWindow.setContentView(contv);
            new android.view.WindowManager.LayoutParams(-2, -2, 2002, 131072, -3);
            this.mTitleView = (TextView) this.mAlertDialogWindow.findViewById(R.id.title);
            this.mMessageView = (TextView) this.mAlertDialogWindow.findViewById(R.id.message);
            this.mButtonLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.buttonLayout);
            this.mMessageScrollview = (ScrollView) this.mAlertDialogWindow.findViewById(R.id.message_scrollview);
            this.mProgressBar = (ProgressBar) this.mAlertDialogWindow.findViewById(R.id.pbar);
            LinearLayout linearLayout;
            if (MiDialog.this.mView != null) {
                linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.contentView);
                linearLayout.removeAllViews();

                linearLayout.addView(MiDialog.this.mView);

//                MiDialog.this.mView.setMinimumHeight(200);
//                MiDialog.this.mView.set
            }

            if (MiDialog.this.isShowProgressBar()) {
                this.mProgressBar.setVisibility(View.VISIBLE);
            }

            if (MiDialog.this.mTitleResId != 0) {
                this.mTitleView.setVisibility(View.VISIBLE);
                this.setTitle(MiDialog.this.mTitleResId);
            }

            if (MiDialog.this.mTitle != null) {
                this.mTitleView.setVisibility(View.VISIBLE);
                this.setTitle(MiDialog.this.mTitle);
            }

            if (MiDialog.this.mTitle == null && MiDialog.this.mTitleResId == 0) {
                this.mTitleView.setVisibility(View.GONE);
            }

            if (MiDialog.this.mMessageResId != 0) {
                this.setMessage(MiDialog.this.mMessageResId);
            }

            if (MiDialog.this.mMessage != null) {
                this.setMessage(MiDialog.this.mMessage);
            }

            if (MiDialog.this.mPositiveButton != null) {
                this.mButtonLayout.addView(MiDialog.this.mPositiveButton);
            }

            if (MiDialog.this.mLayoutParams != null && MiDialog.this.mNegativeButton != null) {
                if (this.mButtonLayout.getChildCount() > 0) {
                    MiDialog.this.mLayoutParams.setMargins(MiDialog.this.dip2px(12.0F), 0, 0, MiDialog.this.dip2px(9.0F));
                    MiDialog.this.mNegativeButton.setLayoutParams(MiDialog.this.mLayoutParams);
                    this.mButtonLayout.addView(MiDialog.this.mNegativeButton, 1);
                } else {
                    MiDialog.this.mNegativeButton.setLayoutParams(MiDialog.this.mLayoutParams);
                    this.mButtonLayout.addView(MiDialog.this.mNegativeButton);
                }
            }

            if (MiDialog.this.mBackgroundResId != 0) {
                linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackgroundResource(MiDialog.this.mBackgroundResId);
            }

            if (MiDialog.this.mBackgroundDrawable != null) {
                linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackground(MiDialog.this.mBackgroundDrawable);
            }

            if (MiDialog.this.mBackgroundColor != 0) {
                linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackgroundColor(MiDialog.this.mBackgroundColor);
            }


            if (MiDialog.this.mMessageContentView != null) {
                this.setContentView(MiDialog.this.mMessageContentView);
            }


            MiDialog.this.mAlertDialog.setCanceledOnTouchOutside(MiDialog.this.mCancel);
            if (MiDialog.this.mOnDismissListener != null) {
                MiDialog.this.mAlertDialog.setOnDismissListener(MiDialog.this.mOnDismissListener);
            }

        }

        public void showProgressbar(boolean par) {
            if (par) {
                this.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                this.mProgressBar.setVisibility(View.GONE);
            }
        }

        public void setTitle(int resId) {
            this.mTitleView.setVisibility(View.VISIBLE);
            this.mTitleView.setText(resId);
        }

        public void setTitle(CharSequence title) {
            this.mTitleView.setVisibility(View.VISIBLE);
            this.mTitleView.setText(title);
        }

        public void setMessage(int resId) {
            this.mMessageView.setText(resId);
        }

        public void setMessage(CharSequence message) {
            this.mMessageScrollview.setVisibility(View.VISIBLE);
            this.mMessageView.setText(message);
        }

        public void setPositiveButton(String text, View.OnClickListener listener) {
            Button button = new Button(MiDialog.this.mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            button.setLayoutParams(params);
//            button.setBackgroundResource(R.drawable.material_card);
            button.setTextColor(Color.argb(255, 35, 159, 242));
            button.setText(text);
            button.setGravity(17);
            button.setTextSize(14.0F);
            button.setPadding(MiDialog.this.dip2px(12.0F), 0, MiDialog.this.dip2px(32.0F), MiDialog.this.dip2px(9.0F));
            if (listener == null) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MiDialog.this.dismiss();
                    }
                });
            } else {
                button.setOnClickListener(listener);
            }
            this.mButtonLayout.addView(button);
        }

        public void setNegativeButton(String text, View.OnClickListener listener) {
            Button button = new Button(MiDialog.this.mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            button.setLayoutParams(params);
//            button.setBackgroundResource(R.drawable.material_card);
            button.setText(text);
            button.setTextColor(Color.argb(222, 0, 0, 0));
            button.setTextSize(14.0F);
            button.setGravity(17);
            button.setPadding(0, 0, 0, MiDialog.this.dip2px(8.0F));
            if (listener == null) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MiDialog.this.dismiss();
                    }
                });
            } else {
                button.setOnClickListener(listener);
            }

            if (this.mButtonLayout.getChildCount() > 0) {
                params.setMargins(20, 0, 10, MiDialog.this.dip2px(9.0F));
                button.setLayoutParams(params);
                this.mButtonLayout.addView(button, 1);
            } else {
                button.setLayoutParams(params);
                this.mButtonLayout.addView(button);
            }

        }

        public void setView(View view) {
            LinearLayout l = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.contentView);
            l.removeAllViews();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    System.out.println("-->" + hasFocus);
                    Builder.this.mAlertDialogWindow.setSoftInputMode(5);

                    InputMethodManager imm = (InputMethodManager) MiDialog.this.mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(2, 1);
                }
            });
            l.addView(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;

                int i;
                for (i = 0; i < viewGroup.getChildCount(); ++i) {
                    if (viewGroup.getChildAt(i) instanceof EditText) {
                        EditText autoCompleteTextView = (EditText) viewGroup.getChildAt(i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                }

                for (i = 0; i < viewGroup.getChildCount(); ++i) {
                    if (viewGroup.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView var7 = (AutoCompleteTextView) viewGroup.getChildAt(i);
                        var7.setFocusable(true);
                        var7.requestFocus();
                        var7.setFocusableInTouchMode(true);
                    }
                }
            }
        }

        public void setContentView(View contentView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentView.setLayoutParams(layoutParams);
            LinearLayout linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);
            }

            for (int i = 0; i < linearLayout.getChildCount(); ++i) {
                if (linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) linearLayout.getChildAt(i);
                    autoCompleteTextView.setFocusable(true);
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.setFocusableInTouchMode(true);
                }
            }

        }

        public void setBackgroundColor(int color) {
            LinearLayout linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackgroundColor(color);
        }

        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackground(drawable);
        }

        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout = (LinearLayout) this.mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackgroundResource(resId);
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            MiDialog.this.mAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }
}
