/*$(document).ready(function (){
  $('.table .editBtn').on('click', function (event){
     event.preventDefault();
     var href = $(this).attr('href');

    $.get(href, function (user, status){
        $('.myEditForm #id').val(user.id);
        $('.myEditForm #name').val(user.name);
        $('.myEditForm #emal').val(user.email);
     });

    $('.myEditForm #exampleModal').modal();
  });
});*/

/*$('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var recipient = button.data('whatever') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)
    $.get(href, function (userList, status){
        $('.exampleModal #id').val(user.id);
        $('.exampleModal #name').val(user.name);
        $('.myEditForm #emal').val(user.email);
    });

    modal.find('.modal-title').text('New message to ' + recipient)
    modal.find('.modal-body input').val(recipient)
})*/
async function getUsers(id) {
    let jobs = [];
    let job = fetch(`http://localhost:8089/adminrest/edit/${id}`).then(
        successResponse => {
            if (successResponse.status != 200) {
                    return null;
                } else {
                    return successResponse.json();
                }
            },
            failResponse => {
                return null;
            }
        );
        jobs.push(job);

    let results = await Promise.all(jobs);
    console.log(results);
    return results;
}
