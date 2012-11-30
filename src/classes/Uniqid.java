/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import controllers.UserActif;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author suly
 */
//Permet de générer un id unique pour la synchro
public class Uniqid {
  private String uniqid;
    public Uniqid(UserActif user)
    {
      try {
        byte[] hash = getHash();
        this.uniqid = hashToString(hash)+user.getId();
      }
      catch(NoSuchAlgorithmException e)
      {
        System.out.println(e.getMessage());
      }
    }
    public Uniqid(int iduser)
    {
      try {
        byte[] hash = getHash();
        this.uniqid = hashToString(hash)+iduser;
      }
      catch(NoSuchAlgorithmException e)
      {
        System.out.println(e.getMessage());
      }
    }
    // fonction de cryptage
    public byte[] getHash() throws NoSuchAlgorithmException {
            String ts = Long.toString(System.currentTimeMillis());
            byte[] input = ts.getBytes();
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(input, 0, input.length);
            int hashLength = 20; // SHA-1 donne un hash de longueur 20
            byte[] hash = new byte[hashLength];
            try {
                    digest.digest(hash, 0, hashLength);
            } catch (DigestException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return hash;
    }
    //Retournb le string du cryptage
    public String hashToString(byte[] hash) {  
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < hash.length; i++) {  
            int v = hash[i] & 0xFF; 
            if(v < 16) {
                sb.append("0"); 
            }
            sb.append(Integer.toString(v, 16)); 
        }  
        return sb.toString(); 
}
    public String getUniqid() {
        return uniqid;
    }

    public void setUniqid(String uniqid) {
        this.uniqid = uniqid;
    } 
}
