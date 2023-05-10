/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Produit;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sarra Kachouandi
 */
public class ServiceProduit {
      public static ServiceProduit instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceProduit getInstance() {
        if(instance == null )
            instance = new ServiceProduit();
        return instance ;
    }
    private ArrayList<Object> Produits;
    
    
    public ServiceProduit() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutProduit(Produit produit) {
        
        String url = Statics.BASE_URL+"produit/neww?nom="+produit.getNom()+"&prix="+produit.getPrix()+"&photo="+produit.getPhoto(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
//           String url = Statics.BASE_URL + "produit/new";
        req.setUrl(url);
//         req.addArgument("name", produit.getNom());
//        req.addArgument("prix", produit.getPrix()+"");
//        req.addArgument("photo", produit.getPhoto());
     
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
//      
//         public ArrayList<Produit>produit() {
//        ArrayList<Produit> result = new ArrayList<>();
//        
        public ArrayList<Object> parseProduit(String jsonText){
        try {
            Produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> mapReclamations = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)mapReclamations.get("root");
            for(Map<String,Object> obj : list){
                    Produit t = new Produit();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);

              

                    t.setNom(obj.get("nom").toString());
                    float Prix = Float.parseFloat(obj.get("prix").toString());
                         t.setPrix((int)Prix);
                    
                Produits.add(t);
     

            }
            
            
        } catch (IOException ex) {
            
        }
        
        return Produits;
    }


        
          public ArrayList<Object> produit(){
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"produit/showall";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;
    }

        //String url = Statics.BASE_URL+"/tasks/";
//        req=new ConnectionRequest();
//        String url = Statics.BASE_URL+"produit/";
//        System.out.println(url);
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                result = parseCoachs(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return Coachs;
//    }
        
//        String url = Statics.BASE_URL+"produit/";
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp ;
//                jsonp = new JSONParser();
//                
//                try {
//                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
//                    
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Produit re = new Produit();
//                        
//                        //dima id fi codename one float 5outhouha
//                        float id = Float.parseFloat(obj.get("id").toString());
//                        
//                        String nom = obj.get("nom").toString();
//                        String photo = obj.get("photo").toString();
//                        
//                    float prix = Float.parseFloat(obj.get("prix").toString());
//                       
//                        
//                        re.setId((int)id);
//                        re.setNom(nom);
//                        re.setPhoto(photo);
//                      
//                        re.setPrix((float)prix);
//                        
//                     
//                        
//                        //insert data into ArrayList result
//                        result.add(re);
//                       
//                    
//                    }
//                    
//                }catch(Exception ex) {
//                    
//                    ex.printStackTrace();
//                }
//            
//            }
//        });
//        
//      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//        return result;
//        
//        
//    }
    
    

   
    
    
    //Delete 
    public boolean deleteReclamation(int id ) {
        String url = Statics.BASE_URL +"produit/deleteproduitJSON1/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
//    
//    
//    
    //Update 
    public boolean modifierReclamation(Produit produit) {
        String url = Statics.BASE_URL +"produit/edit1/"+produit.getId()+"?"+"&nom="+produit.getNom()+"&prix="+produit.getPrix();
        
                
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
}   

    
//}
