package com.example.vititp3bd4.utilitaire;

import com.example.vititp3bd4.modele.Viticulteur;

import java.util.ArrayList;


// Force la classe qui l'implemente a avoir les methodes indiquees ci-dessous
// Cette methode sera appelee lorsque la tache asynchrone sera terminee
public interface EventAsyncViticulteur { 
	public abstract void onTacheTerminee(String resultat);
	public abstract void onTacheTerminee(ArrayList<Viticulteur> resultat);
	public abstract void onTacheTerminee(Viticulteur resultat);

}
