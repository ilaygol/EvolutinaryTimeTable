$(function(){
    $("#addNewMutation").click(function(){
        $.ajax({
            url:"mutation",
            type: "PUT",
            timeout:2000,
            success:(function(){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("Mutation was added successfully");
                myModal.show();
            }),
            error: (function(errorObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            })
        })
    })
})

$(function (){
    setInterval(AlgoPageContentUpdate,2000);
});

function AlgoPageContentUpdate()
{
    $.ajax({
        url:"mutation",
        type:'GET',

        success:function (mutationDataList){
            refreshMutationTableRows(mutationDataList)
        }
    });

}

function refreshMutationTableRows(mutationDataList)
{
    $("#mutationsTableBody").empty();
    $.each(mutationDataList || [],function (index,mutation){
        $("<tr><td>"+mutation["m_Name"]+
            "</td><td>"+mutation["m_Probability"]+
            "</td><td>"+mutation["m_Tupples"]+
            "</td><td>"+mutation["m_Component"]+
            "</td><td><button class='btn btn-primary updateMutation' id='"+index+"'>Update</button></td></tr>"+
            "</td><td><button class='btn btn-danger deleteMutation' id='"+index+"'>Delete</button></td></tr>").appendTo($("#mutationsTableBody"))
    })

}

