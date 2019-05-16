package com.dariahaze.learning_english.ui.videoLessons;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariahaze.learning_english.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoLessonsFragment extends Fragment {


    public VideoLessonsFragment() {
        // Required empty public constructor
    }

    public static VideoLessonsFragment newInstance() {
        VideoLessonsFragment fragment = new VideoLessonsFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_lessons, container, false);
    }

}
