/**
 * Javascript pour le module fournisseur
 */
function formAddFrn()
{
   var div = '<div id="addfrn">' +
   '<center>'+ 
        '<form class="formAddFrn" id="formAddFrn" name="formAddFrn" action="POST"> '+
		    '<fieldset class="field">'+
		        '<legend class="legend"><p>Fournisseur</p></legend>' +
		        '<div class="left">'+
		            '<label for="addrFrn">Adresse :</label>' +
		            '<input type="text" id="addrFrn" name="addrFrn" value=""/>' +
		            '<label for="cpFrn">Code Postale :</label>' +
		            '<input type="text" id="cpFrn" name="cpFrn" value=""/>' +
		            '<label for="villeFrn">Ville :</label>' +
		            '<input type="text" id="villeFrn" name="villeFrn" value=""/>' + 
		        '</div>' +
			    '<div class="right">'+
		            '<label for="raisFrn">Forme juridique :</label>' +
		            '<input type="text" id="raisFrn" name="raisFrn" value=""/>'+
		            '<label for="nomFrn">Raison sociale :</label>' +
		            '<input type="text" id="nomFrn" name="nomFrn" value=""/>'+
			    '</div>' +     
		    '</fieldset>' + 
		    '<fieldset class="field">'+
		        '<legend class="legend"><p>Interlocuteur</p></legend>' +
		        '<div class="left">'+
		            '<label for="nomInter">Nom :</label>' +
		            '<input type="text" id="nomInter" name="nomInter" value=""/>' +
		            '<label for="prenomInter">Prénom :</label>' +
		            '<input type="text" id="prenomInter" name="prenomInter" value=""/>' + 
		        '</div>' +
			    '<div class="right">'+
		            '<label for="mailInter">Email :</label>' +
		            '<input type="text" id="emailInter" name="emailInter" value=""/>'+
		            '<label for="telInter">Téléphone :</label>' +
		            '<input type="text" id="telInter" name="telInter" value=""/>'+
			    '</div>' +
		   '</fieldset>' +
           '<input class="submitMat" onclick="addFrn(this.form)" style="cursor:pointer" type="button" value="Enregistrer"/>' +
        '</form>' +
    '</center>'+
    '</div>';
    //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
     $("#jdialog").html(div);
     //Affichage de la jqueryDialog
     $("#jdialog").dialog({
         title:"Ajouter un fournisseur",
         width:770, 
         height:450,
         position: ["center","center"],
         modal: true
     });
}
function addFrn(form) 
{
	$.ajax({'url' : '../stock/addfourn',
        data: $(form).serialize(), //envoi du formulaire complet serialisé
        type:       'POST',
        dataType:   'html',
        success: function() {
            alert("Enregistrement effectué");
            $("#jdialog").dialog( "close" );
            location.reload();
        },
        error : function(data){
            alert(data.responseText);
        }
    });
}
function delFrn(frnid) {
	if(confirm("Attention le fournisseur sera définitivement supprimé"))
    {
        var frn = "delfrn_" + frnid;
        $.ajax({'url' : '../stock/deletefourn',
        data: {'id':frn},
            type:       'GET',
            dataType:   'html',
            success: function() {
                alert("Suppression effectuée");
                $("#jdialog").dialog( "close" );
                location.reload();
            },
            error : function(data){
                alert(data.responseText);
            }
        });
    }	
}
function interFrn(interid)
{
	var inter = "inter_" + interid;
	$.ajax({'url' : '../stock/interfrn',
		data: {'id':inter},
        dataType: 'html',
        type : 'GET',
	success : function (dataInter){
	//Récupération des données encodé en JSON (fournisseur)
	var arrayJSONInter = JSON.parse(dataInter);
	//Boucle sur les données json fournisseur
    var objInter = arrayJSONInter[0];
    var div = '<div id="inter">' +
	 		        '<div class="left">'+
	 		            '<p><b>Nom :</b> '+objInter["internom"]+'</p><br>'+
	 		            '<p><b>Prénom :</b> '+objInter["interprenom"]+'</p>'+
	 		        '</div>' +
	 			    '<div class="right">'+
	 			    	'<p><b>Email :</b> '+objInter["intermail"]+'</p><br>'+
	 			    	'<p><b>Téléphone :</b> '+objInter["intertel"]+'</p>'+
	 			    '</div>' +
 			    '</div>';
     //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
      $("#jdialog").html(div);
      //Affichage de la jqueryDialog
      $("#jdialog").dialog({
          title:"Coordonnées de l'interlocuteur",
          width:450, 
          height:120,
          position: ["center","center"],
          modal: true
      });
	}	,    
	error : function(dataInter){
	    alert(dataInter.responseText);
	}
});
}
function formUpdtFrn(frnid)
{
	var frn = "frn_" + frnid;
	$.ajax({'url' : '../stock/infosupdtfrn',
		data: {'id':frn},
        dataType: 'html',
        type : 'GET',
	success : function (dataFrn){
	//Récupération des données encodé en JSON
	var arrayJSONFrn = JSON.parse(dataFrn);
    var objFrn = arrayJSONFrn[0];
    var div = '<div id="updtfrn">' +
    '<center>'+ 
         '<form class="formUpdtFrn" id="frmUpdtFrn" name="frmUpdtFrn" action="POST">'+
 		    '<fieldset class="field">'+
 		        '<legend class="legend"><p>Fournisseur</p></legend>' +
 		        '<div class="left">'+
 		            '<label for="addrFrn">Adresse :</label>' +
 		            '<input type="text" id="addrFrn" name="addrFrn" value="'+ objFrn["fouradresse"] +'"/>' +
 		            '<label for="cpFrn">Code Postale :</label>' +
 		            '<input type="text" id="cpFrn" name="cpFrn" value="'+ objFrn["fourcp"] +'"/>' +
 		            '<label for="villeFrn">Ville :</label>' +
 		            '<input type="text" id="villeFrn" name="villeFrn" value="'+ objFrn["fourville"] +'"/>' + 
 		        '</div>' +
 			    '<div class="right">'+
 		            '<label for="raisFrn">Forme juridique :</label>' +
 		            '<input type="text" id="raisFrn" name="raisFrn" value="'+ objFrn["fourrais"] +'"/>'+
 		            '<label for="nomFrn">Raison sociale :</label>' +
 		            '<input type="text" id="nomFrn" name="nomFrn" value="'+ objFrn["fournom"] +'"/>'+
 			    '</div>' +     
 		    '</fieldset>' + 
 		    '<fieldset class="field">'+
 		        '<legend class="legend"><p>Interlocuteur</p></legend>' +
 		        '<div class="left">'+
 		            '<label for="nomInter">Nom :</label>' +
 		            '<input type="text" id="nomInter" name="nomInter" value="'+ objFrn["internom"] +'"/>' +
 		            '<label for="prenomInter">Prénom :</label>' +
 		            '<input type="text" id="prenomInter" name="prenomInter" value="'+ objFrn["interprenom"] +'"/>' + 
 		        '</div>' +
 			    '<div class="right">'+
 		            '<label for="mailInter">Email :</label>' +
 		            '<input type="text" id="emailInter" name="emailInter" value="'+ objFrn["intermail"] +'"/>'+
 		            '<label for="telInter">Téléphone :</label>' +
 		            '<input type="text" id="telInter" name="telInter" value="'+ objFrn["intertel"] +'"/>'+
 		            '<input type="hidden" id="idInter" name="idInter" value="'+ objFrn["interid"] +'"/>'+
 		            '<input type="hidden" id="idFrn" name="idFrn" value="'+ objFrn["fourid"] +'"/>'+
 			    '</div>' +
 		   '</fieldset>' +
            '<input class="submitMat" onclick="updtFrn(this.form)" style="cursor:pointer" type="button" value="Modifier"/>' +
         '</form>' +
     '</center>'+
     '</div>';
     //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
      $("#jdialog").html(div);
      //Affichage de la jqueryDialog
      $("#jdialog").dialog({
          title:"Modifier un fournisseur",
          width:770, 
          height:450,
          position: ["center","center"],
          modal: true
      });
	}	,    
	error : function(dataFrn){
	    alert(dataFrn.responseText);
	}
});
}
function updtFrn(form)
{
	$.ajax({'url' : '../stock/editfourn',
        data: $(form).serialize(), //envoi du formulaire complet serialisé
        type:       'POST',
        dataType:   'html',
        success: function() {
            alert("Modification apportée");
            $("#jdialog").dialog( "close" );
            location.reload();
        },
        error : function(data){
            alert(data.responseText);
        }
    });
}