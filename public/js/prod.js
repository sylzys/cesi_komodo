//Fonction pour créer le formulaire d'ajout de chaine de prod
function createFormAddChain()
{
   var div = '<div id="addchain">' +
       '<center>'+ 
            '<form class="formchain" id="formAddChain" name="formAddChain" action="POST" url=""> '+
                '<label style="margin-bottom:10px;" for="lblChain"><b>Désignation :</b></label>'+
                '<input style="height:25px" type="text" id="lblChain" name="lblChain" value=""/>'+
                '<input class="submitMat" onclick="addChain()" style="cursor:pointer" type="button" value="Enregistrer"/>' +
            '</form>' +
        '</center>'+
    '</div>';
    //On écrit dans le dom pour remplir la div detail dans la jqueryDialog
     $("#detail").html(div);
     //Affichage de la jqueryDialog
     $("#detail").dialog({
         title:"",
         width:300, 
         height:200,
         position: ["center","center"],
         modal: true
     });
}

//Fonction pour ajouter une chaine en ajax
function addChain()
{
    var lbl = $("#lblChain").val();
    if(lbl != "")
    {
    $.ajax({'url' : '../prod/ajaxaddchain',
            data:       {'lbl':lbl},
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
         alert("Veuillez renseigner une désignation");
     }
}