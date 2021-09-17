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
    $.ajax({
        url:"rows",
        success:function(rowsList){
            refreshRows(rowsList);
        }
    });
});

$(function (){
    setInterval(contentUpdate,2000);
});


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
    $.each(usersList || [],function (index,name){
        $("<tr><td>"+name+"</td></tr>").appendTo($("#usersList"));
    })
}


function refreshRows(rowsList){
    $("#tableBody").empty();
    $.each(rowsList || [],function (index,row){
        $("<tr><td>"+row["m_HostName"]+
            "</td><td>"+row["m_AmountOfDays"]+
            "</td><td>"+row["m_AmountOfHours"]+
            "</td><td>"+row["m_AmountOfClasses"]+
            "</td><td>"+row["m_AmountOfTeachers"]+
            "</td><td>"+row["m_AmountOfSubjects"]+
            "</td><td>"+row["m_HardRolesCount"]+
            "</td><td>"+row["m_SoftRolesCount"]+
            "</td><td>"+row["m_SolvingUsers"]+
            "</td><td>"+row["m_MaxFitness"]+
            "</td><td><button class='btn btn-secondary solvebtn' id='"+index+"'>Solve</button></td></tr>").appendTo($("#tableBody"));
    })
    $(".solvebtn").click(function()
    {
        $.ajax({
            data: "managerIndex="+this.getAttribute("id"),
            url: "algo-entry",
            timeout: 2000,
            success: (function(nextURL){
                window.location.replace(nextURL);
            }),
            error: function (){
                console.log("Error moving to algo page");
            }

        })
    })
}

