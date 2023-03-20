package com.example.vititp3bd4.modele;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.vititp3bd4.utilitaire.AccesHTTP;
import com.example.vititp3bd4.utilitaire.EventAsyncViticulteur;


public abstract class  ViticulteurDAO implements EventAsyncViticulteur {
	private static final String serveur="";
	private static final String chemin="apiviticulture/";
	
	public ViticulteurDAO(){
	}
	
	public void getViticulteurs(){		
		AccesHTTP objAsyncTask = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				Log.d("log","onPostExecute ViticulteurDAO");
				onTacheTerminee(jsonStringToViticulteurArrayList(this.ret));
			}
		}; 
		objAsyncTask.execute("http://"+serveur+chemin+"getViticulteurs.php");
	}
	
	public void getViticulteurByIdV(Long idV){		
		AccesHTTP requeteHttp = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				Log.d("log","onPostExecute ViticulteurDAO");
				onTacheTerminee(jsonStringToViticulteur(this.ret));
			}
		}; 
		requeteHttp.addParam("idV", idV.toString());
		requeteHttp.execute("http://"+serveur+chemin+"getViticulteurByIdV.php");
	}

	public void getViticulteurByIdGet(Long idV){
		AccesHTTP requeteHttp = new AccesHTTP(){
			@Override
			protected void onPostExecute(Long result) {
				Log.d("log","onPostExecute ViticulteurDAO");
				onTacheTerminee(jsonStringToViticulteur(this.ret));
			}
		};
		requeteHttp.requestType = "GET";
		//requeteHttp.addParam("idV", idV.toString());
		requeteHttp.execute("http://"+serveur+chemin+"getViticulteursByIdvGet.php?idV=" + idV.toString());
	}

	
	private ArrayList<Viticulteur> jsonStringToViticulteurArrayList(String jsonString){
		Log.d("log","conversion jsonToViticulteurArray : "+jsonString);

		ArrayList<Viticulteur> listeViticulteur = new ArrayList<Viticulteur>();
		long idV;
		String nomV;
			
		try {
			JSONArray tabJson = new JSONArray(jsonString);
			for(int i=0;i<tabJson.length();i++){
				idV = Long.parseLong(tabJson.getJSONObject(i).getString("idV"));
				nomV = tabJson.getJSONObject(i).getString("nomV");
				listeViticulteur.add(new Viticulteur(idV,nomV));
			}
		}
		catch (JSONException e){
			Log.d("log","pb decodage JSON");
		}
		return listeViticulteur;
	}
	
	private Viticulteur jsonStringToViticulteur(String jsonString){
		Log.d("log","conversion jsonToViticulteur : "+jsonString);
		
		Viticulteur unViticulteur=null;
		long idV;
		String nomV;
			
		try {
			JSONObject objJson = new JSONObject(jsonString);
			idV = Long.parseLong(objJson.getString("idV"));
			nomV = objJson.getString("nomV");
			unViticulteur = new Viticulteur(idV,nomV);
			
		}
		catch (JSONException e){
			Log.d("log","pb decodage JSON");
		}
		return unViticulteur;
	}
}
