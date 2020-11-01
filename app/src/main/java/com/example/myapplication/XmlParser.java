package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import java.io.InputStream;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;
        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.TextView;


public class XmlParser extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);
        tv1=(TextView)findViewById(R.id.textView1);

        try {
            InputStream is = getAssets().open("file.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    tv1.setText(tv1.getText()+"\nName : " + getValue("name", element2)+" ");
                    tv1.setText(tv1.getText()+ getValue("surname", element2)+"\n");
                    tv1.setText(tv1.getText()+"Address : " + getValue("address", element2)+"\n");
                    tv1.setText(tv1.getText()+"Contact : " + getValue("contact", element2)+"\n");
                    tv1.setText(tv1.getText()+"Salary : " + getValue("salary", element2)+"\n");
                    tv1.setText(tv1.getText()+"-------------------------------------------------");

                }
            }
        } catch (Exception e) {e.printStackTrace();}

    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}