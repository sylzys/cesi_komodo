/*
 *Fichier nécéssaire au fonctionnement du module stock
 *il permet de générer les différents formulaire en jquery et
 *de les poster en ajax
**/
/*
 *Fonction init chargement des fournisseurs
**/
function initStock()
{
    $.ajax({'url' : '../stock/ajaxlistfour',
                    dataType: 'html',
                    type : 'GET',
        success : function (dataFour){
       //Récupération des données encodé en JSON (fournisseur)
       arrayJSONfour = JSON.parse(dataFour);
            }	,    
            error : function(dataFour){
                alert(dataFour.responseText);
            }
    });
    arrayJSONunite = null;
}
/*
 *Fonction pour remplir les infos du fournisseur sélectionné
**/
function infosFour(idP,select){ 
var id = $(select).find('option:selected').attr('id');
if(id != "slctOpt")
    {
        $.ajax({'url' : '../stock/ajaxinfosfour',
                data: {'id_four':id},
                dataType: 'html',
                type : 'POST',
        success : function (dataInfos){
            var arrayJSONinfos = JSON.parse(dataInfos);
            var objInfos = arrayJSONinfos[0];
            $("#interidnew_"+idP).val(objInfos["interid"]);
            $("#rais_"+idP).html("Raison social : &nbsp;<b>"+objInfos["fourrais"]+"</b>");
            $("#addr_"+idP).html("Adresse : &nbsp;<b>"+objInfos["fouradresse"]+"</b>");
            $("#cp_"+idP).html("<b>"+objInfos["fourcp"]+"</b>");
            $("#ville_"+idP).html("<b>"+objInfos["fourville"]+"</b>");
            $("#inter_"+idP).html("Interlocuteur : &nbsp;<b>"+objInfos["internom"]+' '+objInfos["interprenom"]+"</b>");
            $("#mail_"+idP).html("Email : &nbsp;<b>"+objInfos["intermail"]+"</b>");
            $("#tel_"+idP).html("Tél : &nbsp;<b>"+objInfos["intertel"]+"</b>");             
        }	,    
        error : function(dataFour){
            alert(dataFour.responseText);
        }
        });
    }
    else
    {
            $("#interidnew_"+idP).val("");
            $("#rais_"+idP).html("Raison social :");
            $("#addr_"+idP).html("Adresse :");
            $("#cp_"+idP).html("");
            $("#ville_"+idP).html("");
            $("#inter_"+idP).html("Interlocuteur :");
            $("#mail_"+idP).html("Email :");
            $("#tel_"+idP).html("Tél :");   
    }

}
//Fonction pour générer le ou les formulaire(s) de modification de stocks
function createFormUpdtStock(idMat, unitelbl)
{
idMat = 'matId_'+idMat;
//Chargement des données en Ajax avec jquery
$.ajax({'url' : '../stock/ajaxeditmat',
        data: {'id':idMat},
        dataType: 'html',
        type : 'POST',
        success : function (data){ 
            //Récupération des données encodé en JSON
            var arrayJSON = JSON.parse(data);
            //Si la variable vaut false donc pas de stock
            if(arrayJSON == "false")
                {
                    alert("Aucun stock pour cette matière première");
                }
            else
                {
                var matid = arrayJSON[0]["matid"];    
            	//Création du/des formulaire(s) de détail d'un stock. Insertion des formulaires dans une liste à puces afin de la paginer (1 formulaire par page)
                    var div = '<div id="formstock"><ul id="items">';
                     //Boucle sur le nombre de ligne dans le tableau JSON
                    for(var i=0;i<arrayJSON.length;i++) {
                        //On récupère l'objet sur la ligne de valeur i
                        var obj = arrayJSON[i];
                        var formId = 'detailmatForm_' + i;
                        //Si une des dates est null on met le champ à vide pour ne pas afficher null
                        if(obj["stockdatecom"] == null){obj["stockdatecom"] = "";}
                        if(obj["stockentree"] == null){obj["stockentree"] = "";}
                        if(obj["stocksortie"] == null){obj["stocksortie"] = "";}
                        //FORMULAIRE DE MODIFICATION D'UN STOCK
                        div+= '<li>'+
                        '<div class="divDel">'+
                            '<a href="#" onclick="delStock('+ obj["stockid"]+ ')"><img style="width:40px; height:40px;" src="../images/pictos/gris/trash_can.png" alt="Supprimer le stock" title="Supprimer le stock n°'+ obj["stockid"] +'" /></a>'+
                        '</div>'+
                        '<center>'+ 
                            '<form class="formStock" action="POST" id="'+ formId +'" name="'+ formId +'" url="">' +
                                    '<div class="refStock"><p>Référence stock : &nbsp;'+
                                    '<b>'+ obj["stockid"] +'</b></p></div>' + 
                                    '<fieldset class="field">'+
                                        '<legend class="legend"><p>Matière première</p></legend>' +
                                        'Désignation :<br><p style="color:green">'+ obj["matlib"] + '</p>' +
                                    '</fieldset>' +
                                    '<fieldset class="field">'+
                                        '<legend class="legend"><p>Stock</p></legend>' +
                                        '<div id="left">'+
                                            '<label for="reffour">Référence fournisseur :</label>' +
                                            '<input type="text" id="reffour" name="reffour" value="'+ obj["stockreffour"] +'"/>'+
                                            '<label for="qte">Quantité (mesurée en <u>'+unitelbl+'</u>) :</label>' +
                                            '<input type="text" id="qte" name="qte" value="'+ obj["stockqte"] +'"/>'+
                                            '<label for="pu">Prix Unitaire H.T :</label>' +
                                            '<input type="text" id="pu" name="pu" value="'+ obj["stockpu"] +'"/>'+
                                        '</div>' +
                                        '<div id="right">'+
                                            '<label for="dtecom">Date de la commande :</label>' +
                                            '<input style="cursor:pointer" type="text" class="datepicker" id="stockdatecom_' + i +'" name="stockdatecom_' + i +'" value="'+ obj["stockdatecom"] +'"/>' +
                                            '<label for="dteent">Date d\'entrée :</label>' +
                                            '<input style="cursor:pointer" type="text" class="datepicker" id="stockdateent_' + i +'" name="stockdateent_' + i +'" value="'+ obj["stockentree"] +'"/>' +
                                            '<label for="dtesor">Date de sortie :</label>' +
                                            '<input style="cursor:pointer" type="text" class="datepicker" id="stockdatesor_' + i +'" name="stockdatesor_' + i +'" value="'+ obj["stocksortie"] +'"/>' + 
                                        '</div>' +
                                    '</fieldset>' + 
                                    '<fieldset class="field">'+
                                        '<legend class="legend"><p>Fournisseur</p></legend>' +
                                        '<div id="leftFour">'+
                                            '<p id="rais_' + i + '">Raison social : &nbsp;<b>'+obj["fourrais"]+'</b></p>'+
                                            '<p>Entreprise :</p>'+
                                                '<select onchange="infosFour('+i+', this)" style="width:185px" id="fourSlct" name="fourSlct">';
                                                    //Boucle sur les données json fournisseur
                                                    for(var j=0;j<arrayJSONfour.length;j++) {
                                                        var objFour = arrayJSONfour[j];
                                                        if(obj["fourid"]== "" || obj["fourid"] != objFour["fourid"])
                                                        {
                                                        	if(objFour["foursuppr"] == false)
                                                    		{
                                                        		div+='<option id="'+objFour["fourid"]+'">';
                                                                div+= objFour["fournom"];
                                                                div+='</option>';
                                                    		}     
                                                        }
                                                        else
                                                        {
                                                            div+='<option selected id="'+objFour["fourid"]+'">'; 
                                                            div+= objFour["fournom"];
                                                            div+='</option>';
                                                        }   
                                                    }
                                            div+='</select>'+
                                            '<p id="addr_' + i + '">Adresse : &nbsp;<b>'+obj["fouradresse"]+'</b></p><p id="cp_' + i + '"><b>'+obj["fourcp"]+'</b></p><p id="ville_' + i + '"><b>'+obj["fourville"]+'</b></p>'+
                                        '</div>' +
                                        '<div id="rightFour">'+
                                            '<p id="inter_' + i + '">Interlocuteur : &nbsp;<b>'+obj["internom"]+' '+obj["interprenom"]+'</b></p><br>'+
                                            '<p id="mail_' + i + '">Email : &nbsp;<b>'+obj["intermail"]+'</b></p><br>'+
                                            '<p id="tel_' + i + '">Tel : &nbsp;<b>'+obj["intertel"]+'</b></p>'+
                                        '</div>' +
                                    '</fieldset>' +
                                '<input id="stockid" name="stockid" type="hidden" value="'+ obj["stockid"] +'"/>' +
                                '<input id="i" name="i" type="hidden" value="'+ i +'"/>' +
                                '<input id="interidnew_' + i + '" name="interidnew_' + i + '" type="hidden" value="'+ obj["interid"] +'"/>' +
                                '<input onclick="updtStock('+formId+')" style="cursor:pointer" type="button" value="Modifier"/>' +
                            '</form>' +
                        '</center>' +
                        '</li>';
                    }
                    //Fin du formulaire et fermeture de la liste à puce
                    div+= '</ul></div>';
                    //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
                    $("#detail").html(div);
                    //Insertion d'un date picker sur les champs dates
                    $( ".datepicker" ).datepicker({
                           "dateFormat":"yy-mm-dd" 
                    });
                     //Pagination de la liste à puce (1 formulaire par page)
                         $('ul#items').easyPaginate({
                      step:1,
                      delay: 0,
                      nextprev: false
                      });
                     //Affichage de la jqueryDialog
                     $("#detail").dialog({ 
                         title: "Stock de la matière N°"+matid,
                         width:770, 
                         height:620,
                         position: ["center","center"],
                         modal: true
                     });
                } 
            }	,    
            error : function(data){
                alert(data.responseText);
            }
    });
}
/*
 *Fonction pour poster le formulaire de modification d'un stock
**/
function updtStock(form)
{
    $.ajax({'url' : '../stock/ajaxupdtstock',
        data: $(form).serialize(), //envoi du formulaire complet serialisé
        type:       'POST',
        dataType:   'html',
        success: function() {
            alert("Modification apportée");
            $("#detail").dialog( "close" );
            location.reload();
        },
        error : function(data){
            alert(data.responseText);
        }
    });
}
/*
 *Fonction pour supprimer un stock
**/
function delStock(stockid)
{
    if(confirm("Attention le stock n°"+ stockid +" sera définitivement supprimé"))
        {
            stockid = "delstock_" + stockid;
            $.ajax({'url' : '../stock/ajaxdelstock',
            data: {'id':stockid},
                type:       'POST',
                dataType:   'html',
                success: function() {
                    alert("Suppression effectuée");
                    $("#detail").dialog( "close" );
                    location.reload();
                },
                error : function(data){
                    alert(data.responseText);
                }
            });
        }
}
/*
 *Fonction pour créer le formulaire d'ajout de stock
**/
function createFormAddStock(id, matLib, unitelbl)
{
   var formid = "formAddStock";
   var idM = 0;
   var div = '<div id="formstock">' +
       '<center>'+ 
            '<form class="formStock" id="'+ formid +'" name="'+ formid +'" action="POST" url=""> '+
                    '<fieldset class="field">'+
                        '<legend class="legend"><p>Matière première</p></legend>' +
                        'Désignation :<br><p style="color:green">'+ matLib + '</p>' +
                    '</fieldset>' +
                    '<fieldset class="field">'+
                        '<legend class="legend"><p>Stock</p></legend>' +
                        '<div id="left">'+
                            '<label for="reffour">Référence fournisseur :</label>' +
                            '<input type="text" id="reffour" name="reffour" value=""/>'+
                            '<label for="qte">Quantité (mesurée en <u>'+unitelbl+'</u>) :</label>' +
                            '<input type="text" id="qte" name="qte" value=""/>'+
                            '<label for="pu">Prix Unitaire H.T :</label>' +
                            '<input type="text" id="pu" name="pu" value=""/>'+
                        '</div>' +
                        '<div id="right">'+
                            '<label for="dtecom">Date de la commande :</label>' +
                            '<input style="cursor:pointer" type="text" class="datepicker" id="stockdatecom" name="stockdatecom" value=""/>' +
                            '<label for="dteent">Date d\'entrée :</label>' +
                            '<input style="cursor:pointer" type="text" class="datepicker" id="stockdateent" name="stockdateent" value=""/>' +
                            '<label for="dtesor">Date de sortie :</label>' +
                            '<input style="cursor:pointer" type="text" class="datepicker" id="stockdatesor" name="stockdatesor" value=""/>' + 
                        '</div>' +
                    '</fieldset>' + 
                    '<fieldset class="field">'+
                        '<legend class="legend"><p>Fournisseur</p></legend>' +
                        '<div id="leftFour">'+
                            '<p id="rais_0">Raison social :</p>'+
                            '<p>Entreprise :</p>'+
                                '<select onchange="infosFour('+idM+', this)" style="width:185px" id="frnSlct" name="frnSlct"><option id="slctOpt" selected></option>';
                                    //Boucle sur les données json fournisseur
                                    for(var i=0;i<arrayJSONfour.length;i++) {
                                        var objFour = arrayJSONfour[i];
                                        if(objFour["foursuppr"] == false)
                                        	{
	                                        	div+='<option id="'+objFour["fourid"]+'">';
	                                            div+= objFour["fournom"];
	                                            div+='</option>';
                                        	}   
                                    }
                            div+='</select>'+
                            '<p id="addr_0">Adresse :</p><p id="cp_0"</p><p id="ville_0"></p>'+
                        '</div>' +
                        '<div id="rightFour">'+
                            '<p id="inter_0">Interlocuteur :</p><br>'+
                            '<p id="mail_0">Email :</p><br>'+
                            '<p id="tel_0">Tel :</p>'+
                        '</div>' +
                    '</fieldset>' +
                '<input id="matid" name="matid" type="hidden" value="'+ id +'"/>' +
                '<input id="interidnew_0" name="interidnew_0" type="hidden" value=""/>' +
                '<input onclick="addStock('+formid+')" style="cursor:pointer" type="button" value="Enregistrer"/>' +
            '</form>' +
        '</center>'+
     '</div>';
     //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
     $("#detail").html(div);
     //Affichage de la jqueryDialog
     $("#detail").dialog({ 
         title: "Entrée en stock matière N°"+id,
         width:770, 
         height:620,
         position: ["center","center"],
         modal: true
     });
     //Insertion d'un date picker sur les champs dates
      $( ".datepicker" ).datepicker({
           "dateFormat":"yy-mm-dd" 
      });
}
//Fonction pour ajouter un stock
function addStock(form)
{
     var idinter = document.getElementById('interidnew_0').value;
     if(idinter != "")
         {
             $.ajax({'url' : '../stock/ajaxaddstock',
            data: $(form).serialize(), //envoi du formulaire complet serialisé
            type:       'POST',
            dataType:   'html',
            success: function() {
                alert("Enregistrement effectué");
                $("#detail").dialog( "close" );
                location.reload();
            },
            error : function(data){
                alert(data.responseText);
            }
            });
         }
     else
         {
             alert("Veuillez renseigner un fournisseur");
         }
     
}
function listunite()
{
    $.ajax({'url' : '../stock/ajaxlistunite',
        dataType: 'html',
        async: false, 
        type : 'GET',
		success : function (dataUnite){
		//Récupération des données encodé en JSON (unité de mesure)
		 arrayJSONunite = JSON.parse(dataUnite);
		}	,    
		error : function(dataUnite){
		    alert(dataUnite.responseText);
		}
	});
}
//Fonction pour créer le formulaire d'ajout de matiere
function createFormAddMat()
{
	listunite();
   var div = '<div id="addmat">' +
       '<center>'+ 
            '<form class="formMat" id="formAddMat" name="formAddMat" action="POST" url=""> '+
                '<label style="margin-bottom:5px;" for="lblMat"><b>Désignation :</b></label>'+
                '<input style="height:25px" type="text" id="lblMat" name="lblMat" value=""/>'+
   				'<label style="margin-bottom:10px;" for="unite"><b>Unité de mesure :</b></label>'+
   				'<select id="unite" name="unite"><option id="slct_0" selected></option>';

			    //Boucle sur les données json unite
			    for(var i=0;i<arrayJSONunite.length;i++) {
			       var objUnite = arrayJSONunite[i];
			       if(objUnite["unitesuppr"] == false)
			       	{
			           	div+='<option id="'+objUnite["uniteid"]+'">';
			               div+= objUnite["unitelbl"];
			               div+='</option>';
			       	}   
			    }
                div+= '</select>'+
                '<input class="submitMat" onclick="addMat()" style="cursor:pointer" type="button" value="Enregistrer"/>' +
            '</form>' +
        '</center>'+
    '</div>';
    //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
     $("#detail").html(div);
     //Affichage de la jqueryDialog
     $("#detail").dialog({
         title:"Ajouter une matière première",
         width:470, 
         height:260,
         position: ["center","center"],
         modal: true
     });
}
//Fonction pour ajouter une matière en ajax
function addMat()
{
    var lbl = $("#lblMat").val();
    var uniteid = $("#unite").find('option:selected').attr('id');
    if(lbl != "" && uniteid != "slct_0")
    {
    $.ajax({'url' : '../stock/ajaxaddmat',
            data:       {
            	'lbl':lbl,
            	'uniteid': uniteid
            },
            type:       'POST',
            dataType:   'html',
            success: function() {
                alert("Enregistrement effectué");
                $("#detail").dialog( "close" );
                location.reload();
            },
            error : function(data){
                alert(data.responseText);
            }
            });
     }
     else
     {
         alert("Tous les champs sont obligatoires");
     }
}
//Fonction pour supprimer une matière en ajax
function delMat(matid)
{
    if(confirm("Attention la matière première n°"+ matid +" sera définitivement supprimée"))
        {
            matid = "delmat_" + matid;
            $.ajax({'url' : '../stock/ajaxdelmat',
            data: {'id':matid},
                type:       'POST',
                dataType:   'html',
                success: function() {
                    alert("Suppression effectuée");
                    $("#detail").dialog( "close" );
                    location.reload();
                },
                error : function(data){
                    alert(data.responseText);
                }
            });
        }
}
//Fonction pour créer le formulaire de modification d'une matière
function createFormUpdtMat(matid, matlib, uniteid)
{
listunite();
   var div = '<div id="updtmat">' +
       '<center>'+ 
            '<form class="formMat" id="formUpdtMat" name="formUpdtMat" action="POST" url=""> '+
                '<label style="margin-bottom:5px;" for="lblMat"><b>Désignation :</b></label>'+
                '<input style="height:25px" type="text" id="lblMat" name="lblMat" value="'+matlib+'"/>'+
                '<label style="margin-bottom:10px;" for="unite"><b>Unité de mesure :</b></label>'+
   				'<select id="unite" name="unite"><option id="slct_0" selected></option>';
			    //Boucle sur les données json unite
			    for(var i=0;i<arrayJSONunite.length;i++) {
			       var objUnite = arrayJSONunite[i];
		    	   if(uniteid == objUnite["uniteid"])
		    		   {
		    		   		div+='<option selected id="'+objUnite["uniteid"]+'">';
		    		   	    div+= objUnite["unitelbl"];
			                div+='</option>';
		    		   }
		    	   else if(objUnite["unitesuppr"] == false)
		    		   {
		    		   		div+='<option id="'+objUnite["uniteid"]+'">';
		    		   	    div+= objUnite["unitelbl"];
			                div+='</option>';
		    		   }     
			    }
                div+= '</select>'+
                '<input type="hidden" id="idMat" name="idMat" value="'+matid+'"/>'+
                '<input class="submitMat" onclick="updtMat(this.form)" style="cursor:pointer" type="button" value="Modifier"/>' +
            '</form>' +
        '</center>'+
    '</div>';
     //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
     $("#detail").html(div);
     //Affichage de la jqueryDialog
     $("#detail").dialog({ 
         title:"Modifier une matière première",
         width:470, 
         height:260,
         position: ["center","center"],
         modal: true
     });
}
//Fonction pour envoyer le formulaire de modification d'une matière en ajax
function updtMat(form)
{
    var lbl = $("#lblMat").val();
    var matid = $("#idMat").val();
    var uniteid = $("#unite").find('option:selected').attr('id');
    if(lbl != "" && uniteid != "slct_0")
    {
    $.ajax({'url' : '../stock/ajaxupdtmat',
            data:       {
            	'lbl':lbl,
            	'matid': matid, 
            	'uniteid':uniteid
            	},
            type:       'POST',
            dataType:   'html',
            success: function() {
                alert("Modification apportée");
                $("#detail").dialog( "close" );
                location.reload();
            },
            error : function(data){
                alert(data.responseText);
            }
            });
     }
     else
     {
         alert("Tous les champs sont obligatoires");
     }
}
//Fonction pour créer le formulaire de commande de matière
function createFormCmdMat(matid, matlib, unitelbl)
{
   var div = '<div id="cmdmat">' +
       '<center>'+ 
           '<p style="margin-top:10px; margin-bottom:20px;">Désignation : <b>'+matlib+'</b></p><hr>'+
            '<form class="formMat" id="formCmdMat" name="formCmdMat" action="POST" url=""> '+
            '<label for="frnSlct">Fournisseur :</label>'+
             '<select style="width:185px" id="frnSlct" name="frnSlct"><option id="slctOpt" selected></option>';
                    //Boucle sur les données json fournisseur
                    for(var i=0;i<arrayJSONfour.length;i++) {
                        var objFour = arrayJSONfour[i];
                        div+='<option id="'+objFour["fourid"]+'">';
                        div+= objFour["fournom"];
                        div+='</option>';
                    }
                div+='</select>'+
                '<label for="qte">Quantité (mesurée en <u>'+unitelbl+'</u>):</label>'+
                '<input type="text" id="qte" name="qte" value=""/>' +
                '<label for="reffour">Référence fournisseur :</label>'+
                '<input type="text" id="reffour" name="reffour" value=""/>' +  
                '<label for="pu">Prix unitaire H.T :</label>'+
                '<input type="text" id="pu" name="pu" value=""/>' + 
                '<input type="hidden" id="matid" name="matid" value="'+matid+'"/> '+
                '<input class="submitMat" onclick="addCmdMat()" style="cursor:pointer" type="button" value="Enregistrer"/>' +
            '</form>' +
        '</center>'+
    '</div>';
    //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
     $("#detail").html(div);
     //Affichage de la jqueryDialog
     $("#detail").dialog({
         title: "Commande pour la matière N°"+matid,
         width:500, 
         height:400,
         position: ["center","center"],
         modal: true
     });
}
//Fonction pour envoyer le formulaire de commande d'une matière en ajax
function addCmdMat()
{
    var matid = $("#matid").val();
    var idFrn = $("#frnSlct option:selected").attr("id");
    var qte = $("#qte").val();
    var pu = $("#pu").val();
    var reffour = $("#reffour").val();
    var testNumQte = verif_numeric(qte);
    var testNumPu = verif_numeric(pu);
    if(idFrn != "slctOpt" && qte!="" && testNumQte == true && pu!="" && testNumPu == true && reffour != "")
    {
            $.ajax({'url' : '../stock/ajaxcmdmat',
                    data:       {'matid':matid, 'idFrn':idFrn, 'qte':qte, 'pu':pu, 'reffourmat':reffour},
                    type:       'POST',
                    dataType:   'html',
                    success: function(data) {
                        alert("Commande enregistrée");
                        $("#detail").dialog( "close" );
                        //Redirection vers le pdf
                        $(location).attr('href',document.url+data);
                    },
                    error : function(data){
                        alert(data.responseText);
                    }
                    });
     }
     else
     {
         if(idFrn == "slctOpt" || qte=="" || pu=="" || reffour =="")
         {
             alert("Tous les champs sont obligatoires");
         }
         else if(testNumQte == false)
         {
             alert("La quantité doit être un entier");
         }
         else if(testNumPu == false)
         {
           alert("Le prix unitaire doit être un entier");  
         }   
     }
}
function verif_numeric(variable)
{
var exp = new RegExp("^[0-9]+$","g");
return exp.test(variable);
}
function tableUnite() {
	//Récupérer la liste des unité en ajax
	$.ajax({'url' : '../stock/ajaxlistunite',
        dataType: 'html',
        type : 'GET',
	success : function (dataUnite){
		//parse résultat JSON
		var arrayUnite = JSON.parse(dataUnite);
		var divUnite = '<form id="formUnite" name="formUnite" action="POST"><table class="tablUnite" id="tabUnite" name="tabUnite"><thead><tr><th>Identifiant</th><th>Libellé</th><th></th></tr></thead><tbody>';
		 for(var i=0;i<arrayUnite.length;i++) {
	         var objUnite = arrayUnite[i];
	         var idUnite = objUnite["uniteid"];
	         var lblUnite = objUnite["unitelbl"];
	         var unitesuppr = objUnite["unitesuppr"];
	         if (unitesuppr != true)
	        	 {
		        	 divUnite += '<tr><td>'+idUnite+'</td><td><p style="display:none">'+lblUnite+'</p><input id="row_'+idUnite+'" style="margin:0 auto;min-width:300px" type="text" value="'+lblUnite+'"/></td>'+
			         '<td style="width:70px;">'+
			         '<a style="cursor:pointer"'+
						'onclick="updtUnite('+idUnite+')">'+
						'<img style="width: 30px; height: 30px;"'+
						'src="../images/modifier.png"'+
						'alt="Modifier l\'unité de mesure"'+
						'title="Modifier l\'unité de mesure" />'+
					'</a>'+
					'<a style="cursor:pointer"'+
						'onclick="delUnite('+idUnite+')">'+
						'<img style="width: 30px; height: 30px;"'+
						'src="../images/corbeille.png"'+
						'alt="Supprimer l\'unité de mesure"'+
						'title="Supprimer l\'unité de mesure" />'+
					'</a>'+
			         '</td></tr>';   
	        	 } 
		 }
		  divUnite+= '</tbody></table></form>';
		  $("#divunite").html(divUnite);
		  $('#divunite').hide();
		  setTimeout(function()
		  {
			$('#divunite').fadeIn(500);
		  }, 100);
		  $("table.tablUnite").dataTable({
          "iDisplayLength": 6,
            "oLanguage": {
                "sUrl": "../js/traduction_fr_datatable.txt"
            },
            "bStateSave":true,
            "sPaginationType": "full_numbers",
            "bLengthChange": false,
            "bInfo": false,
            "bAutoWidth": false,
            "aoColumns": [
            { "bSortable": true },
            { "bSortable": true },
            { "bSortable": false }
            ],
            "aaSorting": [[ 2, "asc" ]]
        } );
	}	,    
	error : function(dataUnite){
	    alert(dataUnite.responseText);
	}
	});
	 
}
function createFormUnite()
{
	var divAddUnite = '<div id="addunite">' +
         '<form class="formAddUnite" id="formAddUnite" name="formAddUnite" action="POST"> '+
             '<div style="float:left; margin-top:3px;"><label for="lblAddUnite"><b>Libellé :</b></label></div>'+
             '<div style="float:left; margin-top:1px; margin-right:5px; margin-left:5px;"><input type="text" id="lblAddUnite" name="lblAddUnite" value=""/></div>'+
             '<div style="float:left"><a href="#" onclick="addUnite()"><img style="width:24px; height:24px;" src="../images/enregistrer.png" alt="Enregistrer l\'unité de mesure" title="Enregistrer l\'unité de mesure" /></a></div>'+
         '</form></div><hr></hr>';
	var divUnite = '<div id="divunite"></div>';  
	//On écrit dans le dom pour remplir la div detail dans la jqueryDialog
	$("#detail").html(divAddUnite + divUnite);
	tableUnite();
	//Affichage de la jqueryDialog
	$("#detail").dialog({
      title:"Gestion des unités de mesure",
      width:600, 
      height:550,
      position: ["center","center"],
      modal: true
	});

}

function addUnite() {
	 var lbl = document.getElementById('lblAddUnite').value;
     if(lbl != "")
         {
             $.ajax({'url' : '../stock/ajaxaddunite',
            data: {'lbl':lbl},
            type:       'POST',
            dataType:   'html',
            success: function() {
                alert("Enregistrement effectué");
                document.getElementById('lblAddUnite').value = "";
                tableUnite();
            },
            error : function(data){
                alert(data.responseText);
            }
            });
         }
     else
         {
             alert("Veuillez renseigner un libellé");
         }
}
function updtUnite(id){
	var lbl = document.getElementById('row_'+id+'').value;
     if(lbl != "")
         {
             $.ajax({'url' : '../stock/ajaxupdtunite',
            data: {'idUnite':id,'lbl':lbl},
            type:       'POST',
            dataType:   'html',
            success: function() {
                alert("Modification apportée");
                tableUnite(); 
            },
            error : function(data){
                alert(data.responseText);
            }
            });
         }
     else
         {
             alert("Veuillez renseigner un libellé");
         }
}
function delUnite(id){
	var lbl = document.getElementById('row_'+id+'').value;
	if(confirm("Attention l'unité de mesure \""+lbl+"\" sera définitivement supprimée"))
    {
        $.ajax({'url' : '../stock/ajaxdelunite',
        data: {'idUnite':id},
            type:       'POST',
            dataType:   'html',
            success: function() {
                alert("Suppression effectuée");
                tableUnite(); 
            },
            error : function(data){
                alert(data.responseText);
            }
        });
    }
}

