package com.example.ekasilabalexcdtb.sqlite3reportcard.DatabaseHelper;

import android.app.ProgressDialog;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.example.ekasilabalexcdtb.sqlite3reportcard.R;

/**
 * Created by eKasiLab Alex CDTB on 23 Nov 2017.
 */

public class BaseActivity extends AppCompatActivity{
    @VisibleForTesting
    public ProgressDialog mProgressDialog;
    public void showProgressDialog(){
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
