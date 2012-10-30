
package controllers;
public class getInterlocuteurInfos {
   private String nom, prenom, tel, fax, mail;

   public getInterlocuteurInfos(){}
   public getInterlocuteurInfos(String nom, String prenom, String tel, String fax, String mail){
      this.nom = nom;
      this.prenom = prenom;
      this.tel = tel;
      this.fax = fax;
      this.mail = mail;
   }
      
   public String toString(){
      String str;
//      if(this.login != null && this.pass != null ){
//        str = this.login + "#" + Encryptor.encode(this.pass);
//         
//      }
//      else{
//         str = "-NA-";
//      }
      return "interlo modified";
   }
}
