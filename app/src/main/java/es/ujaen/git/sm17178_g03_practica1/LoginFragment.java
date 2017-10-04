package es.ujaen.git.sm17178_g03_practica1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.


     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String ip, int port) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, ip);
        args.putInt(ARG_PARAM2, port);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_login, container, false);
        Button connect = (Button) fragment.findViewById(R.id.button_login);


        final EditText name = (EditText) fragment.findViewById(R.id.editText_login_user);
        final EditText user = (EditText) fragment.findViewById(R.id.editText_login_password);
        final EditText pass = (EditText) fragment.findViewById(R.id.editText_login_ip);
        final EditText ip = (EditText) fragment.findViewById(R.id.editText_login_ip);
        final EditText port = (EditText) fragment.findViewById(R.id.editText_login_port);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_user = user.getText().toString();
                String s_pass =  pass.getText().toString();
                String s_ip =  ip.getText().toString();
                String s_port =  port.getText().toString();
               String nombre = name.getText().toString();
                Toast.makeText(getContext(),"Hola " + nombre + " "+s_user+ "" +s_pass+" "+s_ip+" "+s_port,Toast.LENGTH_LONG).show();
            }
        });

        return fragment;

    }

}
