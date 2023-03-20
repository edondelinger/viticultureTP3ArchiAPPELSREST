package com.example.vititp3bd4.utilitaire;

import com.example.vititp3bd4.modele.Jure;

import java.util.ArrayList;
// Force la classe qui l'implemente a avoir les methodes indiquees ci-dessous
// Cette methode sera appelee lorsque la tache asynchrone sera terminee
public interface EventAsyncJure { 
	public void onTacheTerminee(String resultat);
	public void onTacheTerminee(ArrayList<Jure> resultat);
	public void onTacheTerminee(Jure resultat);

}
