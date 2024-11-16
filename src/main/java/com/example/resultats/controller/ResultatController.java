package com.example.resultats.controller;

import com.example.resultats.model.Resultat;
import com.example.resultats.repository.ResultatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resultat") // Utilisation de "/resultat" comme base pour toutes les URLs
public class ResultatController {

    private final ResultatRepository resultatRepository;

    public ResultatController(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    // Afficher tous les résultats
    @GetMapping
    public String afficherResultats(Model model) {
        List<Resultat> resultats = resultatRepository.findAll();
        model.addAttribute("resultats", resultats);
        model.addAttribute("resultat", new Resultat()); // Objet vide pour le formulaire d'ajout
        return "resultats"; // Retourne le fichier HTML "resultats.html"
    }

    // Charger un résultat spécifique pour modification
    @GetMapping(params = "id")
    public String afficherFormulaireModification(@RequestParam Long id, Model model) {
        Resultat resultat = resultatRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID invalide : " + id));
        model.addAttribute("resultat", resultat); // Charger le résultat existant
        model.addAttribute("resultats", resultatRepository.findAll()); // Charger tous les résultats
        return "resultats"; // Retourne le fichier HTML
    }

    // Ajouter ou modifier un résultat
    @PostMapping
    public String ajouterOuModifierResultat(@ModelAttribute Resultat resultat) {
        resultatRepository.save(resultat); // Sauvegarder ou mettre à jour
        return "redirect:/resultat"; // Rediriger vers la liste des résultats
    }

    // Supprimer un résultat
    @GetMapping("/supprimer/{id}")
    public String supprimerResultat(@PathVariable Long id) {
        resultatRepository.deleteById(id); // Supprimer le résultat par ID
        return "redirect:/resultat"; // Rediriger vers la liste des résultats
    }
}
