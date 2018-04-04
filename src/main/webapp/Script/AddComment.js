function AddComment(postId)
{    
    $.get(
        'BlogDetails/AddComment',
        { id: postId, message: $('#Comment').val() },
        function(responseJson)
            { 
                var comments = '';
                for(var i=0; i < responseJson.length; i++)
                {
                    var date = new Date(responseJson[i].date);
                    comments += 
                        "<div class='content-box middle-box'>" +
                            "<div class='middle-head' >" +
                                "By Anonymous on " + date.toLocaleString() + 
                            "</div>" +
                            "<div class='middle-content'>" +
                                responseJson[i].content +                  
                            "</div>" +
                        "</div>";
                }
                $('#Comments').html(comments); 
                $('#Comment').val('');
            },
        'json');
};



