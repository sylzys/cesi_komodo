package controllers;

import com.google.common.base.Strings;

public class getInterlocuteurInfos {

    private String nom, prenom, tel, fax, mail;
    private Boolean cancel = false;

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

        if (!this.cancel)
        {
            if (is_ok() != "")
            {

                str = this.nom + "#-#" + this.prenom + "#-#" + this.tel + "#-#" + this.mail;
            }
            else {
                str = is_ok();
            }
        }
        return str;
    }

    public String is_ok() {
        String str = "";
        if (Strings.isNullOrEmpty(this.nom) || this.nom.trim().isEmpty())
        {
            str += "<html>Le champ <i>Nom</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(this.prenom) || this.prenom.trim().isEmpty())
        {
            str += "<html>Le champ <i>Prénom</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(this.tel) || this.tel.trim().isEmpty())
        {
            str += "<html>Le champ <i>Téléphone</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(this.mail) || this.mail.trim().isEmpty())
        {
            str += "<html>Le champ <i>Email</i> ne peut être vide<br />";
        }
        else
        {
            EmailValidator ev = new EmailValidator();
            if (!ev.validate(this.mail))
            {
                str += "<html>L'adresse <i>Email</i> est invalide<br />";
            }
        }
        if (!Strings.isNullOrEmpty(str))
        {
            str += "</html>";
        }
        return str;
    }
}
