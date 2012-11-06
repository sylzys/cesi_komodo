package controllers;

public class getSteInfos {

    private String nom, prenom, tel, fax, mail;
    private Boolean cancel = false;

    public getSteInfos() {
        this.cancel = true;
    }

    public getSteInfos(String nom, String prenom, String tel, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        //this.fax = fax;
        this.mail = mail;
    }

    public String toString() {
        String str;
        if (this.cancel)
        {
            str = "N/A";
        }
        else
        {
            str = this.nom + "#-#" + this.prenom + "#-#" + this.tel + "#-#" + this.mail;
        }
        return str;
    }

    public Boolean is_ok() {
        if (this.nom.isEmpty() || this.prenom.isEmpty() || this.tel.isEmpty() || this.mail.isEmpty())
        {
            return false;
        }
        return true;
    }
}
