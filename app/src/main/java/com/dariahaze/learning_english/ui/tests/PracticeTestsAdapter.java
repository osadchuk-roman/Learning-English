package com.dariahaze.learning_english.ui.tests;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.PracticeTest;
import com.dariahaze.learning_english.ui.grammar.WebViewActivity;
import com.dariahaze.learning_english.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class PracticeTestsAdapter extends RecyclerView.Adapter<PracticeTestsAdapter.ViewHolder> {
    private List<String> dataSet;
    private String topicLarge;

    public void setTopicLarge(String topicLarge) {
        this.topicLarge = topicLarge;
    }

    public PracticeTestsAdapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    public List<String> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<String> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PracticeTestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_practice, parent, false);
        return new PracticeTestsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PracticeTestsAdapter.ViewHolder holder, int position) {
        holder.setItem(dataSet.get(position));

    }

    @Override
    public int getItemCount() {
        if(dataSet !=null)
            return dataSet.size();
        else return 0;    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private int number;
        private String name;
        private PracticeTest practiceTest;
        private ConstraintLayout constraintLayout;
        private TextView nameTV, numberTV, bestScoreTV, timeTV;


        public ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            constraintLayout = itemView;
            nameTV = constraintLayout.findViewById(R.id.testName);
            numberTV = constraintLayout.findViewById(R.id.testNumberTV);
            bestScoreTV = constraintLayout.findViewById(R.id.bestScoreTV);
            timeTV = constraintLayout.findViewById(R.id.testTimeTV);
        }

        public void setItem(String element) {
            this.name = element;
            nameTV.setText(name);
            number = getAdapterPosition()+1;
            ObjectMapper mapper = new ObjectMapper();
            try {
                practiceTest = mapper.readValue(constraintLayout.getContext().getAssets()
                        .open(Utils.PRACTICE_TESTS_PATH + name + ".json"), PracticeTest.class);
                System.out.println(practiceTest);
                numberTV.setText(number+"");
                timeTV.setText(practiceTest.getMinutes()+"'");
                bestScoreTV.setText("0/"+practiceTest.getQuestions().size());

                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent (v.getContext(), PracticeTestActivity.class);
                        intent.putExtra("Test",practiceTest);
                        intent.putExtra("Topic",name);
                        v.getContext().startActivity(intent);
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("FILE NOT OPENED");
            }
        }
    }
}

