package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    Button xmlparse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json_stu = getListData();
        try {
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            ListView listview = findViewById(R.id.students);
            JSONObject jsObj = new JSONObject(json_stu);
            JSONArray jsArray = jsObj.getJSONArray("Students");
            for (int i = 0; i < jsArray.length(); i++) {
                HashMap<String, String> stu = new HashMap<>();
                JSONObject obj = jsArray.getJSONObject(i);
                stu.put("name", obj.getString("name"));
                stu.put("Branch", obj.getString("Branch"));
                stu.put("institute", obj.getString("institute"));
                userList.add(stu);
            }
            ListAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, userList, R.layout.list, new String[]{"name", "Branch", "institute"}, new int[]{R.id.StudentName, R.id.Branch, R.id.institute});
            listview.setAdapter(simpleAdapter);
        } catch (JSONException ex) {
            Log.e("JsonParser ", "Exception", ex);
        }
        xmlparse=findViewById(R.id.xmlparser);
        xmlparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),XmlParser.class);
                startActivity(i);

            }
        });
    }
    private String getListData() {
        String json_stu1 = "{ \"Students\" :[" +
                "{\"name\":\"Shruti Mahajan \",\"Branch\":\"Computer Science\",\"institute\":\"PCCOE\"}" +
                ",{\"name\":\"Shreya Mainkar\",\"Branch\":\"Biotechnology\",\"institute\":\" MIT\"}" +
                ",{\"name\":\"Riya Sharma \",\"Branch\":\"Information Technology\",\"institute\":\" PIBM\"}" +
                ",{\"name\":\"Amit Kumar\",\"Branch\":\"Mechanical\",\"institute\":\" MIT\"}" +
                ",{\"name\":\"Nutan Kadam\",\"Branch\":\"Textile\",\"institute\":\" IICMR\"}" +
                ",{\"name\":\"Vibha Thakur\",\"Branch\":\"Electrical\",\"institute\":\" IISER-Pune\"}" +
                ",{\"name\":\"Sima Patil\",\"Branch\":\"Mechanical\",\"institute\":\"IIT\"}] }";
        return json_stu1;
    }

}