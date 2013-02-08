package controllers;

import com.google.common.base.Strings;

public class getInterlocuteurInfos {

    private String nom, prenom, tel, fax, mail;
    private Boolean cancel = false;
    private String str = "";

    public getInterlocuteurInfos() {
        this.cancel = true;
    }

    public getInterlocuteurInfos(String nom, String prenom, String tel, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        //this.fax = fax;
        this.mail = mail;
    }

    public String toString() {
        String str = "";

        if (this.cancel)
        {
            return "ERROR";
        }
        else
        {
            if (is_ok())
            {

                return (this.nom + "#-#" + this.prenom + "#-#" + this.tel + "#-#" + this.mail);
            }
            else
            {
                return (str);
            }
        }
        // return str;
    }

    public Boolean is_ok() {
        Integer error = 0;
        if (Strings.isNullOrEmpty(this.nom) || this.nom.trim().isEmpty())
        {
            str += "<html>Le champ <i>Nom</i> ne peut être vide<br />";
            error = 1;
        }
        if (Strings.isNullOrEmpty(this.prenom) || this.prenom.trim().isEmpty())
        {
            str += "<html>Le champ <i>Prénom</i> ne peut être vide<br />";
            error = 1;
        }
        if (Strings.isNullOrEmpty(this.tel) || this.tel.trim().isEmpty())
        {
            str += "<html>Le champ <i>Téléphone</i> ne peut être vide<br />";
            error = 1;
        }
        if (Strings.isNullOrEmpty(this.mail) || this.mail.trim().isEmpty())
        {
            str += "<html>Le champ <i>Email</i> ne peut être vide<br />";
            error = 1;
        }
        else
        {
            EmailValidator ev = new EmailValidator();
            if (!ev.validate(this.mail))
            {
                str += "<html>L'adresse <i>Email</i> est invalide<br />";
                error = 1;
            }
        }
        if (error == 1)
        {
            str += "</html>";
            return false;
        }
        return true;
    }

    public String getStr() {
        return str;
    }
}
