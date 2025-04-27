package com.example.pfe;

import java.io.Serializable;
public class Devoir implements Serializable{
    private String id;
    private String titre;
    private String description;
    private String fileURL;
    private String idFormation;
    private String nomFormation;
    private String semestre;
    private String date;
    private String dateLimite;
    private String formateurId;
    private String formateurNom;
    private String createdAt;

    public Devoir() {
        // Constructeur vide requis pour Firebase
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFileURL() { return fileURL; }
    public void setFileURL(String fileURL) { this.fileURL = fileURL; }

    public String getIdFormation() { return idFormation; }
    public void setIdFormation(String idFormation) { this.idFormation = idFormation; }

    public String getNomFormation() { return nomFormation; }
    public void setNomFormation(String nomFormation) { this.nomFormation = nomFormation; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDateLimite() { return dateLimite; }
    public void setDateLimite(String dateLimite) { this.dateLimite = dateLimite; }

    public String getFormateurId() { return formateurId; }
    public void setFormateurId(String formateurId) { this.formateurId = formateurId; }

    public String getFormateurNom() { return formateurNom; }
    public void setFormateurNom(String formateurNom) { this.formateurNom = formateurNom; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
