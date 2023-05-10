/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.serviceV;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entity.ServiceV;
import com.mycompany.myapp.helper.ServiceURL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mynet
 */
public class ServiceService {
    
     ConnectionRequest req;
      static ServiceService instance = null;
      boolean resultOK = false;
    List<ServiceV> services = new ArrayList<>();
    private ServiceService() {
        req = new ConnectionRequest();
    }
   public static ServiceService getInstance() {
        if (instance == null) {
            instance = new ServiceService();
        }
        return instance;
    }
 public boolean addService(ServiceV service) {
        String url = ServiceURL.BASE_ASSO + "/new/" + service.getId();
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
 
     public List<ServiceV> getAllServices() {
        req = new ConnectionRequest();
        req.setUrl(ServiceURL.BASE_ASSO);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                services = parseServices(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return services;
    } 
         public List<ServiceV> parseServices(String jsonText) {
        services = new ArrayList<>();
        JSONParser jp = new JSONParser();
        try {
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
            for (Map<String, Object> item : list) {
                services.add(new ServiceV(
                              
                
              (int) Float.parseFloat(item.get("id").toString()),
              Double.parseDouble(item.get("prix").toString()),
              item.get("Nom").toString(),
              item.get("Description").toString(),
              item.get("Date").toString(),
              (int) Float.parseFloat(item.get("Category").toString()),
              (int) Float.parseFloat(item.get("ViewCount").toString()),
              (int) Float.parseFloat(item.get("Likes").toString())
               // Parse prix as double
));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    public void deleteService(int id) {
        req.setUrl(ServiceURL.BASE_ASSO + "/" + id + "/delete");
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }


   }
    
