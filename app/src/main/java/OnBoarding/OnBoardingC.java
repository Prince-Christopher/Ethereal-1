package OnBoarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ethereal.Activities.MainActivity;
import com.example.ethereal.R;


public class OnBoardingC extends Fragment {

    TextView skiptomain3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding_c, container, false);

        skiptomain3 = view.findViewById(R.id.skiptomain3);
        skiptomain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplication(), MainActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}