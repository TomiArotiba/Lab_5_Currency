package com.example.lab_5_app;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<String> getRates(InputStream stream) throws IOException, XmlPullParserException {
        ArrayList<String> rates = new ArrayList<>();
        String singleRate = "rate";

        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            xmlFactoryObject.setNamespaceAware(true);
            XmlPullParser myParser = xmlFactoryObject.newPullParser();
            myParser.setInput(stream, null);

            int event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){
                String name = myParser.getName();
                switch (event){
                    case XmlPullParser.TEXT:
                        singleRate = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equalsIgnoreCase("title")){
                            if(singleRate.startsWith("XML")){
                                break;
                            }
                            else{
                                rates.add(singleRate);
                            }

                        }
                        break;
                }
                event = myParser.next();
            }
        }
        catch (XmlPullParserException e){
            e.printStackTrace();
        }
        return rates;
    }
}