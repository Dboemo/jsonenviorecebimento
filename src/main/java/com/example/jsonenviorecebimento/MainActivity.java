package com.example.jsonenviorecebimento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    Button btenvia;
    private static String url2 = "http://200.132.17.49/webservice/consulta.php";


    TextView tv1;
    private ListView listagem;
    ArrayList<HashMap<String, String>> arraylista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arraylista = new ArrayList<>();
        listagem = (ListView) findViewById(R.id.lista);
        new buscavalores().execute();
        btenvia=(Button) findViewById(R.id.btenvia);
        btenvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Enviajsonpost().execute();
            }
        });
    }
    class Enviajsonpost extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "http://200.132.17.49/webservice/insere_noticia.php";
                JSONObject jsonValores = new JSONObject();
                jsonValores.put("titulo", "deu problema");
                jsonValores.put("autor", "jose");
                jsonValores.put("conteudo", "Daniel Ganhou nova mega sena");
                jsonValores.put("data", "2024-11-10");


                conexaouniversal mandar = new conexaouniversal();
                String mensagem=mandar.postJSONObject(url,jsonValores);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }


    }

    private class  buscavalores extends AsyncTask<Void, Void, Void> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Lendo Dados ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            manipulahttp sh = new manipulahttp();
            String jsonStr = sh.requisitaservico(url2);

            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray sistemas = jsonObj.getJSONArray("noticia");

                for (int i = 0; i < sistemas.length(); i++) {
                    JSONObject noticia = sistemas.getJSONObject(i);
                    String titulo = noticia.getString("titulo");
                    String autor = noticia.getString("autor");
                    String conteudo = noticia.getString("conteudo");
                    String data = noticia.getString("data");

                    HashMap<String, String> sistema = new HashMap<>();
                    sistema.put("titulo",titulo);
                    sistema.put("autor",autor);
                    sistema.put("conteudo",conteudo);
                    sistema.put("data",data);
                    arraylista.add(sistema);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, arraylista,
                    R.layout.list_item, new String[]{"titulo", "autor","conteudo","data"}, new int[]{R.id.titulo,
                    R.id.autor,R.id.conteudo,R.id.data});

            listagem.setAdapter(adapter);

            manipulahttp sh = new manipulahttp();
            Toast.makeText(MainActivity.this,"Teste de recebimento",Toast.LENGTH_LONG).show();
        }

    }

}