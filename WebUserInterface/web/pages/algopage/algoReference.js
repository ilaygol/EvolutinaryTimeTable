
$(function(){
    $("#algoRefForm").submit(function(){
        var form=this;
        $.ajax({
            data: $(this).serialize(),
            url:this.action,
            method:'POST',
            timeout:2000,
            success: function(){
                if(form.generationsCheck.checked)
                {
                    var reqGenerations=form.generationsText.value;
                    document.getElementById("generationProgressBar").setAttribute("aria-valuemax",reqGenerations);;
                }

                if(form.fitnessCheck.checked) {
                    var reqFitness = form.fitnessText.value;
                    document.getElementById("fitnessProgressBar").setAttribute("aria-valuemax", reqFitness);
                }
                if(form.timeCheck.checked) {
                    var reqTimeInMinutes = parseInt(form.timeText.value);
                    var reqTimeInMillis = reqTimeInMinutes * 60000;
                    document.getElementById("timeProgressBar").setAttribute("aria-valuemax", reqTimeInMillis.toString());
                }
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("Algorithm references have been saved.");
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
});

$(function(){
    $.ajax({
        url:"algoReferences",
        timeout: 2000,
        success: function (algoRefObj){
            document.getElementById("reqGenerationTXT").value=algoRefObj["m_Generations"];
            if(algoRefObj["m_Generations"])
                document.getElementById("reqGenerationCHK").checked=true;
            document.getElementById("reqFitnessTXT").value=algoRefObj["m_Fitness"];
            if(algoRefObj["m_Fitness"])
                document.getElementById("reqFitnessCHK").checked=true;
            document.getElementById("reqTimeTXT").value=algoRefObj["m_Time"];
            if(algoRefObj["m_Time"])
                document.getElementById("reqTimeCHK").checked=true;

            document.getElementById("selectionType").value=algoRefObj["m_SelectionType"];
            document.getElementById("elitism").value=algoRefObj["m_Elitism"];
            document.getElementById("selectionPercent").value=algoRefObj["m_Percent"];
            document.getElementById("pte").value=algoRefObj["m_PTE"];
            document.getElementById("crossoverType").value=algoRefObj["m_CrossoverType"];
            document.getElementById("cuttingPoints").value=algoRefObj["m_CuttingPoints"];
            document.getElementById("aspect").value=algoRefObj["m_Aspect"];
            document.getElementById("initial").value=algoRefObj["m_Initial"];
            document.getElementById("showEvery").value=algoRefObj["m_ShowEvery"];

        },
        error: function () {
            console.log("failed to get algoRefData object");
        }
    });
});

$(function(){
    document.getElementById("pauseBtn").disabled=true;
    document.getElementById("stopBtn").disabled=true;
})

