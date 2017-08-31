package com.app.heydriver.heydriver.model;

import com.app.heydriver.heydriver.common.Entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAPGrock on 8/30/2017.
 */

public class ApiNHTSA {

    public ArrayList<String> getVehicleBrands() throws Exception {
        try {
            ArrayList<String> brands = new ArrayList<String>();
            URL url = new URL("https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=xml");
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(url);
            Element root = doc.getRootElement();
            List<Element> list = root.getChildren();
            Element result = list.get(2);
            List<Element> resultsList = result.getChildren();
            for (Element e:resultsList) {
                List<Element> aux = e.getChildren();
                String h = aux.get(1).getValue();
                brands.add(h);
            }
            return brands;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public ArrayList<String> getModelsOfABrand(String brand) throws Exception {
        try {
            ArrayList<String> models = new ArrayList<String>();
            URL url = new URL("https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/" + brand + "?format=xml");
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(url);
            Element root = doc.getRootElement();
            List<Element> list = root.getChildren();
            Element result = list.get(3);
            List<Element> resultsList = result.getChildren();
            for (Element e:resultsList) {
                List<Element> aux = e.getChildren();
                String h = aux.get(3).getValue();
                models.add(h);
            }
            return models;
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
