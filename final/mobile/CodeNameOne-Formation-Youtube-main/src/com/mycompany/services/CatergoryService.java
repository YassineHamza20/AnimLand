/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycomany.entities.CategorieV;
import com.mycomany.utils.CategoryURL;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Mynet
 */
public class CatergoryService {
   ConnectionRequest req;
      static CatergoryService instance = null;
      boolean resultOK = false;
    List<CategorieV> categories = new ArrayList<>();
    public CatergoryService() {
        req = new ConnectionRequest();
    }
   public static CatergoryService getInstance() {
        if (instance == null) {
            instance = new CatergoryService();
        }
        return instance;
    }
 public boolean addCategory(CategorieV category) {
        String url = CategoryURL.BASE_ASSO + "/new/" + category.getType();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 private ArrayList<CategorieV> listCategorieV;
     public ArrayList<CategorieV> getAll() {
        listCategorieV = new ArrayList<>();

        req = new ConnectionRequest();   
        req.setUrl(Statics.BASE_URL);
        //        cr.setUrl(Statics.BASE_URL + "/users/alluser/CarteJson");

        System.out.println("url = "+req);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (req.getResponseCode() == 200) {
                    listCategorieV = (ArrayList<CategorieV>) getList();   
                }

                req.removeResponseListener(this);
            }
        });

        try {
            req.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCategorieV;
    }

    public ArrayList<CategorieV> getList() {
           JSONParser jsonp ;
            jsonp = new JSONParser();
            
        try {
            Map<String, Object> parsedJson =jsonp.parseJSON(new CharArrayReader(                    
                    new String(req.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                CategorieV reclamation = new CategorieV();
                
                      float id = Float.parseFloat( obj.get("id").toString() ) ; 
                      
                        reclamation.setType((String)obj.get("type").toString());
                       
                        
                        //System.out.println("reclamation test :  "+ reclamation);
                        
                         
                        reclamation.setId((int)id);
                
                        listCategorieV.add(reclamation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCategorieV;
    }
    
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     public List<CategorieV> getAllCategories() {
        req = new ConnectionRequest();
        req.setUrl(CategoryURL.BASE_ASSO);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    } 
         public List<CategorieV> parseCategories(String jsonText) {
        categories = new ArrayList<>();
        JSONParser jp = new JSONParser();
        try {
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
            for (Map<String, Object> item : list) {
                categories.add(new CategorieV(
                        (int) Float.parseFloat(item.get("id").toString()),
                        item.get("type").toString()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void deleteCategory(int id) {
        req.setUrl(CategoryURL.BASE_ASSO + "/" + id + "/delete");
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
   public void editCategory(String type) {
    req.setUrl (CategoryURL.BASE_ASSO + "/" + type + "/edit");
    req.setPost(false);
   req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                req.removeResponseListener(this);
            }
        });


   }
}
       