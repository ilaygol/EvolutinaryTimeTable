$(function() {
    $.ajax({
        data: "",
        url: "welcome",
        timeout: 2000,
        error: function (loginURL) {
            window.location.replace(loginURL);
            },
        success: function (clientName) {
            $("h1","#welcomeTxt").text("Welcome "+clientName.trim()+"!");
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
    setInterval(contentUpdate,2000);
})


function contentUpdate()
{
    $.ajax({
        url:"userList",
        success:function (usersList){
            refreshUsersList(usersList)
        }
    });

    $.ajax({
        url:"rows",
        success:function(rowsList){
            refreshRows(rowsList);
        }
    })
}

function refreshUsersList(usersList){
    $("#usersList").empty();
    $.each(usersList || [],function (index,user){
        $("<tr><td>"+user["m_Username"]+"</td></tr>").appendTo($("#usersList"));
    })
}


function refreshRows(rowsList){
    $("#tableBody").empty();
    $.each(rowsList || [],function (index,row){
        $("<tr>").appendTo($("#tableBody"));
        $("<td>"+row["m_HostName"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_AmountOfDays"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_AmountOfHours"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_AmountOfClasses"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_AmountOfTeachers"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_AmountOfSubjects"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_HardRolesCount"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_SoftRolesCount"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_SolvingUsers"]+"</td>").appendTo($("#tableBody"));
        $("<td>"+row["m_MaxFitness"]+"</td>").appendTo($("#tableBody"));
        $("<td><button class='btn btn-secondary' id='"+index+"'>Solve</button></td>").appendTo($("#tableBody"));
        $("</tr>").appendTo($("#tableBody"));
    })
}

