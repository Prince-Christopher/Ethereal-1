package OnBoarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ethereal.Activities.BreatheActivity;
import com.example.ethereal.Activities.MainActivity;
import com.example.ethereal.R;

public class OnBoardingB extends Fragment {

    TextView skiptomain2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_boarding_b, container, false);

        skiptomain2 = view.findViewById(R.id.skiptomain2);
        skiptomain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplication(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.fadeinsplash, R.anim.fadeoutsplash);
                getActivity().finish();
            }
        });
        return view;
    }
}