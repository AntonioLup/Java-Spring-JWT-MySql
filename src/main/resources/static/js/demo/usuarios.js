// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
   actualizarEmailDelUsuario();

});
function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}
function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'Authorization': localStorage.token
   };
}
async function cargarUsuarios(){

      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()
      }).catch(error => console.log(error));
      const usuarios = await request.json();

      console.log(usuarios);

       let listadoHtml = "";

      for(let usuario of usuarios){
        let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger  btn-circle"> <i class="fas fa-trash"></i></a>';
        let telefono = usuario.telefono == null ? '-' : usuario.telefono;
        let usuariotabla = '<tr><td>'+usuario.id+'</td> <td>'+usuario.nombre
                           + ' '+ usuario.apellido
                           +'</td> <td>'+usuario.email
                           + '</td><td>'+telefono
                           + '</td> <td class="d-flex justify-content-center"> '+botonEliminar+' </td> </tr>';
      listadoHtml += usuariotabla;
      }
      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;


}

async function eliminarUsuario(id){
        if (!confirm('¿Desea eliminar este usuario?')) {
          return;
        }

       const request = await fetch('api/usuarios/' + id, {
          method: 'DELETE',
          headers:  getHeaders()

        }).then((response) => response.json())
        .catch((error) => console.log(error));
       location.reload()
}
