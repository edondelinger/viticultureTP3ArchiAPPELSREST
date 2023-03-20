package com.example.vititp3bd4.controleur;

import java.util.ArrayList;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vititp3bd4.R;
import com.example.vititp3bd4.modele.Jure;
import com.example.vititp3bd4.modele.JureDAO;
import com.example.vititp3bd4.modele.Viticulteur;
import com.example.vititp3bd4.modele.ViticulteurDAO;

public class MainActivity extends AppCompatActivity {
	private Viticulteur viticulteurSelectionne;
	private Jure jureSelectionne;
	private EditText txtIdJ;
	private TextView lblJure;
	private Button btnEnregistrer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtIdJ = (EditText) findViewById(R.id.txtIdJ);
		lblJure = (TextView) findViewById(R.id.lblJure);
		btnEnregistrer = (Button) findViewById(R.id.btnEnregistrer);
		
		remplirSpinViticulteurs();

		btnEnregistrer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Implementer l'enregistrement de la note pour le couple jure-viticulteur
				Log.d("log",viticulteurSelectionne+" "+jureSelectionne);
			}
		});

		this.testEnGet();
		
		txtIdJ.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				jureSelectionne = null;
				lblJure.setText("");
				if (s.length()>0){
					Long saisie = Long.valueOf(s.toString());
					
					JureDAO jureAcces = new JureDAO(){
						@Override
						public void onTacheTerminee(Jure resultat){ 
							jureSelectionne = resultat;
							Log.d("log","jure : "+jureSelectionne);
							if (jureSelectionne != null){
								lblJure.setText(jureSelectionne.getPrenomJ()+ " "+ jureSelectionne.getNomJ());
							}
						}

						@Override
						public void onTacheTerminee(String resultat) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onTacheTerminee(ArrayList<Jure> resultat) {
							// TODO Auto-generated method stub
							
						}
					};
					jureAcces.getJureByIdJ(saisie);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
			}
	       });
	}

	private void testEnGet() {
		ViticulteurDAO vdao = new ViticulteurDAO() {
			@Override
			public void onTacheTerminee(String resultat) {

			}

			@Override
			public void onTacheTerminee(ArrayList<Viticulteur> resultat) {

			}

			@Override
			public void onTacheTerminee(Viticulteur resultat) {
				Log.d("log","MainActivity.TESTMETHODGET : "+resultat);
			}
		};

		vdao.getViticulteurByIdGet(1l);

	}

	public void remplirSpinViticulteurs(){
	Log.d("log","methode remplirSinViticulteurs()");
	ViticulteurDAO viticulteurAcces = new ViticulteurDAO() {
		@Override
		public void onTacheTerminee(final ArrayList<Viticulteur> resultat){
			Log.d("log","MainActivity.getViticulteurs : "+resultat.toString());
			
			// remplissage du spinner a l'aide de la liste recuperee
			Spinner spinViticulteurs = (Spinner) findViewById(R.id.spinViticulteurs);
			ArrayAdapter<String> spinViticulteursAdapter = new ArrayAdapter<String>(
					MainActivity.this.getBaseContext(),android.R.layout.simple_spinner_item
					);
			for(int i=0;i<resultat.size();i++){
				spinViticulteursAdapter.add(resultat.get(i).getNomV());
			}				
			spinViticulteurs.setAdapter(spinViticulteursAdapter);
			
			// gestion des evenements sur le spinner de selection du viticulteur
			spinViticulteurs.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Log.d("log",arg2 + " "+ resultat.get(arg2));
					viticulteurSelectionne = resultat.get(arg2);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
			});
			
		}

		@Override
		public void onTacheTerminee(String resultat) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTacheTerminee(Viticulteur resultat) {
			// TODO Auto-generated method stub
		}
	};
	viticulteurAcces.getViticulteurs();
}
	
	public void getViticulteurByIdV(Long idV){
		ViticulteurDAO viticulteurAcces = new ViticulteurDAO() {
			@Override
			public void onTacheTerminee(Viticulteur resultat){
				Log.d("log","MainActivity.getViticulteurByIdV : "+resultat);
			}

			@Override
			public void onTacheTerminee(String resultat) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTacheTerminee(ArrayList<Viticulteur> resultat) {
				// TODO Auto-generated method stub
				
			}
		};
		viticulteurAcces.getViticulteurByIdV(idV);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return true;
	}
}
