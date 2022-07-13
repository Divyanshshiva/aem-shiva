    alert('ajax servlet starts');
        $(document).on("click","#somebutton",function(){
            $.get("/bin/ajaxmyservlet",function(responsetext){
                var json= JSON.stringify(responsetext);
                    alert('response from servlet----'+json);
                $("#somediv").text(json);
            });
        });
    
