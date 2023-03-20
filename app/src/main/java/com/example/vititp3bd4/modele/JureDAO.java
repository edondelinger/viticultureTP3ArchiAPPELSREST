package com.example.vititp3bd4.modele;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.vititp3bd4.utilitaire.AccesHTTP;
import com.example.vititp3bd4.utilitaire.EventAsyncJure;


public abstract class JureDAO implements EventAsyncJure {
	private static final String serveur="";
	private static final String chemin="apiviticulture/";
	
	public JureDAO(){
	}
		
	public void getJures(){		
		AccesHTTP objAsyncTask = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				Log.d("log","onPostExecute JureDAO2");
				onTacheTerminee(jsonStringToJureArrayList(this.ret));
			}
		}; 
		objAsyncTask.execute("http://"+serveur+chemin+"getJures.php");
	}
	
	public void getJureByIdJ(Long idJ){
		AccesHTTP requeteHttp = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				Log.d("log","onPostExecute JureDAO2");
				onTacheTerminee(jsonStringToJure(this.ret));
			}
		}; 
		requeteHttp.addParam("idJ", idJ.toString());
		requeteHttp.execute("http://"+serveur+chemin+"getJureByIdJ.php");
	}

	
	private ArrayList<Jure> jsonStringToJureArrayList(String jsonString){
		Log.d("log","conversion jsonToJureArray : "+jsonString);

		ArrayList<Jure> listeJure = new ArrayList<Jure>();
		long idJ;
		String nomJ;
		String prenomJ;
			
		try {
			JSONArray tabJson = new JSONArray(jsonString);
			for(int i=0;i<tabJson.length();i++){
				idJ = Long.parseLong(tabJson.getJSONObject(i).getString("idJ"));
				nomJ = tabJson.getJSONObject(i).getString("nomJ");
				prenomJ = tabJson.getJSONObject(i).getString("prenomJ");
				listeJure.add(new Jure(idJ,nomJ,prenomJ));
			}
		}
		catch (JSONException e){
			Log.d("log","pb decodage JSON");
		}
		return listeJure;
	}
	
	private Jure jsonStringToJure(String jsonString){
		Log.d("log","conversion jsonToJure : "+jsonString);
		
		Jure unJure=null;
		long idJ;
		String nomJ;
		String prenomJ;
			
		try {
			JSONObject objJson = new JSONObject(jsonString);
			idJ = Long.parseLong(objJson.getString("idJ"));
			nomJ = objJson.getString("nomJ");
			prenomJ = objJson.getString("prenomJ");
			unJure = new Jure(idJ,nomJ,prenomJ);
			
		}
		catch (JSONException e){
			Log.d("log","pb decodage JSON");
		}
		return unJure;
	}
}
