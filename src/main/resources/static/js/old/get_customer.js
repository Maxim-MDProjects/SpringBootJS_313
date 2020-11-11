jQuery(document).ready(function(){
    (function(){
        jQuery.ajax({
            type : "GET",
            url : "/adminrest/list",
            success: function(response){
                jQuery.each(response.name, (i, id) => {
                console.log(name)
              /*  <button type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#myModal">
                Open modal
              </button>

                let deleteButton = '<button ' +
                                        'id=' +
                                        '\"' + 'btn_delete_' + id + '\"'+
                                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                                        '>&times</button>';

                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + getUsers().id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' + 
                                            customer.id +
                                            '</button>';
                
                let tr_id = 'tr_' + user.id;
                let customerRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_first_name\">' + user.firstname.toUpperCase() + '</td>' +
                          '<td class=\"td_address\">' + customer.address + '</td>' +
                          '<td>' + deleteButton + '</td>' +
                          '</tr>';                
                $('#customerTable tbody').append(customerRow);*/
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/ap.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});