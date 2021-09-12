$(function() {
    $.ajax({
        data: "",
        url: "welcome",
        timeout: 2000,
        error: function (loginURL) {
            window.location.replace(loginURL);
            },
        success: function (clientName) {
            $("h1","#welcomeTxt").text("Welcome "+clientName+"!");
        }
    });
});

$(function() {
    $.ajax({
        url: "userList",
        success: function (usersList) {
            refreshUsersList(usersList);
        }
    });
});

$(function (){
    setInterval(userListManager,2000);
})


function userListManager()
{
    $.ajax({
        url:"userList",
        success:function (usersList){
            refreshUsersList(usersList)
        }
    })
}

function refreshUsersList(usersList){
    $("#usersList").empty();
    $.each(usersList || [],function (index,username){
        $("<tr><td>"+username+"</td></tr>").appendTo($("#usersList"));
    })
}