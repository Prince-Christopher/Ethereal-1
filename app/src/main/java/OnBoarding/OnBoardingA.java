package OnBoarding;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ethereal.Activities.BreatheActivity;
import com.example.ethereal.Activities.MainActivity;
import com.example.ethereal.Activities.SigninActivity;
import com.example.ethereal.R;

import io.github.muddz.styleabletoast.StyleableToast;

public class OnBoardingA extends Fragment {

    TextView skiptomain1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding_a, container, false);


        skiptomain1 = view.findViewById(R.id.skiptomain1);
        skiptomain1.setOnClickListener(new View.OnClickListener() {
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