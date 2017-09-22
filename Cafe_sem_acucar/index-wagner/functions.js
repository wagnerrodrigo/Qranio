$(function () {
  var operacao = "C"; 
  var selecao_indice = -1; 
  var tbPesoas = localStorage.getItem("tbPesoas"); 
  tbPesoas = JSON.parse(tbPesoas); 
  if (tbPesoas === null)
      tbPesoas = [];

  function Create() {
   
    var pessoa = JSON.stringify({
      Name: $("#txtName").val(),
      Phone: $("#txtPhone").val(),
      Email: $("#txtEmail").val()
    }); 
   
    tbPesoas.push(pessoa);    
    localStorage.setItem("tbPesoas", JSON.stringify(tbPesoas));
    alert("Os dados foram armazenados");
    return true;
  }

  function Edit() {
    
    tbPesoas[selecao_indice] = JSON.stringify({

        Name: $("#txtName").val(),
        Phone: $("#txtPhone").val(),
        Email: $("#txtEmail").val()
    });
    
    localStorage.setItem("tbPesoas", JSON.stringify(tbPesoas)); 
    alert("Os dados foram editados"); 
    return true;
  }

  function Delete() {
   
    tbPesoas.splice(selecao_indice, 1); 
    
    localStorage.setItem("tbPesoas", JSON.stringify(tbPesoas)); 
    alert("Os dados foram apagados"); 
  }

  function List() {
    $("#tblList").html("");
    $("#tblList").html(
            "<thead>" +
            "<tr>" +                
            "<th>Nome</th>" +
            "<th>Telefone</th>" +
            "<th>Email</th>" +
            "<th>Açao</th>" +
            "</tr>" +
            "</thead>" +
            "<tbody>" +
            "</tbody>"
            ); 
    for (var i in tbPesoas) {
        var pes = JSON.parse(tbPesoas[i]);
        $("#tblList tbody").append("<tr>" +                    
                "<td>" + pes.Name + "</td>" +
                "<td>" + pes.Phone + "</td>" +
                "<td>" + pes.Email + "</td>" +                    
                "<td><img src='edit.png' alt='Edit" + i + "' class='btnEdit'/>&nbsp &nbsp<img src='delete.png' alt='Delete" + i + "' class='btnDelete'/></td>" +
                "</tr>"
                );
    } 
  }

  $("#frmPerson").bind("submit", function () {
    if (operacao === "C")
        return Create();
    else
        return Edit();
  });
  
  List();

  $(".btnEdit").bind("click", function () {
    operacao = "E";     
    selecao_indice = parseInt($(this).attr("alt").replace("Edit", ""));    
    var pes = JSON.parse(tbPesoas[selecao_indice]); 
    $("#txtID").val(pes.ID);
    $("#txtName").val(pes.Name);
    $("#txtPhone").val(pes.Phone);
    $("#txtEmail").val(pes.Email);
    $("#txtID").attr("readonly", "readonly");
    $("#txtName").focus();
  });

  $(".btnDelete").bind("click", function () {   
    selecao_indice = parseInt($(this).attr("alt").replace("Delete", "")); 
    Delete(); 
    List(); 
  });
});

