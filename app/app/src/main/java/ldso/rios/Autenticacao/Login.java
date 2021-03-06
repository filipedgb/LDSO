package ldso.rios.Autenticacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import ldso.rios.DataBases.DB_functions;
import ldso.rios.DataBases.User;
import ldso.rios.MainActivities.Homepage;
import ldso.rios.R;

import static ldso.rios.MainActivities.Homepage.PREFS_NAME;

public class Login extends AppCompatActivity {

    protected EditText email;
    protected EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(Homepage.PREFS_NAME, 0);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Autenticação");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email=(EditText)findViewById(R.id.email_login);
        password=(EditText)findViewById(R.id.password_login);
    }

    public void login(View view) throws IOException, JSONException {
        String email_txt=email.getText().toString();
        String password_txt=password.getText().toString();
        if (DB_functions.haveNetworkConnection(getApplicationContext()))
        DB_functions.login(this,email_txt, password_txt, this);
        else {
            Toast toast = Toast.makeText(Login.this, "Sem ligação à Internet", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void register(View view){
        startActivity(new Intent(this, Register.class));
    }

    public void login_response(final Boolean error, final String error_txt) {

        new Thread()
        {
            public void run()
            {
                Login.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        Toast toast;
                        Context context = getApplicationContext();
                        if (error){
                            toast = Toast.makeText(context, ""+error_txt, Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else {
                            User u = User.getInstance();
                            toast = Toast.makeText(context, "Bem vindo "+u.getName(), Toast.LENGTH_LONG);
                            toast.show();

                            updateSharedPreferencesUser();

                            finish();
                        }
                    }
                });
            }
        }.start();

    }

    public void updateSharedPreferencesUser() {
        User u = User.getInstance();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("id",String.valueOf(u.getId()));
        editor.putString("token",u.getAuthentication_token());
        editor.putString("name",u.getName());
        editor.putString("email",u.getEmail());

        editor.putString("telef",u.getTelef());
        editor.putString("profissao",u.getProfissao());
        editor.putString("habilitacoes",u.getHabilitacoes());
        editor.putString("formacao", String.valueOf(u.getFormacao()));
        editor.putString("distrito",u.getDistrito());
        editor.putString("concelho",u.getConcelho());

        editor.putString("permissoes",u.getPermissoes().toString());

        // Commit the edits!
        editor.commit();
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
}

