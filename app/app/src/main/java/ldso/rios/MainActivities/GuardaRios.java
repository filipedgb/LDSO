package ldso.rios.MainActivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import ldso.rios.DataBases.DB_functions;
import ldso.rios.Form.GuardaRios_form;
import ldso.rios.R;

public class GuardaRios extends AppCompatActivity {


    GridLayout gridLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guarda_rios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Guarda Rios");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            DB_functions.getGuardaRios(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gridLayout = (GridLayout) findViewById(R.id.girdLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void form_guardarios(View view){
        startActivity(new Intent(this, GuardaRios_form.class));
    }


    public void images(final String s) throws JSONException {
        new Thread()
        {
            public void run()
            {
                GuardaRios.this.runOnUiThread(new Runnable()
                {
                    public void run() {
                        try {
                            JSONObject g= new JSONObject(s);
                            Log.e("entrou aqui","");
                            final JSONArray array= g.getJSONArray("guardarios");
                            Log.e("entrou aqui","");
                            for(int i =0;i<array.length();i++)
                            {
                                if (i==0)
                                {
                                    final int finalI = i;
                                    Thread thread = new Thread(new Runnable(){
                                        @Override
                                        public void run() {
                                            try {
                                                JSONObject guardario= array.getJSONObject(finalI);

                                                JSONObject array_imagens= (JSONObject) guardario.get("image");

                                                final String url=DB_functions.base_url+array_imagens.get("url");
                                                Log.e("url",url);

                                                final ImageView imageView= (ImageView) findViewById(R.id.imageView);
                                                final Drawable d=LoadImageFromWebOperations(url);
                                                final Bitmap b= LoafImageURL(url);

                                                new Thread()
                                                {
                                                    public void run()
                                                    {
                                                        GuardaRios.this.runOnUiThread(new Runnable()
                                                        {
                                                            public void run() {
                                                                imageView.setImageBitmap(b);



                                                            }
                                                        });
                                                    }
                                                }.start();






                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    thread.start();
                                }







                            }



                        } catch (Exception e){

                        }



                    }
                });
            }
        }.start();


    }

    public Bitmap LoafImageURL(String url) throws IOException {
        URL url_value = new URL(url);
        Bitmap srcBmp =BitmapFactory.decodeStream(url_value.openConnection().getInputStream());
        Bitmap dstBmp;

        if (srcBmp.getWidth() >= srcBmp.getHeight()){

            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    srcBmp.getWidth()/2 - srcBmp.getHeight()/2,
                    0,
                    srcBmp.getHeight(),
                    srcBmp.getHeight()
            );

        }else{

            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    0,
                    srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
                    srcBmp.getWidth(),
                    srcBmp.getWidth()
            );
        }
        return dstBmp;
    }

    public static Drawable LoadImageFromWebOperations(String url) {



        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            Log.e("nao nulo","img nao nula");
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("nulo","img nula");
            return null;
        }
    }
}
