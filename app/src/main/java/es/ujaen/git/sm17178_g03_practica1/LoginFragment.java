package es.ujaen.git.sm17178_g03_practica1;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


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
     *
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


        final EditText user = (EditText) fragment.findViewById(R.id.editText_login_user);
        final EditText pass = (EditText) fragment.findViewById(R.id.editText_login_pass);


        connect.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View view) {
                String s_user = user.getText().toString();
                String s_pass = pass.getText().toString();


                ConnectionUserData data = new ConnectionUserData(
                        s_user, s_pass);
                Toast.makeText(getContext(), "Hola " + s_user + " con contraseña: " + s_pass + "\n Con direccion Ip"
                        , Toast.LENGTH_LONG).show();
                TareaAutentica tarea = new TareaAutentica();
                tarea.execute(data);
                //Intent nueva = new Intent(getActivity(), BaseAplication.class);
               // nueva.putExtra(BaseAplication.PARAM_USER, data.getUser());
                //nueva.putExtra("param_pass", data.getPass());

                //startActivity(nueva);



                /*Intent nueva = new Intent(getActivity(),ServiceActivity.class); //Prepara la actividad, se puede llamar tambien a otras aplicaciones
                nueva.putExtra(ServiceActivity.PARAM_USER,data.getUser());
                nueva.putExtra("param_pass",data.getPass());
                nueva.putExtra("param_ip",data.getConnectionIP());
                nueva.putExtra("param_port",data.getConnectionPort());
                //Hacerlo mejor con un bundle
                startActivity(nueva);*/
            }
        });

        return fragment;
    }
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class TareaAutentica extends AsyncTask<ConnectionUserData,Void,String> {

        private ConnectionUserData data;
        public String doInBackground(ConnectionUserData... param) { //Los tres puntos es de java y significa que param puede ser un array
            try {
                data=param[0];
                String s_user = data.user;
                String s_pass = data.pass;
                Socket client = new Socket(InetAddress.getByName("www4.ujaen.es"), 80);
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                String temp = "GET /~jccuevas/ssmm/autentica.php?user=" + s_user + "&pass=" + s_pass + " HTTP/1.1\r\nhost:www4.ujaen.es\n\r\n\r\n";
                output.write(temp.getBytes());

                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();}


            if (param != null)
                if (param.length >= 1)
                    data = param[0];
            //TODO proceso de autenticación
            return "OK";//OK si la operacion fue correcta y si no otro valor
        }


        public void onPostExecute(String result){

            if(result.compareToIgnoreCase("OK")==0) {

                Intent nueva = new Intent(getActivity(), BaseAplication.class);
                nueva.putExtra(BaseAplication.PARAM_USER, data.getUser());
                nueva.putExtra("param_pass", data.getPass());

                startActivity(nueva);
            }else
            {
                Toast.makeText(getContext(),"Error autenticando a "+data.getUser(),Toast.LENGTH_LONG).show();//se obtienen los datos si es ok si no se muestra error autenticado
            }
        }
    }
}








