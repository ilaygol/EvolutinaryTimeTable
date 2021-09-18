$(function(){
    $("#mutationsForm").submit(function(){
        $.ajax({
            data: $(this).serialize(),
            url:this.action,
            method:'POST',
            timeout:2000,
            success: function(msgObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("Mutation has been added successfully");
                myModal.show();
            },
            error: function(errorObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            }
        });
        // return value of the submit operation
        // by default - we'll always return false so it doesn't redirect the user.
        return false;

    })
})


$(function (){
    $.ajax({
        url:"mutation",
        type:'GET',
        success:function (mutationDataList){
            refreshMutationTableRows(mutationDataList)
        }
    });
});


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

function refreshMutationTableRows(mutationDataList) {
    $("#mutationsTableBody").empty();
    $.each(mutationDataList || [], function (index, mutation) {
        $("<tr><td>" + mutation["m_Name"] +
            "</td><td>" + mutation["m_Probability"] +
            "</td><td>" + mutation["m_Tupples"] +
            "</td><td>" + mutation["m_Component"] +
            "</td><td><button class='btn btn-primary updateMutation' id='" + index + "'>Update</button></td>" +
            "</td><td><button class='btn btn-danger deleteMutation' id='" + index + "'>Delete</button></td></tr>").appendTo($("#mutationsTableBody"))
    })

}

