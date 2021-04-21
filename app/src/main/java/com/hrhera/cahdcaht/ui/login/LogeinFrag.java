package com.hrhera.cahdcaht.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hrhera.cahdcaht.R;
import com.hrhera.cahdcaht.ui.MainActivity;
import com.hrhera.cahdcaht.ui.RegisterFrag;
import com.hrhera.cahdcaht.ui.home.HomeFrag;
import com.hrhera.cahdcaht.utl.DataMannger;
import com.hrhera.cahdcaht.utl.model.OnUserData;
import com.hrhera.cahdcaht.utl.model.User;

import java.util.Objects;

public class LogeinFrag extends Fragment implements OnUserData {


    public LogeinFrag() {
    }


    EditText phoneEdit, pass;
    private boolean isRememberME;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_logein, container, false);
        ((MainActivity) getActivity()).hidActionBar();
        phoneEdit = root.findViewById(R.id.getUserPhone);
        pass = root.findViewById(R.id.getPass);
        Button login = root.findViewById(R.id.Login);
        CheckBox rememberME=root.findViewById(R.id.rememberME);
        isRememberME=DataMannger.getInstance().isRemember(getContext());


        rememberME.setChecked(isRememberME);
        rememberME.setOnCheckedChangeListener((compoundButton, checked) ->
                isRememberME=checked
                );


        login.setOnClickListener(v -> {

            if (phoneEdit.getText().length() == 0) {
                phoneEdit.setError(getString(R.string.this_is_requir));
                return;
            }
            if (pass.getText().length() == 0) {
                pass.setError(getString(R.string.this_is_requir));
                return;
            }
            String phone = phoneEdit.getText().toString();
            String password = pass.getText().toString();
            User user = new User();
            user.setPassword(password);
            user.setPhone(phone);
            if (isRememberME){
                DataMannger.getInstance().setRememberMeStatus(getContext(),isRememberME);
                DataMannger.getInstance().setUserData(phone,password);
            }

            DataMannger.getInstance().startLogin(user, this, getContext());


        });



        if (isRememberME){
            phoneEdit.setText(DataMannger.getInstance().getPhone());
            pass.setText(DataMannger.getInstance().getPass());
            login.callOnClick();
        }





        root.findViewById(R.id.reg).setOnClickListener(v -> ((MainActivity) Objects.requireNonNull(getActivity())).AttatchFrag(new RegisterFrag()));


        return root;
    }


    @Override
    public void onDone() {
        ((MainActivity) Objects.requireNonNull(getActivity())).AttatchFrag(new HomeFrag());

    }

    @Override
    public void onFail(String Msg) {
        if (Msg.equals(getString(R.string.not_user))) {
            phoneEdit.setError(Msg);
        } else {
            pass.setError(Msg);
        }
    }
}
