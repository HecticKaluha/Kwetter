function ToggleAdvanced()
{
    $.get(
        'Admin/ToggleAdvanced',
        function(responseJson)
            { 
                if (responseJson)
                {
                    $('#Posts').removeClass('basic');
                    $('#ToggleAdvanced').val('Basic...');
                }
                else
                {
                    $('#Posts').addClass('basic');
                    $('#ToggleAdvanced').val('Advanced...');
                }
            },
        'json');
}

function GetPost(postId)
{    
    $.get(
        'Admin/GetPost',
        { id: postId },
        function(responseJson)
            { 
                $('#AuthorInput').val(responseJson.author); 
                $('#TitleInput').val(responseJson.title); 
                $('#ContentInput').val(responseJson.content);
                $('#Save').val('Save Post');
                $('#Save').attr('onclick', 'EditPost('+responseJson.id+')');                
            },
        'json');
};

function AddPost()
{    
    $.get(
        'Admin/AddPost',
        { author: $('#AuthorInput').val(), title: $('#TitleInput').val(), content: $('#ContentInput').val() },
        function(responseJson)
            {
                var date = new Date(responseJson.date);                           
                
                $('#Posts').find('tbody').append(
                    "<tr id='Post"+responseJson.id+"'>" +
                        "<td id='Title'>"+responseJson.title+"</td>" +
                        "<td width='160px' id='Date'>"+date.toLocaleString()+"</td>" +
                        "<td width='160px'>" +
                            "<input type='submit' value='Edit' onclick='GetPost("+responseJson.id+")' />" +
                            "<input type='submit' value='Delete' onclick='DeletePost("+responseJson.id+")'/>" +
                        "</td>" +
                    "</tr>");
                $('#Archive').append(
                    "<li><a id='TitleSide" + responseJson.id + "' href='BlogDetails?id="+responseJson.id+"'>" +                                   
                    responseJson.title+"</a></li>");
                $('#AuthorInput').val('');
                $('#TitleInput').val('');
                $('#ContentInput').val('');
            },
        'json');
};

function EditPost(postId)
{    
    $.get(
        'Admin/EditPost',
        { id: postId, author: $('#AuthorInput').val(), title: $('#TitleInput').val(), content: $('#ContentInput').val() },
        function(responseJson)
            { 
                $('#Title'+responseJson.id).html(responseJson.title);   
                $('#TitleSide'+responseJson.id).html(responseJson.title);   
                $('#Save').val('Add Post');
                $('#Save').attr('onclick', 'AddPost()');
                $('#AuthorInput').val('');
                $('#TitleInput').val('');
                $('#ContentInput').val('');
            },
        'json');
};

function DeletePost(postId)
{
    $.get(
        'Admin/DeletePost',
        { id: postId },
        function(responseJson)
            { 
                $('#Post'+responseJson).remove();
                $('#TitleSide'+responseJson).remove();
            },
        'json');
};


