package dtrix.jsonreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView mtextView;
    private ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str = getJSONfile();
        String[] data =null;

        try {
            JSONObject object =new JSONObject(str);
            JSONArray jsonArray = object.getJSONArray("topping");

            data = new String[jsonArray.length()];

            for(int i=0; i<jsonArray.length();i++){
                JSONObject jobj =jsonArray.getJSONObject(i);
                data[i] = jobj.getString("type");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mtextView = (TextView)findViewById(R.id.textView);
        mtextView.setText(R.string.app_name);

        mlistView = (ListView) findViewById(R.id.Listview);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,data);

        if(mlistView != null){
            mlistView.setAdapter(adapter);
        }


    }

    protected String getJSONfile(){
        String jstring = null;
        InputStream file=null;
        try{
            file = getResources().openRawResource(R.raw.samplejson);
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            jstring = new String(buffer,"UTF-8");
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(file != null)
                    file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jstring;
    }
}
