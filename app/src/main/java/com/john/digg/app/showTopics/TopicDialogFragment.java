package com.john.digg.app.showTopics;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.john.digg.R;

/**
 * Created by changjohn on 2017/5/4.
 */

public class TopicDialogFragment extends DialogFragment implements TopicContract.TopicDialogView{
    private TopicPresenter mPresenter;

    public interface TopicDialogListener {
        public void onPostClick();
        public void onCancellClick();
    }

    private TopicDialogListener mListener;
    protected EditText mEdtContent = null;

    public TopicDialogFragment() {
        mPresenter = new TopicPresenter(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (TopicDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        Button btnPost = (Button) v.findViewById(R.id.id_btn_post);
        Button btnCancel = (Button) v.findViewById(R.id.id_btn_cancel);
        mEdtContent = (EditText) v.findViewById(R.id.id_txt_content);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEdtContent.getText().toString();
                mPresenter.addTopic("John", content);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
        return v;
    }

    @Override
    public void notifyCreation() {
        getDialog().dismiss();
        mListener.onPostClick();
    }

    @Override
    public void notifyError() {
        Toast.makeText(TopicDialogFragment.this.getContext(),"Error",Toast.LENGTH_SHORT);
        mListener.onCancellClick();
    }
}
